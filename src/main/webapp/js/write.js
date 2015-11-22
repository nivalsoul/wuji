
$(function() {
	var url = window.location.href.split("?");
	var article_type="原创发表";
	if(url.length>1 && url[1]==1){
		article_type = "引用发表";
		$("#quotationDiv").show();
		$("#quotation").val(window.opener.selectedText);
		$("#quotation").attr("readonly","readonly")
		$("#reference_id").val(window.opener.id);
		console.log("ref_id:"+window.opener.id);
	}
	$("#article_type").val(article_type);
	$("#article_type").change(function(){
		if($(this).val()=="引用发表")
		    $("#quotationDiv").show();
		else{
			$("#quotation").val("");
			$("#quotationDiv").hide();
		}
	});
});
