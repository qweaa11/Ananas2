package com.spring.bookmanage.library.Yjkcontroller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.bookmanage.JDSmodel.AdminVO;
import com.spring.bookmanage.JDSmodel.LibrarianVO;
import com.spring.bookmanage.common.AES256;
import com.spring.bookmanage.common.FileManager;
import com.spring.bookmanage.common.SHA256;
import com.spring.bookmanage.library.Yjkmodel.LibraryVO;
import com.spring.bookmanage.library.Yjkmodel.YjkVO;
import com.spring.bookmanage.library.Yjkservice.InterYjkService;

@Controller
public class YjkController {
	
	//==== 의존객체 주입하기(DI : Dependency Injection)  ====
	@Autowired
	private InterYjkService service;
	
	//===== 양방향 암호화 알고리즘인 AES256을 사용하여 암호화/복호화 하기 위한 클래스 의존객체 주입하기(DI : Dependency Injection)  =====
	@Autowired
	private AES256 aes;
	
	// ==== 파일업로드 및 파일 다운로드를 해주는 FileManager 클래스 의존객체 주입하기 ====
	@Autowired
	private FileManager fileManager;
	
	// ==== 관리자 등록 페이지 보여주기 ==== //
	@RequestMapping(value="/librarianRegist.ana",method= {RequestMethod.GET})
	public String librarianRegist(HttpServletRequest req, HttpServletResponse response) {

		List<LibraryVO> libInfo = service.getliblibrary(); // DB에서 가져온 도서관 정보를 리스트로 넣기
		
		req.setAttribute("libInfo", libInfo);
		
		HttpSession session = req.getSession();
		LibrarianVO loginLibrarian = (LibrarianVO)session.getAttribute("loginLibrarian"); // 사서등록 페이지로 이동시키기 위해 session에 저장되어 있는 사서 로그인 여부 확인하기
		AdminVO loginAdmin = (AdminVO)session.getAttribute("loginAdmin"); // 관리자등록 페이지로 이동시키기 위해 session에 저장되어 있는 관리자 로그인 여부 확인하기
		
		if(loginLibrarian != null && loginLibrarian.getStatus()==1) { // status 1은 도서관장 도서관장이라면 사서등록 페이지로 이동
			return "library/librarianRegist2.tiles1";
		}else if(loginAdmin != null) { // 총 관리자로 접속시 관리자 등록 페이지 이동
			return "library/librarianRegist.tiles1"; 
		}else if(loginLibrarian != null && loginLibrarian.getStatus()==0) { // 사서라면 메세지 출력
			
			String msg = "사서는 접근권한이 없습니다.";
			String loc = "javascript:history.back();";
			
			req.setAttribute("msg", msg);
			req.setAttribute("loc", loc);
			
			return "msg";
		}else {
			String msg = "로그인 후에 이용가능합니다.";
			String loc = "javascript:history.back();";
			
			req.setAttribute("msg", msg);
			req.setAttribute("loc", loc);
			
			return "msg";
		}
		
		
		/*return "library/librarianRegist.tiles1";*/
		
	}
	
	// ==== 관리자 등록하기 ==== //
	@RequestMapping(value="/librarianRegistEnd.ana",method= {RequestMethod.POST})
	public String adminRegistEnd(MultipartHttpServletRequest req,HttpServletResponse response, YjkVO adminvo) {
		
		int n = 0;
		
		try {
		// view 페이지에서 관리자 등록을 위한 정보 값 받아오기
		String libid = req.getParameter("libid");
		String pwd = req.getParameter("pwd");
		String name = req.getParameter("name");
		String tel = req.getParameter("tel");
		String libcode = req.getParameter("libcode");
		String status = req.getParameter("status");
		String email = req.getParameter("email");

		/*System.out.println(libid);
		System.out.println(pwd);
		System.out.println(name);
		System.out.println(tel);
		System.out.println(libcode);
		System.out.println(status);*/
		
		MultipartFile attach = adminvo.getAttach(); // 파일첨부를 위해
		
		if(attach.isEmpty()) {
			adminvo.setImgFileName("NONE");
		}
		else {
			
			byte[] bytes = null;
			String imgFileName = "";
			String fatternName = "";
			
			HttpSession session = req.getSession();
	
			String root = session.getServletContext().getRealPath("/");
			
			System.out.println("root : " + root);
			
			String path = root + "resources"+File.separator+"librarian";
			System.out.println("path : "+ path);
			
			bytes = attach.getBytes();
			
			imgFileName = fileManager.doFileUpload(bytes, attach.getOriginalFilename(), path);
			
			
			
			System.out.println("recordPicName : " + imgFileName);
			
			adminvo.setImgFileName(imgFileName);
			
		}
		
		adminvo.setLibid(libid);
		adminvo.setPwd(SHA256.encrypt(pwd));
		adminvo.setName(name);
		adminvo.setTel(tel);
		adminvo.setLibcode_fk(libcode);
		adminvo.setStatus(status);
		adminvo.setEmail(email);
		
		n = service.adminRegistEnd(adminvo);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(n == 1) {
			
			String msg = "회원가입 되었습니다.";
			String loc = "index.ana";
			
			req.setAttribute("msg", msg);
			req.setAttribute("loc", loc);
			
			return "msg";
					
		}
		else {
			
			String msg = "회원가입 실패하였습니다.";
			String loc = "javascript:history.back()";
			
			req.setAttribute("msg", msg);
			req.setAttribute("loc", loc);
			
			return "msg";
		}

	}// end of public String adminRegistEnd()--------------
	
	// ==== 아이디 중복체크 ==== //
	@RequestMapping(value="/idDuplicateCheck.ana",method= {RequestMethod.GET})
	@ResponseBody
	public HashMap<String, Integer> idDuplicateCheck(HttpServletRequest req,HttpServletResponse response) {
		
		String libid = req.getParameter("libid");
		
		int n = service.idDuplicateCheck(libid);

		HashMap<String, Integer> returnMap = new HashMap<String, Integer>();
		
		returnMap.put("n", n);
		
		return returnMap;
	}
	
}
