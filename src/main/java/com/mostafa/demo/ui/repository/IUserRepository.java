package com.mostafa.demo.ui.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mostafa.demo.ui.entitties.User;


@Repository
public interface IUserRepository extends JpaRepository<User, Long>{

	Optional<User> findUserByEmail(String email);
	
}
