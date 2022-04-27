package study.init;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Study1WebXmlInit")
public class TestWebXmlInit extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// web.xml에서 지정해 놓은 공통변수의 값들을 읽어온다.
		String logoName = getServletContext().getInitParameter("logoName");
		String homeAddress = getServletContext().getInitParameter("homeAddress");
		
		HttpSession session = request.getSession();
		
		session.setAttribute("logoName", logoName);
		session.setAttribute("homeAddress", homeAddress);
		
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		out.println("alert('공통변수값을 읽어옵니다.');");
		out.println("location.href='"+request.getContextPath()+"/study/init/test1.jsp';");
		out.println("</script>");
	}
}