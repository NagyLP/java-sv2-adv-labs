FROM eclipse-temurin:18_36-jdk
RUN mkdir "/opt/app"
COPY target/*.jar /opt/app/locations.jar
CMD ["java", "-jar", "/opt/app/locations.jar"]