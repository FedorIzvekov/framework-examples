# Soap-server-jax-ws
This project implements a SOAP web service using JAX-WS, providing server-side functionality for handling registration-related SOAP requests.

* Run:
```
cd <projects_directory>/protocol-templates/soap-server-jax-ws

mvn clean install

java -jar ./target/soap-server-jax-ws-1.0.0-SNAPSHOT.jar
```

* Check WSDL:
```
curl "http://localhost:8080/registration?wsdl"
```

* Check server:
```
curl -H "Content-Type: text/xml;charset=UTF-8" -d @client_request.xml http://localhost:8080/registration
```

* WSDL Generation
```
wsgen -cp target/classes -wsdl -r ./wsdl -verbose com.fedorizvekov.soap.server.jax.ws.service.impl.RegistrationServiceImpl
```

* Generation Client from WSDL
```
mkdir -p ./generated && wsimport -d ./generated ./wsdl/RegistrationServiceImplService.wsdl
```