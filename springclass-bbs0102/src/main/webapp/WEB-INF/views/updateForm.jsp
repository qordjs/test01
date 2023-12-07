<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row my-5" id="global-content">
	<div class="col-10 offset-1">
	
		<div class="row">
			<div class="col">
				<h2 class="fs-3 fw-bold text-center">게시글 수정하기</h2>
			</div>
		</div>
		
		<!-- action="update"는 별도 프로세스 파일을 만드는 게 아니라 mapping을 뜻함 -->
		<form name="updateForm" id="updateForm" action="update" class="row g-3" method="post">
		
			<input type="hidden" name="no" value="${board.no}">
			<input type="hidden" name="pageNum" value="${pageNum}">
		
			<div class="col-5 offset-1">
			    <label for="writer" class="form-label">글쓴이</label>
			    <input type="text" class="form-control" name="writer" id="writer" value="${board.writer}">
		    </div>
		  	<div class="col-5">
			    <label for="pass" class="form-label">비밀번호</label>
			    <input type="password" class="form-control" name="pass" id="pass">
		    </div>
		    
		    <div class="col-10 offset-1">
			    <label for="title" class="form-label">제목</label>
			    <input type="text" class="form-control" name="title" id="title" value="${board.title}">
		    </div>
		    
		     <div class="col-10 offset-1">
			    <label for="content" class="form-label">내용</label>
			    <textarea name="content" id="content" class="form-control" rows="10">${board.content}</textarea>
		    </div>
		    
		    <div class="col-10 offset-1 mt-5 text-center">
			    <input type="submit" value="수정하기" class="btn btn-primary">
			    <input type="button" value="목록보기" class="btn btn-primary" onclick="location.href='boardList?pageNum=${pageNum}'">
		    </div>
		    
		</form>
	
	</div>
</div>