### Demo Application

## Description

This project is split into two main modules mainly:

* client - Frontend Angular 4 app
* server - Spring Boot application server

The purpose of this project is to create a server which handles api calls from the front end.

To carry out this project at the server side I decided to use the Hexagonal architecture.

Hexagonal Architecture is an architectural style that moves a programmer’s focus from conceptual layers to a distinction between the software’s inside and outside parts. The inside part consists of what we would call application and domain layers in a Layered Architecture – its use cases and the domain model it’s built upon. The outside part consists of everything else – UI, database, messaging, and other stuff.

## Loading Steps

In order to run this project carry out the following steps:

Assuming you already have NodeJs installed go to

```
/demo
```

In a command line run:

```
mvn clean install -Pit.test
```

**NOTE: In order to run integration tests use -Pit.test**


After having a successful build (it takes sometime since it needs to install angular-cli) you should have all project built including the installation of ***angular-cli***

When ready goto

```
/demo/server
```

And run

```
mvn spring-boot:run
```

This should launch a server running on port ***8080***

After goto

```
/dome/client/src
```

And run

```
ng serve
```

This should launch the front-end application at port ***4200***

Open a browser of your choice and type:

```
http://localhost:4200
```

The database used is an in memory H2. To access data in the database you can access the H2 console by typing the following url in the browser:

```
http://localhost:8080/h2-console
```

When prompted enter the following url int he **JDBC URL** field: ***jdbc:h2:mem:mifinity*** 

Click on connect and you should be prompted with the database.

Enjoy! :)
