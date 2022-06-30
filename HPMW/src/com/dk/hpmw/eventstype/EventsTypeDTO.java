package com.dk.hpmw.eventstype;

public class EventsTypeDTO {
	private int etno;
	private String etname;

	public EventsTypeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EventsTypeDTO(int etno, String etname) {
		super();
		this.etno = etno;
		this.etname = etname;
	}

	@Override
	public String toString() {
		return "EventsTypeDTO [etno=" + etno + ", etname=" + etname + "]";
	}

	public int getEtno() {
		return etno;
	}

	public void setEtno(int etno) {
		this.etno = etno;
	}

	public String getEtname() {
		return etname;
	}

	public void setEtname(String etname) {
		this.etname = etname;
	}

}
