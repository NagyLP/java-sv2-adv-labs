package sv2advfinalexam.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sv2advfinalexam.dto.CreateSchoolCommand;
import sv2advfinalexam.dto.CreateStudentCommand;
import sv2advfinalexam.dto.SchoolDto;
import sv2advfinalexam.service.SchoolAdministrationService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/schools")
@AllArgsConstructor
public class SchoolController {

    private SchoolAdministrationService schoolAdministrationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SchoolDto createSchool(@Valid @RequestBody CreateSchoolCommand createSchoolCommand){
        return  schoolAdministrationService.createSchool(createSchoolCommand);
    }

    @PostMapping("/{id}/students")
    @ResponseStatus(HttpStatus.CREATED)
    public SchoolDto addNewStudent(@PathVariable("id") long id, @Valid @RequestBody CreateStudentCommand createStudentCommand){
        return schoolAdministrationService.addNewStudent(id, createStudentCommand);
    }

    @GetMapping
    public List<SchoolDto> getAllSchools(@RequestParam Optional<String> city){
        return schoolAdministrationService.getAllSchools(city);
    }

    @PutMapping("/{id}/students/{stdId}")
    public SchoolDto fireStudent(@PathVariable("id") long id, @PathVariable("stdId") long studentId){
        return schoolAdministrationService.fireStudent(id, studentId);
    }
}
