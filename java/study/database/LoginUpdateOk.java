package study.database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginUpdateOk")
public class LoginUpdateOk extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mid = (String)session.getAttribute("sMid");
		
		//Form element 유효성체크(null값 등) 후 VO에 셋팅 후, DAO로 자료 Update
		String pwd = request.getParameter("pwd")==null?"":request.getParameter("pwd");
		String name = request.getParameter("name")==null?"":request.getParameter("name");

		LoginVO vo = new LoginVO();
		vo.setMid(mid);
		vo.setPwd(pwd);
		vo.setName(name);
		
		LoginDAO dao = new LoginDAO();
		
		//회원정보 수정
		int res = dao.update(vo);
		PrintWriter out = response.getWriter();
		if (res > 0) {
			session.setAttribute("sName", vo.getName());
			
			out.println("<script>");
			out.println("alert('회원정보 수정됬습니다.');");
			out.println("location.href='"+request.getContextPath()+"/study/database/loginMain.jsp';");
			out.println("</script>");
		} 
		else {
			out.println("<script>");
			out.println("alert('회원정보 수정 실패했습니다.');");
			out.println("location.href='"+request.getContextPath()+"/loginUpdate';");
			out.println("</script>");
		}
	}
}
