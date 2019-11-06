<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/29
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
<script type="text/javascript" src="static/js/echarts.js"></script>
<script type="text/javascript" src="static/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
    $(function () {
        $.ajax({
            type:"GET",
            url:"comparision/getComparisions",
            success:function (msg) {
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('main'));
                var company=[];
                var salesamount=[];
                $(msg).each(function (index,item) {
                    company.push(item.company);
                    salesamount.push(item.targetmoney);
                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: '2018年教育行业收入对比',
                            left:'center',
                            top:'bottom',
                        },
                        tooltip: {},
                        legend: {
                            data:['净收入']
                        },
                        xAxis: {
                            data: company
                        },
                        yAxis: {
                            axisLabel:{
                                formatter:'{value}亿'
                            }
                        },
                        series: [{
                            name: '净收入',
                            type: 'bar',
                            data: salesamount
                        }]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                })
            }
        })
    })
</script>
</head>
<body>
<base href="${pageContext.request.contextPath}/">
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
    <tr>
        <td height="26" background="skin/images/newlinebg3.gif">
            <table width="58%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td >
                        当前位置:对标管理>>数据统计
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>

</body>
</html>
