package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.security.auth.Subject;
import javax.sql.DataSource;

public class BoardDao {
	private static BoardDao instance;

	private BoardDao() {

	}

	public static BoardDao getInstance() {
		if (instance == null) {
			instance = new BoardDao();
		}
		return instance;
	}

	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public int getTotalCnt() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int tot = 0;
		String sql = "select count(*) from board";
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next())
				tot = rs.getInt(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return tot;
	}

	public List<Board> boardList(int startRow, int endRow) throws SQLException {
		List<Board> list = new ArrayList<Board>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select *FROM"
					+ "(select rownum rn ,a.* from "
					+ "(SELECT * FROM board Order by ref desc,re_step) a)"
					+ "where rn between ? And ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					Board board =new Board();
					board.setNum(rs.getInt("num"));
					board.setSubject(rs.getString("Subject"));
					board.setWriter(rs.getString("Writer"));
					board.setEmail(rs.getString("Email"));
					board.setIp(rs.getString("Ip"));
					board.setRef(rs.getInt("ref"));
					board.setRe_level(rs.getInt("Re_level"));
					board.setRe_step(rs.getInt("Re_step"));
					board.setReg_date(rs.getDate("Reg_date"));
					board.setReadcount(rs.getInt("Readcount"));
					list.add(board);
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) rs.close();
			if(pstmt != null)pstmt.close();
			if(conn != null)conn.close();
		}
		return list;
	}

	public Board select(int num) throws SQLException {
		Board board = new Board();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select*from board where num=?";
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			System.out.println(sql);
			if(rs.next()) {
				do {
					board.setNum(rs.getInt("NUM"));
					board.setSubject(rs.getString("SUBJECT"));
					board.setWriter(rs.getString("WRITER"));
					board.setEmail(rs.getString("EMAIL"));
					board.setIp(rs.getString("IP"));
					board.setRef(rs.getInt("REF"));
					board.setRe_level(rs.getInt("RE_LEVEL"));
					board.setRe_step(rs.getInt("RE_STEP"));
					board.setReg_date(rs.getDate("REG_DATE"));
					board.setReadcount(rs.getInt("READCOUNT"));
					board.setContent(rs.getString("CONTENT"));
					
				}while(rs.next());
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		
		return board;
	}

	public void readCount(int num) throws SQLException {
		
		Connection conn = null;
		String sql ="select READCOUNT from board where num=?";
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
				
		try {
			conn = getConnection();	
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int cnt = rs.getInt(1);
				String sql1 ="update board set READCOUNT=? where num=?";
				pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setInt(1, cnt+1);
				pstmt1.setInt(2, num);
				
				int result = pstmt1.executeUpdate();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			pstmt.close();
			pstmt1.close();
			conn.close();
		}
		
		
	}

	public int update(Board board) throws SQLException {
		Connection conn = null;
		String sql = "update board set writer=?,email=?,"
					+"subject=?,passwd=?,content=? where num=?";
		PreparedStatement pstmt =null;
		int result = 0;
		try {
			conn = getConnection();
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, board.getWriter());
			pstmt.setString(2, board.getEmail());
			pstmt.setString(3, board.getSubject());
			pstmt.setString(4, board.getPasswd());
			pstmt.setString(5, board.getContent());
			pstmt.setInt(6, board.getNum());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null)pstmt.close();
			if(conn != null)conn.close();
		}
		return result;
	}

	public int insert(Board board) throws SQLException {
		int num = board.getNum();
		Connection conn =null;
		PreparedStatement pstmt =null;
		int result =0;
		ResultSet rs = null;
		String sql1 = "select nvl(max(num),0)from board";
		String sql = "insert into board values(?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		//홍해의 기적
		String sql2 ="update board set re_step=re_step+1 where ref=? and re_step>?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			result = pstmt.executeUpdate();
			rs=pstmt.executeQuery();
			rs.next();
			// key 인 num 1씩 증가 ,mysql auto_increament 또는 oracle sequence
			// sequence 를 사용 : values(시퀀스명(board_seq).nextval,?,?...)
			int number = rs.getInt(1)+1;
			rs.close();
			pstmt.close();
			
			// 댓글 -->sql2
			if(num!=0) {
				System.out.println("BoardDAO insert 댓글 sql2->"+sql2);
				System.out.println("BoardDAO insert 댓글 board.getRef()->"+board.getRef());
				System.out.println("BoardDAO insert 댓글 board.getRe_step()"+board.getRe_step());
				pstmt =conn.prepareStatement(sql2);
				pstmt.setInt(1, board.getRef());
				pstmt.setInt(2, board.getRe_step());
				pstmt.executeUpdate();
				pstmt.close();
				
				// 댓글 관련 정보
				board.setRe_step(board.getRe_step()+1);
				board.setRe_level(board.getRe_level()+1);
			}
			if(num == 0)board.setRef(number); // 신규글일 때 num과 Ref 맞춰줌
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getSubject());
			pstmt.setString(4, board.getContent());
			pstmt.setString(5, board.getEmail());
			pstmt.setInt(6, board.getReadcount());
			pstmt.setString(7, board.getPasswd());
			pstmt.setInt(8, board.getRef());
			pstmt.setInt(9, board.getRe_step());
			pstmt.setInt(10, board.getRe_level());
			pstmt.setString(11, board.getIp());
			
			result=pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally {
			if(pstmt != null)pstmt.close();
			if(conn != null)conn.close();
			if(rs != null)rs.close();
		}
		
		return result;
	}

	public int delete(int num, String passwd) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt =null;
		int result = 0;
		String sql = "delete from board where num=? and passwd=?";
		
		try {
			conn = getConnection();
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, passwd);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally {
			if(pstmt!=null)pstmt.close();
			if(conn!=null)conn.close();
			
		}
		
		return result;
	}

	

	

	
	


		
}