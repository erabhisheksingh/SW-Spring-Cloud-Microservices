FROM adoptopenjdk/openjdk15

COPY build/libs/*.jar /deployments/app.jar

WORKDIR /deployments

RUN chmod 600 .

CMD ["java", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]