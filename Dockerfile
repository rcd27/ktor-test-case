FROM openjdk:8u252-slim
EXPOSE 8080

WORKDIR /
COPY build/libs/ktor-test-case-0.0.1.jar api.jar

ENTRYPOINT ["java","-jar","api.jar"]

