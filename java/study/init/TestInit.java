package study.init;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*똑같은 서블릿매핑주소가 '@WebServlet과 web.xml'과 양쪽으로 선언시 에러남. 서블릿매핑주소는 1개 뿐이다.*/
//1.서블릿에서 urlPatterns주소로 이동시, 초기값 설정해서 이동하는 방법
//@WebServlet(urlPatterns = "/Study1ServletInit", initParams = {@WebInitParam(name="mid", value="servletAdmin"), @WebInitParam(name="pwd",value="1234"), @WebInitParam(name="className", value="INSA")})
//2.web.xml에서 서블릿주소 설정 및 초기값 설정해서 이동하는 방법 : 이곳에서는 @WebServlet 생략함
public class TestInit extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("이곳은 service 입니다.");
		
		PrintWriter out = response.getWriter();
				
		// 지정된 서블릿의 초기값을 불러온다.
		String mid = getInitParameter("mid");
		String pwd = getInitParameter("pwd");
		String className = getInitParameter("className");
		
		System.out.println("mid : " + mid);
		System.out.println("pwd : " + pwd);
		System.out.println("className : " + className);
		
		request.setAttribute("mid", mid);
		request.setAttribute("pwd", pwd);
		request.setAttribute("className", className);
		
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/0421/init/test1.jsp");
//		dispatcher.forward(request, response);
		request.getRequestDispatcher("/study/init/test1.jsp").forward(request, response);
	}
}