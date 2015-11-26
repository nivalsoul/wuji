
function setMenu(tabId){
	$("#topMenu").load("menu.html #topMenuContent",function(){
    	$("#menuTabs li").each(function(){
    		var id=$(this).attr("id");
    		if(id==tabId){
    			$(this).addClass("active");
    			$(this).find("a").attr("href", "javascript:void(0)");
    		}else{
    			$(this).removeClass("active");
    			$(this).find("a").attr("href", id+".html");
    		}
    	});
    	//设置登录信息
    	setUserInfo();
    });
	
	$("#loginRegDIv").load("menu.html #loginRegContent",function(){
    	
    });
}

function setUserInfo(){
	var useraccount = $.cookie("useraccount");
	var username = $.cookie("username");
	if(useraccount!=null){
		$("#loginBtn").hide();
		$("#userProfile").show();
		$("#userProfile #un").html(username);
		$("#userProfile #ua").attr("href", "user/"+useraccount);
	}else{
		$("#loginBtn").show();
		$("#userProfile").hide();
	}
	$('#loginModal').on('show.bs.modal', function () {
		$("#loginDiv").show();
		$("#regDiv").hide();
	});
}

function getArticles(source, article_type, pageNum) {
	if(last){
		$("#loadMoreBtn").html("没有更多数据了...");
		$("#loadMoreBtn").attr("disabled", "disabled");
		return;
	}
	var d = new Date();
	var ymd = d.getFullYear()+"-"+(d.getMonth() + 1)+"-"+d.getDate(); 
	$.getJSON("article2", {"source":source, "article_type":article_type, "page":pageNum, "size":pageSize},function(data) {
		var list = data.content;
		last = data.last;
		number = data.number+1;
		for (var i = 0; i < list.length; i++) {
			var article = list[i];
			var obj = $("#article");
			$(obj).find("#article_title").html(article.article_title);
			var url = "article/"+article.id+".html";
			if(article.original==0)
				url = article.article_link;
			$(obj).find("#article_title").attr("href",url);
			$(obj).find("#author_name").html(article.author_name);
			if(article.author!="visitor"){
				var userLink = "user/"+article.author;
				$(obj).find("#userLink").attr("href",userLink);
				$(obj).find("#author_name").attr("href",userLink);
			}else{
				$(obj).find("#userLink").removeAttr("href");
				$(obj).find("#author_name").removeAttr("href");
			}
			var pubTime = article.pub_time;
			if(pubTime.substring(0,11) == ymd)
				pubTime = pubTime.substring(12);
			else
				pubTime = pubTime.substring(0,11);
			$(obj).find("#pub_time").html(pubTime);
			$(obj).find("#article_content").html(article.article_content);
			$(obj).find("#read_num").html(article.read_num);
			$(obj).find("#comment_num").html(article.comment_num);
			$(obj).find("#quotation").html(article.quotation);
			if(article.quotation==null || article.quotation==""){
				$(obj).find("#quotationBlock").hide();
			}
			var obj2 = obj.clone();
			obj2.attr("id", "article" + article.id);
			obj2.show();
			$(obj2).insertBefore(obj);
		}
		if(list.length>0)
		    $("#article").hide();
		if(last){
			$("#loadMoreBtn").html("没有更多数据了...");
			$("#loadMoreBtn").attr("disabled", "disabled");
		}
	});
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
				$.cookie("useraccount", null ,{path:"/"});
				$.cookie("username", null ,{path:"/"});
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
