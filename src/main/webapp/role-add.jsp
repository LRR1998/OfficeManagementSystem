<%@ page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>添加角色</title>
<script src="${pageContext.request.contextPath}/static/js/jquery-3.3.1.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/docs.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/static/js/ztree/jquery.ztree.all-3.5.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/skin/css/base.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/ztree/zTreeStyle.css">
<script type="text/javascript">
    $(function(){

        var setting = {
            async: {
                enable: true,
                type:"get",
                url:"${pageContext.request.contextPath}/sources/list",
                autoParam:["id", "name=n", "level=lv"]
            },
            view: {
                addHoverDom: function(treeId, treeNode){
                    var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
                    aObj.attr({"href":"javascript:void(0)","target":"_self"});
                    if (treeNode.editNameFlag || $("#btnGroup"+treeNode.tId).length>0) return;
                    var s = '<span id="btnGroup'+treeNode.tId+'">';
                    if ( treeNode.level == 0 ) {
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="addNode('+treeNode.id+')" href="javascript:void(0)" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
                    } else if ( treeNode.level == 1 ) {
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  onclick="editNode('+treeNode.id+')" href="#" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
                        if (treeNode.children.length == 0) {
                            s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="deleteNode('+treeNode.id+')" href="javascript:void(0)" >&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
                        }
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="addNode('+treeNode.id+')" href="javascript:void(0)" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
                    } else if ( treeNode.level == 2 ) {
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  onclick="editNode('+treeNode.id+')" href="#" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="deleteNode('+treeNode.id+')" href="javascript:void(0)">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
                    }
                    s += '</span>';
                    aObj.after(s);
                },
                removeHoverDom: function(treeId, treeNode){
                    $("#btnGroup"+treeNode.tId).remove();
                }
            },
            callback:{
                onClick:zTreeOnclick,
            },
            check:{
                enable:true,
            }
        };
        $.fn.zTree.init($("#permissionTree"), setting);
    });
    function zTreeOnclick() {
        return false;
    }
    function submit(){
        var rolename = $("#rolename").val();
        var status = $("#status").val();
        var roledis = $("#roledis").val();
        var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
        var nodes = treeObj.getCheckedNodes(true);
        var ids = "";
        for(var i = 0;i<nodes.length;i++){
            ids = ids + nodes[i].id +",";
		}
        ids = ids.substr(0,ids.length-1);
		alert(ids);
		$.ajax({
			type:"POST",
			url:"role/saveInfo",
			data:{"rolename":rolename,"status":status,"roledis":roledis,"ids":ids},
			success:function(msg){
			    if (msg.map.statusCode==200) {
                    alert("添加成功！");
                    window.location="role.jsp";
				}

			}
		})
       // $("#form2").submit();
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
    当前位置:权限管理>>添加角色信息
 </td>
 </tr>
</table>
</td>
</tr>
</table>
<base href="${pageContext.request.contextPath}/">
<form name="form2" id="form2" action="role/saveInfo" method="post">

<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="2" background="skin/images/tbg.gif">&nbsp;添加角色&nbsp;</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">角色名称：</td>
	<td  align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<input name="rolename" id="rolename"/></td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">状态：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<select name="status" id="status">
			<option value=0>禁用</option>
			<option value=1>启用</option>
		</select>
	</td>
</tr>
<tr >
	<td align="right" bgcolor="#FAFAF1" height="22">赋菜单资源：</td>
	<td align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
		<div class="panel-body">
			<ul id="permissionTree" class="ztree"></ul>
		</div>
	</td>
</tr>

<tr >
	<td align="right" bgcolor="#FAFAF1" >备注：</td>
	<td colspan=3 align='left' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" >
		<textarea name="roledis" id="roledis" rows=10 cols=130></textarea>
	</td>
</tr>
<tr bgcolor="#FAFAF1">
<td height="28" colspan=4 align=center>
	&nbsp;
	<a href="javascript:void(0)" onclick="submit()" class="coolbg">保存</a>
	<a href="role.jsp" class="coolbg">返回</a>
</td>
</tr>
</table>
</form>
</body>
</html>