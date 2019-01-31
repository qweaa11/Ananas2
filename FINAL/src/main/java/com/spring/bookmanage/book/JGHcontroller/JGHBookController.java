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

/**
 * 삭제도서 컨트롤러
 * @author implements(nine9ash)
 *
 */
@Controller
public class JGHBookController {

	@Autowired private JGHBookService service;

	/**
	 * 삭제도서 목록 페이지 제어
	 * @param request
	 * @param response
	 * @return
	 */
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
			request.setAttribute("searchWord", searchWord);
		} else {// 검색이 없는경우(페이징 처리 미구현)
			deleteBookList = service.noSearchList();
		}// end of if~else

		request.setAttribute("colname", colname);// view단에서 검색어를 유지시키기 위해 보낸것.

		request.setAttribute("deleteBookList", deleteBookList);

		return "book/deleteLog.tiles1";
	}// end of deleteBookLog

	/**
	 * 삭제도서 복구처리 제어
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "restore.ana", method = {RequestMethod.POST})
	public String restore(HttpServletRequest request, HttpServletResponse response) {
		String[] delidArray = request.getParameterValues("delid");

		String msg = "";
		String loc = "";

		try {
			service.restoreBookService(delidArray);

			msg = "삭제된 도서를 복구하는데 성공했습니다.";
			loc = "deleteLog.ana";

		} catch (Throwable e) {
			e.printStackTrace();
			msg = "삭제된 도서를 복구하는데 실패했습니다.";
			loc = "javascript:history.back();";
		}// end of try~catch

		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);

		return "msg";
	}// end of restore
}