package study.database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class Logout extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("sName");
		
		session.invalidate(); //웹브라우저에 설정해서 페이지마다 유지했던 세션을 모두 삭제
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('"+name+"님 로그아웃됬습니다');");
		out.println("location.href='"+request.getContextPath()+"/study/database/login.jsp';");
		out.println("<script>");
		out.println("</script>");
	}
}
