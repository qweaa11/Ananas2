package com.spring.bookmanage.board.BSBmodel;

import org.springframework.web.multipart.MultipartFile;

// ===== #51. VO 생성하기
//       먼저, 오라클에서 tblBoard 테이블을 생성해야 한다.
public class BSBboardVO {

 	private String idx;         // 글번호
 	private String libid_fk;   // 사용자ID
 	private String name;        // 글쓴이
 	private String subject;     // 글제목
 	private String content;     // 글내용  
 	private String pw;          // 글암호
 	private int readCount;   // 글조회수
 	private String regDate;     // 글쓴시간
 	private String status;      // 글삭제여부  1:사용가능한글,  0:삭제된글
 	
 	private String groupno;
 	/*
 	  답변글쓰기에 있어서 그룹번호
         원글(부모글)과 답변글은 동일한 groupno 를 가진다. 
         답변글이 아닌 원글(부모글)인 경우 groupno 의 값은  groupno 컬럼의 최대값(max)+1 로 한다.   
    */ 	 	
 	private String root;
 	
 	private String depthno;
 	/*
 	   답변글쓰기에 있어서 답변글 이라면 원글(부모글)의 depthno + 1 을 가지게 되며,
          답변글이 아닌 원글일 경우 0 을 가지도록 한다. 
    */
 	private String commentCount; // 댓글수
	
 	private String previousidx;      // 이전글번호
 	private String previoussubject;  // 이전글제목
 	private String nextidx;          // 다음글번호
 	private String nextsubject;      // 다음글제목
 	
 	// ===== #83. commentCount 프로퍼티 추가하기
 	//            먼저 tblBoard 테이블에 commentCount 컬럼을 추가한 다음에 해야 한다. =====
 	
 	
 	
 	// ===== #122. 답변형 게시판을 위한 멤버변수 추가하기
 	//             먼저, 오라클에서 tblBoard 테이블과 tblComment 테이블을 기존것은 제거한후
 	//             새로이 만든 다음에 아래처럼 해야한다. =====
 	
 	
 	 
 	
 	
 	
 	/*
 	   ===== #133. 파일첨부를 하도록 VO 수정하기
 	                           먼저, 오라클에서 tblBoard 테이블에
 	               3개 컬럼(fileName, orgFilename, fileSize)을 추가한 다음에 아래의 작업을 해야 한다. ===== 
    */
 	private String fileName;     // WAS(톰캣)에 저장될 파일명(20161121324325454354353333432.png)
 	
 	private String fileSize;     // 파일크기
 	
 	private MultipartFile attach; // 진짜 파일 ==> WAS(톰캣) 디스크에 저장됨.
 	// !!!!!! MultipartFile attach 는 오라클 데이터베이스 tblBoard 테이블의 컬럼이 아니다.!!!!!!  
 	// /Board/src/main/webapp/WEB-INF/views/tiles1/board/add.jsp 파일에서 input type="file" 인 name 의 이름(attach)과 
 	// 동일해야만 파일첨부가 가능해진다.!!!!
 	
 	public BSBboardVO() {}

	public BSBboardVO(String idx, String libid_fk, String name, String subject, String content, String pw,
			int readCount, String regDate, String status, String groupno, String root, String depthno,
			String commentCount, String previousidx, String previoussubject, String nextidx, String nextsubject,
			String fileName,  String fileSize, MultipartFile attach) {
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
		this.groupno = groupno;
		this.root = root;
		this.depthno = depthno;
		this.commentCount = commentCount;
		this.previousidx = previousidx;
		this.previoussubject = previoussubject;
		this.nextidx = nextidx;
		this.nextsubject = nextsubject;
		this.fileName = fileName;
		
		this.fileSize = fileSize;
		this.attach = attach;
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

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
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

	public String getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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
