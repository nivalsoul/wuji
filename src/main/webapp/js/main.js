
var last=false;
var number=0;
var pageSize=10;

var source = "原创";

var tabName="tab1";

$(function() {
	//设置导航菜单
	setMenu("index");
	
	getArticles(source, null, 0);
	
});

function loadMore(){
	getArticles(source, null, number);
}



