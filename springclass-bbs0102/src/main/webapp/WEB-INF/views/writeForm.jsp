<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- content 영역 -->
<div class="row my-5" id="global-content">
	<div class="col-10 offset-1">
		<div class="row">
			<div class="col">
				<h2 class="fs-3 fw-bold text-center">게시 글 쓰기</h2>
			</div>
		</div>
		<form name="writeForm" id="writeForm" action="writeProcess"
			class="row g-3" method="post">
			<div class="col-5 offset-1">
				<label for="writer" class="form-label">글쓴이l</label>
				 <input type="text" class="form-control" name="writer" id="writer">
			</div>
			<div class="col-5">
				<label for="pass" class="form-label">비밀번호</label>
				 <input type="password" class="form-control" name="pass" id="pass">
			</div>
			<div class="col-10 offset-1">
				<label for="title" class="form-label">제목</label>
				 <input type="text" class="form-control" name="title" id="title">
			</div>
			<div class="col-10 offset-1">
				<label for="content" class="form-label">내용</label>
				 <textarea class="form-control" name="content" id="content" rows="10"></textarea>
			</div>
			<div class="col-10 offset-1 text-center mt-5">
				<input type="submit" value="등록하기" class="btn btn-primary">
				<input type="button" value="목록보기" class="btn btn-warning" onclick="location.href='boardList'">
			</div>
		</form>
	</div>
</div>