package br.com.university.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.university.controller.form.RegistrationForm;
import br.com.university.controller.form.UpdateTopicForm;
import br.com.university.db.Registration;
import br.com.university.dto.DetailsRegistrationDto;
import br.com.university.dto.RegistrationDTO;
import br.com.university.repository.CourseRepository;
import br.com.university.repository.RegistrationRepository;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	private RegistrationRepository registrationRepository;

	@Autowired
	private CourseRepository courseRepository;

	@GetMapping
	@Cacheable(value = "Lista")
	public Page<RegistrationDTO> listAll(@RequestParam(required = false) String nameCourse,
			@PageableDefault(direction = Direction.DESC, page = 0, size = 10) Pageable pagination) {

		if (nameCourse == null) {
			Page<Registration> registration = registrationRepository.findAll(pagination);
			return RegistrationDTO.convert(registration);
		} else {
			Page<Registration> registration = registrationRepository.findByCourseName(nameCourse, pagination);
			return RegistrationDTO.convert(registration);
		}
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "Lista", allEntries = true)
	public ResponseEntity<RegistrationDTO> register(@RequestBody @Valid RegistrationForm form,
			UriComponentsBuilder uriBuilder) {
		Registration registration = form.convert(courseRepository);
		registrationRepository.save(registration);

		URI uri = uriBuilder.path("/registration/{id}").buildAndExpand(registration.getId()).toUri();
		return ResponseEntity.created(uri).body(new RegistrationDTO(registration));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetailsRegistrationDto> datails(@PathVariable Long id) {
		Optional<Registration> registration = registrationRepository.findById(id);
		if (registration.isPresent()) {
			return ResponseEntity.ok(new DetailsRegistrationDto(registration.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@Transactional
	@PutMapping("/{id}")
	@CacheEvict(value = "Lista", allEntries = true)
	public ResponseEntity<RegistrationDTO> update(@PathVariable Long id, @RequestBody @Valid UpdateTopicForm form) {
		Optional<Registration> optional = registrationRepository.findById(id);
		if (optional.isPresent()) {
			Registration registration = form.update(id, registrationRepository);
			return ResponseEntity.ok(new RegistrationDTO(registration));
		}

		return ResponseEntity.notFound().build();
	}

	@Transactional
	@DeleteMapping("/{id}")
	@CacheEvict(value = "Lista", allEntries = true)
	public ResponseEntity<?> remove(@PathVariable Long id) {
		Optional<Registration> optional = registrationRepository.findById(id);
		if (optional.isPresent()) {
			registrationRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}