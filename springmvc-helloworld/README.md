Spring MVC - Hello World
==========================
In this example is implemented a simple _"Hello World"_ using [Spring MVC] (https://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html).

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
cd /d  d:\workspace\springmvc-helloworld
mvn clean package
```
This command produce the `helloworld.war` archive that can be deployed in any servlet container like Tomcat, Jetty, etc.  

For example, to deploy this application into Tomcat Server just copy the `helloworld.war` in `tomcat-install-directory\webapps` and start the server using following command:

``` bat
cd /d tomcat-install-directory\bin
start.bat
```

Alternatively, you can run the application using Maven Tomcat plugin:

``` bat
cd /d  d:\workspace\springmvc-helloworld
mvn tomcat7:run
```
This command launches _Maven Embeded Tomcat_ and next, open in your favorite browser the following address: [http://localhost:8000/springmvc-helloworld/] (http://localhost:8000/springmvc-helloworld/).


### Using Spring Tool Suite

To compile and run the project using STS you must perform the following steps:

* Import the project:
 - `File -> Import -> Import Existing Maven Projects`
 -  Select the folder where is located the `springmvc-helloworld` project (e.g. `d:\workspace\springmvc-helloworld`)
* Run the project:
 - From the `Package Explorer` view select the  `springmvc-helloworld` project, right click and select `Run As -> Run on Server`
