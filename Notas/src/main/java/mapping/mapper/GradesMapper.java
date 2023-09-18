package mapping.mapper;

import domain.models.Grade;
import mapping.dtos.GradesDto;

import java.util.List;

public class GradesMapper {
    public static GradesDto mapFrom(Grade source){
        return new GradesDto(source.getId(),
                source.getStudent(),
                source.getSubject(),
                source.getGrade());
    }

    public static Grade mapFrom(GradesDto source){
        return new Grade(source.id(),
                source.student(),
                source.subject(),
                source.grade());
    }

    public static List<Grade> mapFrom(List<GradesDto> source){
        return source.parallelStream()
                .map(GradesMapper::mapFrom)
                .toList();

    }
    public static List<GradesDto> mapFromDto(List<Grade> source){
        return source.parallelStream()
                .map(GradesMapper::mapFrom)
                .toList();

    }
}
