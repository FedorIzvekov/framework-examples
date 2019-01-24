# Http server servlet

This project is HTTP server implemented using Servlet Api and Jetty Server

## Table of Contents

* [Return to main README.md](../README.md#project-framework-examples)
* [Building and Running](#building-and-running)
* [Checking](#checking)

## Building and Running

1. Navigate to the project directory:

```
cd <projects_directory>/framework-examples/http-server-servlet
```

2. Build the project:

```
mvn clean install
```

3. Run the application:

```
java -jar ./target/http-server-servlet-1.0.0-SNAPSHOT.jar
```

## Checking

```
curl -v -i -X POST -H 'Content-Type: application/json' -d '{"email": "testFirstName@email.com", "firstName": "testFirstName", "lastName": "testLastName"}' http://localhost:8080/users
```

```
curl -v -i -X POST -H 'Content-Type: application/json' -d '{"email": "testName@email.com", "name": "testName"}' http://localhost:8080/users
```

```
curl -v -i -X POST -H 'Content-Type: application/json' -d '{"invalid": "inavalidValue"}' http://localhost:8080/users
```