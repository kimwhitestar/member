package study.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String sql = "";
	
	LoginVO vo = null;
	
	//생성시 DB Connection 연결 처리
	public LoginDAO() {
		String url = "jdbc:mysql://localhost:3306/javagreen03";
		String user = "root";
		String password = "1234";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch(ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패~~");
		} catch (SQLException e) {
			System.out.println("데이타베이스 연결 실패~~");
		}
	}
	
	public void connClose() {
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("Connection Close 실패~~");
			}
		}
	}
	
	public void pstmtClose() {
		if (null != pstmt) {
			try {
				 pstmt.close();
			} catch (SQLException e) {
				System.out.println("PreparedStatement Close 실패~~");
			}
		}
	}
	
	public void rsClose() {
		if (null != rs) {
			try {
				 rs.close();
			} catch (SQLException e) {
				System.out.println("ResultSet Close 실패~~");
			}
		}
	}

	//로그인 체크(id, pwd)
	public LoginVO loginCheck(String mid, String pwd) {
		try {
			sql = "select * from login where mid = ? and pwd = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();//pk 1건 획득
			
			if (rs.next()) {
				vo = new LoginVO();
				vo.setName(rs.getString("name"));//로그인 user name
				vo.setPoint(rs.getInt("point"));
				vo.setvCount(rs.getInt("vCount"));
			}
		} catch(SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			rsClose();
			pstmtClose();
		}
		return vo;
	}
	//아이디 중복체크
	public boolean searchExistMid(String mid) {
		boolean existMid = false;//아이디중복존재FLG = flase
		try {
			sql = "select mid from login where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();//pk 1건 획득
			
			//아이디 검색 결과가 존재하면
			if (rs.next()) { 
				existMid = true; //아이디중복존재FLG = true
			}
		} catch(SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			rsClose();
			pstmtClose();
		}
		return existMid;
	}
	//로그인 정보 조회
	public LoginVO search(String mid) {
		try {
			sql = "select * from login where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();//pk 1건 획득
			
			if (rs.next()) {
				vo = new LoginVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setPoint(rs.getInt("point"));
				vo.setLastDate(rs.getString("lastDate"));
				vo.setvCount(rs.getInt("vCount"));
			}
		} catch(SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			rsClose();
			pstmtClose();
		}
		return vo;
	}
	//전체회원 목록조회
	public List<LoginVO> searchLoginList() {
		List list = new ArrayList();
		try {
			sql = "select * from login order by idx desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();//pk 1건 획득
			
			if (rs.next()) {
				vo = new LoginVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setPoint(rs.getInt("point"));
				vo.setLastDate(rs.getString("lastDate"));
				vo.setvCount(rs.getInt("vCount"));
				list.add(vo);
			}
		} catch(SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			rsClose();
			pstmtClose();
		}
		return list;
	}
	//point와 방문count 1씩 증가
	public void updatePointAndVcountAndLastdate(String mid) {
		int res = 0;
		try {
			sql = "update login set point = point + 1, vCount = vCount + 1, lastDate = now() where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			res = pstmt.executeUpdate();
			System.out.println("방문횟수, 포인트 update = "+ res);
//			pstmt.close();
//			
//			sql = "update login set vCount = vCount + 1 where mid = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, mid);
//			res = pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
	}

	//회원 가입
	public int insert(LoginVO vo) {
		int res = 0;
		try {
			sql = "insert into login values (default, ?, ?, ?, default, default, default)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			res = pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	//회원 탈퇴
	public int delete(String mid) {
		int res = 0;
		try {
			sql = "delete from login where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			res = pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

	//회원정보 수정
	public int update(LoginVO vo) {
		int res = 0;
		try {
			sql = "update login set pwd = ?, name = ? where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getMid());
			res = pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("SQL 에러 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}
}