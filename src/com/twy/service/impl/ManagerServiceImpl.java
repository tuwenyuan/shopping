package com.twy.service.impl;

import java.util.List;

import com.twy.dao.ManagerDao;
import com.twy.dao.MenuDao;
import com.twy.dao.RoleDao;
import com.twy.dao.impl.ManagerDaoImpl;
import com.twy.dao.impl.MenuDaoImpl;
import com.twy.dao.impl.RoleDaoImpl;
import com.twy.domain.Manager;
import com.twy.domain.Menu;
import com.twy.domain.Role;
import com.twy.service.ManagerService;

public class ManagerServiceImpl implements ManagerService {
	private ManagerDao mDao = new ManagerDaoImpl();
	private MenuDao menuDao = new MenuDaoImpl();
	private RoleDao rDao = new RoleDaoImpl();
	public Manager login(String username, String password) {
		return mDao.findUser(username,password);
	}
	public List<Menu> findAllMenus() {
		return menuDao.findAllMenus();
	}
	public List<Role> findRoleByManagerId(String id) {
		return mDao.findRoleById(id);
	}
	public List<Menu> findMenuByRoleId(String id) {
		return rDao.findMenuByRoleId(id);
	}
	
}
