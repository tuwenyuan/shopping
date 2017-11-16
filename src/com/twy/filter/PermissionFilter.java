package com.twy.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.twy.domain.Manager;
import com.twy.domain.Menu;
import com.twy.domain.Role;
import com.twy.service.ManagerService;
import com.twy.service.impl.ManagerServiceImpl;
//基于URI的权限拦截
public class PermissionFilter implements Filter {
	private ManagerService s = new ManagerServiceImpl();
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		String uri = request.getRequestURI();//    /day19_shopping/manager/showDetails.jsp
			//判断用户是否登陆
			HttpSession session = request.getSession();
			Manager manager = (Manager) session.getAttribute("manager");
			//没有登陆：转向登陆页面
			if(manager==null){
				response.sendRedirect(request.getContextPath()+"/login/login.jsp");
				return;
			}else{
				//有登陆：根据用户查询对应的角色，遍历角色，得到能访问的菜单（Set<Menu>）
				Set<Menu> menus = new HashSet<Menu>();//用户可以访问的菜单
				List<Role> roles = s.findRoleByManagerId(manager.getId());//根据当前登录用户查询用户可以访问的角色
				if(roles!=null&&roles.size()>0){
					for(Role r:roles){
						//根据角色查询可以访问的菜单
						List<Menu> ms = s.findMenuByRoleId(r.getId());
						if(ms!=null&&ms.size()>0){
							for(Menu m:ms)
								menus.add(m);//把可以访问的菜单放到Set中
						}
					}
				}
				//得到用户当前访问的资源的uri：  /day19_shopping/manager/showDetails.jsp--->/manager/showDetails.jsp
				uri = uri.replace(request.getContextPath(), "");//   /manager/showDetails.jsp
				//遍历那个Set，看看其中包含uri否
				boolean hasPermission = false;//是否有权限
				for(Menu m:menus){
					if(uri.equals(m.getUri())){
						hasPermission = true;
						break;
					}
				}
				
				if(!hasPermission){
					//不包含：通知没有权限
					response.getWriter().write("对不起，您没有权限");
					response.setHeader("Refresh", "2;URL="+request.getContextPath()+"/login/index.jsp");
					return;
				}
			}
		chain.doFilter(request, response);
	}
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	

	public void destroy() {

	}

}
