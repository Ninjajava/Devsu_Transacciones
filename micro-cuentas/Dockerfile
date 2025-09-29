# ===============================
# Etapa 1: Construcción con Maven
# ===============================
FROM maven:3.9.9-eclipse-temurin-17 AS build

# Establecer directorio de trabajo
WORKDIR /app

# Copiar pom.xml y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el resto del código fuente
COPY src ./src

# Construir el JAR del microservicio
RUN mvn clean package -DskipTests

# ===============================
# Etapa 2: Imagen ligera de ejecución
# ===============================
FROM eclipse-temurin:17-jdk-alpine

# Establecer directorio de trabajo
WORKDIR /app

# Copiar el JAR desde la etapa de build
COPY --from=build /app/target/prueba.devsu.cuenta-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto del microservicio
EXPOSE 8080

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]
