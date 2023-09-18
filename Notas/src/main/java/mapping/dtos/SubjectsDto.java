package mapping.dtos;

import domain.models.Teacher;

public record SubjectsDto(long id,
                          String name,
                          Teacher teacher) {
}
