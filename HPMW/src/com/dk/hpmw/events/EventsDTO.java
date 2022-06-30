package com.dk.hpmw.events;

import java.sql.Date;

public class EventsDTO {
	private String evno;
	private String evtitle;
	private String evdetail;
	private Date evstartdate;
	private int etno;
	private int mno;

	public EventsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EventsDTO(String evno, String evtitle, String evdetail, Date evstartdate, int etno, int mno) {
		super();
		this.evno = evno;
		this.evtitle = evtitle;
		this.evdetail = evdetail;
		this.evstartdate = evstartdate;
		this.etno = etno;
		this.mno = mno;
	}

	@Override
	public String toString() {
		return "EventsDTO [evno=" + evno + ", evtitle=" + evtitle + ", evdetail=" + evdetail + ", evstartdate="
				+ evstartdate + ", etno=" + etno + ", mno=" + mno + "]";
	}

	public String getEvno() {
		return evno;
	}

	public void setEvno(String evno) {
		this.evno = evno;
	}

	public String getEvtitle() {
		return evtitle;
	}

	public void setEvtitle(String evtitle) {
		this.evtitle = evtitle;
	}

	public String getEvdetail() {
		return evdetail;
	}

	public void setEvdetail(String evdetail) {
		this.evdetail = evdetail;
	}

	public Date getEvstartdate() {
		return evstartdate;
	}

	public void setEvstartdate(Date evstartdate) {
		this.evstartdate = evstartdate;
	}

	public int getEtno() {
		return etno;
	}

	public void setEtno(int etno) {
		this.etno = etno;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

}
