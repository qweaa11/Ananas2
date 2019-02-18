package com.spring.bookmanage.book.KKHservice;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bookmanage.book.KKHmodel.InterKKHBookDAO;
import com.spring.bookmanage.book.KKHmodel.KKHBookVO;

@Service
public class KKHBookService implements InterKKHBookService{
	
	@Autowired
	private InterKKHBookDAO bookdao;
	
	@Override
	//DB에 있는 도서관정보와 해당 도서관에 있는 책수를 가져오는 메소드
	public List<HashMap<String, String>> findAllLibrary(HashMap<String,String> libcode) {
		List<HashMap<String,String>> libraryList = bookdao.findAllLibrary(libcode);
		return libraryList;
	}
	
	@Override
	//DB에 있는 모든 언어(Language테이블)정보와 해당 언어를 가진 책 갯수를 가져오는 메소드
	public List<HashMap<String, String>> findAllLanguage(HashMap<String,String> libcode) {
		List<HashMap<String,String>> languageList = bookdao.findAllLanguage(libcode);
		return languageList;
	}
	@Override
	//DB에 있는 모든 종류(Category 테이블)정보와 해당 종류를 가진 책 갯수를 가져오는 메소드
	public List<HashMap<String, String>> findAllCategory(HashMap<String,String> libcode) {
		List<HashMap<String,String>> categoryList = bookdao.findAllCategory(libcode);
		return categoryList;
	}
	@Override
	//DB에 있는 모든 분야(field 테이블) 정보와 해당 분야를 가진 책 갯수를 가져오는 메소드
	public List<HashMap<String, String>> findAllField(HashMap<String,String> libcode) {
		List<HashMap<String,String>> fieldList = bookdao.findAllField(libcode);
		return fieldList;
	}
	
	
	@Override
	//사이드바에서 넘긴 값들을 파라미터에 담고,  담긴 LIBRARY, CATEGORY, FIELD, LANGUAGE,SORT,LIBCODE(사서 접속시) 값을 이용해 도서 리스트를 검색하는 메소드
	public List<KKHBookVO> findBookBysidebar(HashMap<String, Object> parameterMap) {
		List<KKHBookVO> bookList = bookdao.findBookBysidebar(parameterMap);
		return bookList;
	}

	@Override
	//검색창에서 입력한 정보(searchType,searchWord,sort) 들을 사용해 도서리스트를 검색하는 메소드
	public List<KKHBookVO> findBookBySearchbar(HashMap<String, String> parameterMap) {
		List<KKHBookVO> bookList = bookdao.findBookBysearchbar(parameterMap);
		return bookList;
	}

	@Override
	/*도서 상세로 가는 메소드 리스트 인 이유는 해당 도서번호 'L1000KRE02530UN-1' 를 가진 모든 책들을 가져오기 때문*/
	public List<KKHBookVO> findBookDetail(String bookid) {
		List<KKHBookVO> bookDetail = bookdao.findBookDetail(bookid);
		return bookDetail;
	}

	@Override
	//도서 상세 페이지에 있는 책중 예약된 책들의 예약정보리스트를 가져오는 메소드, 이때 bookid 는 두번째 하이푼(-)이전 까지의 코드이다. ex)L1000KRE02530UN-1
	public List<HashMap<String,String>> findBookReservateList(String bookid) {
		List<HashMap<String,String>> bookReservateList = bookdao.findBookReservateList(bookid);
		return bookReservateList;
	}
	
//도서 수정을 위한 항목별 정보 가져오기 시작
		@Override
		public List<HashMap<String, String>> findcategory() {
			List<HashMap<String,String>> categoryList = bookdao.findcategory();
			return categoryList;
		}
		
		@Override
		public List<HashMap<String, String>> findlanguage() {
			List<HashMap<String,String>> languageList = bookdao.findlanguage();
			return languageList;
		}
		
		@Override
		public List<HashMap<String, String>> findgenre() {
			List<HashMap<String,String>> genreList = bookdao.findgenre();
			return genreList;
		}
		
		@Override
		//분야별 큰 항목(000, 100, 200,.....900)을 가져오는 메소드
		public List<HashMap<String, String>> findfield() {
			List<HashMap<String,String>> fieldList = bookdao.findfield();
			return fieldList;
		}
		
		@Override
		//큰 항목에 따른 분야별 작은 항목(110,120,130,....190)등을 가져오는 메소드
		public List<HashMap<String, String>> findDetailField(String bigfcode) {
			List<HashMap<String,String>> detailFieldList = bookdao.findDetailField(bigfcode);
			return detailFieldList;
		}
//도서 수정을 위한 항목별 정보 가져오기 끝
	
