# WebSocket server spring

This project implements WebSocket server using Spring Framework

## Table of Contents

* [Return to main README.md](../README.md#project-framework-examples)
* [Building and Running](#building-and-running)
* [Stopping](#stopping)

## Building and Running

1. Navigate to the project directory:

```
cd <projects_directory>/framework-examples/websocket-server-spring
```

2. Build the project:

```
mvn clean install
```

3. Run the application:

```
java -jar ./target/websocket-server-spring-1.0.0-SNAPSHOT.jar
```

## Stopping

Shutdown the application:

```
curl -v -X POST http://localhost:8084/actuator/shutdown
```
