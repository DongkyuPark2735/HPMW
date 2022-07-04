package com.dk.hpmw.parttimercontract;

import java.sql.Date;

public class ParttimerContractDTO {
	private String ptconno;
	private String ptid;
	private String ptname;
	private String pttel;
	private String ptemail;
	private String ptaddress;
	private int btno;
	private String ptaccountno;
	private String evno;
	private int ptstatus;
	private int ptworktime;
	private int pthourlywage;
	private int pttotalpay;
	private Date ptrdate;
	
	public ParttimerContractDTO() {
		super();
	}

	public ParttimerContractDTO(String ptconno, String ptid, String ptname, String pttel, String ptemail,
			String ptaddress, int btno, String ptaccountno, String evno, int ptstatus, int ptworktime, int pthourlywage,
			int pttotalpay, Date ptrdate) {
		super();
		this.ptconno = ptconno;
		this.ptid = ptid;
		this.ptname = ptname;
		this.pttel = pttel;
		this.ptemail = ptemail;
		this.ptaddress = ptaddress;
		this.btno = btno;
		this.ptaccountno = ptaccountno;
		this.evno = evno;
		this.ptstatus = ptstatus;
		this.ptworktime = ptworktime;
		this.pthourlywage = pthourlywage;
		this.pttotalpay = pttotalpay;
		this.ptrdate = ptrdate;
	}

	@Override
	public String toString() {
		return "ParttimerContractDTO [ptconno=" + ptconno + ", ptid=" + ptid + ", ptname=" + ptname + ", pttel=" + pttel
				+ ", ptemail=" + ptemail + ", ptaddress=" + ptaddress + ", btno=" + btno + ", ptaccountno="
				+ ptaccountno + ", evno=" + evno + ", ptstatus=" + ptstatus + ", ptworktime=" + ptworktime
				+ ", pthourlywage=" + pthourlywage + ", pttotalpay=" + pttotalpay + ", ptrdate=" + ptrdate + "]";
	}

	public String getPtconno() {
		return ptconno;
	}

	public void setPtconno(String ptconno) {
		this.ptconno = ptconno;
	}

	public String getPtid() {
		return ptid;
	}

	public void setPtid(String ptid) {
		this.ptid = ptid;
	}

	public String getPtname() {
		return ptname;
	}

	public void setPtname(String ptname) {
		this.ptname = ptname;
	}

	public String getPttel() {
		return pttel;
	}

	public void setPttel(String pttel) {
		this.pttel = pttel;
	}

	public String getPtemail() {
		return ptemail;
	}

	public void setPtemail(String ptemail) {
		this.ptemail = ptemail;
	}

	public String getPtaddress() {
		return ptaddress;
	}

	public void setPtaddress(String ptaddress) {
		this.ptaddress = ptaddress;
	}

	public int getBtno() {
		return btno;
	}

	public void setBtno(int btno) {
		this.btno = btno;
	}

	public String getPtaccountno() {
		return ptaccountno;
	}

	public void setPtaccountno(String ptaccountno) {
		this.ptaccountno = ptaccountno;
	}

	public String getEvno() {
		return evno;
	}

	public void setEvno(String evno) {
		this.evno = evno;
	}

	public int getPtstatus() {
		return ptstatus;
	}

	public void setPtstatus(int ptstatus) {
		this.ptstatus = ptstatus;
	}

	public int getPtworktime() {
		return ptworktime;
	}

	public void setPtworktime(int ptworktime) {
		this.ptworktime = ptworktime;
	}

	public int getPthourlywage() {
		return pthourlywage;
	}

	public void setPthourlywage(int pthourlywage) {
		this.pthourlywage = pthourlywage;
	}

	public int getPttotalpay() {
		return pttotalpay;
	}

	public void setPttotalpay(int pttotalpay) {
		this.pttotalpay = pttotalpay;
	}

	public Date getPtrdate() {
		return ptrdate;
	}

	public void setPtrdate(Date ptrdate) {
		this.ptrdate = ptrdate;
	}
	
	
}
