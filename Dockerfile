# Estágio 1: Build da aplicação
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app

# Copia os arquivos de configuração do Gradle
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Dá permissão de execução ao wrapper do Gradle
RUN chmod +x gradlew

# Copia o código fonte e gera o JAR (pulando os testes para agilizar o deploy)
COPY src src
RUN ./gradlew bootJar -x test

# Estágio 2: Execução da aplicação
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copia apenas o JAR gerado no estágio anterior
COPY --from=build /app/build/libs/*.jar app.jar

# Porta que o Spring Boot costuma usar
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]