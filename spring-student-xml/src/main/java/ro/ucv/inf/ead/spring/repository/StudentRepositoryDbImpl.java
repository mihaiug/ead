package ro.ucv.inf.ead.spring.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import ro.ucv.inf.ead.spring.db.DBException;
import ro.ucv.inf.ead.spring.db.DBOperationException;
import ro.ucv.inf.ead.spring.model.Student;

public class StudentRepositoryDbImpl implements StudentRepository {

  private DataSource dataSource;

  public StudentRepositoryDbImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public List<Student> findAll() {
    Connection connection = getConnection();
    List<Student> students = new ArrayList<Student>();
    try {
      String query = "SELECT * FROM students";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery(query);
      while (resultSet.next()) {
        Student student = new Student();

        student.setId(resultSet.getLong("id"));
        student.setName(resultSet.getString("name"));
        student.setFaculty(resultSet.getString("faculty"));

        students.add(student);
      }
      resultSet.close();
      preparedStatement.close();
    } catch (SQLException e) {
      throw new DBOperationException(e.getMessage(), e);
    }
    return students;
  }

  @Override
  public void addStudent(Student student) {
    Connection connection = getConnection();
    String query = "INSERT INTO students (name, faculty) VALUES (?,?)";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, student.getName());
      preparedStatement.setString(2, student.getFaculty());
      preparedStatement.executeUpdate();
      preparedStatement.close();
    } catch (SQLException e) {
      throw new DBOperationException(e.getMessage(), e);
    }
  }

  private Connection getConnection() throws DBException {
    try {
      return dataSource.getConnection();
    } catch (SQLException e) {
      throw new DBException("Create connection failed: " + e.getMessage());
    }
  }

}
