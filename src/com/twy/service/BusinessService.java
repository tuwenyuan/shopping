package com.twy.service;

import java.util.List;

import com.twy.domain.Book;
import com.twy.domain.Category;
import com.twy.domain.Orders;
import com.twy.domain.OrdersItem;
import com.twy.domain.User;
import com.twy.web.bean.Page;

public interface BusinessService {

	void addCategory(Category c);

	List<Category> findAllCategories();

	void addBook(Book book);
	/**
	 * 查询所有书籍的分页数据
	 * @param pagenum
	 * @return
	 */
	Page findAllBooks(String pagenum);

	Category findCategoryById(String categoryId);
	/**
	 * 按照分类查询分页数据
	 * @param pagenum
	 * @param categoryId
	 * @return
	 */
	Page findAllBooksByCategory(String pagenum, String categoryId);

	Book findBookById(String bookId);

	void regist(User user);
	/**
	 * 判断用户和密码是否正确，并且激活时才返回数据
	 * @param username
	 * @param password
	 * @return
	 */
	User login(String username, String password);

	User findUserByCode(String code);

	void active(User user);

	void genOrders(Orders o);

	List<Orders> findOrdersByUserId(String id);
	//改为已付款
	void paiedOrders(String r6_Order);
	//根据订单id查询订单项，同时把订单项关联的书也查出来
	List<OrdersItem> findOrdersItemByOrdersId(String ordersId);
	//根据状态查询订单信息：还要查用户
	List<Orders> findOrdersByStatus(int i);
	//发货
	void sendBook(String ordersNum);

}
