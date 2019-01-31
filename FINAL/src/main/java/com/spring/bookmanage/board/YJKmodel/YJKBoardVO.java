package com.spring.bookmanage.board.YJKmodel;

import org.springframework.web.multipart.MultipartFile;

public class YJKBoardVO {

	private String idx;				// 실제 글번호
	private String libid_fk;		// 관리자아이디
	private String name;			// 작성자명
	private String subject;			// 제목
	private String content;			// 내용
	private String pw;				// 글비밀번호
	private String readCount;		// 조회수
	private String regDate;			// 작성일자
	private String status;			// 글상태(삭제 등)
	
	private String previousidx;      // 이전글번호
 	private String previoussubject;  // 이전글제목
 	private String nextidx;          // 다음글번호
 	private String nextsubject;      // 다음글제목
	
 	private String commentCount;	// 댓글수
	private String groupno;			// 그룹번호
	/*
	  답변글쓰기에 있어서 그룹번호
       원글(부모글)과 답변글은 동일한 groupno 를 가진다. 
       답변글이 아닌 원글(부모글)인 경우 groupno 의 값은  groupno 컬럼의 최대값(max)+1 로 한다.   
	 */
	
	private String root;			// 원글번호
	
	/*
	  root 컬럼은 절대로 foreign key가 아니다.
    root 컬럼은 자신의 글(답변글)에 있어서 원글(부모글)이 누구인지에 대한 정보값이다.
         답변글쓰기에 있어서 답변글이라면 fk_seq 컬럼의 값은 원글(부모글)의 seq 컬럼의 값을 가지게 되며,
         답변글이 아닌 원글일 경우 0 을 가지도록 한다. 
	*/
	
	private String depthno;			// 답글높이
	/*
	   답변글쓰기에 있어서 답변글 이라면 원글(부모글)의 depthno + 1 을 가지게 되며,
       답변글이 아닌 원글일 경우 0 을 가지도록 한다. 
	 */
	
	private String fileName;		// WAS(톰캣)에 저장될 파일명(20161121324325454354353333432.png)
	private String orgFileName;		// 진짜 파일명(강아지.png). 사용자가 파일을 업로드 하거나 파일을 다운로드 할때 사용되어지는 파일명
	private String fileSize;		// 파일크기
	
	private MultipartFile attach; // 진짜 파일 ==> WAS(톰캣) 디스크에 저장됨.
 	// !!!!!! MultipartFile attach 는 오라클 데이터베이스 tblBoard 테이블의 컬럼이 아니다.!!!!!!  
 	// /Board/src/main/webapp/WEB-INF/views/tiles1/board/add.jsp 파일에서 input type="file" 인 name 의 이름(attach)과 
 	// 동일해야만 파일첨부가 가능해진다.!!!!
	private int fileCount;     		//board_attachfile 테이블에 있는 첨부파일 갯수

	public YJKBoardVO() {}
	
	public YJKBoardVO(String idx, String libid_fk, String name, String subject, String content, String pw,
			String readCount, String regDate, String status, String previousidx, String previoussubject, String nextidx,
			String nextsubject, String commentCount, String groupno, String root, String depthno, String fileName,
			String orgFileName, String fileSize, MultipartFile attach) {
		super();
		this.idx = idx;
		this.libid_fk = libid_fk;
		this.name = name;
		this.subject = subject;
		this.content = content;
		this.pw = pw;
		this.readCount = readCount;
		this.regDate = regDate;
		this.status = status;
		this.previousidx = previousidx;
		this.previoussubject = previoussubject;
		this.nextidx = nextidx;
		this.nextsubject = nextsubject;
		this.commentCount = commentCount;
		this.groupno = groupno;
		this.root = root;
		this.depthno = depthno;
		this.fileName = fileName;
		this.orgFileName = orgFileName;
		this.fileSize = fileSize;
		this.attach = attach;
	}
	

	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPreviousidx() {
		return previousidx;
	}

	public void setPreviousidx(String previousidx) {
		this.previousidx = previousidx;
	}

	public String getPrevioussubject() {
		return previoussubject;
	}

	public void setPrevioussubject(String previoussubject) {
		this.previoussubject = previoussubject;
	}

	public String getNextidx() {
		return nextidx;
	}

	public void setNextidx(String nextidx) {
		this.nextidx = nextidx;
	}

	public String getNextsubject() {
		return nextsubject;
	}

	public void setNextsubject(String nextsubject) {
		this.nextsubject = nextsubject;
	}

	public String getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}

	public String getGroupno() {
		return groupno;
	}

	public void setGroupno(String groupno) {
		this.groupno = groupno;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getDepthno() {
		return depthno;
	}

	public void setDepthno(String depthno) {
		this.depthno = depthno;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOrgFileName() {
		return orgFileName;
	}

	public void setOrgFileName(String orgFileName) {
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
