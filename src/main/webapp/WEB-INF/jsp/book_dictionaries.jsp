<%@ page import="com.book.domain.Book" %>
<%@include file="taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图书字典</title>
    <style>
        body{
            background-color: rgb(240,242,245);
        }
    </style>
</head>
<body>
<div style="padding: 70px 550px 10px">
    <form   method="post" action="querybook.html" class="form-inline"  id="searchform">
        <div class="input-group">
           <input type="text" placeholder="图书名/ISBN" class="form-control" id="search" name="searchWord" class="form-control">
            <span class="input-group-btn">
                            <input type="submit" value="搜索" class="btn btn-default">
            </span>
        </div>
    </form>
    <script>
        function mySubmit(flag){
            return flag;
        }
        $("#searchform").submit(function () {
            var val=$("#search").val();
            if(val==''){
                layer.msg("请输入图书名或扫描ISBN");
                return mySubmit(false);
            }
        })
        //input 框失去焦点事件
        $('#search').blur(function(){
        	$("#searchform").submit();
        })
    </script>
</div>
<div style="position: relative;top: 10%">
<c:if test="${!empty succ}">
    <div class="alert alert-success alert-dismissable">
        <button type="button" class="close" data-dismiss="alert"
                aria-hidden="true">
            &times;
        </button>
        ${succ}
    </div>
</c:if>
<c:if test="${!empty error}">
    <div class="alert alert-danger alert-dismissable">
        <button type="button" class="close" data-dismiss="alert"
                aria-hidden="true">
            &times;
        </button>
        ${error}
    </div>
</c:if>
</div>
<div class="panel panel-default" style="width: 90%;margin-left: 5%">
    <div class="panel-heading" style="background-color: #fff">
        <h3 class="panel-title">
            全部图书
        </h3>
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
                <th>语言</th>
                <th>出版日期</th>
                <th>价格</th>
                <th>编辑</th>
              <!--   <th>删除</th> -->
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${books}" var="book">
            <tr>
            	    <td><c:out value="${book.bookId }"></c:out></td>
                <td><c:out value="${book.name}"></c:out></td>
                <td><c:out value="${book.author}"></c:out></td>
                <td><c:out value="${book.publish}"></c:out></td>
                <td><c:out value="${book.isbn}"></c:out></td>
                  <td><c:out value="${book.language }"></c:out></td>
                  <td><c:out value="${book.pubdate}"></c:out></td>
                <td><c:out value="${book.price}"></c:out></td>
                <td><a href="updatebook.html?bookId=<c:out value="${book.id}"></c:out>"><button type="button" class="btn btn-info btn-xs">编辑</button></a></td>
                <%-- <td><a href="deletebook.html?bookId=<c:out value="${book.bookId}"></c:out>"><button type="button" class="btn btn-danger btn-xs">删除</button></a></td> --%>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
