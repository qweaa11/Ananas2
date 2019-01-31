package com.spring.bookmanage.book.JGHmodel;

/**
 * 삭제도서 VO
 * @author implements(nine9ash)
 *
 */
public class DeleteBookVO {

	/* 테이블에 존재하지 않는 필드 */
	private int rno;// 행번호
	private int count;// 삭제된 동일도서의 총 권수

	/* 도서 테이블 */
	private int idx;				// 인덱스
	private String bookid; 			// 도서일련번호
	private String title; 			// 도서명
	private String author; 			// 저자명
	private int status; 			// 상태(일반 0, 대여 1,예약 2,분실 3, 삭제 4)
	private int agecode; 			// 연령코드
	private String ISBN; 			// 국제표준도서번호
	private String pubcode; 		// 출판사 코드
	private int ncode; 			// 국가코드
	private String lcode; 		// 언어코드
	private String fcode; 		// 분야코드
	private String ccode; 		// 종류코드
	private String gcode; 		// 장르코드
	private String libcode; 		// 도서관번호

	/* 도서 상세 테이블 */
	private String intro; 			// 책소개
	private String image; 			// 이미지
	private String price; 			// 가격
	private String weight; 			// 무게
	private String totalpage; 		// 쪽수
	private String pdate; 			// 발행일자(출판)
	private String regdate; 		// 등록일자

	/* join하여 가져오는 컬럼 */
	private String gname; 			// 장르명
	private String cname; 			// 종류명
	private String fname; 			// 분야명
	private String lname; 			// 언어명
	private String nname; 			// 국가명
	private String pubname; 		// 출판사명
	private String libname; 		// 도서관명
	private String addr; 			// 도서관 주소
	private String tel; 			// 도서관 전화번호

	/* 삭제된 도서테이블의 컬럼 */
	private int delid;				// 삭제도서 그룹번호
	private String deldate;			// 삭제일자
	private String cleanerid;		// 처리자아이디

	public DeleteBookVO() { }// end of default constructor

	public DeleteBookVO(int idx, String bookid, String title, String author, int status, int agecode, String ISBN,
			String pubcode, int ncode, String lcode, String fcode, String ccode, String gcode,
			String libcode, String intro, String image, String price, String weight, String totalpage, String pdate,
			String regdate) {
		this.idx = idx;
		this.bookid = bookid;
		this.title = title;
		this.author = author;
		this.status = status;
		this.agecode = agecode;
		this.ISBN = ISBN;
		this.pubcode = pubcode;
		this.ncode = ncode;
		this.lcode = lcode;
		this.fcode = fcode;
		this.ccode = ccode;
		this.gcode = gcode;
		this.libcode = libcode;
		this.intro = intro;
		this.image = image;
		this.price = price;
		this.weight = weight;
		this.totalpage = totalpage;
		this.pdate = pdate;
		this.regdate = regdate;
	}// end of DeleteBookVO

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
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

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public String getPubcode() {
		return pubcode;
	}

	public void setPubcode(String pubcode) {
		this.pubcode = pubcode;
	}

	public int getNcode() {
		return ncode;
	}

	public void setNcode(int ncode) {
		this.ncode = ncode;
	}

	public String getLcode() {
		return lcode;
	}

	public void setLcode(String lcode) {
		this.lcode = lcode;
	}

	public String getFcode() {
		return fcode;
	}

	public void setFcode(String fcode) {
		this.fcode = fcode;
	}

	public String getCcode() {
		return ccode;
	}

	public void setCcode(String ccode) {
		this.ccode = ccode;
	}

	public String getGcode() {
		return gcode;
	}

	public void setGcode(String gcode) {
		this.gcode = gcode;
	}

	public String getLibcode() {
		return libcode;
	}

	public void setLibcode(String libcode) {
		this.libcode = libcode;
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

	public int getDelid() {
		return delid;
	}

	public void setDelid(int delid) {
		this.delid = delid;
	}

	public String getDeldate() {
		return deldate;
	}

	public void setDeldate(String deldate) {
		this.deldate = deldate;
	}

	public String getCleanerid() {
		return cleanerid;
	}

	public void setCleanerid(String cleanerid) {
		this.cleanerid = cleanerid;
	}

	public String getAgecode_trans() {
		String agerange = "";

		switch (agecode) {
		case 0:
			agerange = "전체";
			break;

		case 1:
			agerange = "아동";
			break;

		case 2:
			agerange = "청소년";
			break;

		case 3:
			agerange = "성인";
			break;
		}// end of switch

		return agerange;
	}// end of getAgecode_trans
}