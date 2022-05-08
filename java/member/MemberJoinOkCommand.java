package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conn.SecurityUtil;
import member.database.MemberDAO;
import member.database.MemberVO;

public class MemberJoinOkCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();
		String mid = request.getParameter("mid");
		String pwd = request.getParameter("pwd");
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
		
		if (null == mid        
			|| null == pwd        
			|| null == nickName   
			|| null == name       
			|| null == gender     
			|| null == birthday   
			|| null == telNo      
			|| null == address    
			|| null == email      
			|| null == homepage   
			|| null == job        
			|| null == checkedHobbies 
			|| null == content    
			|| null == userInfo   
			|| null == photo    ) {
			  
			//되돌아가기
			return;
		}
		
		//전화번호 파라미터 연결체크(DB저장을 위해 재체크) : 02-999-9999 or 010-9999-9999)
		//인터넷연결등의 이유로 파라미터가 정확히 넘어오지 못하거나, 화면 유효성체크 오류로 넘어온 유효하지 않은 파라미터는 유효한 data?를 위해 공란처리?
		if (11 <= telNo.length() && 13 >= telNo.length()) {
			int idx1 = telNo.indexOf("-"), idx2 = telNo.lastIndexOf("-");
			if (-1 == idx1 || -1 == idx2 || idx1 >= idx2) {
				telNo = "";
			}
		} else {
			telNo = "";
		}
		
		//이메일 파라미터 연결체크 (DB저장을 위해 재체크) : 이메일id@이메일도메인
		//인터넷연결등의 이유로 파라미터가 정확히 넘어오지 못하거나, 화면 유효성체크 오류로 넘어온 유효하지 않은 파라미터는 유효한 data?를 위해 공란처리?
		if (3 > email.length() || -1 == email.indexOf("@")) {
			email = "";
		}
		
		//아이디 중복 또는 닉네임 중복시
		if (dao.memberIdCheck(mid)) {
			request.setAttribute("msg", "idCheckNo");
			request.setAttribute("url", request.getContextPath()+"/memberJoin.mbr");
			return;
		} else if (dao.memberNickNameCheck(nickName)) {
			request.setAttribute("msg", "nickNameCheckNo");
			request.setAttribute("url", request.getContextPath()+"/memberJoin.mbr");
			return;
		}
		
		//비밀번호 암호화처리-SHA2(sha256)
		String shaPwd = new SecurityUtil().encryptSHA256(pwd);

		//회원사진
		String fileSystemName = "";//웹서버(톰캣)에 실제로 저장되는 화일명
		if (photo.equals("noimage")) {
			fileSystemName = "noimage.jpg";//웹서버(톰캣)에 실제로 저장되는 화일명
		}
		else {
			fileSystemName = photo;//웹서버(톰캣)에 실제로 저장되는 화일명
		}
		
		//DB에 저장될 각각의 필드길이 체크(화면에서)...
		
		//DB 저장
		MemberVO vo = new MemberVO();
		vo.setMid(mid);
		vo.setPwd(shaPwd);
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

		int res = dao.insert(vo);
		if (1 == res) {
			request.setAttribute("msg", "memberJoinOk");
			request.setAttribute("url", request.getContextPath()+"/memberLogin.mbr");
		} else {
			request.setAttribute("msg", "memberJoinNo");
			request.setAttribute("url", request.getContextPath()+"/memberJoin.mbr");
		}
	}
}
