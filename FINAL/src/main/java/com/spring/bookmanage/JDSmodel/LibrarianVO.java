package com.spring.bookmanage.JDSmodel;


public class LibrarianVO {
	private int idx;            // 인덱스
	private String libid;       // 아이디
	private String name;        // 이름
	private String pwd;         // 비밀번호
	private String libcode_fk;  // 도서관번호
	private String tel;         // 연락처
	private int status;         // 등급(일반사서 0,도서관장 1) 
	

	public LibrarianVO() { }

	public LibrarianVO(int idx, String libid, String name, String pwd, String libcode_fk, String tel, int status) {
		
		this.idx = idx;
		this.libid = libid;
		this.name = name;
		this.pwd = pwd;
		this.libcode_fk = libcode_fk;
		this.tel = tel;
		this.status = status;
	}


	public int getIdx() {
		return idx;
	}


	public void setIdx(int idx) {
		this.idx = idx;
	}


	public String getLibid() {
		return libid;
	}


	public void setLibid(String libid) {
		this.libid = libid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getLibcode_fk() {
		return libcode_fk;
	}


	public void setLibcode_fk(String libcode_fk) {
		this.libcode_fk = libcode_fk;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
