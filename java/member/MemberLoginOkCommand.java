package member;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.database.BoardDAO;
import guest.database.GuestDAO;
import member.database.MemberDAO;
import member.database.MemberVO;

public class MemberLoginOkCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");//login요청-mid
		String pwd = request.getParameter("pwd");//login요청-pwd
		String idSave = request.getParameter("idSave");//logoin요청-idSave저장(mid쿠키저장)

		HttpSession session = request.getSession();
		String sPoint = (String)session.getAttribute("sPoint");
		int intSPoint = 0;//int타입의 포인트(세션정보)
		if (null != sPoint && 0 < sPoint.trim().length()) intSPoint = Integer.parseInt(sPoint);
			
		// --------------------------------------------------
		// 로그인 성공시 처리 내용
		// --------------------------------------------------
		// 1.오늘방문횟수, 전체방문횟수 1씩 증가 
		// 2.포인터 1씩 증가(방문시마다 1포인트씩 증가하며 1일 10회 이하, 가입시 100 포인트 증가-insert시 db default 증가)
		// 3.주요자료 세션 저장 
		// 4.쿠키에 아이디저장유무? 
		// --------------------------------------------------
		MemberDAO dao = new MemberDAO();
		int res = 0;
		MemberVO vo = dao.searchMemberLogin(mid, pwd);
		//DB nickName은 not null이며, 닉네임 세션 저장하여 전체 웹페이지마다 닉네임 출력 처리
		if (null == vo || 0 == vo.getNickName().trim().length()) {
			request.setAttribute("msg", "memberLoginNo");
			request.setAttribute("url", request.getContextPath() + "/memberLogin.mbr");
			return;
		}
		session.setAttribute("sMid", vo.getMid());
		session.setAttribute("sLevel", vo.getLevel());
		session.setAttribute("sLevelName", vo.getLevelName());
		session.setAttribute("sNickName", vo.getNickName());
		session.setAttribute("sLastDate", vo.getLastDate());
		
		// --------------------------------------------------
		// DB 저장 : 오늘방문횟수, 전체방문횟수, 포인터 1씩 증가
		// --------------------------------------------------
		//최종방문일과 오늘날짜 비교해서 다른 경우, 오늘방문횟수(todayCnt)값을 0으로 초기화
    String strNow = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if (! vo.getLastDate().substring(0, 10).equals(strNow)) 
			dao.updateTodayCnt(vo.getIdx(), vo.getMid());//todayCnt초기화 : 0
		//1.오늘방문횟수, 전체방문횟수 1씩 증가 
		dao.updateVisitCntAndTodayCnt(vo.getIdx(), vo.getMid());
		//2.포인터 1씩 증가(방문시마다 1포인트씩 증가<세션저장,DB저장>, 1일 10회 이하)
		if (0 <= intSPoint && 10 >= intSPoint) //포인트 1일 10회 이하(세션정보값으로 체크)
			dao.updatePoint(vo.getIdx(), vo.getMid());//DB포인트 1포인트 증가
		
		// --------------------------------------------------
		// 세션 저장 : 오늘방문횟수, 전체방문횟수, 포인터 1씩 증가
		// --------------------------------------------------
		session.setAttribute("sTodayVCnt", vo.getTodayCnt() + 1);
		session.setAttribute("sVCnt", vo.getVisitCnt() + 1);
		session.setAttribute("sPoint", ++intSPoint);
		
		// --------------------------------------------------
		// 세션 저장 : 활동내역(방명록에 올린 글 수, 게시판에 올린 글 수, 자료실에 올린 글 수
		// --------------------------------------------------
		BoardDAO boardDao = new BoardDAO();
		GuestDAO guestDao = new GuestDAO();
		session.setAttribute("sGuWritingCnt", 
				guestDao.searchGuestWriteCnt(vo.getMid(), vo.getName()));
		session.setAttribute("sBdWritingCnt", 
				boardDao.searchBoardWriteCnt(vo.getMid(), vo.getNickName())
				+ boardDao.searchBoardreplyWriteCnt(vo.getMid(), vo.getNickName()));
		//자료실은 수업 아직 안했음
		//session.setAttribute("sPdsWritingCnt", 
		//	new PdsDAO().searchPdsWriteCnt(vo.getMid(), vo.getName(), vo.getNickName()));
		
System.out.println("idSave= " + idSave);		
		//idSave체크시 : 쿠키에 아이디(mid)를 저장 checkbox checked 클릭 여부 - on/null
		Cookie cookie = new Cookie("cMid", mid);
		if (null != idSave && idSave.equals("on")) 
			cookie.setMaxAge(60*60*24*7); //쿠키 만료시간은 7일
		else 
			cookie.setMaxAge(0); //쿠키 삭제
		response.addCookie(cookie);

		request.setAttribute("msg", "memberLoginOk");
		request.setAttribute("url", request.getContextPath() + "/memberMain.mbr");
	}
}
