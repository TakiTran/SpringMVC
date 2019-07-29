package com.topica.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topica.model.User;
import com.topica.repository.UserDao;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public User getUser(User u) {
		List<User> users = userDao.getUser(u.getUsername());
		User user = null;
		if (!users.isEmpty()) {
			user = users.get(0);
		}
		return user;
	}
}
