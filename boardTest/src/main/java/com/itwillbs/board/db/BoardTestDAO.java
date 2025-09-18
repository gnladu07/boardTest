package com.itwillbs.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardTestDAO {
	
	// 공통 변수 선언
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	// 공통 메서드 선언 - 디비연결(CP)
	private Connection getConnectCP() throws Exception {
		
		Context initCTX = new InitialContext();
		
		DataSource ds = (DataSource)initCTX.lookup("java:comp/env/jdbc/boardTest");
		
		conn = ds.getConnection();
		
		System.out.println(" DAO : 디비연결 성공! (Connection Pool 사용) ");
		System.out.println(" DAO : "+conn);
		
		return conn;
	}
	// 공통 메서드 선언
	
	// 공통 메서드 선언 - 자원해제
	private void closeDB() {
		System.out.println(" DAO : DB 연결된 자원을 해제 ");
		
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	// 게시판 글쓰기 - start
	public void insertBoard(BoardTestDTO dto) {
		
		int bno = 0;
		
		try {
			// 1) 2) 디비 연결
			conn = getConnectCP();
			
			// 3) SQL쿼리 & pstmt 객체
			sql="select max(bno) from itwill_board";
			pstmt = conn.prepareStatement(sql);
			
			// 4) SQL 구문 실행
			rs = pstmt.executeQuery();
			
			// 5) 데이터 처리
			if(rs.next()) {
				System.out.println(" if ");
				bno = rs.getInt("max(bno)") + 1;
			}
			System.out.println(" DAO : bno : "+bno);
			
			sql = "insert into itwill_board (bno, name, pass, subject, content,"
					+ "readcount, re_ref, re_lev, re_seq, date, ip, file) "
					+ "values(?,?,?,?,?,?,?,?,?,now(),?,?)";
			pstmt = conn.prepareStatement(sql);
			
			// ???
			pstmt.setInt(1, bno);
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPass());
			pstmt.setString(4, dto.getSubject());
			pstmt.setString(5, dto.getContent());
			pstmt.setInt(6, 0);   // 조회수 0
			
			pstmt.setInt(7, bno); // ref - 초기값 글번호와 같음
			pstmt.setInt(8, 0);   // lev - 초기값은 0
			pstmt.setInt(9, 0);   // seq - 초기값은 0
			
			pstmt.setString(10, dto.getIp());
			pstmt.setString(11, dto.getFile());
			
			pstmt.executeUpdate();
			System.out.println(" DAO : 게시판 글쓰기 완료! ");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
	}
	
	// 게시판 총 글 개수 조회 - start
	public int getBoardCount() {
		int cnt = 0;
		
		try {
			conn = getConnectCP();
			
			sql = "select count(*) from itwill_board";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			System.out.println(" DAO : 게시판 글 총 개수 조회 완료! ");
			
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			System.out.println(" DAO : 총 개수는 "+cnt+"개 ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return cnt;
	}
	
	// 게시판 목록정보를 조회 - start
	public List<BoardTestDTO> getBoardList(){
		List<BoardTestDTO> btlist = new ArrayList<BoardTestDTO>();
		
		try {
			conn = getConnectCP();
			
			sql = "select * from itwill_board order by bno desc";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardTestDTO dto = new BoardTestDTO();
				
				dto.setBno(rs.getInt("bno"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setFile(rs.getString("file"));
				dto.setIp(rs.getString("ip"));
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pass"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setSubject(rs.getString("subject"));
				
				btlist.add(dto);
			}
			System.out.println(" DAO : 게시판 목록 조회 성공! ");
			System.out.println(" DAO : 총 "+btlist.size()+"개");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return btlist;
	}
	
	// 게시판 목록정보를 조회(+페이징) - start
	public List<BoardTestDTO> getBoardList(int startRow, int pageSize){
		List<BoardTestDTO> btlist = new ArrayList<BoardTestDTO>();
		
		try {
			conn = getConnectCP();
			
			sql = "select * from itwill_board order by bno desc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, startRow - 1);
			pstmt.setInt(2, pageSize);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardTestDTO dto = new BoardTestDTO();
				
				dto.setBno(rs.getInt("bno"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setFile(rs.getString("file"));
				dto.setIp(rs.getString("ip"));
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pass"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setSubject(rs.getString("subject"));
				
				btlist.add(dto);
			}
			System.out.println(" DAO : 게시판 목록 조회 성공! ");
			System.out.println(" DAO : 총 "+btlist.size()+"개");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return btlist;
	}
	
	// 해당 게시글의 조회수를 1 증가 - start
	public void increseReadcount(int bno) {
		
		try {
			conn = getConnectCP();
			
			sql = "update itwill_board set readcount = readcount + 1 where bno=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			
			pstmt.executeUpdate();
			
			System.out.println(" DAO : 글 조회수 1증가! ");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
	}
	
	// 해당 게시글의 정보를 모두 가져오기 - start
	public BoardTestDTO getBoard(int bno) {
		BoardTestDTO dto = null;
		
		try {
			conn = getConnectCP();
			
			sql = "select * from itwill_board where bno=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new BoardTestDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setFile(rs.getString("file"));
				dto.setIp(rs.getString("ip"));
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pass"));
				dto.setRe_lev(rs.getInt("re_lev"));
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setSubject(rs.getString("subject"));
			}
			
			System.out.println(" DAO : "+bno+"번 글 정보 저장완료!");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return dto;
	}
	
	// 게시글을 수정하는 메서드 - start
	public int updateBoard(BoardTestDTO dto) {
		int result = -1;
		
		try {
			conn = getConnectCP();
			
			sql = "select pass from itwill_board where bno=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getBno());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dto.getPass().equals(rs.getString("pass"))) {
					sql = "update itwill_board set name=?, subject=?, content=? where bno=?";
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, dto.getName());
					pstmt.setString(2, dto.getSubject());
					pstmt.setString(3, dto.getContent());
					pstmt.setInt(4, dto.getBno());
					
					result = pstmt.executeUpdate();
					
					System.out.println(" DAO - 회원정보 수정완료! ");
				} else {
					System.out.println(" DAO - 회원정보 수정실패 비밀번호 오류! ");
					result = 0; // 비밀번호 오류
				}
			} else {
				System.out.println(" DAO - 회원정보 수정실패 글 정보 없음! ");
				result = -1; // 글 정보 없음
			}
			System.out.println(" DAO : 글 정보 수정완료!("+result+")");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}
	
	// DB에서 글 정보를 삭제하는 메서드 - start
	public int deleteBoard(BoardTestDTO dto) {
		int result = -1;
		
		try {
			conn = getConnectCP();
			
			sql = "select pass from itwill_board where bno=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getBno());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dto.getPass().equals(rs.getString("pass"))) {
					sql = "delete from itwill_board where bno=?";
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, dto.getBno());
					
					result = pstmt.executeUpdate();
				} else {
					System.out.println(" DAO : 게시글 삭제 실패! 비밀번호 오류 ("+result+")");
					result = 0;
				}
			} else {
				System.out.println(" DAO : 게시글 정보가 없습니다. ("+result+")");
				result = -1;
			}
			
			System.out.println(" DAO : 게시판 글 삭제 완료! ("+result+")");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}
	

}
