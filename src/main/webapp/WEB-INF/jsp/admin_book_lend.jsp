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
	<%-- <nav
		style="position:fixed;z-index: 999;width: 100%;background-color: #fff"
		class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header" style="margin-left: 8%;margin-right: 1%">
				<a class="navbar-brand" href="admin_main.html">图书管理系统</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-left">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> 图书管理 <b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a href="${ctx }/allbooks.html">全部图书</a></li>
							<li><a href="${ctx }/book_add.html">增加图书</a></li>
							<li class="divider"></li>
							<li><a href="${ctx}/classInfo/getClassInfo">全部分类</a></li>
							<li><a href="${ctx }/classInfo/toAddClassInfo">分类维护</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> 读者管理 <b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a href="allreaders.html">全部读者</a></li>
							<li class="divider"></li>
							<li><a href="reader_add.html">增加读者</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> 借还管理 <b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a href="lendlist.html">借还日志</a></li>
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
	</nav> --%>

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
							readonly="readonly" class="form-control" name="name" id="name"
							value="${book.name}">
					</div>
					<br />
					<div class="input-group">
						<span class="input-group-addon">读者证号</span> <input type="text"
							class="form-control" name="readerId" id="readerId"
							placeholder="借阅人读者证号">
					</div>
					<div class="input-group">
						<span class="input-group-addon">读者姓名</span> <input type="text"
							class="form-control" name="readName" id="readName"
							placeholder="读者姓名">
					</div>
					<div class="input-group">
						<span class="input-group-addon">借阅天数</span> <input type="text"
							class="form-control" name="borrowingDay" placeholder="借阅天数">
					</div>
					<div class="input-group">
						<span class="input-group-addon">到期归还时间</span> <input type="text"
							class="form-control" name="returnDaty" id="returnDaty"
							placeholder="到期归还时间">
					</div>
					<br /> <input type="submit" value="确定"
						class="btn btn-success btn-sm" class="text-left">
					<script type="text/javascript">
						layui.use('laydate', function() {
							var laydate = layui.laydate;
							laydate.render({
								elem : '#returnDaty',
								trigger : 'click'
							});
						});
						function mySubmit(flag) {
							return flag;
						}
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
									$("#readName").val(data.name);
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
