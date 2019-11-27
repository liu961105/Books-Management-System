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
	<nav
		style="position:fixed;z-index: 999;width: 100%;background-color: #fff"
		class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header" style="margin-left: 8%;margin-right: 1%">
				<a class="navbar-brand" href="admin_main.html">图书管理系统</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-left">
					 <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        图书管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${ctx }/allbooks.html">全部图书</a></li>
                        <li><a href="${ctx }/book_add.html">增加图书</a></li>
                           <li class="divider"></li>
                           <li><a href="${ctx}/classInfo/getClassInfo">全部分类</a></li>
                        <li><a href="${ctx }/classInfo/toAddClassInfo">分类维护</a></li>
                    </ul>
                </li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> 读者管理 <b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a href="allreaders.html">全部读者</a></li>
							<li class="divider"></li>
							<li><a href="${ctx}/reader_add.html">增加读者</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> 借还管理 <b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a href="${ctx}/lendlist.html">借还日志</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> 管理员管理 <b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a href="admin/allAdmin">全部管理员</a></li>
							<li class="divider"></li>
						     <li><a href="admin/toAddadmin">增加管理员</a></li>
						</ul></li>
					<li><a href="admin_repasswd.html"> 密码修改 </a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="login.html"><span
							class="glyphicon glyphicon-user"></span>&nbsp;${admin.adminId}，已登录</a></li>
					<li><a href="logout.html"><span
							class="glyphicon glyphicon-log-in"></span>&nbsp;退出</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div style="position: relative;top: 10%;width: 80%;margin-left: 10%">
		<form action="${ctx}/admin/adminSave" method="post" id="addbook">
			<div class="form-group">
				<label for="name">用户名</label> <input type="text"
					class="form-control" name="adminId" id="adminId"
					placeholder="请输入用户名">
			</div>
			<div class="form-group">
				<label for="author">真实姓名</label> <input type="text"
					class="form-control" name="adminName" id="adminName"
					placeholder="请输入姓名">
			</div>
			<div class="form-group">
				<label for="publish">年龄</label> <input type="text"
					class="form-control" name="adminage" id="adminage"
					placeholder="请输入年龄">
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
