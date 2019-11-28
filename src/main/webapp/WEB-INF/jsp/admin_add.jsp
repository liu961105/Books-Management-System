<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="taglib.jsp"%>
<html>
<head>
<title>添加管理员</title>
<style>
body {
	background-color: rgb(240, 242, 245);
}
</style>

</head>
<body>

	<div style="position: relative;top: 10%;width: 80%;margin-left: 10%">
		<form action="${ctx}/admin/adminSave" method="post" id="addbook">
			<div class="form-group">
				<label for="name">登录用户名</label> <input type="text"
					class="form-control" name="adminId" id="adminId"
					placeholder="请输入用户名">
			</div>
			<div class="form-group">
				<label for="author">真实姓名</label> <input type="text"
					class="form-control" name="adminName" id="adminName"
					placeholder="请输入姓名">
			</div>
			<div class="form-group">
				<label for="gender">性别</label> 
				<select class="form-control" name = "gender">
					<option>请选择</option>
					<option value="男">男</option>
					<option value = "女">女</option>
				</select>
			</div>
			<div class="form-group">
				<label for="adminage">年龄</label> <input type="text"
					class="form-control" name="adminage" id="adminage"
					placeholder="请输入年龄">
			</div>
			<div class="form-group">
				<label for="address">地址</label> <input type="text"
					class="form-control" name="address" id="address"
					placeholder="请输入地址">
			</div>
			<div class="form-group">
				<label for="phone">电话</label> <input type="text"
					class="form-control" name="phone" id="phone"
					placeholder="请输入电话">
			</div>
			<input type="submit" value="添加" class="btn btn-success btn-sm"
				class="text-left">

		</form>

	</div>



</body>
</html>

<script type="text/javascript">
	layui.use('layer', function() {
		var layer = layui.layer;

		function mySubmit(flag) {
			return flag;
		}

		$("#addbook").submit(function() {
			if (isNaN($("#adminId").val())) {
				layer.msg("用户名必须为纯数字！", {
					icon : 5
				});
				return mySubmit(false);
			}
		})
	});
</script>
