<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<body>
	<nav
		style="position:fixed;z-index: 999;width: 100%;background-color: #fff"
		class="navbar navbar-default" role="navigation">
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
						<li class = "divider"></li>
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
</body>
</html>
