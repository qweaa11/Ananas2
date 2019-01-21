package com.spring.bookmanage.book.YMHmodel;

public class YMHBookVO 
{
	// ===  도서 테이블용  === //
	private String bookId;		// 도서 일련 번호
	private String title; 		// 도서명
	private String author; 		// 저자명
	private String pubcode_fk; 	// 출판사 코드
	private int agecode;		// 연령 코드
	private int ncode_fk; 		// 국가코드
	private String lcode_fk; 	// 언어코드
	private String fcode_fk; 	// 분야코드
	private String ccode_fk;	// 종류코드
	private String gcode_fk; 	// 장르코드
	private String libcode_fk; 	// 도서관코드
	private String ISBN; 		// ISBN 코드
	
	
	// ===  도서상세 테이블용  === //
	private String intro; 		// 책소개
	private String image;			// 이미지파일
	private int price; 		// 가격
	private String weight;			// 무게
	private int totalpage; 	// 총페이지수
	private String pDate;			// 발행일자
	
	
	
	
	
}
