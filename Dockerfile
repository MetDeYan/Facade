FROM openjdk:11
ENV TZ=Europe/Minsk
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} api-facade-service/api-facade-service.jar
COPY ./config api-facade-service/config
#COPY .env /api-facade-service/.env
WORKDIR ./api-facade-service
ENTRYPOINT ["java", "-jar","api-facade-service.jar"]

EXPOSE 8085
