package com.mostafa.demo.ui.controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import com.mostafa.demo.ui.model.response.UserRest;
import com.mostafa.demo.ui.model.request.UserDetailsRequestModel;
import com.mostafa.demo.ui.entitties.User;
import com.mostafa.demo.ui.interfaces.IService;
import com.mostafa.demo.ui.model.request.UpdateFirstLast;
import com.mostafa.demo.ui.service.UserService;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class UserController {
	
	private IService<UserDetailsRequestModel, UserRest, User> userService;
	
	@Autowired
	public UserController(IService<UserDetailsRequestModel, UserRest, User> userService) {
		this.userService = userService;
	}
	
	
	@GetMapping(path="/{userId}", produces = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
	})

	public ResponseEntity<UserRest> getOneUser(@PathVariable Long userId, @RequestParam(value="name", defaultValue="") String name) {
		User user = userService.getOne(userId);
		UserRest retUser = new UserRest(user.getFirstname(), user.getLastname(), user.getEmail(), user.getId());
		return new ResponseEntity<>(retUser, HttpStatus.OK);
		
//		return new ResponseEntity<UserRest>(new UserRest("shaka", "bala", "sheka@bala.com", userId), HttpStatus.BAD_GATEWAY);
	}

	@GetMapping
	public List<User> getUsers() {
		return userService.getAll();
	}

	@PostMapping(
			consumes= {
				MediaType.APPLICATION_XML_VALUE,
				MediaType.APPLICATION_JSON_VALUE
			},
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
			})
	public ResponseEntity<UserRest> createUser (@Valid @RequestBody UserDetailsRequestModel user, BindingResult result) throws Exception {
		
		if(result.hasErrors()) {
			throw new Exception("some exception message");
		}else {
			User createdUser = userService.create(user);
			UserRest retUser = new UserRest(createdUser.getFirstname(), createdUser.getLastname(), createdUser.getEmail(), createdUser.getId());
			return new ResponseEntity<UserRest>(retUser, HttpStatus.OK);	
		}
	}
	
//	Object x = new UserRest("sd", "sdf", "skdf", "lskdf");
	
	@PutMapping(path="/{userId}", consumes = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public ResponseEntity<UserRest> updateUser (@PathVariable Long userId ,@RequestBody UserDetailsRequestModel user){
		User updatedUser = userService.update(userId, user);
		UserRest retUser = new UserRest(updatedUser.getFirstname(), updatedUser.getLastname(), updatedUser.getEmail(), updatedUser.getId());
		return new ResponseEntity<>(retUser, HttpStatus.OK);
	}
	
	@DeleteMapping(path="{userId}", consumes = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public ResponseEntity<User> deleteUser(@PathVariable Long userId){
		userService.delete(userId);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
