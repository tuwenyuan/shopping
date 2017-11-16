package com.twy.dao;

import java.util.List;

import com.twy.domain.Orders;
import com.twy.domain.OrdersItem;

public interface OrdersDao {

	void addOrders(Orders o);

	List<Orders> findOrdersByUserId(String userId);

	void updateOrders(String ordersnum,int status);

	List<OrdersItem> findOrdersItemByOrdersId(String ordersId);

	List<Orders> findOrdersByStatus(int i);

}
