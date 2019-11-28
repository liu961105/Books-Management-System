<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="taglib.jsp"%>
<html>
<head>
<title>编辑《 ${classInfo.className}》</title>
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
				<h3 class="panel-title">编辑《 ${classInfo.className}》</h3>
			</div>
			<div class="panel-body">
				<form action="classInfoEdit?id=${classInfo.classId}"
					method="post" id="addbook">
					<div class="input-group">
						<span class="input-group-addon">分类编码</span> <input type="text"
							class="form-control" name="classId" id="classId" readonly="readonly"
							value="${classInfo.classId}">
					</div>
					<div class="input-group">
						<span class="input-group-addon">分类名称</span> <input type="text"
							class="form-control" name="className" id="className"
							value="${classInfo.className}">
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
