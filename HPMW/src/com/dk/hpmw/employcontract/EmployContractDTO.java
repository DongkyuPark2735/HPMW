package com.dk.hpmw.employcontract;

public class EmployContractDTO {
	private String ecno;
	private String ectitle;
	private String eccontent;
	public EmployContractDTO(String ecno, String ectitle, String eccontent) {
		super();
		this.ecno = ecno;
		this.ectitle = ectitle;
		this.eccontent = eccontent;
	}
	public EmployContractDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "EmployContractDTO [ecno=" + ecno + ", ectitle=" + ectitle + ", eccontent=" + eccontent + "]";
	}
	public String getEcno() {
		return ecno;
	}
	public void setEcno(String ecno) {
		this.ecno = ecno;
	}
	public String getEctitle() {
		return ectitle;
	}
	public void setEctitle(String ectitle) {
		this.ectitle = ectitle;
	}
	public String getEccontent() {
		return eccontent;
	}
	public void setEccontent(String eccontent) {
		this.eccontent = eccontent;
	}
	
	
}
