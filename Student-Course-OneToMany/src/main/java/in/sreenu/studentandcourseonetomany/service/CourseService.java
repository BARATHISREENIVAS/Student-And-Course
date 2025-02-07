package in.sreenu.studentandcourseonetomany.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import in.sreenu.studentandcourseonetomany.dto.CourseDto;

public interface CourseService {

	CourseDto createCourse(CourseDto courseDto);
	CourseDto getCourseById(Integer courseId);
	CourseDto getCourseByStudentIdAndCourseId(Integer courseId, Integer studentId);
	List<CourseDto> getAllCourse();
	CourseDto update(CourseDto courseDto, Integer courseId);
	void delete(Integer courseId);
	List<CourseDto> pagination(PageRequest page);
}
