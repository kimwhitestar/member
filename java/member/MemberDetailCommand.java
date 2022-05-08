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
		String attrMid = (String)request.getAttribute("attrMid");
		MemberVO vo = new MemberDAO().search(attrMid);
		String strLevel = new String("");
		if (null != vo) {
			if (0 == vo.getLevel()) strLevel = "관리자";
			else if (1 == vo.getLevel()) strLevel = "준회원";
			else if (2 == vo.getLevel()) strLevel = "정회원";
			else if (3 == vo.getLevel()) strLevel = "우수회원";
			else if (4 == vo.getLevel()) strLevel = "운영자";
			vo.setStrLevel(strLevel);
		}
		
		request.setAttribute("vo", vo);
	}
}
