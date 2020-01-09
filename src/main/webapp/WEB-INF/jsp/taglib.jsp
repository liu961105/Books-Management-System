<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 设置一个项目路径的变量  -->

<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%@ include file = "public.jsp" %>
<script src="${ctx}/static/js/jquery-3.2.1.js"></script>
<script src="${ctx}/static/js/bootstrap.min.js"></script>
<script src="${ctx}/static/layui/layui.js"></script>
<script src="${ctx}/static/layer/layer.js"></script>
<link rel="stylesheet" href="${ctx}/static/layui/css/layui.css">
<link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css">

