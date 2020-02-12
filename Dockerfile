FROM openjdk:8
ENV APP_HOME=~/development/backenddevelopment/userplatform/
WORKDIR $APP_HOME
COPY ./build/libs/* ./userplatform-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","userplatform-0.0.1-SNAPSHOT.jar"]
