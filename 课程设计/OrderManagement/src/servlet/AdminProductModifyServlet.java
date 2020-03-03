package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import pojo.Dish;
import service.DishService;

/**
 * Servlet implementation class AdminProductModifyServlet
 */
//修改菜品 包括新的图片上传
@WebServlet("/admin/AdminProductModifyServlet")
@MultipartConfig
public class AdminProductModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProductModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dishId = request.getParameter("dishId");
		String dishName = request.getParameter("dishName");
		String dishPrice = request.getParameter("dishPrice");
		String dishCookWay = request.getParameter("dishCookWay");
		String dishFlavour = request.getParameter("dishFlavour");
		String dishImage = request.getParameter("dishImageUrl"); //图片原路径
		
		
		Part imagePart = request.getPart("dishImage");					//图片Part
		
		String msg = "";
		
		if(imagePart.getSize()>0) {						//如有新的图片 就重新上传
			String path = "D:\\Code\\eclipse-workspace-ee\\OrderManagement\\WebContent\\DishImage";
			
			String fileName = String.valueOf(new Date().getTime()) + ".jpg";
			dishImage ="DishImage/"+ fileName;   //时间命名图片	
			OutputStream out = new FileOutputStream(new File(path,fileName));	
			InputStream in = imagePart.getInputStream();							//获得图片流
			int len = 0;
			byte[] buf = new byte[1024];
			while((len=in.read(buf))!=-1) {											//写入图片
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
			
		}
		int price = Integer.valueOf(dishPrice);     //转换为int类型
		Dish dish = new Dish(dishName, price,dishCookWay, dishFlavour, dishImage);   //一个新的dish
		
		DishService ds = new DishService();
		int count = ds.modifyDish(dishId, dish);
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
