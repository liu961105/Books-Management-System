<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="taglib.jsp"%>
<html>
<head>
<title>全部读者</title>
<style>
body {
	background-color: rgb(240, 242, 245);
}
</style>
</head>
<body>
	<!-- 搜索框 -->
	<div style="padding: 70px 550px 10px;margin-bottom: -50px">
		<form method="post" action="${ctx}/queryReader" class="form-inline"
			id="searchform">
			<div class="input-group">
				<input type="text" placeholder="读者号/姓名" class="form-control"
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
					layer.msg("请输入读者姓名或读者号");
					return mySubmit(false);
				}
			})
			//input 框失去焦点事件
			$('#search').blur(function() {
				$("#searchform").submit();
			})
		</script>
	</div>
	<!-- 搜索框完 -->

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
		style="position:relative;top: 80px;width: 90%;margin-left: 5%">
		<div class="panel-heading">
			<h3 class="panel-title">全部读者</h3>
		</div>
		<div class="panel-body">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>读者号</th>
						<th>姓名</th>
						<th>性别</th>
						<th>学校</th>
						<th>班级</th>
						<th>生日</th>
						<th>地址</th>
						<th>电话</th>
						<th>编辑</th>
						<th>删除</th>
						<th>二维码</th>
						<th>借阅记录</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${readers}" var="reader">
						<tr>
							<td><c:out value="${reader.readerId}"></c:out></td>
							<td><c:out value="${reader.name}"></c:out></td>
							<td><c:out value="${reader.sex}"></c:out></td>
							<td><c:out value="${reader.schoolName}"></c:out></td>
							<td><c:out value="${reader.className}"></c:out></td>
							<td><c:out value="${reader.birth}"></c:out></td>
							<td><c:out value="${reader.address}"></c:out></td>
							<td><c:out value="${reader.telcode}"></c:out></td>
							<td><a
								href="reader_edit.html?readerId=<c:out value="${reader.readerId}"></c:out>"><button
										type="button" class="btn btn-info btn-xs">编辑</button></a></td>
							<td><a
								href="reader_delete.html?readerId=<c:out value="${reader.readerId}"></c:out>"><button
										type="button" class="btn btn-danger btn-xs">删除</button></a></td>
							<td><a
								href="reader_show?readerId=<c:out value="${reader.readerId}"></c:out>"><button
										type="button" class="btn btn-info btn-xs">查看二维码</button></a></td>
							<td><button type="button" class="btn btn-success btn-xs showLendBtn"  data-pkid="${reader.readerId}">查看借阅记录</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	$(".showLendBtn").on('click',function(){
		var thisId = $(this).data('pkid');
		window.location.href = "${ctx}/showReaderLend?readerId="+thisId;
	})
</script>
