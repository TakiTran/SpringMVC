package com.topica.repository;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topica.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUser(String username) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("FROM User where username = :username");
		query.setParameter("username", username);
		List<User> users = null;
		users = query.getResultList();
		session.close();
		return users;
	}

}
