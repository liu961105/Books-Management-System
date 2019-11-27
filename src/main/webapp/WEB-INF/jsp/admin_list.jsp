<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="taglib.jsp"%>
<html>
<head>
<title>全部</title>
<style>
body {
	background-color: rgb(240, 242, 245);
}
</style>

</head>
<body>
	<c:if test="${!empty info}">
		<script>alert("${info}");
			window.location.href = "${ctx}/admin/allAdmin"
		</script>
	</c:if>
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
						<li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        管理员管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${ctx}/admin/allAdmin">全部管理员</a></li>
                         <li class="divider"></li>
                            <li><a href="${ctx}/admin/toAddadmin">增加管理员</a></li>
                    </ul>
                </li>
					<li><a href="${ctx}/admin_repasswd.html"> 密码修改 </a></li>
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
	<div style="position: relative;top: 15%">
		<c:if test="${!empty succ}">
			<div class="alert alert-success alert-dismissable">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				${succ}
			</div>
		</c:if>
		<c:if test="${!empty error}">
			<div class="alert alert-danger alert-dismissable">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				${error}
			</div>
		</c:if>
	</div>


	<div class="panel panel-default"
		style="position:relative;top: 80px;width: 90%;margin-left: 5%">
		<div class="panel-heading">
			<h3 class="panel-title">全部读者</h3>
		</div>
		<div class="panel-body">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>管理员编号</th>
						<th>姓名</th>
						<th>性别</th>
						<th>年龄</th>
						<th>地址</th>
						<th>电话</th>
						<th>编辑</th>
						<th>删除</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${admins}" var="admin">
						<tr>
							<td><c:out value="${admin.adminId}"></c:out></td>
							<td><c:out value="${admin.adminName}"></c:out></td>
							<td><c:out value="${admin.adminage}"></c:out></td>
							<td><c:out value=""></c:out></td>
							<td><c:out value=""></c:out></td>
								<td><c:out value=""></c:out></td>
							<td><a
								href="reader_edit.html?readerId=<c:out value="${reader.readerId}"></c:out>"><button
										type="button" class="btn btn-info btn-xs">编辑</button></a></td>
							<td><a
								href="reader_delete.html?readerId=<c:out value="${reader.readerId}"></c:out>"><button
										type="button" class="btn btn-danger btn-xs">删除</button></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
