package sv2advfinalexam.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sv2advfinalexam.model.Address;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateSchoolCommand {

    @NotBlank(message = "Schoolname cannot be blank!")
    private String schoolName;
    private String postalCode;
    private String city;
    private String street;
    private int houseNumber;
}
