package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.database.MemberDAO;

public class MemberIdFindoutOkCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		
		MemberDAO dao = new MemberDAO();
		String mid = dao.searchMid(email, pwd);//회원ID찾기
		if (null == mid) {
			request.setAttribute("existIdYN", "N");
			request.setAttribute("msg", "memberIdFindoutNo");
		} else {
			request.setAttribute("existIdYN", "Y");
			request.setAttribute("msg", "memberIdFindoutOk");
		}
		request.setAttribute("mid", mid);
		request.setAttribute("url", request.getContextPath()+"/memberIdFindout.mbr");
	}
}