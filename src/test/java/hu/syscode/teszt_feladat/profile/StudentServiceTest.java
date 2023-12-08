package hu.syscode.teszt_feladat.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Spy
    private StudentMapper studentMapper = new StudentMapperImpl();

    @Test
    public void testSave() {
        UUID newId = UUID.randomUUID();

        log.debug("Creating new student with id " + newId.toString());

        StudentDto studentDto = StudentDto.builder().name("Mariah Carey").email("mariah.carey@mariahcarey.com").build();
        Student student = Student.builder().id(newId).name("Mariah Carey").email("mariah.carey@mariahcarey.com").build();

        Mockito.when(studentRepository.save(any())).thenReturn(student);

        StudentDto newStudentDto = studentService.save(studentDto);

        verify(studentRepository).save(any());
        assertEquals(newId.toString(), newStudentDto.getId());

    }

}
