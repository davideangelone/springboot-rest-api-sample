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
public class ApiControllerIntegrationTest {
	
	
	@Autowired
    private WebApplicationContext context;
	
    private MockMvc mockMvc;
    
    @BeforeEach
    public void setup() {
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    /**
     * Test for payments list
     */
    @Test
    @Order(1)
    public void testListPayments() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/payment/get"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.length()").value(3));
    }
    
    /**
     * Test for adding user (OK)
     */
    @Test
    @Order(2)
    public void testAddUserOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
        		.post("/user/add")
        		.contentType(MediaType.APPLICATION_JSON_VALUE)
        		.content("{ \"username\" : \"testuser2\", \"email\" : \"test@test.it\" }"))
            .andExpect(MockMvcResultMatchers.status().isCreated());
    }
    
    /**
     * Test for adding user (KO)
     */
    @Test
    @Order(3)
    public void testAddUserKo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
        		.post("/user/add")
        		.contentType(MediaType.APPLICATION_JSON_VALUE)
        		.content("{ \"username\" : \"testuser2\", \"email\" : \"test@test.it\" }"))
            .andExpect(MockMvcResultMatchers.status().isConflict());
    }
    
    /**
     * Test for adding payment (OK)
     */
    @Test
    @Order(4)
    public void testAddPayment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
        		.post("/payment/add")
        		.contentType(MediaType.APPLICATION_JSON_VALUE)
        		.content("{ \"tipo\" : \"cash\", \"importo\" : \"123\", \"username\" : \"testuser2\" }"))
            .andExpect(MockMvcResultMatchers.status().isCreated());
    }
    
    /**
     * Test for adding payment (KO)
     */
    @Test
    @Order(5)
    public void testAddPaymentKo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
        		.post("/payment/add")
        		.contentType(MediaType.APPLICATION_JSON_VALUE)
        		.content("{ \"tipo\" : \"cash\", \"importo\" : \"123\", \"username\" : \"userNotFound\" }"))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    
    /**
     * Test for getting payment (OK)
     */
    @Test
    @Order(6)
    public void testGetPaymentOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
        		.get("/payment/get/100"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(content().string(containsString("98765")));
    }
    
    /**
     * Test for getting payment (KO)
     */
    @Test
    @Order(7)
    public void testGetPaymentKo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
        		.get("/payment/get/999"))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    
    /**
     * Test for payment list after insert
     */
    @Test
    @Order(8)
    public void testPaymentListAfterInsert() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/payment/get"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.length()").value(4));
    }
    
    /**
     * Test for deleting payment (OK)
     */
    @Test
    @Order(9)
    public void testDeletePaymentOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
        		.delete("/payment/delete/100"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    /**
     * Test for deleting payment (KO)
     */
    @Test
    @Order(10)
    public void testDeletePaymentKo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
        		.delete("/payment/delete/999"))
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    
    /**
     * Test for list after delete
     */
    @Test
    @Order(11)
    public void testListAfterDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/payment/get"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.length()").value(3));
    }

}
