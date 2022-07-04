package com.dk.hpmw.parttimer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ParttimerDAO {
	public static final int ParttimerLoginFAIL = 0;
	public static final int ParttimerLoginSUCCESS = 1;
	private static ParttimerDAO instance = new ParttimerDAO();

	public static ParttimerDAO getInstance() {
		return instance;
	}

	private ParttimerDAO() {
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

//	-- 파트타이머 로그인 : ParttimerDTO loginParttimer(int ptid, int ptpw) 
	public ParttimerDTO loginParttimer(String ptid, String ptpw){
		ParttimerDTO pdto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM PARTTIMER WHERE PTID = ? AND PTPW = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ptid);
			pstmt.setString(2, ptpw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
			  int ptempconchek = rs.getInt("ptempconchek");
			  pdto = new ParttimerDTO(ptid, ptpw, ptempconchek);
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
		return pdto;
	}
	
//	-- 파트타이머 아이디 비밀번호 찾기 : ParttimerDTO SearchPtidPtpw(int ptid, String ptname)
	public ParttimerDTO searchPtidPtpw(String ptid, String ptname){
		ParttimerDTO pdto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT P.PTID, PTNAME, PTPW, PTEMPCONCHEK FROM PARTTIMER P, PARTTIMERCONTRACT PC" + 
				     " WHERE P.PTID = PC.PTID AND P.PTID = ? AND PTNAME = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ptid);
			pstmt.setString(2, ptname);
			rs = pstmt.executeQuery();
			if(rs.next()) {
			  int ptempconchek = rs.getInt("ptempconchek");
			  String ptpw = rs.getString("ptpw");
			  pdto = new ParttimerDTO(ptid, ptpw, ptempconchek);
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
		return pdto;
	}
//	-- 파트타이머 근로계약서 작성후 ptempconchek 수정
	public int insertParttimerPtempconchek(String ptid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = ParttimerLoginFAIL;
		String sql = "UPDATE PARTTIMER SET PTEMPCONCHEK = 1 WHERE PTID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ptid);
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
//	-- 파트타이머 근로계약서 작성후 ptempconchek 0으로 수정
	public int deleteParttimerPtempconchek(String ptid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = ParttimerLoginFAIL;
		String sql = "UPDATE PARTTIMER SET PTEMPCONCHEK = 0 WHERE PTID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ptid);
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
}



























