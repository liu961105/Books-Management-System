<%@ page import="com.book.domain.Book"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file = "one_taglib.jsp" %>
<html>
<head>
<title>查看借阅记录</title>

<style>
body {
	background-color: rgb(240, 242, 245);
}
</style>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation"
		style="background-color:#fff">
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
							<li class="divider"></li>
							<li><a href="${ctx}/bookLanguage/getBooKLanguage">书籍语言</a></li>
							<li><a href="${ctx }/bookLanguage/toAddLanguage">语言维护</a></li>
						</ul>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> 读者管理 <b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a href="${ctx}/allreaders.html">全部读者</a></li>
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
							<li><a href="${ctx }/admin/allAdmin">全部管理员</a></li>
							<li class="divider"></li>
							<li><a href="${ctx }/admin/toAddadmin">增加管理员</a></li>
						</ul></li>
					<li><a href="${ctx }/admin_repasswd.html"> 密码修改 </a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${ctx }/login.html"><span
							class="glyphicon glyphicon-user"></span>&nbsp;${admin.adminId}，已登录</a></li>
					<li><a href="${ctx }/logout.html"><span
							class="glyphicon glyphicon-log-in"></span>&nbsp;退出</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- 搜索框 -->
	<div style="padding: 7px 550px 10px;margin-bottom: -70px">
		<form method="post" action="${ctx }/queryreaderLend" class="form-inline"
			id="searchform">
			<div class="input-group">
				<input type="text" placeholder="图书名/图书号/状态" class="form-control"
					id="search" name="searchWord" class="form-control"> <span
					class="input-group-btn"> <input type="submit" value="搜索"
					class="btn btn-default">
				</span>
			</div>
		</form>
	</div>
	<!--  搜索框完-->
	<div style="position: relative;top: 10%">
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
		style="width: 90%;margin-left: 5%;margin-top: 5%">
		<div class="panel-heading">
			<h3 class="panel-title">借阅记录</h3>
		</div>
		<div class="panel-body">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>图书号</th>
						<th>图书名</th>
						<th>借出日期</th>
						<th>归还日期</th>
						<th>状态</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lendList}" var="alog">
						<tr>
							<td><c:out value="${alog.bookId}"></c:out></td>
							<td><c:out value="${alog.bookName}"></c:out></td>
							<td><c:out value="${alog.lendDate}"></c:out></td>
							<td><c:out value="${alog.backDate}"></c:out></td>
							<c:if test="${empty alog.backDate}">
								<td>未还</td>
							</c:if>
							<c:if test="${!empty alog.backDate}">
								<td>已还</td>
							</c:if>
							<c:if test="">
								<td>超期</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	function mySubmit(flag) {
		return flag;
	}
	$("#searchform").submit(function() {
		var val = $("#search").val();
		if (val == '') {
			layer.msg("请输入书名/书号/状态");
			return mySubmit(false);
		}
	})
	//input 框失去焦点事件
	$('#search').blur(function() {
		$("#searchform").submit();
	})
</script>
