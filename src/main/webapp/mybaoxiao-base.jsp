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
	function addbaoxiao() {
		window.location.href="mybaoxiao-add.jsp";
    }
    function commit() {
        var pageNum = $("#pageNum1").val();
        var r=/^[0-9]*[1-9][0-9]*$/;
        if (!r.test(pageNum)){
            alert("请输入正确的页码数")
            $("#pageNum").val("")
        } else
            window.location.href="bx/findAll?pageNum="+pageNum;
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
  <td >
    当前位置:个人报销管理>>报销列表
 </td>
  <td>
    <input type='button' class="coolbg np" onclick="addbaoxiao()"  value='添加报销' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>
<form name='form3' action="bx/findAll" method="get">
	<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
		<tr bgcolor='#EEF4EA'>
			<td background='skin/images/wbg.gif' align='center'>
				<table border='0' cellpadding='0' cellspacing='0'>
					<tr>
						<td width='90' align='center'>搜索条件：</td>
						<td width='160'>
							<select ID="status" name='search_status' style='width:150px'>
								<option value=''>审批状态</option>
								<option value='0'>审批中</option>
								<option value='1'>驳回</option>
								<option value='2'>已审批</option>
								<option value='3'>已打款</option>
							</select>
						</td>
						<td width='70'>
							关键字：
						</td>
						<td width='160'>
							<input id="keyword" name="search_like_keyword" type='text' value='' style='width:120px' />
						</td>
						<td>
							<%--&nbsp;&nbsp;&nbsp;<input ONCLICK="commit()" name="imageField" type="image" src="skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />--%>
							<%--&nbsp;&nbsp;&nbsp;<input onclick="commit()" type="button" src="skin/images/frame/search.gif" width="45" height="20" border="0" class="np" value="搜索"/>--%>
							<button onclick="commit()">搜索</button>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>
<!--  内容列表   -->
<form name="form2">
<table width="98%"  cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;报销单列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="10%">编号</td>
	<td width="16%">总金额</td>
	<td width="10%">使用时间</td>
	<td width="40%">备注信息</td>
	<td width="10%">审批状态</td>
	<td width="10%">操作</td>
</tr>
	<c:forEach items="${page.list}" var="bs" varStatus="i">
		<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
			<td><input name="id" type="checkbox" id="id" value="${bs.bxid}" class="np"></td>
			<td>${i.count}</td>
			<td>${bs.totalmoney}</td>
			<td align="center"><a href=''><u> <fmt:formatDate value="${bs.bxtime}" pattern="yyyy-MM-dd"></fmt:formatDate> </u></a></td>
			<td>${bs.bxremark}</td>
			<c:if test="${bs.bxstatus == 0}">
				<td>审批中</td>
				<td><a href="mybaoxiao-edit.jsp?bxid=${bs.bxid}">编辑</a> </td>
			</c:if>
			<c:if test="${bs.bxstatus == 1}">
				<td style="color: red">驳回</td>
				<td ><a href="javascript:void(0)" style="pointer-events:none" >编辑</a> </td>
			</c:if>
			<c:if test="${bs.bxstatus == 2}">
				<td style="color: green;">已审批</td>
				<td><a href="mybaoxiao-edit.jsp?bxid=${bs.bxid}">编辑</a> </td>
			</c:if>
			<c:if test="${bs.bxstatus == 3}">
				<td>已付款</td>
				<td><a href="javascript:void(0)" style="pointer-events:none;color: grey" >编辑</a> </td>
			</c:if>
		</tr>
	</c:forEach>
	<%--//将分页那一栏提取出去，在此处引用--%>
<jsp:include page="page.jsp"></jsp:include>
</table>
</form>
</body>

</html>