FROM java:8-jdk-alpine
COPY ./esb-mock.jar ./esb-mock.jar
##WORKDIR ./usr/app
#RUN sh 'touch esb-mock-v1.jar'
ENTRYPOINT ["java", "-jar", "esb-mock.jar"]
EXPOSE 9000 61616