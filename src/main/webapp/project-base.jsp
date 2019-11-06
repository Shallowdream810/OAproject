<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<base href="${pageContext.request.contextPath}/">
<%
    String bath = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>项目信息管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
    <script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="static/js/moment.min.js"></script>
    <script type="text/javascript">
        $(function () {
            // 页面加载前发送异步请求查询列表
            $.ajax({
                type:"get",
                url:"pro/all",
                success:function (msg) {
                    bianli(msg);
                }
            })
        })
        //模糊查询错误的话只需删除这个
        function commit() {
            $(".det_tr").remove();
            var position = $("#position").val();
            var keyword = $("#keyword").val();
            var orderby = $("#orderby").val();
            $.ajax({
                type:"get",
                url:"pro/all",
                data:{"position":position,"keyword":keyword,"orderby":orderby},
                success:function (msg) {
                    bianli(msg);
                }
            })
        }
        function edit(pid) {
            window.location.href="pro/findProById/"+pid;
        }
        function detail(pid) {
            window.location.href="pro/lookDetail/"+pid;
        }
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
                    url: "<%=bath%>/pro/delete/"+ids,
                    data: {"_method": "delete"},
                    success: function (msg) {
                        if (msg.status==200){
                            window.location.replace("project-base.jsp");
                        }
                    }
                });
            }
        }

        //导出exvel文件
        function exportExcel() {
            $.ajax({
                type:"get",
                url:"pro/exportExcel",
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
  <td >
    当前位置:项目管理>>基本信息管理
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='project-base-add.jsp';" value='添加新项目' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>
<!--  搜索表单  -->
<form name='form3' action="javascript:void(0)">
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
              <select ID="position" name='position' style='width:150px'>
                <option value='0'>默认</option>
                <option value='1'>项目名称</option>
                <option value='2'>项目经理</option>
              </select>
        </td>
        <td width='70'>
          关键字：
        </td>
        <td width='160'>
          	<input ID="keyword" type='text' name='keyword' value='' style='width:120px' />
        </td>
        <td width='110'>
    		<select ID="orderby" name='orderby' style='width:120px'>
                <option value='0'>默认</option>
                <option value='1'>立项时间</option>
                <option value='2'>计划完成时间</option>
      	    </select>
        </td>
        <td>
          &nbsp;&nbsp;&nbsp;<input ONCLICK="commit()" name="imageField" type="image" src="skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
              <%--&nbsp;&nbsp;&nbsp;<input onclick="commit()" name="imageField" type="button" src="skin/images/frame/search.gif" width="45" height="20" border="0" class="np" value="搜索"/>--%>
        </td>
       </tr>
      </table>
    </td>
  </tr>
</table>
</form>
<!--  内容列表   -->
<form name="form2">
<table  width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;项目信息列表&nbsp;</td>
</tr>
<tr  align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="4%">序号</td>
	<td width="10%">项目名称</td>
	<td width="10%">客户公司名称</td>
	<td width="10%">客户方负责人</td>
	<td width="5%">项目经理</td>
	<td width="8%">开发人员数</td>
	<td width="8%">项目开始时间</td>
	<td width="8%">立项时间</td>
	<td width="8%">计划完成时间</td>
	<td width="8%">状态</td>
	<td width="10%">操作</td>
</tr>
<tr id="projeclist" bgcolor="#FAFAF1">
    <td height="28" colspan="12">
        &nbsp;
        <a href="javascript:choseAll()" class="coolbg">全选</a>
        <a href="javascript:invert()" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="javascript:deletebyId()" class="coolbg">&nbsp;删除&nbsp;</a>
        <a href="javascript:exportExcel()" class="coolbg">&nbsp;导出Excel&nbsp;</a>
    </td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center"><!--翻页代码 --></td>
</tr>
</table>
</form>
<script type="text/javascript">
    function bianli(msg) {
        $(msg).each(function (index,item) {
            var str1 = "<tr class='det_tr' align='center' bgcolor=\"#FFFFFF\" onMouseMove=\"javascript:this.bgColor='#FCFDEE';\" onMouseOut=\"javascript:this.bgColor='#FFFFFF';\" height=\"22\" >\n" +
                "\t<td><input name=\"id\" type=\"checkbox\" id=\"id\" value='"+item.pid+"' class=\"np\"></td>\n" +
                "\t<td>"+(index+1)+"</td>\n" +
                "\t<td align=\"left\"><a href=''><u>"+item.pname+"</u></a></td>\n" +
                "\t<td>"+item.comname+"</td>\n" +
                "\t<td>"+item.companyperson+"</td>\n" +
                "\t<td>"+item.ename+"</td>\n" +
                "\t<td>"+item.empcount+"</td>\n" +
                "\t<td>"+moment(item.starttime).format("YYYY-MM-DD")+"</td>\n" +
                "\t<td>"+moment(item.buildtime).format("YYYY-MM-DD")+"</td>\n" +
                "\t<td>"+moment(item.endtime).format("YYYY-MM-DD")+"</td>\n" +
                "\t<td>"+item.remark+"</td>\n" +
                "\t<td><a href=\"javascript:edit('"+item.pid+"')\">编辑</a> | <a href=\"javascript:detail('"+item.pid+"')\">查看详情</a></td>"
            "</tr>"
            $("#projeclist").before(str1);
        })
    }
</script>
</body>
</html>