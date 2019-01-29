package com.spring.bookmanage.JDSmodel;


public class AdminVO {
	private int idx;            // 인덱스
	private String adminid;     // 아이디
	private String pwd;         // 비밀번호
	private String name;		// 이름
	
	public AdminVO() { }
	
	public AdminVO(int idx, String adminid, String pwd) {
		
		this.idx = idx;
		this.adminid = adminid;
		this.pwd = pwd;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getAdminid() {
		return adminid;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
