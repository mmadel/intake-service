FROM openjdk:11-jre-slim
VOLUME /tmp
ADD target/patient_intake-1.0.1.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
