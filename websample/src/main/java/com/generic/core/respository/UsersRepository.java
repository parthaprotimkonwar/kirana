package com.generic.core.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generic.core.model.entities.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, String>{

	Users findByEmail(String email);
	
	Users findByEmailAndPassword(String email, String password);
}
