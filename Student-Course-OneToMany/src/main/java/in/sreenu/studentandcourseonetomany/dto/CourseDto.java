package in.sreenu.studentandcourseonetomany.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseDto {

	private Integer courseId;
	@NotBlank(message = "Course Title can not be empty")
//	@Min(value = 15, message = "Course title should have at least 5 characters")
    private String courseTitle;
	@NotBlank(message = "Course Description can not be empty")
//	@Min(value = 15, message = "Course Description should have at least 5 characters")
    private String courseDescription;
	
	private Integer studentId;
}
