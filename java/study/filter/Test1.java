package study.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/filter/test1")
public class Test1 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

/*//'web.xml encodingFilter url(/*)' OR '서블릿 url(/*)'로 EncodingFilter.java서블릿 이동해서 인코딩 설정함*/
//아래 인코딩은 생략
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html; charset=utf-8");
		
/*'web.xml servlet_init_parameter'에서 설정한 초기값 읽기 : getInitParameter()*/
//위의 @WebServlet과 아래 request파라미터 초기값은 생략
//		String name = getInitParameter("name");
//		String gender = getInitParameter("gender");
//		String age = getInitParameter("age");
		String name = request.getParameter("name")==null ? "" : request.getParameter("name");
		String gender = request.getParameter("gender")==null ? "" : request.getParameter("gender");
		int age = request.getParameter("age")==null ? 20 : Integer.parseInt(request.getParameter("age"));
		
		PrintWriter out = response.getWriter();
		
		out.println("<p>성명 : "+name+"</p>");
		out.println("<p>성별 : "+gender+"</p>");
		out.println("<p>나이 : "+age+"</p>");
		out.println("<p><a href='"+request.getContextPath()+"/study/filter/test1.jsp'>돌아가기</a></p>");
	}
}
