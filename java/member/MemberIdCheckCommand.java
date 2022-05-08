package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.database.MemberDAO;

public class MemberIdCheckCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");
		MemberDAO dao = new MemberDAO();
		if (null != mid) {
			//isExist = true 아이디 중복
			if (dao.memberIdCheck(mid)) {
				request.setAttribute("existIdYN", "Y");
			}
		}
		request.setAttribute("mid", mid);
	}
}
