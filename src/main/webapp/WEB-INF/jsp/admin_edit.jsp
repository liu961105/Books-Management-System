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
				<h3 class="panel-title">编辑《 ${admin.adminName}》</h3>
			</div>
			<div class="panel-body">
				<form action="adminEdit?id=${admin.adminId}" method="post"
					id="addbook">
					<div class="input-group">
						<span class="input-group-addon">登录名</span> <input type="text"
							class="form-control" name="adminId" id="adminId"
							readonly="readonly" value="${admin.adminId}">
					</div>
					<div class="input-group">
						<span class="input-group-addon">姓名</span> <input type="text"
							class="form-control" name="adminName" id="adminName"
							value="${admin.adminName}">
					</div>
					<div class="input-group">
						<span class="input-group-addon">性别</span> <input type="text"
							class="form-control" name="gender" id="gender"
							value="${admin.gender}">
					</div>
					<div class="input-group">
						<span class="input-group-addon">年龄</span> <input type="text"
							class="form-control" name="adminage" id="adminage"
							value="${admin.adminage}">
					</div>
					<div class="input-group">
						<span class="input-group-addon">地址</span> <input type="text"
							class="form-control" name="address" id="address"
							value="${admin.address}">
					</div>
					<div class="input-group">
						<span class="input-group-addon">电话</span> <input type="text"
							class="form-control" name="phone" id="phone"
							value="${admin.phone}">
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
