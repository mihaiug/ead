package ro.ucv.inf.ead.jpa.model;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
  private Collection<Student> students = new HashSet<Student>();

  public Course() {
  }

  public Course(String name) {
    this.name = name;
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

  public Collection<Student> getStudents() {
    return students;
  }

  public void setStudents(Collection<Student> students) {
    this.students = students;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Course (");
    sb.append(id).append(", ");
    sb.append(name);
    sb.append(")");
    return sb.toString();
  }

}