JSP Life Cycle 
==================
The __life cycle__ of a JSP page is controlled by the container in which the web application has been deployed. 
When a request is mapped to a JSP file, the container performs the following steps:

1. When first time a JSP file is requested then the Servlet Container:
	* translate the JSP page to a Servlet 
	* compile the Servlet source
	* load classes, create a Servlet instance 
	* invoke the `jspInit()` method
2. Process the request processing via `_jspService()` method
3. When web container shuts down or web application is unloaded the `jspDestroy()` method is called

In this example shows the _JSP lifecycle_ and how it all works out in the servlet container.   

Requirements
------------
In order to compile and run this application the following software are needed:

* JDK 1.8 +
* [Apache Maven](https://maven.apache.org) 
* [Apache Tomcat](https://tomcat.apache.org)
* [Spring Tool Suite for Eclipse](https://spring.io/tools)

Compile and run the application
----------------------------
After you download the project in order to compile project and run the application the following operations must be performed.  

### Using Maven
To compile project using the Apache Maven execute:
``` bat
cd /d  d:\workspace\jsp-lifecycle
mvn clean package
```
This command produce the `jsp-lifecycle.war` archive that can be deployed in any servlet container like Tomcat, Jetty, etc.  

For example, to deploy this application into Tomcat Server just copy the `jsp-lifecycle.war` in `tomcat-install-directory\webapps` and start the server using following command:

``` bat
cd /d tomcat-install-directory\bin
start.bat
```

Alternatively, you can run the application using Maven Tomcat plugin:

``` bat
cd /d  d:\workspace\jsp-lifecycle
mvn tomcat7:run
```
This command launches _Maven Embeded Tomcat_ and next, open in your favorite browser the following address: [http://localhost:8000/jsp-lifecycle/](http://localhost:8000/jsp-lifecycle/)


### Using Spring Tool Suite

To compile and run the project using STS you must perform the following steps:

* Import the project:
 - `File -> Import -> Import Existing Maven Projects`
 -  Select the folder where is located the `jsp-lifecycle` project (e.g. `d:\workspace\jsp-lifecycle`)
* Run the project:
 - From the `Package Explorer` view select the  `jsp-lifecycle` project, right click and select `Run As -> Run on Server`
 
References
-----------
 * [Java Platform, Enterprise Edition (Java EE) 8 - The Java EE Tutorial](https://javaee.github.io/tutorial/servlets.html)
