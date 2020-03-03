package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import pojo.Dish;
import service.DishService;

/**
 * Servlet Filter implementation class AdminModifyProductFilter
 */
//菜品修改，获得所中的默认值
@WebFilter("/admin/product_modify.jsp")
public class AdminModifyProductFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminModifyProductFilter() {
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
		String dishId = request.getParameter("dishId");
		int did = Integer.valueOf(dishId);
		DishService ds = new DishService();
		Dish dish = ds.findDishById(did);
		request.setAttribute("tempDish", dish);
		
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
