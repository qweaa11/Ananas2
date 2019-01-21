package com.spring.bookmanage.member.PMGmodel;

/**
 * <b>member(회원)테이블 VO</b>
 * <pre>회원상세정보 페이지에서 회원기본정보를 보여주는 VO</pre>
 * @author 박민규
 */
public class PMGMemberVO {

	private int idx;			// 인덱스(회원번호)
	private String memberid;	// (회원)아이디
	private String pwd;			// (회원)비밀번호
	private String name;		// (회원)이름
	private String email;		// (회원)이메일
	private String phone;		// (회원)연락처	
	private String addr1;		// (신)주소
	private String addr2;		// 상세주소
	private String post;		// 우편번호	
	private String birth;		// 생년월일
	private String gender;		// 성별 (0:남자 1:여자)
	
	private String regDate;		// 가입일자
	private String lastDate;	// 최근접속일자
	private String pwDate;		// 비밀번호변경일자
	private String status;		// 상태(0.활동 1.휴면 2.정지 3.탈퇴)
	
	private String imgFileName;	// 회원이미지 파일명
	private int fileSize;		// 파일크기
	
	private String address;		// 결과주소(post+addr1+addr2)
	private int age;			// 나이
	
	public PMGMemberVO() { }
	
	public PMGMemberVO(int idx, String memberid, String pwd, String name, String email, String phone, String addr1,
			String addr2, String post, String birth, String gender, String regDate, String lastDate, String pwDate,
			String status, String imgFileName, int fileSize, String address, int age) {
		this.idx = idx;
		this.memberid = memberid;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.post = post;
		this.birth = birth;
		this.gender = gender;
		this.regDate = regDate;
		this.lastDate = lastDate;
		this.pwDate = pwDate;
		this.status = status;
		this.imgFileName = imgFileName;
		this.fileSize = fileSize;
		this.address = address;
		this.age = age;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public String getPwDate() {
		return pwDate;
	}

	public void setPwDate(String pwDate) {
		this.pwDate = pwDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
	
}
