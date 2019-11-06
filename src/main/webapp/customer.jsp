<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<base href="${pageContext.request.contextPath}/">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<base href="${pageContext.request.contextPath}">
<%
    String bath = request.getContextPath();
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title>客户信息管理</title>
    <link rel="stylesheet" type="text/css" href="skin/css/base.css">
    <script type="text/javascript" src="<%=bath%>/static/js/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        function choseAll() {
            $("input:checkbox").prop("checked", "checked");
        }
        function invert() {
            $("input:checkbox").each(function () {
                var b = $(this).prop("checked");
                $(this).prop("checked", !b);
            })
        }
// 批量删除
        function deletebyId() {
            var ids = "";
            $("input:checkbox:checked").each(function () {
                var id = $(this).val();
                ids = ids + id + ",";
            })
            ids = ids.substring(0, ids.length - 1);
            if (ids.length = null || ids == "") {
                alert("请选择要删除的数据");
                return;
            }
            if (confirm("确定要删除所选择的数据么")){
                $.ajax({
                    type: "post",
                    url: "<%=bath%>/cust/delete/" + ids,
                    data: {"_method": "delete"},
                    success: function (msg) {
                        alert(msg.msage);
                        window.location.href = "<%=bath%>/cust/list";
                    }
                });
            }
        }
        // 导出excel表格
        function exportExcel() {
            $.ajax({
                type:"get",
                url:"cust/exportExcel",
                success:function (msg) {
                    if (msg.statue == 200){
                        alert(msg.message1);
                    }
                }
            });
        }
        function importExcel() {
            var file =  $("#file")[0].files[0];
            var formdata = new  FormData()
            formdata.append("file",file);
            $.ajax({
                type:"post",
                url:"cust/importExcel",
                data:formdata,
                cache:false,
                processData:false,
                contentType:false,
                success:function (msg) {
                    if (msg.status == 200){
                        alert(msg.message);
                    }
                }
            })
        }
    </script>
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>
<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
    <tr>
        <td height="26" background="skin/images/newlinebg3.gif">
            <table width="58%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td>
                        当前位置:客户信息管理>>客户信息
                    </td>
                    <td>
                        <input type='button' class="coolbg np" onClick="location='customer-add.jsp';" value='添加客户信息'/>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<!--  搜索表单  -->
<form name='form3' action='<%=bath%>/cust/search' method='get'>
    <input type='hidden' name='dopost' value=''/>
    <table width='98%' border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center"
           style="margin-top:8px">
        <tr bgcolor='#EEF4EA'>
            <td background='skin/images/wbg.gif' align='center'>
                <table border='0' cellpadding='0' cellspacing='0'>
                    <tr>
                        <td width='90' align='center'>搜索条件：</td>
                        <td width='160'>
                            <select name='cid' style='width:150px'>
                                <option value='0'>选择类型...</option>
                                <option value='1'>公司名称</option>
                                <option value='2'>联系人姓名</option>
                            </select>
                        </td>
                        <td width='70'>
                            关键字：
                        </td>
                        <td width='160'>
                            <input type='text' name='keyword' value='' style='width:120px'/>
                        </td>
                        <td width='110'>
                            <select name='orderby' style='width:120px'>
                                <option value='0'>默认</option>
                                <option value='1'>降序</option>
                            </select>
                        </td>
                        <td>
                            &nbsp;&nbsp;&nbsp;<input id="imageField" name="imageField" type="image"
                                                     src="skin/images/frame/search.gif" width="45" height="20"
                                                     border="0" class="np"/>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
<!--  内容列表   -->
<form name="form2">

    <table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center"
           style="margin-top:8px">
        <tr bgcolor="#E7E7E7">
            <td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;需求列表&nbsp;</td>
        </tr>
        <tr align="center" bgcolor="#FAFAF1" height="22">
            <td width="4%">选择</td>
            <td width="6%">序号</td>
            <td width="10%">联系人</td>
            <td width="10%">公司名称</td>
            <td width="8%">添加时间</td>
            <td width="8%">联系电话</td>
            <td width="10%">操作</td>
        </tr>
        <c:forEach items="${custlist}" var="list" varStatus="i">
            <tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';"
                onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
                <td><input name="id" type="checkbox" id="id" value="${list.id}" class="np"></td>
                <td>${i.count}</td>
                <td>${list.companyperson}</td>
                <td align="center">${list.comname}</td>
                <td>
                    <fmt:formatDate value="${list.addtime}" pattern="yyyy-MM-dd"></fmt:formatDate>
                </td>
                <td>${list.comphone}</td>
                <td><a href="cust/findById/${list.id}">编辑</a> | <a href="cust/detail/${list.id}">查看详情</a></td>
            </tr>
        </c:forEach>
        <tr bgcolor="#FAFAF1">
            <td height="28" colspan="12">
                &nbsp;
                <a href="javascript:choseAll()" class="coolbg">全选</a>
                <a href="javascript:invert()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="javascript:deletebyId()" class="coolbg">&nbsp;删除&nbsp;</a>
                <a href="javascript:exportExcel()" class="coolbg">&nbsp;导出Excel&nbsp;</a>
                &nbsp;&nbsp;&nbsp;<input id="file"  type="file"/>
                <button onclick="importExcel()">导入</button>
            </td>
        </tr>
        <tr align="right" bgcolor="#EEF4EA">
            <td height="36" colspan="12" align="center"><!--翻页代码 --></td>
        </tr>
    </table>
</form>
</body>
</html>