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
	
	public int checkUser(User u) {
		int result = -1;
		List<User> users = userDao.getUser(u.getUsername());
		if (!users.isEmpty()) {
			if(users.get(0).getPassword().equals(u.getPassword())) {
				if (users.get(0).getRole().equals("admin")) {
					result = 0;
				} else {
					result = 1;
				}
			}
		}
		return result;
	}
}
