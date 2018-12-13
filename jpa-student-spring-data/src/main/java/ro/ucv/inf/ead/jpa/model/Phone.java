package ro.ucv.inf.ead.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phones")
public class Phone {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "number", nullable = false, unique = true)
  private String number;

  @Column(name = "type")
  private String type;

  @ManyToOne
  @JoinColumn(name = "studentId")
  private Student student;

  public Phone() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Phone (");
    sb.append(id).append(", ");
    sb.append(number);
    sb.append(",").append(type);
    sb.append(")");
    return sb.toString();
  }

}