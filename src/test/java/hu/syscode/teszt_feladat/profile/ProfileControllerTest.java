package hu.syscode.teszt_feladat.profile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    public ProfileController profileController;

    @MockBean
    public StudentService studentService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testNewStudent() throws Exception {
        String newId = UUID.randomUUID().toString();
        log.debug("Creating new student with id " + newId);
        
        StudentDto inputStudentDto = StudentDto.builder().name("Mariah Carey").email("mariah.carey@mariahcarey.com").build();
        StudentDto outputStudentDto = StudentDto.builder().id(newId).name("Mariah Carey").email("mariah.carey@mariahcarey.com").build();

        when(studentService.save(any())).thenReturn(outputStudentDto);

        mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsBytes(inputStudentDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(newId))
                .andExpect(jsonPath("$.name").value("Mariah Carey"));
    }
}
