package com.springstudy.bbs.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springstudy.bbs.domain.Board;
import com.springstudy.bbs.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService service;
	
	
	
	@RequestMapping("/delete")
	public String delete(HttpServletResponse response,PrintWriter out,int no,String pass,@RequestParam(required=false,defaultValue="1") int pageNum) {
		
	boolean result = service.isPassCheck(no, pass);
		
		// 비밀번호가 틀렸다면, 틀렸다고 응답(자바스크립트가 실행되도록)
		if(!result) {
			response.setContentType("text/html; charset=utf-8");
			out.println("<script>");
			out.println("	alert('비밀번호가 틀렸습니다.');");
			out.println("	history.back();");
			out.println("</script>");
			return null;
		}
		
		service.deleteBoard(no);
		
		return "redirect:boardList?pageNum="+pageNum;
	}
	
	// overloading : get 방식과 post 방식 요청 처리를 다르게 해주기 위해 파라미터를 바꿔줌
		@RequestMapping(value="/update", method=RequestMethod.POST)
		public String updateBoard(Model model, PrintWriter out, HttpServletResponse response, Board board, @RequestParam(required=false,defaultValue="1") int pageNum) {
			
			// 게시글을 수정하려면, 비밀번호를 체크 (수정 권한 여부를 확인)
			boolean result = service.isPassCheck(board.getNo(), board.getPass());
			
			// 비밀번호가 틀렸다면, 틀렸다고 응답(자바스크립트가 실행되도록)
			if(!result) {
				response.setContentType("text/html; charset=utf-8");
				out.println("<script>");
				out.println("	alert('비밀번호가 틀렸습니다.');");
				out.println("	history.back();");
				out.println("</script>");
				return null;
			}

			// 비밀번호가 맞다면, no에 해당하는 게시글 데이터를 DB에서 수정
			service.updateBoard(board);
			
			return "redirect:boardList?pageNum="+pageNum;
		}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String updateBoard(Model model, PrintWriter out, HttpServletResponse response, int no, String pass,@RequestParam(required=false,defaultValue="1") int pageNum) {
		
		// 게시글을 수정하려면, 비밀번호를 체크 (수정 권한 여부를 확인)
		boolean result = service.isPassCheck(no, pass);
		
		
		
		// 비밀번호가 틀렸다면, 틀렸다고 응답(자바스크립트가 실행되도록)
		if(!result) {
			response.setContentType("text/html; charset=utf-8");
			out.println("<script>");
			out.println("	alert('비밀번호가 틀렸습니다.');");
			out.println("	history.back();");
			out.println("</script>");
			return null;
		}

		// 비밀번호가 맞다면, no에 해당하는 게시글 데이터를 DB에서 가져와서 화면에 출력 
		Board board = service.getBoard(no,false);
		model.addAttribute("board", board);
		model.addAttribute("pageNum",pageNum);
		
		return "updateForm";
	}
	
	
	
	// springclass-bbs0101/
	@RequestMapping(value={"/boardList", "/list"}, method=RequestMethod.GET)
	public String boardList(Model model,@RequestParam(value="pageNum", required=false, defaultValue="1") int pageNum,
			@RequestParam(value="type",required=false,defaultValue="null") String type,
			@RequestParam(value="keyword",required=false,defaultValue="null") String keyword) {
		
		
		model.addAllAttributes(service.boardList(pageNum,type,keyword));
		
		// ViewResolver에 설정한 prefix, suffix를 조합해서 뷰가 결정됨
		// /WEB-INF/index.jsp?body=views/ + boardList + .jsp		
		return "boardList";
	}
	
	@RequestMapping("/boardDetail")
	public String boardDetail(Model model, int no, @RequestParam(required=false,defaultValue="1") int pageNum,
			@RequestParam(value="type",required=false,defaultValue="null") String type,
			@RequestParam(value="keyword",required=false,defaultValue="null") String keyword) {
		
		boolean searchOption=(type.equals("null") || keyword.equals("null")) ? false:true;
		
		// service
		Board b = service.getBoard(no,true);
		
		// request.setAttribute("board", b);
		model.addAttribute("board", b);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("searchOption", searchOption);
		
		if(searchOption) {
			model.addAttribute("type", type);
			model.addAttribute("keyword", keyword);
		}
		
		// /WEB-INF/index.jsp?body=views/ + boardDetail + .jsp	
		return "boardDetail";
	}
	
	@RequestMapping(value="/writeProcess",method=RequestMethod.POST)
	public String insertBoard(Board b) {
		
		service.insertBoard(b);
		
		System.out.println(b.getNo());
		
		return "redirect:boardList";		
		
	}
	
	
}
