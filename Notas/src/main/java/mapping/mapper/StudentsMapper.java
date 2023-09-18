package mapping.mapper;

import domain.models.Student;
import mapping.dtos.StudentsDto;

import java.util.List;

public class StudentsMapper {
    public static StudentsDto mapFrom(Student source){
        return new StudentsDto(source.getId(),
                source.getName(),
                source.getEmail(),
                source.getSemestre()
        );
    }

    public static Student mapFrom(StudentsDto source){
        return new Student(source.id(),
                source.name(),
                source.email(),
                source.semestre());
    }

    public static List<Student> mapFrom(List<StudentsDto> source){
        return source.parallelStream()
                .map(StudentsMapper::mapFrom)
                .toList();

    }
    public static List<StudentsDto> mapFromDto(List<Student> source){
        return source.parallelStream()
                .map(StudentsMapper::mapFrom)
                .toList();

    }
}
