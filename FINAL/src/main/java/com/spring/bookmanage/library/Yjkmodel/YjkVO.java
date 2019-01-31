package com.spring.bookmanage.library.Yjkmodel;

import org.springframework.web.multipart.MultipartFile;

public class YjkVO {

	private String libid;			// 아이디
	private String libcode_fk;		// 도서관번호
	private int idx;				// 인덱스
	private String pwd;				// 비밀번호
	private String name;			// 이름
	private String tel;				// 연락처
	private String status;			// 등급
	private String imgFileName;		// 이미지 파일이름
	private String email;			// 이메일
	private MultipartFile attach;	// 파일첨부
	
	public YjkVO() {}
	
	public YjkVO(String libid, String libcode_fk, int idx, String pwd, String name, String tel, String status, String imgFileName) {
		super();
		this.libid = libid;
		this.libcode_fk = libcode_fk;
		this.idx = idx;
		this.pwd = pwd;
		this.name = name;
		this.tel = tel;
		this.status = status;
		this.imgFileName = imgFileName;
		
	}
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLibid() {
		return libid;
	}

	public void setLibid(String libid) {
		this.libid = libid;
	}

	public String getLibcode_fk() {
		return libcode_fk;
	}

	public void setLibcode_fk(String libcode_fk) {
		this.libcode_fk = libcode_fk;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public MultipartFile getAttach() {
		return attach;
	}

	public void setAttach(MultipartFile attach) {
		this.attach = attach;
	}
	
}
