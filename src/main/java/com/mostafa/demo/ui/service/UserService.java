package com.mostafa.demo.ui.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mostafa.demo.ui.interfaces.IService;
import com.mostafa.demo.ui.model.request.UserDetailsRequestModel;
import com.mostafa.demo.ui.model.response.UserRest;
import com.mostafa.demo.ui.repository.IUserRepository;
import com.mostafa.demo.ui.entitties.User;


@Service
public class UserService implements IService<UserDetailsRequestModel, UserRest, User>{

	private IUserRepository userRepo;
	
	public UserService(IUserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public List<User> getAll() {
		return userRepo.findAll();
	}

	@Override
	public User getOne(Long id) {
		return userRepo.findById(id).get();
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
	
	
}
