<%@include file="taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>全部图书分类</title>
<style>
body {
	background-color: rgb(240, 242, 245);
}
</style>
</head>
<body>

	<%-- <nav  style="position:fixed;z-index: 999;width: 100%;background-color: #fff" class="navbar navbar-default" role="navigation" >
    <div class="container-fluid">
        <div class="navbar-header" style="margin-left: 8%;margin-right: 1%">
            <a class="navbar-brand" href="${ctx}/admin_main.html">图书管理系统</a>
        </div>
        <div class="collapse navbar-collapse" >
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
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        读者管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${ctx}/allreaders.html">全部读者</a></li>
                        <li class="divider"></li>
                        <li><a href="${ctx}/reader_add.html">增加读者</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        借还管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${ctx}/lendlist.html">借还日志</a></li>
                    </ul>
                </li>
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
                <li >
                    <a href="${ctx}/admin_repasswd.html" >
                        密码修改
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${ctx}/login.html"><span class="glyphicon glyphicon-user"></span>&nbsp;${admin.adminId}，已登录</a></li>
                <li><a href="${ctx}/logout.html"><span class="glyphicon glyphicon-log-in"></span>&nbsp;退出</a></li>
            </ul>
        </div>
    </div>
</nav> --%>

	<div style="padding: 70px 550px 10px">
		<form method="post" action="querybook.html" class="form-inline"
			id="searchform">
			<div class="input-group">
				<input type="text" placeholder="分类名" class="form-control"
					id="search" name="searchWord" class="form-control"> <span
					class="input-group-btn"> <input type="submit" value="搜索"
					class="btn btn-default">
				</span>
			</div>
		</form>
		<script>
			function mySubmit(flag) {
				return flag;
			}
			$("#searchform").submit(function() {
				var val = $("#search").val();
				if (val == '') {
					layer.msg("请输入图书分类");
					return mySubmit(false);
				}
			})
			//input 框失去焦点事件
			$('#search').blur(function() {
				$("#searchform").submit();
			})
		</script>
	</div>
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
	<div class="panel panel-default" style="width: 90%;margin-left: 5%">
		<div class="panel-heading" style="background-color: #fff">
			<h3 class="panel-title">全部分类</h3>
		</div>
		<div class="panel-body">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>图书分类编码</th>
						<th>图书分类名称</th>
						<th>详情</th>
						<th>编辑</th>
						<th>删除</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${classInfos}" var="classInfo">
						<tr>
							<td><c:out value="${classInfo.classId}"></c:out></td>
							<td><c:out value="${classInfo.className}"></c:out></td>
							<%--  <c:if test="${book.state==1}">
                    <td><a href="lendbook.html?bookId=<c:out value="${book.bookId}"></c:out>"><button type="button" class="btn btn-primary btn-xs">借阅</button></a></td>
                </c:if>
                <c:if test="${book.state==0}">
                    <td><a href="returnbook?bookId=<c:out value="${book.bookId}"></c:out>"><button type="button" class="btn btn-primary btn-xs">归还</button></a></td>
                </c:if> --%>
							<td><a
								href="classInfoDetail?classId=<c:out value="${classInfo.classId}"></c:out>"><button
										type="button" class="btn btn-success btn-xs">详情</button></a></td>
							<td><a
								href="toClassInfoEdit?classId=<c:out value="${classInfo.classId}"></c:out>"><button
										type="button" class="btn btn-info btn-xs">编辑</button></a></td>
							<td><button type="button" data-pkid="${classInfo.classId}"
									class="btn btn-danger btn-xs deleteBtn">删除</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
<script type="text/javascript">
	$(".deleteBtn").on('click', function() {
		var classId = $(this).data('pkid');
		layer.open({
			content : '您确认删除该数据吗?',
			btn : [ '确认', '取消' ],
			yes : function(index, layero) {
				$.post("${ctx}/classInfo/classInfoDelete", {
					"classId" : classId
				}, function(res) {
					if (res.success == "1") {
						layer.msg("刪除成功！", {
							icon : 6
						})
						window.location.href = "${ctx}/classInfo/getClassInfo";
					}
				})
			},
			btn2 : function(index, layero) {
				//取消按钮
			}
		});
	})
</script>
</html>
