package member.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conn.MysqlConn;

public class MemberDAO {
	private final MysqlConn instance = MysqlConn.getInstance();
	private final Connection conn = instance.getConn();
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private MemberVO vo = null;
	private String sql = new String("");

	public MemberDAO() {}
	
	//페이징 총 레코드건수//관리자의 경우 level=0, 관리자가 아닐 경우 level=99
	public int totRecCnt(int level) {
		int totRecCnt = 0;
		try {
			if (0 == level) { //관리자
				sql = "select count(*) as totRecCnt from member";
			} else { //운영자회원
				sql = "select count(*) as totRecCnt from member where userInfo = '공개' ";
			}
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next(); //ResultSet레코드움직이기(count함수는 무조건 0값조차 가져옴)
			totRecCnt = rs.getInt("totRecCnt");
		} catch (SQLException e) {
			e.getMessage();
		} finally {
			instance.pstmtClose();
			instance.rsClose();
		}
		return totRecCnt;
	}
	//관리자의 경우 level=0, 관리자가 아닐 경우 level=99
	public List<MemberVO> searchMemberList(int level, int startIndexNo, int pageSize) {
		List<MemberVO> vos = new ArrayList();
		try {
			if (0 == level) { //관리자
				sql = "select *, timestampdiff(day, lastDate, now()) as overDaysUserDel from member order by idx desc limit ?, ? ";
			} else {
				sql = "select * from member where userInfo = '공개' order by idx desc limit ?, ? ";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startIndexNo);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new MemberVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));//암호화 비밀번호ㅜㅜ
				vo.setNickName(rs.getString("nickName"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setBirthday(rs.getString("birthday"));
				vo.setTel(rs.getString("tel"));
				vo.setAddress(rs.getString("address"));
				vo.setEmail(rs.getString("email"));
				vo.setHomepage(rs.getString("homepage"));
				vo.setJob(rs.getString("job"));
				vo.setHobby(rs.getString("hobby"));
				vo.setPhoto(rs.getString("photo"));
				vo.setContent(rs.getString("content"));
				vo.setUserInfo(rs.getString("userInfo"));
				vo.setUserDel(rs.getString("userDel"));
				vo.setPoint(rs.getInt("point"));
				vo.setLevel(rs.getInt("level"));
				vo.setVisitCnt(rs.getInt("visitCnt"));
				vo.setStartDate(rs.getString("startDate"));
				vo.setLastDate(rs.getString("lastDate"));
				vo.setTodayCnt(rs.getInt("todayCnt"));
				if (0 == level) { //관리자
					vo.setOverDaysUserDel(rs.getInt("overDaysUserDel"));
				}
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			instance.pstmtClose();
			instance.rsClose();
		}
		return vos;
	}

