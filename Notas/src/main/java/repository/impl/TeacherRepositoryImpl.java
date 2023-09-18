package repository.impl;

import domain.models.Teacher;
import repository.TeacherRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepositoryImpl implements TeacherRepository {

    private Teacher createTeacher(ResultSet resultSet) throws
            SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getLong("id_teacher"));
        teacher.setName(resultSet.getString("name"));
        teacher.setEmail(resultSet.getString("email"));
        return teacher;
    }
    @Override
    public Connection getConnection() throws SQLException {
        return TeacherRepository.super.getConnection();
    }
    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher> teacherList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from teachers")) {
            while (resultSet.next()) {
                Teacher teacher = createTeacher(resultSet);
                teacherList.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return teacherList;
    }

    @Override
    public Teacher getTeacherById(Long id) {
        Teacher teacher = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT * FROM teachers WHERE id_teacher =?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                teacher = createTeacher(resultSet);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }
    @Override
    public void updateAddTeacher(Teacher teacher) {
        String sql;
        if (teacher.getId() != null && teacher.getId()>0 ) {
            sql = "UPDATE teachers SET name=?,email=? WHERE id=?";
        }else {
            sql = "INSERT INTO teachers(name,email) VALUES(?,?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)){
            stmt.setString(1, teacher.getName());
            stmt.setString(2, teacher.getEmail());

           if (teacher.getId() != null && teacher.getId()>0){
               stmt.setLong(3, teacher.getId());
            }

           stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void deleteTeacher(Long id) {
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM teachers WHERE id =?")){
            stmt.setLong(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
