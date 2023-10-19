#
# Build
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml -DskipTests=true clean package

FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/patient_intake-1.0.1.jar /usr/local/lib/patient_intake-1.0.1.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/patient_intake-1.0.1.jar"]