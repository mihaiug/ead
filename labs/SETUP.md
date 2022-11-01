Java Enterprise Application Development - Environment Setup
===========================================================

In this document is described how to setup the environment in order to build and run the examples presented at _Developing Enterprise Java Applications_ course on Windows OS.

Software Requirements
---------------------

* [Java SE Development Kit 8](https://www.oracle.com/java/technologies/downloads/#java8) 
* [Apache Maven](https://maven.apache.org) 
* [Apache Tomcat](https://tomcat.apache.org)
* [Spring Tool Suite](https://spring.io/tools)


Installing the Java SE Development Kit 8
-----------------------------------------
In order to install the Java SE Development Kit 8 follow the next steps:

1. Download the Java SE Development Kit 8 installer. The current version is [JDK 8 8u351](https://www.oracle.com/java/technologies/downloads/#java8). The version you download may be newer.
2. Run the setup, accept the defaults and follow the screen instructions to install JDK and JRE.
3. Set the `JAVA_HOME` environment variable to `C:\Program Files\Java\jdk1.8.0_351` or location where you choose to install:
```bat
set JAVA_HOME="C:\Program Files\Java\jdk1.8.0_351"
```
4. Update the `PATH` environment variable to include the location where Java executables are stored. In this case `C:\Program Files\Java\jdk1.8.0_351\bin`:

```bat
set PATH="%PATH%;%JAVA_HOME%\bin"
```
To set environment variables permanently see section [How to set an Environment Variable](#how-to-set-an-environment-variable).

You can check if JDK is correct installed opening a `Command Prompt Console` and running the following command: 
``` bat
C:\Users\Admin>java -version
java version "1.8.0_351"
Java(TM) SE Runtime Environment (build 1.8.0_351-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.261-b12, mixed mode)
```

Installing the Apache Maven 
---------------------------
[Apache Maven](https://maven.apache.org/) is a build automation tool used primarily for Java projects.
In order to install the Apache Maven follow the next steps:

1. Download the [latest release](https://maven.apache.org/download.cgi),  for example  [apache-maven-3.6.3-bin.zip](https://mirrors.hostingromania.ro/apache.org/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip). The version you download may be newer. 
2. Unpack the binary archive `apache-maven-3.6.3-bin.zip` to the folder you want to install, for example `d:\tools\apache-maven-3.6.3`.
3. Check if `JAVA_HOME` point to location where JDK is installed.

``` bat
C:\Users\Admin>echo %JAVA_HOME%
C:\Program Files\Java\jdk1.8.0_351
```
4. Set the `MAVEN_HOME` environment variable to `d:\tools\apache-maven-3.6.3` (location where Maven is unpacked).
5. Update the `PATH` environment variable to include the folder where `mvn.cmd` is stored:
```bat
set PATH=%PATH%;%MAVEN_HOME%\bin
```

To set environment variables permanently see section [How to set an Environment Variable](#how-to-set-an-environment-variable).

You can check if Maven is correct installed opening a `Command Prompt Console` and running the following command: 
``` bat
C:\Users\Admin>mvn -version
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: C:\MG\tools\apache-maven-3.6.3\bin\..
Java version: 1.8.0_351, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk1.8.0_351\jre
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 7", version: "6.1", arch: "amd64", family: "windows"
```

Installing the Apache Tomcat
----------------------------
[Apache Tomcat](http://tomcat.apache.org/), is an open-source Java Servlet Container developed by the Apache Software Foundation. Tomcat implements several Java EE specifications including Java Servlet, JavaServer Pages (JSP), Java EL, and WebSocket.
In order to install the Apache Tomcat 8.5 follow the next steps:

1. Download the [latest release](https://tomcat.apache.org/download-80.cgi), for example [apache-tomcat-8.5.83.zip](https://dlcdn.apache.org/tomcat/tomcat-8/v8.5.83/bin/apache-tomcat-8.5.83.zip). The version you download may be newer like Tomcat 9.x. Please note that Tomcat 10.x is implements Jakarta EE specifications that are not compatible with Java EE specifications.
2. Unpack the binary archive `apache-tomcat-8.5.83.zip` to the folder you want to install, for example `d:\tools\apache-tomcat-8.5.83`
3. Review server settings (like server port, default value is 8080) in `d:\tools\apache-tomcat-8.5.83\conf\server.xml`
 
In order to start the server execute the following commands:
``` bat
cd /d d:\tools\apache-tomcat-8.5.83\bin
start.bat
```
In order to check installation in your favorite browser open the following link [http://localhost:8080](http://localhost:8080).



Installing the Spring Tools Suite
---------------------------------
The [Spring Tool Suite](https://spring.io/tools) is an Eclipse-based development environment that is customized for developing Spring applications.
In order to install the Spring Tools Suite for Eclipse follow the next steps:

1. Download the _Spring Tool Suite_, for example [spring-tool-suite-4-4.8.0.RELEASE-e4.17.0-win32.win32.x86_64.zip](https://download.springsource.com/release/STS4/4.8.0.RELEASE/dist/e4.17/spring-tool-suite-4-4.8.0.RELEASE-e4.17.0-win32.win32.x86_64.zip)
2. Unpack the binary archive `spring-tool-suite-4-4.8.0.RELEASE-e4.17.0-win32.win32.x86_64.zip` to the folder you want to install, for example `d:\tools\sts-4.8.0.RELEASE`
3. Launch the application `SpringToolSuite4.exe` located in `d:\tools\sts-4.8.0.RELEASE\bin` and perform the following additional configurations to add installed Tomcat 8.5 in STS servers configuration:
* Go to `Window` -> `Show View` -> `Other` -> `Server` -> `Servers`  
* In the `Servers` panel click on `No servers available...` in order to add support for Tomcat 8.x in STS 4
* Select `Apache`->`Tomcat v8.5 server`
* Select folder where Tomcat is unpacked, for example `d:\tools\apache-tomcat-8.5.83`   

How to set an Environment Variable
----------------------------------
1. Load the `System Properties` or press <kbd>WIN</kbd> + <kbd>Break</kbd> keys. 
2. Find the `Advanced Tab` in the `Properties Window`. Click `Environmental Variables`.
3. Scroll down in the `System variables` and edit or add the variable.
   
References
---------------
1. [How to install Maven on Windows, Linux, Mac](https://www.baeldung.com/install-maven-on-windows-linux-mac)

