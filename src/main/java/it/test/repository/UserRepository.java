package it.test.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.test.model.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	public boolean existsByUsername(String username);
	
	public User findByUsername(String username);
}
