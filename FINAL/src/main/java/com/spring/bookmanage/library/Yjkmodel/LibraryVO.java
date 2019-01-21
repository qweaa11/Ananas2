package com.spring.bookmanage.library.Yjkmodel;

public class LibraryVO {
	
	private String libcode;
	private String idx;
	private String libname;
	private String tel;
	private String addr;
	
	public LibraryVO() {}
	
	public LibraryVO(String libcode, String idx, String libname, String tel, String addr) {
		super();
		this.libcode = libcode;
		this.idx = idx;
		this.libname = libname;
		this.tel = tel;
		this.addr = addr;
	}

	public String getLibcode() {
		return libcode;
	}

	public void setLibcode(String libcode) {
		this.libcode = libcode;
	}

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getLibname() {
		return libname;
	}

	public void setLibname(String libname) {
		this.libname = libname;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	

}
