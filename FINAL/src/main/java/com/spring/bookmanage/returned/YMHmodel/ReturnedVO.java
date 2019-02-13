package com.spring.bookmanage.returned.YMHmodel;

public class ReturnedVO 
{
	private String idx;
	private String memberID;
	private String name;
	private String phone;
	private String title;
	private String author;
	private String ageCode;
	private String returnDate;
	private String rentalDate;
	private String returnlibcode;
	
	
	public ReturnedVO(){}


	public ReturnedVO(String idx, String memberID, String name, String phone, String title, String author,
			String ageCode, String returnDate, String rentalDate, String returnlibcode) {
		super();
		this.idx = idx;
		this.memberID = memberID;
		this.name = name;
		this.phone = phone;
		this.title = title;
		this.author = author;
		this.ageCode = ageCode;
		this.returnDate = returnDate;
		this.rentalDate = rentalDate;
		this.returnlibcode = returnlibcode;
	}


	public String getIdx() {
		return idx;
	}


	public void setIdx(String idx) {
		this.idx = idx;
	}


	public String getMemberID() {
		return memberID;
	}


	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getAgeCode() {
		return ageCode;
	}


	public void setAgeCode(String ageCode) {
		this.ageCode = ageCode;
	}


	public String getReturnDate() {
		return returnDate;
	}


	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}


	public String getRentalDate() {
		return rentalDate;
	}


	public void setRentalDate(String rentalDate) {
		this.rentalDate = rentalDate;
	}


	public String getReturnlibcode() {
		return returnlibcode;
	}


	public void setReturnlibcode(String returnlibcode) {
		this.returnlibcode = returnlibcode;
	}
	
	
	
	
	
	
}