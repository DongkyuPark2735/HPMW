package com.dk.hpmw.noticeboard;

import java.sql.Timestamp;

public class NoticeBoardDTO {
	private int nbno;
	private int mno;
	private String nbtitle;
	private String nbcontent;
	private String nbfilename;
	private int nbhit;
	private Timestamp nbrdate;
	public NoticeBoardDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NoticeBoardDTO(int nbno, int mno, String nbtitle, String nbcontent, String nbfilename, int nbhit,
			Timestamp nbrdate) {
		super();
		this.nbno = nbno;
		this.mno = mno;
		this.nbtitle = nbtitle;
		this.nbcontent = nbcontent;
		this.nbfilename = nbfilename;
		this.nbhit = nbhit;
		this.nbrdate = nbrdate;
	}
	@Override
	public String toString() {
		return "NoticeBoardDTO [nbno=" + nbno + ", mno=" + mno + ", nbtitle=" + nbtitle + ", nbcontent=" + nbcontent
				+ ", nbfilename=" + nbfilename + ", nbhit=" + nbhit + ", nbrdate=" + nbrdate + "]";
	}
	public int getNbno() {
		return nbno;
	}
	public void setNbno(int nbno) {
		this.nbno = nbno;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getNbtitle() {
		return nbtitle;
	}
	public void setNbtitle(String nbtitle) {
		this.nbtitle = nbtitle;
	}
	public String getNbcontent() {
		return nbcontent;
	}
	public void setNbcontent(String nbcontent) {
		this.nbcontent = nbcontent;
	}
	public String getNbfilename() {
		return nbfilename;
	}
	public void setNbfilename(String nbfilename) {
		this.nbfilename = nbfilename;
	}
	public int getNbhit() {
		return nbhit;
	}
	public void setNbhit(int nbhit) {
		this.nbhit = nbhit;
	}
	public Timestamp getNbrdate() {
		return nbrdate;
	}
	public void setNbrdate(Timestamp nbrdate) {
		this.nbrdate = nbrdate;
	}
	
	
}
