FROM openjdk:17
WORKDIR app
COPY target/*.jar .
ENTRYPOINT ["java", "-jar", "/opt/app/locations.jar"]