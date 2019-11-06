//遍历导航页层的方法,提供一个map
function page(msg1) {
    var msg = msg1.pagelist;
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