<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="taglib.jsp" %>
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

<div class="panel panel-default"
     style="position:relative;top: 80px;width: 90%;margin-left: 5%">
    <div class="panel-heading">
        <h3 class="panel-title">全部</h3>
    </div>
    <div class="panel-body">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>序号</th>
                <th>书架编码</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${bookPressmarks}" var="bookPressmark" varStatus="status">
                <tr>
                    <td><c:out value="${status.index+1}"></c:out></td>
                    <td><c:out value="${bookPressmark.code}"></c:out></td>
                    <td>
                        <button type="button" class="btn btn-info btn-xs editBtn" data-pkid="${bookPressmark.id}">编辑
                        </button>
                    </td>
                    <td>
                        <button type="button" class="btn btn-danger btn-xs deleteBtn" data-pkid="${bookPressmark.id}">
                            删除
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
<script type="text/javascript">
    $(".deleteBtn").on("click", function (res) {
        var thisId = $(this).data('pkid');
        layer.open({
            content: '您确认删除该数据吗?',
            btn: ['确认', '取消'],
            yes: function (index, layero) {
                $.post("${ctx}/deleteBookPressmark", {
                    "id": thisId
                }, function (res) {
                    if (res.success == "1") {
                        layer.msg("刪除成功！", {
                            icon: 6
                        })
                        window.location.href = "${ctx}/getBookPressmark";
                    }
                })
            },
            btn2: function (index, layero) {
                //取消按钮
            }
        });
    })
    $(".editBtn").on('click', function () {
        var thisId = $(this).data('pkid');
        window.location.href = "${ctx}/editBookPressmark?id=" + thisId;
    })
</script>
</html>
