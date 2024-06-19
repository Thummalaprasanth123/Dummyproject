FROM openjdk:17
MAINTAINER prasanth<thummalaprasanth8393@gmail.com>
COPY target/springboot-app.jar /usr/app/
WORKDIR /usr/app/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "springboot-app.jar"]
