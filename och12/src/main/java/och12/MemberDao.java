package och12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.cj.exceptions.RSAException;

// singleton + DBCP -> Memory 절감 + DOS공격
public class MemberDao {
	private static MemberDao instance;
	Connection conn = null;

	private MemberDao() {

	}

	public static MemberDao getInstance() {
		if (instance == null) {
			instance = new MemberDao();
		}
		return instance;
	}

	private Connection getConnection() {

		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public int insert(Member2 member) throws SQLException {
		getConnection();
		String sql = "insert into member2 values(?,?,?,?,?,sysdate)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, member.getId());
		pstmt.setString(2, member.getPasswd());
		pstmt.setString(3, member.getName());
		pstmt.setString(4, member.getAddress());
		pstmt.setString(5, member.getTel());

		int result = pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		return result;

	}

	public int check(String id, String passwd) throws SQLException {
		getConnection();
		String sql = "select id, passwd from member2 where id=? And passwd=?";
		System.out.println("sql : " + sql);

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, passwd);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {

			return 1;

		} else
			return 0;

	}

	// DAO

	// 역할
	// 1. 회원명단 : DB Connection 후 쿼리 설정 후 결과 값을 List로 생성 (List<Member2> list()
	// Method)
	// 2. 회원가입 : DB Connection 후 체크 (check Method)
	// 3. 로그인 : DB Connection 후 저장 (insert Method)
	// 4. 회원수정 : DB Connection 후 조회한 후 수정 (select, update Method)
	// 5. 회원삭제 : DB Connection 후 삭제 (delete Method)

	public List<Member2> list() throws SQLException {
		List<Member2> list = new ArrayList<Member2>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select id,name,address,tel,reg_date from member2";
		try {
			conn = getConnection();
			// 연결
			stmt = conn.createStatement();
			// 쿼리를 넣는 객체
			rs = stmt.executeQuery(sql);
			// 결과
			while (rs.next()) {
				Member2 memb2 = new Member2();
				memb2.setId(rs.getString(1));
				memb2.setName(rs.getString(2));
				memb2.setAddress(rs.getString(3));
				memb2.setTel(rs.getString(4));
				memb2.setReg_date(rs.getDate(5));
				list.add(memb2);
			}
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
		return list;
	}
	public Member2 select(String id)throws SQLException {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Member2 memb2 = new Member2();
		
		try {
			conn = getConnection();
			// 연결
			stmt = conn.createStatement();
			// 쿼리를 넣는 객체
			String sql = "select id,name,address,tel,reg_date from member2";
			rs = stmt.executeQuery(sql);
			// 결과
			while (rs.next()) {
				memb2.setId(id);
				memb2.setName(rs.getString(2));
				memb2.setAddress(rs.getString(3));
				memb2.setTel(rs.getString(4));
				memb2.setReg_date(rs.getDate(5));
			}
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
		return memb2;
	}
	
	public int confirm(String id)throws SQLException{
		
		String sql ="select id from member2 where id="+"'"+id+"'";
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		System.out.println(sql);
		ResultSet rs = stmt.executeQuery(sql);			
		if(rs.next()) {
			conn.close();
			rs.close();
			stmt.close();
			return 1;
			
		}else {
			stmt.close();
			rs.close();
			conn.close();
			return 0;
			} 
		
		
	}
	public int delete(String id,String passwd) throws SQLException{
		getConnection();
		String sql ="delete from member2 where id=? and passwd=?";
		PreparedStatement pstmt = null;
		
		int result=0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			pstmt.close();
			conn.close();
		}
		
		return result;
	}
	public int update(Member2 memb2) throws SQLException{
		int result = 0;
		Connection conn = null;
		String sql = "update Member2"
					+" set passwd=?,"
					+	 	"name=?,"
					+ 	"address=?,"
					+ 		"tel=?,"
					+ " where id=? ";
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,memb2.getPasswd());			
					pstmt.setString(2,memb2.getName());			
					pstmt.setString(3,memb2.getAddress());			
					pstmt.setString(4,memb2.getTel());			
					pstmt.setString(5,memb2.getId());			
			result =pstmt.executeUpdate();
			if(result!=0)return 1;
			else return 0;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			conn.close();
			pstmt.close();
			
		}return result;
	}
}
