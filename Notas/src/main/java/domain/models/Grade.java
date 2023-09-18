package domain.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Grade {
    private Long id;
    private Student student;
    private Subject subject;
    private double grade;
}
