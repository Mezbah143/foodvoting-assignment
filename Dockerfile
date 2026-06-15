FROM gradle:8.10.2-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/build/libs/foodvoting-0.0.1-SNAPSHOT.jar app.jar
ENV PORT=10000
EXPOSE 10000
ENTRYPOINT ["java", "-jar", "app.jar"]
