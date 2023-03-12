FROM openjdk:17
MAINTAINER MarkCrouch
COPY target/MetadataApplication-0.0.1-SNAPSHOT.jar metadata-application-1.0.jar
ENTRYPOINT ["java","-jar","/MetadataApplication-1.0.jar"]