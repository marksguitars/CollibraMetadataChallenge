# CollibraMetadataChallenge
Coding Challenge for Collibra Asset Management Application

The following considerations should be taken into account when reviewing this application:

The application is based on Spring Boot 3 and Java 17.
Lombok was used for controlling certain boilerplate peristance functionality for Domain Classes.
Apacke Kafka was used for event notifications when new Assets are created.
MongoDB is the backstore used. The docker-compose file contains entries to bring up the MondoDB service which is used by this application.
The web application is also configured to be spun up by way of the docker-compose file.
A Swagger API document(assets.yanl) may be found at the root directory which contains an Open API based description of all REStful endpoints as well as schema information.
Minimal unit test code coverage was added based on time available.
