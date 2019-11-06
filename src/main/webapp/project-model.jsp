<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base href="${pageContext.request.contextPath}/">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>模块管理</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
    <script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        $(function () {
            $.ajax({
                type:"get",
                url:"mod/findAll",
                success:function (msg1) {
                    //ajax请求
                    bianli(msg1);
                    //底部导航页信息
                    var msg = msg1.page;
                    //遍历分页
                    var Info = "当前第"+msg.pageNum+"页,共"+msg.pages+"页,共"+msg.total+"条数据";
                    //首页
                    var startPage = "<a href='javascript:void(0)' onclick='findList(this.name)' name='"+msg1.requestURI+"?pageNum=1"+msg1.requestInfo+"'>首页</a>";
                    //上一页
                    var lastPage =  "<a href='javascript:void(0)' onclick='findList(this.name)' name='"+msg1.requestURI+"?pageNum="+(msg.pageNum-1)+msg1.requestInfo+"'>上一页</a>";
                    //遍历页码导航层
                    var nowPage="";
                    $(msg.navigatepageNums).each(function (index,item) {
                        nowPage = nowPage+"  "+"<a href='javascript:void(0)' onclick='findList(this.name)' name='"+msg1.requestURI+"?pageNum="+item+msg1.requestInfo+"'>"+(index+1)+"</a>";
                    })
                    //下一页
                    var nextPage = "<a href='javascript:void(0)' onclick='findList(this.name)' name='"+msg1.requestURI+"?pageNum="+(msg.pageNum+1)+msg1.requestInfo+"'>下一页</a>";
                    //末页
                    var endPage = "<a href='javascript:void(0)' onclick='findList(this.name)' name='"+msg1.requestURI+"?pageNum="+msg.pages+msg1.requestInfo+"'>尾页</a>";
                    $("#start").append(Info).append(startPage).append(lastPage).append(nowPage).append(nextPage).append(endPage);
                }
            })
        })
        // 获取后台的jeson列表串后遍历操作,分页查询
        function findList(obj) {
            $.ajax({
                type:"get",
                url:obj,
                success:function (msg1) {
                    //删除之前遍历的数据
                    $(".del_tr").remove();
                    $("#start").empty();
                    //增加新查到的数据
                    bianli(msg1);
                    page(msg1);
                }
            })
        }
        //条件查询
        function commit() {
            // 便利前删除所有之前填充的所有tr
            $(".del_tr").remove();
            // alert(123)
            // $("#forms3").submit();
            var search_style = $("#search_style").val();
            var search_like_keyword = $("#search_like_keyword").val();
            $.ajax({
                type:"get",
                url:"mod/findAll",
                data:{"search_style":search_style,"search_like_keyword":search_like_keyword},
                success:function (msg) {
                    bianli(msg);
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
    当前位置:项目管理>>模块管理
 </td>
  <td>
    <input type='button' class="coolbg np" onClick="location='project-model-add.jsp';" value='添加新模块' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>
<!--  搜索表单  -->
<form id="forms3" name='form3' action='mod/findAll' method='get'>
<input type='hidden' name='dopost' value='' />
<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='skin/images/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select id="search_style" name='search_style' style='width:150px'>
                <option value='0'>选择类型...</option>
                <option value='1'>项目名称</option>
                <option value='2'>模块名称</option>
          </select>
        </td>
        <td width='70'>
          关键字：
        </td>
        <td width='160'>
          	<input id="search_like_keyword" type='text' name='search_like_keyword' value='' style='width:120px' />
        </td>
        <td>
          &nbsp;&nbsp;&nbsp;<input value="搜索" onclick="commit()" name="imageField" type="button" src="skin/images/frame/search.gif" width="45" height="20" border="0" class="np" />
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
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;模块列表&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">模块名称</td>
	<td width="10%">需求名称</td>
	<td width="10%">项目名称</td>
	<td width="10%">优先级</td>
	<td width="8%">添加时间</td>
	<td width="8%">修改时间</td>
	<td width="10%">操作</td>
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
<script type="text/javascript">
    //遍历数据的方法
    function bianli(msg1) {
        var msg = msg1.page;//page属性
        $(msg.list).each(function (index,item) {
            var str = "<tr class='del_tr' align='center' bgcolor=\"#FFFFFF\" onMouseMove=\"javascript:this.bgColor='#FCFDEE';\" onMouseOut=\"javascript:this.bgColor='#FFFFFF';\" height=\"22\" >\n" +
                "\t<td><input name=\"id\" type=\"checkbox\" id=\"id\" value=\'" + item.id + "'\" class=\"np\"></td>\n" +
                "\t<td>" + (index + 1) + "</td>\n" +
                "\t<td align=\"center\"><a href=''><u>" + item.modname + "</u></a></td>\n" +
                "\t<td align=\"center\"><a href=''><u>" + item.analyname+"</u></a></td>\n" +
                "\t<td align=\"center\"><a href=''><u>" + item.proname + "</u></a></td>\n" +
                "\t<td>" + item.level + "</td>\n" +
                "\t<td>2015-02-03</td>\n" +
                "\t<td>2015-06-03</td>\n" +
                "\t<td><a href=\"project-model-edit.jsp\">编辑</a> | <a href=\"project-model-look.jsp\">查看详情</a></td>\n" +
                "</tr>";
            $("#down_tr").before(str);
        })
    }
    //遍历导航页层的方法
    function page(msg1) {
        var msg = msg1.page;
        //遍历分页
        var Info = "当前第"+msg.pageNum+"页,共"+msg.pages+"页,共"+msg.total+"条数据";
        //首页
        var startPage = "<a href='javascript:void(0)' onclick='findList(this.name)' name='"+msg1.requestURI+"?pageNum=1"+msg1.requestInfo+"'>首页</a>";
        //上一页
        var lastPage =  "<a href='javascript:void(0)' onclick='findList(this.name)' name='"+msg1.requestURI+"?pageNum="+(msg.pageNum-1)+msg1.requestInfo+"'>上一页</a>";
        //遍历页码导航层
        var nowPage="";
        $(msg.navigatepageNums).each(function (index,item) {
            nowPage = nowPage+"  "+"<a href='javascript:void(0)' onclick='findList(this.name)' name='"+msg1.requestURI+"?pageNum="+item+msg1.requestInfo+"'>"+(index+1)+"</a>";
        })
        //下一页
        var nextPage = "<a href='javascript:void(0)' onclick='findList(this.name)' name='"+msg1.requestURI+"?pageNum="+(msg.pageNum+1)+msg1.requestInfo+"'>下一页</a>";
        //末页
        var endPage = "<a href='javascript:void(0)' onclick='findList(this.name)' name='"+msg1.requestURI+"?pageNum="+msg.pages+msg1.requestInfo+"'>尾页</a>";
        $("#start").append(Info).append(startPage).append(lastPage).append(nowPage).append(nextPage).append(endPage);
    }

</script>
</body>
</html>