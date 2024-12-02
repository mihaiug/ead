Student Enrollment System (using Spring Java Configuration class)
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
     
  List<Student> students = new LinkedList<>();
  
  
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

```java
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
      throw new IllegalArgumentException("Faculty cannot be null");
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

Spring Java Configuration
-------------------------
The application beans used by IoC container are described by the following class:

```java
package ro.ucv.inf.ead.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import ro.ucv.inf.ead.spring.repository.StudentRepository;
import ro.ucv.inf.ead.spring.repository.StudentRepositoryImpl;
import ro.ucv.inf.ead.spring.service.StudentService;
import ro.ucv.inf.ead.spring.service.StudentServiceImpl;

public class ApplicationConfig {

  @Bean(name="studentRepository", initMethod = "init", destroyMethod = "shutdown" )
  @Scope("singleton")
  public StudentRepository createStudentRepository() {
    return new StudentRepositoryImpl();
  }
  
  @Bean("studentService")
  public StudentService createStudentService() {
    return new StudentServiceImpl(createStudentRepository());
  }
}
```

Requirements
------------
In order to compile and run this application the following software are needed:

* JDK 1.8 +
* [Apache Maven](https://maven.apache.org) 
* [Spring Tool Suite](https://spring.io/tools)

Compile and Run
---------------
To compile and run the project using STS you must perform the following steps:

* Import the project:
 - `File -> Import -> Import Existing Maven Projects`
 -  Select the folder where is located the `spring-student-annotations` project (e.g. `d:\workspace\spring-student-config`)
* Run the project:
 - From the `Package Explorer` view select `ro.ucv.inf.ead.spring.Application`  class from the `spring-student-config` project, right click and select `Run As -> Java Application`

 Alternatively, if you use Apache Maven the following commands must executed:
``` bat
cd /d  d:\workspace\spring-student-config
mvn clean package
mvn exec:java
```  