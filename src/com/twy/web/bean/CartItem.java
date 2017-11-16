package com.twy.web.bean;

import java.io.Serializable;

import com.twy.domain.Book;

public class CartItem implements Serializable {
	private Book book;
	private int num;//购物项数量
	private float price;//小计
	
	public CartItem(Book book){
		this.book = book;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public float getPrice() {
		return book.getPrice()*num;
	}
	
	public Book getBook() {
		return book;
	}
	
}
