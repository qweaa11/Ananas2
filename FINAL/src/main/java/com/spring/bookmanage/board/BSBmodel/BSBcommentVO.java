package com.spring.bookmanage.board.BSBmodel;


// ===== #82. 댓글쓰기용 VO 생성하기
//            먼저 오라클에서 tblComment 테이블을 생성한다.
//            또한 tblBoard 테이블에 commentCount 컬럼을 추가한다. =====

public class BSBcommentVO {
	
	private String idx;          // 댓글번호
	private String libid_fk;    // 사용자ID
	private String name;         // 성명
	private String content;      // 댓글내용
	private String regDate;      // 작성일자	
	private String status;       // 글삭제여부
	private String parentidx;    // 원게시물 글번호
	
	public BSBcommentVO() { }
	
	public BSBcommentVO(String idx, String libid_fk, String name, String content, String regDate, String parentidx, String status) {
		this.idx = idx;
		this.libid_fk = libid_fk;
		this.name = name;
		this.content = content;
		this.regDate = regDate;
		this.parentidx = parentidx;
		this.status = status;
	}

	public String getidx() {
		return idx;
	}

	public void setidx(String idx) {
		this.idx = idx;
	}

	public String getlibid_fk() {
		return libid_fk;
	}

	public void setlibid_fk(String libid_fk) {
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

	public String getParentidx() {
		return parentidx;
	}

	public void setParentidx(String parentidx) {
		this.parentidx = parentidx;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
