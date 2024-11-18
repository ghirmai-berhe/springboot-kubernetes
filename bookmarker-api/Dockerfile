# define base docker image
FROM openjdk:21

COPY target/sb_bookmarker_app.jar /usr/app/

WORKDIR /usr/app

ENTRYPOINT ["java","-jar","sb_bookmarker_app.jar"]
