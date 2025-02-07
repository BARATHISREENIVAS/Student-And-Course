package in.sreenu.studentandcourseonetomany.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import in.sreenu.studentandcourseonetomany.service.CourseService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/course")
public class CourseController {

	private CourseService courseService;

	public CourseController(CourseService courseService) {
		super();
		this.courseService = courseService;
	}

	@PostMapping("/createcourse")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CourseDto> save(@Valid @RequestBody CourseDto courseDto) {
		CourseDto create = courseService.createCourse(courseDto);
		return new ResponseEntity<CourseDto>(create, HttpStatus.CREATED);
	}

	@GetMapping("/getcoursebyid/{courseId}")
	public ResponseEntity<CourseDto> getByCourseId(@PathVariable Integer courseId) {
		CourseDto courseDto = courseService.getCourseById(courseId);
		return new ResponseEntity<CourseDto>(courseDto, HttpStatus.OK);
	}

	@GetMapping("getbystudentidandcourseid/{courseId}/{studentId}")
	public ResponseEntity<CourseDto> findByCourseIdAndStudentIs(@PathVariable Integer courseId,
			@PathVariable Integer studentId) {
		CourseDto courseDto = courseService.getCourseByStudentIdAndCourseId(courseId, studentId);
		return new ResponseEntity<CourseDto>(courseDto, HttpStatus.OK);
	}

	@GetMapping("/getallcourses")
	public ResponseEntity<CourseDto> getAll() {
		List<CourseDto> courseDto = courseService.getAllCourse();
		return new ResponseEntity(courseDto, HttpStatus.OK);
	}

	@PutMapping("/update/{courseId}")
	public ResponseEntity<CourseDto> updateCourse(@Valid @RequestBody CourseDto courseDto, @PathVariable Integer courseId) {
		CourseDto update = courseService.update(courseDto, courseId);
		return new ResponseEntity<CourseDto>(update, HttpStatus.OK);
	}
	@DeleteMapping("/delete/{courseId}")
	public ResponseEntity<String> deleteCourse(@PathVariable Integer courseId){
		courseService.delete(courseId);
		return new ResponseEntity<String>("Deleted", HttpStatus.OK);
	}
	@GetMapping("/page")
	public ResponseEntity<CourseDto> getAllPagination(
			@RequestParam(value = "pageNum", defaultValue = "0", required = false) Integer pageNum,
			@RequestParam(value="pageSize", defaultValue = "3", required = false) Integer pageSize,
			@RequestParam(value="sortBy", defaultValue = "courseId", required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "ASC", required = false) String sortDir
			){
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending():
			Sort.by(sortBy).descending();
		PageRequest pagable = PageRequest.of(pageNum, pageSize);
		List<CourseDto> findAllCategory = courseService.pagination(pagable);
			return new ResponseEntity(findAllCategory, HttpStatus.OK);
		
	}
	
}
