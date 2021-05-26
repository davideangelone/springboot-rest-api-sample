package it.test.requests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import it.test.controllers.UserController;
import it.test.model.response.UserResponse;
import it.test.services.UserService;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = {"classpath:application-test.properties"})
public class ApiControllerUnitTestController {
	
	@InjectMocks
    private UserController userController;
	
	@Mock
    private UserService userService;
	    

    @Test
    public void findByIdSuccess() {
    	
        UserResponse user = mock(UserResponse.class);
        user.setId(1);
        
        when(userService.getUser(anyInt())).thenReturn(user);
        ResponseEntity<UserResponse> userDTO = userController.getUser(anyInt());
        
        verify(userService, times(1)).getUser(anyInt());
        
        assertNotNull(userDTO);
        assertEquals(userDTO.getStatusCode(), HttpStatus.OK);
        assertEquals(userDTO.getBody().getId(), user.getId());

    }

}
