package in.sreenu.studentandcourseonetomany.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import in.sreenu.studentandcourseonetomany.dto.CourseDto;
import in.sreenu.studentandcourseonetomany.entity.Course;
import in.sreenu.studentandcourseonetomany.entity.Student;
import in.sreenu.studentandcourseonetomany.exception.NotFoundException;
import in.sreenu.studentandcourseonetomany.pagination.PageResponse;
import in.sreenu.studentandcourseonetomany.repository.CourseRepository;
import in.sreenu.studentandcourseonetomany.repository.StudentRepository;

@Service
public class CourseServiceImplementation implements CourseService {

	private CourseRepository courseRepository;
	private StudentService studentService;
	private StudentRepository studentRepository;
	public CourseServiceImplementation(CourseRepository courseRepository, StudentService studentService,
			StudentRepository studentRepository) {
		super();
		this.courseRepository = courseRepository;
		this.studentService = studentService;
		this.studentRepository = studentRepository;
	}
	
	private CourseDto entityToDto(Course course) {
		CourseDto courseDto = new CourseDto();
		courseDto.setCourseId(course.getCourseId());
		courseDto.setCourseTitle(course.getCourseTitle());
		courseDto.setCourseDescription(course.getCourseDescription());
		courseDto.setStudentId(course.getStudent().getStudentId());
		return courseDto;
	}
	private Course dtoToEntity(CourseDto courseDto) {
		Student course = studentService.findStudentId(courseDto.getStudentId());
		Course cours = new Course();
		cours.setCourseId(courseDto.getCourseId());
		cours.setCourseTitle(courseDto.getCourseTitle());
		cours.setCourseDescription(courseDto.getCourseDescription());
		cours.setStudent(course);
		return cours;	
	}

	@Override
	public CourseDto createCourse(CourseDto courseDto) {
		// TODO Auto-generated method stub
		Optional<Student> student = studentRepository.findById(courseDto.getStudentId());
		if(student.isPresent()) {
			Course course = courseRepository.save(dtoToEntity(courseDto));
			return entityToDto(course);
		}
		throw new NotFoundException("Student Id with "+ courseDto.getStudentId()+" is not available to Create Course");
	}

	@Override
	public CourseDto getCourseById(Integer courseId) {
		// TODO Auto-generated method stub
		Optional<Course> course = courseRepository.findById(courseId);
		if(course.isPresent()) {
			return entityToDto(course.get());
		}
		throw new NotFoundException("Course id with "+ courseId +" is not available");
	}

	@Override
	public CourseDto getCourseByStudentIdAndCourseId(Integer courseId, Integer studentId) {
		// TODO Auto-generated method stub
		Optional<Student> student = studentRepository.findById(studentId);
		Optional<Course> course = courseRepository.findById(courseId);
		if(student.isPresent() && course.isPresent()) {
			return entityToDto(course.get());
		}
		throw new NotFoundException("Student id and course Id is not available to fetch");
	}

	@Override
	public List<CourseDto> getAllCourse() {
		// TODO Auto-generated method stub
		List<Course> course = courseRepository.findAll();
		return course.stream().map( all-> entityToDto(all)).toList();
	}

	@Override
	public CourseDto update(CourseDto courseDto, Integer courseId) {
		// TODO Auto-generated method stub
		Optional<Course> course = courseRepository.findById(courseId);
		if(course.isPresent() ) {
			Course update = courseRepository.save(dtoToEntity(courseDto));
			return entityToDto(update);
		}
		throw new NotFoundException(" CourseId is not Available to Update or CourseId in pathvaraible and requestBody is not matching");
	}

	@Override
	public void delete(Integer courseId) {
		// TODO Auto-generated method stub
		Optional<Course> course = courseRepository.findById(courseId);
		if(course.isPresent()) {
			courseRepository.deleteById(courseId);
		}else {
			throw new NotFoundException("CourseId with "+ courseId +" is not available to Delete");
		}
		
	}

	@Override
	public List<CourseDto> pagination(PageRequest page) {
		// TODO Auto-generated method stub
		Page<Course> findAll = courseRepository.findAll(page);
		List<Course> course = findAll.getContent();
		List<CourseDto> getAll = course.stream().map(cst-> entityToDto(cst)).toList();
		PageResponse pageResponse = new PageResponse();
		pageResponse.setPageNum(page.getPageNumber());
		pageResponse.setPageSize(page.getPageSize());
		pageResponse.setCourseDto(getAll);
		return getAll;
	}


	
}
