package hu.syscode.teszt_feladat.profile;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@Slf4j
public class ProfileController {

	private final StudentService service;
	
	@GetMapping("/students")
	public List<StudentDto> all() {
		return service.list();
	}

	@DeleteMapping("/students/{id}")
	public void deleteStudent(@PathVariable String id) {
		log.debug("Deleting student with id {}", id);
		service.delete(id);
	}
	
	@GetMapping("/students/{id}")
	public StudentDto one(@PathVariable String id) {
		log.debug("Getting student with id {}", id);
		return service.get(id);
	}

	@PostMapping("/students")
	public StudentDto newStudent(@RequestBody StudentDto studentDto) {
		log.debug("Adding new student {}", studentDto);
		return service.save(studentDto);
	}
	
	@PutMapping("/students/{id}")
	public StudentDto modifyStudent(@RequestBody StudentDto studentDto, @PathVariable String id) {
		log.debug("Updating student {}", studentDto);
		return service.save(studentDto, id);
	}

}
