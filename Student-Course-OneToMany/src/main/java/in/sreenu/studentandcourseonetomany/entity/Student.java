package in.sreenu.studentandcourseonetomany.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "StudentId")
	private Integer studentId;
	@Column(name = "StudentName")
	private String studentName;
	@Column(name = "StudentEmail")
	private String studentEmail;
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private List<Course> course;
}
