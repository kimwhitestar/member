package member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Paging;
import member.database.MemberDAO;
import member.database.MemberVO;

//운영자회원용 MemberListCommand
public class MemberListCommand implements MemberInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao = new MemberDAO();
		
		//페이징 설정하기 
		int pageNo = request.getParameter("pageNo")==null ? 1 : Integer.parseInt(request.getParameter("pageNo"));//현 페이지
		int totalRecordSize = dao.memberListTotRecCnt((char)0, 0);//목록의 총 레코드 갯수
		int pagingRecordSize = 5;//각 페이징할 목록의 레코드 갯수
		int blockingSize = 3;//페이징할 블록 갯수
		
		//페이징을 설정하면, 페이징 객체로 부터 산출된 페이징정보가 REQUEST 객체에 설정된다
		Paging paging = new Paging(request, response);
		paging.setPaging(pageNo, totalRecordSize, pagingRecordSize, blockingSize);
		
		//한 페이징에 표시할 레코드 검색
		List<MemberVO> vos = dao.searchMemberList(
				(char)0, 0, paging.getStartIndexNo(), paging.getPageSize());
		
		request.setAttribute("vos", vos);
		//request.setAttribute("curScrStartNo", vos.size());
	}
}
