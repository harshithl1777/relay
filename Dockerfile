FROM node:latest AS build-node
ARG PROJECT_NAME=RELAY_BLITZ_207
ENV PROJECT_NAME=${PROJECT_NAME}

WORKDIR /client
COPY ./client/package.json .
COPY ./decrypt-fe.sh .
RUN npm install

COPY ./client .
RUN ./decrypt-fe.sh

RUN npm run build

FROM maven:3.9.5-amazoncorretto-21 AS build
ARG PROJECT_NAME=RELAY_BLITZ_207
ENV PROJECT_NAME=${PROJECT_NAME}

WORKDIR /
COPY ./pom.xml .
COPY ./decrypt-be.sh .
COPY ./src ./src
COPY --from=build-node /client/build ./src/main/resources/static
RUN ./decrypt-be.sh
RUN mvn install -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"

FROM amazoncorretto:21-alpine3.16-jdk
WORKDIR /
COPY --from=build /target/*.jar app.jar
COPY --from=build /src ./src

EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/app.jar" ]
