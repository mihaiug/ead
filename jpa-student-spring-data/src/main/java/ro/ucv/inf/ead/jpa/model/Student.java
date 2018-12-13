package ro.ucv.inf.ead.jpa.model;

import java.util.Set;
import java.util.HashSet;

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

	@Column(name = "name", nullable = false, unique = true, length = 64)
	private String name;

	@Column(name = "faculty", nullable = false)
	private String faculty;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "addressId")
	private Address address;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Phone> phones = new HashSet<>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name = "student_courses", 
			joinColumns = { @JoinColumn(name = "studentId", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "courseId", referencedColumnName = "id") }
	)
	private Set<Course> courses = new HashSet<>();

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

	public Set<Phone> getPhones() {
		return phones;
	}

	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Student (");
		sb.append(id).append(", ");
		sb.append(name).append(", ");
		sb.append(faculty);
		if (getAddress() != null) {
			sb.append(", ").append(getAddress().toString());
		}
		sb.append(")");
		return sb.toString();
	}
}
