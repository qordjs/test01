$(function(){

	$("#detailUpdate").on("click",function(){
		let pass=$("#pass").val();
		if(pass.length==0){
			alert("비밀번호를 입력하세요");
			return false;
		}
		$("#rPass").val(pass);
		$("#checkForm").attr("action","update");
		$("#checkForm").submit();
	});
	
	$("#detailDelete").on("click",function(){
		let pass=$("#pass").val();
		if(pass.length==0){
			alert("비밀번호를 입력하세요");
			return false;
		}
		$("#rPass").val(pass);
		$("#checkForm").attr("action","delete");
		$("#checkForm").attr("method","post");
		$("#checkForm").submit();
	});

	//게시 글 쓰기 폼이 서브밋 될때
	$("#writeForm").on("submit",function(){
		
		if($("#writer").val().length<=0){
		alert("작성자를 입력해주세요.");
		$("#writer").focus();
		return false;
		}
		
		if($("#title").val().length<=0){
		alert("제목을 입력해주세요.");
		$("#title").focus();
		return false;
		}
		
		if($("#pass").val().length<=0){
		alert("비밀번호를 입력해주세요.");
		$("#pass").focus();
		return false;
		}
		
		if($("#content").val().length<=0){
		alert("내용을 입력해주세요.");
		$("#content").focus();
		return false;
		}
	
	});
});