package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.database.MemberDAO;
import member.database.MemberVO;

public class MemberUpdateOkCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();
		HttpSession session = request.getSession();
		String sMid = (String)session.getAttribute("sMid");
		String pwd = request.getParameter("pass");
		String nickName = request.getParameter("nickName");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		String telNo = request.getParameter("telNo");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String homepage = request.getParameter("homepage");
		String job = request.getParameter("job");
		String checkedHobbies = request.getParameter("checkedHobbies");
		String content = request.getParameter("content");
		String userInfo = request.getParameter("userInfo");
		String photo = request.getParameter("photo");
		
//		//전화번호 파라미터 연결체크(DB저장을 위해 재체크) : 02-999-9999 or 010-9999-9999)
//		//인터넷연결등의 이유로 파라미터가 정확히 넘어오지 못하거나, 화면 유효성체크 오류로 넘어온 유효하지 않은 파라미터는 유효한 data?를 위해 공란처리?
//		if (11 <= telNo.length() && 13 >= telNo.length()) {
//			int idx1 = telNo.indexOf("-"), idx2 = telNo.lastIndexOf("-");
//			if (-1 == idx1 || -1 == idx2 || idx1 >= idx2) {
//				telNo = "";
//			}
//		} else {
//			telNo = "";
//		}
//		
//		//이메일 파라미터 연결체크 (DB저장을 위해 재체크) : 이메일id@이메일도메인
//		//인터넷연결등의 이유로 파라미터가 정확히 넘어오지 못하거나, 화면 유효성체크 오류로 넘어온 유효하지 않은 파라미터는 유효한 data?를 위해 공란처리?
//		if (3 > email.length() || -1 == email.indexOf("@")) {
//			email = "";
//		}
		
		//회원사진
		String fileSystemName = "";//웹서버(톰캣)에 실제로 저장되는 화일명
		if (photo.equals("noimage")) {
			fileSystemName = "noimage.jpg";//웹서버(톰캣)에 실제로 저장되는 화일명
		}
		else {
			fileSystemName = photo;//웹서버(톰캣)에 실제로 저장되는 화일명
		}
		
		//회원ID, 비밀번호 확인
		MemberVO loginOkVO = dao.searchMemberLogin(sMid, pwd);
		if (null == loginOkVO) {
			request.setAttribute("msg", "memberUpdateNo");
			request.setAttribute("url", request.getContextPath()+"/memberUpdate.mbr");
			return;
		}
		
		if ( ! nickName.equals((String)session.getAttribute("sNickName")) ) {
			//닉네임 중복시
			if (dao.memberNickNameCheck(nickName)) {
				request.setAttribute("msg", "nickNameCheckNo");
				request.setAttribute("url", request.getContextPath()+"/memberUpdate.mbr");
				return;
			}
		}

		//회원정보DB 수정
		MemberVO vo = new MemberVO();
		vo.setIdx(loginOkVO.getIdx());
		vo.setMid(loginOkVO.getMid());
		vo.setPwd(pwd);
		vo.setNickName(nickName);
		vo.setName(name);
		vo.setGender(gender);
		vo.setBirthday(birthday);
		vo.setTel(telNo);
		vo.setAddress(address);
		vo.setEmail(email);
		vo.setHomepage(homepage);
		vo.setJob(job);
		vo.setHobby(checkedHobbies);
		vo.setPhoto(fileSystemName);
		vo.setContent(content);
		vo.setUserInfo(userInfo);//공개,비공개

		//회원정보수정
		int res = dao.update(vo);
		//회원정보수정 성공시 세션정보 정정
		if (1 == res) {
			//Form에 출력을 위한 분리작업
			//Email 분리(@)
			String[] arrEmail = vo.getEmail().split("@");
			if (null == arrEmail || 2 > arrEmail.length) {
				request.setAttribute("email1", "");
				request.setAttribute("email2", "");
			} else {
				request.setAttribute("email1", arrEmail[0]);
				request.setAttribute("email2", arrEmail[1]);
			}
			//생일
			request.setAttribute("birthday", vo.getBirthday().substring(0, 10));
			//전화번호 분리(-)
			String[] arrTel = vo.getTel().split("-");
			if (null == arrTel || 3 > arrTel.length) {
				request.setAttribute("tel1", "");
				request.setAttribute("tel2", "");
				request.setAttribute("tel3", "");
			} else {
				request.setAttribute("tel1", arrTel[0]);
				request.setAttribute("tel2", arrTel[1]);
				request.setAttribute("tel3", arrTel[2]);
			}
			//주소 분리(/)
			String[] arrAddress = vo.getAddress().split("/");
			if (null == arrAddress || 3 > arrAddress.length) {
				request.setAttribute("postcode", "");
				request.setAttribute("roadAddress", "");
				request.setAttribute("extraAddress", "");
				request.setAttribute("detailAddress", "");
			} else {
				request.setAttribute("postcode", arrAddress[0]);
				request.setAttribute("roadAddress", arrAddress[1]);
				request.setAttribute("extraAddress", arrAddress[2]);
				request.setAttribute("detailAddress", arrAddress[3]);
			}
			
			session.setAttribute("sName", vo.getName());
			session.setAttribute("sNickName", vo.getNickName());
			request.setAttribute("msg", "memberUpdateOk");

			//회원정보수정 실패시 세션정보 변경없음
		} else {
			request.setAttribute("msg", "memberUpdateNo");
		}		
		request.setAttribute("url", request.getContextPath()+"/memberUpdate.mbr");
	}
}