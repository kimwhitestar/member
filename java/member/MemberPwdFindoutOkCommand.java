package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberPwdFindoutOkCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("msg", "memberPwdFindoutOk");
		request.setAttribute("url", request.getContextPath()+"/memberLogin.mbr");
		request.setAttribute("msg", "memberPwdFindoutNo");
		request.setAttribute("url", request.getContextPath()+"/memberPwdFindout.mbr");

	}
}