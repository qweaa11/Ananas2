package com.spring.bookmanage.board.BSBmodel;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



//===== #32. DAO 선언  =====
@Repository
public class BSBboardDAO implements BSBInterBoardDAO {
	
	//===== #33. 의존객체 주입하기(DI: Dependency Injection) =====
	@Autowired
	private SqlSessionTemplate sqlsession;

	@Override
	public int getTotalCountWithSearch(HashMap<String, String> paraMap) {
		int count = sqlsession.selectOne("BSB.getTotalCountWithSearch", paraMap);
		return count;
	}

	@Override
	public int getTotalCountNoSearch() {
		int count = sqlsession.selectOne("BSB.getTotalCountNoSearch");
		return count;
	}

	@Override
	public List<BSBboardVO> boardListPaging(HashMap<String, String> paraMap) {
		List<BSBboardVO> boardList = sqlsession.selectList("BSB.boardListPaging", paraMap);
		return boardList;
	}

	@Override
	public int add(BSBboardVO boardvo) {
		int n = sqlsession.insert("BSB.add",boardvo);
		
		return n;
	}

	@Override
	public BSBboardVO getView(String idx) {
		BSBboardVO boardvo = sqlsession.selectOne("BSB.getView", idx);
		return boardvo;
	}

	@Override
	public int addComment(BSBcommentVO commentvo) {
		int n = sqlsession.insert("BSB.addComment", commentvo);		
		return n;
	}

	@Override
	public int updateCommentCount(String parentidx) {
		int n = sqlsession.update("BSB.updateCommentCount", parentidx);		
		return n;
	}

	@Override
	public List<BSBcommentVO> listComment(HashMap<String, String> paraMap) {
		List<BSBcommentVO> commentList =  sqlsession.selectList("BSB.listComment", paraMap);
		return commentList;
	}

	@Override
	public int getCommentTotalCount(HashMap<String, String> paraMap) {
		int totalCount = sqlsession.selectOne("BSB.getCommentTotalCount", paraMap);
		return totalCount;
	}

	// ===== #73. 글수정 및 글삭제시 암호일치 여부 알아오기 =====
		@Override
		public boolean checkPW(HashMap<String, String> paraMap) {
			
			int n = sqlsession.selectOne("BSB.checkPW", paraMap);
			
			boolean result = false;
			
			if(n==1) {
				result = true;
			}
			
			
			return result;
		}

	

	 // ==== #101. 원글에 딸린 댓글이 있는지 없는지 확인하기
		@Override
		public int isExistsComment(HashMap<String, String> paraMap) {
			int Count = sqlsession.selectOne("BSB.isExistsComment", paraMap);
			return Count;
		}
		
		// ===== #80. 글1개 삭제하기 =====
		@Override
		public int deleteContent(HashMap<String, String> paraMap) {
			
			int result = sqlsession.update("BSB.deleteContent", paraMap);
			
			return result;
		}


		
		// ==== #102. 원글에 딸린 댓글 삭제하기 ====
		@Override
		public int delComment(HashMap<String, String> paraMap) {
			int n = sqlsession.update("BSB.delComment", paraMap);
			return n;
		}

		// ==== #128. tblBoard 테이블에서 groupno 컬럼의 최대값 알아오기
		@Override
		public int getGroupnoMax() {
			
			int maxgroupno = sqlsession.selectOne("BSB.getGroupnoMax");

			return maxgroupno;
		}


		
		// #142. 글쓰기(첨부파일이 있는 경우)
		@Override
		public int add_withFile(BSBboardVO boardvo) {
			int n = sqlsession.insert("BSB.add_withFile", boardvo);
			return n;
		}

		@Override
		public int commentdel(HashMap<String, String> paraMap) {
			int n = sqlsession.update("BSB.commentdel", paraMap);
			return n;
		}
		
		@Override
		public int updateCommentdelCount(String paraMap) {
			int result = sqlsession.update("BSB.updateCommentdelCount", paraMap);		
			return result;
		}
	


	
}
