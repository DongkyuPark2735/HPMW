package com.dk.hpmw.manager;

public class ManagerDTO {
	private int mno;
	private String mpw;
	private String mname;
	private int allevel;
	
	public ManagerDTO(int mno, String mpw, String mname, int allevel) {
		super();
		this.mno = mno;
		this.mpw = mpw;
		this.mname = mname;
		this.allevel = allevel;
	}
	
	public ManagerDTO() {
		super();
	}
	
	@Override
	public String toString() {
		return "ManagerDTO [mno=" + mno + ", mpw=" + mpw + ", mname=" + mname + ", allevel=" + allevel + "]";
	}

	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getMpw() {
		return mpw;
	}
	public void setMpw(String mpw) {
		this.mpw = mpw;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public int getAllevel() {
		return allevel;
	}
	public void setAllevel(int allevel) {
		this.allevel = allevel;
	}
	
	
}