	public MemberVO searchMemberLogin(String mid, String pwd) {
		try {
			sql = "select * from member where mid = ? and pwd = ? and userDel = 'No'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new MemberVO();
				vo.setIdx(rs.getInt("idx"));//pk(idx,mid)
				vo.setMid(rs.getString("mid"));//pk(idx,mid)
				vo.setNickName(rs.getString("nickName"));
				vo.setLevel(rs.getInt("level"));
				vo.setLastDate(rs.getString("lastDate"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			instance.pstmtClose();
			instance.rsClose();
		}
		return vo;
	}

	//멤버회원 개별정보 취득
	public MemberVO search(String sMid) {
		try {
			sql = "select * from member where mid = ? and userDel = 'No'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sMid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new MemberVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));//암호화 비밀번호ㅜㅜ
				vo.setNickName(rs.getString("nickName"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setBirthday(rs.getString("birthday"));
				vo.setTel(rs.getString("tel"));
				vo.setAddress(rs.getString("address"));
				vo.setEmail(rs.getString("email"));
				vo.setHomepage(rs.getString("homepage"));
				vo.setJob(rs.getString("job"));
				vo.setHobby(rs.getString("hobby"));
				vo.setPhoto(rs.getString("photo"));
				vo.setContent(rs.getString("content"));
				vo.setUserInfo(rs.getString("userInfo"));
				vo.setUserDel(rs.getString("userDel"));
				vo.setPoint(rs.getInt("point"));
				vo.setLevel(rs.getInt("level"));
				vo.setVisitCnt(rs.getInt("visitCnt"));
				vo.setStartDate(rs.getString("startDate"));
				vo.setLastDate(rs.getString("lastDate"));
				vo.setTodayCnt(rs.getInt("todayCnt"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			instance.pstmtClose();
			instance.rsClose();
		}
		return vo;
	}

	//아이디 중복 체크
	public boolean memberIdCheck(String mid) {
		boolean isExist = false;
		try {
			sql = "select mid from member where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			if (rs.next()) isExist = true;//아이디 중복
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			instance.pstmtClose();
			instance.rsClose();
		}
		return isExist;
	}
	
	//닉네임 중복 체크
	public boolean memberNickNameCheck(String nickName) {
		boolean isExist = false;
		try {
			sql = "select nickName from member where nickName = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickName);
			rs = pstmt.executeQuery();
			if (rs.next()) isExist = true;//닉네임 중복
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			instance.pstmtClose();
			instance.rsClose();
		}
		return isExist;
	}

	public int insert(MemberVO vo) {
		int res = 0;
		try {
			sql = "insert into member values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , default, default, default, default, default, default, default)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1 , vo.getMid());
			pstmt.setString(2 , vo.getPwd());
			pstmt.setString(3 , vo.getNickName());
			pstmt.setString(4 , vo.getName());
			pstmt.setString(5 , vo.getGender());
			pstmt.setString(6 , vo.getBirthday());
			pstmt.setString(7 , vo.getTel());
			pstmt.setString(8 , vo.getAddress());
			pstmt.setString(9 , vo.getEmail());
			pstmt.setString(10 , vo.getHomepage());
			pstmt.setString(11 , vo.getJob());
			pstmt.setString(12 , vo.getHobby());
			pstmt.setString(13 , vo.getPhoto());
			pstmt.setString(14 , vo.getContent());
			pstmt.setString(15 , vo.getUserInfo());
			res = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			instance.pstmtClose();
		}
		return res;
	}

	//오늘 처음 방문시 오늘방문카운트(todayCnt)를 0으로 셋팅한다.
	public int updateTodayCnt(String mid) {
		int res = 0;
		try {
			sql = "update member set todayCnt = 0 where mid = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1 , mid);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			instance.pstmtClose();
		}
		return res;
	}
	
	//방문수, 오늘방문수, 포인트 증가
	public int updateVisitCntAndTodayCntAndPoint(String mid) {
		int res = 0;
		try {
			sql = "update member set point = point+1, visitCnt = visitCnt+1, todayCnt = todayCnt+1, lastDate=now() where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1 , mid);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			instance.pstmtClose();
		}
		return res;
	}

	//회원탈퇴 - 1달간은 회원정보유지, userDel만 OK(회원탈퇴)로 수정
	public int updateUserDel(String mid) {
		int res = 0;
		try {
			sql = "update member set userDel = 'OK' where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1 , mid);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			instance.pstmtClose();
		}
		return res;	
	}
	
	//관리자 -전체회원 목록(공개회원)->선택된 회원의 등급 수정
	public int updateMemberLevel(String level, String mid) {
		int res = 0;
		try {
			sql = "update member set level = ? and mid = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(level));
			pstmt.setString(2, mid);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			instance.pstmtClose();
		}
		return res;	
	}

	//회원탈퇴신청(userDel) 후 30일 경과시, 회원삭제 처리
	public int delete(String mid) {
		int res = 0;
		try {
			sql = "delete from member where mid = ? and 30 <= timestampdiff(day, lastDate, now()) and userDel = 'OK'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			instance.pstmtClose();
		}
		return res;	
	}
}