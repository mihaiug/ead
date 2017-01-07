Servlet Life Cycle 
==================
The __life cycle__ of a servlet is controlled by the container in which the servlet has been deployed. 
When a request is mapped to a servlet, the container performs the following steps:

1. If an instance of the servlet does not exist, the web container
  * Loads the servlet class.
  * Creates an instance of the servlet class.
  * Initializes the servlet instance by calling the `init()` method. This method is called only once. 
2. Invokes the `service()` method, passing request and response objects. This method usually call `doGet()` or `doPost()` (or related methods according HTTP request type).
3. When web container shuts down or servlet is unloaded  the `destroy()` method is called.

In this example shows the _servlet lifecycle_ and how it all works out in the servlet container.
   

Requirements
------------
In order to compile and run this application the following software are needed:

* JDK 1.7 +
* [Apache Maven] (https://maven.apache.org) 
* [Apache Tomcat] (https://tomcat.apache.org)
* [Spring Tool Suite] (https://spring.io/tools)

Compile and run application
----------------------------
After you download the project in order to compile project and run the application the following operations must be performed.  

### Using Maven
To compile project using the Apache Maven execute:
``` bat
cd /d  d:\workspace\servlet-lifecycle
mvn clean package
```
This command produce the `lifecycle.war` archive that can be deployed in any servlet container like Tomcat, Jetty, etc.  

For example, to deploy this application into Tomcat Server just copy the `lifecycle.war` in `tomcat-install-directory\webapps` and start the server using following command:

``` bat
cd /d tomcat-install-directory\bin
start.bat
```

Alternatively, you can run the application using Maven Tomcat plugin:

``` bat
cd /d  d:\workspace\servlet-lifecycle
mvn tomcat7:run
```
This command launches _Maven Embeded Tomcat_ and next, open in your favorite browser the following address: [http://localhost:8000/lifecycle/] (http://localhost:8000/lifecycle/)


### Using Spring Tool Suite

To compile and run the project using STS you must perform the following steps:

* Import the project:
 - `File -> Import -> Import Existing Maven Projects`
 -  Select the folder where is located the `servlet-lifecycle` project (e.g. `d:\workspace\servlet-lifecycle`)
* Run the project:
 - From the `Package Explorer` view select the  `servlet-lifecycle` project, right click and select `Run As -> Run on Server`
