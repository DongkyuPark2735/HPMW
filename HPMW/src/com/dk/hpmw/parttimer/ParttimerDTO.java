package com.dk.hpmw.parttimer;

public class ParttimerDTO {
	private String ptid;
	private String ptpw;
	private int ptempconchek;
	
	public ParttimerDTO(String ptid, String ptpw, int ptempconchek) {
		super();
		this.ptid = ptid;
		this.ptpw = ptpw;
		this.ptempconchek = ptempconchek;
	}
	public ParttimerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ParttimerDTO [ptid=" + ptid + ", ptpw=" + ptpw + ", ptempconchek=" + ptempconchek + "]";
	}
	public String getPtid() {
		return ptid;
	}
	public void setPtid(String ptid) {
		this.ptid = ptid;
	}
	public String getPtpw() {
		return ptpw;
	}
	public void setPtpw(String ptpw) {
		this.ptpw = ptpw;
	}
	public int getPtempconchek() {
		return ptempconchek;
	}
	public void setPtempconchek(int ptempconchek) {
		this.ptempconchek = ptempconchek;
	}
}
