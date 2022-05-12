package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conn.SecurityUtil;
import member.database.MemberDAO;

public class MemberPwdChangeOkCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");
		String email = request.getParameter("email");
		String shaEmail = request.getParameter("shaEmail");
		String newPwd = request.getParameter("newPwd");
		String rePwd = request.getParameter("rePwd");
		
		MemberDAO dao = new MemberDAO();
		//ID 비존재
		if ( ! dao.memberIdCheck(mid) ) {
			request.setAttribute("existIdYN", "N");
			request.setAttribute("msg", "memberPwdChangeNo");
			request.setAttribute("url", request.getContextPath()+"/memberPwdChange.mbr");
			return;
		}
		//email 비존재시
		if ( ! dao.memberEmailCheck(email) ) {
			request.setAttribute("existEmailYN", "N");
			request.setAttribute("msg", "memberPwdChangeNo");
			request.setAttribute("url", request.getContextPath()+"/memberPwdChange.mbr");
			return;
		}
		
		//email로 본인확인 암호화문자 전송 및 암호화문자 같은지 체크
		//String confirmShaEmail = new SecurityUtil().encriptSHAEmailRandom();//암호화 문자
		String confirmShaEmail = shaEmail;
		if ( ! shaEmail.equals(confirmShaEmail) ) {
			request.setAttribute("existShaEmailYN", "N");
			request.setAttribute("msg", "memberPwdChangeNo");
			request.setAttribute("url", request.getContextPath()+"/memberPwdChange.mbr");
			return;
		}
		
		//New비밀번호와 Re비밀번호가 같은지 체크
		if ( ! newPwd.equals(rePwd) ) {
			request.setAttribute("validPwdYN", "N");
			request.setAttribute("msg", "memberPwdChangeNo");
			request.setAttribute("url", request.getContextPath()+"/memberPwdChange.mbr");
			return;
		}
		
		int res = dao.updatePwd(mid, email, newPwd);
		if (1 == res) {
			request.setAttribute("changedPwdYN", "Y");
			request.setAttribute("msg", "memberPwdChangeOk");
			request.setAttribute("url", request.getContextPath()+"/memberPwdChange.mbr");
		} else {
			request.setAttribute("changedPwdYN", "N");
			request.setAttribute("msg", "memberPwdChangeNo");
			request.setAttribute("url", request.getContextPath()+"/memberPwdChange.mbr");
		}
	}
}