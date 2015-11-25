
var last=false;
var number=0;
var pageSize=10;

var source = "原创";
var article_type = "开心一刻";

var tabName="tab4";

$(function() {
	//设置导航菜单
	setMenu("fun");
	
	getArticles(source, article_type, 0);
});

function loadMore(){
	getArticles(source, article_type, number);
}

