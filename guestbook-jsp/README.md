Simple GuestBook using Servlets and JSP pages 
=============================================

In this example is implemented a simple guestbook using Java Servlets and Java Server Pages. 
The basic functionalities are the following:

* View visitors messages
* Any visitor can add a message

In this implementation the users messages are stored in memory, so at each restart the messages are lost. 

Requirements
------------
In order to compile and run this application the following software are needed:

* JDK 1.7 +
* [Apache Maven] (https://maven.apache.org) 
* [Apache Tomcat] (https://tomcat.apache.org)


Compile and run application
----------------------------
After you download the project in order to compile project and run the application the following operations must be performed.  

### Using Maven
To compile project using the Apache Maven execute:
``` bat
cd /d  d:\workspace\guestbook-jsp
mvn clean package
```
This command produce the `guestbook-jsp.war` archive that can be deployed in any servlet container like Tomcat, Jetty, etc.  

For example, to deploy this application into Tomcat Server just copy the `guestbook-jsp.war` in `tomcat-install-directory\webapps` and start the server using following command:

``` bat
cd /d tomcat-install-directory\bin
start.bat
```

Alternatively, you can run the application using Maven Tomcat plugin:

``` bat
cd /d  d:\workspace\guestbook-jsp
mvn tomcat7:run
```

This command launches _Maven Embeded Tomcat_, and next, open in your favorite browser the following address: [http://localhost:8000/guestbook-jsp/] (http://localhost:8000/guestbook-jsp/)



### Using Spring Tool Suite

To compile and run the project using STS you must perform the following steps:

* Import the project:
 - `File -> Import -> Import Existing Maven Projects`
 -  Select the folder where is located the `guestbook-jsp` project (e.g. `d:\workspace\guestbook-jsp`)
* Run the project:
 - From the `Package Explorer` view select the  `guestbook-jsp` project, right click and select `Run As -> Run on Server`
