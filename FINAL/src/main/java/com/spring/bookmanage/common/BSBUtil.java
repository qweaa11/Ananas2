package com.spring.bookmanage.common;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 * project utility class TEST
 * @author nine9ash
 *
 */
public class BSBUtil {

	/**
	 * 현재시각 조회
	 * @return
	 */
	public static String getNowTime() {
		Date now = new Date();
		String today = String.format("%tF %tT", now, now);

		return today;
	}// end of getNowTime

	/**
	 * 현재요일명 조회
	 * @return
	 */
	public static String getNowDay() {
		Date now = new Date();
		String day = String.format("%tA", now);

		return day;
	}// end of getNowDay

	/*
	 * >> 암호정책 -- 암호는 8글자 이상 15글자 이하에서 영문자, 숫자, 특수기호가 혼합되어진 암호이라면 true 를 리턴해주고, 아니라면
	 * false 를 리턴해주는 메소드 생성하기
	 */
	public static boolean checkPasswd(String passwd) {
		boolean result = false;
		int flagAlphabet = 0;
		int flagNumber = 0;
		int flagSpecial = 0;
		int len = passwd.length();

		if (len < 8 || len > 15) {
			return result;
		} else {
			char ch = ' ';
			for(int i=0;i<len;i++) {
				ch = passwd.charAt(i);

				if(('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z')) {
					flagAlphabet = 1;
				} else if('0' <= ch && ch <= '9') {
					flagNumber = 1;
				} else if(ch == '!' || ch == '@' || ch == '#' || ch == '$' || ch == '%' || ch == '^' || ch == '&'
						|| ch == '*' || ch == '(' || ch == ')' || ch == '-' || ch == '_' || ch == '+' || ch == '=') {
					flagSpecial = 1;
				}// end of inner if~else if
			} // end of for

			if(flagAlphabet+flagNumber+flagSpecial == 3) {
				result = true;
			}// end of inner if

			return result;
		} // end of outer if~else

	}// end of checkPasswd

	/**
	 * 페이지바
	 * @param sizePerPage
	 * @param blockSize
	 * @param totalPage
	 * @param currentShowPageNo
	 * @param url
	 * @return
	 */
	public static String getPageBar(int sizePerPage, int blockSize, int totalPage, int currentShowPageNo, String url) {
		String pageBar = "";
		int loop = 1;
		int pageNo = ((currentShowPageNo-1)/blockSize)*blockSize+1;// 공식임!!!

		// currentShowPageNo 가 1~10 일때 pageNo 는 1
		// currentShowPageNo 가 11~20 일때 pageNo 는 11
		// currentShowPageNo 가 21~30 일때 pageNo 는 21
		String str_pageNo = "";

		if(pageNo == 1) {
			str_pageNo = "";
		} else {
			str_pageNo = "&nbsp;<a href=\""+url+"?currentShowPageNo="+(pageNo - 1)+"&sizePerPage="+sizePerPage
					+ "\" >"+"[이전]</a>&nbsp;";
		} // end of if~else

		pageBar += str_pageNo;

		while(!(pageNo > totalPage || loop > blockSize)) {
			if(pageNo == currentShowPageNo) {
				str_pageNo = "&nbsp;<span style=\"color:red; font-size:13pt; font-weight:bold; text-decoration:underline;\">"
						+ pageNo+"</span>&nbsp;";
			} else {
				str_pageNo = "&nbsp;<a href=\""+url+"?currentShowPageNo="+pageNo+"&sizePerPage="+sizePerPage
						+ "\" >"+pageNo+"</a>"+"&nbsp;";
			} // end of if~else

			pageBar += str_pageNo;
			pageNo++;
			loop++;
		} // end of while

		if(pageNo > totalPage) {
			str_pageNo = "";
		} else {
			str_pageNo = "&nbsp;<a href=\""+url+"?currentShowPageNo="+pageNo+"&sizePerPage="+sizePerPage
					+ "\" >"+"[다음]</a>&nbsp;";
		} // end of if~else

		pageBar += str_pageNo;

		return pageBar;
	}// end of getPageBar

