package study.database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginJoin")
public class LoginJoin extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글처리... (Filter에서 처리함)
		
		//Form element 유효성체크(null값 등) 후 VO에 셋팅 후, DAO로 자료 저장
		String mid = request.getParameter("mid")==null?"":request.getParameter("mid");
		String pwd = request.getParameter("pwd")==null?"":request.getParameter("pwd");
		String name = request.getParameter("name")==null?"":request.getParameter("name");

		LoginVO vo = new LoginVO();
		vo.setMid(mid);
		vo.setPwd(pwd);
		vo.setName(name);
		
		LoginDAO dao = new LoginDAO();
		
		//아이디 중복체크
		boolean existMid = dao.searchExistMid(mid);
		PrintWriter out = response.getWriter();
		//아이디 중복존재함
		if (existMid) {
			out.println("<script>");
			out.println("alert('아이디가 중복되었습니다. 새로운 아이디를 입력하세요.');");
			out.println("location.href='"+request.getContextPath()+"/study/database/loginJoin.jsp';");
			out.println("</script>");
		} else {
			//회원가입
			int res = dao.insert(vo);
			if (res > 0) {
				out.println("<script>");
				out.println("alert('회원 가입됬습니다.');");
				out.println("location.href='"+request.getContextPath()+"/study/database/login.jsp';");
				out.println("</script>");
			} 
			else {
				out.println("<script>");
				out.println("alert('회원 가입 실패했습니다.');");
				out.println("location.href='"+request.getContextPath()+"/study/database/login.jsp';");
				out.println("</script>");
			}
		}
	}
}
