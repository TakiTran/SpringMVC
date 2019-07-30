<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<title>Word List</title>
</head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"><script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script><script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script><script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css"/>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.css"/>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css"/>
<style>
	.card {
		width: 900px;
		height: auto;
		margin: auto;
		padding-top:10px;
		margin-top:20px;
		box-shadow: 0 0 50px #676767;
		border-radius: 15px;
	}
	.container > .card > .form-group {
		padding: 15px 30px 15px 30px;;
	}
	.title {
		padding-bottom: 30px;
	}
	.container > .card > .form-group > .table > thead > tr > th {
		text-align: center;
	}
	.container > .card > .form-group > .table > tbody > tr > td {
		text-align: center;
	}

</style>
<body>	
	<div class="container">
		<div class="card">
			<div class="form-group">'
				<h4 class="title">Quản lý từ điển</h4>
				<a href="word-save" class="btn btn-sm btn-primary"><i class="fas fa-plus"> Thêm mới</i></a>
			</div>
			<div class="form-group">
				<form action="word-list" method="post">
					<div class="row">
						<div class="form-group form-check col-sm-4">
							<label>Thể loại</label>
							 <select class="form-control" id="select-type" name="type">
						     	<option value="1" selected="selected">English - Vietnamese</option>
						     	<option value="2">Vietnamese - English</option>
						      </select>
						</div>
						<div class="form-group form-check col-sm-4">
							<label>Từ khóa</label>
							<input name="key" class="form-control" />
						</div>
						<div class="form-group form-check col-sm-4">
							<button class="btn btn-sm btn-primary" type="submit" style="margin-top: 35px;margin-left: 50px;"><i class="fas fa-search"> Tìm kiếm</i></button>
						</div>
					</div>
				</form>
			</div>
			<div class="form-group">
				<table class="table">
					<thead>
						<tr>
							<th>STT</th>
							<th>Key</th>
							<th>Meanning</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="word" items="${words}" varStatus="cnt">
							<tr>
								<td><c:out value="${cnt.count+ 3*curPage - 3}" /></td>
								<td>${word.key}</td>
								<td>${word.mean}</td>
								<td>
									<a class="btn btn-sm btn-success" href="word-save?id=${word.id}">chi tiết</a>
									<a class="btn btn-sm btn-danger twitter" href="word-delete?id=${word.id}">xóa</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<ul class="pagination justify-content-center">
				 	<li class="page-item"><a class="page-link" href="#">Previous</a></li>
					<c:forEach begin="1" end="${totalPages}" var="page" >
						<c:url value="/admin/word-list" var="pagingUrl">
				            <c:param name="page" value="${page}"></c:param>
				        </c:url>
				        <c:choose>
				        	<c:when test="${ curPage == page }">
				              	<li class="page-item active"><a class="page-link"
				                href="${ pagingUrl }">${ page }</a></li>
				            </c:when>
				            <c:otherwise>
				              <li class="page-item"><a class="page-link"
				                href="${ pagingUrl }">${ page }</a></li>
				            </c:otherwise>
				    	</c:choose>
					</c:forEach >
					<li class="page-item"><a class="page-link" href="#">Next</a></li>
			  	</ul>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
<script type="text/javascript">
	function Message() {
		var messageResult = '${typeMessage}';
		var contentResult = '${contentMessage}';
		if(messageResult == "success"){
	    	 toastr.success(contentResult,'Notification')
	    }
	    if(messageResult == "warning"){
	    	 toastr.warning(contentResult,'Notification')
	    }
	}
	$(document).ready(function(){
		 $('a.twitter').confirm({
			title: 'Are you sure?',
		    content: 'Do you want to delete this record?',
		    type: 'red',
		    typeAnimated: true,
		});
		$('a.twitter').confirm({
		    buttons: {
		        hey: function(){
		        	btnClass: 'btn-red'
		            location.href = this.$target.attr('href');
		        }
		    }
		});
		Message();
		console.log('${curPage}');
	});
</script>
</html>