<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>发信息</title>
    <script type="application/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.js"></script>
<script type="text/javascript">

     function commit(){
         var ntitle = $("#ntitle").val();
         var remark = $("#remark").val();
         $.ajax({
             type:"post",
             url:"${pageContext.request.contextPath}/notice/saveInfo",
             data:{"ntitle":ntitle,"remark":remark},
             success:function (msg) {
                 if (msg.map.statusCode==200){
                     window.location="notice.jsp";
                 } else {
                     alert("添加失败");
                 }
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
    当前位置:信息箱>>通知通告
 </td>
 </tr>
</table>
</td>
</tr>
</table>
<base href="${pageContext.request.contextPath}/">
<form name="form2" id="form12" action="${pageContext.request.contextPath}/notice/saveInfo" method="post">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;发布公告&nbsp;</td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">标题：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
        <input id="ntitle" name="ntitle"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">内容：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
        <textarea id="remark" rows=15 cols=130 name="remark"></textarea>
    </td>
</tr>



<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:commit()" class="coolbg">保存</a>
    <a href="javascript:history.go(-1)" class="coolbg">返回</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>