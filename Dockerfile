FROM openjdk:17
COPY ./target/SCAD-0.1.0.7-jar-with-dependencies.jar /tmp
WORKDIR /tmp
CMD ["java", "-jar", "SCAD-0.1.0.7-jar-with-dependencies.jar"]
#RUN ["java", "-jar", "SCAD-0.1.0.7-jar-with-dependencies.jar"]