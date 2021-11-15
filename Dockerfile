# Base image
FROM openjdk:11

ADD target/photoAlbum-0.0.1-SNAPSHOT.jar dockerise-spring-boot-application.jar

ENTRYPOINT ["java", "-jar", "dockerise-spring-boot-application.jar"]

EXPOSE 8080