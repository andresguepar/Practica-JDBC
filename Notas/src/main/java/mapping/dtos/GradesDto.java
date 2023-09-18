package mapping.dtos;

import domain.models.Student;
import domain.models.Subject;

public record GradesDto(long id,
        Student student,
        Subject subject,
        double grade) {
}
