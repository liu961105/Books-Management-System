
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="taglib.jsp"%>
<html>
<head>
<title>图书信息添加</title>
<style>
body {
	background-color: rgb(240, 242, 245);
}
</style>

</head>
<body>
	<div style="position: relative;top: 10%;width: 80%;margin-left: 10%">
		<form action="book_add_do.html" method="post" id="addbook">
			<div class="form-group">
				<label for="name">图书名</label> <input type="text"
					class="form-control" name="name" id="name" placeholder="请输入书名">
			</div>
			<div class="form-group">
				<label for="author">作者</label> <input type="text"
					class="form-control" name="author" id="author" placeholder="请输入作者名">
			</div>
			<div class="form-group">
				<label for="publish">出版社</label> <input type="text"
					class="form-control" name="publish" id="publish"
					placeholder="请输入出版社">
			</div>
			<div class="form-group">
				<label for="isbn">ISBN</label> <input type="text"
					class="form-control" name="isbn" id="isbn" placeholder="请扫描ISBN">
			</div>
			<div class="form-group">
				<label for="introduction">简介</label>
				<textarea class="form-control" rows="3" name="introduction"
					id="introduction" placeholder="请输入简介"></textarea>
			</div>
			<div class="form-group">
				<label for="language">语言</label> <input type="text"
					class="form-control" name="language" id="language"
					placeholder="请输入语言">
			</div>
			<div class="form-group">
				<label for="price">价格</label> <input type="text"
					class="form-control" name="price" id="price" placeholder="请输入价格">
			</div>
			<div class="form-group">
				<label for="pubdate">出版日期</label> <input type="text"
					class="form-control" name="pubdate" id="pubdate">
			</div>
			<div class="form-group">
				<label for="classId">分类号</label> <select class="form-control"
					name="classId" id="classId">
					<option>请选择分类号</option>

				</select>
			</div>
			<div class="form-group">
				<label for="pressmark">书架号</label> <input type="text"
					class="form-control" name="pressmark" id="pressmark"
					placeholder="请输入书架号">
			</div>
			<div class="form-group">
				<label for="state">状态</label> <select class="form-control"
					name="state" id="state">
					<option>请选择图书当前状态</option>
					<option value="0">已借出</option>
					<option value="1">未借出</option>
				</select>
			</div>


			<input type="submit" value="添加" class="btn btn-success btn-sm"
				class="text-left">

		</form>

	</div>



</body>
</html>

<script type="text/javascript">
//layui 时间组件运用
layui.use('laydate', function(){
  var laydate = layui.laydate;
  laydate.render({
    elem: '#pubdate'
    ,trigger: 'click'
  });
});
	$(function() {
		getClassInfo();
	})
	function getClassInfo() {
		$.post("${ctx}/classInfo/findClassInfos", {}, function(res) {
			if (res.success == '1') {
				for(var i =0;i<res.data.length;i++){
					$("#classId").append('<option value="' + res.data[i].classId + '">' + res.data[i].className + '</option>');
				}
			}
		})
	}
	function mySubmit(flag) {
		return flag;
	}
	$("#addbook").submit(function() {
		if ($("#name").val() == '' || $("#author").val() == '' || $("#publish").val() == '' || $("#isbn").val() == '' || $("#introduction").val() == '' || $("#language").val() == '' || $("#price").val() == '' || $("#pubdate").val() == '' || $("#classId").val() == '' || $("#pressmark").val() == '' || $("#state").val() == '') {
			layer.msg("请填入完整图书信息！", {
				icon : 5
			});
			return mySubmit(false);
		}
	})
	
</script>
