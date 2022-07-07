package com.dk.hpmw.pastparttimercontractdata;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.dk.hpmw.parttimercontract.ParttimerContractDAO;
import com.dk.hpmw.parttimercontract.ParttimerContractDTO;

public class PastParttimerContractDataDAO {
	public static final int ParttimerContractDeleteFAIL = 0;
	public static final int ParttimerContractDeleteSUCCESS = 1;
	public static final int ParttimerContractModifyFAIL = 0;
	public static final int ParttimerContractModifySUCCESS = 1;
	public static final int ParttimerContractInsetFAIL = 0;
	public static final int ParttimerContractInsetSUCCESS = 1;
	private static PastParttimerContractDataDAO instance = new PastParttimerContractDataDAO();

	public static PastParttimerContractDataDAO getInstance() {
		return instance;
	}

	private PastParttimerContractDataDAO() {
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
//	-- 지난 파트타이머 목록 보기 
	public ArrayList<PastParttimerContractDataDTO> listPastParttimerContractData(int startRow, int endRow) {
		ArrayList<PastParttimerContractDataDTO> pcdtoArr = new ArrayList<PastParttimerContractDataDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * " + 
				"    FROM (SELECT ROWNUM RN, A.* " + 
				"                 FROM (SELECT * " + 
				"                            FROM PASTPARTTIMERCONTRACTDATA " + 
				"                                 ORDER BY PCDATANO DESC, PTCONNO DESC) A) " + 
				"                            WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String pcdatano = rs.getString("pcdatano");
				String ptconno = rs.getString("ptconno") ;
				String ptid = rs.getString("ptid");
				String ptname = rs.getString("ptname");
				String pttel = rs.getString("pttel");
				String ptemail = rs.getString("ptemail");
				String ptaddress = rs.getString("ptaddress");
				int btno = rs.getInt("btno");
				String ptaccountno = rs.getString("ptaccountno");
				String evno = rs.getString("evno");
				int ptstatus = rs.getInt("ptstatus");
				int ptworktime = rs.getInt("ptworktime");
				int pthourlywage = rs.getInt("pthourlywage");
				int pttotalpay = rs.getInt("pttotalpay");
				String ptrdate = rs.getString("ptrdate");
				pcdtoArr.add(new PastParttimerContractDataDTO(pcdatano, ptconno, ptid, ptname, pttel, ptemail, ptaddress,
								btno, ptaccountno, evno, ptstatus, ptworktime, pthourlywage, pttotalpay, ptrdate));
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
		return pcdtoArr;
	}
	// 지난 파트타이머 데이터 입력
	public int insertPastParttimerContractData(ParttimerContractDTO pcdto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = ParttimerContractInsetFAIL;
		String sql = "INSERT INTO PASTPARTTIMERCONTRACTDATA(PCDATANO, PTCONNO, PTID, PTNAME, PTTEL, PTEMAIL, PTADDRESS, BTNO, "
				   + " PTACCOUNTNO, EVNO, PTSTATUS, PTWORKTIME, PTHOURLYWAGE, PTTOTALPAY, PTRDATE)\r\n" + 
				"    VALUES(PASTPARTTIMERCONTRACTDATA_SQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pcdto.getPtconno());
			pstmt.setString(2, pcdto.getPtid());
			pstmt.setString(3, pcdto.getPtname());
			pstmt.setString(4, pcdto.getPttel());
			pstmt.setString(5, pcdto.getPtemail());
			pstmt.setString(6, pcdto.getPtaddress());
			pstmt.setInt(7, pcdto.getBtno());
			pstmt.setString(8, pcdto.getPtaccountno());
			pstmt.setString(9, pcdto.getEvno());
			pstmt.setInt(10, pcdto.getPtstatus());
			pstmt.setInt(11, pcdto.getPtworktime());
			pstmt.setInt(12, pcdto.getPthourlywage());
			pstmt.setInt(13, pcdto.getPttotalpay());
			pstmt.setString(14, String.format("yy/mm/dd", pcdto.getPtrdate()));
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
	
	// 지난 파트타이머 데이터 글 갯수
	public int PastParttimerContractDataTotcnt() {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM PASTPARTTIMERCONTRACTDATA";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return cnt;
	}
}
