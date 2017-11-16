package com.twy.dao;

import java.util.List;

import com.twy.domain.Book;

public interface BookDao {

	void addBook(Book book);

	int getTotalRecords();

	List findBooksPageRecords(int startindex, int pagesize);
	/**
	 * 按照分类查询记录数据
	 * @param categoryId
	 * @return
	 */
	int getTotalRecords(String categoryId);
	/**
	 * 按照分类查询分页数据
	 * @param categoryId
	 * @return
	 */
	List findBooksPageRecords(int startindex, int pagesize, String categoryId);

	Book findBookById(String bookId);

}
