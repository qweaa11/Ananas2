package com.spring.bookmanage.book.KKHmodel;

public class KKHBookVO {
	
	private String bookid;  // 도서일련번호
	private int idx;		// book 테이블의 index 값
	private String title;	// book 테이블의 도서명
	private String author;	// book 테이블의 저자
	private int status;		// book 테이블의 도서 상태값
	private int agecode;	// book 테이블의 도서연령
	private int count; 		// 해당 도서번호를 가진 같은 책의 도서권수
	private String gcode_fk;
	private String gname; 	// genre 코드  이름(장르 코드)
	private String ncode_fk;
	private String nname;	// nation 코드 이름(국가코드 0-국내 / 1- 해외)
	private String lcode_fk;
	private String lname; 	// language 코드 이름 (언어 코드)
	private String fcode_fk;
	private String fname;	// field 코드 이름(분야 코드 ex] 철학, 과학 etc)
	private String ccode_fk;
	private String cname;	// category 코드 이름(종류 코드) 소설, 시 등등
	private String libcode_fk;
	private String libname;	// 도서관 코드 이름
	private String pubcode_fk;
	private String pubname;	// 출판사 코드 이름
	private String isbn;	// 도서별 ISBN 값
	private String intro;	// book_detail 테이블의 책소개 글
	private String image;	// book_detail 테이블의 책 이미지 값
	private String price;	// 책가격
	private String weight;	// 책 무게
	private String totalpage;// 총 페이지수
	private String pdate;	// 출판날짜
	private String regdate;	// 도서 등록날짜
	
	public KKHBookVO() {}
	
	public KKHBookVO(String bookid, int idx, String title, String author, int status, int agecode, String gname,
			String nname, String lname, String fname, String cname, String libname, String pubname, String isbn,
			String intro, String image, String price, String weight, String totalpage, String pdate, String regdate) {
		this.bookid = bookid;
		this.idx = idx;
		this.title = title;
		this.author = author;
		this.status = status;
		this.agecode = agecode;
		this.gname = gname;
		this.nname = nname;
		this.lname = lname;
		this.fname = fname;
		this.cname = cname;
		this.libname = libname;
		this.pubname = pubname;
		this.isbn = isbn;
		this.intro = intro;
		this.image = image;
		this.price = price;
		this.weight = weight;
		this.totalpage = totalpage;
		this.pdate = pdate;
		this.regdate = regdate;
	}
	
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getGcode_fk() {
		return gcode_fk;
	}

	public void setGcode_fk(String gcode_fk) {
		this.gcode_fk = gcode_fk;
	}

	public String getNcode_fk() {
		return ncode_fk;
	}

	public void setNcode_fk(String ncode_fk) {
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

	public String getCcode_fk() {
		return ccode_fk;
	}

	public void setCcode_fk(String ccode_fk) {
		this.ccode_fk = ccode_fk;
	}

	public String getLibcode_fk() {
		return libcode_fk;
	}

	public void setLibcode_fk(String libcode_fk) {
		this.libcode_fk = libcode_fk;
	}

	public String getPubcode_fk() {
		return pubcode_fk;
	}

	public void setPubcode_fk(String pubcode_fk) {
		this.pubcode_fk = pubcode_fk;
	}

	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getAgecode() {
		return agecode;
	}
	public void setAgecode(int agecode) {
		this.agecode = agecode;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getNname() {
		return nname;
	}
	public void setNname(String nname) {
		this.nname = nname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getLibname() {
		return libname;
	}
	public void setLibname(String libname) {
		this.libname = libname;
	}
	public String getPubname() {
		return pubname;
	}
	public void setPubname(String pubname) {
		this.pubname = pubname;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(String totalpage) {
		this.totalpage = totalpage;
	}
	public String getPdate() {
		return pdate;
	}
	public void setPdate(String pdate) {
		this.pdate = pdate;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	public String getTranseStatus() {
		String statusname = "";
		switch (status) {
		case 0:
			statusname = "기본";
			break;
		case 1:
			statusname = "대여";
			break;
		case 2:
			statusname = "예약";
			break;
		case 3:
			statusname = "분실";
			break;
		case 4:
			statusname = "삭제";
			break;
		}
		
		return statusname;
		
	}
	
	public String getTranseAgecode() {
		String agename = "";
		switch (agecode) {
		case 0:
			agename = "전체 연령가";
			break;
		case 1:
			agename = "아동";
			break;
		case 2:
			agename = "청소년";
			break;
		case 3:
			agename = "성인";
			break;
		}
		
		
		return agename;
	}
	
	
	
	

}
