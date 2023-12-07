package com.springstudy.bbs.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.springstudy.bbs.domain.Board;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static final String NAME_SPACE = "com.springstudy.bbs.mapper.BoardMapper";
	
	

	@Override
	public List<Board> boardList(int start,int num, String type, String keyword) {
		Map<String,Object> paramMap=new HashMap<>();
		paramMap.put("start", start);
		paramMap.put("num", num);
		paramMap.put("type", type);
		paramMap.put("keyword", keyword);
		
		return sqlSession.selectList(NAME_SPACE + ".boardList",paramMap);		
	}

	@Override
	public Board getBoard(int no) {
		// SELECT * FROM springbbs WHERE no = ?
		return sqlSession.selectOne(NAME_SPACE + ".boardDetail", no);
	}

	@Override
	public void insertBoard(Board board) {
		sqlSession.insert(NAME_SPACE + ".insertBoard", board);

	}

	@Override
	public String isPassCheck(int no, String pass) {
		return sqlSession.selectOne(NAME_SPACE + ".isPassCheck", no);
	}

	@Override
	public void updateBoard(Board board) {
		sqlSession.update(NAME_SPACE+".updateBoard", board);

	}

	@Override
	public void deleteBoard(int no) {
		sqlSession.update(NAME_SPACE+".deleteBoard", no);

	}

	@Override
	public int getBoardCount(String type, String keyword) {
		
		Map<String,String> paramMap=new HashMap<>();
		paramMap.put("type", type);
		paramMap.put("keyword", keyword);
		
		return sqlSession.selectOne(NAME_SPACE+".getBoardCount",paramMap);
	}

	@Override
	public void incrementReadcount(int no) {
		sqlSession.update(NAME_SPACE+".incrementReadCount", no);
		
	}

}
