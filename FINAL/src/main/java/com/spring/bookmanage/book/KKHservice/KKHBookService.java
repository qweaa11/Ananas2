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
	public List<HashMap<String, String>> findAllLibrary(HashMap<String,String> libcode) {
		List<HashMap<String,String>> libraryList = bookdao.findAllLibrary(libcode);
		return libraryList;
	}
	@Override
	public List<HashMap<String, String>> findAllLanguage(HashMap<String,String> libcode) {
		List<HashMap<String,String>> languageList = bookdao.findAllLanguage(libcode);
		return languageList;
	}
	@Override
	public List<HashMap<String, String>> findAllCategory(HashMap<String,String> libcode) {
		List<HashMap<String,String>> categoryList = bookdao.findAllCategory(libcode);
		return categoryList;
	}
	@Override
	public List<HashMap<String, String>> findAllField(HashMap<String,String> libcode) {
		List<HashMap<String,String>> fieldList = bookdao.findAllField(libcode);
		return fieldList;
	}
	
	
	@Override
	public List<KKHBookVO> findBookBysidebar(HashMap<String, Object> parameterMap) {
		List<KKHBookVO> bookList = bookdao.findBookBysidebar(parameterMap);
		return bookList;
	}

	@Override
	public List<KKHBookVO> findBookBySearchbar(HashMap<String, String> parameterMap) {
		List<KKHBookVO> bookList = bookdao.findBookBysearchbar(parameterMap);
		return bookList;
	}

	@Override
	public List<KKHBookVO> findBookDetail(String bookid) {
		List<KKHBookVO> bookDetail = bookdao.findBookDetail(bookid);
		return bookDetail;
	}

	@Override
	public List<HashMap<String,String>> findBookReservateList(String bookid) {
		List<HashMap<String,String>> bookReservateList = bookdao.findBookReservateList(bookid);
		return bookReservateList;
	}
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
	public List<HashMap<String, String>> findfield() {
		List<HashMap<String,String>> fieldList = bookdao.findfield();
		return fieldList;
	}
	@Override
	public List<HashMap<String, String>> findDetailField(String bigfcode) {
		List<HashMap<String,String>> detailFieldList = bookdao.findDetailField(bigfcode);
		return detailFieldList;
	}
	@Override
	public KKHBookVO findOneBook(String bookid) {
		KKHBookVO book = bookdao.findOneBook(bookid);
		return book;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor={Throwable.class})
	public int editBookPlzChangeBookid(HashMap<String, String> parameterMap, KKHBookVO book) {
		int result = 0;
		int n2 = 0;
		// LIB+N+L+C+F+G - 큰번호- 개별번호
		String newBookid = parameterMap.get("EDITLIBRARY")+parameterMap.get("EDITNATION")+parameterMap.get("EDITLANGUAGE")+parameterMap.get("EDITCATEGORY")+parameterMap.get("EDITFIELD")+parameterMap.get("EDITGENRE");
		String newBookFirstNum = bookdao.findNewBook1stNum(newBookid);
		//System.out.println("firstNum:"+newBookFirstNum);
		newBookid += "-"+newBookFirstNum; 
		parameterMap.put("NEWBOOKID", newBookid);
		List<KKHBookVO> bookDetailList = bookdao.selectAndDelBookDetail(parameterMap.get("BOOKID"));
		for(int i=0; i<bookDetailList.size();i++) {
			String oldBookid = bookDetailList.get(i).getBookid();
			//System.out.println("oldBookid:"+oldBookid);
			newBookid = parameterMap.get("NEWBOOKID")+oldBookid.substring(oldBookid.indexOf("-",16));
			bookDetailList.get(i).setBookid(newBookid);
			
			//System.out.println("bookid:"+newBookid);
			
		}
		int n1 = bookdao.updateNewBookid(parameterMap);
		bookdao.insertNewBookDetail(bookDetailList);
		if(parameterMap.get("EDITIMAGE")!=null) {
			n2 = bookdao.updateNewBookDetail(parameterMap);
		}else {
			n2 = Integer.parseInt(parameterMap.get("LENGTH"));
		}
		
	
		if(n1-n2 == 0) result = 1;
		return result;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor={Throwable.class})
	public int eidtBookNoChangeBookid(HashMap<String, String> parameterMap, KKHBookVO book) {
		int result = 0;
		int n1 = bookdao.updateBookInfo(parameterMap);
		int n2 = 0;
		if(parameterMap.get("EDITIMAGE")!=null) {
			n2 = bookdao.updateBookDetail(parameterMap);
		}else {
			n2 =Integer.parseInt(parameterMap.get("LENGTH"));
		}
		System.out.println("n1:"+n1+",n2:"+n2);
		if(n1-n2 == 0 ) result = 1;
		
		return result;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor={Throwable.class})
	public int editIndivBookInfo(HashMap<String, String> parameterMap) {
		int n = bookdao.editIndivBookInfo(parameterMap);
		return n;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor={Throwable.class})
	public int deleteIndivBook(String bookid) {
		int n= bookdao.deleteIndivBook(bookid);
		return n;
	}
	@Override
	public KKHBookVO findBookInfoSample(String bookid) {
		KKHBookVO bookInfoSample= bookdao.findBookInfoSample(bookid); 
		
		return bookInfoSample;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor={Throwable.class})
	public int insertAdditionalBook(KKHBookVO bookInfoSample, HashMap<String, String> parameterMap) {
		int n = bookdao.insertAdditionalBook(bookInfoSample,parameterMap);
		return n;
	}
	@Override
	public int findStartBookNum(String bookid) {
		int startBookNum = bookdao.findStartBookNum(bookid);
		return startBookNum;
	}
	@Override
	public List<KKHBookVO> findDeleteBook(String bookid) {
		List<KKHBookVO> deleteBookList = bookdao.findDeleteBook(bookid);
		return deleteBookList;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int insertAndDeleteBookList(List<KKHBookVO> deleteBookList,String bookid,String cleanerid) {
		int n1 = bookdao.insertDelete_BookList(deleteBookList,cleanerid);
		int n2 = bookdao.deleteBookAndBookDetail(bookid);
		if(n1 == n2 ) return 1;
		else return 0;
		
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public String[] updateDeadline(String[] extendBookArr) {
		String[] extendSuccessBookArr = new String[extendBookArr.length];
		for(int i=0; i<extendBookArr.length;i++) {
			int n = bookdao.updateDeadline(extendBookArr[i]);
			if(n == 1) {
				extendSuccessBookArr[i] = extendBookArr[i];
			}
				
		}
		
		return extendSuccessBookArr;
	}
	@Override
	public int updateDeadline(String extendBookid) {
		int n = bookdao.updateDeadline(extendBookid);
		return n;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public String[] returnBook(String[] returnBookidArr) {
		String[] returnSuccessBookArr = new String[returnBookidArr.length];
		//List<HashMap<String,String>> rentalBookInfoList = new ArrayList<HashMap<String,String>>();
		for(int i =0; i<returnBookidArr.length;i++) {
			System.out.println("returnBookid1:"+returnBookidArr[i]);
			HashMap<String,String> rentalBookInfo = bookdao.findRentalBook(returnBookidArr[i]);
			int n1 = bookdao.insertReturnedBook(rentalBookInfo);
			int n2 = bookdao.updateReturnedBookStatus(rentalBookInfo);
			int latedate = Integer.parseInt(rentalBookInfo.get("LATEDATE"));
			int n3= 1;
			if(latedate >0) {
				n3 = bookdao.updateLateMemberInfo(rentalBookInfo);
			}
			int n4 = bookdao.deleteRentalBook(returnBookidArr[i]);
			if(n1*n2*n3*n4 == 1) {
				returnSuccessBookArr[i] = returnBookidArr[i];
			}
		}
		
		return returnSuccessBookArr;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int returnBook(String returnBookid) {
		System.out.println("returnBookid2:"+returnBookid);
		HashMap<String,String> rentalBookInfo = bookdao.findRentalBook(returnBookid);
		int n1 = bookdao.insertReturnedBook(rentalBookInfo);
		int n2 = bookdao.updateReturnedBookStatus(rentalBookInfo);
		int latedate = Integer.parseInt(rentalBookInfo.get("LATEDATE"));
		int n3= 1;
		if(latedate >0) {
			n3 = bookdao.updateLateMemberInfo(rentalBookInfo);
		}
		int n4 = bookdao.deleteRentalBook(returnBookid);
		if(n1*n2*n3*n4 == 1) return 1;
		else return 0;
	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor= {Throwable.class})
	public int reserveCancel(String cancelBookid) {
		int n = bookdao.reserveCancel(cancelBookid);
		return n;
	}
	

	

}
