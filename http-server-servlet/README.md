# Http-server-servlet
This project is HTTP server implemented using Servlet Api and Jetty Server

* Run:
```
cd <projects_directory>/protocol-templates/http-server-servlet

mvn clean install

java -jar ./target/http-server-servlet-1.0.0-SNAPSHOT.jar
```

* Check:
```
curl -v -i -X POST -H 'Content-Type: application/json' -d '{"email": "testFirstName@email.com", "firstName": "testFirstName", "lastName": "testLastName"}' http://localhost:8080/registration

curl -v -i -X POST -H 'Content-Type: application/json' -d '{"email": "testName@email.com", "name": "testName"}' http://localhost:8080/registration

curl -v -i -X POST -H 'Content-Type: application/json' -d '{"invalid": "inavalidValue"}' http://localhost:8080/registration
```