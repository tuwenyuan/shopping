package com.twy.web.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.twy.domain.Book;

public class Cart implements Serializable {
	//key:书籍的id  value：书籍对应的购物项
	private Map<String, CartItem> items = new HashMap<String, CartItem>();
	private int totalNum;//总数量
	private float totalPrice;//总计：应付金额
	public Map<String, CartItem> getItems() {
		return items;
	}
	public int getTotalNum() {
		totalNum = 0;
		for(Map.Entry<String, CartItem> me:items.entrySet()){
			totalNum+=me.getValue().getNum();
		}
		return totalNum;
	}
	public float getTotalPrice() {
		totalPrice = 0;
		for(Map.Entry<String, CartItem> me:items.entrySet()){
			totalPrice+=me.getValue().getPrice();
		}
		return totalPrice;
	}
	//把一本书添加到items中
	public void addBook(Book book){
		if(items.containsKey(book.getId())){
			CartItem item = items.get(book.getId());
			item.setNum(item.getNum()+1);
		}else{
			CartItem item = new CartItem(book);
			item.setNum(1);
			items.put(book.getId(), item);
		}
	}
}
