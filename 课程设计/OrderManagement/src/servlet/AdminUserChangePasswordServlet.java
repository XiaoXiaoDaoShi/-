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
 * Servlet implementation class AdminUserChangePasswordServlet
 */
//用户密码修改
@WebServlet("/admin/AdminUserChangePasswordServlet")
public class AdminUserChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUserChangePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newPassword = request.getParameter("newpassword2");
		String userId = request.getParameter("userId");
		UserService us = new UserService();
		int count = us.modifyPasswordById(userId, newPassword);
		
		String msg = "";
		if(count>0) {
			msg = "1";
		}else {
			msg = "0";
		}
		response.setCharacterEncoding("utf-8");
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
