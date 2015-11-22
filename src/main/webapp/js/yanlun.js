
var last=false;
var number=0;
var pageSize=10;

var source = "原创";
var article_type = "言论";

$(function() {
	getArticles(source, article_type, 0);
});

function loadMore(){
	getArticles(source, article_type, number);
}