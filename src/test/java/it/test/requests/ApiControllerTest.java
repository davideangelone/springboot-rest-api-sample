package it.test.requests;

import static org.hamcrest.CoreMatchers.containsString;
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
     * Test for list with 3 elements
     */
    @Test
    @Order(1)
    public void testList3Elements() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/get"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.length()").value(3));
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
        		.content("{ \"tipo\" : \"cash\", \"importo\" : \"123\" }"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    /**
     * Test for getting item
     */
    @Test
    @Order(3)
    public void testGet100() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
        		.get("/api/get/100"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(content().string(containsString("98765")));
    }
    
    /**
     * Test for list after insert
     */
    @Test
    @Order(4)
    public void testListAfterInsert() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/get"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.length()").value(4));
    }
    
    /**
     * Test for deleting item
     */
    @Test
    @Order(5)
    public void testDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
        		.delete("/api/delete/1"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    /**
     * Test for list after delete
     */
    @Test
    @Order(6)
    public void testListAfterDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/get"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.length()").value(3));
    }

}
