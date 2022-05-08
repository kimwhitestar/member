package member;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import conn.SecurityUtil;
import member.database.MemberDAO;
import member.database.MemberVO;

public class MemberLoginOkCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");
		String pwd = request.getParameter("pwd");
		String idSave = request.getParameter("idSave");
		
		//비밀번호 암호화처리-SHA2(sha256)
		String shaPwd = new SecurityUtil().encryptSHA256(pwd);

		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.searchMemberLogin(mid, shaPwd);
		if (null == vo || 0 == vo.getNickName().trim().length()) {
			request.setAttribute("msg", "memberLoginNo");
			request.setAttribute("url", request.getContextPath() + "/memberLogin.mbr");
		}
		
		//로그인 성공 (1.주요자료 세션 저장, 2. 오늘 방문횟수, 3.방문수,포인터 증가, 4.쿠키에 아이디저장유무? 
		HttpSession session = request.getSession();
		session.setAttribute("sMid", vo.getMid());
		session.setAttribute("sNickName", vo.getNickName());
		session.setAttribute("sLevel", vo.getLevel());
//		session.setAttribute("sLastDate", vo.getLastDate());
//		session.setAttribute("sPoint", vo.getPoint());
//		session.setAttribute("sTodayCnt", vo.getTodayCnt());
//		session.setAttribute("sVisitCnt", vo.getVisitCnt());
		
    String strNow = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    System.out.println("vo:" + vo);
		System.out.println("strNow : "+strNow);
		
		//최종방문일과 오늘날짜 비교해서 다른 경우, 오늘방문횟수(todayCnt)값을 0으로 초기화
		if (vo.getLastDate().substring(0, 10).equals(strNow)) {
			int res = dao.updateTodayCnt(mid);
		}

//학생질문 - ?
//		int point = vo.getPoint();
//		if (vo.getTodayCnt() > 10) point++;
		
		int res = dao.updateVisitCntAndTodayCntAndPoint(mid);
		
		//쿠키에 아이디(mid)를 저장 checkbox checked 클릭 여부 - on/null
		Cookie cookie = new Cookie("cMid", mid);
		if (null != idSave && idSave.equals("on")) {
			cookie.setMaxAge(60*60*24*7); //쿠키 만료시간은 7일
		} 
		else {
			cookie.setMaxAge(0); //쿠키 삭제
		}
		response.addCookie(cookie);

		request.setAttribute("msg", "memberLoginOk");
		request.setAttribute("url", request.getContextPath() + "/memberMain.mbr");
	}
}
