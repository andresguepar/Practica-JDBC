package repository;

import domain.models.Grade;
import domain.models.Subject;
import singleDomain.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GradeRepository {
    default Connection getConnection() throws SQLException {
        return ConnectionDB.getInstance();
    }
    List<Grade> getAllGrades();
    Grade getGradesById(Long id);
    void updateAddGrades(Grade grade);
    void deleteGrades(Long id);

}
