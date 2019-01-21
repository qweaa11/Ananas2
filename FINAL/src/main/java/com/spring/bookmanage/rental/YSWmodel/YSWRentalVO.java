package com.spring.bookmanage.rental.YSWmodel;

public class YSWRentalVO {
	
	private String idx;
	private String bookid;
	private String memberid;
	private String retalDate;
    private String deadline;
    private String renew;
    private String bookTitle;
    private String pubname;
    private String bookAuthor;
    private String memberName;
    
   
    
    public YSWRentalVO() {}
    
    
    
	public YSWRentalVO(String idx, String bookid, String memberid, String retalDate, String deadline, String renew,
			String bookTitle, String pubname, String bookAuthor, String memberName) {
		
		this.idx = idx;
		this.bookid = bookid;
		this.memberid = memberid;
		this.retalDate = retalDate;
		this.deadline = deadline;
		this.renew = renew;
		this.bookTitle = bookTitle;
		this.pubname = pubname;
		this.bookAuthor = bookAuthor;
		this.memberName = memberName;
	}


	
	

	public String getIdx() {
		return idx;
	}



	public void setIdx(String idx) {
		this.idx = idx;
	}



	public String getBookid() {
		return bookid;
	}



	public void setBookid(String bookid) {
		this.bookid = bookid;
	}



	public String getMemberid() {
		return memberid;
	}



	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}



	public String getRetalDate() {
		return retalDate;
	}



	public void setRetalDate(String retalDate) {
		this.retalDate = retalDate;
	}



	public String getDeadline() {
		return deadline;
	}



	public void setDeadline(String deadline) {
		this.deadline = deadline;
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
	


	public String getPubname() {
		return pubname;
	}



	public void setPubname(String pubname) {
		this.pubname = pubname;
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
