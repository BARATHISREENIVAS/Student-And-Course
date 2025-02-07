package in.sreenu.studentandcourseonetomany.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class StudentDto {

	private Integer studentId;
	@NotBlank(message="Name can not be empty")
//	@Min(value = 5, message = "Name should have at leash 5 characters")
	private String studentName;
	@NotBlank(message="Email can not be empty and it should be in correct formate")
	private String studentEmail;
	
}
