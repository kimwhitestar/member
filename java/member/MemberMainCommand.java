package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.database.BoardDAO;
import guest.database.GuestDAO;

public class MemberMainCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//level = 1:준회원, 2:정회원, 3:우수회원 (4:운영자) 0:관리자
		HttpSession session = request.getSession();
		String sName = (String)session.getAttribute("sName");
		String sNickName = (String)session.getAttribute("sNickName");
		String sMid = (String)session.getAttribute("sMid");

		// --------------------------------------------------
		// 세션 저장 : 활동내역(방명록에 올린 글 수, 게시판에 올린 글 수, 자료실에 올린 글 수
		// --------------------------------------------------
		BoardDAO boardDao = new BoardDAO();
		GuestDAO guestDao = new GuestDAO();
		session.setAttribute("sGuWritingCnt", 
				guestDao.searchGuestWriteCnt(sMid, sNickName));
		session.setAttribute("sBdWritingCnt", 
				boardDao.searchBoardWriteCnt(sMid, sNickName)
				+ boardDao.searchBoardreplyWriteCnt(sMid, sNickName));
		//자료실은 수업 아직 안했음
		//session.setAttribute("sPdsWritingCnt", 
		//	new PdsDAO().searchPdsWriteCnt(vo.getMid(), vo.getName(), vo.getNickName()));
	}
}