	@Override
	//도서 수정을 위해 변경할 정보와 비교하기 위한 기존정보를 가져오는 메소드
	public KKHBookVO findOneBook(String bookid) {
		KKHBookVO book = bookdao.findOneBook(bookid);
		return book;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor={Throwable.class})
	//도서일련번호를 새로 생성해서 변경할 책들에게 부여한뒤 책정보를 변경해주는 메소드
	public int editBookPlzChangeBookid(HashMap<String, String> parameterMap, KKHBookVO book) {
		int result = 0;
		int n2 = 0;
		//도서일련번호 ==>> LIB+N+L+C+F+G -큰번호- 개별번호
		String newBookid = parameterMap.get("EDITLIBRARY")+parameterMap.get("EDITNATION")+parameterMap.get("EDITLANGUAGE")+parameterMap.get("EDITCATEGORY")+parameterMap.get("EDITFIELD")+parameterMap.get("EDITGENRE");
		
		//큰번호를 채번해오는 메소드
		String newBookFirstNum = bookdao.findNewBook1stNum(newBookid);
		//System.out.println("firstNum:"+newBookFirstNum);
		
		newBookid += "-"+newBookFirstNum; //ex) 결과 --> L1000KRE02530UN-1
		parameterMap.put("NEWBOOKID", newBookid);
		
		//수정할 도서의 book_detail 테이블의 정보를 가져온뒤 delete 해주는 메소드
		//이러는 이유는 book_detail의 bookid가 book 테이블의 bookid에 foreign key 이기때문에 삭제전에는 수정이 안되기 때문.
		//새롭게 bookid를 부여해주기 위해 book_detail테이블의 수정하려는 도서정보를 List<KKHBookVO>에 저장한뒤 삭제해준다.
		List<KKHBookVO> bookDetailList = bookdao.selectAndDelBookDetail(parameterMap.get("BOOKID"));
		for(int i=0; i<bookDetailList.size();i++) {
			String oldBookid = bookDetailList.get(i).getBookid();//기존 book_detail테이블의 bookid
			//System.out.println("oldBookid:"+oldBookid);
			
			//위에서 생성한 새로운 newbookid값에 기존 책의 개별번호 (L1000KRE02530UN-1-5 중 5에 해당하는 번호) 를 붙여서 저장해준다.   
			newBookid = parameterMap.get("NEWBOOKID")+oldBookid.substring(oldBookid.indexOf("-",16));
			bookDetailList.get(i).setBookid(newBookid);
			
		}
		//book 테이블에 있는 수정하려는 도서들에 새롭게 만든 bookid를 update 해준다.
		int n1 = bookdao.updateNewBookid(parameterMap);
		
		//book_detail 테이블에 새롭게 부여한 bookid와 기존정보를 insert 해준다.
		bookdao.insertNewBookDetail(bookDetailList);
		if(parameterMap.get("EDITIMAGE")!=null) {
			//이미지 파일을 변경했을 경우 book_detail 테이블에 image 값을 update 해준다.
			n2 = bookdao.updateNewBookDetail(parameterMap);
		}else {
			n2 = Integer.parseInt(parameterMap.get("LENGTH"));
		}
		
		if(n1-n2 == 0) result = 1;
		return result;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor={Throwable.class})
	//도서 일련번호 없이 도서공용정보를 수정할때는 작가, 저자, 연령 정보만 변경한다.
	public int eidtBookNoChangeBookid(HashMap<String, String> parameterMap, KKHBookVO book) {
		int result = 0;
		//수정하려는 도서들의 book 테이블의 정보를 update해준다.
		int n1 = bookdao.updateBookInfo(parameterMap);
		int n2 = 0;
		if(parameterMap.get("EDITIMAGE")!=null) {
			//이미지 파일을 변경했을경우 book_detail 테이블의 image컬럼값을 update 해준다.
			n2 = bookdao.updateBookDetail(parameterMap);
		}else {
			n2 =Integer.parseInt(parameterMap.get("LENGTH"));
		}
		//System.out.println("n1:"+n1+",n2:"+n2);
		if(n1-n2 == 0 ) result = 1;
		
		return result;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor={Throwable.class})
	//개별 도서정보를 수정하는 메소드
	public int editIndivBookInfo(HashMap<String, String> parameterMap) {
		int n = bookdao.editIndivBookInfo(parameterMap);
		return n;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor={Throwable.class})
	//개별 도서를 테이블에서 삭제하는 메소드
	public int deleteIndivBook(String bookid) {
		int n= bookdao.deleteIndivBook(bookid);
		return n;
	}
	
