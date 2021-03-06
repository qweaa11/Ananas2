package com.spring.bookmanage.r3.KGBModel;

public class KGBBookVO {
	
	
	// 도서 테이블
	private String bookid;		// 도서일련번호
	private String bookid_fk;	// 참조키인 도서일련번호
	private String title;		// 도서명
	private String author;		// 저자명
	private int status;			// 상태(일반 0, 대여 1,예약 2,분실 3, 삭제 4)
	private int agecode;		// 연령코드
	private String ISBN;		// 국제표준도서번호
	private String pubcode_fk;	// 출판사 코드
	private int ncode_fk;		// 국가코드
	private String lcode_fk;	// 언어코드
	private String fcode_fk;	// 분야코드
	private String ccode_fk;	// 종류코드
	private String gcode_fk;	// 장르코드
	private String libcode_fk;	// 도서관번호
	
	
	// 도서 상세 테이블
	private String intro;		// 책소개
	private String image;		// 이미지
	private String price;		// 가격
	private String weight;		// 무게
	private String totalpage;	// 쪽수
	private String pdate;		// 발행일자(출판)
	private String regdate;		// 등록일자
	
	
	// join하여 가져오는 컬럼
	private String gname;		// 장르명
	private String cname;		// 종류명
	private String fname;		// 분야명
	private String lname;		// 언어명
	private String nname;		// 국가명
	private String pubname;		// 출판사명
	private String libname;		// 도서관명
	private String addr;		// 도서관 주소
	private String tel;			// 도서관 전화번호
	
	
	// 대여/반납 테이블
	private String memberid_fk;	// 참조 회원 아이디
	private String returndate;	// 반납일자
	private String rentaldate;	// 대여날짜
	private String deadline;	// 반납예정날짜
	private String renew;		// 연장여부
	
	public KGBBookVO() {}

	public KGBBookVO(String bookid, String title, String author, int status, int agecode, String ISBN,
			String pubcode_fk, int ncode_fk, String lcode_fk, String fcode_fk, String ccode_fk, String gcode_fk,
			String libcode_fk, String intro, String image, String price, String weight, String totalpage, String pdate,
			String regdate) {
		this.bookid = bookid;
		this.title = title;
		this.author = author;
		this.status = status;
		this.agecode = agecode;
		this.ISBN = ISBN;
		this.pubcode_fk = pubcode_fk;
		this.ncode_fk = ncode_fk;
		this.lcode_fk = lcode_fk;
		this.fcode_fk = fcode_fk;
		this.ccode_fk = ccode_fk;
		this.gcode_fk = gcode_fk;
		this.libcode_fk = libcode_fk;
		this.intro = intro;
		this.image = image;
		this.price = price;
		this.weight = weight;
		this.totalpage = totalpage;
		this.pdate = pdate;
		this.regdate = regdate;
	}

	public String getBookid() {
		return bookid;
	}

	public void setBookid(String bookid) {
		this.bookid = bookid;
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

	public String getISBN() {
		return ISBN;
	}

	public void setIsbn(String ISBN) {
		this.ISBN = ISBN;
	}

	public String getPubcode_fk() {
		return pubcode_fk;
	}

	public void setPubcode_fk(String pubcode_fk) {
		this.pubcode_fk = pubcode_fk;
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

	public void setLibcode_fk(String libcode_fk) {
		this.libcode_fk = libcode_fk;
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

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}

	public String getPubname() {
		return pubname;
	}

	public void setPubname(String pubname) {
		this.pubname = pubname;
	}

	public String getLibname() {
		return libname;
	}

	public void setLibname(String libname) {
		this.libname = libname;
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

	public String getReturndate() {
		return returndate;
	}

	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}

	public String getRentaldate() {
		return rentaldate;
	}

	public void setRentaldate(String rentaldate) {
		this.rentaldate = rentaldate;
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
	
	
}
