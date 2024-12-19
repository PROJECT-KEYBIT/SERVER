FROM openjdk:17-ea-jdk-slim
VOLUME /tmp
COPY build/libs/member-1.0.jar MemberService.jar
ENTRYPOINT ["java", "-jar", "MemberService.jar"]