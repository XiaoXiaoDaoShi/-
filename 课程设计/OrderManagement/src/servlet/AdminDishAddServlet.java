package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.catalina.connector.OutputBuffer;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import pojo.Dish;
import service.DishService;

/**
 * Servlet implementation class AdminDishAddServlet
 */
//菜品添加 包括图片上传
@WebServlet("/admin/AdminDishAddServlet")
@MultipartConfig
public class AdminDishAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDishAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dishName = request.getParameter("dishName");
		String dishPrice = request.getParameter("dishPrice");
		String dishCookWay = request.getParameter("dishCookWay");
		String dishFlavour = request.getParameter("dishFlavour");
		
		Part imagePart = request.getPart("dishImage");					//图片Part
		String header = imagePart.getHeader("Content-Disposition");		
		
		
		String msg = "";
		
		String path = "D:\\Code\\eclipse-workspace-ee\\OrderManagement\\WebContent\\DishImage";
	
		
		// 数据没有为空
		if(dishName!=null && dishPrice!=null && dishCookWay !=null &dishFlavour !=null && imagePart.getSize()>0) {
			if(!dishName.isEmpty() && !dishPrice.isEmpty() && !dishCookWay.isEmpty() && !dishFlavour.isEmpty() ) {
				String fileName = String.valueOf(new Date().getTime()) + ".jpg";    //时间命名图片
				OutputStream out = new FileOutputStream(new File(path,fileName));	
				InputStream in = imagePart.getInputStream();							//获得图片流
				int len = 0;
				byte[] buf = new byte[1024];
				while((len=in.read(buf))!=-1) {											//写入图片
					out.write(buf, 0, len);
				}
			in.close();
			out.close();
			int price = Integer.valueOf(dishPrice);     //转换为int类型
			Dish dish = new Dish(dishName, price, 0, dishCookWay, dishFlavour, "DishImage/"+fileName);   //一个新的dish
			DishService ds = new DishService();
			int count = ds.addDish(dish);
			if(count>0) {
				msg = "1";
			}
			else {
				msg = "0";
			}
			
		}	
	}	
		else {
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
