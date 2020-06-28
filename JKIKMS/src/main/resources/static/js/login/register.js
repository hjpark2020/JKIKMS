var idCheck = false;

$(document).ready(function(){
	//btnFunction();
	eventFunction();
})

function idOverlapCheck(){
	
	var dataStr = {
		userId : $("#userId").val()
	}
	
	$.ajax({
	    type: "post",
	    url: "/idCheck",
	    data: dataStr,
		async : true,
	    success: function(jRes) {
	    	if(jRes.success == "Y"){
	    		if(jRes.result == 0){
	    			$("#idFalseP").remove();
	    			idCheck = true;
	    		} else {
	    			$("#userId").after('<p class="text-danger" id="idFalseP">이미 사용중인 아이디입니다.</p>')
	    		}
	    	} else {
	    		
	    	}
	    }
	});

}

function eventFunction(){
	$("#userId").on("keyup",function(e){
		$("#idFalseP").remove();
		idCheck = false;
		
		if($("#userId").val().trim().length < 5) {
			$("#userId").after('<p class="text-danger" id="idFalseP">아이디는 5자 이상만 가능합니다.</p>')
			return false;
		} else {
			idOverlapCheck();
		}
	});
	
	$("#userPw").on("keyup",function(e){
		$("#pwFalseP").remove();
		
		if($("#userPw").val().trim().length < 6) {
			$("#userPw").after('<p class="text-danger" id="pwFalseP">비밀번호는 6자 이상만 가능합니다.</p>')
			return false;
		} else {
			//idOverlapCheck();
		}
		
	});
	
	$("#userPwChk").on("keyup",function(e){
		$("#pwChkFalseP").remove();
		
		if($("#userPwChk").val().trim().length < 6) {
			$("#userPwChk").after('<p class="text-danger" id="pwChkFalseP">비밀번호는 6자 이상만 가능합니다.</p>')
			return false;
		} else {
			if($("#userPw").val() != $("#userPwChk").val()){
				$("#userPwChk").after('<p class="text-danger" id="pwChkFalseP">비밀번호가 일치하지 않습니다.</p>')
			}
		}
		
	});
}