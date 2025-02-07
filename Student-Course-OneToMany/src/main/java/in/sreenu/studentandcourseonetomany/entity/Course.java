package in.sreenu.studentandcourseonetomany.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CourseId")
	private Integer courseId;
	@Column(name="CourseTitle")
	private String courseTitle;
	@Column(name="CourseDescription")
	private String courseDescription;
	
	@ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
