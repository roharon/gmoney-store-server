FROM openjdk:8-jdk AS builder
COPY ./ /gmoney-server
WORKDIR /gmoney-server
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM openjdk:8-jdk
COPY --from=builder ./gmoney-server/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]