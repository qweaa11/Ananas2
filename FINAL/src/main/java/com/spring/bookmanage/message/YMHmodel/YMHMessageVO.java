package com.spring.bookmanage.message.YMHmodel;

public class YMHMessageVO 
{
	private int idx;			// 글번호
	private String tartgetid;	// 받는이 아이디
	private String name;
	private String sender;		// 보내는이 아이디
	private String sendername;		// 보내는이 아이디
	private String title;		// 쪽지 제목
	private String message;		// 쪽지 내용
	private String opendate;	// 열람 일자
	private String senddate;	// 발송 일자
	private String deltarget;	// 받는이 삭제 여부
	private String delsend;		// 보낸이 삭제 여부
	
	////////////////////////////////////////////////////////////////
	public YMHMessageVO(){ }
	
	
	public YMHMessageVO(int idx, String tartgetid, String tartgetname, String sender, String sendername, String title,
			String message, String opendate, String senddate, String deltarget, String delsend) {
		super();
		this.idx = idx;
		this.tartgetid = tartgetid;
		this.name = tartgetname;
		this.sender = sender;
		this.sendername = sendername;
		this.title = title;
		this.message = message;
		this.opendate = opendate;
		this.senddate = senddate;
		this.deltarget = deltarget;
		this.delsend = delsend;
	}


	////////////////////////////////////////////////////////////////

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getTartgetid() {
		return tartgetid;
	}

	public void setTartgetid(String tartgetid) {
		this.tartgetid = tartgetid;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOpendate() {
		return opendate;
	}

	public void setOpendate(String opendate) {
		this.opendate = opendate;
	}

	public String getSenddate() {
		return senddate;
	}

	public void setSenddate(String senddate) {
		this.senddate = senddate;
	}

	public String getDeltarget() {
		return deltarget;
	}

	public void setDeltarget(String deltarget) {
		this.deltarget = deltarget;
	}

	public String getDelsend() {
		return delsend;
	}

	public void setDelsend(String delsend) {
		this.delsend = delsend;
	}


	public String getTartgetname() {
		return name;
	}


	public void setTartgetname(String tartgetname) {
		this.name = tartgetname;
	}


	public String getSendername() {
		return sendername;
	}


	public void setSendername(String sendername) {
		this.sendername = sendername;
	}
	
	////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
