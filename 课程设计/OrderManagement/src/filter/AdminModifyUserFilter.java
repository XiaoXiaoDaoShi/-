package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import pojo.User;
import service.UserService;

/**
 * Servlet Filter implementation class AdminModifyUserFilter
 */
//用户修改，获得初始值
@WebFilter("/admin/user-edit.jsp")
public class AdminModifyUserFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminModifyUserFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		UserService us = new UserService();
		String uid = request.getParameter("userId");
		User user = us.findUserById(uid);
		request.setAttribute("tempUser", user);
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
