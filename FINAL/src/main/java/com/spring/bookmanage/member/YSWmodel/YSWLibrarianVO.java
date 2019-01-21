package com.spring.bookmanage.member.YSWmodel;

public class YSWLibrarianVO {
	
	private int RNO;
	private int librarianIDX;
	private String libid;
	private String pwd;
	private String libcode_fk;
	private String librarianName;
	private String librarianTel;
	private int status;
	private String imgfilename;
    private String libName;
    private String libTel;
    private String addr;
    
    
    
    public YSWLibrarianVO() {}
    
    
	public YSWLibrarianVO(int RNO, int librarianIDX, String libid, String pwd, String libcode_fk, String librarianName,
			String librarianTel, int status, String imgfilename, String libName, String libTel, String addr) {
		super();
		this.RNO = RNO;
		this.librarianIDX = librarianIDX;
		this.libid = libid;
		this.pwd = pwd;
		this.libcode_fk = libcode_fk;
		this.librarianName = librarianName;
		this.librarianTel = librarianTel;
		this.status = status;
		this.imgfilename = imgfilename;
		this.libName = libName;
		this.libTel = libTel;
		this.addr = addr;
	}
	
	
	

	public int getRNO() {
		return RNO;
	}


	public void setRNO(int rNO) {
		RNO = rNO;
	}


	public int getLibrarianIDX() {
		return librarianIDX;
	}


	public void setLibrarianIDX(int librarianIDX) {
		this.librarianIDX = librarianIDX;
	}


	public String getLibid() {
		return libid;
	}


	public void setLibid(String libid) {
		this.libid = libid;
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


	public String getLibrarianName() {
		return librarianName;
	}


	public void setLibrarianName(String librarianName) {
		this.librarianName = librarianName;
	}


	public String getLibrarianTel() {
		return librarianTel;
	}


	public void setLibrarianTel(String librarianTel) {
		this.librarianTel = librarianTel;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getImgfilename() {
		return imgfilename;
	}


	public void setImgfilename(String imgfilename) {
		this.imgfilename = imgfilename;
	}


	public String getLibName() {
		return libName;
	}


	public void setLibName(String libName) {
		this.libName = libName;
	}


	public String getLibTel() {
		return libTel;
	}


	public void setLibTel(String libTel) {
		this.libTel = libTel;
	}


	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}
	
    

}
