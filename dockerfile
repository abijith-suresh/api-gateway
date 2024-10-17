FROM openjdk:21-oracle
COPY ./target/Gateway-0.0.1-SNAPSHOT.jar gateway-service.jar
CMD ["java","-jar","gateway-service.jar"]