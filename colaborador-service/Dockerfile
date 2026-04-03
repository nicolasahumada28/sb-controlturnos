#1 Construccion con uso de maven y JDK
FROM maven:3.9.9-eclipse-temurin-17-alpine AS BUILDER

#Creacion directorio
WORKDIR /app

#Copia de pom.xml y descarga de dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

#Copia codigo fuente
COPY src ./src

#Compila proyecto y genera el .jar
RUN mvn clean package -DskipTests -B

#2 Imagen ligera produccion JDK
FROM eclipse-temurin:17-jdk-alpine

# Crear un usuario sin privilegios (mejora seguridad)
RUN addgroup -S spring && adduser -S spring -G spring

#Creacion directorio
WORKDIR /app

# Crear carpeta de logs para compatibilidad con tu app
RUN mkdir -p /app/logs && chown spring:spring /app -R

#Copia el .jar en contenedor builder
COPY --from=BUILDER /app/target/*.jar app.jar

# Cambiar usuario (no root)
USER spring:spring

EXPOSE 8082

#Comando a ejecutar
ENTRYPOINT [ "java", "-jar", "app.jar" ]