package com.twy.dao;

import java.util.List;

import com.twy.domain.Menu;

public interface RoleDao {

	List<Menu> findMenuByRoleId(String id);

}
