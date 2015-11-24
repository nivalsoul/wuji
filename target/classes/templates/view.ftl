<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${article.article_title}</title>

<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/main.css">

<script src="../js/jquery-1.11.3.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/jquery.cookie.js"></script>
<script src="../js/view.js"></script>

</head>
<body>
	<div id="wrap">
		<div id="pageBody">
			<div class="container">
				<div class="row" id="${article.id?c}"  onclick="setId(this.id)">
					<div id="article" class="panel panel-info">
						<div class="panel-heading">
							<h2>${article.article_title}</h2>
						</div>
						<div class="panel-body">
							<p><span id="author_name">${article.author_name}</span>
							     <span id="pub_time">${article.pub_time}</span></p>
							<div id="article_content" >${article.article_content}</div>
						</div>
						<div class="panel-footer">分享到...</div>
						<div id="commentDiv" class="panel panel-default">
							<div class="panel-heading">
								<h3 class="commentCount">共有<span id="commentNum">5</span>条评论</h3>
							</div>
							<div class="panel-body">
								<div id="pccDiv" class="editableDiv form-control" >
									<blockquote id="citeReplyContent" style="display:none;" readonly="readonly"></blockquote>
									<div id="postContent" tabindex="0" contenteditable="true">说点什么吧</div>
								</div>
								<div style="height:35px;margin:10px">
								    <span id="username" style="color:#3da9f7"></span>
								    <span class="pull-right" >
								        <button type="button" class="btn btn-info" onclick="postComment()">提交评论</button>
								    </span>
								</div>
							</div>
							<ul id="comments" class="media-list list-group">
								<li id="comment" class="media list-group-item">
								    
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div id="commentTemplate" style="display:none" >
					<div class="media-left">
						<a id="userLink"  target="_blank"> 
						    <img id="photo" class="media-object img-circle" src="../images/defaultphoto64.png" alt="用户头像">
						</a>
					</div>
					<div class="media-body">
						<div style="font-size:12px;">
						    <span><a id="author_name"  target="_blank">评论人</a>
						    </span>&nbsp;•&nbsp;
						    <span id="pub_time">评论时间</span>
						</div>
						<blockquote id="quotation" style="display:none;" ></blockquote>
						<p id="content">评论内容</p>
						<div align="right" class="replyBtnDiv"><a id="replyBtn" href="javascript:void(0)">回复</a></div>
					</div>
				</div>
				<div id="menu" class="panel panel-default" style="display:none;width:200px;position:absolute">
					<button type="button" class="btn btn-info" onclick="refandwrite()">引用并发文</button>
					<button type="button" class="btn btn-primary" onclick="refandreply()">引用并回复</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>