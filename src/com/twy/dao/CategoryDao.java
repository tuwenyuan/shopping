package com.twy.dao;

import java.util.List;

import com.twy.domain.Category;

public interface CategoryDao {

	void addCategory(Category c);

	List<Category> findAllCategories();

	Category findCategoryById(String categoryId);

}
