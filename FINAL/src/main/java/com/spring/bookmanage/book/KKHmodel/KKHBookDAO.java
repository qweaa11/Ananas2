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
	//DB에 있는 도서관정보와 해당 도서관에 있는 책수를 가져오는 메소드
	public List<HashMap<String, String>> findAllLibrary(HashMap<String,String> libcode) {
		List<HashMap<String,String>> libraryList = sqlsession.selectList("KKH.findAllLibrary",libcode);
		return libraryList;
	}  
	@Override
	//DB에 있는 모든 언어(Language테이블)정보와 해당 언어를 가진 책 갯수를 가져오는 메소드
	public List<HashMap<String, String>> findAllLanguage(HashMap<String,String> libcode) {
		List<HashMap<String,String>> languageList = sqlsession.selectList("KKH.findAllLanguage",libcode);
		return languageList;
	}

	@Override
	//DB에 있는 모든 종류(Category 테이블)정보와 해당 종류를 가진 책 갯수를 가져오는 메소드
	public List<HashMap<String, String>> findAllCategory(HashMap<String,String> libcode) {
		List<HashMap<String,String>> categoryList = sqlsession.selectList("KKH.findAllCategory",libcode);
		return categoryList;
	}

	@Override
	//DB에 있는 모든 분야(field 테이블) 정보와 해당 분야를 가진 책 갯수를 가져오는 메소드
	public List<HashMap<String, String>> findAllField(HashMap<String,String> libcode) {
		List<HashMap<String,String>> fieldList = sqlsession.selectList("KKH.findAllField",libcode);
		return fieldList;
	}
	
	@Override
	//사이드바에서 넘긴 값들을 파라미터에 담고,  담긴 LIBRARY, CATEGORY, FIELD, LANGUAGE,SORT,LIBCODE(사서 접속시) 값을 이용해 도서 리스트를 검색하는 메소드
	public List<KKHBookVO> findBookBysidebar(HashMap<String, Object> parameterMap) {
	//	System.out.println("library=>"+parameterMap.get("LIBRARY")+",  language=>"+parameterMap.get("LANGUAGE")+",  category=>"+parameterMap.get("CATEGORY")+",  field=>"+parameterMap.get("FIELD"));
		List<KKHBookVO> bookList = sqlsession.selectList("KKH.findBookBysidebar", parameterMap);
		
		return bookList;
	}

	
	@Override
	//검색창에서 입력한 정보(searchType,searchWord,sort) 들을 사용해 도서리스트를 검색하는 메소드
	public List<KKHBookVO> findBookBysearchbar(HashMap<String, String> parameterMap) {
		List<KKHBookVO> bookList = sqlsession.selectList("KKH.findBookBysearchbar", parameterMap);
		return bookList;
	}


	@Override
	/*도서 상세로 가는 메소드 리스트 인 이유는 해당 도서번호 'L1000KRE02530UN-1' 를 가진 모든 책들을 가져오기 때문*/
	public List<KKHBookVO> findBookDetail(String bookid) {
		List<KKHBookVO> bookDetail = sqlsession.selectList("KKH.findBookDetail", bookid);
		return bookDetail;
	}


	@Override
	//도서 상세 페이지에 있는 책중 예약된 책들의 예약정보리스트를 가져오는 메소드, 이때 bookid 는 두번째 하이푼(-)이전 까지의 코드이다. ex)L1000KRE02530UN-1
	public List<HashMap<String,String>> findBookReservateList(String bookid) {
		List<HashMap<String,String>> bookReservateList =  sqlsession.selectList("KKH.findBookReservation",bookid);
		return bookReservateList;
	}
	
//도서 수정을 위한 항목별 정보 가져오기 시작
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
	//분야별 큰 항목(000, 100, 200,.....900)을 가져오는 메소드
	public List<HashMap<String, String>> findfield() {
		List<HashMap<String,String>> fieldList = sqlsession.selectList("KKH.findField");
		return fieldList;
	}
	@Override
	//큰 항목에 따른 분야별 작은 항목(110,120,130,....190)등을 가져오는 메소드
	public List<HashMap<String, String>> findDetailField(String bigfcode) {
		List<HashMap<String,String>> detailFieldList = sqlsession.selectList("KKH.findDetailField", bigfcode);
		return detailFieldList;
	}
