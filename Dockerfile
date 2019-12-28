FROM openjdk:8-jre

MAINTAINER Tomasz Bęben <tomek.beben@gmail.com>

ADD restaurant-app/target/restaurant-app.jar restaurant-app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar", "restaurant-app.jar"]