Advanced GuestBook using Java Servlets 
======================================

In this example is implemented a guestbook using Java Servlets.
There are two type of users: _vistors_ and _administrators_.

A _visitor_ can perform the following operations:

* View visitors messages
* Add a message


An _administrator_ can perform any _visitors_ operations and after authentication using email/password
can perform following operations:

* Delete a message


In this implementation the users messages are stored a MySQL database.

Requirements
------------
In order to compile and run this application the following software are needed:

* JDK 1.7 +
* MySQL 6 + or MariaDB Server
* [Apache Maven] (https://maven.apache.org) 
* [Apache Tomcat] (https://tomcat.apache.org)

In order to run this application you must create a MySQL database called `guestbook` and import the `guestbook.sql` structure. Optionally, you can add a database user to connect to database.
In our case we use default `root` without password. The database connection details can be changed from file `src/main/resources/config.properties`:

```properties 
#Database connection details
db.driver=com.mysql.cj.jdbc.Driver
db.url=jdbc:mysql://localhost:3306/guestbook?autoReconnect=true
db.user=root
db.password=
```

Compile and run application
----------------------------
After you download the project in order to compile project and run the application the following operations must be performed.  

### Using Maven
To compile project using the Apache Maven execute:
``` bat
cd /d  d:\workspace\guestbook-db
mvn clean package
```
This command produce the `guestbook-db.war` archive that can be deployed in any servlet container like Tomcat, Jetty, etc.  

For example, to deploy this application into Tomcat Server just copy the `guestbook-db.war` in `tomcat-install-directory\webapps` and start the server using following command:

``` bat
cd /d tomcat-install-directory\bin
start.bat
```

Alternatively, you can run the application using Maven Tomcat plugin:

``` bat
cd /d  d:\workspace\guestbook-db
mvn tomcat7:run
```
This command launches _Maven Embeded Tomcat_, and next, open in your favorite browser the following address: [http://localhost:8000/guestbook-db/] (http://localhost:8000/guestbook-db/)


### Using Spring Tool Souite

To compile and run the project using STS you must perform the following steps:

* Import the project:
 - `File -> Import -> Import Existing Maven Projects`
 -  Select the folder where is located the `guestbook` project (e.g. `d:\workspace\guestbook-db`)
* Run the project:
 - From the `Package Explorer` view select the  `guestbook-db` project, right click and select `Run As -> Run on Server`
