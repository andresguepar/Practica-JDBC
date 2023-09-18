package repository.impl;

import domain.enums.Semestre;
import domain.models.Student;
import domain.models.Subject;
import domain.models.Teacher;
import repository.StudentRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {
    private Student createStudents(ResultSet resultSet) throws
            SQLException {
        Student student = new Student();
        student.setId(resultSet.getLong("id"));
        student.setName(resultSet.getString("name"));
        student.setEmail(resultSet.getString("email"));
        student.setSemestre(resultSet.getString("semester"));

        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from students")) {
            while (resultSet.next()) {
                Student student = createStudents(resultSet);
                studentList.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return studentList;
    }

    @Override
    public Student getStudentsById(Long id) {
        Student student = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT * FROM students WHERE id =?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student = createStudents(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public void updateAddStudents(Student student) {
        String sql;
        if (student.getId() != null && student.getId()>0 ) {
            sql = "UPDATE students SET name=?,email=?,semester=? WHERE id=?";
        }else {
            sql = "INSERT INTO students (name,email,semester) VALUES (?,?,?);";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)){
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getSemestre());


            if (student.getId() != null && student.getId()>0){
                stmt.setLong(4, student.getId());
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void deleteStudents(Long id) {
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM students WHERE id =?")){
            stmt.setLong(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
