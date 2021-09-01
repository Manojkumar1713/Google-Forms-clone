package com.spring.dao;

import org.springframework.stereotype.Repository;

import com.spring.model.User;
@Repository
public interface UserDao {
	public void addUser(User user);
	public User getUser(User user);
}
