package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.database.MemberDAO;

public class MemberDeletePractCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sMid = (String)session.getAttribute("sMid");
		
		MemberDAO dao = new MemberDAO();
		//회원탈퇴 - 1달간은 회원정보유지, userDel만 OK(회원탈퇴)로 수정
		int res = dao.updateUserDel(sMid);
		
		if (1 == res) {
			request.setAttribute("msg", "memberDeletePractOk");
			request.setAttribute("url", request.getContextPath()+"/memberLogout.mbr");
		} else {
			request.setAttribute("msg", "memberDeletePractNo");
			request.setAttribute("url", request.getContextPath()+"/memberMain.mbr");
			
		}
	}
}
