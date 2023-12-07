package hu.syscode.teszt_feladat.profile;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class StudentService {

	private final StudentRepository repository;

	private final StudentMapper mapper;

	public List<StudentDto> list() {
		return mapper.toDtoList(repository.findAll());
	}

	public void delete(String id) {
		log.debug("Deleting student with id {}", id);
		repository.deleteById(UUID.fromString(id));
	}

	public StudentDto get(String id) {
		log.debug("Gettting student with id {}", id);
		Student student = repository.findById(UUID.fromString(id)).orElseThrow(() -> new StudentNotFoundException(id));
		return mapper.toDto(student);
	}

	public StudentDto save(StudentDto studentDto) {
		log.debug("Creating new student with id {}", studentDto);
		Student entity = mapper.toEntity(studentDto);
		Student newEntity = repository.save(entity);
		return mapper.toDto(newEntity);
	}
	
	public StudentDto save(StudentDto studentDto, String id) {
		log.debug("Updating student with id {}", id);
		Student student = repository.findById(UUID.fromString(id)).orElseThrow(() -> new StudentNotFoundException(id));
		mapper.updateEntityFromDto(studentDto, student);
		log.debug("Updated student entity after copy {}", student);
		student.setId(UUID.fromString(id));
		Student updatedEntity = repository.save(student);
		return mapper.toDto(updatedEntity);
	}

}
