package com.springstudy.bbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springstudy.bbs.dao.BoardDao;
import com.springstudy.bbs.domain.Board;

@Service
public class BoardServiceImpl implements BoardService {
	
	private static final int PAGE_SIZE=10;
	private static final int PAGE_GROUP=10;
	
	
	@Autowired
	private BoardDao boardDao;

	@Override
	public Map<String,Object> boardList(int pageNum,String type,String keyword) {
		int currentPage=pageNum;
		
		//1 =0 =>1*10=10 -10=0
		//3=20 =>3*10=30 -10
		//7=60 => 7*10 =70 -10
		//이해못했음..
		int start= currentPage * PAGE_SIZE-PAGE_SIZE;
		
		//페이지 처리를 위한 전체 게시글 수
		int listCount=boardDao.getBoardCount(type,keyword);
		
		//현재 페이지에 출력할 게시글 리스트
		List<Board> boardList=boardDao.boardList(start,PAGE_SIZE,type,keyword);
		
		//전체 페이지 수 계산
		int pageCount=listCount/PAGE_SIZE+(listCount%PAGE_SIZE==0 ? 0 :1);
		
		//페이지 네이션의 시작 페이지와 마지막 페이지
		
		//1~10 :1 11~20:2
		//1/10 = 0 , 5/10 =0 ,
		//11/10=1
		int startPage= (currentPage/PAGE_GROUP)*PAGE_GROUP+1-(currentPage%PAGE_GROUP==0 ? PAGE_GROUP:0);
		
		int endPage=startPage+PAGE_GROUP-1;
		
		if(endPage>pageCount) {
			endPage=pageCount;
		}
		
		Map<String,Object> modelMap=new HashMap<>();
		modelMap.put("bList", boardList);
		modelMap.put("pageCount", pageCount);
		modelMap.put("startPage", startPage);
		modelMap.put("endPage", endPage);
		modelMap.put("currentPage", currentPage);
		modelMap.put("pageGroup", PAGE_GROUP);
		modelMap.put("listCount", listCount);
		
		boolean searchOption =(type.equals("null") || keyword.equals("null")) ? false : true;
		modelMap.put("searchOption", searchOption);
		if(searchOption) {
			modelMap.put("type", type);
			modelMap.put("keyword", keyword);
		}
		
		//게시글 리스트에서 페이징 처리에 필요한 데이터가 한개가 아님
		//1. 현재 페이지에 해당하는 게시 글 리스트
		//2. 페이지 네이션 필요한 것 start,end
		//3. 전체 페이지 ,현재 페이지
		return modelMap;
	}

	@Override
	public Board getBoard(int no,boolean isCount) {	
		//게시글 상세보기 요청때만 읽은 횟수를 증가, 나머지는 증가x
		if(isCount) {
			boardDao.incrementReadcount(no);
		}
		return boardDao.getBoard(no);
	}

	@Override
	public void insertBoard(Board board) {
		boardDao.insertBoard(board);
	}

	@Override
	public boolean isPassCheck(int no, String pass) {
		boolean result=false;
		String dbPass= boardDao.isPassCheck(no, pass);
		if(dbPass.equals(pass)) {
			result=true;
		}
		return result;
	}

	@Override
	public void updateBoard(Board board) {
		boardDao.updateBoard(board);

	}

	@Override
	public void deleteBoard(int no) {
		boardDao.deleteBoard(no);

	}

}
