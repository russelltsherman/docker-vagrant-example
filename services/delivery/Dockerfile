FROM java:8-jre
VOLUME /tmp
ADD build/libs/delivery-0.0.1-SNAPSHOT.jar delivery.jar
RUN sh -c 'touch /delivery.jar'
EXPOSE 5002
ENV JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5002"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /delivery.jar" ]
