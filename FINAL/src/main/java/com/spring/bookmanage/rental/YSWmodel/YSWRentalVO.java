package com.spring.bookmanage.rental.YSWmodel;

public class YSWRentalVO {
	
	private String idx;
	private String bookId;
	private String memberId;
	private String rentalDate;
    private String deadline;
    private String delayDate;
    private String renew;
    private String bookTitle;
    private String pubName;
    private String bookAuthor;
    private String memberName;
    
   
    
    public YSWRentalVO() {}
    
    
    
	public YSWRentalVO(String idx, String bookId, String memberId, String rentalDate, String deadline, String delayDate, String renew,
			String bookTitle, String pubName, String bookAuthor, String memberName) {
		
		this.idx = idx;
		this.bookId = bookId;
		this.memberId = memberId;
		this.rentalDate = rentalDate;
		this.deadline = deadline;
		this.delayDate = delayDate;
		this.renew = renew;
		this.bookTitle = bookTitle;
		this.pubName = pubName;
		this.bookAuthor = bookAuthor;
		this.memberName = memberName;
	}

	
	
	public String getIdx() {
		return idx;
	}


	public void setIdx(String idx) {
		this.idx = idx;
	}


	public String getBookId() {
		return bookId;
	}


	public void setBookId(String bookId) {
		this.bookId = bookId;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public String getRentalDate() {
		return rentalDate;
	}


	public void setRentalDate(String rentalDate) {
		this.rentalDate = rentalDate;
	}


	public String getDeadline() {
		return deadline;
	}
	

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}


	public String getDelayDate() {
		return delayDate;
	}


	public void setDelayDate(String delayDate) {
		this.delayDate = delayDate;
	}


	public String getRenew() {
		return renew;
	}


	public void setRenew(String renew) {
		this.renew = renew;
	}


	public String getBookTitle() {
		return bookTitle;
	}


	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	

	public String getPubName() {
		return pubName;
	}


	public void setPubName(String pubName) {
		this.pubName = pubName;
	}


	public String getBookAuthor() {
		return bookAuthor;
	}


	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}


	public String getMemberName() {
		return memberName;
	}
	

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


}
