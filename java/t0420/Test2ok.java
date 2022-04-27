package t0420;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/T2ok")
public class Test2ok extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//text/html을 test/html로 철자틀리면, 화면이동이 안되고, download가 되는 에러가 생기네ㅜㅜ
		response.setContentType("text/html; charset=utf-8"); 
		
		PrintWriter out = response.getWriter();
		
		String[] products = request.getParameterValues("product");
		out.println("products[1] = " + products[1]);
		
		String strProduct = "";
		for (String product : products) {
			strProduct += product + "/";
		}

//		if (cnt == 0) {
//			out.println("<script>");
//			out.println("alert('1개 이상 상품을 등록하세요. 뒤로 갑니다');");
//			out.println("history.back();");//이전화면의 값이 초기화된 상태로 되돌아가기한다
//			out.println("</script>");
//		}
		out.println("<p>등록하신 상품은?</p>");
		out.println("<p>" + strProduct + "</p>");
		out.println("<script>");
		out.println("alert('상품을 등록했습니다');");
		out.println("</script>");
		out.println("<p><a href='"+request.getContextPath()+"/0420/test2.jsp'>돌아가기</a></p>");
	}
}
