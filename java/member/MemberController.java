package member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("*.mbr")
public class MemberController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberInterface command = null;
		String viewPage = "/WEB-INF";
		String uri = request.getRequestURI();
		String com = uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf("."));
		
		//세션이 끊겼으면, 회원레벨을 비회원으로 바꿔서 작업의 진행을 로그인창으로 보낸다.
		HttpSession session = request.getSession();
		int level = session.getAttribute("sLevel")==null ? 99 : (int) session.getAttribute("sLevel");
		
		//로그인
		if (com.equals("memberLogin")) {
			command = new MemberLoginCommand();
			command.execute(request, response);
			viewPage += "/member/memberLogin.jsp";
		}
		//로그인OK
		else if (com.equals("memberLoginOk")) {
			command = new MemberLoginOkCommand();
			command.execute(request, response);
			viewPage = "/message/message.jsp";
		}
		//아이디 찾기
		else if (com.equals("memberIdFindout")) {
//			command = new MemberIdFindoutCommand();
//			command.execute(request, response);
			viewPage += "/member/memberIdFindout.jsp";
		}
		//아이디 찾기Ok
		else if (com.equals("memberIdFindoutOk")) {
			command = new MemberIdFindoutOkCommand();
			command.execute(request, response);
			viewPage = "/message/message.jsp";
		}
		//비밀번호 찾기
		else if (com.equals("memberPwdChange")) {
//			command = new MemberPwdFindoutCommand();
//			command.execute(request, response);
			viewPage += "/member/memberPwdChange.jsp";
		}
		//비밀번호 찾기Ok
		else if (com.equals("memberPwdChangeOk")) {
			command = new MemberPwdChangeOkCommand();
			command.execute(request, response);
			viewPage = "/message/message.jsp";
		}
		//ID 중복체크
		else if (com.equals("memberIdCheck")) {
			command = new MemberIdCheckCommand();
			command.execute(request, response);
			viewPage += "/member/memberIdCheck.jsp";
		}
		//닉네임 중복체크 
		else if (com.equals("memberNickNameCheck")) {
			command = new MemberNickNameCheckCommand();
			command.execute(request, response);
			viewPage += "/member/memberNickNameCheck.jsp";
		}
		//회원가입
		else if (com.equals("memberJoin")) {
			viewPage += "/member/memberJoin.jsp";
		}
		//회원가입OK
		else if (com.equals("memberJoinOk")) {
			command = new MemberJoinOkCommand();
			command.execute(request, response);
			viewPage = "/message/message.jsp";
		}
		else if (4 < level) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/");
			dispatcher.forward(request, response);
		}
		//로그인 Main
		else if (com.equals("memberMain")) {
			command = new MemberMainCommand();
			command.execute(request, response);
			viewPage += "/member/memberMain.jsp";
		}
		//회원탈퇴-회원로그인불허(회원삭제 대기기간 : 30일)
		else if (com.equals("memberDeletePract")) {
			command = new MemberDeletePractCommand();
			command.execute(request, response);
			viewPage = "/message/message.jsp";
		}
		//로그아웃
		else if (com.equals("memberLogout")) {
			command = new MemberLogoutCommand();
			command.execute(request, response);
			viewPage += "/member/memberLogout.jsp";
		}
		//회원목록(공개회원)
		else if (com.equals("memberList")) {
			command = new MemberListCommand();
			command.execute(request, response);
			viewPage += "/member/memberList.jsp";
		}
		//회원상세조회
		else if (com.equals("memberDetail")) {
			command = new MemberDetailCommand();
			command.execute(request, response);
			viewPage += "/member/memberDetail.jsp";
		}
		//회원정보수정
		else if (com.equals("memberUpdate")) {
			command = new MemberUpdateCommand();
			command.execute(request, response);
			viewPage += "/member/memberUpdate.jsp";
		}
		//회원정보수정OK
		else if (com.equals("memberUpdateOk")) {
			command = new MemberUpdateOkCommand();
			command.execute(request, response);
			viewPage = "/message/message.jsp";
		}
		//회원비밀번호수정
		else if (com.equals("memberUpdatePwd")) {
			command = new MemberUpdatePwdCommand();
			command.execute(request, response);
			viewPage += "/member/memberUpdatePwd.jsp";
		}
		
		request.getRequestDispatcher(viewPage).forward(request, response);
	}
}
