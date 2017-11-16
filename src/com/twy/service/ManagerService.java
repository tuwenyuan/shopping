package com.twy.service;

import java.util.List;

import com.twy.domain.Manager;
import com.twy.domain.Menu;
import com.twy.domain.Role;

public interface ManagerService {

	Manager login(String username, String password);

	List<Menu> findAllMenus();
	/**
	 * 根据U用户的id查询他的角色
	 * @param id
	 * @return
	 */
	List<Role> findRoleByManagerId(String id);
	/**
	 * 根据角色id查询他的菜单
	 * @param id
	 * @return
	 */
	List<Menu> findMenuByRoleId(String id);

}
