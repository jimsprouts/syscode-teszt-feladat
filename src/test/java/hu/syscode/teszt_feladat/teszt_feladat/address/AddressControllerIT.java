package hu.syscode.teszt_feladat.teszt_feladat.address;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressControllerIT {

	@Autowired
	private MockMvc mockMvc;

    @Test
    public void testGetAddress() throws Exception {
		mockMvc.perform(get("/address")
				.with(httpBasic("user","password")))
		.andDo(print())
		.andExpect(status().isOk())
        .andExpect(jsonPath("$.id").isNotEmpty())
		.andExpect(jsonPath("$.address").isNotEmpty())
		;
    }
}
