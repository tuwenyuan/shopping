package com.twy.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.twy.dao.OrdersDao;
import com.twy.domain.Book;
import com.twy.domain.Orders;
import com.twy.domain.OrdersItem;
import com.twy.domain.User;
import com.twy.exception.DaoException;
import com.twy.util.DBCPUtil;

public class OrdersDaoImpl implements OrdersDao {
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	public void addOrders(Orders o) {
		try{
			//保存订单的基本信息
			qr.update("insert into orders(id,ordersnum,num,money,status,userId) values(?,?,?,?,?,?)", 
					o.getId(),o.getOrdersnum(),o.getNum(),o.getMoney(),o.getStatus(),o.getUser().getId());
			//判断订单中有木有订单项：如果有保存订单项信息
			List<OrdersItem> items = o.getItems();
			if(items!=null&&items.size()>0){
				Object params[][] = new Object[items.size()][];
				String sql = "insert into orders_item(id,num,price,bookId,ordersId) values(?,?,?,?,?)";
				for(int i=0;i<items.size();i++){
					OrdersItem item = items.get(i);
					params[i] = new Object[]{item.getId(),item.getNum(),item.getPrice(),item.getBook().getId(),o.getId()};
				}
				qr.batch(sql, params);
			}
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	public List<Orders> findOrdersByUserId(String userId) {
		try{
			return qr.query("select * from orders where userId=? order by createtime desc", new BeanListHandler<Orders>(Orders.class), userId);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	public void updateOrders(String ordersnum,int status) {
		try{
			qr.update("update orders set status=? where ordersnum=?", 
					status,ordersnum);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	public List<OrdersItem> findOrdersItemByOrdersId(String ordersId) {
		try{
			List<OrdersItem> items = qr.query("select * from orders_item where ordersId=?", new BeanListHandler<OrdersItem>(OrdersItem.class), ordersId);
			//把他关联的书查询出来
			if(items!=null&&items.size()>0){
				for(OrdersItem item:items){
					Book book = qr.query("select * from book where id=(select bookId from orders_item where id=?)", new BeanHandler<Book>(Book.class), item.getId());
					item.setBook(book);
				}
			}
			return items;
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	public List<Orders> findOrdersByStatus(int i) {
		try{
			//根据订单状态查询订单
			List<Orders> orders = qr.query("select * from orders where status=?", new BeanListHandler<Orders>(Orders.class), i);
			//把订单的购买人查询出来
			if(orders!=null&&orders.size()>0){
				for(Orders o:orders){
					User user = qr.query("select u.* from user u,orders o where u.id=o.userId and o.id=?", new BeanHandler<User>(User.class), o.getId());
					o.setUser(user);
				}
			}
			return orders;
		}catch(Exception e){
			throw new DaoException(e);
		}
	}

}
