FROM java:8-jre
ADD build/libs/order-0.0.1-SNAPSHOT.jar order.jar
RUN sh -c 'touch /order.jar'
EXPOSE 5000
ENV JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5000"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /order.jar" ]
