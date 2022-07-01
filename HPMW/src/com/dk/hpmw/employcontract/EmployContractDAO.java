package com.dk.hpmw.employcontract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmployContractDAO {
	public static final int EmployContractDeleteFAIL = 0;
	public static final int EmployContractDeleteSUCCESS = 1;
	public static final int EmployContractModifyFAIL = 0;
	public static final int EmployContractModifySUCCESS = 1;
	public static final int EmployContractInsetFAIL = 0;
	public static final int EmployContractInsetSUCCESS = 1;
	private static EmployContractDAO instance = new EmployContractDAO();

	public static EmployContractDAO getInstance() {
		return instance;
	}

	private EmployContractDAO() {
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
	
	// 근로계약서 작성 : int insertEmployContract(EmployContractDTO ecdto)
	public int insertEmployContract(EmployContractDTO ecdto) {
		int result = EmployContractInsetFAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO EMPLOYCONTRACT(ECNO, ECTITLE, ECCONTENT) " + 
				"    VALUES(TO_CHAR(SYSDATE, 'YYMMDD')||TRIM(TO_CHAR(EMPLOYCONTRACT_SEQ.NEXTVAL, '0009')), ?, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ecdto.getEctitle());
			pstmt.setString(2, ecdto.getEccontent());
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
		return result ;
	}
// 근로계약서 목록 : ArrayList<EmployContractDTO> listEmployContract()
	public ArrayList<EmployContractDTO> listEmployContract() {
		ArrayList<EmployContractDTO> ecdtoArr = new ArrayList<EmployContractDTO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM EMPLOYCONTRACT";
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String ecno = rs.getString("econ");
				String ectitle = rs.getString("ectitle");
				String eccontent = rs.getString("eccontent");
				ecdtoArr.add(new EmployContractDTO(ecno, ectitle, eccontent));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return ecdtoArr;
	}
	
	
	// 근로계약서 상세보기용 DTO 가져오기: EmployContractDTO detailEmployContract(String ecno)
	// 매니저 관리자중 부서 관리자 등급일떄 근로계약서 수정
	public EmployContractDTO detailEmployContract() {
		EmployContractDTO ecdto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT ECNO, ECTITLE, ECCONTENT FROM EMPLOYCONTRACT";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String ecno= rs.getString("ecno");
				String ectitle = rs.getString("ectitle");
				String eccontent = rs.getString("eccontent");
				ecdto = new EmployContractDTO(ecno, ectitle, eccontent);
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
		return ecdto;
	}

// 근로계약서 수정 : int modifyEmployContract(EmployContractDTO ecdto )
	public int modifyEmployContract(EmployContractDTO ecdto) {
		int result = EmployContractModifyFAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE EMPLOYCONTRACT SET ECTITLE = ?," + 
											  " ECCONTENT = ?" + 
				                              " WHERE ECNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ecdto.getEctitle());
			pstmt.setString(2, ecdto.getEccontent());
			pstmt.setString(3, ecdto.getEcno());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return result ;
	}
	
//	근로계약서 삭제 : int deleteEmployContract(String ecno)
	public int deleteEmployContract(String ecno) {
		int result = EmployContractDeleteFAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM EMPLOYCONTRACT WHERE ECNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ecno);
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
		return result ;
	}
	
	
}
