<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="taglib.jsp"%>
<html>
<head>
<title>添加图书分类</title>
<style>
body {
	background-color: rgb(240, 242, 245);
}
</style>

</head>
<body>

	<div style="position: relative;top: 10%;width: 80%;margin-left: 10%">
		<form action="${ctx}/bookLanguage/languageSave" method="post"
			id="addbook">
			<div class="form-group">
				<label for="name">语言编码</label> <input type="text"
					class="form-control" name="languageNumber" id="languageNumber"
					placeholder="请输入语言">
			</div>
			<div class="form-group">
				<label for="author">语言名称</label> <input type="text"
					class="form-control" name="languageName" id="languageName"
					placeholder="请输入语言名称">
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
			if (("#languageName").val()==""||("#languageNumber").val()=="") {
				layer.msg("请输入完整信息！", {
					icon : 5
				});
				return mySubmit(false);
			}
		})
	});
</script>
