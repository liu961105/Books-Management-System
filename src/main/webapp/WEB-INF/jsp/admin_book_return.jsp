<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="taglib.jsp"%>
<html>
<head>
<title>归还《 ${book.name}》</title>
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
				<h3 class="panel-title">归还《 ${book.name}》</h3>
			</div>
			<div class="panel-body">
				<form action="returnbook.html?id=${book.bookId}" method="post"
					id="lendbook">
					<div class="input-group">
						<span class="input-group-addon">书名</span> <input type="text"
							readonly="readonly" class="form-control" name="name" id="name"
							value="${book.name}">
					</div>
					<br />
					<div class="input-group">
						<span class="input-group-addon">读者二维码</span> <input type="text"
							class="form-control" id="readerId" name = "readerId" placeholder="请扫描/输入归还人二维码">
					</div>
					<br /> <input type="submit" value="确定归还"
						class="btn btn-success btn-sm" class="text-left">
					<script type="text/javascript">
						function mySubmit(flag) {
							return flag;
						}
						$("#lendbook").submit(function() {
							if ($("#readerId").val()=="") {
								return mySubmit(false);
							}
						})
					
					
						/* 	$("#readerId").change(function() {
								var readerId = $("#readerId").val()
								$.post("getReadName", {
									"readerId" : readerId
								}, function(res) {
									var data = res.data;
									if (res.success == "1") {
										$("#readName").val(data.name);
									}
								})
							}); */
					</script>
				</form>
			</div>
		</div>

	</div>

</body>
</html>
