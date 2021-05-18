package it.test.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.test.exception.ResourceNotFoundException;
import it.test.model.entity.User;
import it.test.model.request.UserRequest;
import it.test.model.response.UserResponse;
import it.test.repository.UserRepository;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private Mapper mapperDozer;

	@Override
	public List<UserResponse> getUsers() {
		return StreamSupport.stream(this.repository.findAll().spliterator(), false)
				.map(from -> mapperDozer.map(from, UserResponse.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public UserResponse getUser(int id) {
		return mapperDozer.map(
				this.repository.findById(id).orElseThrow(ResourceNotFoundException::new), 
				UserResponse.class);
	}

	@Override
	public void addUser(UserRequest payment) {
		this.repository.save(mapperDozer.map(payment, User.class));
	}
	
	@Override
	public void deleteUser(int id) {
		if (this.repository.existsById(id)) {
			this.repository.deleteById(id);
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public boolean existsByUsername(String username) {
		return this.repository.existsByUsername(username);
	}

}
