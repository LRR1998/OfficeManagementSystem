<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>发件箱</title>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
	<script type="application/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.js"></script>
	<script type="application/javascript" src="${pageContext.request.contextPath}/static/js/moment.js"></script>
	<script type="application/javascript">
	$(function(){
		$.ajax({
			type:'GET',
			url:"${pageContext.request.contextPath}/notice/showAll",
			dataType:'json',
			success:function(resultData){
			    var page = resultData.map.page;
				$(page.list).each(function(index,item){
				    var dateInfo= new Date(item.ndate);
				    var di= dateInfo.getFullYear()+"-"+(dateInfo.getMonth()+1)+"-"+dateInfo.getDate();
					var tr = "<tr name='appendtr' align='center' bgcolor='#FFFFFF' onMouseMove='javascript:this.bgColor='#FCFDEE';' onMouseOut='javascript:this.bgColor='#FFFFFF';' height='22' >\n" +
                        "\t<td><input name='id' type='checkbox' id='id' value='"+item.nid+"' class='np'></td>\n" +
                        "\t<td>"+((page.pageNum-1)*page.pageSize+(index+1))+"</td>\n" +
                        "\t<td>"+item.ntitle+"</td>\n" +
                        "\t<td align='center'><span >"+item.remark+"</span></td>\n" +
                        "\t<td>"+moment(item.ndate).format('YYYY-MM-DD')+"</td>\n" +
                        "\t<td><a href='${pageContext.request.contextPath}/notice/delete?nid="+item.nid+"'>删除</a></td>\n" +
                        "</tr>";
				    $("#lasttr").before(tr);
				});
                //遍历分页
				var div = "<div id='page'></div>";
				var firstPage="<a href='javascript:void(0)' onclick='findList(this.name)' name='notice/showAll?num=1'>首页</a>&nbsp;";
				var prePage = "<a href='javascript:void(0)' onclick='findList(this.name)' name='notice/showAll?num="+page.prePage+"'>上一页</a>"
				var nextPage = "<a href='javascript:void(0)' onclick='findList(this.name)' name='notice/showAll?num="+page.nextPage+"'>下一页</a>&nbsp;";
                var endPage = "<a href='javascript:void(0)' onclick='findList(this.name)' name='notice/showAll?num="+page.pageSize+"'>尾页</a>";
                var pages = "";
                $(page.navigatepageNums).each(function(index,item){
                    pages = pages + "&nbsp;<a href='javascript:void(0)' onclick='findList(this.name)' name='notice/showAll?num="+item+"'>"+item+"</a>&nbsp;";
                });
                $(div).append(firstPage).append(prePage).append(pages).append(nextPage).append(endPage).appendTo($("#pageTD"));
			}
		});
	});

	function findList(obj) {
	    $("tr[name=appendtr]").remove();
	    $("#page").remove();
        $(function(){
            $.ajax({
                type:'GET',
                url:obj,
                dataType:'json',
                success:function(resultData){
                    var page = resultData.map.page;
                    $(page.list).each(function(index,item){
                        var dateInfo= new Date(item.ndate);
                        var di= dateInfo.getFullYear()+"-"+(dateInfo.getMonth()+1)+"-"+dateInfo.getDate();
                        var tr = "<tr name='appendtr' align='center' bgcolor='#FFFFFF' onMouseMove='javascript:this.bgColor='#FCFDEE';' onMouseOut='javascript:this.bgColor='#FFFFFF';' height='22' >\n" +
                            "\t<td><input name='id' type='checkbox' id='id' value='"+item.nid+"' class='np'></td>\n" +
                            "\t<td>"+((page.pageNum-1)*page.pageSize+(index+1))+"</td>\n" +
                            "\t<td>"+item.ntitle+"</td>\n" +
                            "\t<td align='center'><span >"+item.remark+"</span></td>\n" +
                            "\t<td>"+moment(item.ndate).format('YYYY-MM-DD')+"</td>\n" +
                            "\t<td><a href='${pageContext.request.contextPath}/notice/delete?nid="+item.nid+"'>删除</a></td>\n" +
                            "</tr>";
                        $("#lasttr").before(tr);
                    });
                    var div = "<div id='page'></div>";
                    var firstPage="<a href='javascript:void(0)' onclick='findList(this.name)' name='notice/showAll?num=1'>首页</a>&nbsp;";
                    var prePage = "<a href='javascript:void(0)' onclick='findList(this.name)' name='notice/showAll?num="+page.prePage+"'>上一页</a>"
                    var nextPage = "<a href='javascript:void(0)' onclick='findList(this.name)' name='notice/showAll?num="+page.nextPage+"'>下一页</a>&nbsp;";
                    var endPage = "<a href='javascript:void(0)' onclick='findList(this.name)' name='notice/showAll?num="+page.pageSize+"'>尾页</a>";
                    var pages = "";
                    $(page.navigatepageNums).each(function(index,item){
                        pages = pages + "&nbsp;<a href='javascript:void(0)' onclick='findList(this.name)' name='notice/showAll?num="+item+"'>"+item+"</a>&nbsp;";
                    });
                    $(div).append(firstPage).append(prePage).append(pages).append(nextPage).append(endPage).appendTo($("#pageTD"));

                },
            });
        });

	    return false;
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

<!--  搜索表单  -->

<!--  内容列表   -->
<form name="form2">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="12" background="skin/images/tbg.gif">&nbsp;发件箱&nbsp;</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22" id="tr2">
	<td width="4%">选择</td>
	<td width="6%">序号</td>
	<td width="10%">标题</td>
	<td width="10%">内容</td>
	<td width="8%">发送时间</td>
	<td width="8%">操作</td>
</tr>

<%--<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
	<td><input name="id" type="checkbox" id="id" value="101" class="np"></td>
	<td>1</td>
	<td>项目完成的咋样?</td>
	<td align="center"><span >最近工作累不？项目完成的咋样?加油哈</span></td>
	<td>2015-02-03</td>
	<td><a >删除</a></td>
</tr>--%>


<tr id="lasttr" bgcolor="#FAFAF1">
<td height="28" colspan="12">
	&nbsp;
	<a href="" class="coolbg">全选</a>
	<a href="" class="coolbg">反选</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="" class="coolbg">&nbsp;删除&nbsp;</a>
	<a href="" class="coolbg">&nbsp;导出Excel&nbsp;</a>
</td>
</tr>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="12" align="center" id="pageTD"><!--翻页代码 -->


	</td>
</tr>
</table>

</form>
  

</body>
</html>