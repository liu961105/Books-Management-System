<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="taglib.jsp"%>
<html>
<head>
<title>详情</title>
<style>
body {
	background-color: rgb(240, 242, 245);
}
</style>

</head>
<body>
	<div class="col-xs-6 col-md-offset-3"
		style="position: relative;top: 10%">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">《 ${classInfo.className}》</h3>
			</div>
			<div class="panel-body">
				<table class="table table-hover">
					<tr>
						<th width="15%">分类编码</th>
						<td>${classInfo.classId}</td>
					</tr>
					<tr>
						<th>分类名称</th>
						<td>${classInfo.className}</td>
					</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
