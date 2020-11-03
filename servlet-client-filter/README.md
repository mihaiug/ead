Java Servlet Filters
====================================

In this example is implemented Java Servlet Filter used to ban requests coming from a specified list of IPs. 

To use this filter you must add in `web.xml` the following entries:
``` 
<filter>
	<filter-name>ClientIPBlockFilter</filter-name>
	<filter-class>ro.ucv.inf.ead.filter.ClientIPFilter</filter-class>
	<init-param>
		<param-name>bannedIPs</param-name>
		<param-value>ip-1;ip-2;...;ip-n</param-value>
	</init-param>
</filter>

<filter-mapping>
	<filter-name>ClientIPBlockFilter</filter-name>
	<url-pattern>*</url-pattern>
</filter-mapping>
 ```


Requirements
------------
In order to compile and run this application the following software are needed:

* JDK 1.8
* [Apache Maven](https://maven.apache.org) 
* [Apache Tomcat](https://tomcat.apache.org)


Compile and run the application
----------------------------
After you download the project in order to compile project and run the application the following operations must be performed.  

### Using Maven
To compile project using the Apache Maven execute:
``` bat
cd /d  d:\workspace\servlet-client-filter
mvn clean package
```
This command produce the `client-filter.war` archive that can be deployed in any servlet container like Tomcat, Jetty, etc.  

For example, to deploy this application into Tomcat Server just copy the `client-filter.war` in `tomcat-install-directory\webapps` and start the server using following command:

``` bat
cd /d tomcat-install-directory\bin
start.bat
```

Alternatively, you can run the application using Maven Tomcat plugin:

``` bat
cd /d  d:\workspace\servlet-client-filter
mvn tomcat7:run
```
This command launches _Maven Embeded Tomcat_, and next, open in your favorite browser the following address: [http://localhost:8000/client-filter/](http://localhost:8000/client-filter/)


### Using Spring Tool Souite

To compile and run the project using STS you must perform the following steps:

* Import the project:
 - `File -> Import -> Import Existing Maven Projects`
 -  Select the folder where is located the `servlet-client-filter` project (e.g. `d:\workspace\servlet-client-filter`)
* Run the project:
 - From the `Package Explorer` view select the  `client-filter` project, right click and select `Run As -> Run on Server`
