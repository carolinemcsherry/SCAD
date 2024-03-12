FROM openjdk:17
COPY ./target/SCAD-0.1.0.7-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "SCAD-0.1.0.7-jar-with-dependencies.jar"]