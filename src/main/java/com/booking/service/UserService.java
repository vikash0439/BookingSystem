package com.booking.service;

import com.booking.bean.User;
import com.booking.generic.GenericService;

public interface UserService extends GenericService<User> {

	boolean authenticate(String email, String password);
	User findByEmail(String email);
	
}
