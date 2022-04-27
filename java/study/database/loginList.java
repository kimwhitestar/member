package study.database;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginList")
public class loginList extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 서블릿 컨트롤러 주소로 보안처리(비로그인시 처리) : 속도 향상
		// .jsp화일로 주소입력해서 화면로딩하는 것보다 속도가 빠름
		String mid = (String)request.getSession().getAttribute("sMid");
		if ( null == mid ) {
			response.sendRedirect(request.getContextPath()+"/log");
			return;
		}		
		
		LoginDAO dao = new LoginDAO();
		List<LoginVO> loginList = dao.searchLoginList();
		
		request.setAttribute("vos", loginList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/study/database/loginList.jsp"); 
		dispatcher.forward(request, response);
	}
}
