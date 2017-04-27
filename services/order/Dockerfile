FROM java:8-jre
ADD build/libs/order-0.0.1-SNAPSHOT.jar order.jar
RUN sh -c 'touch /order.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /order.jar" ]
