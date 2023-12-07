<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
	<!-- content 영역 -->
	<div class="row my-5" id="global-content">		
		<div class="offset-1 col-10">
			<form name="checkForm" id="checkForm">
				<input type="hidden" name="no" id="no" value="${board.no}">
				<input type="hidden" name="pass" id="rPass">
				<input type="hidden" name="pageNum" value="${pageNum}">
				<c:if test="${searchOption }">
					<input type="hidden" name="type" value="${type}">
					<input type="hidden" name="keyword" value="${keyword}">
				</c:if>
			</form>
			<div class="row">
				<div class="col">
					<h2 class="fs-3 fw-bold text-center">게시 글 상세보기</h2>
				</div>
			</div>
			<div class="row my-3">
				<div class="col">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<th>제 목</th>
								<td colspan="3">${board.title}</td>
							</tr>
							<tr>
								<th>작성자</th>
								<td>${board.writer}</td>
								<th>작성일</th>
								<td>${board.regDate}</td>
							</tr>
							<tr>
								<th>비밀번호</th>
								<td>
									<div class="col-8">
										<input type="password" class="form-control" 
											name="pass" id="pass">
									</div>
								</td>
								<th>조회수</th>
								<td>${board.readCount}</td>
							</tr>
							<tr>
								<th>파 일</th>
								<td colspan="3">
									<c:if test="${empty board.file1}">
										첨부파일 없음
									</c:if>
									<c:if test="${not empty board.file1}">
										<a href="upload/${board.file1}">파일 다운로드</a>
									</c:if>
								</td>
							</tr>							
							<tr>								
								<td colspan="4">
									${board.content}
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row my-3">
				<div class="col text-center">
					<input class="btn btn-warning" type="button" id="detailUpdate" value="수정하기">
					<input class="btn btn-danger" type="button" id="detailDelete" value="삭제하기">
					
					<!-- 검색요청일때 -->
					<c:if test="${searchOption}">
						<input class="btn btn-primary"  type="button" value="목록보기"
							onclick="location.href='boardList?pageNum=${pageNum}&type=${type}&keyword=${keyword}'">
					</c:if>
					<!-- 일반(검색요청이 아닌경우)요청일때 -->
					<c:if test="${not searchOption}">
						<input class="btn btn-primary"  type="button" value="목록보기"
							onclick="location.href='boardList?pageNum=${pageNum}'">
					</c:if>
				</div>
			</div>
		</div>
	</div>		