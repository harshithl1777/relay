FROM node:latest AS build-node

WORKDIR /client
COPY ./client/package.json .
RUN npm install

COPY ./client .
RUN npm run build

FROM maven:3.9.5-amazoncorretto-21 AS build

WORKDIR /
COPY ./pom.xml ./
COPY ./decrypt.sh ./
COPY ./src ./src
COPY --from=build-node /client/build ./src/main/resources/static
RUN ./decrypt.sh
RUN mvn install -DskipTests

FROM amazoncorretto:21-alpine3.16-jdk
WORKDIR /
COPY --from=build /target/*.jar app.jar
COPY --from=build /src ./src

EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/app.jar" ]
