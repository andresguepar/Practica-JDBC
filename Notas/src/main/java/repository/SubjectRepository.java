package repository;

import domain.models.Subject;
import singleDomain.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SubjectRepository {
    default Connection getConnection() throws SQLException {
        return ConnectionDB.getInstance();
    }
    List<Subject> getAllSubjects();
    Subject getSubjectsById(Long id);
    void updateAddSubjects(Subject subject);
    void deleteSubjects(Long id);
}
