## Sakila Spring Boot Backend

A sample backend demonstrating how a Spring Backend can be mapped to a hosted MySQL server

### Install instructions:

Dependencies for the Java backend will be automatically pulled through the `pom.xml` file when building the project through Maven

The full Sakila database can be found [here](https://dev.mysql.com/doc/index-other.html), and instructions for installing MySQL server can be found [here](https://dev.mysql.com/doc/sakila/en/sakila-installation.html)


### Running the project:

Ensure the MySQL database is hosted on port `localhost:3306` and matches the defaults given in the `application.properties` file

Alternatively, create a `.env` file to configure your own database hosted elsewhere

After building the Maven project, the backend can be run through the `Main` method in `SakilaApplication.java`

This will host on `localhost:8080/` by default

Import the `sakila.postman_collection.json` into Postman to get started with some simple base queries