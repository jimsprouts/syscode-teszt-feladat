package hu.syscode.teszt_feladat.profile;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {
	StudentDto toDto(Student student);
	Student toEntity(StudentDto studentDto);
	List<StudentDto> toDtoList(List<Student> student);
	List<Student> toEntityList(List<StudentDto> studentDto);
	void updateEntityFromDto(StudentDto  studentDto, @MappingTarget Student student);
}
