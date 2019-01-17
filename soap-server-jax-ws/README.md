# Soap server jax ws

This project implements a SOAP web service using JAX-WS, providing server-side functionality for handling registration-related SOAP
requests.

## Table of Contents

* [Return to main README.md](../README.md#project-framework-examples)
* [Building and Running](#building-and-running)
* [Checking](#checking)

## Building and Running

1. Navigate to the project directory:

```
cd <projects_directory>/framework-examples/soap-server-jax-ws
```

2. Build the project:

```
mvn clean install
```

3. Run the application:

```
java -jar ./target/soap-server-jax-ws-1.0.0-SNAPSHOT.jar
```

## Checking

### Check WSDL:

```
curl "http://localhost:8082/registration?wsdl"
```

### Check server:

```
curl -H "Content-Type: text/xml;charset=UTF-8" -d @client_request.xml http://localhost:8082/registration
```

### Generation Client from WSDL

```
mkdir -p ./generated && wsimport -d ./generated ./wsdl/RegistrationServiceImplService.wsdl
```