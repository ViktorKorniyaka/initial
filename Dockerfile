FROM ubuntu
RUN apt-get update
RUN apt-get install -y apt-utils
RUN apt-get install -y openjdk-11-jdk
RUN apt-get install -y git
RUN apt-get install unzip
RUN apt-get install wget
RUN git clone https://github.com/ViktorKorniyaka/initial.git

RUN wget https://services.gradle.org/distributions/gradle-5.1-bin.zip -P /tmp
RUN unzip -d /opt/gradle /tmp/gradle-5.1-bin.zip


ENV GRADLE_HOME=/opt/gradle/gradle-5.1
ENV PATH=$GRADLE_HOME/bin:$PATH

#RUN cd /initial
COPY build/libs/gs-spring-boot-0.1.0.jar ./
#ADD build /initial
#RUN gradle build

RUN cd /initial
CMD gradle build
#RUN gradle build
EXPOSE 8080
#RUN cd /initial
CMD chmod +x gradlew
RUN gradle build --stacktrace

CMD java -jar gs-spring-boot-0.1.0.jar
#ENTRYPOINT ["java","-jar","/initial/build/libs/gs-spring-boot-0.1.0.jar"]

#RUN java -jar build/libs/gs-spring-boot-0.1.0.jar hello.Application

#ENTRYPOINT ["java","-cp","app:app/lib/*","hello.Application"]

#ENTRYPOINT ["java","-jar","hotline-api.jar","--spring.profiles.active=test","--server.port=5555"]
