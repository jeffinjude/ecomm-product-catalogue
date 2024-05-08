FROM openjdk:23-jdk
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ecomm-product-catalogue.jar
ENTRYPOINT ["java","-jar","/ecomm-product-catalogue.jar"]
EXPOSE 8091