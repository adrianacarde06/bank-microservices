# Etapa 1: Compilar la aplicación
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
# Copiamos el pom y el código
COPY pom.xml .
COPY src ./src
# Compilamos sin tests
RUN mvn clean package -DskipTests

# Etapa 2: Ejecutar la aplicación
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copiamos solo el .jar generado
COPY --from=build /app/target/*.jar app.jar
# Exponemos el puerto (informativo)
EXPOSE 8080
# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]