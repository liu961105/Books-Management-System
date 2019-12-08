<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="taglib.jsp"%>
<html>
<head>
<title>借阅《 ${book.name}》</title>
<style>
body {
	background-color: rgb(240, 242, 245);
}
</style>

</head>
<body>
	<div class="col-xs-6 col-md-offset-3"
		style="position: relative;top: 25%">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">借阅《 ${book.name}》</h3>
			</div>
			<div class="panel-body">
				<form action="lendbookdo.html?id=${book.bookId}" method="post"
					id="lendbook">
					<div class="input-group">
						<span class="input-group-addon">书名</span> <input type="text"
							readonly="readonly" class="form-control" name="bookName"
							id="bookName" value="${book.name}">
					</div>
					<br />
					<div class="input-group">
						<span class="input-group-addon">读者证号</span> <input type="text"
							class="form-control" name="readerId" id="readerId"
							placeholder="借阅人读者证号">
					</div>
					<div class="input-group">
						<span class="input-group-addon">读者姓名</span> <input type="text"
							class="form-control" name="readerName" id="readerName"
							placeholder="读者姓名">
					</div>
					<div class="input-group">
						<span class="input-group-addon">借阅天数</span> <input type="text"
							class="form-control" name="borrowingDay" id = "borrowingDay" placeholder="借阅天数">
					</div>
					<div class="input-group">
						<span class="input-group-addon">到期归还时间</span> <input type="text"
							class="form-control" name="returnDate" id="returnDate" readonly="readonly"
							placeholder="到期归还时间">
					</div>
					<br /> <input type="submit" value="确定"
						class="btn btn-success btn-sm" class="text-left">
					<script type="text/javascript">
						function mySubmit(flag) {
							return flag;
						}
						$("#borrowingDay").blur (function() {
							var borrowingDay = $(this).val();
							var now = new Date();
							now.setDate((now.getDate())*1+(borrowingDay*1))
							var returnDate =  now.getFullYear() + "-" + ((now.getMonth() + 1) < 10 ? "0" : "") + (now.getMonth() + 1) + "-" + (now.getDate() < 10 ? "0" : "") + now.getDate();
							$("#returnDate").val(returnDate);
						});
						$("#lendbook").submit(function() {
							if ($("#name").val() == '' || $("#readerId").val() == '') {
								layer.msg("请填入完整图书信息！", {
									icon : 7
								});
								return mySubmit(false);
							}
						})
					
						$("#readerId").change(function() {
							var readerId = $("#readerId").val()
							$.post("getReadName", {
								"readerId" : readerId
							}, function(res) {
								var data = res.data;
								if (res.success == "1") {
									$("#readerName").val(data.name);
									$.post("checkReaderLog", {
										"bookId" : '${book.bookId}',
										"readerId" : readerId
									}, function(res) {
										if (res.success == '1') {
											layer.msg("该读者已借阅过此书，并且未归还", {
												icon : 5
											})
										}
									})
								}
							})
						});
					</script>
				</form>
			</div>
		</div>

	</div>

</body>
</html>
