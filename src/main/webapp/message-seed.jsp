<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<base href="${pageContext.request.contextPath}/">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>发信息</title>
	<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
	<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
    $(function(){
		$.ajax({
			type:"get",
			url:"sys/findemail",
			success:function (msg) {
				$(msg).each(function (index,item) {
					var option = "<option value='"+item.email+"'>"+item.ename+"</option>"
					$("#person").append(option)
                })
            }
		})
    });
    function commit() {

        var  person = $("#person").val();
        var title = $("#title").val();
        var  content = $("#content").val();
        var files = $("#files")[0].files[0];
        var sendtime = $("#d12").val();
		var formData = new FormData();
		formData.append("ename",person);
        formData.append("title",title);
        formData.append("content",content);
        formData.append("files",files);
        formData.append("sendtime",sendtime);
        $.ajax({
            type:"post",
            url:"ema/saveInfo",
            data:formData,
            caches:false,
            processData:false,
            contentType:false,
            success:function (msg) {
                alert("发送成功")
                window.location.href="message.jsp";
            }
        })
    }

</script>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>
<!--  快速转换位置按钮  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="skin/images/newlinebg3.gif">
  <table width="58%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td >
    当前位置:信息箱>>发信息
 </td>
 </tr>
</table>
</td>
</tr>
</table>

<form name="form2" action="javascript:void(0)">
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;发信息&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">收件人：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="ename" id="person">
		</select></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">标题：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
        <input id="title" name="title"/></td>
</tr>
<tr >
    <td align="right" bgcolor="#FAFAF1" height="22">发送时间：</td>
    <td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
        <input id="d12" name="sendtime" type="text"/>
        <img onclick="WdatePicker({el:'d12','skin':'green','minDate':'%y-%M-%d'})" src="My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">内容：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
        <textarea id="content" name="content" rows=15 cols=130></textarea></td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >附件：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<input id="files" name="files" type="file"/>
	</td>
</tr>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:commit()" class="coolbg">保存</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>