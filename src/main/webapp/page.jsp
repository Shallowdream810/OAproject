<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<base href="${pageContext.request.contextPath}/">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title>附件管理</title>
    <link rel="stylesheet" type="text/css" href="skin/css/base.css">
    <script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
    <script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        function commit() {
            var pageNum = $("#pageNum1").val();
            var r=/^[0-9]*[1-9][0-9]*$/;
            if (!r.test(pageNum)){
                alert("请输入正确的页码数")
                $("#pageNum").val("")
            } else
                window.location.href="${requestURI}?pageNum="+pageNum;
        }
    </script>
</head>
<tr id="pageInfo" align="right" bgcolor="#EEF4EA">
    <td height="36" colspan="12" align="center">
        <div>
            当前第${page.pageNum}页,共${page.pages}页,共${page.total}条数据
            <a href="${requestURI}?pageNum=1${requestInfo}">首页</a>
            <a href="${requestURI}?pageNum=${page.pageNum-1}${requestInfo}">上一页</a>
            <c:forEach items="${page.navigatepageNums}" var="pageNumA">
                <c:if test="${pageNumA == page.pageNum}">
                    <a style="font-weight: bold" href="#">${pageNumA}</a>
                </c:if>
                <c:if test="${pageNumA != page.pageNum}">
                    <a href="${requestURI}?pageNum=${pageNumA}${requestInfo}">${pageNumA}</a>
                </c:if>
            </c:forEach>
            <a href="${requestURI}?pageNum=${page.pageNum+1}${requestInfo}">下一页</a>
            <a href="${requestURI}?pageNum=${page.pages}${requestInfo}">尾页</a>
            <input id="pageNum1" value="1" name="pageNum" type="text" size="1"/>
            <button onclick="commit()">跳转</button>
        </div>
    </td>
</tr>
