package br.com.university.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.university.db.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

	Page<Registration> findByCourseName(String nameCourse, Pageable pagination);

}
