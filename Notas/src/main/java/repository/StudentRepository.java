package repository;

import domain.models.Student;
import domain.models.Subject;
import singleDomain.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface StudentRepository {
    default Connection getConnection() throws SQLException {
        return ConnectionDB.getInstance();
    }
    List<Student> getAllStudents();
    Student getStudentsById(Long id);
    void updateAddStudents(Student student);
    void deleteStudents(Long id);

}
