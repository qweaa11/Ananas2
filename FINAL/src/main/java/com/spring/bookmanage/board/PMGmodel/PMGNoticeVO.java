package com.spring.bookmanage.board.PMGmodel;

import org.springframework.web.multipart.MultipartFile;

public class PMGNoticeVO {

	private String idx;			// 글번호
	private String libid_fk;   	// 관리자아이디 
 	private String adminid_fk;     // 총관리자아이디
 	private String subject;     // 글제목
 	private String content;     // 글내용  
 	private String pw;          // 글암호
 	private String readCount;   // 글조회수
 	private String regDate;     // 글쓴시간
 	private String status;      // 글삭제여부  1:사용가능한글,  0:삭제된글  
	
 	private String previousseq;      // 이전글번호
 	private String previoussubject;  // 이전글제목
 	private String nextseq;          // 다음글번호
 	private String nextsubject;      // 다음글제목
	
 	private String fileName;     // WAS(톰캣)에 저장될 파일명(20161121324325454354353333432.png)
 	private String orgFileName;  // 진짜 파일명(강아지.png). 사용자가 파일을 업로드 하거나 파일을 다운로드 할때 사용되어지는 파일명
 	private String fileSize;     // 파일크기
 	
 	private MultipartFile attach; // 진짜 파일 ==> WAS(톰캣) 디스크에 저장됨.

 	public PMGNoticeVO() { }
 	
	public PMGNoticeVO(String idx, String libid_fk, String adminid_fk, String subject, String content, String pw,
			String readCount, String regDate, String status, String previousseq, String previoussubject, String nextseq,
			String nextsubject, String fileName, String orgFileName, String fileSize) {
		this.idx = idx;
		this.libid_fk = libid_fk;
		this.adminid_fk = adminid_fk;
		this.subject = subject;
		this.content = content;
		this.pw = pw;
		this.readCount = readCount;
		this.regDate = regDate;
		this.status = status;
		this.previousseq = previousseq;
		this.previoussubject = previoussubject;
		this.nextseq = nextseq;
		this.nextsubject = nextsubject;
		this.fileName = fileName;
		this.orgFileName = orgFileName;
		this.fileSize = fileSize;
	}

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getLibid_fk() {
		return libid_fk;
	}

	public void setLibid_fk(String libid_fk) {
		this.libid_fk = libid_fk;
	}

	public String getAdminid() {
		return adminid_fk;
	}

	public void setAdminid(String adminid_fk) {
		this.adminid_fk = adminid_fk;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getReadCount() {
		return readCount;
	}

	public void setReadCount(String readCount) {
		this.readCount = readCount;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPreviousseq() {
		return previousseq;
	}

	public void setPreviousseq(String previousseq) {
		this.previousseq = previousseq;
	}

	public String getPrevioussubject() {
		return previoussubject;
	}

	public void setPrevioussubject(String previoussubject) {
		this.previoussubject = previoussubject;
	}

	public String getNextseq() {
		return nextseq;
	}

	public void setNextseq(String nextseq) {
		this.nextseq = nextseq;
	}

	public String getNextsubject() {
		return nextsubject;
	}

	public void setNextsubject(String nextsubject) {
		this.nextsubject = nextsubject;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOrgFilename() {
		return orgFileName;
	}

	public void setOrgFilename(String orgFileName) {
		this.orgFileName = orgFileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public MultipartFile getAttach() {
		return attach;
	}

	public void setAttach(MultipartFile attach) {
		this.attach = attach;
	}
	
 	
}
