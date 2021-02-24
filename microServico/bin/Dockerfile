FROM openjdk:11
VOLUME / tmp
#*.jar pega todos os arquivos .jar da aplicação do diretorio target
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ADD customerdb.h2.db  /tmp/customerdb.h2.db
ENTRYPOINT ["java","-jar","/app.jar"]