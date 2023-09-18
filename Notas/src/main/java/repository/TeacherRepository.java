package repository;

import domain.models.Teacher;
import singleDomain.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface TeacherRepository {
    default Connection getConnection() throws SQLException {
        return ConnectionDB.getInstance();
    }
    List<Teacher> getAllTeachers();
    Teacher getTeacherById(Long id);
    void updateAddTeacher(Teacher teacher);
    void deleteTeacher(Long id);
}
