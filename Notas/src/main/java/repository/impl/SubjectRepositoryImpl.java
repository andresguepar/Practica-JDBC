package repository.impl;

import domain.models.Subject;
import domain.models.Teacher;
import repository.SubjectRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepositoryImpl implements SubjectRepository {


    private Subject createSubjetcs(ResultSet resultSet) throws
            SQLException {
        Subject subject = new Subject();
        subject.setId(resultSet.getLong("id_subject"));
        subject.setName(resultSet.getString("name"));

        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getLong("id_teacher"));
        teacher.setName(resultSet.getString("teacher"));
        teacher.setEmail(resultSet.getString("email"));

        subject.setTeacher(teacher);
        return subject;
    }

    @Override
    public List<Subject> getAllSubjects() {
        List<Subject> subjectList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT s.*, t.name as teacher, t.email " +
                     "FROM subjects s INNER JOIN teachers t ON (s.id_teacher = t.id_teacher) ;")) {
            while (resultSet.next()) {
                Subject subject = createSubjetcs(resultSet);
                subjectList.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return subjectList;
    }

    @Override
    public Subject getSubjectsById(Long id) {
        Subject subject = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT s.*, t.name as teacher, t.email " +
                        "FROM subjects s INNER JOIN teachers t ON (s.id_teacher = t.id_teacher AND s.id_subject=?);")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                subject = createSubjetcs(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subject;
    }

    @Override
    public void updateAddSubjects(Subject subject) {
        String sql;

        if (subject.getId() != null && subject.getId()>0 ) {
            sql = "UPDATE subjects SET name=?,id_teacher=? WHERE id=?";
        }else {
            sql = "INSERT INTO subjects (name,id_teacher) VALUES (?,?);";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)){
            stmt.setString(1, subject.getName());
            stmt.setLong(2, subject.getTeacher().getId());

            if (subject.getId() != null && subject.getId()>0){
                stmt.setLong(3, subject.getId());
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void deleteSubjects(Long id) {
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM subjects WHERE id =?")){
            stmt.setLong(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
