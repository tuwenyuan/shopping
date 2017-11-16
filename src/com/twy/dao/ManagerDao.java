package com.twy.dao;

import java.util.List;

import com.twy.domain.Manager;
import com.twy.domain.Role;

public interface ManagerDao {

	Manager findUser(String username, String password);
	/**
	 * 根据用户id查询角色
	 * @param id
	 * @return
	 */
	List<Role> findRoleById(String id);

}
