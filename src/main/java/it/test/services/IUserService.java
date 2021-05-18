package it.test.services;

import java.util.List;

import it.test.model.request.UserRequest;
import it.test.model.response.UserResponse;

public interface IUserService {

	public List<UserResponse> getUsers();
	
	public UserResponse getUser(int id);
	
	public void addUser(UserRequest payment);
	
	public void deleteUser(int it);
	
	public boolean existsByUsername(String username);
	
}
