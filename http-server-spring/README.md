# Http-server-spring

This project implements HTTP server using Spring framework

* Run:

```
cd <projects_directory>/protocol-templates/http-server-spring

mvn clean install

java -jar ./target/http-server-spring-1.0.0-SNAPSHOT.jar
```

* Check the server:

```
curl -v -i -X POST -H 'Content-Type: application/json' -d '{"email": "test@email.com", "name": "TestName"}' http://localhost:8083/registration
```

```
curl -v -i -X POST -H 'Content-Type: application/json' -d '{"email": "test@email.com", "firstName": "TestFirstName", "lastName": "TestLastName"}' http://localhost:8083/registration
```