//도서 수정을 위한 항목별 정보 가져오기 끝	
	
	@Override
	//도서 수정을 위해 변경할 정보와 비교하기 위한 기존정보를 가져오는 메소드
	public KKHBookVO findOneBook(String bookid) {
		List<KKHBookVO> bookList = sqlsession.selectList("KKH.findOneBook", bookid);
		KKHBookVO book= bookList.get(0);
		return book;
	}
	@Override
	//새로 부여할 bookid 의 큰번호를 채번해오는 메소드
	public String findNewBook1stNum(String newBookid) {
		int result = sqlsession.selectOne("KKH.findNewBook1stNum", newBookid);
		return String.valueOf(result);
	}
	
	@Override
	//book_detail 테이블에서 수정할 도서의 정보(intro,price,weight 등등)을 가져온뒤 삭제하는 메소드
	public List<KKHBookVO> selectAndDelBookDetail(String bookid) {
		//수정하려는 도서번호에 해당하는 [ex)L1000KRE02530UN-1 에 해당하는] book_detail 의 정보들을 select 해온다.
		List<KKHBookVO> bookDetailList = sqlsession.selectList("KKH.selectBookDetail", bookid);
		System.out.println("select로 가져옴");
		//select 해온뒤 해당 정보들을 book_detail 테이블에서 delete한다.
		sqlsession.delete("KKH.deleteBookDetail", bookid);
		System.out.println("삭제됨");
		return bookDetailList;
	}
	
	@Override
	//book 테이블의 bookid 를 새롭게 부여해주는 메소드
	public int updateNewBookid(HashMap<String, String> parameterMap) {
		System.out.println("111");
		int n = sqlsession.update("KKH.updateNewBookid",parameterMap);
		return n;
	}
	
	@Override
	//book_detail 테이블에 저장해두었던 정보와 새롭게 부여한 bookid 값을 insert해주는 메소드
	public void insertNewBookDetail(List<KKHBookVO> bookDetailList) {
		for(KKHBookVO bookvo : bookDetailList) {
			System.out.println("bookid:"+bookvo.getBookid()+", idx:"+bookvo.getIdx()+",image:"+bookvo.getImage()+",price:"+bookvo.getPrice()+",weight:"+bookvo.getWeight()+",totalpage:"+bookvo.getTotalpage()+",pdate:"+bookvo.getPdate());
			sqlsession.insert("KKH.inserNewBookDetail", bookvo);
		}
	}
	
	
	@Override
	//이미지 파일을 변경했을경우 book_detail 테이블의 image 컬럼을 update해주는 메소드
	public int updateNewBookDetail(HashMap<String, String> parameterMap) {
		System.out.println("222");
		int n = sqlsession.update("KKH.updateNewBookDetail", parameterMap);
		return n;
	}

	
	@Override
	//수정하려는 도서들의 book 테이블의 정보를 update해준다.
	public int updateBookDetail(HashMap<String, String> parameterMap) {
		System.out.println("333");
		int n = sqlsession.update("KKH.updateBookDetail", parameterMap);
		return n;
	}
	@Override
	//이미지 파일을 변경했을경우 book_detail 테이블의 image컬럼값을 update 해준다.
	public int updateBookInfo(HashMap<String, String> parameterMap) {
		System.out.println("444");
		int n = sqlsession.update("KKH.updateBookInfo", parameterMap);
		return n;
	}
	@Override
	//개별 도서정보를 수정하는 메소드
	//이때 ISBN은 book테이블에 있는거라 따로 update해준다.
	public int editIndivBookInfo(HashMap<String, String> parameterMap) {
		int n1 = sqlsession.update("KKH.editIndivBookInfo", parameterMap);
		int n2 = sqlsession.update("KKH.editIndivBookISBN",parameterMap);
		if(n1*n2 == 1) return 1;
		else return 0;	
	}
	@Override
	//개별 도서를 테이블에서 삭제하는 메소드
	//book, book_detail 테이블 두곳에서 모드 삭제해야한다.
	//이때 book_detail 테이블의정보부터 삭제해야함.
	public int deleteIndivBook(String bookid) {
		int n1 = sqlsession.delete("KKH.deleteIndivBookDetail", bookid);
		int n2 = sqlsession.delete("KKH.deleteIndivBook", bookid);
		if(n1*n2 == 1) return 1;
		else return 0;
	}
	
	@Override
	//추가될 도서의 시작일련번호를 채번해오는 메소드
	public int findStartBookNum(String bookid) {
		int startBookNum = sqlsession.selectOne("KKH.findStartBookNum", bookid);
		return startBookNum;
	}
	
	@Override
	//추가될 도서에 입력될 도서정보를 해당 도서번호를 가진 책들중 첫번째 책에서 가져오는 메소드
	public KKHBookVO findBookInfoSample(String bookid) {
		List<KKHBookVO> bookSampleList = sqlsession.selectList("KKH.findBookSampleList", bookid);
		List<KKHBookVO> bookDetailSampleList = sqlsession.selectList("KKH.findBookDetailSampleList",bookid);
		//가져온 리스트의 첫번째 객체정보를 넣어준다.
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
	//book테이블,book_detail 테이블에 추가할 도서갯수만큼 insert해주는 메소드
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
		if(n1-n2 == 0) return 1; // 성공적으로 될경우 book에 insert 한 갯수 = book_detail 한 갯수
		else return 0;
	}
	
	@Override
	//삭제할 도서의 정보를 VO에 저장하는 메소드
	public List<KKHBookVO> findDeleteBook(String bookid) {
		List<KKHBookVO> deleteBookList = sqlsession.selectList("KKH.deleteBookList", bookid);
		return deleteBookList;
	}
	
	@Override
	//delete_book 테이블에 가져온 도서정보를 insert 한뒤 book, book_detail 테이블에서 해당 도서의 정보를 삭제하는 메소드
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
	//해당 도서번호를 가진 모든 책들을 삭제하는 메소드
	public int deleteBookAndBookDetail(String bookid) {
		int n2 = sqlsession.delete("KKH.deleteBook_Detail",bookid);//book_detail 테이블에서 삭제
		int n1 = sqlsession.delete("KKH.deleteBook", bookid);	   //book 테이블에서 삭제
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
		String reserveCount = sqlsession.selectOne("KKH.getReserveCount", returnBookid);
		rentalBookInfo.put("RESERVECOUNT", reserveCount);
		System.out.println(rentalBookInfo.get("IDX")+", "+rentalBookInfo.get("BOOKID_FK"));
		return rentalBookInfo;
	}
	
	@Override
	public int insertReturnedBook(HashMap<String, String> rentalBookInfo) {
		int n = sqlsession.insert("KKH.insertReturnedBook", rentalBookInfo);
		return n;
	}
	
	@Override
	public int updateReturnedBookStatus(HashMap<String, String> rentalBookInfo) {
		System.out.println("reservecount:"+rentalBookInfo.get("RESERVECOUNT"));
		int n = sqlsession.update("KKH.updateReturnBookStatus", rentalBookInfo);
		return n;
	}
	
	@Override
	public int updateLateMemberInfo(HashMap<String, String> rentalBookInfo) {
		int n = sqlsession.update("KKH.updateLateMember", rentalBookInfo);				
		return n;
	}
	
	@Override
	public int deleteRentalBook(String returnBookid) {
		int n = sqlsession.delete("KKH.deleteRentalBook", returnBookid);
		return n;
	}
	
	@Override
	public int reserveCancel(String cancelBookid) {
		sqlsession.update("KKH.CancelChangeStatus", cancelBookid);
		int n = sqlsession.delete("KKH.reserveCancel", cancelBookid);
		return n;
	}
	
	


	

}
