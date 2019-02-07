package com.spring.bookmanage.library.NSYcontroller;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.bookmanage.common.FileManager;
import com.spring.bookmanage.library.NSYmodel.NSYLibraryVO;
import com.spring.bookmanage.library.NSYservice.NSYInterLibraryService;

//==== 컨트롤러 선언 ====
@Controller
@Component
public class NSYLibraryController {
	
	//==== Serivce 의존객체 주입하기(DI : Dependency Injection) ====
	@Autowired
	private NSYInterLibraryService service;
	
	//===== 파일업로드 및 파일다운로드 해주는 FileManager 클래스 의존객체 주입하기 (DI : Dependency Injection) =====
	@Autowired
	private FileManager fileManager;
	
	//==== 도서관등록 페이지 요청하기 ====
	@RequestMapping(value="/resisterLibrary.ana", method={RequestMethod.GET})
	public String resisterLibrary(HttpServletRequest req, HttpServletResponse res) {
		
		return "library/NSYresisterLibrary.tiles1";
		
	}// end of resisterLibrary()
	
	//==== 도서관등록 완료하기 ====
	@RequestMapping(value="/resisterLibraryEnd.ana", method={RequestMethod.POST})
	public String resisterLibraryEnd(MultipartHttpServletRequest req, HttpServletResponse res, NSYLibraryVO libraryvo) {
		
		String libname = req.getParameter("libname");
		String tel = req.getParameter("tel");
		String post = req.getParameter("post");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		String y = req.getParameter("y");
		String x = req.getParameter("x");
		
		libraryvo.setLibname(libname);
		libraryvo.setTel(tel);
		libraryvo.setPost(post);
		libraryvo.setAddr1(addr1);
		libraryvo.setAddr2(addr2);
		libraryvo.setY(y);
		libraryvo.setX(x);

		MultipartFile imgFile = libraryvo.getImgFile();
	
		String newFileName="";
		
		byte[] bytes = null;
		
		long fileSize= 0;
		
		//단일파일
		if(!imgFile.isEmpty()) {
			// imgFile(첨부파일) 있는경우 
			String path = "C:\\Users\\user1\\git\\Ananas2\\FINAL\\src\\main\\webapp\\resources\\NSYfiles";
			
			try {
				
				bytes = imgFile.getBytes();
				
				newFileName = fileManager.doFileUpload(bytes, imgFile.getOriginalFilename(), path);
				
				libraryvo.setFileName(newFileName);
				libraryvo.setOrgFilename(imgFile.getOriginalFilename());
				fileSize = imgFile.getSize();
				libraryvo.setFileSize(String.valueOf(fileSize));
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}// end of if
		else { 
			// imgFile(첨부파일) 없는경우
			newFileName = "none";
			fileSize = 0;
			
			libraryvo.setFileName(newFileName);
			libraryvo.setOrgFilename(newFileName);
			libraryvo.setFileSize(String.valueOf(fileSize));
		
		}// end of if else
		
		int result = service.resisterLibraryEnd(libraryvo);
		
		String loc = "";
		
		if(result==1) {
			loc = req.getContextPath()+"/libraryList.ana";
		}
		else{
			loc = req.getContextPath()+"/resisterLibrary.ana";
		}
		
		req.setAttribute("n", result);
		req.setAttribute("loc", loc);
		
		return "NSYresisterLibraryEnd.notiles";
		
	}// end of resisterLibrary()
	
	//==== 도서관목록페이지 보여주기 (페이징,검색 가능) ====
	@RequestMapping(value="/libraryList.ana", method={RequestMethod.GET})
	public String libraryList(HttpServletRequest req, HttpServletResponse res) {
		
		return "library/NSYlibraryList.tiles1";
		
	}// end of resisterLibrary()
	
	//==== 도서관목록 가져오기 ====
	@RequestMapping(value="/getLibraryList.ana", method={RequestMethod.GET})
	@ResponseBody
	public List<HashMap<String, Object>> getLibraryList(HttpServletRequest req, HttpServletResponse res){
		
		List<HashMap<String, Object>> resultMapList = new ArrayList<HashMap<String, Object>>();
		
		String colname = req.getParameter("colname");
		String searchWord = req.getParameter("searchWord");
		String currentShowPageNo = req.getParameter("currentShowPageNo");
		String sizePerPage = req.getParameter("sizePerPage");
		
		if(currentShowPageNo==null || "".equals(currentShowPageNo)) {
			currentShowPageNo= "1";
		}
		
		int rno1 = Integer.parseInt(currentShowPageNo) * Integer.parseInt(sizePerPage) - (Integer.parseInt(sizePerPage)-1); // 공식 !!!
		int rno2 = Integer.parseInt(currentShowPageNo) * Integer.parseInt(sizePerPage); // 공식 !!!
		
		HashMap<String,String> paraMap =new HashMap<String,String>();
		paraMap.put("rno1", String.valueOf(rno1));
		paraMap.put("rno2", String.valueOf(rno2));
		paraMap.put("colname", colname);
		paraMap.put("searchWord", searchWord);
		
		List<NSYLibraryVO> libraryList = service.getLibraryList(paraMap);
		
		for(NSYLibraryVO librarMap : libraryList) {
			
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			
			resultMap.put("libcode", librarMap.getLibcode());
			resultMap.put("idx", librarMap.getIdx());
			resultMap.put("libname", librarMap.getLibname());
			resultMap.put("post",librarMap.getPost());
			resultMap.put("addr1", librarMap.getAddr1());
			resultMap.put("addr2", librarMap.getAddr2());
			resultMap.put("tel", librarMap.getTel());
			resultMap.put("fileName", librarMap.getFileName());
			resultMap.put("fileSize", librarMap.getFileSize());
			resultMap.put("imgFile", librarMap.getImgFile());
			resultMap.put("regDate", librarMap.getRegDate());
			resultMap.put("y", librarMap.getY());
			resultMap.put("x", librarMap.getX());
			
			resultMapList.add(resultMap);
		}
		
		return resultMapList;
	}
	
	//==== 도서관목록 페이지바 만들기 기능 ====
	@RequestMapping(value="/getMakeBarPage", method={RequestMethod.GET})
	@ResponseBody
	public HashMap<String, Integer> getMakeBarPage(HttpServletRequest req, HttpServletResponse res){
		
		HashMap<String, Integer> returnMap = new HashMap<String,Integer>();
		
		String colname = req.getParameter("colname");
		String searchWord = req.getParameter("searchWord");
		String sizePerPage = req.getParameter("sizePerPage");
		String currentShowPageNo = req.getParameter("currentShowPageNo");
		
		if(currentShowPageNo==null || "".equals(currentShowPageNo)) {
			currentShowPageNo= "1";
		}
		
		HashMap<String,String > paraMap = new HashMap<String,String>();
		paraMap.put("sizePerPage", sizePerPage);
		paraMap.put("colname", colname);
		paraMap.put("searchWord", searchWord);
		
		// ==== 도서관 전체 갯수를 구해오기 ====
		int totalCount = service.getLibraryTotalList(paraMap);
		
		int totalPage = (int)Math.ceil((double)totalCount/Integer.parseInt(sizePerPage));
		
		returnMap.put("totalPage", totalPage);
		
		return returnMap;
	}
	
	// ==== 파업창을 이용한 도서관상세정보 보기 ====
	@RequestMapping(value="/libraryDetailInfo.ana", method={RequestMethod.GET})
	public String getLibraryDetailInfo(HttpServletRequest req, HttpServletResponse res) {
		
		String idx = req.getParameter("idx");
		
		NSYLibraryVO libraryDetailInfo = service.getLibraryDetailInfo(idx);
		
		req.setAttribute("idx", idx);
		req.setAttribute("libraryDetailInfo", libraryDetailInfo);
		
		return "NSYlibraryDetailInfo.notiles";
	}
	
	// ==== 도서관수정페이지 보여주기 (dao, service 도서관상세정보 보기랑 같이사용함)====
	@RequestMapping(value="/editLibraryInfo.ana", method= {RequestMethod.GET})
	public String editLibraryInfo(HttpServletRequest req, HttpServletResponse res) {
		
		String idx = req.getParameter("idx");
		
		NSYLibraryVO libraryDetailInfo = service.getLibraryDetailInfo(idx);
		
		req.setAttribute("idx", idx);
		req.setAttribute("libraryDetailInfo", libraryDetailInfo);
		
		return "NSYeditLibraryInfo.notiles";
	}
	
	// ==== 도서관정보 수정 완료 하기 ====
	@RequestMapping(value="/editLibraryInfoEnd.ana", method= {RequestMethod.POST})
	public String editLibraryInfoEnd(MultipartHttpServletRequest req, HttpServletResponse res, NSYLibraryVO libraryvo) {
		
		String idx = req.getParameter("idx");
		String libname = req.getParameter("libname");
		String tel = req.getParameter("tel");
		String post = req.getParameter("post");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		String y = req.getParameter("y");
		String x = req.getParameter("x");
		String oldFileName = req.getParameter("fileName");	
		
		libraryvo.setIdx(idx);
		libraryvo.setLibname(libname);
		libraryvo.setTel(tel);
		libraryvo.setPost(post);
		libraryvo.setAddr1(addr1);
		libraryvo.setAddr2(addr2);
		libraryvo.setY(y);
		libraryvo.setX(x);
		
		MultipartFile imgFile = libraryvo.getImgFile();
		
		String newFileName="";
		
		byte[] bytes = null;
		
		long fileSize= 0;
		
		int result = 0;
		
		if(!imgFile.isEmpty()) { 
			// imgFile(첨부파일) 있는경우 
			String path = "C:\\Users\\user1\\git\\Ananas2\\FINAL\\src\\main\\webapp\\resources\\NSYfiles";
			
			// ==== 기존 파일 삭제처리===================
			// 첨부파일을 다시 새파일을 업로드할때 기존파일명을 삭제처리한다.
			File deleteOldFile = new File(path+"\\"+oldFileName);
			
			if(deleteOldFile.exists()) { //기존파일명 여부확인
				deleteOldFile.delete(); //파일삭제
			}
			else {
				System.out.println("(확인용)파일이 존재하지 않습니다.");
			}
			// ==== 기존 파일 삭제처리 끝===================
			try {
				bytes = imgFile.getBytes();
			
				newFileName = fileManager.doFileUpload(bytes, imgFile.getOriginalFilename(), path);
				
				libraryvo.setFileName(newFileName);
				libraryvo.setOrgFilename(imgFile.getOriginalFilename());
				fileSize = imgFile.getSize();
				libraryvo.setFileSize(String.valueOf(fileSize));
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			result = service.editLibraryInfoEnd(libraryvo);
			
		}// end of if
		else { 
			// imgFile(첨부파일) 없는경우
			result = service.editLibraryInfoEnd_noImg(libraryvo);
		
		}// end of if else
		
		String loc = "";
		
		if(result==1) {
			loc = req.getContextPath()+"/libraryList.ana";
		}
		else{
			loc = req.getContextPath()+"/libraryList.ana";
		}
		
		req.setAttribute("n", result);
		req.setAttribute("loc", loc);
		
		return "NSYeditLibraryInfoEnd.notiles";
	}
	
}//end of class NSYLibraryController
