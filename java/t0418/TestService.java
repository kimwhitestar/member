package t0418;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestService")
public class TestService extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		int num = Integer.parseInt(request.getParameter("num"));
		String mid = request.getParameter("mid");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String hostIP = request.getParameter("hostIP");
		String hostIP3 = request.getParameter("hostIP3");

		PrintWriter out = response.getWriter();
		if (name.equals("")) {
			out.println("<script>");
			out.println("  history.back()");//뒤로가기 2번: history.back(2)
			out.println("</script>");
		}
		
//		out.println("<button name='뒤로가기' onclick='goBack()'></button>");
//		out.println("<script>");
//		out.println("function goBack() {");
//		out.println(" history.back()");//뒤로가기 2번: history.back(2)
//		out.println("}");
//		out.println("</script>");
		
		out.println("<br>아이디 : " + mid);
		out.println("<br>비밀번호 : " + pwd);
		out.println("<br>이름 : " + name);
		out.println("<br>WAS Run으로 웹실행하면, 톰캣 서버 IP로 바꿔서 가져옴(hostIP) : " + hostIP);
		out.println("<br>WAS Run으로 웹실행하면, 톰캣 서버 IP로 바꿔서 가져옴(hostIP3) : " + hostIP3);
		out.println("<br>전송방식 : " + request.getMethod());
		out.println("<br>URL : " + request.getRequestURL());
		out.println("<br>URI : " + request.getRequestURI());
		out.println("<br>접속서버이름 : " + request.getServerName());
		out.println("<br>접속서버포트 : " + request.getServerPort());
		out.println("<br>요청 파라메터의 길이 : " + request.getContentLength());
		out.println("<br>context path : " + request.getContextPath());
		out.println("<br>현재 사용중인 프로토콜 : " + request.getProtocol());
		
		out.println("<br><a href='"+request.getContextPath()+"/0418/TestService.jsp'>돌아가기</a>");//왼쪽은 doGet방식으로만 돌아가기 적용됨
	}
}
