FROM adoptopenjdk:8-jre-hotspot-focal
RUN adduser --system --group spring
USER spring:spring
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5859
ARG JAR_FILE
COPY ${JAR_FILE} app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "/app.jar"]
