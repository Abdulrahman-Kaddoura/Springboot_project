FROM eclipse-temurin:11-jre

RUN groupadd app && useradd -m -g app app

WORKDIR /app

COPY build/libs/*.jar app.jar

RUN chown -R app:app /app

USER app

EXPOSE 9092

ENTRYPOINT ["java", "-jar", "app.jar"]
