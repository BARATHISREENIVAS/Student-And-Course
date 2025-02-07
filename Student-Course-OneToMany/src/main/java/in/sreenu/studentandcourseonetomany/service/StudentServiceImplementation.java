package in.sreenu.studentandcourseonetomany.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import in.sreenu.studentandcourseonetomany.dto.StudentDto;
import in.sreenu.studentandcourseonetomany.entity.Student;
import in.sreenu.studentandcourseonetomany.exception.NotFoundException;
import in.sreenu.studentandcourseonetomany.pagination.PageResponse;
import in.sreenu.studentandcourseonetomany.repository.StudentRepository;

@Service
public class StudentServiceImplementation implements StudentService {

	private StudentRepository studentRepository;

	public StudentServiceImplementation(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	private StudentDto entityToDto(Student student) {
		StudentDto studentDto = new StudentDto();
		studentDto.setStudentId(student.getStudentId());
		studentDto.setStudentName(student.getStudentName());
		studentDto.setStudentEmail(student.getStudentEmail());
		return studentDto;
	}

	private Student dtoToEntity(StudentDto studentDto) {
		Student student = new Student();
		student.setStudentId(studentDto.getStudentId());
		student.setStudentName(studentDto.getStudentName());
		student.setStudentEmail(studentDto.getStudentEmail());
		return student;
	}

//	Creating Student
	@Override
	public StudentDto createStudent(StudentDto studentDto) {
		// TODO Auto-generated method stub
		Student student = studentRepository.save(dtoToEntity(studentDto));
		return entityToDto(student);
	}

//	Find Student By Id
	@Override
	public StudentDto getById(Integer studentId) {
		// TODO Auto-generated method stub
		Optional<Student> student = studentRepository.findById(studentId);
		if (student.isPresent()) {
			return entityToDto(student.get());
		}
		throw new NotFoundException("Student with id " + studentId + " is not available");
	}

//	Get All Students
	@Override
	public List<StudentDto> getAll() {
		// TODO Auto-generated method stub
		List<Student> student = studentRepository.findAll();
		return student.stream().map(students -> entityToDto(students)).toList();
	}

//	Updating studentId
	@Override
	public StudentDto updateStduent(StudentDto studentDto, Integer studentId) {
		// TODO Auto-generated method stub
		Optional<Student> student = studentRepository.findById(studentId);
		if(student.isPresent()) {
			Student update = studentRepository.save(dtoToEntity(studentDto));
			return entityToDto(update);
		}else {
		throw new NotFoundException("Student Id with "+ studentId + "not available to update");	
		}
	}

//	Deleting a student
	@Override
	public void deleteById(Integer studentId) {
		// TODO Auto-generated method stub
		Optional<Student> student = studentRepository.findById(studentId);
		if(student.isPresent()) {
			studentRepository.deleteById(studentId);
		}else {
			throw new NotFoundException("Student Id with "+ studentId + " is not available to delete");
		}
		
	}

	@Override
	public Student findStudentId(Integer studentId) {
		Optional<Student> student = studentRepository.findById(studentId);
		if(student.isPresent()) {
			return student.get();
		}
		throw new NotFoundException("Id not found");
	}
	@Override
	public List<StudentDto> pagination(PageRequest page){
		Page<Student> findAll = studentRepository.findAll(page);
		List<Student> student = findAll.getContent();
		List<StudentDto> studentDto= student.stream().map( pag-> entityToDto(pag)).toList();
		PageResponse response = new PageResponse();
		response.setPageNum(page.getPageNumber());
		response.setPageSize(page.getPageSize());
		response.setStudentDto(studentDto);
		return studentDto;
		
	}
	
	
}
