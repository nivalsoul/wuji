
var last=false;
var number=0;
var pageSize=10;

var source = "原创";

$(function() {
	var useraccount = $.cookie("useraccount");
	var username = $.cookie("username");
	if(useraccount!=null){
		$("#loginBtn").hide();
		$("#userProfile").show();
		$("#un").html(username);
		$("#ua").attr("href", "user/"+useraccount);
	}else{
		$("#loginBtn").show();
		$("#userProfile").hide();
	}
	getArticles(source, null, 0);
	$('#loginModal').on('show.bs.modal', function () {
		$("#loginDiv").show();
		$("#regDiv").hide();
	});
});

function loadMore(){
	getArticles(source, null, number);
}


function login(){
	var useraccount = $("#loginForm #useraccount").val();
	var password = $("#loginForm #password").val();
	$.post("user/login", {"useraccount" : useraccount, "password" : password}, 
		function(data, status) {
			if(data.code==0){
				//登录成功
				$("#loginModal").modal('hide');
				$("#loginBtn").hide();
				$("#userProfile").show();
				var useraccount = $.cookie("useraccount");
				var username = $.cookie("username");
				console.log(useraccount+username);
				$("#un").html(username);
				$("#ua").attr("href", "user/"+useraccount);
			}else{
				alert(data.info);
			}
		}
	);
}

function toReg(){
	$("#loginDiv").hide();
	$("#regDiv").show();
	$("#myModalLabel").html("用户注册");
}

function register(){
	var useraccount = $("#regForm #useraccount").val();
	var username = $("#regForm #username").val();
	var email = $("#regForm #email").val();
	var password = $("#regForm #password").val();
	$.post("user", {"useraccount" : useraccount, "username" : username,
		"email" : email, "password" : password}, 
		function(data, status) {
			if(data=="ok"){
				alert("注册成功！已自动登录。");
				$("#loginModal").modal('hide');
				$("#loginBtn").hide();
				$("#userProfile").show();
				$("#un").html(username);
				$("#ua").attr("href", "user/"+useraccount);
			}else{
				alert(data);
			}
		}
	);
}

function logout(){
	$.post("user/logout", {}, 
		function(data, status) {
			if(data=="ok"){
				$("#loginBtn").show();
				$("#userProfile").hide();
				$("#un").html("");
				$("#ua").attr("href", "#");
				$.cookie("useraccount", null);
				$.cookie("username", null);
			}else{
				alert(data);
			}
		}
	);
}

function toWrite(){
	var useraccount = $.cookie("useraccount");
	if(useraccount != null){
		window.open("view/write.html");
	}else{
		alert("登录加入组织后才能发表，谢谢！");
	}
}

