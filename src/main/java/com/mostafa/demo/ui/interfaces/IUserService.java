package com.mostafa.demo.ui.interfaces;

import java.util.Optional;

import com.mostafa.demo.ui.entitties.User;
import com.mostafa.demo.ui.model.request.UserDetailsRequestModel;
import com.mostafa.demo.ui.model.response.UserRest;

public interface IUserService extends IService <UserDetailsRequestModel, UserRest, User>{
	public Optional<User> auth(UserDetailsRequestModel user);
}
