
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
		<button type="button" class="layui-btn" id="import">批量导入</button>
		<form action="book_add_do.html" method="post" id="addbook">
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
			<div class="form-group">
				<label for="pressmark">总数量</label> <input type="text"
					class="form-control" name="number" id="number" placeholder="请输入数量">
			</div>

			<input type="submit" value="添加" class="btn btn-success btn-sm"
				class="text-left">

		</form>
	</div>

	<div id="importDiv" style="display: none;">
		<div class="layui-upload">
			<div class="layui-row" style="margin-top: 35px;">
				<div class="layui-col-xs12">
					<label class="layui-form-label requied">文件选择：</label>
					<div class="layui-input-block">
						<form class="form-x" method="post" enctype="multipart/form-data"
							id="uploadform">
							<div class="layui-upload">
								<button type="button" class="layui-btn layui-btn-normal"
									id="test8">选择文件</button>
							</div>
							<div class="layui-form-item" style="margin-top: 35px;">
								<div class="layui-input-block"
									style="margin: 0 auto; text-align: center;">
									<button type="button" class="layui-btn" id="test9">开始导入</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 完 -->
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
		layui.use('upload', function() {
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
		});
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
		/* 
		*通过扫描书的条形码，调用接口查询书籍信息如果未收录该书，则需要到基础数据维护中手动添加该书信息
		 */
		$("#isbn").change(function() {
			 var isbn = $("#isbn").val();
			 /*
			$.post("https://www.mxnzp.com/api/barcode/goods/details", {
				"barcode" : isbn
			}, function(res) {
				if (res.code == '0') { */
					$.post("${ctx}/bookDictionaries/checkISBN", {
						"isbn" : isbn
					}, function(res) {
						if (res.success == "1") {
						debugger
							$("#name").val(res.data.name);
							$("#author").val(res.data.author);
							$("#publish").val(res.data.publish)
							$("#introduction").val(res.data.introduction)
							$("#language").val(res.data.language)
							$("#price").val(res.data.price)
							$("#pubdate").val(res.data.pubdate)
							$("#classId").val(res.data.classId)
							$("#pressmark").val(res.data.pressmark)
							$("#state").val(res.data.state)
						} else {
							layer.msg("暂未收录此书,请前往基础数据管理维护", {
								icon : 5
							})
						}
					})
				/*}  else {
					$("#price").val(res.data.price)
					$("#name").val(res.data.goodsName);
				}
			}) */
		})
		$("#addbook").submit(function() {
			if ($("#name").val() == '' || $("#author").val() == '' || $("#publish").val() == '' || $("#isbn").val() == '' || $("#introduction").val() == '' || $("#language").val() == '' || $("#price").val() == '' || $("#pubdate").val() == '' || $("#classId").val() == '' || $("#pressmark").val() == '' || $("#state").val() == '') {
				layer.msg("请填入完整图书信息！", {
					icon : 5
				});
				return mySubmit(false);
			}
		})
		//图书批量导入
		$("#import").on("click", function() {
			layer.open({
				type : 1,
				title : "批量导入文件",
				area : [ '30%', '30%' ],
				content : $("#importDiv")
			})
		})
	});
</script>
