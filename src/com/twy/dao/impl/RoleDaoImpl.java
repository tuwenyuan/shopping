package com.twy.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.twy.dao.RoleDao;
import com.twy.domain.Menu;
import com.twy.exception.DaoException;
import com.twy.util.DBCPUtil;

public class RoleDaoImpl implements RoleDao {
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	public List<Menu> findMenuByRoleId(String id) {
		try {
			return qr.query("select * from menu where id in (select m_id from menu_role where r_id=?)", new BeanListHandler<Menu>(Menu.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

}
