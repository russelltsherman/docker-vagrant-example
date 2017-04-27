FROM java:8-jre
VOLUME /tmp
ADD build/libs/delivery-0.0.1-SNAPSHOT.jar delivery.jar
RUN sh -c 'touch /delivery.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /delivery.jar" ]
