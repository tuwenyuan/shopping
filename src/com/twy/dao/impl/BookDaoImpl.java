package com.twy.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.twy.dao.BookDao;
import com.twy.domain.Book;
import com.twy.exception.DaoException;
import com.twy.util.DBCPUtil;

public class BookDaoImpl implements BookDao {
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	public void addBook(Book book) {
		try {
			qr.update(
					"insert into book (id,name,author,price,photo,description,categoryId) values(?,?,?,?,?,?,?)",
					book.getId(),book.getName(),book.getAuthor(),book.getPrice(),book.getPhoto(),book.getDescription(),book.getCategoryId());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	public int getTotalRecords() {
		return getTotalRecords(null);
	}
	public List findBooksPageRecords(int startindex, int pagesize) {
		return findBooksPageRecords(startindex,pagesize,null);
	}
	public int getTotalRecords(String categoryId) {
		try {
			if(categoryId==null){
				Long l = (Long)qr.query("select count(*) from book", new ScalarHandler(1));
				return l.intValue();
			}else{
				Long l = (Long)qr.query("select count(*) from book where categoryId=?", new ScalarHandler(1),categoryId);
				return l.intValue();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	public List findBooksPageRecords(int startindex, int pagesize,
			String categoryId) {
		try {
			if(categoryId==null)
				return qr.query("select * from book limit ?,?", new BeanListHandler<Book>(Book.class), startindex,pagesize);
			else
				return qr.query("select * from book where categoryId=? limit ?,?", new BeanListHandler<Book>(Book.class), categoryId,startindex,pagesize);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	public Book findBookById(String bookId) {
		try {
			return qr.query("select * from book where id=?", new BeanHandler<Book>(Book.class), bookId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

}
