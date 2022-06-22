package sv2advfinalexam.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sv2advfinalexam.dto.CreateSchoolCommand;
import sv2advfinalexam.dto.CreateStudentCommand;
import sv2advfinalexam.dto.SchoolDto;
import sv2advfinalexam.exception.SchoolNotFoundException;
import sv2advfinalexam.exception.StudentNotFoundException;
import sv2advfinalexam.model.Address;
import sv2advfinalexam.model.School;
import sv2advfinalexam.model.SchoolAgeStatus;
import sv2advfinalexam.model.Student;
import sv2advfinalexam.repository.SchoolRepository;
import sv2advfinalexam.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SchoolAdministrationService {

    private SchoolRepository schoolRepository;
    private StudentRepository studentRepository;
    private ModelMapper modelMapper;

    public SchoolDto createSchool(CreateSchoolCommand createSchoolCommand) {
        Address address = new Address(createSchoolCommand.getPostalCode(), createSchoolCommand.getCity(), createSchoolCommand.getStreet(), createSchoolCommand.getHouseNumber());
        School school = new School(createSchoolCommand.getSchoolName(), address);

        schoolRepository.save(school);

        return modelMapper.map(school, SchoolDto.class);

    }

    public SchoolDto addNewStudent(long id, CreateStudentCommand createStudentCommand) {
        School school = schoolRepository.findById(id).orElseThrow(() -> new SchoolNotFoundException(id));
        Student student = new Student(createStudentCommand.getName(), createStudentCommand.getDateOfBirth());
        student.setSchool(school);
        setSchoolAgeStatus(student);

        studentRepository.save(student);

        return modelMapper.map(school, SchoolDto.class);
    }

    private void setSchoolAgeStatus(Student student) {
        if (student.calculateAge() < 16) {
            student.setSchoolAgeStatus(SchoolAgeStatus.SCHOOL_AGED);
        } else {
            student.setSchoolAgeStatus(SchoolAgeStatus.NOT_SCHOOL_AGED);
        }
    }


    public List<SchoolDto> getAllSchools(Optional<String> city) {
        return schoolRepository.findAllByCity(city)
                .stream().map(s -> modelMapper.map(s, SchoolDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public SchoolDto fireStudent(long id, long studentId) {
        School school = schoolRepository.findById(id).orElseThrow(() -> new SchoolNotFoundException(id));
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
        if (student.getSchool().getId() != school.getId()) {
            throw new StudentNotFoundException(studentId);
        }
        student.setSchool(null);
        school.getStudents().remove(student);
        return modelMapper.map(school, SchoolDto.class);
    }
}
