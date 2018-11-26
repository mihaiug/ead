package ro.ucv.inf.ead.jpa.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", nullable = false, unique = true, length=64)
  private String name;
  
  @Column(name = "faculty", nullable = false)
  private String faculty;
  
  @OneToOne
  @JoinColumn(name="addressId")
  private Address address;
  
  @OneToMany (mappedBy="student" , cascade = CascadeType.ALL, orphanRemoval = true)
  private Collection<Phone> phones = new LinkedList<Phone>();
  
  @ManyToMany
  @JoinTable(
      name="student_courses",
      joinColumns={@JoinColumn(name="studentId", referencedColumnName="id")},
      inverseJoinColumns={@JoinColumn(name="courseId", referencedColumnName="id")})
  private Collection<Course> courses = new HashSet<Course>();
  
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
  
  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }
 
  public Collection<Phone> getPhones() {
    return phones;
  }

  public void setPhones(Collection<Phone> phones) {
    this.phones = phones;
  }
  
  public Collection<Course> getCourses() {
    return courses;
  }

  public void setCourses(Collection<Course> courses) {
    this.courses = courses;
  }

  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("Student (");
    sb.append(id).append(", ");
    sb.append(name).append(", ");
    sb.append(faculty);
    if(getAddress()!=null){
      sb.append(",").append(getAddress().toString());
    }
    if (getPhones() != null) {
    	sb.append(",").append(getPhones().toString());
    }
    sb.append(")");
    return sb.toString();    
  }
}
