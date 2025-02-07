package in.sreenu.studentandcourseonetomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sreenu.studentandcourseonetomany.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
