FROM java:8-jre
VOLUME /tmp
ADD build/libs/kitchen-0.0.1-SNAPSHOT.jar kitchen.jar
RUN sh -c 'touch /kitchen.jar'
EXPOSE 5001
ENV JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5001"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /kitchen.jar" ]
