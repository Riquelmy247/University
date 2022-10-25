package br.com.university.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.university.db.Course;
import br.com.university.db.Registration;
import br.com.university.repository.CourseRepository;

public class RegistrationForm {

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String title;

	@NotNull
	@NotEmpty
	@Length(min = 10)
	private String message;

	@NotNull
	@NotEmpty
	private String nameCourse;

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setNameCourse(String nameCourse) {
		this.nameCourse = nameCourse;
	}

	public Registration convert(CourseRepository courseRepository) {
		Course course = courseRepository.findByName(nameCourse);
		return new Registration(title, message, course);
	}

}

