$(function(){
	var url = window.location.href.split("?");
	if(url.length>1){
		var id = url[1];
		$.getJSON("../article/"+id, {},function(article) {
			for(var o in article){
				eval('$("#' + o + '").val("' + article[o] + '");');
			}
			if($("#article_type").val()=="引用发表"){
				$("#quotationDiv").show();
			}
			//初始化textarea为富文本编辑器
	    	var arry = new Array();
	    	for(var i=1;i<100;i++){
	    		arry.push("../expressions/"+i+".gif");
	    	}
	        var editor = $('#article_content').wangEditor({'expressions': arry});
	        
	    	$("#article_type").change(function(){
	    		if($(this).val()=="引用发表")
	    		    $("#quotationDiv").show();
	    		else{
	    			$("#quotation").val("");
	    			$("#quotationDiv").hide();
	    		}
	    	});
		});
	}
});