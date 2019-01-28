# Http server spring

This project implements HTTP server using Spring framework

## Table of Contents

* [Return to main README.md](../README.md#project-framework-examples)
* [Building and Running](#building-and-running)
* [Checking](#checking)
* [Stopping](#stopping)

## Building and Running

1. Navigate to the project directory:

```
cd <projects_directory>/framework-examples/http-server-spring
```

2. Build the project:

```
mvn clean install
```

3. Run the application:

```
java -jar ./target/http-server-spring-1.0.0-SNAPSHOT.jar
```

## Checking

```
curl -v -i -X POST -H 'Content-Type: application/json' -d '{"email": "test@email.com", "name": "TestName"}' http://localhost:8083/users
```

```
curl -v -i -X POST -H 'Content-Type: application/json' -d '{"email": "test@email.com", "firstName": "TestFirstName", "lastName": "TestLastName"}' http://localhost:8083/users
```

## Stopping

Shutdown the application:

```
curl -v -X POST http://localhost:8083/actuator/shutdown
```
