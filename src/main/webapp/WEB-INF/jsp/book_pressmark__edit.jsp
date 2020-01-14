<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="taglib.jsp"%>
<html>
<head>
<title>编辑</title>
<style>
body {
	background-color: rgb(240, 242, 245);
}
</style>

</head>
<body>

	<div class="col-xs-6 col-md-offset-3"
		style="position: relative;top: 10%">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">编辑</h3>
			</div>
			<div class="panel-body">
				<form action="editBookPressmark_do?id=${bookPressmark.id}" method="post"
					id="addbook">
					<div class="input-group">
						<span class="input-group-addon">编码</span> <input type="text"
							class="form-control" name="code" id="code"  value="${bookPressmark.code}">
					</div>
					<input type="submit" value="确定" class="btn btn-success btn-sm"
						class="text-left">
					<script>
						function mySubmit(flag) {
							return flag;
						}
						$("#addbook").submit(function() {
					
							return mySubmit(true);
						})
					</script>
				</form>
			</div>
		</div>

	</div>

</body>
</html>
