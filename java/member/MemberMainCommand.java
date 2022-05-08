package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import guest.database.GuestDAO;
import member.database.MemberDAO;
import member.database.MemberVO;

public class MemberMainCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//level = 1:준회원, 2:정회원, 3:우수회원 (4:운영자) 0:관리자
		HttpSession session = request.getSession();
		String sMid = (String)session.getAttribute("sMid");
		
		MemberVO vo = new MemberDAO().search(sMid);
		String strLevel = new String("");
		if (null != vo) {
			if (0 == vo.getLevel()) strLevel = "관리자";
			else if (1 == vo.getLevel()) strLevel = "준회원";
			else if (2 == vo.getLevel()) strLevel = "정회원";
			else if (3 == vo.getLevel()) strLevel = "우수회원";
			else if (4 == vo.getLevel()) strLevel = "운영자";
			vo.setStrLevel(strLevel);
		}
		
		request.setAttribute("strLevel", vo.getStrLevel());
		request.setAttribute("point", vo.getPoint());
		request.setAttribute("lastDate", vo.getLastDate());
		request.setAttribute("todayCnt", vo.getTodayCnt());
		request.setAttribute("visitCnt", vo.getVisitCnt());
		
		//사용자가 방명록에 글쓴횟수 가져오기
		int cnt = new GuestDAO().searchGuestWriteCnt(sMid, vo.getName(), vo.getNickName());
		request.setAttribute("guestCnt", cnt);
	}
}