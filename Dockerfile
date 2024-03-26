FROM openjdk:17
COPY ./target/SCAD.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "SCAD.jar", "db:3306", "10000"]