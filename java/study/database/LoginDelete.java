package study.database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginDelete")
public class LoginDelete extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		
		String mid = (String)session.getAttribute("sMid");
		
		LoginDAO dao = new LoginDAO();
		int res = dao.delete(mid);
		PrintWriter out = response.getWriter();
		if (res > 0) {
			session.invalidate();
			out.println("<script>");
			out.println("alert('회원 탈퇴됬습니다.');");
			out.println("location.href='"+request.getContextPath()+"/study/database/login.jsp';");
			out.println("</script>");
		} 
		else {
			out.println("<script>");
			out.println("alert('회원 탈퇴 실패했습니다.');");
			out.println("location.href='"+request.getContextPath()+"/study/database/login.jsp';");
			out.println("</script>");
		}
	}
}
