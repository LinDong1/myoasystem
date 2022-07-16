<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath(); //获取当前工程的根目录
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; //项目url根目录
%>
<html>
<head>
    <base href="<%=basePath%>"> <!--这个让此文件下的路径都相对于当前工程开始-->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link href="css/select.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="js/select-ui.min.js"></script>
    <script type="text/javascript" src="editor/kindeditor.js"></script>
    <script type="text/javascript">
        $(document).ready(function(e) {
            $(".select1").uedSelect({
                width : 345
            });

        });
    </script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">人事管理</a></li>
        <li><a href="#">员工信息详情</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>

    <ul class="forminfo">
        <li>
            <label>用户名</label>
            <label>${emp.empid}</label>
        </li>
        <li>
        <li>
            <label>真实姓名</label>
            <label>${emp.realname}</label>
        </li>
        <li>
            <label>性别</label>
            <label>${emp.sex}</label>
        </li>
        <li>
            <label>出生日期</label>
            <label>${emp.sbirthdate}</label>
        </li>
        <li>
        <li>
            <label>入职时间</label>
            <label>${emp.shiredate}</label>
        </li>

        <li>
            <label>离职时间</label>
            <label>${emp.sleavedate}</label>
        </li>
        <li>
            <label>是否在职</label>
            <label>${emp.onduty==1?"在职":"离职"}</label>
        </li>
        <li>
            <label>所属部门<b>*</b></label>
            <label>${emp.dept.deptname}</label>

        </li>
        <li>
            <label>直接上级<b>*</b></label>
            <label>${emp.mgr.realname}<b>*</b></label>
        </li>
        </li>
        <li>
            <label>联系方式</label>
            <label>${emp.phone}</label>
        </li>
        <li>
            <label>QQ号</label>
            <label>${emp.qq}</label>
        </li>
        <li>
            <label>紧急联系人信息</label>
            <label>${emp.emercontactperson}</label>
        </li>
        <li>
            <label>身份证号</label>
            <label>${emp.idcard}</label>
        </li>
        <li>
            <label>&nbsp;</label>
            <script>
                function back(){
                    window.location.href="showEmpList"
                }
            </script>
            <input name="" type="button" class="btn" value="返回" onclick="back()"/>
        </li>
    </ul>

</div>

</body>

</html>