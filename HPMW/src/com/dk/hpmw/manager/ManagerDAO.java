package com.dk.hpmw.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ManagerDAO {
	public static final int ManagerDeleteFAIL = 0;
	public static final int ManagerDeleteSUCCESS = 1;
	public static final int ManagerModifyFAIL = 0;
	public static final int ManagerModifySUCCESS = 1;
	public static final int ManagerInsetFAIL = 0;
	public static final int ManagerInsetSUCCESS = 1;
	public static final int ManagerNONEXISTENT = 0;
	public static final int ManagerEXISTENT = 1;
	private static ManagerDAO instance = new ManagerDAO();

	public static ManagerDAO getInstance() {
		return instance;
	}

	private ManagerDAO() {
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
//	-- 관리자 로그인 : ManagerDTO loginManager(int mno, String mpw)
	public ManagerDTO loginManager(int mno, String mpw){
		ManagerDTO mdto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MANAGER WHERE MNO = ? AND MPW = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			pstmt.setString(2, mpw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String mname = rs.getString("mname");
				int allevel = rs.getInt("allevel");
				mdto = new ManagerDTO(mno, mpw, mname, allevel);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return mdto;
	}
	
	
//	-- 매니저 관리자 생성 : int inSertManager(int mno, String mpw, String mname)  
	public int inSertManager(int mno, String mpw, String mname){
		int result = ManagerInsetFAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO MANAGER(MNO, MPW, MNAME, ALLEVEL) VALUES( ?, ?, ?, 1)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			pstmt.setString(2, mpw);
			pstmt.setString(3, mname);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
	
//	-- 매니저 관리자 삭제 : int deleteManager(int mno)
	// 매니저 관리자가 작성한 글 처리 로직 필요
	// 매니저 관리자의 글이 있어서 삭제 불가합니다 안내??
	public int deleteManager(int mno){
		int result = ManagerDeleteFAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM MANAGER WHERE MNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
//	-- 매니저 관리자 목록 : ArrayList<ManagerDTO> listManager()
	public ArrayList<ManagerDTO> listManager() {
		ArrayList<ManagerDTO> mgdtoArr = new ArrayList<ManagerDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MANAGER WHERE ALLEVEL != 2";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int mno = rs.getInt("mno");
				String mname = rs.getString("mname");
				String mpw = rs.getString("mpw");
				int allevel = rs.getInt("allevel");
				mgdtoArr.add(new ManagerDTO(mno, mpw, mname, allevel) );
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return mgdtoArr;
	}
	
//	-- 매니저 관리자 아이디 중복체크 : int managerIdConfirm(int mno) {
	public int managerIdConfirm(int mno) {
		int result = ManagerNONEXISTENT;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MANAGER WHERE MNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = ManagerEXISTENT;
			}else {
				result = ManagerNONEXISTENT;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
}



















