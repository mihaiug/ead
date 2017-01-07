package ro.ucv.inf.ead.spring.model;

public class Student {
  private Long id;
  private String name;
  private String faculty;
  
  public Student() {
    
  }
  
  public Student(String name, String faculty) {
    this.name = name;
    this.faculty = faculty;
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
 
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("(");
    sb.append("name:").append(name).append(", ");
    sb.append("faculty:").append(faculty);
    sb.append(")");
    return sb.toString();    
  }
  
}
