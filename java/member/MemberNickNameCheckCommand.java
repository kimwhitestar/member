package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.database.MemberDAO;

public class MemberNickNameCheckCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nickName = request.getParameter("nickName");
		request.setAttribute("nickName", nickName);
		request.setAttribute("existNickNameYN", null);
		MemberDAO dao = new MemberDAO();
		if (null != nickName) {
			//isExist = true 닉네임 중복
			if (dao.memberNickNameCheck(nickName)) {
				request.setAttribute("existNickNameYN", "Y");
			} else {
				request.setAttribute("existNickNameYN", "N");
			}
		}
	}
}
