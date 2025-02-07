package in.sreenu.studentandcourseonetomany.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.sreenu.studentandcourseonetomany.dto.CourseDto;
import in.sreenu.studentandcourseonetomany.dto.StudentDto;
import in.sreenu.studentandcourseonetomany.exception.NotFoundException;
import in.sreenu.studentandcourseonetomany.service.StudentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	@PostMapping("/save")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<StudentDto> save(@Valid @RequestBody StudentDto studentDto){
		StudentDto create = studentService.createStudent(studentDto);
		return new ResponseEntity<StudentDto>(create, HttpStatus.CREATED);
	}
	
	@GetMapping("/getbyid/{studentId}")
	public ResponseEntity<StudentDto> getId(@PathVariable Integer studentId){
		StudentDto studentDto = studentService.getById(studentId);
		return new ResponseEntity<StudentDto>(studentDto, HttpStatus.OK);
		
	}
	@GetMapping("/getall")
	public ResponseEntity<StudentDto> getAll(){
		List<StudentDto> studentDto = studentService.getAll();
		return new ResponseEntity(studentDto, HttpStatus.OK);
	}
	@PutMapping("/update/{studentId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<StudentDto> updateStudent(@Valid @RequestBody StudentDto studentDto, @PathVariable Integer studentId){
		
		if(studentId.equals(studentDto.getStudentId())) {
			StudentDto update = studentService.updateStduent(studentDto, studentId);
			return new ResponseEntity<StudentDto>(update, HttpStatus.OK);
		}
		throw new NotFoundException("Student id in the RequestBody and studentId in the PathVariabke is not matching");
		
	}
	@DeleteMapping("/delete/{studentId}")
	public ResponseEntity<String> delete(@PathVariable Integer studentId){
		studentService.deleteById(studentId);
		return new ResponseEntity<String>("Deleted", HttpStatus.OK);
	}
	
	@GetMapping("/page")
	public ResponseEntity<StudentDto> page(
			@RequestParam(value = "pageNum",defaultValue = "0", required = false) Integer pageNum,
			@RequestParam(value = "pageSize",defaultValue = "2", required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = "studentId", required = false) String sortBy,
			@RequestParam(value = "SortDir",defaultValue = "ASC", required = false) String sortDir){
				
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending():
			Sort.by(sortBy).descending();
		PageRequest pagable = PageRequest.of(pageNum, pageSize);
		List<StudentDto> findAllCategory = studentService.pagination(pagable);
			return new ResponseEntity(findAllCategory, HttpStatus.OK);
		
	}
}
