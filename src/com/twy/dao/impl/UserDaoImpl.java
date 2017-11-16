package com.twy.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.twy.dao.UserDao;
import com.twy.domain.User;
import com.twy.exception.DaoException;
import com.twy.util.DBCPUtil;

public class UserDaoImpl implements UserDao {
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	public void addUser(User user) {
		try {
			qr.update(
					"insert into user (id,username,nick,password,phonenum,address,email,code,actived) values(?,?,?,?,?,?,?,?,?)",
					user.getId(),user.getUsername(),user.getNick(),user.getPassword(),user.getPhonenum(),user.getAddress(),user.getEmail(),user.getCode(),user.isActived());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	public User findUser(String username, String password) {
		try {
			return qr.query("select * from user where username=? and password=?", new BeanHandler<User>(User.class), username,password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	public User findUserByCode(String code) {
		try {
			return qr.query("select * from user where code=?", new BeanHandler<User>(User.class), code);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	public void update(User user) {
		try {
			qr.update(
					"update  user set username=?,nick=?,password=?,phonenum=?,address=?,email=?,code=?,actived=? where id=?",
					user.getUsername(),user.getNick(),user.getPassword(),user.getPhonenum(),user.getAddress(),user.getEmail(),user.getCode(),user.isActived(),user.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

}
