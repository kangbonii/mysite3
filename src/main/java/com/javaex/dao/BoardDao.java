package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlsession;
	
	public List<BoardVo> getList(int startRnum,int endRnum, String kwd){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("startRnum",startRnum);
		map.put("endRnum",endRnum);
		map.put("kwd", kwd );
		
		List<BoardVo> list = sqlsession.selectList("board.selectList", map);
		return list;
	}
	
	//전체글갯수
	public int totalCount(String kwd) {
		return sqlsession.selectOne("board.totalCount",kwd);
	}
	
	
	public int insert(BoardVo vo) {
		return sqlsession.insert("board.insert",vo);
	}
	
	public BoardVo selectno(int no) {
		return sqlsession.selectOne("board.selectOne",no);
	}
	
	public int update(BoardVo boardvo) {
		return sqlsession.update("board.update",boardvo);
	}

	public int delete(int no) {
		return sqlsession.delete("board.delete",no);
	}

	public void updateHit(int no) {
		sqlsession.update("board.updatehit", no);	
	}
	
	public void insert70() {
		BoardVo vo = new BoardVo();
		vo.setUser_no(2);
		for(int i = 1; i <= 70; i++) {
			vo.setTitle(i+"번째 제목");
			vo.setContent(i+"안녕");
			
			sqlsession.insert("board.insert",vo);
		}
	}

//	public List<BoardVo> getList(){
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<BoardVo> list = new ArrayList<BoardVo>();
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
//					"select b.no, "+
//				       "	b.title, "+
//				       "	b.content, "+
//				       "	b.hit, "+
//				       "	b.reg_date, u.name, "+
//				       "	b.user_no from board b "+
//				       "	join users u on b.user_no = u.no ";
//			pstmt = conn.prepareStatement(query);
//			rs = pstmt.executeQuery();
//			
//			
//			// 4.결과처리
//			while(rs.next()) {
//				int no = rs.getInt("no");
//				String title  = rs.getString("title");
//				String content  = rs.getString("content");
//				int hit  = rs.getInt("hit");
//				Date reg_date  = rs.getDate("reg_date");
//				String name  = rs.getString("name");
//				int user_no  = rs.getInt("user_no");
//				
//				BoardVo vo= new BoardVo (no, title, content ,hit ,reg_date,user_no,name);
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
//	
//	public int insert(BoardVo vo) {
//		// 0. import java.sql.*;
//				Connection conn = null;
//				PreparedStatement pstmt = null;
//				int count =0;
//
//				try {
//					// 1. JDBC 드라이버 (Oracle) 로딩
//					Class.forName("oracle.jdbc.driver.OracleDriver");
//					
//					// 2. Connection 얻어오기 
//					String url = "jdbc:oracle:thin:@localhost:1521:xe";
//					conn = DriverManager.getConnection(url, "webdb", "webdb");
//					
//					// 3. SQL문 준비 / 바인딩 / 실행
//					String query =         
//							"insert into board "+
//						    "			(no, "+
//						    "			title,  "+
//						    "			content,  "+
//						    "			reg_date, "+
//						    " 			user_no	) "+
//						    "values (seq_board_no.nextval, "+
//						        "		?, "+
//						        "		?, "+
//						        "       sysdate, " +
//						        "		? ) ";
//						        
//					pstmt = conn.prepareStatement(query);
//					pstmt.setString(1, vo.getTitle());
//					pstmt.setString(2, vo.getContent());
//					pstmt.setInt(3, vo.getUser_no());
//			
//					
//					count = pstmt.executeUpdate();
//					
//					// 4.결과처리					
//					
//					
//					
//				} catch (ClassNotFoundException e) {
//					System.out.println("error: 드라이버 로딩 실패 - " + e);
//				} catch (SQLException e) {
//					System.out.println("error:" + e);
//				} finally {
//					// 5. 자원정리
//					try {
//						if (pstmt != null) {
//							pstmt.close();
//						}
//						if (conn != null) {
//							conn.close();
//						}
//					} catch (SQLException e) {
//						System.out.println("error:" + e);
//					}
//				}
//		
//		return count;
//	}
//	
//	
//	
//	public int delete(int num) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
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
//			String query =         
//					"DELETE FROM board WHERE NO = ?";
//			pstmt = conn.prepareStatement(query);
//			pstmt.setInt(1,  num);
//			count = pstmt.executeUpdate();
//			// 4.결과처리			
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
//		return count;
//	}
//	
//	
//	
//	
//	public BoardVo readList(int num){
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		BoardVo vo = null;
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
//					"select no, title, content,user_no from board where no = ? ";
//			pstmt = conn.prepareStatement(query);
//			pstmt.setInt(1,num);
//			rs = pstmt.executeQuery();
//			
//			
//			// 4.결과처리
//			while(rs.next()) {
//				int no = rs.getInt("no");
//				String title  = rs.getString("title");
//				String content  = rs.getString("content");
//				int user_no  = rs.getInt("user_no");
//				
//				vo= new BoardVo (no, title, content ,user_no);
//				
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
//		return vo;
//	}
//	
//	
//	
//	
//	public int update(BoardVo vo) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
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
//			String query =         
//					"update board set title = ? , content =?  where no = ?" ;
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1,  vo.getTitle());
//			pstmt.setString(2,   vo.getContent());
//			pstmt.setInt(3,   vo.getNo());
//			count = pstmt.executeUpdate();
//			
//			
//			
//			
//			// 4.결과처리			
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
//		return count;
//	}





	
}
