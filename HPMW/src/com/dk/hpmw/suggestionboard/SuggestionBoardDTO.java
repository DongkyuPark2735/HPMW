package com.dk.hpmw.suggestionboard;

import java.sql.Timestamp;

public class SuggestionBoardDTO {
	private int sbno;
	private String ptid;
	private int mno;
	private String sbtitle;
	private String sbcontent;
	private String sbfilename;
	private String sbpw;
	private int sbstep;
	private int sbindent;
	private int sbgroup;
	private int sbhit;
	private String sbip;
	private Timestamp sbrdate;
	
	public SuggestionBoardDTO() {
		super();
	}
	public SuggestionBoardDTO(int sbno, String ptid, int mno, String sbtitle, String sbcontent, String sbfilename,
			String sbpw, int sbstep, int sbindent, int sbgroup, int sbhit, String sbip, Timestamp sbrdate) {
		super();
		this.sbno = sbno;
		this.ptid = ptid;
		this.mno = mno;
		this.sbtitle = sbtitle;
		this.sbcontent = sbcontent;
		this.sbfilename = sbfilename;
		this.sbpw = sbpw;
		this.sbstep = sbstep;
		this.sbindent = sbindent;
		this.sbgroup = sbgroup;
		this.sbhit = sbhit;
		this.sbip = sbip;
		this.sbrdate = sbrdate;
	}
	
	@Override
	public String toString() {
		return "SuggestionBoardDTO [sbno=" + sbno + ", ptid=" + ptid + ", mno=" + mno + ", sbtitle=" + sbtitle
				+ ", sbcontent=" + sbcontent + ", sbfilename=" + sbfilename + ", sbpw=" + sbpw + ", sbstep=" + sbstep
				+ ", sbindent=" + sbindent + ", sbgroup=" + sbgroup + ", sbhit=" + sbhit + ", sbip=" + sbip
				+ ", sbrdate=" + sbrdate + "]";
	}
	public int getSbno() {
		return sbno;
	}
	public void setSbno(int sbno) {
		this.sbno = sbno;
	}
	public String getPtid() {
		return ptid;
	}
	public void setPtid(String ptid) {
		this.ptid = ptid;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getSbtitle() {
		return sbtitle;
	}
	public void setSbtitle(String sbtitle) {
		this.sbtitle = sbtitle;
	}
	public String getSbcontent() {
		return sbcontent;
	}
	public void setSbcontent(String sbcontent) {
		this.sbcontent = sbcontent;
	}
	public String getSbfilename() {
		return sbfilename;
	}
	public void setSbfilename(String sbfilename) {
		this.sbfilename = sbfilename;
	}
	public String getSbpw() {
		return sbpw;
	}
	public void setSbpw(String sbpw) {
		this.sbpw = sbpw;
	}
	public int getSbstep() {
		return sbstep;
	}
	public void setSbstep(int sbstep) {
		this.sbstep = sbstep;
	}
	public int getSbindent() {
		return sbindent;
	}
	public void setSbindent(int sbindent) {
		this.sbindent = sbindent;
	}
	public int getSbgroup() {
		return sbgroup;
	}
	public void setSbgroup(int sbgroup) {
		this.sbgroup = sbgroup;
	}
	public int getSbhit() {
		return sbhit;
	}
	public void setSbhit(int sbhit) {
		this.sbhit = sbhit;
	}
	public String getSbip() {
		return sbip;
	}
	public void setSbip(String sbip) {
		this.sbip = sbip;
	}
	public Timestamp getSbrdate() {
		return sbrdate;
	}
	public void setSbrdate(Timestamp sbrdate) {
		this.sbrdate = sbrdate;
	}
	
}
