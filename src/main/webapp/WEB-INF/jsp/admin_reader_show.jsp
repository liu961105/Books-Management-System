
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="taglib.jsp"%>
<script src="${ctx}/static/js/jquery.PrintArea.js"></script>
<html>
<head>
<title>查看读者二维码《 ${readerInfo.readerId}》</title>
<style>
body {
	background-color: rgb(240, 242, 245);
}
</style>

</head>
<body>
	<div class="col-xs-6 col-md-offset-3"
		style="position: relative;top: 10%">
		<div class="panel panel-primary " style="width: 500px">
			<div class="panel-heading">
				<h3 class="panel-title">查看读者二维码信息《 ${readerInfo.readerId}》</h3>
			</div>

			<div class="panel-body" id="thisDiv">
				<h3>北京师范大学贵阳市附属小学</h3>
				<div class="input-group">
					<span class="input-group-addon">读者证号:${readerInfo.readerId}</span> 
					
				</div>
				<div class="input-group">
					<img src="img/lzn1501014107.jpg" style="width:100%" id="thisImg">
				</div>
			</div>
			<div class="input-group">
				<button id="dyBtn" class="layui-btn layui-btn-sm">打印二维码</button>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">

	$("#dyBtn").on('click', function() {
		$("#thisDiv").printArea();
	})
</script>
