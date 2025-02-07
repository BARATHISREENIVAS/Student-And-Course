package in.sreenu.studentandcourseonetomany.pagination;

import java.util.List;

import in.sreenu.studentandcourseonetomany.dto.CourseDto;
import in.sreenu.studentandcourseonetomany.dto.StudentDto;
import lombok.Data;

@Data
public class PageResponse {

	private List<StudentDto> studentDto;
	private List<CourseDto> courseDto;
	private Integer pageNum;
	private Integer PageSize;
}
