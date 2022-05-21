package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.database.MemberDAO;
import member.database.MemberVO;

public class MemberUpdateCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int sIdx = (int) session.getAttribute("sIdx");
		String sMid = (String) session.getAttribute("sMid");
		
		//개별회원정보 조회
		MemberVO vo = new MemberDAO().search(sIdx, sMid);
		
		//Form에 출력을 위한 분리작업
		//Email 분리(@)
		String[] email = vo.getEmail().split("@");
		if (null == email || 2 > email.length) {
			request.setAttribute("email1", "");
			request.setAttribute("email2", "");
		} else {
			request.setAttribute("email1", email[0]);
			request.setAttribute("email2", email[1]);
		}
		//생일
		request.setAttribute("birthday", vo.getBirthday().substring(0, 10));
		//전화번호 분리(-)
		String[] tel = vo.getTel().split("-");
		if (null == tel || 3 > tel.length) {
			request.setAttribute("tel1", "");
			request.setAttribute("tel2", "");
			request.setAttribute("tel3", "");
		} else {
			request.setAttribute("tel1", tel[0]);
			request.setAttribute("tel2", tel[1]);
			request.setAttribute("tel3", tel[2]);
		}
		//주소 분리(/)
		String[] address = vo.getAddress().split("/");
		if (null == address || 3 > address.length) {
			request.setAttribute("postcode", "");
			request.setAttribute("roadAddress", "");
			request.setAttribute("extraAddress", "");
			request.setAttribute("detailAddress", "");
		} else {
			request.setAttribute("postcode", address[0]);
			request.setAttribute("roadAddress", address[1]);
			request.setAttribute("extraAddress", address[2]);
			request.setAttribute("detailAddress", address[3]);
		}
		
		request.setAttribute("vo", vo);
	}
}