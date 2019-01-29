package com.spring.bookmanage.board.PMGcontroller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.bookmanage.board.PMGmodel.PMGNoticeVO;
import com.spring.bookmanage.board.PMGservice.PMGBoardService;
import com.spring.bookmanage.common.AES256;
import com.spring.bookmanage.common.FileManager;

@Controller
public class PMGBoardController {

	//===== 의존객체 주입하기(DI : Dependency Injection) =====
	@Autowired
	private PMGBoardService service;
	
	//===== 양방향 암호화 알고리즘인 AES256를 사용하여 암호화/복호화 하기 위한 클래스 의존객체 주입하기 (DI : Dependency Injection) =====
	@Autowired
	private AES256 aes;
	
	//===== 파일업로드 및 파일다운로드를 해주는 FileManager 클래스 의존객체 주입하기 (DI : Dependency Injection) =====
	@Autowired
	private FileManager fileManager;
	
	/*@RequestMapping(value="/login.ana", method= {RequestMethod.GET})
	public String login(HttpServletRequest request) {
		return ""
	}*/
	
	@RequestMapping(value="/noticeList.ana", method= {RequestMethod.GET})
	public String notice(HttpServletRequest request, HttpServletResponse response) {
		
		return "notice/noticeList.tiles1";
	}
	
	// 공지사항 글쓰기
	@RequestMapping(value="/noticeWrite.ana", method= {RequestMethod.GET})
	public String noticeWrite(HttpServletRequest request, HttpServletResponse response) {
		
		return "notice/noticeWrite.tiles1";
	}
	
	// 공지사항 글쓰기 완료
	@RequestMapping(value="/noticeWriteEnd.ana", method= {RequestMethod.GET})
	public String noticeWriteEnd(PMGNoticeVO noticevo, MultipartHttpServletRequest request) {
		
		MultipartFile attach = noticevo.getAttach();
		
		if(!attach.isEmpty()) {
			HttpSession session = request.getSession();
			String root = session.getServletContext().getRealPath("/"); // Context가 프로그램 전체를 말한다. // "/" => webapp
			String path = root + "resources" + File.separator + "files"; // File.separator 운영체제에 따라서 windows는 \ , 나머지(리눅스, 유닉스) /
			// path 가 첨부파일들을 저장할 WAS(톰캣)의 폴더가 된다.
			
			System.out.println("확인용 path ==> "+path);
			
			String newFileName = "";
			// WAS(톰캣) 디스크에 저장할 파일명
			
			byte[] bytes = null;
			// 첨부파일을 WAS(톰캣) 디스크에 저장할때 사용되는 용도
			
			long fileSize = 0;
			// 파일크기를 읽어오기 위한 용도
			
			try {
				bytes = attach.getBytes();
				// getBytes() 는 첨부된 파일을 바이트 단위로 파일을 다 읽어오는 것이다.
				
				newFileName = fileManager.doFileUpload(bytes, attach.getOriginalFilename(), path);
				// 첨부된 파일을 WAS(톰캣)의 디스크로 파일올기리를 하는 것이다.
				// 파일을 올린후 예를 들어, 20190107091235345464324354353467.png 와 같은 파일명을 얻어온다.
				
				System.out.println(">>>> 확인용 newFileName ==> " + newFileName);
				// >>>> 확인용 newFileName ==> 201901071126192667107421104377.png
				
			// 3. BoardVO boardvo 에 fileName 값과 orgFilename 값과 fileSize 값을 넣어주기
				noticevo.setFileName(newFileName);
				noticevo.setOrgFilename(attach.getOriginalFilename());
				
				fileSize = attach.getSize();
				// 첨부한 파일의 크기인데 리턴타입은 long 타입이다.
				
				noticevo.setFileSize(String.valueOf(fileSize));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		int n = 0;
		if(attach.isEmpty()) {
			// 파일첨부가 없다라면
			n = service.noticeWriteadd(noticevo);
		}
		
		else {
			// 파일첨부가 있다라면
			n = service.noticeWriteadd_withFile(noticevo);
		}		
		
		
		String loc = "";
		
		if(n==1) {
			loc = request.getContextPath()+"/list.action";
		}
		else {
			loc = request.getContextPath()+"/index.action";
		}
		
		request.setAttribute("n", n);
		request.setAttribute("loc", loc);
		
		return "notice/noticeWriteEnd.tiles1";
	}
	
	
}
