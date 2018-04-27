#Docker server Java for run an Mock Server

FROM java:8

ADD docker /opt/src/

WORKDIR /opt/src/

ENTRYPOINT ["java", "-jar", "MockMock.jar", "-p", "2525", "-h", "8080"]
