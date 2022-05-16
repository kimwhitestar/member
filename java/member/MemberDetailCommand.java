package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.database.MemberDAO;
import member.database.MemberVO;

public class MemberDetailCommand implements MemberInterface {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		String mid = request.getParameter("mid");
		
		//개별회원상세정보 조회
		MemberVO vo = new MemberDAO().search(idx, mid);
		request.setAttribute("vo", vo);
	}
}
