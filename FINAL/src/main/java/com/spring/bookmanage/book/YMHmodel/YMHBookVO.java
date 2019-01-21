package com.spring.bookmanage.book.YMHmodel;

import org.springframework.web.multipart.MultipartFile;

public class YMHBookVO 
{
	// ===  도서 테이블용  === //
	private String bookId;		// 도서 일련 번호
	private String title; 		// 도서명
	private String author; 		// 저자명
	private String pubCode_fk; 	// 출판사 코드
	private int ageCode;		// 연령 코드
	private int ncode_fk; 		// 국가코드
	private String lcode_fk; 	// 언어코드
	
	private String fcode_fk; 	// 분야코드
	private String fname; 		// 분야이름
	private String ccode_fk;	// 종류코드
	private String gcode_fk; 	// 장르코드
	private String libcode_fk; 	// 도서관코드
	private String ISBN; 		// ISBN 코드
	private int bookCount;	// 책 수
	
	
	// ===  도서상세 테이블용  === //
	private String intro; 		// 책소개
	private String image;		// 이미지파일
	private int price; 			// 가격
	private String weight;		// 무게
	private int totalPage; 		// 총페이지수
	private String pDate;		// 발행일자
	
	
	// ===  출판사 테이블용  === //
	private String pubCode; 	// 출판사 코드
	private String pubName; 	// 출판사 이름
	private String addr; 		// 출판사 주소
	private String tel; 		// 출판사 연락처
	
	// ===  도서관 테이블용  === //
	private String libCode; 	// 도서관 코드
	private String libName; 	// 도서관 이름
	
	// ===  파일 첨부용  === //
	private String filename;
	private String orgFilename;
	private String filesize;
	private MultipartFile attach;
	
	
	/////////////////////////////////////////////////////////////
	public YMHBookVO() {}


	public YMHBookVO(String bookId, String title, String author, String pubCode_fk, int ageCode, int ncode_fk,
			String lcode_fk, String fcode_fk, String fname, String ccode_fk, String gcode_fk, String libcode_fk,
			String iSBN, int bookCount, String intro, String image, int price, String weight, int totalPage,
			String pDate, String pubCode, String pubName, String addr, String tel, String libCode, String libName,
			String filename, String orgFilename, String filesize) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.pubCode_fk = pubCode_fk;
		this.ageCode = ageCode;
		this.ncode_fk = ncode_fk;
		this.lcode_fk = lcode_fk;
		this.fcode_fk = fcode_fk;
		this.fname = fname;
		this.ccode_fk = ccode_fk;
		this.gcode_fk = gcode_fk;
		this.libcode_fk = libcode_fk;
		this.ISBN = iSBN;
		this.bookCount = bookCount;
		this.intro = intro;
		this.image = image;
		this.price = price;
		this.weight = weight;
		this.totalPage = totalPage;
		this.pDate = pDate;
		this.pubCode = pubCode;
		this.pubName = pubName;
		this.addr = addr;
		this.tel = tel;
		this.libCode = libCode;
		this.libName = libName;
		this.filename = filename;
		this.orgFilename = orgFilename;
		this.filesize = filesize;
	}

	
	
	/////////////////////////////////////////////////////////////
	
	


	public String getBookId() {
		return bookId;
	}


	public void setBookId(String bookId) {
		this.bookId = bookId;
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


	public String getPubCode_fk() {
		return pubCode_fk;
	}


	public void setPubCode_fk(String pubCode_fk) {
		this.pubCode_fk = pubCode_fk;
		this.pubCode = pubCode_fk;
	}


	public int getAgeCode() {
		return ageCode;
	}


	public void setAgeCode(int ageCode) {
		this.ageCode = ageCode;
	}


	public int getNcode_fk() {
		return ncode_fk;
	}


	public void setNcode_fk(int ncode_fk) {
		this.ncode_fk = ncode_fk;
	}


	public String getLcode_fk() {
		return lcode_fk;
	}


	public void setLcode_fk(String lcode_fk) {
		this.lcode_fk = lcode_fk;
	}


	public String getFcode_fk() {
		return fcode_fk;
	}


	public void setFcode_fk(String fcode_fk) {
		this.fcode_fk = fcode_fk;
	}


	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getCcode_fk() {
		return ccode_fk;
	}


	public void setCcode_fk(String ccode_fk) {
		this.ccode_fk = ccode_fk;
	}


	public String getGcode_fk() {
		return gcode_fk;
	}


	public void setGcode_fk(String gcode_fk) {
		this.gcode_fk = gcode_fk;
	}


	public String getLibcode_fk() {
		return libcode_fk;
	}




	public String getISBN() {
		return ISBN;
	}


	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}


	public int getBookCount() {
		return bookCount;
	}


	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}


	public String getIntro() {
		return intro;
	}


	public void setIntro(String intro) {
		this.intro = intro;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getWeight() {
		return weight;
	}


	public void setWeight(String weight) {
		this.weight = weight;
	}


	public int getTotalPage() {
		return totalPage;
	}


	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	public String getpDate() {
		return pDate;
	}


	public void setpDate(String pDate) {
		this.pDate = pDate;
	}


	public String getPubCode() {
		return pubCode;
	}

	

	public String getPubName() {
		return pubName;
	}


	public void setPubName(String pubName) {
		this.pubName = pubName;
	}


	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getLibCode() {
		return libCode;
	}


	public void setLibCode(String libCode) {
		this.libCode = libCode;
		this.libcode_fk = libCode;
	}


	public String getLibName() {
		return libName;
	}


	public void setLibName(String libName) {
		this.libName = libName;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getOrgFilename() {
		return orgFilename;
	}


	public void setOrgFilename(String orgFilename) {
		this.orgFilename = orgFilename;
	}


	public String getFilesize() {
		return filesize;
	}


	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}


	public MultipartFile getAttach() {
		return attach;
	}


	public void setAttach(MultipartFile attach) {
		this.attach = attach;
	}

	
	
	
	
	
	
	
	
	
}
