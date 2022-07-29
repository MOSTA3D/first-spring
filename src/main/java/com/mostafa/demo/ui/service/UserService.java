package com.mostafa.demo.ui.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import com.mostafa.demo.ui.interfaces.IUserService;
import com.mostafa.demo.ui.model.request.UserDetailsRequestModel;
import com.mostafa.demo.ui.repository.IUserRepository;
import com.mostafa.demo.ui.entitties.User;


@Service
public class UserService implements IUserService{

	private IUserRepository userRepo;
	
	@Autowired
	public UserService(IUserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public List<User> getAll() {
		return userRepo.findAll();
	}

	@Override
	public Optional<User> getOne(Long id) {
		return userRepo.findById(id);		
	}

	@Override
	public User create(UserDetailsRequestModel reqUser) {
		User user = new User(reqUser.getFirstname(), reqUser.getLastname(), reqUser.getEmail(), reqUser.getPassword());
		return userRepo.save(user);
	}

	@Override
	public User update(Long id, UserDetailsRequestModel data) {
		User user = userRepo.findById(id).get();
		user.setFirstname(data.getFirstname());
		user.setLastname(data.getLastname());
		return userRepo.save(user);
	}

	@Override
	public void delete(Long id) {
		userRepo.deleteById(id);
	}
	
	// exclusive methods
	
	public Optional<User> auth(UserDetailsRequestModel user) {
		System.out.println("before authentication");
		System.out.println(user.getEmail());
		System.out.println("after authentication");
		return userRepo.findUserByEmail(user.getEmail());
	}
	
}
