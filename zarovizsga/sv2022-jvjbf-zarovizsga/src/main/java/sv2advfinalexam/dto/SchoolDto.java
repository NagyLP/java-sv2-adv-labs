package sv2advfinalexam.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sv2advfinalexam.model.Address;
import sv2advfinalexam.model.Student;

import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SchoolDto {

    private Long id;
    private String schoolName;
    private Address address;
    private List<StudentDto> students;
}
