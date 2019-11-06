<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>发信息</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
    $(function(){
        $.ajax({
            url:"emp/others",
            dataType:"json",
            type:"get",
            success:function(data){
                $(data).each(function (index,item) {
					var option = "<option value='"+item.ename+"#"+item.email+"'>"+item.ename+"</option>";
               		$("#recieve").append(option);
                })
            },
        });
    });
	function submit() {
		$("#form2").submit();
    }

</script>
<link rel="stylesheet" type="text/css" href="skin/css/base.css">
</head>
<body leftmargin="8" topmargin="8" background='skin/images/allbg.gif'>
<base href="${pageContext.request.contextPath}/">
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
<base href="${pageContext.request.contextPath}/">
<form name="form2" id="form2" action="msg/send" method="post">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;发信息&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">收件人：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="ename" id="recieve">
			<option>请选择...</option>
		</select>
		<input type="hidden" name="empFk" value="${user.eid}">
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">标题：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="text" name="title"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">时间：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input type="date" name="sendtime"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">内容：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<textarea name="content" rows=15 cols=130></textarea></td>
</tr>

<%--<tr >--%>
	<%--<td align="right" bgcolor="#FAFAF1" >附件：</td>--%>
	<%--<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >--%>
		<%--<input type="file"/>--%>
	<%--</td>--%>
<%--</tr>--%>


<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:void(0)" onclick="submit()" class="coolbg">保存</a>
</td>
</tr>
</table>

</form>
  

</body>
</html>