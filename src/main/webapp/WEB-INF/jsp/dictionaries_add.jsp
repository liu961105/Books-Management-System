
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="taglib.jsp"%>
<html>
<head>
<title>图书字典添加</title>
<style>
body {
	background-color: rgb(240, 242, 245);
}
</style>

</head>
<body>
	<div style="position: relative;top: 10%;width: 80%;margin-left: 10%">
		<!-- <button type="button" class="layui-btn" id="import">批量导入</button> -->
		<form action="${ctx }/bookDictionaries/saveBook" method="get" id="addbook">
		<div class="form-group">
				<label for="isbn">ISBN</label> <input type="text"
					class="form-control" name="isbn" id="isbn" placeholder="请扫描ISBN">
			</div>
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
				<label for="introduction">简介</label>
				<textarea class="form-control" rows="3" name="introduction"
					id="introduction" placeholder="请输入简介"></textarea>
			</div>
			<div class="form-group">
				<label for="language">语言</label> <select class="form-control"
					id="language" name="language">
					<option>请选择语言</option>
				</select>
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
			<input type="submit" value="添加" class="btn btn-success btn-sm"
				class="text-left">
		</form>
	</div>
</body>
</html>

<script type="text/javascript">
	//layui 时间组件运用
	layui.use('laydate', function() {
		var laydate = layui.laydate;
		laydate.render({
			elem : '#pubdate',
			trigger : 'click'
		});
		/* layui.use('upload', function() {
			var $ = layui.jquery,
				upload = layui.upload;
			upload.render({
				elem : '#test8',
				url : '${ctx}/importExport', //
				method : "post",
				auto : false,
				bindAction : '#test9',
				accept : 'file',
				exts : 'xls|xlsx',
				done : function(res) {
					console.log(res);
					if (res.success == '1') {
						showOkTip(res.message);
						window.location.href = "${ctx}/allbooks.html"
					} else {
						alertError(res.message);
					}
				}
			});
		}); */
		$(function() {
			getClassInfo();
			getLanguage();
		})
		function getClassInfo() {
			$.post("${ctx}/classInfo/findClassInfos", {}, function(res) {
				if (res.success == '1') {
					for (var i = 0; i < res.data.length; i++) {
						$("#classId").append('<option value="' + res.data[i].classId + '">' + res.data[i].className + '</option>');
					}
				}
			})
		}
		
		function getLanguage() {
			$.post("${ctx}/bookLanguage/findLanguage", {}, function(res) {
				if (res.success == '1') {
					for (var i = 0; i < res.data.length; i++) {
						$("#language").append('<option value="' + res.data[i].languageNumber + '">' + res.data[i].languageName + '</option>');
					}
				}
			})
		}
		function mySubmit(flag) {
			return flag;
		}
		/* $("#isbn").change(function(){
				var isbn = $("#isbn").val();
			$.post("https://www.mxnzp.com/api/barcode/goods/details",{"barcode":isbn},function(res){
						if(res.code=='0'){
							layer.msg("暂未收录此书，请前往基础数据维护新增此书！",{icon:7})
						}else{
							layer.msg("做些什么")
						}
				})
		}) */
		$("#addbook").submit(function() {
			if ($("#name").val() == '' || $("#author").val() == '' || $("#publish").val() == '' || $("#isbn").val() == '' || $("#introduction").val() == '' || $("#language").val() == '' || $("#price").val() == '' || $("#pubdate").val() == '' || $("#classId").val() == '' || /*$("#pressmark").val() == '' ||*/ $("#state").val() == '') {
				layer.msg("请填入完整图书信息！", {
					icon : 5
				});
				return mySubmit(false);
			}
		})
		//图书批量导入
		/* $("#import").on("click", function() {
			layer.open({
				type : 1,
				title : "批量导入文件",
				area : [ '30%', '30%' ],
				content : $("#importDiv")
			})
		}) */
	});
</script>
