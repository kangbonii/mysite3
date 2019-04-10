package com.javaex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestBookVo;

@Repository
public class GuestBookDao {

	@Autowired
	private SqlSession sqlsession;

	public List<GuestBookVo> getList() {
		List<GuestBookVo> list = sqlsession.selectList("guestbook.selectList");
		return list;
	}
	public int delete(GuestBookVo vo) {
		return sqlsession.delete("guestbook.delete", vo);
		
	}
	
	public int insert(GuestBookVo vo) {
		return sqlsession.insert("guestbook.insert",vo);
	}


//	public List<GuestBookVo> getList(){
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
//
//		try {
//			// 1. JDBC 드라이버 (Oracle) 로딩
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			// 2. Connection 얻어오기 
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			conn = DriverManager.getConnection(url, "webdb", "webdb");
//			
//			// 3. SQL문 준비 / 바인딩 / 실행
//			String query =         
//					"select no, "+
//				       "	name, "+
//				       "	password, "+
//				       "	content, "+
//				       "	reg_date "+
//			     	"from guestbook1 ";
//			pstmt = conn.prepareStatement(query);
//			rs = pstmt.executeQuery();
//			
//			
//			// 4.결과처리
//			while(rs.next()) {
//				int no = rs.getInt("no");
//				String name  = rs.getString("name");
//				String password  = rs.getString("password");
//				String content  = rs.getString("content");
//				Date reg_date  = rs.getDate("reg_date");
//				
//				GuestBookVo vo= new GuestBookVo (no, name, password ,content ,reg_date);
//				list.add(vo);
//				
//			}
//			
//			
//			
//			
//		} catch (ClassNotFoundException e) {
//			System.out.println("error: 드라이버 로딩 실패 - " + e);
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			// 5. 자원정리
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				System.out.println("error:" + e);
//			}
//		}
//		return list;
//	}
//	

//	public GuestBookVo getSeleectNo(Integer num){
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		GuestBookVo book = new GuestBookVo();
//	
//
//		try {
//			// 1. JDBC 드라이버 (Oracle) 로딩
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			// 2. Connection 얻어오기 
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			conn = DriverManager.getConnection(url, "webdb", "webdb");
//			
//			// 3. SQL문 준비 / 바인딩 / 실행
//			String query =         
//					"select no, "+
//						       "	name, "+
//						       "	password, "+
//						       "	content, "+
//						       "	reg_date "+
//			     	"from guestbook1 " +
//				       "where no=? ";
//			pstmt = conn.prepareStatement(query);
//			pstmt.setInt(1, num);
//			rs = pstmt.executeQuery();
//
//
//			System.out.println(rs);
//			// 4.결과처리
//			while(rs.next()) {
//			int no = rs.getInt("no");
//			String name  = rs.getString("name");
//			String password  = rs.getString("password");
//			String content  = rs.getString("content");
//			Date reg_date  = rs.getDate("reg_date");
//			
//			book = new GuestBookVo (no, name, password ,content ,reg_date);
//			}
//		} catch (ClassNotFoundException e) {
//			System.out.println("error: 드라이버 로딩 실패 - " + e);
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			// 5. 자원정리
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				System.out.println("error:" + e);
//			}
//		}
//		return book;
//	}
//	



//	public boolean delete(Integer num) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		Boolean res = true;
//		try {
//			// 1. JDBC 드라이버 (Oracle) 로딩
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//
//			// 2. Connection 얻어오기
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			conn = DriverManager.getConnection(url, "webdb", "webdb");
//
//			// 3. SQL문 준비 / 바인딩 / 실행
//			String query = " DELETE FROM guestbook1 WHERE NO = ?";
//			pstmt = conn.prepareStatement(query);
//			pstmt.setInt(1, num);
//			rs = pstmt.executeQuery();
//			// 4.결과처리
//		} catch (ClassNotFoundException e) {
//			res = false;
//			System.out.println("error: 드라이버 로딩 실패 - " + e);
//		} catch (SQLException e) {
//			res = false;
//			System.out.println("error:" + e);
//		} finally {
//			// 5. 자원정리
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				res = false;
//				System.out.println("error:" + e);
//			}
//		}
//		return res;
//	}
//
	
	
	

	
//	public int insert(GuestBookVo vo) {
//		// 0. import java.sql.*;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		int count = 0;
//
//		try {
//			// 1. JDBC 드라이버 (Oracle) 로딩
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//
//			// 2. Connection 얻어오기
//			String url = "jdbc:oracle:thin:@localhost:1521:xe";
//			conn = DriverManager.getConnection(url, "webdb", "webdb");
//
//			// 3. SQL문 준비 / 바인딩 / 실행
//			String query = "insert into guestbook1 " + "			(no, " + "			name, " + "			password, "
//					+ "			content, " + "			reg_date ) " + "values (seq_guestbook1_no.nextval, "
//					+ "		?, " + "		?, " + " 		?, " + "       sysdate) ";
//
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, vo.getName());
//			pstmt.setString(2, vo.getPassword());
//			pstmt.setString(3, vo.getContent());
//
//			count = pstmt.executeUpdate();
//
//			// 4.결과처리
//
//		} catch (ClassNotFoundException e) {
//			System.out.println("error: 드라이버 로딩 실패 - " + e);
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			// 5. 자원정리
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				System.out.println("error:" + e);
//			}
//		}
//
//		return count;
//	}

}
