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
 * Servlet implementation class AdminUserEditServlet
 */
//用户修改
@WebServlet("/admin/AdminUserEditServlet")
public class AdminUserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUserEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String userGender = request.getParameter("userGender");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		User user = new User(userId, userName, userPassword, Integer.valueOf(userGender), email, phone);
		
		UserService us = new UserService();
		int count = us.modifyUser(userId, user);
		System.out.println(user);
		String msg="";
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
