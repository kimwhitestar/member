package study.database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginlocation")
public class LoginLocation extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글처리(filter에서 처리함)
		
		String mid = request.getParameter("mid")==null?"":request.getParameter("mid");
		String pwd = request.getParameter("pwd")==null?"":request.getParameter("pwd");
		
		LoginDAO dao = new LoginDAO();
		
		//String name = dao.loginCheck(mid, pwd);
		LoginVO vo = dao.loginCheck(mid, pwd);
		
		PrintWriter out = response.getWriter();
		
		if (null == vo) { //자료를 못 찾았음
			out.println("<script>");
			out.println("alert('로그인 실패!');");
			out.println("location.href='"+request.getContextPath()+"/study/database/login.jsp'");
			out.println("</script>");
		}
		else { //자료를 찾았음
			// 로그인 처리
			// 1. 세션에 아이디 저장
			// 2. 로그인폼에서 '아이디저장' 선택시, '아이디'를 쿠키에 저장, 선택해제시 쿠키에서 삭제처리
			// 3. 한번 접속할때마다 포인트 1씩 증가
			// 4. 한번 접속할때마다 방문count 1씩 증가
			// 5. 로그인 성공시 회원메인페이지로 보낸다
			
			HttpSession session = request.getSession();
			session.setAttribute("sMid", mid);
			session.setAttribute("sName", vo.getName());
			
			//로그인시 point와 방문count 1씩 증가
			dao.updatePointAndVcountAndLastdate(mid);
			
			out.println("<script>");
			out.println("alert('"+vo.getName()+"님 로그인됬습니다.');");
			out.println("location.href='"+request.getContextPath()+"/study/database/loginMain.jsp?point="+vo.getPoint()+"&vCount="+vo.getvCount()+"';");
			out.println("</script>");
		}
	}
}
