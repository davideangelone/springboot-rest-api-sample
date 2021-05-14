package it.test.requests;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApiControllerTest {
	
	
	@Autowired
    private WebApplicationContext context;
	
    private MockMvc mockMvc;
    
    @BeforeEach
    public void setup() {
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    /**
     * Test for empty list
     */
    @Test
    @Order(1)
    public void testEmptyList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/get"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(content().string("[]"));
    }
    
    /**
     * Test for adding item
     */
    @Test
    @Order(2)
    public void testAdd() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
        		.post("/api/add")
        		.contentType(MediaType.APPLICATION_JSON_VALUE)
        		.content("{ \"tipo\" : \"cash\", \"importo\" : 123 }"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    /**
     * Test for full list
     */
    @Test
    @Order(3)
    public void testFullList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/get"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.length()").value(1));
    }

}
