package com.twy.test;

import junit.framework.Assert;

import org.junit.Test;

import com.twy.dao.BookDao;
import com.twy.dao.impl.BookDaoImpl;

public class BookDaoImplTest {
	private  BookDao bDao = new  BookDaoImpl();
	@Test
	public void testGetTotalRecordsString() {
		int num = bDao.getTotalRecords(null);
		Assert.assertEquals(4, num);
		num = bDao.getTotalRecords("3GIQGXO1MQHX3O030GNDW67PPG5MPXEY");
		Assert.assertEquals(3, num);
	}

}
