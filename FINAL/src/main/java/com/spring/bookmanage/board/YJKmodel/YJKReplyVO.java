package com.spring.bookmanage.board.YJKmodel;

public class YJKReplyVO {
	
	private String idx;
	private String libid_fk;
	private String name;
	private String content;
	private String regdate;
	private String status;
	private String parentidx;
	
	public YJKReplyVO() {}
	
	public YJKReplyVO(String idx, String libid_fk, String name, String content, String regdate, String status,
			String parentidx) {
		super();
		this.idx = idx;
		this.libid_fk = libid_fk;
		this.name = name;
		this.content = content;
		this.regdate = regdate;
		this.status = status;
		this.parentidx = parentidx;
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

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getParentidx() {
		return parentidx;
	}

	public void setParentidx(String parentidx) {
		this.parentidx = parentidx;
	}

}
