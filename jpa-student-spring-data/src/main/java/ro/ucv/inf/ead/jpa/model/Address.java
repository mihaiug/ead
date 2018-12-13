package ro.ucv.inf.ead.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "city")
  private String city;

  @Column(name = "address")
  private String address;

  public Address() {
  }
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Address (");
    sb.append(id).append(", ");
    sb.append(city).append(", ");
    sb.append(address);
    sb.append(")");
    return sb.toString();
  }

}
