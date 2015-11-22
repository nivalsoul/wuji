var last=false;
var number=0;
var pageSize=10;

var source = "微信";

$(function() {
	getArticles(source, "热门", 0);
	
	$("#articleTypes").find("li a").each(function(){
		var a = $(this);
		a.attr("onclick",'getArticles("'+source+'", "'+a.html()+'", 0)');
	});
});

function loadMore(){
	getArticles(source, "热门", number);
}


function getArticlesBySource(source, page) {
	if(last){
		$("#loadMoreBtn").html("没有更多数据了...");
		$("#loadMoreBtn").attr("disabled", "disabled");
		return;
	}
	$.getJSON("article2", {"source":source, "page":page, "size":pageSize}, function(data) {
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
			$(obj).find("#pub_time").html(article.pub_time);
			$(obj).find("#article_content").html(article.article_content);
			var obj2 = obj.clone();
			obj2.attr("id", "article" + article.id);
			obj2.show();
			$(obj2).insertBefore(obj);
		}
		if(list.length>0)
			$("#article").hide();
	});
}

