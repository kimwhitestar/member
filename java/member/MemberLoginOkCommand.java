package member;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.database.MemberDAO;
import member.database.MemberVO;

public class MemberLoginOkCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");//login요청-mid
		String pwd = request.getParameter("pwd");//login요청-pwd
		String idSave = request.getParameter("idSave");//logoin요청-idSave저장(mid쿠키저장)

		HttpSession session = request.getSession();
			
		// --------------------------------------------------
		// 로그인 성공시 처리 내용
		// --------------------------------------------------
		// 1.오늘방문횟수, 전체방문횟수 1씩 증가 
		// 2.포인터 1씩 증가(방문시마다 1포인트씩 증가하며 1일 10회 이하, 가입시 100 포인트 증가-insert시 db default 증가)
		// 3.주요자료 세션 저장 
		// 4.쿠키에 아이디저장유무? 
		// --------------------------------------------------
		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.searchMemberLogin(mid, pwd);
		//DB nickName은 not null이며, 닉네임 세션 저장하여 전체 웹페이지마다 닉네임 출력 처리
		if (null == vo || 0 == vo.getNickName().trim().length()) {
			request.setAttribute("msg", "memberLoginNo");
			request.setAttribute("url", request.getContextPath() + "/memberLogin.mbr");
			return;
		}
		session.setAttribute("sIdx", vo.getIdx());
		session.setAttribute("sMid", vo.getMid());
		session.setAttribute("sLevel", vo.getLevel());
		session.setAttribute("sLevelName", vo.getLevelName());
		session.setAttribute("sName", vo.getName());
		session.setAttribute("sNickName", vo.getNickName());
		session.setAttribute("sLastDate", vo.getLastDate());
		
		// --------------------------------------------------
		// DB 저장 : 오늘방문횟수, 전체방문횟수, 포인터 1씩 증가
		// --------------------------------------------------
		//최종방문일과 오늘날짜 비교해서 다른 경우, 오늘방문횟수(todayCnt)값을 0으로 초기화
    String strNow = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if (! vo.getLastDate().substring(0, 10).equals(strNow)) {
			dao.updateTodayCnt(vo.getIdx(), vo.getMid());//todayCnt초기화 : 0
			vo.setTodayCnt(0);
		}
		//1.오늘방문횟수, 전체방문횟수 1씩 증가 
		dao.updateVisitCntAndTodayCnt(vo.getIdx(), vo.getMid());//DB 방문횟수 증가
		vo.setTodayCnt(vo.getTodayCnt() + 1);
		vo.setVisitCnt(vo.getVisitCnt() + 1);
		if (10 >= vo.getTodayCnt()) {
			//2.포인터 5씩 증가(방문시마다 5포인트씩 증가<DB저장>, 1일 10회 이하)
			dao.updatePoint(vo.getIdx(), vo.getMid());//DB 포인트 5포인트 증가
			vo.setPoint(vo.getPoint() + 5);
		}
		// --------------------------------------------------
		// 세션 저장(Mypage 회원전용방 출력용) : 오늘방문횟수, 전체방문횟수, 포인트
		// --------------------------------------------------
		session.setAttribute("sTodayVCnt", vo.getTodayCnt());
		session.setAttribute("sVCnt", vo.getVisitCnt());
		session.setAttribute("sPoint", vo.getPoint());
		
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
