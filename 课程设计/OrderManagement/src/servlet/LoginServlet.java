package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojo.User;
import service.VisitorService;

/**
 * Servlet implementation class LoginServlet
 */
//登录
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		User user = new User(userName, userPassword);
		VisitorService vs = new VisitorService();
		int count = vs.login(user);
		if(count>0) {
			//登录成功
			if(!userName.equals("admin")) {						
				user.setUserType(2);						//type==2
				session.setAttribute("user", user);
				response.sendRedirect("index.jsp");	
			}else {										//是管理员就进入管理员界面
				user.setUserType(3);						//type == 3
				session.setAttribute("user", user);
				response.sendRedirect("admin/index.html");
			}		
		}
		else {
			//登录失败
			response.sendRedirect("login.html");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
