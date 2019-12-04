<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="taglib.jsp"%>
<html>
<head>
<title>添加读者</title>
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
				<h3 class="panel-title">添加读者</h3>
			</div>
			<div class="panel-body">
				<form action="reader_add_do.html" method="post" id="readeredit">
					<div class="input-group">
						<span class="input-group-addon">姓名</span> <input type="text"
							class="form-control" name="name" id="name">
					</div>
					<div class="input-group">
						<span class="input-group-addon">性别</span><select
							class="form-control" name="sex">
							<option>请选择性别</option>
							<option value="男">男</option>
							<option value="女">女</option>
						</select>
					</div>
					<div class="input-group">
						<span class="input-group-addon">学校</span> <input type="text"
							class="form-control" name="schoolName" id="schoolName">
					</div>
					<div class="input-group">
						<span class="input-group-addon">班级</span> <input type="text"
							class="form-control" name="className" id="className">
					</div>
					<div class="input-group">
						<span class="input-group-addon">生日</span> <input type="text"
							class="form-control" name="birth" id="birth">
					</div>
					<div class="input-group">
						<span class="input-group-addon">地址</span> <input type="text"
							class="form-control" name="address" id="address">
					</div>
					<div class="input-group">
						<span class="input-group-addon">电话</span> <input type="text"
							class="form-control" name="telcode" id="telcode">
					</div>
					<input type="submit" value="保存并生成二维码"
						class="btn btn-success btn-sm" class="text-left">
					<script>
						layui.use('laydate', function() {
							var laydate = layui.laydate;
							laydate.render({
								elem : '#birth',
								trigger : 'click'
							});
						});
						function mySubmit(flag) {
							return flag;
						}
						$("#readeredit").submit(function() {
							if ($("#name").val() == '') {
								layer.msg("请填入完整读者信息！");
								return mySubmit(false);
							}
						})
					</script>
				</form>
			</div>
		</div>

	</div>

</body>
</html>
