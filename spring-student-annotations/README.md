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

``` java
@Repository("studentRepository")
public class StudentRepositoryImpl implements StudentRepository {
     
  List<Student> students = new LinkedList<Student>();
  
  
  public StudentRepositoryImpl(){
    System.out.println("Create a StudentRepositoryImpl object.");
  }
  
  public List<Student> findAll(){
    return students;
  }
  

  public void addStudent(Student student){
    students.add(student);
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
``` java
@Service("studentService")
public class StudentServiceImpl implements StudentService {
  
  @Autowired
  private StudentRepository studentRepository;

  public StudentServiceImpl() {
  }
  
  public StudentServiceImpl(StudentRepository studentRepository) {
     this.studentRepository = studentRepository;
  }
  
  public void enrollStudent(Student student) {
    if (student.getName() == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }

    if (student.getFaculty() == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }
    studentRepository.addStudent(student);
  }

  public List<Student> getAllEnrolledStudents() {
    return studentRepository.findAll();
  }
  

  public void setStudentRepository(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }
}
```

Spring configuration file
-------------------------
The Spring bean configuration file `src/main/resources/applicationContext.xml` describe application beans and dependency injection:

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

Requirements
------------
In order to compile and run this application the following software are needed:

* JDK 1.7 +
* [Apache Maven] (https://maven.apache.org) 
* [Spring Tool Suite] (https://spring.io/tools)

Compile and Run
---------------
To compile and run the project using STS you must perform the following steps:

* Import the project:
 - `File -> Import -> Import Existing Maven Projects`
 -  Select the folder where is located the `spring-student-annotations` project (e.g. `d:\workspace\spring-student-annotations`)
* Run the project:
 - From the `Package Explorer` view select `ro.ucv.inf.ead.spring.Application` or `ro.ucv.inf.ead.spring.AplicationConstr` cass from the `spring-student-annotations` project, right click and select `Run As -> Java Application`
