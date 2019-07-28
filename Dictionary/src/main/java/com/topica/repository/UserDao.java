package com.topica.repository;

import java.util.List;

import com.topica.model.User;

public interface UserDao {
	public List<User> getUser(String username);
}
