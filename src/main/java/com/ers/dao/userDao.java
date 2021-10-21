package com.ers.dao;

import java.sql.SQLException;

import com.ers.models.user;

public interface userDao {
	void createUser(user u) throws SQLException;
	user getUser(String username, String password);
	user updateUser(String username, String fieldname, String change);
	void deleteUser(String username);
	
}
