package com.spring.bookmanage.member.JGHmodel;

public class MemberVO {
	private int idx;				// 인덱스
	private String memberid;		// 아이디
	private String pwd;				// 비밀번호(암호화)
	private String name;			// 이름
	private String email;			// 이메일(암호화, 복호화)
	private String phone;			// 연락처(암호화, 복호화)
	private String addr1;			// 주소
	private String addr2;			// 상세주소
	private String post;			// 신우편번호
	private String birth;			// 생년월일
	private String gender;			// 성별코드
	private int revokeDate;			// 정지기간
	private int latefee;			// 연체료
	private String regDate;			// 가입일자
	private String lastDate;		// 최근접속일자=마지막접속일자
	private String pwDate;			// 최근 비밀번호변경일자=마지막 비밀번호 변경일자
	private String status;			// 회원상태(기본=0, 휴면=1, 정지=2, 탈퇴=3 등)
	private String imgFileName;		// 서버에 업로드되는 이미지파일명
	private int fileSize;			// 파일크기

	private String ages;			// 연령대

	private int rno;

	public MemberVO() { }// end of default constructor

	public MemberVO(int idx, String memberid, String pwd, String name, String email, String phone, String addr1,
			String addr2, String post, String birth, String gender, int revokeDate, int latefee, String regDate, String lastDate,
			String pwDate, String status, String imgFileName, int fileSize, String ages) {
		super();
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
		this.revokeDate = revokeDate;
		this.latefee = latefee;
		this.regDate = regDate;
		this.lastDate = lastDate;
		this.pwDate = pwDate;
		this.status = status;
		this.imgFileName = imgFileName;
		this.fileSize = fileSize;
		this.ages = ages;
	}// end of constructor

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
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

	public void setGender(int gender) {
		if(gender == 0)
			this.gender = "남자";
		else
			this.gender = "여자";
	}

	public int getRevokeDate() {
		return revokeDate;
	}

	public void setRevokeDate(int revokeDate) {
		this.revokeDate = revokeDate;
	}

	public String getRegDate() {
		return regDate;
	}

	public int getLatefee() {
		return latefee;
	}

	public void setLatefee(int latefee) {
		this.latefee = latefee;
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

	public void setStatus(int status) {
		switch (status) {
		case 0:
			this.status="일반";
			break;
		case 1:
			this.status="휴면";
			break;
		case 2:
			this.status="이용정지";
			break;
		case 3:
			this.status="탈퇴";
			break;
		case 4:
			this.status="영구정지";
			break;
		}// end of switch
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

	public String getAges() {
		return ages;
	}

	public void setAges(String ages) {
		this.ages = ages;
	}
}