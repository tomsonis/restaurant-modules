FROM openjdk:8-jre

MAINTAINER Tomasz Bęben <tomek.beben@gmail.com>

ADD target/restaurant-app.jar restaurant-app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=docker", "restaurant-app.jar"]