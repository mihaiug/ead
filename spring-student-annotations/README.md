Student Enrollment System (using Java Annotations)
========================================================

In this example we show how to use the __Spring Framework__ to implement a simple application that manipulates students enrolled to a university.

More exactly, we show how can be used the _Repository Pattern_ and a _Service Layer_ to implement an application that provide the following operations:

* enroll a `Student`
* list all enrolled `Students`

 
Model
-----
The __entity__ or __model__ is represented by the `Student` class:
 
```java
public class Student {
  private Long id;
  private String name;
  private String faculty;
  
  public Student() {
  }
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getFaculty() {
    return faculty;
  }
  public void setFaculty(String faculty) {
    this.faculty = faculty;
  }
 
}

```

Repository
----------
A __Repository__ provides an conceptual set, so we can work with a simple object that implements an interface which acts like a collection, with more complex querying capability. 

In our case the `StudentRepository` interface provide two basic operations `findAll()` and `addStudent(Student student)`:

```java
public interface StudentRepository {

  List<Student> findAll();

  void addStudent(Student student);

}
``` 

An implementation of this interface that store information in a simple list in memory is the following:

```java
@Repository("studentRepository")
public class StudentRepositoryImpl implements StudentRepository {

  private static final Logger logger = LoggerFactory.getLogger(StudentRepositoryImpl.class);

  private List<Student> students = new LinkedList<>();

  public StudentRepositoryImpl() {
    logger.debug("Create a {} object.", StudentRepositoryImpl.class.getName());
  }

  @Override
  public List<Student> findAll() {
    return students;
  }

  @Override
  public void addStudent(Student student) {
    students.add(student);
  }

  /**
   * Run after all properties are set on the bean.
   */
  @PostConstruct
  public void init() {
    logger.debug("Call StudentRepositoryImpl.init()");
    System.out.println("Call StudentRepositoryImpl.init()");
  }

   /**
   * Run when the bean is destroyed.
   */
  @PreDestroy
  public void shutdown() {
    logger.debug("Call StudentRepositoryImpl.shutdown()");
    System.out.println("Call StudentRepositoryImpl.shutdown()");
  }

}
```

Service
-------
The __Service Layer__ exposes business logic, which uses a __Repository__.
An example of a service:

```java
public interface StudentService {

  List<Student> getAllEnrolledStudents();
  void enrollStudent(Student student);

}
```

An implementation of this interface is the following:

```java
@Service("studentService")
public class StudentServiceImpl implements StudentService {
  
  private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
  
  private StudentRepository studentRepository;

  public StudentServiceImpl() {
    System.out.println("Create StudentServiceImpl using default constructor");
  }
  
  @Autowired
  public StudentServiceImpl(StudentRepository studentRepository) {
    System.out.println("Create StudentServiceImpl using constructor with args");
    this.studentRepository = studentRepository;
  }

  @Override
  public void enrollStudent(Student student) {
    logger.debug("Start enrolling user: {}", student);
    if (student.getName() == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }

    if (student.getFaculty() == null) {
      throw new IllegalArgumentException("Faculty cannot be null");
    }
    studentRepository.addStudent(student);
    logger.info("User {} was enrolled with success", student);
  }

  @Override
  public List<Student> getAllEnrolledStudents() {
    return studentRepository.findAll();
  }

  public void setStudentRepository(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }
}
```

Spring beans configuration
--------------------------
The application beans used by IoC container are described by the following class:

```java
package ro.ucv.inf.ead.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("ro.ucv.inf.ead.spring")
public class ApplicationConfig {


}
```

Alternatively, we can use a Spring bean XML configuration file `src/main/resources/applicationContext.xml` that describes the application beans and dependency injection:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
	
	<!-- Activates various annotations to be detected in bean classes -->
	<context:annotation-config />

	<!-- Scans the classpath for annotated components that will be auto-registered 
		as Spring beans. For example @Repository, @Service, @Controller. Make sure 
		to set the correct base-package -->
	<context:component-scan base-package="ro.ucv.inf.ead.spring" />

</beans>
```
This variant, based on an XML configuration file, was used in older versions of Spring, currently it is recommended to use Java configuration classes and `properties` or `YAML` files.

Requirements
------------
In order to compile and run this application the following software are needed:

* JDK 17 + 
* [Apache Maven](https://maven.apache.org) or 
* [Spring Tool Suite](https://spring.io/tools)

Compile and Run
---------------
To compile and run the project using STS you must perform the following steps:

* Import the project:
 - `File -> Import -> Import Existing Maven Projects`
 -  Select the folder where is located the `spring-student-annotations` project (e.g. `d:\workspace\spring-student-annotations`)
* Run the project:
 - From the `Package Explorer` view select `ro.ucv.inf.ead.spring.Application` class from the `spring-student-annotations` project, right click and select `Run As -> Java Application`

 Alternatively, if you use Apache Maven the following commands must executed:
``` bat
cd /d  d:\workspace\spring-student-annotations
mvn clean package
mvn exec:java
```  