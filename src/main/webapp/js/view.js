function getSelectionText() {
	if (window.getSelection) {
		return window.getSelection().toString();
	} else if (document.selection && document.selection.createRange) {
		return document.selection.createRange().text;
	}
	return '';
}

function getX(e) {
	e = e || window.event;
	return e.pageX || e.clientX + document.body.scroolLeft;
}

function getY(e) {
	e = e || window.event;
	return e.pageY || e.clientY + document.boyd.scrollTop;
}

var id;
var selectedText;
var source_id=null;
var ref_comment_id=null;

//引用并回复
function refandreply() {
	$("#citeReplyContent").html(selectedText.replace(/(\r\n)/g, "<br>"));
	$("#citeReplyContent").show();
	$("#menu").hide();
	window.location = window.location.toString().split("#")[0]+"#commentDiv";
	$("#postContent").focus();
}
//引用并发文
function refandwrite() {
	$("#menu").hide();
	window.open("../view/write.html?1");
}

function setId(val) {
	id = val;
}

function setRefCommentId(val){
	ref_comment_id = val;
	source_id = val;
}

//回复评论
function reply(commentId){
	source_id = commentId;
	window.location = window.location.toString().split("#")[0]+"#commentDiv";
	$("#postContent").focus();
	$("#menu").hide();
}

//提交评论
function postComment(){
	var content = $("#postContent").html();
	if(content=="" || content=="说点什么吧"){
		alert("请输入有意义的评论！");
		return;
	}
	var quotation = "";
	if(!$("#citeReplyContent").is(":hidden"))
		quotation = $("#citeReplyContent").html();
	$.post("../comment", {"content" : content, "article_id" : id,
		"source_id":source_id, "ref_comment_id":ref_comment_id, "quotation" : quotation}, 
		function(data, status) {
			if(data=="ok" || status=="success"){
				//暂时为刷新页面，以后改为只刷新评论部分
				window.location.reload();
			}
		}
	);
}

var last=false;
var number=0;
var pageSize=20;

$(function() {
	$(document).ready(function(){
		id=$("#article").parent().attr("id");
		console.log("articleId:"+id);
		
		$.getJSON("../comment", {"articleId":id, "page":number, "size":pageSize}, function(data) {
			var list = data.content;
			last = data.last;
			number = data.number+1;
			$("#commentNum").html(data.totalElements);
			for (var i = 0; i < list.length; i++) {
				var comment = list[i];
				var commentLi = $("#comment").clone();
				commentLi.attr("id", "comment" + comment.id);
				$(setItem(comment)).appendTo($(commentLi));
				$(commentLi).insertBefore($("#comment"));
			}
			$("#comment").hide();
		});
		$("#postContent").focus(function(){
			if($(this).html()=="说点什么吧")
			    $(this).html("");
			$(this).parent().css("border-color", "#66afe9");
			$(this).parent().css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(102, 175, 233, 0.6)");
			$(this).parent().css("outline", "0 none");
		});
		$("#postContent").blur(function(){
			if($(this).html()=="")
				$(this).html("说点什么吧");
		});
		
		var username = $.cookie("username");
		if(username!=null){
			$("#username").html(username);
		}else{
			$("#username").html("游客");
		}
	});
	
	$(document).mouseup(function(e) {
		var x = getX(e);
		var y = getY(e);
		$("#menu").css("left",x+20);
		$("#menu").css("top",y+20);
		var txt = getSelectionText();
		if (txt.toString().trim().length > 0) {
			selectedText = txt;
			$("#menu").show();
		} else {
			$("#menu").hide();
		}
	});
});

function setItem(comment){
	var obj = $("#commentTemplate").clone();
	obj.show();
	obj.attr("id", "item-" + comment.id);
	if(comment.author!="visitor"){
		var userLink = "../user/"+comment.author;
		$(obj).find("#userLink").attr("href",userLink);
		$(obj).find("#author_name").attr("href",userLink);
	}
	var photoUrl = "../images/defaultphoto64.png";
	if(comment.photoUrl != null)
		photoUrl = comment.photoUrl;
	$(obj).find("#photo").attr("src",photoUrl);
	$(obj).find("#content").html(comment.content);
	$(obj).find("#content").attr("onclick", "setRefCommentId("+comment.id+")");
	$(obj).find("#author_name").html(comment.author_name);
	$(obj).find("#pub_time").html(comment.pub_time);
	$(obj).find("#replyBtn").attr("onclick","reply("+comment.id+")");
	var q = comment.quotation;
	if(q != null && q != ""){
		$(obj).find("#quotation").html(comment.quotation);
		$(obj).find("#quotation").show();
	}
	if(comment.quotation==null || comment.quotation==""){
		$(obj).find("#quotationBlock").hide();
	}
	var parentComment = comment.parentComment;
	if(parentComment!=null){
		//递归设置子评论
		setItem(parentComment).appendTo($(obj).find("#content").parent());
	}
	return obj.children();
}
