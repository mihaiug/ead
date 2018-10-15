Java Enterprise Application Development - Environment Setup
===========================================================

In this document is described how to setup the environment in order to build and run the examples presented at _Developing Enterprise Java Applications_ course on Windows OS.

Software Requirements
---------------------

* [Java SE Development Kit 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) 
* [Apache Maven](https://maven.apache.org) 
* [Apache Tomcat](https://tomcat.apache.org)
* [Spring Tool Suite](https://spring.io/tools)


Installing the Java SE Development Kit 8
-----------------------------------------
In order to install the Java SE Development Kit 8 follow the next steps:

1. Download the Java SE Development Kit 8 installer. The current version is [JDK 8 u181](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html). The version you download may be newer.
2. Run the setup, accept the defaults and follow the screen instructions to install JDK and JRE.
3. Set the `JAVA_HOME` environment variable to `C:\Program Files\Java\jdk1.8.0_181` or location where you choose to install.
4. Update the `PATH` environment variable to include the location where Java executables are stored. In this case `C:\Program Files\Java\jdk1.8.0_181\bin`:

```bat
set PATH=%PATH%;%JAVA_HOME%\bin
```

You can check if JDK is correct installed opening a `Command Prompt Console` and running the following commands: 
``` bat
C:\Users\Admin>java -version
java version "1.8.0_181"
Java(TM) SE Runtime Environment (build 1.8.0_181-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)
```

Installing the Apache Maven 
---------------------------
[Apache Maven](https://maven.apache.org/) is a build automation tool used primarily for Java projects.
In order to install the Apache Maven follow the next steps:

1. Download the [latest release](https://maven.apache.org/download.cgi),  for example  [apache-maven-3.5.4-bin.zip](http://mirrors.hostingromania.ro/apache.org/maven/maven-3/3.5.4/binaries/apache-maven-3.5.4-bin.zip). The version you download may be newer. 
2. Unpack the binary archive `apache-maven-3.5.4-bin.zip` to the folder you want to install, for example `d:\tools\apache-maven-3.5.4`.
3. Check if `JAVA_HOME` point to location where JDK is installed.

``` bat
C:\Users\Admin>echo %JAVA_HOME%
C:\Program Files\Java\jdk1.8.0_181
```
4. Set the `M2_HOME` and `MAVEN_HOME` environment variables to `d:\tools\apache-maven-3.5.4` (location where Maven is unpacked).
5. Update the `PATH` environment variable to include the folder where `mvn.cmd` is stored:
```bat
set PATH=%PATH%;%MAVEN_HOME%\bin
```
You can check if Maven is correct installed opening a `Command Prompt Console` and running the following command: 
``` bat
C:\Users\Admin>mvn -version
Apache Maven 3.5.2 (138edd61fd100ec658bfa2d307c43b76940a5d7d; 2017-10-18T10:58:13+03:00)
Maven home: C:\tools\apache-maven-3.5.4\bin\..
Java version: 1.8.0_181, vendor: Oracle Corporation
Java home: C:\Program Files\Java\jdk1.8.0_181\jre
Default locale: en_US, platform encoding: Cp1252
```

Installing the Apache Tomcat
----------------------------
[Apache Tomcat](http://tomcat.apache.org/), is an open-source Java Servlet Container developed by the Apache Software Foundation. Tomcat implements several Java EE specifications including Java Servlet, JavaServer Pages (JSP), Java EL, and WebSocket.
In order to install the Apache Tomcat 8.5 follow the next steps:

1. Download the [latest release](https://tomcat.apache.org/download-80.cgi), for example [apache-tomcat-8.5.34.zip](http://mirrors.m247.ro/apache/tomcat/tomcat-8/v8.5.34/bin/apache-tomcat-8.5.34.zip). The version you download may be newer.
2. Unpack the binary archive `apache-tomcat-8.5.34.zip` to the folder you want to install, for example `d:\tools\apache-tomcat-8.5.34`
3. Review server settings (like server port, default value is 8080) in `d:\tools\apache-tomcat-8.5.34\conf\server.xml`
 
In order to start the server execute the following commands:
``` bat
cd /d d:\tools\apache-tomcat-8.5.34\bin
start.bat
```

Installing the Spring Tools Suite
---------------------------------
The [Spring Tool Suite](https://spring.io/tools) is an Eclipse-based development environment that is customized for developing Spring applications.
In order to install the Spring Tools Suite for Eclipse follow the next steps:

1. Download the [latest release](https://spring.io/tools), for example [spring-tool-suite-4-4.0.0.RELEASE-e4.9.0-win32.win32.x86_64.zip]( http://download.springsource.com/release/STS4/4.0.0.RELEASE/dist/e4.9/spring-tool-suite-4-4.0.0.RELEASE-e4.9.0-win32.win32.x86_64.zip)
2. Unpack the binary archive `spring-tool-suite-4-4.0.0.RELEASE-e4.9.0-win32.win32.x86_64.zip` to the folder you want to install, for example `d:\tools\sts-4.0.0.RELEASE`
3. Launch the application `SpringToolSuite4.exe` located in `d:\tools\sts-4.0.0.RELEASE\bin` and perform the following additional configurations:
* Install [Eclipse JST Server Adapters (Apache Tomcat, JOnAS, J2EE)](https://marketplace.eclipse.org/content/eclipse-jst-server-adapters-apache-tomcat-jonas-j2ee#group-details) in order to add support for Tomcat 8.x in STS 4
* Add installed Tomcat 8.5 in STS servers configuration        

How to set an Environment Variable
----------------------------------
1. Load the `System Properties`. 
2. Find the `Advanced Tab` in the `Properties Window`. Click `Environmental Variables`.
3. Scroll down in the System variables and edit or add the variable.
   
References
---------------
1. [How to install Maven on Windows, Linux, Mac](https://www.baeldung.com/install-maven-on-windows-linux-mac)

