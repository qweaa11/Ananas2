package com.spring.bookmanage.board.YJKmodel;

public class YJKAttachFileVO {
	
	private String idx;
	private String board_idx_fk;
	private String filename;
	private String orgfilename;
	private String filesize;
	
	public YJKAttachFileVO() {}
	
	public YJKAttachFileVO(String idx, String board_idx_fk, String filename, String orgfilename, String filesize) {
		super();
		this.idx = idx;
		this.board_idx_fk = board_idx_fk;
		this.filename = filename;
		this.orgfilename = orgfilename;
		this.filesize = filesize;

	}

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getBoard_idx_fk() {
		return board_idx_fk;
	}

	public void setBoard_idx_fk(String board_idx_fk) {
		this.board_idx_fk = board_idx_fk;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getOrgfilename() {
		return orgfilename;
	}

	public void setOrgfilename(String orgfilename) {
		this.orgfilename = orgfilename;
	}

	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	

}
