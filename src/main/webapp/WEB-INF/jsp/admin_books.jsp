<%@ page import="com.book.domain.Book" %>
<%@include file="taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${ctx}/static/js/jquery.PrintArea.js"></script>
<html>
<head>
    <title>全部图书信息</title>
    <style>
        body {
            background-color: rgb(240, 242, 245);
        }
    </style>
</head>
<body>
<div style="padding: 70px 550px 10px">
    <form method="post" action="querybook.html" class="form-inline"
          id="searchform">
        <div class="input-group">
            <input type="text" placeholder="图书名/ISBN" class="form-control"
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

        $("#searchform").submit(function () {
            var val = $("#search").val();
            if (val == '') {
                layer.msg("请输入图书名或扫描ISBN");
                return mySubmit(false);
            }
        })
        //input 框失去焦点事件
        $('#search').blur(function () {
            $("#searchform").submit();
        })
    </script>
</div>
<div style="position: relative;top: 10%">
    <c:if test="${!empty succ}">
        <div class="alert alert-success alert-dismissable">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">&times;
            </button>
                ${succ}
        </div>
    </c:if>
    <c:if test="${!empty error}">
        <div class="alert alert-danger alert-dismissable">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">&times;
            </button>
                ${error}
        </div>
    </c:if>
</div>
<div class="panel panel-default" style="width: 90%;margin-left: 5%">
    <div class="panel-heading" style="background-color: #fff">
        <h3 class="panel-title">全部图书</h3>
    </div>
    <div class="panel-body">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>图书号</th>
                <th>书名</th>
                <th>作者</th>
                <th>出版社</th>
                <th>ISBN</th>
                <th>价格</th>
                <th>总数量</th>
                <th>在馆数</th>
                <th>借出数</th>
                <th>编码</th>
                <th>分类</th>
                <th>借阅</th>
                <th>归还</th>
                <th>详情</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${books}" var="book" varStatus="item">
                <tr>
                    <td><c:out value="${book.bookId}"></c:out></td>
                    <td><c:out value="${book.name}"></c:out></td>
                    <td><c:out value="${book.author}"></c:out></td>
                    <td><c:out value="${book.publish}"></c:out></td>
                    <td><c:out value="${book.isbn}"></c:out></td>
                    <td><c:out value="${book.price}"></c:out></td>
                    <td><c:out value="${book.number}"></c:out></td>
                    <td><c:out value="${book.inNumber}"></c:out></td>
                    <td><c:out value="${book.lendNumber}"></c:out></td>
                    <td>
                        <button type="button"
                                class="btn btn-info btn-xs tmBtn"
                                data-pkid="${book.bookId}">唯一编码
                        </button>
                    </td>
                    <td>
                        <button type="button" class="btn btn-default btn-xs flBtn" data-pkid="${book.bookId}">分类编码
                        </button>
                    </td>
                    <td><a
                            href="lendbook.html?bookId=<c:out value="${book.bookId}"></c:out>">
                        <button
                                type="button" class="btn btn-primary btn-xs">借阅
                        </button>
                    </a></td>
                    <td><a
                            href="returnbook?bookId=<c:out value="${book.bookId}"></c:out>">
                        <button
                                type="button" class="btn btn-primary btn-xs">归还
                        </button>
                    </a></td>
                    <td><a
                            href="bookdetail.html?bookId=<c:out value="${book.bookId}"></c:out>">
                        <button
                                type="button" class="btn btn-success btn-xs">详情
                        </button>
                    </a></td>
                    <td><a
                            href="updatebook.html?bookId=<c:out value="${book.bookId}"></c:out>">
                        <button
                                type="button" class="btn btn-info btn-xs">编辑
                        </button>
                    </a></td>
                    <td><a
                            href="deletebook.html?bookId=<c:out value="${book.bookId}"></c:out>">
                        <button
                                type="button" class="btn btn-danger btn-xs">删除
                        </button>
                    </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <%--			<ul class="pager">--%>
        <%--				<li><a href="#">&laquo;</a></li>--%>
        <%--				<li class="active"><a href="#">1</a></li>--%>
        <%--				<li ><a href="#">2</a></li>--%>
        <%--				<li><a href="#">3</a></li>--%>
        <%--				<li><a href="#">4</a></li>--%>
        <%--				<li><a href="#">5</a></li>--%>
        <%--				<li><a href="#">&raquo;</a></li>--%>
        <%--			</ul>--%>
    </div>
</div>

<div id="importDiv" style="display: none;">
    <div class="layui-upload">
        <div class="layui-row" style="margin-top: 35px;">
            <div class="layui-col-xs12">
                <label class="layui-form-label requied">条形码:</label>
                <div id="thisDiv">
                    <div class="layui-input-block">
                        <div class="layui-upload">
                            <h3>北京师范大学贵阳市附属小学</h3>
                            <img alt="" src="" id="thisImg">
                            <div>
                                <label id="tsbm" style="font-size: 30px;margin-left: 24px"></label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item" style="margin-top: 35px;">
                    <div class="layui-input-block"
                         style="margin: 0 auto; text-align: center;">
                        <button type="button" class="layui-btn" id="dyBtn">打印</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="flDiv" style="display: none">
    <div class="layui-form-item" id="flDyDiv">
        <h3 id="thisFl"></h3>
    </div>
    <div class="layui-form-item" style="margin-top: 35px;">
        <div class="layui-input-block"
             style="margin: 0 auto; text-align: center;">
            <button type="button" class="layui-btn" id="fldyBtn">打印</button>
        </div>
    </div>
</div>

</body>
</html>
<script type="text/javascript">
    $(".tmBtn").on('click', function () {
        var bookid = $(this).data('pkid');
        $.get("https://www.mxnzp.com/api/barcode/create", {
            "content": bookid,
            "width": 300,
            "height": 100,
            "type": 0
        }, function (res) {
            if (res.code == 1) {

                var barCodeUrl = res.data.barCodeUrl;
                layer.open({
                    type: 1,
                    title: "图书条形码",
                    area: ['30%', '40%'],
                    content: $("#importDiv")
                })
                /* $.post("

                ${ctx }/findBook", {"bookId": bookid}, function (res) {

                    if (res.success == "1") {
                        var flbm = res.data.pubdate + "/" + res.data.classId/!*+"/"+res.data.pressmark*!/;
                        $("#flbm").text(flbm);
                    }
                })*/
                $("#thisImg").attr("src", barCodeUrl)
                $("#tsbm").text(bookid);
            }
        })
    })
    $("#dyBtn").click(function () {
        $("#thisDiv").printArea();
    })
    $(".flBtn").click(function () {
        var bookId = $(this).data('pkid');
        debugger
        layer.open({
            type: 1,
            title: '图书分类',
            content: $("#flDiv"),
            area: ['20%', '20%']
        })
        $.post("${ctx}/findBook", {"bookId": bookId}, function (res) {
            if (res.success == '1') {
                var flbm = res.data.classId + "/" + res.data.pressmark + ":" + res.data.row + ":" + res.data.column;
                $("#thisFl").text(flbm);
            } else {
                $("#thisFl").text("数据未查到");
            }
        })
    })
    $("#fldyBtn").click(function () {
        fldyFun();
    })

    function fldyFun() {
        $("#flDyDiv").printArea();
    }
</script>
