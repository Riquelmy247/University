package br.com.university.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.university.db.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

}
