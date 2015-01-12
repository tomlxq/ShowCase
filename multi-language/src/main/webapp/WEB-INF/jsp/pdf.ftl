<html>
<head>
    <title>报告测试1</title>
    <style type="text/css">
        <!--
        body {
            font-family: 'Arial Unicode MS';
        }

        table {
            -fs-table-paginate: paginate;
        }

        div.header-left {
            display: none
        }

        div.header-right {
            display: none
        }

        div.footer-left {
            display: none
        }

        div.footer-right {
            display: none
        }

        @media print {
            div.header-left {
                display: block;
                position: running(header-left);
            }

            div.header-right {
                display: block;
                position: running(header-right);
            }

            div.footer-left {
                display: block;
                position: running(footer-left);
            }

            div.footer-right {
                display: block;
                position: running(footer-right);
            }
        }

        @page {
            margin: 0.65in;
            border: thin solid black;
            padding: 1em;
            @top-left {
                content: element(header-left)
            };
            @top-right {
                content: element(header-right)
            };
            @bottom-left {
                content: element(footer-left)
            };
            @bottom-right {
                content: element(footer-right)
            };
        }

        #pagenumber:before {
            content: counter(page);
        }

        #pagecount:before {
            content: counter(pages);
        }

        -->
    </style>
</head>
<body>
<div id="header-left" class="header-left" align="left"><img src="/assets/jquery-ui-1.11.2/images/ui-bg_diagonals-thick_18_b81900_40x40.png"
                                                            width="10" height="10"/>ttt
    <hr/>
    打印日期1:
</div>
<div id="header-right" class="header-right" align="right">报告对象：
    <hr/>
    产生日期1：
</div>

<div id="footer-left" class="footer-left" align="left">
    <hr/>
    Copyright 2000
</div>
<div id="footer-right" class="footer-right" align="right">
    <hr/>
    第 <span id="pagenumber"/> 页
</div>
<table border="0" width="100%" height="100%" cellpadding="0" cellspacing="0" >
    <tr>
        <td colspan="5" align="center"><#--<img width="500" height="300"
                                             src="http://localhost:8081/xxx/report/barChartSample!sample1.action"/>-->
        </td>
    </tr>
    <tr>
        <td>id</td>
        <td>名称</td>
        <td>创建时间</td>
        <td>角色类型</td>
        <td>适用范围</td>
    </tr>
<#assign i=0>
<#list roleList as role>

    <#assign i = i+1>
    <tr>
        <td>${role["roleId"]}</td>
        <td>${role["roleName"]}</td>
        <td>${role["createTime"]?number_to_datetime}</td>
        <td>${role["roleTypeId"]}</td>
        <td>${role["roleApplyScopeId"]}</td>
    </tr>
</#list>
</table>
</body>
</html>



















