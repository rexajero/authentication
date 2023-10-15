FROM openjdk:8
EXPOSE 8080
ADD target/townhouse-management-docker.jar townhouse-management-docker.jar
ENTRYPOINT [ "java", "-jar", "/townhouse-management-docker.jar"]