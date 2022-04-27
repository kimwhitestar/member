package study.database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginUpdate")
public class LoginUpdate extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String mid = (String)session.getAttribute("sMid");
		
		// 서블릿 컨트롤러 주소로 보안처리(비로그인시 처리) : 속도 향상
		// .jsp화일로 주소입력해서 화면로딩하는 것보다 속도가 빠름
		if ( null == mid ) {
			response.sendRedirect(request.getContextPath()+"/log");
			return;
		}		
		
		LoginDAO dao = new LoginDAO();
		LoginVO vo = dao.search(mid);
		PrintWriter out = response.getWriter();

		if (null == vo) {//자료를 못 찾았음
			out.println("<script>");
			out.println("alert('회원정보를 찾지 못했습니다');");
			out.println("location.href='"+request.getContextPath()+"/study/database/loginMain.jsp'");//이전페이지 내용유지하며 이동할까?
			out.println("</script>");
		}
		else { //자료를 찾았음
			request.setAttribute("vo", vo);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/study/database/loginUpdate.jsp");
			dispatcher.forward(request, response);
		}
	}
}
