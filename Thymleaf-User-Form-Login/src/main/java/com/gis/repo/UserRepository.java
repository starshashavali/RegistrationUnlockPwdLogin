package com.gis.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gis.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	
	User findByEmail(String email);

	User findByEmailAndPassword(String email, String password);


}
