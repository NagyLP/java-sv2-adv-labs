package sv2advfinalexam.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sv2advfinalexam.model.SchoolAgeStatus;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDto {

    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private SchoolAgeStatus schoolAgeStatus;
}
