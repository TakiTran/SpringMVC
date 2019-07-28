<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Topica Translate</title>
</head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"><script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script><script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script><script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
<style>
	.container {
		height: auto;
		padding: 0px;
	}
	
	.container> .card {
		margin-top: 50px;
		height: 200px;
		border: solid 1px #dddddd;
		border-radius: 15px;
		box-shadow: 0 0 25px #d6cdcd;
	}
	
	.container > .card > .card-header {
		border-radius: 15px 15px 0 0;
		border: none;
	}
	.container > .card > .card-body { 
		height: 150px;
		padding-top :0px;
	}
	.container > .card > .card-header > .row > .convert-icon {
		text-align: center;
	}
	.container > .card > .card-body > .row > .language-content > .language-text {
		margin-top: 10px;
		border: none;
	}
	.container > .card > .card-body > .row > .language-convert {
		height: 150px;
		border-left: solid 1px #dddddd;
	}
	.container > .search-table {
		width: 50%;
		height: auto;
	}
	.container > .search-table > .card-body > .list-group > .list-group-item {
		border: none;
	}
</style>
<body>
	<div class="container">
		<div class="card">
			<div class="card-header ">
				<div class="row">
					<div class="language-title col-sm-5">
						<span id="language">Tiếng Việt</span>
					</div>
					<div class="convert-icon col-sm-2" style="">
						<i id="convert-icon" class="fas fa-exchange-alt"></i>
						<input id="type" type="hidden" />
					</div>
					<div class="language-title col-sm-5">
						<span id="language-convert">Tiếng Anh</span>
					</div>
				</div>	
			</div>
			<div class="card-body">
				<div class="row">
					<div class="language-content col-sm-6 ">
						<textarea id="text-search" class="language-text" rows="5" cols="70" placeholder="Nhập văn bản"></textarea>
					</div>
					<div class="language-content col-sm-6 language-convert">
						<textarea id="text-translate" class="language-text" rows="5" cols="70" placeholder="Bản dịch"></textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="card search-table" style="display:none;">
			<div class="card-header">
				<span>Danh sách các từ cùng cách viết</span>
			</div>
			<div class="card-body">
				<ul class="list-group word-list">
					
				</ul>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

	function showListSearch() {
		if ($("#text-search").val() == "") {
			$(".search-table").hide();
		} else {
			$(".search-table").show();
		}
	}
	function absoluteSearch() {
		var text_search = $("#text-search").val();
		var type = $("#type").val();
		$.ajax({
			method: 'get',
			url: 'absoluteSearch',
			data: {
				textSearch: text_search,
				type: type,
				},
			dataType: 'json',
			success: function(data) {
				if (data.length > 0) {
					$("#text-translate").html(data[0].mean);
				} else {
					$("#text-translate").html(text_search);
				}
				
			},
			error: function() {
				console.log("fail!");
			}
		});
	}
	function relativeSearch() {
		var text_search = $("#text-search").val();
		var type = $("#type").val();
		$.ajax({
			method: 'get',
			url: 'relativeSearch',
			data: {
				textSearch: text_search,
				type: type,
				},
			dataType: 'json',
			success: function(data) {
				$(".word-list").html("");
				if (data.length > 0) {
					data.forEach(function(item){
						$(".word-list").append(
							'<li class="list-group-item">\
								<div class="row">\
									<div class="col-sm-4">\
										<span>' + item.key + '</span>\
									</div>\
									<div class="col-sm-8">\
										<span>: </span>\
										<span>' + item.mean + '</span>\
									</div>\
								</div>\
							</li>');
					});
				} else {
					$(".word-list").append('<li class="list-group-item">Không tìm thấy kết quả.</li>');
				}
				
			},
			error: function() {
				console.log("fail!");
			}
		});
	}
	$(document).ready(function(){
		// type = 1 ENG => VIE
		// type = 2 VIE => ENG
		$("#type").val(2);
		$("#convert-icon").click(function(){
			var language_title = $("#language").text();
			var language_title_convert = $("#language-convert").text();
			var text_translate = $("#text-translate").val();
			
			$("#language").text(language_title_convert);
			$("#language-convert").text(language_title);
			$("#text-search").val(text_translate);
			
			if($("#language").text() == "Tiếng Việt") {
				$("#type").val(2);
			} else {
				$("#type").val(1);
			}
			showListSearch()
			absoluteSearch();
			relativeSearch();
		});
		$("#text-search").keydown(function(){
			showListSearch()
			absoluteSearch();
			relativeSearch();
		});
		$("#text-search").keyup(function(){
			showListSearch()
			absoluteSearch();
			relativeSearch();
		});
	});
</script>
</html>