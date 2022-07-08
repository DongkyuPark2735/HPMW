package com.dk.hpmw.events;

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

public class EventsDAO {
	public static final int EventsDeleteFAIL = 0;
	public static final int EventsDeleteSUCCESS = 1;
	public static final int EventsModifyFAIL = 0;
	public static final int EventsModifySUCCESS = 1;
	public static final int EventsInsetFAIL = 0;
	public static final int EventsInsetSUCCESS = 1;
	private static EventsDAO instance = new EventsDAO();

	public static EventsDAO getInstance() {
		return instance;
	}

	private EventsDAO() {
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
	// Events 글갯수
	public int getEventsTotCnt() {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM EVENTS";
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
	
// 행사 목록 가져오기 : ArrayList<EventsDTO> listEvents()
	public ArrayList<EventsDTO> listEvents(int startRow, int endRow) {
		ArrayList<EventsDTO> evdtoArr = new ArrayList<EventsDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * " + 
				"    FROM (SELECT ROWNUM RN, A.* " + 
				"                 FROM (SELECT * FROM EVENTS  " + 
				"                               ORDER BY EVSTARTDATE, EVNO) A) " + 
				"                             WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String evno = rs.getString("evno");
				String evtitle = rs.getString("evtitle");;
				String evdetail = rs.getString("evdetail");;
				Date evstartdate = rs.getDate("evstartdate");
				int etno = rs.getInt("etno");
				int mno = rs.getInt("mno");
				evdtoArr.add(new EventsDTO(evno, evtitle, evdetail, evstartdate, etno, mno) );
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
		return evdtoArr;
	}
	
		
	
//행사 상세보기 DTO가져오기 : EventsDTO detailEvents(int evno)
	public EventsDTO detailEvents(String evno) {
		EventsDTO edto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT EVNO, EVTITLE, EVDETAIL, EVSTARTDATE, ET.ETNO, ET.ETNAME, MNO" + 
				"    FROM EVENTS E, EVENTSTYPE ET WHERE E.ETNO = ET.ETNO AND EVNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, evno);
			rs = pstmt.executeQuery();
			rs.next();
			String evtitle = rs.getString("evtitle");
			String evdetail = rs.getString("evdetail");
			Date evstartdate = rs.getDate("evstartdate");
			int etno = rs.getInt("etno");
			int mno = rs.getInt("mno");
			edto = new EventsDTO(evno, evtitle, evdetail, evstartdate, etno, mno);
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
		return edto;
	}

// 당일 행사 목록 가져오기 : ArrayList<EventsDTO> listDailyEvents()
	public ArrayList<EventsDTO> listDailyEvents() {
		ArrayList<EventsDTO> evdtoArr = new ArrayList<EventsDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM EVENTS WHERE EVSTARTDATE = TO_DATE(SYSDATE, 'YY/MM/DD')";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String evno = rs.getString("evno");
				String evtitle = rs.getString("evtitle");;
				String evdetail = rs.getString("evdetail");;
				Date evstartdate = rs.getDate("evstartdate");
				int etno = rs.getInt("etno");
				int mno = rs.getInt("mno");
				evdtoArr.add(new EventsDTO(evno, evtitle, evdetail, evstartdate, etno, mno) );
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
		return evdtoArr;
	}
	
//-- 행사 등록 : int insertEvents(EventsDTO edto)
	public int insertEvents(EventsDTO edto){
		int result = EventsInsetFAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO EVENTS(EVNO, EVTITLE, EVDETAIL, EVSTARTDATE, ETNO, MNO) " + 
				" VALUES(TO_CHAR(SYSDATE, 'RRMMDD')||TRIM(TO_CHAR(EVENTS_SEQ.NEXTVAL, '009')), ?, ?," + 
				" ?, ?, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, edto.getEvtitle());
			pstmt.setString(2, edto.getEvdetail());
			pstmt.setDate(3, edto.getEvstartdate());
			pstmt.setInt(4, edto.getEtno());
			pstmt.setInt(5, edto.getMno());
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
	
//	행사 수정 : int modifyEvents(EventsDTO edto)
	public int modifyEvents(EventsDTO edto){
		int result = EventsModifyFAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE EVENTS SET EVTITLE = ?," + 
				" EVDETAIL = ?," + 
				" EVSTARTDATE = ?," + 
				" ETNO = ?," + 
				" MNO = ?" + 
				" WHERE EVNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, edto.getEvtitle());
			pstmt.setString(2, edto.getEvdetail());
			pstmt.setDate(3, edto.getEvstartdate());
			pstmt.setInt(4, edto.getEtno());
			pstmt.setInt(5, edto.getMno());
			pstmt.setString(6, edto.getEvno());
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
	
//	-- o행사 삭제 : int deleteEvents(String evno, int mno)
	public int deleteEvents(String evno, int mno){
		int result = EventsDeleteFAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM EVENTS WHERE EVNO = ? AND MNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, evno);
			pstmt.setInt(2, mno);
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











