package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/UpdateUserData")
public class UpdateUserData extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String u_email=(String)session.getAttribute("u_email");
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		String address=request.getParameter("address");
		try {
			dao.DbConnect db=new dao.DbConnect();
			boolean result=db.updateUser(u_email,name,phone,address);
			if(result) {
				session.setAttribute("msg", "User data updated successfully!");
				response.sendRedirect("UserHome.jsp");
			}else {
				session.setAttribute("msg", "Something went wrong!");
				response.sendRedirect("UserHome.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
