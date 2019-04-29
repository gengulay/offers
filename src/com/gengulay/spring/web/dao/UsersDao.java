package com.gengulay.spring.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("usersDao")
public class UsersDao {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void createUser(User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		session().save(user);

	}

	public boolean exists(String username) {

		return getUserByUsername(username) != null;

	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return session().createQuery("from User").list();
	}

	public User getUserByUsername(String username) {
		Criteria crit = session().createCriteria(User.class);

		crit.add(Restrictions.idEq(username));

		return (User) crit.uniqueResult();

	}

}
