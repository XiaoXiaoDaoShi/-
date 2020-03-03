package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import pojo.OrderForm;
import service.OrderFormService;

/**
 * Servlet Filter implementation class AdminProductOrderFilter
 */
//管理，获得所有订单信息
@WebFilter("/admin/product-order.jsp")
public class AdminProductOrderFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminProductOrderFilter() {
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
		OrderFormService os = new OrderFormService();
		List<OrderForm> orderFormList = os.findAllOrderForm();
		request.setAttribute("orderFormList", orderFormList);
		
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
