package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojo.Dish;

/**
 * Servlet implementation class OrderDishServlet
 */
//点单，进入购物车
@WebServlet("/OrderDishServlet")
public class OrderDishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDishServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //获得被选择的商品
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String [] checkBox = request.getParameterValues("dishCheck");			//选中了checkbox的
		HttpSession session = request.getSession();
		List<Dish> dishList = (List<Dish>)session.getAttribute("dishList");			
		List<Dish> orderDishList = new ArrayList<Dish>();
		if(checkBox==null) {
			session.setAttribute("orderDishList", null);
		}
		else {
			for(String c:checkBox) {
			int i = Integer.valueOf(c);
			Dish tempDish = dishList.get(i);
			String number = request.getParameter("number_"+String.valueOf(i));
			tempDish.setOrderNumber(Integer.valueOf(number));
			orderDishList.add(tempDish);					//被order的东西
			}
			session.setAttribute("orderDishList", orderDishList);
		}

		
//		request.getRequestDispatcher("shopping.html").forward(request, response);
		response.sendRedirect("shopping.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
