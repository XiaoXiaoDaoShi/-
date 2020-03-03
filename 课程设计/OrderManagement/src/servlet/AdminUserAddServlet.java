package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.User;
import service.UserService;

/**
 * Servlet implementation class AdminUserAddServlet
 */
//添加新用户
@WebServlet("/admin/AdminUserAddServlet")
public class AdminUserAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUserAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService us = new UserService();
		
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String userGender = request.getParameter("userGender");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
			
		User user = new User(userName, userPassword,Integer.valueOf(userGender), email, phone);
		String msg="";
		int count = us.addUser(user);
		if(count>0) {
			msg = "1";
		}else {
			msg = "0";
		}
		
		response.getWriter().println(msg);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
