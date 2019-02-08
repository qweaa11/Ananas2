package com.spring.bookmanage.board.PMGmodel;

public class CommentVO {

	private String idx;			// 댓글번호
	private String libid_fk;	// 도서관장, 사서 아이디
	private String name;		// 이름
	private String content;		// 내용
	private String regDate;		// 작성 날짜
	private String status;		// 댓글 삭제 여부 상태(0:작성 1:삭제)
	private String parentIdx;	// 원게시물 공지사항 글번호
	
	private String libClass;	// 도서관장, 사서
	private String libName;		// 소속 도서관
	
	public CommentVO() {}
	
	public CommentVO(String idx, String libid_fk, String name, String content, String regDate, String status,
			String parentIdx, String libClass, String libName) {
		this.idx = idx;
		this.libid_fk = libid_fk;
		this.name = name;
		this.content = content;
		this.regDate = regDate;
		this.status = status;
		this.parentIdx = parentIdx;
		this.libClass = libClass;
		this.libName = libName;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getParentIdx() {
		return parentIdx;
	}

	public void setParentIdx(String parentIdx) {
		this.parentIdx = parentIdx;
	}

	public String getLibClass() {
		return libClass;
	}

	public void setLibClass(String libClass) {
		this.libClass = libClass;
	}

	public String getLibName() {
		return libName;
	}

	public void setLibName(String libName) {
		this.libName = libName;
	}
	
	
	
	
}
