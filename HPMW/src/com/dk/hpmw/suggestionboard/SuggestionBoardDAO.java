package com.dk.hpmw.suggestionboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SuggestionBoardDAO {
	public static final int SuggestionBoardDeleteFAIL = 0;
	public static final int SuggestionBoardDeleteSUCCESS = 1;
	public static final int SuggestionBoardModifyFAIL = 0;
	public static final int SuggestionBoardModifySUCCESS = 1;
	public static final int SuggestionBoardInsetFAIL = 0;
	public static final int SuggestionBoardInsetSUCCESS = 1;
	private static SuggestionBoardDAO instance = new SuggestionBoardDAO();

	public static SuggestionBoardDAO getInstance() {
		return instance;
	}

	private SuggestionBoardDAO() {
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

//	-- 건의사항 게시판 글 목록 : ArrayList<SuggestionBoardDTO> listSuggestionBoard(int startRow, int endRow)
	public ArrayList<SuggestionBoardDTO> listSuggestionBoard(int startRow, int endRow) {
		ArrayList<SuggestionBoardDTO> sbdtoArr = new ArrayList<SuggestionBoardDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * " + " FROM (SELECT ROWNUM RN, A.*" + " FROM (SELECT * " + " FROM SUGGESTIONBOARD"
				+ " ORDER BY SBGROUP DESC, SBSTEP) A)" + " WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int sbno = rs.getInt("sbno");
				String ptid = rs.getString("ptid");
				int mno = rs.getInt("mno");
				String sbtitle = rs.getString("sbtitle");
				String sbcontent = rs.getString("sbcontent");
				String sbfilename = rs.getString("sbfilename");
				String sbpw = rs.getString("sbpw");
				int sbstep = rs.getInt("sbstep");
				int sbindent = rs.getInt("sbindent");
				int sbgroup = rs.getInt("sbgroup");
				int sbhit = rs.getInt("sbhit");
				String sbip = rs.getString("sbip");
				Timestamp sbrdate = rs.getTimestamp("sbrdate");
				sbdtoArr.add(new SuggestionBoardDTO(sbno, ptid, mno, sbtitle, sbcontent, sbfilename, sbpw, sbstep,
						sbindent, sbgroup, sbhit, sbip, sbrdate));
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
		return sbdtoArr;
	}

//	-- 글 상세보기전 조회수 올리기
	private void SuggestionBoardhitCountUp(int sbno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE SUGGESTIONBOARD  SET SBHIT = SBHIT +1 WHERE SBNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sbno);
			pstmt.executeUpdate();
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
	}

	// SuggestionBoard 글갯수
	public int getSuggestionBoardTotCnt() {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM SUGGESTIONBOARD";
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

//	-- 건의게시판 글 상세보기 dto가져오기 : SuggestionBoardDTO detailSuggestionBoard(int sbno) 
	public SuggestionBoardDTO detailSuggestionBoard(int sbno) {
		SuggestionBoardhitCountUp(sbno);
		SuggestionBoardDTO sbdto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM SUGGESTIONBOARD WHERE SBNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sbno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String ptid = rs.getString("ptid");
				int mno = rs.getInt("mno");
				String sbtitle = rs.getString("sbtitle");
				String sbcontent = rs.getString("sbcontent");
				String sbfilename = rs.getString("sbfilename");
				String sbpw = rs.getString("sbpw");
				int sbstep = rs.getInt("sbstep");
				int sbindent = rs.getInt("sbindent");
				int sbgroup = rs.getInt("sbgroup");
				int sbhit = rs.getInt("sbhit");
				String sbip = rs.getString("sbip");
				Timestamp sbrdate = rs.getTimestamp("sbrdate");
				sbdto = new SuggestionBoardDTO(sbno, ptid, mno, sbtitle, sbcontent, sbfilename, sbpw, sbstep, sbindent,
						sbgroup, sbhit, sbip, sbrdate);
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
		return sbdto;
	}

//	-- 건의게시판 글 상세보기 dto가져오기 (reply용 조회수 up x : SuggestionBoardDTO forReplydetailSuggestionBoard(int sbno) 
	public SuggestionBoardDTO forReplydetailSuggestionBoard(int sbno) {
		SuggestionBoardDTO sbdto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM SUGGESTIONBOARD WHERE SBNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sbno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String ptid = rs.getString("ptid");
				int mno = rs.getInt("mno");
				String sbtitle = rs.getString("sbtitle");
				String sbcontent = rs.getString("sbcontent");
				String sbfilename = rs.getString("sbfilename");
				String sbpw = rs.getString("sbpw");
				int sbstep = rs.getInt("sbstep");
				int sbindent = rs.getInt("sbindent");
				int sbgroup = rs.getInt("sbgroup");
				int sbhit = rs.getInt("sbhit");
				String sbip = rs.getString("sbip");
				Timestamp sbrdate = rs.getTimestamp("sbrdate");
				sbdto = new SuggestionBoardDTO(sbno, ptid, mno, sbtitle, sbcontent, sbfilename, sbpw, sbstep, sbindent,
						sbgroup, sbhit, sbip, sbrdate);
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
		return sbdto;
	}

//	-- 건의사항게시판 글쓰기 : int insertSuggestionBoard(SuggestionBoardDTO sbdto)
	public int insertSuggestionBoard(SuggestionBoardDTO sbdto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = SuggestionBoardInsetFAIL;
		String sql = "INSERT INTO SUGGESTIONBOARD(SBNO, PTID, SBTITLE, SBCONTENT, SBFILENAME, SBPW, SBSTEP, SBINDENT, SBGROUP, SBIP) "
				+ " VALUES(SUGGBOARD_SEQ.NEXTVAL, ?, ?, ?, ?, ?, 0, 0, SUGGBOARD_SEQ.CURRVAL, ? )";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sbdto.getPtid());
			pstmt.setString(2, sbdto.getSbtitle());
			pstmt.setString(3, sbdto.getSbcontent());
			pstmt.setString(4, sbdto.getSbfilename());
			pstmt.setString(5, sbdto.getSbpw());
			pstmt.setString(6, sbdto.getSbip());
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

//	-- 건의 게시판 글 삭제 : int deleteSuggestionBoard(int sbno, int sbpw)
	public int deleteSuggestionBoard(int sbno, int sbpw) {
		int result = SuggestionBoardDeleteFAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM SUGGESTIONBOARD WHERE SBNO = ? AND SBPW = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sbno);
			pstmt.setInt(1, sbpw);
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

//	-- 건의사항 게시판 답글 쓰기
//	-- 답글쓰기 전 : void preReplyStep(int sbgroup, int sbstep) 
	private void preReplyStep(int sbgroup, int sbstep) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE SUGGESTIONBOARD SET SBSTEP = SBSTEP+1" + 
					"    WHERE SBGROUP = ? AND SBSTEP > ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sbgroup);
			pstmt.setInt(2, sbstep);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

//	-- 답글쓰기 : int insetReplySuggestionBoard(SuggestionBoardDTO sbdto)
	public int insetReplySuggestionBoard(SuggestionBoardDTO sbdto) {
		preReplyStep(sbdto.getSbgroup(), sbdto.getSbstep());
		int result = SuggestionBoardInsetFAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO SUGGESTIONBOARD(SBNO, PTID, MNO, SBTITLE, SBCONTENT, SBFILENAME, "
				+ "SBPW, SBSTEP, SBINDENT, SBGROUP, SBIP)"
				+ "    VALUES(SUGGBOARD_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sbdto.getPtid());
			pstmt.setInt(2, sbdto.getMno());
			pstmt.setString(3, sbdto.getSbtitle());
			pstmt.setString(4, sbdto.getSbcontent());
			pstmt.setString(5, sbdto.getSbfilename());
			pstmt.setString(6, sbdto.getSbpw());
			pstmt.setInt(7, sbdto.getSbstep() + 1);
			pstmt.setInt(8, sbdto.getSbindent() + 1);
			pstmt.setInt(9, sbdto.getSbgroup());
			pstmt.setString(10, sbdto.getSbip());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

//	-- 건의사항게시판 글수정 : int modifySuggestionBoard(SuggestionBoardDTO sbdto)
	public int modifySuggestionBoard(SuggestionBoardDTO sbdto) {
		int result = SuggestionBoardModifyFAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE SUGGESTIONBOARD SET SBTITLE = ?, "
											+ " SBCONTENT = ?, "
											+ " SBFILENAME = ?, "
											+ " SBIP = ? "
										+ " WHERE SBNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sbdto.getSbtitle());
			pstmt.setString(2, sbdto.getSbcontent());
			pstmt.setString(3, sbdto.getSbfilename());
			pstmt.setString(4, sbdto.getSbip());
			pstmt.setInt(5, sbdto.getSbno());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
}