	@Override
	//추가될 도서의 시작일련번호를 채번해오는 메소드
	public int findStartBookNum(String bookid) {
		int startBookNum = bookdao.findStartBookNum(bookid);
		return startBookNum;
	}
	
	@Override
	//추가될 도서에 입력될 도서정보를 해당 도서번호를 가진 책들중 첫번째 책에서 가져오는 메소드
	public KKHBookVO findBookInfoSample(String bookid) {
		KKHBookVO bookInfoSample= bookdao.findBookInfoSample(bookid); 
		
		return bookInfoSample;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor={Throwable.class})
	//book테이블,book_detail 테이블에 추가할 도서갯수만큼 insert해주는 메소드
	public int insertAdditionalBook(KKHBookVO bookInfoSample, HashMap<String, String> parameterMap) {
		int n = bookdao.insertAdditionalBook(bookInfoSample,parameterMap);
		return n;
	}
	
	@Override
	//삭제할 도서의 정보를 VO에 저장하는 메소드
	public List<KKHBookVO> findDeleteBook(String bookid) {
		List<KKHBookVO> deleteBookList = bookdao.findDeleteBook(bookid);
		return deleteBookList;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	//delete_book 테이블에 가져온 도서정보를 insert 한뒤 book, book_detail 테이블에서 해당 도서의 정보를 삭제하는 메소드
	public int insertAndDeleteBookList(List<KKHBookVO> deleteBookList,String bookid,String cleanerid) {
		//delete_book 테이블에 삭제할 도서정보를 isnert 해주는 메소드
		int n1 = bookdao.insertDelete_BookList(deleteBookList,cleanerid);
		
		//book, book_detail 테이블에서 해당 도서번호를 가진 모든 책을 delete 해주는 메소드
		int n2 = bookdao.deleteBookAndBookDetail(bookid);
		if(n1 == n2 ) return 1;
		else return 0;
		
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int updateDeadline(String[] extendBookArr) {
		int n=0;
		String[] extendSuccessBookArr = new String[extendBookArr.length];
		for(int i=0; i<extendBookArr.length;i++) {
			
			//도서 반납예정일을 +7연장해주는 메소드
			n += bookdao.updateDeadline(extendBookArr[i]);
			
				
		}
		if(n == extendBookArr.length) return 1;
		else return 0;
	}
	/*@Override
	public int updateDeadline(String extendBookid) {
		int n = bookdao.updateDeadline(extendBookid);
		return n;
	}*/
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public String[] returnBook(String[] returnBookidArr) {
		String[] returnSuccessBookArr = new String[returnBookidArr.length];
		//List<HashMap<String,String>> rentalBookInfoList = new ArrayList<HashMap<String,String>>();
		for(int i =0; i<returnBookidArr.length;i++) {
			System.out.println("returnBookid1:"+returnBookidArr[i]);
			
			//대여 되어있는 도서의 정보를 가져오는 메소드
			HashMap<String,String> rentalBookInfo = bookdao.findRentalBook(returnBookidArr[i]);
			
			//returned 테이블에 insert하는 메소드
			int n1 = bookdao.insertReturnedBook(rentalBookInfo);
			//도서가 반납될때 예약이 되어있는 책일경우 status = 1 아닐경우 status = 0 으로 update해주는 메소드
			int n2 = bookdao.updateReturnedBookStatus(rentalBookInfo);
			int latedate = Integer.parseInt(rentalBookInfo.get("LATEDATE"));
			int n3= 1;
			if(latedate >0) {
				//도서가 연체되었을 경우 회원정보에 정지일과 연체료를 추가해주는 메소드
				n3 = bookdao.updateLateMemberInfo(rentalBookInfo);
			}
			//rental 테이블에서 대여된 도서 정보를 delete 해주는 메소드
			int n4 = bookdao.deleteRentalBook(returnBookidArr[i]);
			if(n1*n2*n3*n4 == 1) {
				returnSuccessBookArr[i] = returnBookidArr[i];
			}
		}
		
		return returnSuccessBookArr;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int reserveCancel(String cancelBookid) {
		//예약된 도서정보를 reserve 테이블에서 delete해주는 메소드
		int n = bookdao.reserveCancel(cancelBookid);
		return n;
	}
	

	

}
