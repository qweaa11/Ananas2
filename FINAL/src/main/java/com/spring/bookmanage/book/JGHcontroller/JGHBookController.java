package com.spring.bookmanage.book.JGHcontroller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.bookmanage.JDSmodel.LibrarianVO;
import com.spring.bookmanage.book.JGHmodel.DeleteBookVO;
import com.spring.bookmanage.book.JGHservice.JGHBookService;

@Controller
public class JGHBookController {

	@Autowired private JGHBookService service;

	@RequestMapping(value = "deleteLog.ana", method = {RequestMethod.GET})
	public String deleteLog(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		LibrarianVO librarian = (LibrarianVO)session.getAttribute("loginLibrarian");
		HashMap<String,String> libcode = new HashMap<String,String>();
		if(librarian != null) {
			libcode.put("LIBCODE", librarian.getLibcode_fk());
		}// end of if

		List<DeleteBookVO> deleteBookList = null;

		String colname = request.getParameter("colname");
		String searchWord = request.getParameter("searchWord");
		request.setAttribute("colname", colname);
		request.setAttribute("searchWord", searchWord);

		HashMap<String, String> parameterMap = new HashMap<>();
		parameterMap.put("colname", colname);
		parameterMap.put("searchWord", searchWord);

		if(searchWord != null && !searchWord.trim().equals("")) {// 검색이 있는경우(페이징 처리 미구현)
			deleteBookList = service.searchList(parameterMap);

			request.setAttribute("colname", colname);// view단에서 검색어를 유지시키기 위해 보낸것.
			request.setAttribute("searchWord", searchWord);
		} else {// 검색이 없는경우(페이징 처리 미구현)
			deleteBookList = service.noSearchList();
		}// end of if~else

		request.setAttribute("deleteBookList", deleteBookList);

		return "book/deleteLog.tiles1";
	}// end of deleteBookLog
}