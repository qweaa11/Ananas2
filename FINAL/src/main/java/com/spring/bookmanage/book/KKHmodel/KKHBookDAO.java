package com.spring.bookmanage.book.KKHmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class KKHBookDAO implements InterKKHBookDAO {
	
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	@Override
	public List<HashMap<String, String>> findAllLibrary(HashMap<String,String> libcode) {
		List<HashMap<String,String>> libraryList = sqlsession.selectList("KKH.findAllLibrary",libcode);
		return libraryList;
	}  
	@Override
	public List<HashMap<String, String>> findAllLanguage(HashMap<String,String> libcode) {
		List<HashMap<String,String>> languageList = sqlsession.selectList("KKH.findAllLanguage",libcode);
		return languageList;
	}

	@Override
	public List<HashMap<String, String>> findAllCategory(HashMap<String,String> libcode) {
		List<HashMap<String,String>> categoryList = sqlsession.selectList("KKH.findAllCategory",libcode);
		return categoryList;
	}

	@Override
	public List<HashMap<String, String>> findAllField(HashMap<String,String> libcode) {
		List<HashMap<String,String>> fieldList = sqlsession.selectList("KKH.findAllField",libcode);
		return fieldList;
	}
	
	@Override
	public List<KKHBookVO> findBookBysidebar(HashMap<String, Object> parameterMap) {
		System.out.println("library=>"+parameterMap.get("LIBRARY")+",  language=>"+parameterMap.get("LANGUAGE")+",  category=>"+parameterMap.get("CATEGORY")+",  field=>"+parameterMap.get("FIELD"));
		List<KKHBookVO> bookList = sqlsession.selectList("KKH.findBookBysidebar", parameterMap);
		
		return bookList;
	}

	
	@Override
	public List<KKHBookVO> findBookBysearchbar(HashMap<String, String> parameterMap) {
		List<KKHBookVO> bookList = sqlsession.selectList("KKH.findBookBysearchbar", parameterMap);
		return bookList;
	}


	@Override
	public List<KKHBookVO> findBookDetail(String bookid) {
		List<KKHBookVO> bookDetail = sqlsession.selectList("KKH.findBookDetail", bookid);
		return bookDetail;
	}


	@Override
	public List<HashMap<String,String>> findBookReservateList(String bookid) {
		List<HashMap<String,String>> bookReservateList =  sqlsession.selectList("KKH.findBookReservation",bookid);
		return bookReservateList;
	}
	@Override
	public List<HashMap<String, String>> findcategory() {
		List<HashMap<String,String>> categoryList = sqlsession.selectList("KKH.findCategory");
		return categoryList;
	}
	@Override
	public List<HashMap<String, String>> findlanguage() {
		List<HashMap<String,String>> languageList = sqlsession.selectList("KKH.findLanguage");
		return languageList;
	}
	@Override
	public List<HashMap<String, String>> findgenre() {
		List<HashMap<String,String>> genreList = sqlsession.selectList("KKH.findGenre");
		return genreList;
	}
	@Override
	public List<HashMap<String, String>> findfield() {
		List<HashMap<String,String>> fieldList = sqlsession.selectList("KKH.findField");
		return fieldList;
	}
	@Override
	public List<HashMap<String, String>> findDetailField(String bigfcode) {
		List<HashMap<String,String>> detailFieldList = sqlsession.selectList("KKH.findDetailField", bigfcode);
		return detailFieldList;
	}
	@Override
	public KKHBookVO findOneBook(String bookid) {
		List<KKHBookVO> bookList = sqlsession.selectList("KKH.findOneBook", bookid);
		KKHBookVO book= bookList.get(0);
		return book;
	}
	@Override
	public String findNewBook1stNum(String newBookid) {
		
		int result = sqlsession.selectOne("KKH.findNewBook1stNum", newBookid);
		
		return String.valueOf(result);
	}
	
	@Override
	public List<KKHBookVO> selectAndDelBookDetail(String bookid) {
		
		List<KKHBookVO> bookDetailList = sqlsession.selectList("KKH.selectBookDetail", bookid);
		System.out.println("select로 가져옴");
		sqlsession.delete("KKH.deleteBookDetail", bookid);
		System.out.println("삭제됨");
		return bookDetailList;
	}
	
	@Override
	public int updateNewBookid(HashMap<String, String> parameterMap) {
		System.out.println("111");
		int n = sqlsession.update("KKH.updateNewBookid",parameterMap);
		return n;
	}
	
	@Override
	public void insertNewBookDetail(List<KKHBookVO> bookDetailList) {
		for(KKHBookVO bookvo : bookDetailList) {
			System.out.println("bookid:"+bookvo.getBookid()+", idx:"+bookvo.getIdx()+",image:"+bookvo.getImage()+",price:"+bookvo.getPrice()+",weight:"+bookvo.getWeight()+",totalpage:"+bookvo.getTotalpage()+",pdate:"+bookvo.getPdate());
		 sqlsession.insert("KKH.inserNewBookDetail", bookvo);
		}
	}
	
	
	@Override
	public int updateNewBookDetail(HashMap<String, String> parameterMap) {
		System.out.println("222");
		int n = sqlsession.update("KKH.updateNewBookDetail", parameterMap);
		return n;
	}

	
	@Override
	public int updateBookDetail(HashMap<String, String> parameterMap) {
		System.out.println("333");
		int n = sqlsession.update("KKH.updateBookDetail", parameterMap);
		return n;
	}
	@Override
	public int updateBookInfo(HashMap<String, String> parameterMap) {
		System.out.println("444");
		int n = sqlsession.update("KKH.updateBookInfo", parameterMap);
		return n;
	}
	@Override
	public int editIndivBookInfo(HashMap<String, String> parameterMap) {
		int n1 = sqlsession.update("KKH.editIndivBookInfo", parameterMap);
		int n2 = sqlsession.update("KKH.editIndivBookISBN",parameterMap);
		if(n1*n2 == 1) return 1;
		else return 0;	
	}
	@Override
	public int deleteIndivBook(String bookid) {
		int n1 = sqlsession.delete("KKH.deleteIndivBookDetail", bookid);
		int n2 = sqlsession.delete("KKH.deleteIndivBook", bookid);
		if(n1*n2 == 1) return 1;
		else return 0;
	}
	@Override
	public KKHBookVO findBookInfoSample(String bookid) {
		List<KKHBookVO> bookSampleList = sqlsession.selectList("KKH.findBookSampleList", bookid);
		List<KKHBookVO> bookDetailSampleList = sqlsession.selectList("KKH.findBookDetailSampleList",bookid);
		KKHBookVO bookInfoSample = bookSampleList.get(0);
		bookInfoSample.setIntro(bookDetailSampleList.get(0).getIntro());
		bookInfoSample.setImage(bookDetailSampleList.get(0).getImage());
		bookInfoSample.setPrice(bookDetailSampleList.get(0).getPrice());
		bookInfoSample.setWeight(bookDetailSampleList.get(0).getWeight());
		bookInfoSample.setTotalpage(bookDetailSampleList.get(0).getTotalpage());
		bookInfoSample.setPdate(bookDetailSampleList.get(0).getPdate());
		return bookInfoSample;
	}
	@Override
	public int findStartBookNum(String bookid) {
		int startBookNum = sqlsession.selectOne("KKH.findStartBookNum", bookid);
		return startBookNum;
	}
	@Override
	public int insertAdditionalBook(KKHBookVO bookInfoSample, HashMap<String, String> parameterMap) {
		int count = Integer.parseInt(parameterMap.get("COUNT"));
		int n1 = 0;
		int n2 = 0;
		for(int i=0; i<count; i++) {
			bookInfoSample.setBookid(parameterMap.get("BOOKID")+"-"+(Integer.parseInt(parameterMap.get("STARTBOOKNUM"))+i ));
			System.out.println("addBookid:"+bookInfoSample.getBookid());
			n1 +=sqlsession.insert("KKH.insertAdditionalBookInfo", bookInfoSample); 
			n2 +=sqlsession.insert("KKH.insertAdditionalBookDetailInfo", bookInfoSample);
		}
		if(n1-n2 == 0) return 1;
		else return 0;
	}
	@Override
	public List<KKHBookVO> findDeleteBook(String bookid) {
		List<KKHBookVO> deleteBookList = sqlsession.selectList("KKH.deleteBookList", bookid);
		return deleteBookList;
	}
	
	@Override
	public int insertDelete_BookList(List<KKHBookVO> deleteBookList,String cleanerid) {
		int n = 0;
		int delid = sqlsession.selectOne("KKH.selectDelid");
		for(KKHBookVO bookvo : deleteBookList) {
			bookvo.setDelid(delid);
			bookvo.setCleanerid(cleanerid);
			n += sqlsession.insert("KKH.insertDelete_Book", bookvo);
		}
		
		return n;
	}
	@Override
	public int deleteBookAndBookDetail(String bookid) {
		int n2 = sqlsession.delete("KKH.deleteBook_Detail",bookid);
		int n1 = sqlsession.delete("KKH.deleteBook", bookid);
		if(n1 == n2) return n1;
		else return 0;
	}
	@Override
	public int updateDeadline(String extendBookid) {
		int n = sqlsession.update("KKH.updateDeadline", extendBookid);
		return n;
	}
	@Override
	public HashMap<String, String> findRentalBook(String returnBookid) {
		HashMap<String,String> rentalBookInfo = sqlsession.selectOne("KKH.findRentalBook", returnBookid);
		return rentalBookInfo;
	}
	@Override
	public int insertReturnedBook(HashMap<String, String> rentalBookInfo) {
		int n = sqlsession.insert("KKH.insertReturnedBook", rentalBookInfo);
		return n;
	}
	@Override
	public int updateReturnedBookStatus(String returnBookid) {
		int n = sqlsession.update("KKH.updateReturnBOokStatus", returnBookid);
		return n;
	}
	@Override
	public int updateLateMemberInfo(String memberid) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int deleteRentalBook(String returnBookid) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	


	

}