	/**
	 * 검색설정에 따른 페이지바 조회
	 * @param sizePerPage
	 * @param blockSize
	 * @param totalPage
	 * @param currentShowPageNo
	 * @param colname
	 * @param search
	 * @param period
	 * @param url
	 * @return
	 */
	public static String getPageBarWithSearch(int sizePerPage, int blockSize, int totalPage, int currentShowPageNo,
			String colname, String search, String period, String url) {
		String pageBar = "";
		int loop = 1;
		int pageNo = ((currentShowPageNo-1)/blockSize)*blockSize+1;// 공식임!!!

		// currentShowPageNo 가 1~10 일때 pageNo 는 1
		// currentShowPageNo 가 11~20 일때 pageNo 는 11
		// currentShowPageNo 가 21~30 일때 pageNo 는 21
		String str_pageNo = "";

		if(pageNo == 1) {
			str_pageNo = "";
		} else {
			str_pageNo = "&nbsp;<a href=\""+url+"?currentShowPageNo="+(pageNo - 1)+"&sizePerPage="+sizePerPage
					+"&colname="+colname+"&search="+search+"&period="+period+"\" >"
					+"<span class='glyphicon glyphicon-chevron-left' style='font-size:13pt; font-weight: bolder; color:#337ab7;'></span></a>&nbsp;";
		} // end of if~else

		pageBar += str_pageNo;

		while (!(pageNo > totalPage || loop > blockSize)) {
			if (pageNo == currentShowPageNo) {
				str_pageNo = "&nbsp;<span style=\"color:white; background-color:#337ab7; font-size:12pt; font-weight:bold; \">"
						+pageNo+"</span>&nbsp;";
			} else {
				str_pageNo = "&nbsp;<a href=\""+url+"?currentShowPageNo="+pageNo+"&sizePerPage="+sizePerPage
						+"&colname="+colname+"&search="+search+"&period="+period+"\" >"+pageNo+"</a>"
						+"&nbsp;";
			} // end of if~else

			pageBar += str_pageNo;
			pageNo++;
			loop++;
		} // end of while

		if (pageNo > totalPage) {
			str_pageNo = "";
		} else {
			str_pageNo = "&nbsp;<a href=\""+url+"?currentShowPageNo="+pageNo+"&sizePerPage="+sizePerPage
					+ "&colname="+colname+"&search="+search+"&period="+period+"\" >"
					+ "<span class='glyphicon glyphicon-chevron-right' style='font-size:13pt; font-weight: bolder; color:#337ab7;'></span></a>&nbsp;";
		} // end of if~else

		pageBar += str_pageNo;

		return pageBar;
	}// end of getPageBarWithSearch

	/**
	 * 쿼리스트링에 해당하는 추가url값 조회(? 이후에 붙는 부수적인 경로)
	 * @param request
	 * @return
	 */
	public static String getCurrentURL(HttpServletRequest request) {
		String currentURL = request.getRequestURL().toString();// ==> 확인용 currentURL => http://localhost:9090/bookmanage/memberList.ana
		String method = request.getMethod();

		if("GET".equalsIgnoreCase(method)) {
			String queryString = request.getQueryString();
			// ==> 확인용 queryString => currentPageNo=3&sizePerPage=5

			currentURL += "?"+queryString;
			// ==> ==> 확인용 currentURL =>
			// http://localhost:9090/MyMVC/memberList.do?currentShowPageNo=3&sizePerPage=5
		}// end of if

		String ctxPath = request.getContextPath();// /bookmanage

		int beginIndex = currentURL.indexOf(ctxPath);// ==> 확인용 beginIndex => 21
		int ctxNameLength = ctxPath.length();// ==> 6

		currentURL = currentURL.substring(beginIndex+ctxNameLength+1);
		// ==> GET 방식일 경우 확인용 currentURL =>
		// memberList.do?currentShowPageNo=3&sizePerPage=5
		// ==> POST 방식일 경우 확인용 currentURL => memberList.do

		return currentURL;
	}// end of getCurrentURL

	public static String createPageBar(int sizePerPage, int blockSize, int totalPage, int currentPageNo,
			String colname, String searchWord, String url) {
		String pageBar = "";
		int loop = 1;
		int pageNo = ((currentPageNo-1)/blockSize)*blockSize+1;// 공식임!!!

		// currentPageNo 가 1~10 일때 pageNo 는 1
		// currentPageNo 가 11~20 일때 pageNo 는 11
		// currentPageNo 가 21~30 일때 pageNo 는 21
		String str_pageNo = "";

		// prev page
		if(pageNo == 1) {
			str_pageNo = "<li class='disabled'><a href='#'>«</a></li>\n";
		} else {
			str_pageNo = "<li><a href='"+url+"'?currentPageNo="+pageNo+"&sizePerPage="+sizePerPage
					+"&colname="+colname+"&searchWord="+searchWord+"' >«</a></li>\n";
		} // end of if~else

		pageBar += str_pageNo;

		while(!(pageNo > totalPage || loop > blockSize)) {
			if(pageNo == currentPageNo) {
				str_pageNo = "<li class='active'><a href='#'>"+pageNo+"<span class='sr-only'>(current)</span></a></li>\n";
			} else {
				str_pageNo = "<li><a href='"+url+"?currentPageNo="+pageNo+"&sizePerPage="+sizePerPage
						+"&colname="+colname+"&searchWord="+searchWord+"'>"+pageNo+"</a></li>\n";
			} // end of if~else

			pageBar += str_pageNo;
			pageNo++;
			loop++;
		} // end of while

		// next page
		if(pageNo > totalPage) {
			str_pageNo = "<li class='disabled'><a href='#'>»</a></li>\n";
		} else {
			str_pageNo = "<li><a href='"+url+"?currentPageNo="+pageNo+"&sizePerPage="+sizePerPage
					+"&colname="+colname+"&searchWord="+searchWord+"' >»</a></li>\n";
		} // end of if~else	

		pageBar += str_pageNo;

		// System.out.println(pageBar);

		return pageBar;
	}// end of createPageBar
}