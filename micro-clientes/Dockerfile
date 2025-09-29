# Etapa 1: Construcción con Maven
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copiamos pom.xml y descargamos dependencias primero (cache eficiente)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiamos el código fuente y construimos el jar
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final liviana con solo el JAR
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copiamos el jar construido desde la etapa anterior
COPY --from=build /app/target/prueba.devsu-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto (cámbialo si tu micro corre en otro distinto)
EXPOSE 8080

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]
