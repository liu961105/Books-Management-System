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
							<td><c:out value="${admin.gender }"></c:out></td>
							<td><c:out value="${admin.adminage}"></c:out></td>
							<td><c:out value="${admin.address }"></c:out></td>
							<td><c:out value="${admin.phone }"></c:out></td>
							<td><button 	type="button" class="btn btn-info btn-xs editBtn" data-pkid = "${ admin.adminId}">编辑</button></td>
							<td><button type="button" class="btn btn-danger btn-xs deleteBtn" data-pkid  = "${admin.adminId}">删除</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(".deleteBtn").on("click",function(res){
		var thisId = $(this).data('pkid');
		layer.open({
			content : '您确认删除该数据吗?',
			btn : [ '确认', '取消' ],
			yes : function(index, layero) {
				$.post("${ctx}/admin/deleteAdmin", {
					"adminId" : thisId
				}, function(res) {
					if (res.success == "1") {
						layer.msg("刪除成功！", {
							icon : 6
						})
						window.location.href = "${ctx}/admin/allAdmin";
					}
				})
			},
			btn2 : function(index, layero) {
				//取消按钮
			}
		});
	})
	$(".editBtn").on('click',function(){
		var thisId = $(this).data('pkid');
		window.location.href = "${ctx}/admin/toEditAdmin?adminId="+thisId;
	})
</script>
</html>
