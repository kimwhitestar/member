package t0418;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestController
 */
@WebServlet("/TestEditedServletMapping")
public class TestServletMapping extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String mid = request.getParameter("mid");
		String pwd = request.getParameter("pwd");
		
		System.out.println("mid = " + mid);
		System.out.println("pwd = " + pwd);
		PrintWriter out = response.getWriter();
		
		out.println("아이디 : "+mid+"<br>");
		out.println("비밀번호 : "+pwd+"<br>");
		//contextPath = /javagreenJ
		System.out.println("request.getContextPath()  = " + request.getContextPath());
		out.println("<a href='"+request.getContextPath()+"/0418/TestServletMapping.jsp'>돌아가기</a>");
	}
}
