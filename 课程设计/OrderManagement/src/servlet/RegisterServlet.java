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
 * Servlet implementation class RegisterServlet
 */
//注册
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = request.getParameter("userName");
		String userPassowrd = request.getParameter("userPassword");
		int userGender = Integer.valueOf(request.getParameter("userGender"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		User user = new User(userName, userPassowrd, userGender,email, phone);
		VisitorService visitorService = new VisitorService();
		int count = visitorService.register(user);			//注册
		if(count>0) {
			//注册成功
			user.setUserType(2);
			session.setAttribute("User", user);
			response.sendRedirect("index.jsp");
		}
		else {
			response.sendRedirect("register.html");
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
