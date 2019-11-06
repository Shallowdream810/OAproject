<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<base href="${pageContext.request.contextPath}/">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>发件箱</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
	<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
	<SCRIPT type="text/javascript"SRC="static/js/moment.min.js"></SCRIPT>
	<script type="text/javascript"src="static/js/page.js"></script>
<script type="application/javascript">
	$(function () {
        $.ajax({
            type:"get",
            url:"not/findAll",
            success:function (msg) {
                if(msg.map.statusCode == 200){
                    $(msg.map.pagelist.list).each(function (index,item) {
                        var str = "<tr class='del_tr' align='center' bgcolor='#FFFFFF' onMouseMove='javascript:this.bgColor='#FCFDEE';' onMouseOut='javascript:this.bgColor='#FFFFFF';' height='22' >"
                            +"<td><input name='id' type='checkbox' id='id' value='"+item.nid+"' class='np'></td>"
                            +"<td>"+((msg.map.pagelist.pageNum-1)*3+index+1)+"</td>"
                            +"<td>"+item.ntitle+"</td>"
                            +"<td align='center'><span >"+item.remark+"</span></td>"
                            +"<td>"+moment(item.ndate).format("YYYY-MM-DD")+"</td>"
                            +"<td><a HREF='#'>删除</a></td>"
                            +"</tr>"
                        $("#down_tr").before(str);
                    })
                }
                //翻页信息
				page(msg.map);
            }
        })
    })
	//点击下一面
	function findList(obj) {
        $.ajax({
            type:"get",
            url:obj,
            success:function (msg1) {
                //删除之前遍历的数据
                $(".del_tr").remove();
                $("#start").empty();
                //增加新查到的数据
                $(msg1.map.pagelist.list).each(function (index,item) {
                    var str = "<tr class='del_tr' align='center' bgcolor='#FFFFFF' onMouseMove='javascript:this.bgColor='#FCFDEE';' onMouseOut='javascript:this.bgColor='#FFFFFF';' height='22' >"
                        +"<td><input name='id' type='checkbox' id='id' value='"+item.nid+"' class='np'></td>"
                        +"<td>"+((msg1.map.pagelist.pageNum-1)*3+index+1)+"</td>"
                        +"<td>"+item.ntitle+"</td>"
                        +"<td align='center'><span >"+item.remark+"</span></td>"
                        +"<td>"+moment(item.ndate).format('YYYY-MM-DD')+"</td>"
                        +"<td><a HREF='#'>删除</a></td>"
                        +"</tr>"
                    $("#down_tr").before(str);
                })
                page(msg1.map);
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
    当前位置:信息箱>>通知公告
 </td>
	  <td>
		  <input type='button' class="coolbg np" onClick="location='notice-send.jsp';" value='发布新通告' />
	  </td>
 </tr>
</table>
</td>
</tr>
</table>
<form name="form2">
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">发件箱</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22" id="tr2">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">标题</td>
	<td width="10%">内容</td>
	<td width="8%">发送时间</td>
	<td width="8%">操作</td>
</tr>
<tr id="down_tr" bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="" class="coolbg">全选</a>
	<a href="" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
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