Java Enterprise Application Development - Environment Setup
===========================================================

This document offers a step-by-step guide to setup the development environment on Windows OS for building and executing the examples presented at _Developing Enterprise Java Applications_ course.

Software Requirements
---------------------

* [Java SE Development Kit 8](https://www.oracle.com/java/technologies/downloads/#java8) or [Java SE Development Kit 11](https://www.oracle.com/java/technologies/downloads/#java11-windows) 
* [Apache Maven](https://maven.apache.org) 
* [Apache Tomcat](https://tomcat.apache.org)
* [Spring Tool Suite for Eclipse](https://spring.io/tools)


Installing the Java SE Development Kit 8
-----------------------------------------
In order to install the Java SE Development Kit 8 follow the next steps:

1. Download the Java SE Development Kit 8 installer. The current version is [JDK 8 8u421](https://www.oracle.com/java/technologies/downloads/#java8). The version you download may be newer.
2. Run the setup, accept the defaults and follow the screen instructions to install JDK and JRE.
3. Set the `JAVA_HOME` environment variable to `C:\Program Files\Java\jdk1.8.0_421` or location where you choose to install:
```bat
set JAVA_HOME="C:\Program Files\Java\jdk1.8.0_421"
```
4. Update the `PATH` environment variable to include the location where Java executables are stored. In this case `C:\Program Files\Java\jdk1.8.0_421\bin`:

```bat
set PATH="%PATH%;%JAVA_HOME%\bin"
```
To set environment variables permanently see section [How to set an Environment Variable](#how-to-set-an-environment-variable).

You can check if JDK is correct installed opening a `Command Prompt Console` and running the following command: 
``` bat
C:\Users\Admin>java -version
java version "1.8.0_421"
Java(TM) SE Runtime Environment (build 1.8.0_421)
Java HotSpot(TM) 64-Bit Server VM (build 25.261, mixed mode)
```

Installing the Apache Maven 
---------------------------
[Apache Maven](https://maven.apache.org/) is a build automation tool used primarily for Java projects.
In order to install the Apache Maven follow the next steps:

1. Download the [latest release](https://maven.apache.org/download.cgi),  for example  [apache-maven-3.9.9-bin.zip](https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.zip). The version you download may be newer. 
2. Unpack the binary archive `apache-maven-3.9.9-bin.zip` to the folder you want to install, for example `D:\JEE\tools\apache-maven-3.9.9`.
3. Check if `JAVA_HOME` point to location where JDK is installed.

``` bat
C:\Users\Admin>echo %JAVA_HOME%
C:\Program Files\Java\jdk1.8.0_421
```
4. Set the `MAVEN_HOME` environment variable to `D:\JEE\tools\apache-maven-3.9.9` (location where Maven is unpacked).
5. Update the `PATH` environment variable to include the folder where `mvn.cmd` is stored:
```bat
set PATH=%PATH%;%MAVEN_HOME%\bin
```

To set environment variables permanently see section [How to set an Environment Variable](#how-to-set-an-environment-variable).

You can check if Maven is correct installed opening a `Command Prompt Console` and running the following command: 
``` bat
C:\Users\Admin>mvn -version
Apache Maven 3.9.9 (8e8579a9e76f7d015ee5ec7bfcdc97d260186937)
Maven home: D:\JEE\tools\apache-maven-3.9.9
Java version: 1.8.0_421, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk1.8.0_421\jre
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 11", version: "10.0", arch: "amd64", family: "windows"
```

Installing the Apache Tomcat
----------------------------
[Apache Tomcat](http://tomcat.apache.org/), is an open-source Java Servlet Container developed by the Apache Software Foundation. Tomcat implements several Java EE specifications including Java Servlet, JavaServer Pages (JSP), Java EL, and WebSocket.
In order to install the Apache Tomcat 9.0 follow the next steps:

1. Download the [latest release](https://tomcat.apache.org/download-90.cgi), for example [apache-tomcat-9.0.96.zip](https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.96/bin/apache-tomcat-9.0.96.zip). The version you download may be newer like Tomcat 9.x. Please note that Tomcat 10.x is implements Jakarta EE specifications that are not compatible with Java EE specifications.
2. Unpack the binary archive `apache-tomcat-9.0.96.zip` to the folder you want to install, for example `D:\JEE\tools\apache-tomcat-9.0.96`
3. Review server settings (like server port, default value is 8080) in `D:\JEE\tools\apache-tomcat-9.0.96\conf\server.xml`
 
In order to start the server execute the following commands:
``` bat
cd /d D:\JEE\tools\apache-tomcat-9.0.96\bin
start.bat
```
In order to check installation in your favorite browser open the following link [http://localhost:8080](http://localhost:8080).



Installing the Spring Tool Suite for Eclipse
---------------------------------
The [Spring Tool Suite for Eclipse](https://spring.io/tools) is an Eclipse-based development environment that is customized for developing Spring applications.
In order to install the Spring Tools Suite for Eclipse follow the next steps:

1. Download the _Spring Tool Suite for Eclipse_, for example [spring-tool-suite-4-4.25.0.RELEASE-e4.33.0-win32.win32.x86_64.zip](https://cdn.spring.io/spring-tools/release/STS4/4.25.0.RELEASE/dist/e4.33/spring-tool-suite-4-4.25.0.RELEASE-e4.33.0-win32.win32.x86_64.zip)
2. Unpack the binary archive `spring-tool-suite-4-4.25.0.RELEASE-e4.33.0-win32.win32.x86_64.zip` to the folder you want to install, for example `D:\JEE\tools\sts-4.25.0.RELEASE`
3. Launch the application `SpringToolSuite4.exe` located in `D:\JEE\tools\sts-4.25.0.RELEASE\bin` and perform the following additional configurations to add installed Tomcat 9.0 in STS servers configuration:
* Go to `Help` -> `Eclipse Marketplace` -> `Other` -> `Popular` and install [Eclipse Enterprise Java and Web Developer Tools](https://marketplace.eclipse.org/content/eclipse-enterprise-java-and-web-developer-tools) plugin:
    ![Install `Eclipse Enterprise Java and Web Developer Tools` plugin](images/install-eclipse-jee-and-web-plugin.png)

* Go to `Window` -> `Show View` -> `Other` -> `Server` -> `Servers`  
* In the `Servers` panel click on `No servers available...` in order to add support for Tomcat 9.x in STS 4
* Select `Apache`->`Tomcat v9.0 server`
* Select folder where Tomcat is unpacked, for example `D:\JEE\tools\apache-tomcat-9.0.96`   

How to set an Environment Variable
----------------------------------
1. Load the `System Properties` or press <kbd>WIN</kbd> + <kbd>Break</kbd> keys. 
2. Find the `Advanced Tab` in the `Properties Window`. Click `Environmental Variables`.
3. Scroll down in the `System variables` and edit or add the variable.
   
References
---------------
1. [How to install Maven on Windows, Linux, Mac](https://www.baeldung.com/install-maven-on-windows-linux-mac)

