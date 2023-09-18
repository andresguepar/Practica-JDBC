import domain.models.Grade;
import domain.models.Student;
import domain.models.Subject;
import repository.GradeRepository;
import repository.StudentRepository;
import repository.SubjectRepository;
import repository.TeacherRepository;
import repository.impl.GradeRepositoryImpl;
import repository.impl.StudentRepositoryImpl;
import repository.impl.SubjectRepositoryImpl;
import repository.impl.TeacherRepositoryImpl;
import singleDomain.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try(Connection conn = ConnectionDB.getInstance()){

            GradeRepository gradRep = new GradeRepositoryImpl();
            StudentRepository studRep = new StudentRepositoryImpl();
            SubjectRepository subRep = new SubjectRepositoryImpl();
            TeacherRepository teachRep = new TeacherRepositoryImpl();

            /*System.out.println("Add a Teacher");
            System.out.println("Name");
            String nm = sc.next();
            System.out.println("Email");
            String em = sc.next();

            Teachers teachers = Teachers.builder()
                    .name(nmT)
                    .email(emT)
                    .build();
            teachRep.updateAddTeacher(teachers);
            teachRep.getAllTeachers().forEach(System.out::println);
             System.out.println(teachRep.getAllTeachers());

            subRep.getAllSubjects().forEach(System.out::println);
            System.out.println("Add a Subject");
            System.out.println("Name");
            String nmS = sc.next();
            System.out.println("ID Teacher");
            Long idT = sc.nextLong();

            Subject subject = Subject.builder()
                    .name(nmS)
                    .teacher(teachRep.getTeacherById(idT))
                    .build();
            subRep.updateAddSubjects(subject);

            subRep.getAllSubjects().forEach(System.out::println);

            studRep.getAllStudents().forEach(System.out::println);
            System.out.println("Add a Student");
            System.out.println("Name");
            String nmSt = sc.next();
            System.out.println("Email");
            String emSt = sc.next();
            System.out.println("Password");
            String sem = sc.next();

            Student student = Student.builder()
                    .name(nmSt)
                    .email(emSt)
                    .semestre(sem).build();
            studRep.updateAddStudents(student);
            studRep.getAllStudents().forEach(System.out::println);*/

            gradRep.getAllGrades().forEach(System.out::println);

            System.out.println("Add a Grades");
            System.out.println("ID Student");
            Long idSt = sc.nextLong();
            System.out.println("ID Subjects");
            Long idSb = sc.nextLong();
            System.out.println("Grade");
            double gr = sc.nextDouble();

            Grade grade = Grade.builder()
                    .student(studRep.getStudentsById(idSt))
                    .subject(subRep.getSubjectsById(idSb))
                    .grade(gr)
                    .build();
            gradRep.updateAddGrades(grade);
            gradRep.getAllGrades().forEach(System.out::println);


        }catch (SQLException e) {
            e.printStackTrace();



        }
    }
}
