<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Word</title>
</head>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css"/>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"/>
<style>
	.container > .card {
		margin: auto;
		margin-top: 100px;
		width: 50%;
		height: auto;
		box-shadow: 0 0 50px #676767;
		border-radius: 15px;
	}
</style>
<body>
	<div class="container">
		<div class="card ">
			<div class="card-header">
				<h4 style="text-align:center">Word</h4>
			</div>
			<div class="card-body">
				<form:form id="submit-word" action="word-save" method="post" modelAttribute="word">
					<form:input type="hidden" path="id"/>
					<div class="row">
						<div class="col-sm-6 form-group">
							<span>Thể loại</span>
							<form:select class="form-control" path="type" id="type">
								<form:option value="1">ENG-VIE</form:option>
								<form:option value="2">VIE-ENG</form:option>
							</form:select>
						</div>
						<div class="col-sm-6 form-group">
							<span>Key</span>
							<form:input class="form-control" path="key" id="key" style="margin-bottom:5px;" required="required"/>
							<span id="message-error" style="color: red"></span>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12 form-group">
							<span>Nghĩa của từ</span>
							<form:textarea class="form-control" path="mean" required="required"/>
						</div>
					</div>
					<div class="form-group" style="text-align: right;">
						<a id="btn-delete" class="btn btn-sm btn-danger twitter" href="word-delete?id=${word.id}">Xóa</a>	
						<button id ="btn_submit" class="btn btn-sm btn-success" type="submit">Cập nhật</button>	
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
<script>
	function updateWord() {
		$("#key").attr("readonly", true);
		$("#type").attr("disabled", true);
	}
	function checkUpdate() {
		var id = '${word.id}';
		if (id != 0) {
			updateWord();
		} else {
			$("#btn-delete").hide();
		}
	}
	function checkKey() {
		var key = $("#key").val();
		$.ajax({
			url:'checkKey',
			data: {key:key},
			success: function(data) {
				if(data == "exist") {
					$("#message-error").html("Key đã tồn tại.");
					$("#key").css({"border-color":"red"});
				} else {
					$("#message-error").html("");
					$("#key").css({"border-color":"#ced4da"});
				}
			},
			error: function (e) {
				console.log(e);
			}
		});
	}
	function check() {
		var error = $("#message-error").text();
		if (error != '') {
			$("#btn_submit").show();
		} else {
			$("#btn_submit").hide();
		}
	}
	$(document).ready(function(){
		checkUpdate();
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
		$("#key").change(function(){
			checkKey();
			check();
		});
	});
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
</html>