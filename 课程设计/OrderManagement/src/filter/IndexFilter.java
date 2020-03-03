package filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import pojo.Dish;
import pojo.User;
import service.DishService;

/**
 * Servlet Filter implementation class IndexFilter
 */
//前端index首页，展示热门推荐
@WebFilter("/index.jsp")
public class IndexFilter implements Filter {

    /**
     * Default constructor. 
     */
    public IndexFilter() {
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
	//热门推荐
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		DishService ds = new DishService();
		List<Dish> dishList = ds.findTopDishesByLimit(10);					//获得前10个热门
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		session.setAttribute("hotDishList", dishList);
		
		User user = (User) session.getAttribute("user");
		if(user==null) {									//如果没有user 初始化一个游客
			User user2 = new User();
			user2.setUserName("游客");
			user2.setUserType(1);
			session.setAttribute("user", user2); //设置初始用户为游客
		}
		
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
