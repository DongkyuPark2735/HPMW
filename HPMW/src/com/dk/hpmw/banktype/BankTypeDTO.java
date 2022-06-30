package com.dk.hpmw.banktype;

public class BankTypeDTO {
	private int btno;
	private String btname;

	public BankTypeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BankTypeDTO(int btno, String btname) {
		super();
		this.btno = btno;
		this.btname = btname;
	}

	@Override
	public String toString() {
		return "BankTypeDTO [btno=" + btno + ", btname=" + btname + "]";
	}

	public int getBtno() {
		return btno;
	}

	public void setBtno(int btno) {
		this.btno = btno;
	}

	public String getBtname() {
		return btname;
	}

	public void setBtname(String btname) {
		this.btname = btname;
	}

}
