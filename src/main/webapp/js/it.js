
var last=false;
var number=0;
var pageSize=10;

var source = "IT信息";
var article_type = "";

var tabName="tab5";

$(function() {
	//设置导航菜单
	setMenu("it");
	
	getITArticles(source, article_type, 0);
});

function getITArticles(source, page, pageNum) {
	if(last){
		$("#loadMoreBtn").html("没有更多数据了...");
		$("#loadMoreBtn").attr("disabled", "disabled");
		return;
	}
	var d = new Date();
	var ymd = d.getFullYear()+"-"+(d.getMonth() + 1)+"-"+d.getDate(); 
	$.getJSON("itArticles", {"source":source, "article_type":article_type, "page":pageNum, "size":pageSize},function(data) {
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
			$(obj).find("#author_name").html(article.author);
			$(obj).find("#article_source").html(article.source);
			if(article.author_link!=""){
				$(obj).find("#userLink").attr("href",article.author_link);
				$(obj).find("#author_name").attr("href",article.author_link);
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

function loadMore(){
	getITArticles(source, article_type, number);
}

