package com.dk.hpmw.parttimercontract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ParttimerContractDAO {
	public static final int ParttimerContractDeleteFAIL = 0;
	public static final int ParttimerContractDeleteSUCCESS = 1;
	public static final int ParttimerContractModifyFAIL = 0;
	public static final int ParttimerContractModifySUCCESS = 1;
	public static final int ParttimerContractInsetFAIL = 0;
	public static final int ParttimerContractInsetSUCCESS = 1;
	private static ParttimerContractDAO instance = new ParttimerContractDAO();

	public static ParttimerContractDAO getInstance() {
		return instance;
	}

	private ParttimerContractDAO() {
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
	
// -- 파트타이머 근로계약서 총 갯수 : int 	parttimerContractTotcnt
	public int parttimerContractTotcnt() {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM PARTTIMERCONTRACT";
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
	
	
	
	
	
//	-- 파트타이머 근로계약서 목록 보기 : ArrayList<ParttimerContractDTO> listParttimerContract(int startRow, int endRow)
//	-- 파트타이머 목록 보기 :  ArrayList<ParttimerContractDTO> listParttimerContract(int startRow, int endRow)
	public ArrayList<ParttimerContractDTO> listParttimerContract(int startRow, int endRow) {
		ArrayList<ParttimerContractDTO> pcdtoArr = new ArrayList<ParttimerContractDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * " + 
				"    FROM (SELECT ROWNUM RN, A.* " + 
				"                 FROM (SELECT PC.*, P.PTEMPCONCHEK " + 
				"                            FROM PARTTIMER P ,PARTTIMERCONTRACT PC " + 
				"                                WHERE P.PTID = PC.PTID  ORDER BY PTCONNO DESC) A) " + 
				"                            WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
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
				pcdtoArr.add(new ParttimerContractDTO(ptconno, ptid, ptname, pttel, ptemail, ptaddress, btno, ptaccountno, evno, ptstatus, ptworktime, pthourlywage, pttotalpay));
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
	
//	-- 파트타이머 & 파트타이머 근로계약서 상세보기 : ParttimerContractDTO detailParttimerContract(String ptconno)
	public ParttimerContractDTO detailParttimerContract(String ptconno) {
		ParttimerContractDTO pcdto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT PC.*, P.PTEMPCONCHEK " + 
				"    FROM PARTTIMER P ,PARTTIMERCONTRACT PC " + 
				"    WHERE P.PTID = PC.PTID AND PTCONNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ptconno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
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
				pcdto = new ParttimerContractDTO(ptconno, ptid, ptname, pttel, ptemail, ptaddress, btno, ptaccountno, evno, ptstatus, ptworktime, pthourlywage, pttotalpay);
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
		return pcdto;
	}
	
// -- 파트타이머 근로계약서 작성 : int insertParttimerContract(ParttimerContractDTO pcdto)
	public int insertParttimerContract(ParttimerContractDTO pcdto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = ParttimerContractInsetFAIL;
		String sql = "INSERT INTO PARTTIMERCONTRACT(PTCONNO, PTID, PTNAME, PTTEL, PTEMAIL, PTADDRESS, BTNO, PTACCOUNTNO)" + 
				   		" VALUES(TRIM(TO_CHAR(PTCONT_SEQ.NEXTVAL, '00000009')), ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pcdto.getPtid());
			pstmt.setString(2, pcdto.getPtname());
			pstmt.setString(3, pcdto.getPttel());
			pstmt.setString(4, pcdto.getPtemail());
			pstmt.setString(5, pcdto.getPtaddress());
			pstmt.setInt(6, pcdto.getBtno());
			pstmt.setString(7, pcdto.getPtaccountno());
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
	
// -- 파트타이머 근로계약서 수정 : int modifyParttimerContract(ParttimerContractDTO pcdto)
	public int modifyParttimerContract(ParttimerContractDTO pcdto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = ParttimerContractModifyFAIL;
		String sql = "UPDATE PARTTIMERCONTRACT SET PTNAME = ?," + 
												" PTTEL = ?, " + 
												" PTEMAIL = ?," + 
												" PTADDRESS = ?," + 
												" BTNO = ?," + 
												" PTACCOUNTNO = ?" + 
											" WHERE PTCONNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pcdto.getPtname());
			pstmt.setString(2, pcdto.getPttel());
			pstmt.setString(3, pcdto.getPtemail());
			pstmt.setString(4, pcdto.getPtaddress());
			pstmt.setInt(5, pcdto.getBtno());
			pstmt.setString(6, pcdto.getPtaccountno());
			pstmt.setString(7, pcdto.getPtconno());
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
	
//	-- 파트타이머 근로계약서 삭제 : int deleteParttimerContract(String ptconno)
	public int deleteParttimerContract(String ptconno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = ParttimerContractDeleteFAIL;
		String sql = "DELETE FROM PARTTIMERCONTRACT WHERE PTCONNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ptconno);
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
//	-- 파트타이머 근로계약서 행사정보 입력 : int insertEventsParttimer(String evno, String ptconno)
	public int insertEventsParttimer(String evno, String ptconno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = ParttimerContractInsetFAIL;
		String sql = "UPDATE PARTTIMERCONTRACT SET EVNO = ? WHERE PTCONNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, evno);
			pstmt.setString(2, ptconno);
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
//	-- 파트타이머 근로계약서 시급, 마감 처리 : int insertParttimerHourlyWage(int ptworktime, int pthourlywage, String ptconno)
	public int insertParttimerHourlyWage(int ptworktime, int pthourlywage, int pttotalpay, String ptconno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = ParttimerContractInsetFAIL;
		String sql = "UPDATE PARTTIMERCONTRACT SET PTWORKTIME = ?," + 
												 " PTHOURLYWAGE = ?, " + 
												 " PTTOTALPAY = ? " + 
										" WHERE PTCONNO = ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ptworktime);
			pstmt.setInt(2, pthourlywage);
			pstmt.setInt(3, pttotalpay);
			pstmt.setString(4, ptconno);
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

//	-- 파트타이머 근로계약서 마감 : int intsertParttimerPtstatus(String ptconno)
	public int intsertParttimerPtstatus(String ptconno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = ParttimerContractInsetFAIL;
		String sql = "UPDATE PARTTIMERCONTRACT SET PTSTATUS = 1 " + 
				"                        WHERE PTCONNO = ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ptconno);
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
