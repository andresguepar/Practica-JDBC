package mapping.mapper;

import domain.models.Teacher;
import mapping.dtos.TeachersDto;

import java.util.List;

public class TeachersMapper {
    public static TeachersDto mapFrom(Teacher source){
        return new TeachersDto(source.getId(),
                source.getName(),
                source.getEmail());
    }

    public static Teacher mapFrom(TeachersDto source){
        return new Teacher(source.id(),
                source.name(),
                source.email());
    }

    public static List<Teacher> mapFrom(List<TeachersDto> source){
        return source.parallelStream()
                .map(TeachersMapper::mapFrom)
                .toList();

    }
    public static List<TeachersDto> mapFromDto(List<Teacher> source){
        return source.parallelStream()
                .map(TeachersMapper::mapFrom)
                .toList();

    }
}
