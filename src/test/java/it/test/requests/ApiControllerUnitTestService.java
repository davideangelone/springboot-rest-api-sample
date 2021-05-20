package it.test.requests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.dozer.Mapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import it.test.model.entity.User;
import it.test.model.request.UserRequest;
import it.test.model.response.UserResponse;
import it.test.repository.UserRepository;
import it.test.services.UserService;

@ExtendWith(MockitoExtension.class)
public class ApiControllerUnitTestService {
	
	@InjectMocks
    private UserService userService;
	
	@Mock
	private Mapper mapperDozer;
	
	@Mock
    private UserRepository userRepository;

    @Test
    public void findUserByIdOK() {
    	
        UserResponse user = mock(UserResponse.class);
        user.setId(1);
        
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(mock(User.class)));
        when(mapperDozer.map(any(), any())).thenReturn(mock(UserResponse.class));
        
        UserResponse response = userService.getUser(anyInt());
        
        verify(userRepository, times(1)).findById(anyInt());
        assertNotNull(response);
        assertEquals(response.getId(), user.getId());
         
    }
    
    
    @Test
    public void saveUserOK() {
    	
        when(mapperDozer.map(any(), any())).thenReturn(mock(User.class));
        
        userService.addUser(mock(UserRequest.class));
        verify(userRepository, times(1)).save(any(User.class));
         
    }

}
