package mapping.mapper;

import domain.models.Subject;
import mapping.dtos.SubjectsDto;

import java.util.List;

public class SubjectsMapper {
    public static SubjectsDto mapFrom(Subject source){
        return new SubjectsDto(source.getId(),
                source.getName(),
                source.getTeacher());
    }

    public static Subject mapFrom(SubjectsDto source){
        return new Subject(source.id(),
                source.name(),
                source.teacher());
    }

    public static List<Subject> mapFrom(List<SubjectsDto> source){
        return source.parallelStream()
                .map(SubjectsMapper::mapFrom)
                .toList();

    }
    public static List<SubjectsDto> mapFromDto(List<Subject> source){
        return source.parallelStream()
                .map(SubjectsMapper::mapFrom)
                .toList();

    }
}
