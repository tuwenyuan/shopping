package com.twy.web.functions;

import com.twy.domain.Category;
import com.twy.service.BusinessService;
import com.twy.service.impl.BusinessServiceImpl;

public class WebFunctions {
	private static BusinessService s = new BusinessServiceImpl();
	public static String showCategoryName(String categoryId){
		Category c = s.findCategoryById(categoryId);
		if(c!=null)
			return c.getName();
		return null;
	}
}
