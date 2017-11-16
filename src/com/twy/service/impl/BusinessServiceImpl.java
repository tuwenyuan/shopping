package com.twy.service.impl;

import java.util.List;


import com.twy.dao.BookDao;
import com.twy.dao.CategoryDao;
import com.twy.dao.OrdersDao;
import com.twy.dao.UserDao;
import com.twy.domain.Book;
import com.twy.domain.Category;
import com.twy.domain.Orders;
import com.twy.domain.OrdersItem;
import com.twy.domain.User;
import com.twy.factory.BeanFactory;
import com.twy.service.BusinessService;
import com.twy.util.IdGenerator;
import com.twy.web.bean.Page;

public class BusinessServiceImpl implements BusinessService {
	private CategoryDao cDao = BeanFactory.getInstance().getDaoImpl("CategoryDao",CategoryDao.class);//到底是哪个由自己维护的。由外部维护，控制权交给外部的，控制反转
	private BookDao bDao =BeanFactory.getInstance().getDaoImpl("BookDao",BookDao.class);
	private UserDao uDao = BeanFactory.getInstance().getDaoImpl("UserDao",UserDao.class);
	private OrdersDao oDao = BeanFactory.getInstance().getDaoImpl("OrdersDao",OrdersDao.class);
	

	public void addCategory(Category c) {
		c.setId(IdGenerator.genPK());
		cDao.addCategory(c);
	}
	public List<Category> findAllCategories() {
		return cDao.findAllCategories();
	}
	public void addBook(Book book) {
		book.setId(IdGenerator.genPK());
		bDao.addBook(book);
	}
	public Page findAllBooks(String pagenum) {
		int num = 1;
		if(pagenum!=null&&!pagenum.equals(""))
			num = Integer.parseInt(pagenum);
		int totalrecords = bDao.getTotalRecords();
		Page page = new Page(num,totalrecords);
		List records = bDao.findBooksPageRecords(page.getStartindex(),page.getPagesize());
		page.setRecords(records);
		return page;
	}
	public Category findCategoryById(String categoryId) {
		return cDao.findCategoryById(categoryId);
	}
	public Page findAllBooksByCategory(String pagenum, String categoryId) {
		int num = 1;
		if(pagenum!=null&&!pagenum.equals(""))
			num = Integer.parseInt(pagenum);
		int totalrecords = bDao.getTotalRecords(categoryId);
		Page page = new Page(num,totalrecords);
		List records = bDao.findBooksPageRecords(page.getStartindex(),page.getPagesize(),categoryId);
		page.setRecords(records);
		return page;
	}
	public Book findBookById(String bookId) {
		return bDao.findBookById(bookId);
	}
	public void regist(User user) {
		user.setId(IdGenerator.genPK());
		uDao.addUser(user);
	}
	public User login(String username, String password) {
		User user = uDao.findUser(username,password);
		if(user==null)
			return null;
		if(!user.isActived())
			return null;
		return user;
	}
	public User findUserByCode(String code) {
		return uDao.findUserByCode(code);
	}
	public void active(User user) {
		user.setActived(true);
		uDao.update(user);
	}
	public void genOrders(Orders o) {
		o.setId(IdGenerator.genPK());
		o.setOrdersnum(IdGenerator.genOrdersNum());
		//设置购物项的id:业务没有需要
		if(o.getItems()!=null){
			for(OrdersItem item:o.getItems()){
				item.setId(IdGenerator.genPK());
			}
		}
		oDao.addOrders(o);
	}
	public List<Orders> findOrdersByUserId(String userId) {
		return oDao.findOrdersByUserId(userId);
	}
	public void paiedOrders(String ordersnum) {
		oDao.updateOrders(ordersnum,1);
	}
	public List<OrdersItem> findOrdersItemByOrdersId(String ordersId) {
		return oDao.findOrdersItemByOrdersId(ordersId);
	}
	public List<Orders> findOrdersByStatus(int i) {
		return oDao.findOrdersByStatus(i);
	}
	public void sendBook(String ordersNum) {
		oDao.updateOrders(ordersNum, 2);
	}

}
