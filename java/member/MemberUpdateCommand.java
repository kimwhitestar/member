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
		String sMid = (String) session.getAttribute("sMid");
		
		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.search(sMid);
		
		//Form에 출력을 위한 분리작업
		//Email 분리(@)
		String[] email = vo.getEmail().split("@");
		request.setAttribute("email1", email[0]);
		request.setAttribute("email2", email[1]);
		//생일
		request.setAttribute("birthday", vo.getBirthday().substring(0, 10));
		//전화번호 분리(-)
		String[] tel = vo.getTel().split("-");
		request.setAttribute("tel1", tel[0]);
		request.setAttribute("tel2", tel[1]);
		request.setAttribute("tel3", tel[2]);
		//주소 분리(/)
//		String[] address = vo.getAddress().split("/");
		request.setAttribute("postcode", "30010");
		request.setAttribute("roadAddress", "테스트주소1");
		request.setAttribute("detailAddress", "테스트주소2");
		request.setAttribute("extraAddress", "테스트주소3");
		//취미 분리(/)
		
		request.setAttribute("vo", vo);
	}
}