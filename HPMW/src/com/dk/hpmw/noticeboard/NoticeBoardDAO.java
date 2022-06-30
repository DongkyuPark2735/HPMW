package com.dk.hpmw.noticeboard;

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

public class NoticeBoardDAO {
	public static final int NoticeBoardDeleteFAIL = 0;
	public static final int NoticeBoardDeleteSUCCESS = 1;
	public static final int NoticeBoardModifyFAIL = 0;
	public static final int NoticeBoardModifySUCCESS = 1;
	public static final int NoticeBoardInsetFAIL = 0;
	public static final int NoticeBoardInsetSUCCESS = 1;
	private static NoticeBoardDAO instance = new NoticeBoardDAO();

	public static NoticeBoardDAO getInstance() {
		return instance;
	}

	private NoticeBoardDAO() {
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

//	-- 공지사항 글 목록 : ArrayList<NoticeBoardDTO> listNoticeBoard(int startRow, int endRow)
	public ArrayList<NoticeBoardDTO> listNoticeBoard(int startRow, int endRow) {
		ArrayList<NoticeBoardDTO> nbdtoArr = new ArrayList<NoticeBoardDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * " + " FROM (SELECT  ROWNUM RN, A.* "
				+ " FROM (SELECT * FROM NOTICEBOARD ORDER BY NBRDATE DESC) A) " + " WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int nbno = rs.getInt("nbno");
				int mno = rs.getInt("mno");
				String nbtitle = rs.getString("nbtitle");
				String nbcontent = rs.getString("nbcontent");
				String nbfilename = rs.getString("nbfilename");
				int nbhit = rs.getInt("nbhit");
				Timestamp nbrdate = rs.getTimestamp("nbrdate");
				nbdtoArr.add(new NoticeBoardDTO(nbno, mno, nbtitle, nbcontent, nbfilename, nbhit, nbrdate));
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
		return nbdtoArr;
	}

	// noticeBoard 글갯수
	public int getnoticeBoardTotCnt() {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM NOTICEBOARD";
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

// 글 상세보기 전 조회수 올리기 : 	void noticeBoardhitCountUp(int nbno)
	private void noticeBoardhitCountUp(int nbno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE NOTICEBOARD SET NBHIT = NBHIT + 1 WHERE NBNO = ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nbno);
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

//	-- 공지사항글 상세보기용 DTO가져오기 : NoticeBoardDTO detailNoticeBoard(int nbno)
	public NoticeBoardDTO detailNoticeBoard(int nbno) {
		noticeBoardhitCountUp(nbno);
		NoticeBoardDTO nbdto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT NBNO, MNO, NBTITLE, NBCONTENT, NBFILENAME, NBHIT, NBRDATE "
				+ "FROM NOTICEBOARD WHERE NBNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nbno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int mno = rs.getInt("mno");
				String nbtitle = rs.getString("nbtitle");
				String nbcontent = rs.getString("nbcontent");
				String nbfilename = rs.getString("nbfilename");
				int nbhit = rs.getInt("nbhit");
				Timestamp nbrdate = rs.getTimestamp("nbrdate");
				nbdto = new NoticeBoardDTO(nbno, mno, nbtitle, nbcontent, nbfilename, nbhit, nbrdate);
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
		return nbdto;
	}

//	-- 공지사항 글 등록 : int insertNoticeBoard(NoticeBoardDTO nbdto)
	public int insertNoticeBoard(NoticeBoardDTO nbdto) {
		int result = NoticeBoardInsetFAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO NOTICEBOARD(NBNO, MNO, NBTITLE, NBCONTENT, NBFILENAME) "
				+ " VALUES(NOTICEBOARD_SEQ.NEXTVAL, ?, ?, ?, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nbdto.getMno());
			pstmt.setString(2, nbdto.getNbtitle());
			pstmt.setString(3, nbdto.getNbcontent());
			pstmt.setString(4, nbdto.getNbfilename());
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

//	-- 공지사항 글 수정 : int modifyNoticeBoard(NoticeBoardDTO nbdto)
	public int modifyNoticeBoard(NoticeBoardDTO nbdto) {
		int result = NoticeBoardModifyFAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE NOTICEBOARD SET MNO = ?," + " NBTITLE = ?," + " NBCONTENT = ?," + " NBFILENAME = ?"
				+ " WHERE NBNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nbdto.getMno());
			pstmt.setString(2, nbdto.getNbtitle());
			pstmt.setString(3, nbdto.getNbcontent());
			pstmt.setString(4, nbdto.getNbfilename());
			pstmt.setInt(5, nbdto.getNbno());
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

	// -- 공지사항 글 삭제 : int deleteNoticeBoard(String mno, int nbno)
	public int deleteNoticeBoard(int mno, int nbno) {
		int result = NoticeBoardDeleteFAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM NOTICEBOARD WHERE MNO = ? AND NBNO = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mno);
			pstmt.setInt(2, nbno);
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
