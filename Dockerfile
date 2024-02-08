FROM openjdk:latest
COPY ./target/SCAD-0.1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "SCAD-0.1.0.2-jar-with-dependencies.jar"]