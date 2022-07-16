<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">人事管理</a></li>
        <li><a href="#">修改岗位信息</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>

    <%
        String posid = request.getParameter("posid");
        String pname = request.getParameter("pname");
        String pdesc = request.getParameter("pdesc");
    %>
    <ul class="forminfo">
        <form action="updatePosition" method="post">
            <li><label>岗位编号</label><input name="posid" type="text" class="dfinput" value="<%=posid%>"  readonly/> </li>
            <li><label>岗位名称</label><input name="pname" type="text" class="dfinput"value="<%=pname%>" /> </li>
            <li><label>岗位描述</label><input name="pdesc" type="text" class="dfinput" value="<%=pdesc%>"/></li>
            <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存"/></li>
        </form>

    </ul>


</div>


</body>

</html>