# Base image
FROM openjdk:11

ADD target/photoAlbum-0.0.1-SNAPSHOT.jar blog-api-docker.jar

ENTRYPOINT ["java", "-jar", "blog-api-docker.jar"]

EXPOSE 8080