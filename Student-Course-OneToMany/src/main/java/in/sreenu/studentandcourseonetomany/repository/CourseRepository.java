package in.sreenu.studentandcourseonetomany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sreenu.studentandcourseonetomany.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {


}
