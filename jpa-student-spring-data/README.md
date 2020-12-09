Spring Data JPA Student Demo Application
===================================

This tutorial shows how can be used [Spring Data JPA Framework](http://spring.io/projects/spring-data-jpa) to implements basic CRUD operations on a database.

Database configuration
----------------------
In order to run this application you must create a MySQL database called `jpastudent`:

```sql
CREATE DATABASE jpastudent CHARACTER SET utf8 COLLATE utf8_bin;
```
Optionally, you can add a database user to connect to database. In our case we use default `root` without password. 
The database connection details can be changed from file `src/main/resources/jpaContext.xml`:

```xml 
<!-- The database connection details -->
<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
	<property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
	<property name="url" value="jdbc:mysql://localhost:3306/jpastudent?autoReconnect=true" />
	<property name="username" value="root" />
	<property name="password" value="" />
</bean>
```

Database Schema
---------------
The following image shows the database schema used in this application: 

![Database Schema](db-schema.png "Student Database Schema")

A _student_ has an address and one or more _phones_. Each _student_ is enrolled to a set of _courses_.

References
---------
1) [Spring Data JPA Tutorial](https://www.petrikainulainen.net/spring-data-jpa-tutorial/)
1) [Spring Data JPA Example + Spring Boot](https://www.devglan.com/spring-boot/spring-data-jpa-example)
2) [Spring Data JPA - Reference Documentation](https://docs.spring.io/spring-data/jpa/docs/2.1.3.RELEASE/reference/html/)
