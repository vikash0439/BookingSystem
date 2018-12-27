package com.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.bean.User;
import com.booking.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User save(User entity) {
		return userRepository.save(entity);
	}

	public User update(User entity) {
		return userRepository.save(entity);
	}

	public void delete(User entity) {
		userRepository.delete(entity);
	}

	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public boolean authenticate(String username, String password) {
		User user = this.findByEmail(username);
		if (user == null) {
			return false;
		} else {
			if (password.equals(user.getPassword()))
				return true;
			else
				return false;
		}
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void deleteInBatch(List<User> users) {
		userRepository.deleteInBatch(users);
	}

	public User find(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findAllById(id);
	}

	

}
