#FROM ghcr.io/carlossg/maven:3-graalvm-community-21 as build
FROM maven:3-eclipse-temurin-21 as build
# 工作目录
WORKDIR /app

COPY ./ ./

RUN mvn -s settings.xml clean package -Dmaven.test.skip=true

FROM maven:3-eclipse-temurin-21

WORKDIR /app
COPY --from=build /app/target/*.jar /app

# debian/ubuntu
RUN apt install ca-certificates -y

# 端口
EXPOSE 8080

CMD ["java", "-jar", "/app/demo-0.0.1-SNAPSHOT.jar"]