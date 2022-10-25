package br.com.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.university.db.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	Course findByName(String name);

}
