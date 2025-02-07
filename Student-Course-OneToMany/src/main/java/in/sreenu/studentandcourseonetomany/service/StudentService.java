package in.sreenu.studentandcourseonetomany.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import in.sreenu.studentandcourseonetomany.dto.StudentDto;
import in.sreenu.studentandcourseonetomany.entity.Student;

public interface StudentService {

	StudentDto createStudent(StudentDto studentDto);
	StudentDto getById(Integer studentId);
	List<StudentDto> getAll();
	StudentDto updateStduent(StudentDto studentDto, Integer studentId);
	void deleteById(Integer studentId);
	Student findStudentId(Integer studentId);
	List<StudentDto> pagination(PageRequest page);
}
