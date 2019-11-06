<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<base href="${pageContext.request.contextPath}/">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>角色管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
    <script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="static/js/page.js"></script>
    <script type="text/javascript" >
        $(function () {
            $.ajax({
                type:"get",
                url:"role/getRoleList",
                success:function (msg) {
                    if (msg.map.statusCode == 200){
                        $(msg.map.pagelist.list).each(function (index,item) {
                            var str = ""
                            if(item.status ==1){
                                str = "启用";
                            }else {
                                str = "禁用"
                            }
                            var tr = "<tr class='del_tr' align='center' bgcolor='#FFFFFF' onMouseMove='javascript:this.bgColor='#FCFDEE';' onMouseOut='javascript:this.bgColor='#FFFFFF';' height='22' >"
                                +  " <td><input name='id' type='checkbox' id='id' value='"+item.roleid+"' class='np'></td>"
                                +  " <td>"+(((msg.map.pagelist.pageNum)*3)-2+index)+"</td>"
                                +  "<td>"+item.roleid+"</td>"
                                +  "<td align='center'>"+item.rolename+"</td>"
                                +  "<td>"+str+"</td>"
                                +   " <td><a >删除</a> |<a href='role-edit.jsp'>编辑</a> | <a href='role-look.jsp'>查看详情</a></td>"
                                +  " </tr>"
                            $("#down_tr").before(tr);
                        })
                    }
                    //分页信息
                    page(msg.map);
                }
            })
        })
        function findList(url) {
            $.ajax({
                type:"get",
                url:url,
                success:function (msg) {
                    $(".del_tr").remove();
                    $("#start").empty();
                    if (msg.map.statusCode == 200){
                        $(msg.map.pagelist.list).each(function (index,item) {
                            var str = ""
                            if(item.status ==1){
                                str = "启用";
                            }else {
                                str = "禁用"
                            }
                            var tr = "<tr class='del_tr' align='center' bgcolor='#FFFFFF' onMouseMove='javascript:this.bgColor='#FCFDEE';' onMouseOut='javascript:this.bgColor='#FFFFFF';' height='22' >"
                                +  " <td><input name='id' type='checkbox' id='id' value='"+item.roleid+"' class='np'></td>"
                                +  " <td>"+(((msg.map.pagelist.pageNum)*3)-2+index)+"</td>"
                                +  "<td>"+item.roleid+"</td>"
                                +  "<td align='center'>"+item.rolename+"</td>"
                                +  "<td>"+str+"</td>"
                                +   " <td><a >删除</a> |<a href='role-edit.jsp'>编辑</a> | <a href='role-look.jsp'>查看详情</a></td>"
                                +  " </tr>"
                            $("#down_tr").before(tr);
                        })
                    }
                    //分页信息
                    page(msg.map);
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
    当前位置:权限管理>>角色管理
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='role-add.jsp';" value='添加角色' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<!--  搜索表单  -->
<form name='form3' action='' method='get'>
<input type='hidden' name='dopost' value='' />
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select name='cid' style='width:150px'>
          <option value='0'>选择类型...</option>
          	<option value='1'>角色名称</option>
          </select>
        </td>
        <td width='70'>
          关键字：
        </td>
        <td width='160'>
          	<input type='text' name='keyword' value='' style='width:120px' />
        </td>
        <td>
          &nbsp;&nbsp;&nbsp;<input name="imageField" type="image" src="skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
        </td>
       </tr>
      </table>
    </td>
  </tr>
</table>
</form>
<!--  内容列表   -->
<form name="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;角色列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">角色编号</td>
	<td width="10%">角色名称</td>
	<td width="8%">状态</td>
	<td width="10%">操作</td>
</tr>


	



<tr id="down_tr" bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="" class="coolbg">全选</a>
	<a href="" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="" class="coolbg">&nbsp;禁用&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center">
        <div id="start">

        </div>
    </td>
</tr>
</table>

</form>
  

</body>
</html>