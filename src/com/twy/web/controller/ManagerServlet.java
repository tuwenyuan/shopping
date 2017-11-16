package com.twy.web.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.twy.domain.Book;
import com.twy.domain.Category;
import com.twy.domain.Manager;
import com.twy.domain.Menu;
import com.twy.domain.Orders;
import com.twy.service.BusinessService;
import com.twy.service.ManagerService;
import com.twy.service.impl.BusinessServiceImpl;
import com.twy.service.impl.ManagerServiceImpl;
import com.twy.util.IdGenerator;
import com.twy.util.WebUtil;
import com.twy.web.bean.Page;

public class ManagerServlet extends HttpServlet {
	private BusinessService s = new BusinessServiceImpl();
	private ManagerService ms = new ManagerServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if("addCategory".equals(operation)){
			addCategory(request,response);
		}else if("showAllCategories".equals(operation)){
			showAllCategories(request,response);
		}else if("showAddBook".equals(operation)){
			showAddBook(request,response);
		}else if("addBook".equals(operation)){
			addBook(request,response);
		}else if("showBooks".equals(operation)){
			showBooks(request,response);
		}else if("showPayedOrders".equals(operation)){
			showPayedOrders(request,response);
		}else if("sendBook".equals(operation)){
			sendBook(request,response);
		}else if("login".equals(operation)){
			login(request,response);
		}
	}
	//后台用户的登陆
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Manager m = ms.login(username,password);
		if(m!=null){
			request.getSession().setAttribute("manager", m);
			//把菜单都查出来
			List<Menu> menus = ms.findAllMenus();//查询出所有的菜单
			getServletContext().setAttribute("menus", menus);//放到应用域中
			request.getRequestDispatcher("/login/index.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
		}
	}
	//确认发货
	private void sendBook(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String ordersNum = request.getParameter("ordersNum");
		s.sendBook(ordersNum);
		response.sendRedirect(request.getContextPath()+"/servlet/ManagerServlet?operation=showPayedOrders");
	}
	//查看已付款的订单
	private void showPayedOrders(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("status");
		int numStatus = 1;
		if(status!=null){
			numStatus = Integer.parseInt(status);
		}
		List<Orders> orders = s.findOrdersByStatus(numStatus);//1 已付款
		request.setAttribute("numStatus", numStatus);
		request.setAttribute("os", orders);
		request.getRequestDispatcher("/manager/showOrders.jsp").forward(request, response);
	}
	//查询分页数据：书籍
	private void showBooks(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pagenum = request.getParameter("pagenum");
		Page page = s.findAllBooks(pagenum);
		page.setUrl("/manager/ManagerServlet?operation=showBooks");
		request.setAttribute("page",page);
		request.getRequestDispatcher("/manager/listBooks.jsp").forward(request, response);
	}
	//保存书籍信息：用到了文件上传
	private void addBook(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload sfu = new ServletFileUpload(factory);
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if(!isMultipart)//放菜鸟
				throw new RuntimeException("表单有误");
			List<FileItem> items = sfu.parseRequest(request);
			Book book = new Book();
			//得到存放图片的文件夹的真实路径
			String storeDirectory = getServletContext().getRealPath("/images");
			for(FileItem item:items){
				if(item.isFormField()){
					//普通字段
					String fieldName = item.getFieldName();//字段名   name
					String fieldValue = item.getString(request.getCharacterEncoding());//java
					BeanUtils.setProperty(book, fieldName, fieldValue);//  book.setName("java");
				}else{
					//上传字段
					String fileName = item.getName();//   java.jpg
					//截取文件的扩展名     jpg
					String extendFilename = fileName.substring(fileName.lastIndexOf(".")+1);//jpg
					String newFileName = IdGenerator.genPK()+"."+extendFilename;//sdfjlkdsflkdskjlk23432.jpg
					book.setPhoto(newFileName);//单独设置图片的名称信息
					
					//开始处理文件的上传
					InputStream in = item.getInputStream();
					OutputStream out = new FileOutputStream(storeDirectory+"/"+newFileName);
					int len = -1;
					byte b[] = new byte[1024];
					while((len=in.read(b))!=-1){
						out.write(b, 0, len);
					}
					in.close();
					out.close();
					item.delete();
				}
			}
			s.addBook(book);
			request.setAttribute("message", "<script type='text/javascript'>alert('添加成功')</script>");
			request.getRequestDispatcher("/manager/addBook.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	//转向添加书籍的页面，要查询分类
	private void showAddBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Category> cs = (List<Category>) getServletContext().getAttribute("cs");
		if(cs==null||cs.size()<1){
			cs = s.findAllCategories();
			getServletContext().setAttribute("cs", cs);
		}
		
		request.getRequestDispatcher("/manager/addBook.jsp").forward(request, response);
	}
	//查询所有分类
	private void showAllCategories(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Category> cs = (List<Category>) getServletContext().getAttribute("cs");
		if(cs==null||cs.size()<1){
			cs = s.findAllCategories();
			getServletContext().setAttribute("cs", cs);
		}
		
		request.getRequestDispatcher("/manager/listCategory.jsp").forward(request, response);
	}
	//添加分类信息到数据库中
	private void addCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Category c = WebUtil.findFormData(Category.class, request);//封装分类信息的类
		s.addCategory(c);
		request.setAttribute("message", "<script type='text/javascript'>alert('添加成功')</script>");
		request.getRequestDispatcher("/manager/addCategory.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
