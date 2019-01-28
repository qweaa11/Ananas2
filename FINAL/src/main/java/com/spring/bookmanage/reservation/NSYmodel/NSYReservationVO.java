package com.spring.bookmanage.reservation.NSYmodel;

public class NSYReservationVO {
	
	private int idx;
	private String bookid_fk;
	private String memberid_fk;
	private String reserveDate;
	
	public NSYReservationVO() {}
	
	public NSYReservationVO(int idx, String bookid_fk, String memberid_fk, String reserveDate) {
		super();
		this.idx = idx;
		this.bookid_fk = bookid_fk;
		this.memberid_fk = memberid_fk;
		this.reserveDate = reserveDate;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getBookid_fk() {
		return bookid_fk;
	}

	public void setBookid_fk(String bookid_fk) {
		this.bookid_fk = bookid_fk;
	}

	public String getMemberid_fk() {
		return memberid_fk;
	}

	public void setMemberid_fk(String memberid_fk) {
		this.memberid_fk = memberid_fk;
	}

	public String getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}
	
}
