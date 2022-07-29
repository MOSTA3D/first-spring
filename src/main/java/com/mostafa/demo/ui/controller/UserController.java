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

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.mostafa.demo.ui.model.response.UserRest;
import com.mostafa.demo.ui.model.request.UserDetailsRequestModel;
import com.mostafa.demo.ui.entitties.User;
import com.mostafa.demo.ui.interfaces.IUserService;
import com.mostafa.demo.ui.model.request.Creds;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/user")
public class UserController {
	
	private IUserService userService;
	
	@Autowired
	public UserController(IUserService userService) {
		this.userService = userService;
	}
	
	
	@GetMapping(path="/{userId}", produces = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
	})

	public ResponseEntity<UserRest> getOneUser(@PathVariable Long userId, @RequestParam(value="name", defaultValue="") String name) {
		Optional<User> optionalUser = userService.getOne(userId);
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			UserRest retUser = new UserRest(user.getFirstname(), user.getLastname(), user.getEmail(), user.getId());
			return new ResponseEntity<>(retUser, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(
		produces = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
		}
	)
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = userService.getAll();
		
		if(users.size() == 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(users, HttpStatus.OK);
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
	public ResponseEntity<UserRest> signup (@Valid @RequestBody UserDetailsRequestModel user, BindingResult result) throws Exception {
		
		if(result.hasErrors()) {
			throw new Exception("test exception message");
		}else {
			User createdUser = userService.create(user);
			UserRest retUser = new UserRest(createdUser.getFirstname(), createdUser.getLastname(), createdUser.getEmail(), createdUser.getId());
			// to-do: JWT token
			return new ResponseEntity<UserRest>(retUser, HttpStatus.OK);	
		}
	}
	
	
	@PostMapping(
		path="/signin",
		consumes= {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
		},
		produces = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
		}
	)
	public ResponseEntity<UserRest> signin(@RequestBody Creds data){
		UserDetailsRequestModel reqUser = new UserDetailsRequestModel(data.getEmail(), data.getPassword());
		System.out.println("request email is " + reqUser.getEmail());
		Optional<User> optionalUser = userService.auth(reqUser);
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();

			if(user.getPassword().equals(reqUser.getPassword())) {
				System.out.println("condition is true");
				UserRest retUser = new UserRest(user.getFirstname(), user.getLastname(),user.getEmail(), user.getId());
				return new ResponseEntity<>(retUser, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}
		
	@PutMapping(path="/{userId}",
		consumes = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
		},
		 produces = {
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
		}
	)
	public ResponseEntity<UserRest> updateUser (@PathVariable Long userId ,@RequestBody UserDetailsRequestModel user){
		User updatedUser = userService.update(userId, user);
		UserRest retUser = new UserRest(updatedUser.getFirstname(), updatedUser.getLastname(), updatedUser.getEmail(), updatedUser.getId());
		return new ResponseEntity<>(retUser, HttpStatus.OK);
	}
	
	@DeleteMapping(path="{userId}", produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
	})
	public ResponseEntity<Object> deleteUser(@PathVariable Long userId){
		userService.delete(userId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
