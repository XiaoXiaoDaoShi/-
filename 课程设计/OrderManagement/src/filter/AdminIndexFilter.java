package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import pojo.User;
import sun.rmi.transport.proxy.HttpReceiveSocket;

/**
 * Servlet Filter implementation class AdminIndexFilter
 */
//后台身份管理
@WebFilter("/admin/*")
public class AdminIndexFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminIndexFilter() {
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
		request.setCharacterEncoding("utf-8");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		User user = (User) httpRequest.getSession().getAttribute("user");
		
		if(user == null) {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("<a href='http://localhost:8081/OrderManagement/login.html'>点击这里去登录</a>");
		}else {
			if(user.getUserType()!=3) {	
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().println("your are not admin, can not get in");
				response.getWriter().println("<a href=\"http://localhost:8081/OrderManagement/login.html\">点击这里重新登录</a>");
			}else {
				chain.doFilter(request, response);
			}
		}
		// pass the request along the filter chain
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
