FROM node:latest AS build-node
USER root
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
RUN mvn install -DskipTests

FROM debian:latest
RUN apt-get update -y
RUN apt-get install wget -y
RUN apt install -y gnupg
RUN wget -O - https://apt.corretto.aws/corretto.key | gpg --dearmor -o /usr/share/keyrings/corretto-keyring.gpg && \
echo "deb [signed-by=/usr/share/keyrings/corretto-keyring.gpg] https://apt.corretto.aws stable main" | tee /etc/apt/sources.list.d/corretto.list
RUN apt-get update -y
RUN apt-get install -y java-21-amazon-corretto-jdk
WORKDIR /
COPY --from=build /target/*.jar app.jar
COPY --from=build /src ./src

EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/app.jar" ]
