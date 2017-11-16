package com.twy.dao;

import com.twy.domain.User;

public interface UserDao {

	void addUser(User user);

	User findUser(String username, String password);

	User findUserByCode(String code);

	void update(User user);

}
