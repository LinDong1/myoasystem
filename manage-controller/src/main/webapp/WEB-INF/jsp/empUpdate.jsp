<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    String path = request.getContextPath(); //获取当前工程的根目录
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; //项目url根目录
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <base href="<%=basePath%>">
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
        <li><a href="#">修改员工信息</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>基本信息</span></div>

    <ul class="forminfo">

        <form action="goEmpUpdate" method="post">
            <li>
                <label>用户名</label>
                <input name="empid" type="text" class="dfinput" value="${emp.empid}" readonly /><i>用户名不可修改 ，必须唯一</i></li>
            <li>
            <li>
                <label>真实姓名</label>
                <input name="realname" type="text" class="dfinput" value="${emp.realname}"/><i></i></li>
            <li>
                <label>性别</label><cite>
                <c:if test="${emp.sex=='男' || emp.sex=='1'}">
                    <input name="sex" type="radio" value="男" checked="checked" />男&nbsp;&nbsp;&nbsp;&nbsp;
                    <input name="sex" type="radio" value="女" />女
                </c:if>
                <c:if test="${emp.sex=='女' || emp.sex=='0'}">
                    <input name="sex" type="radio" value="男" />男&nbsp;&nbsp;&nbsp;&nbsp;
                    <input name="sex" type="radio" value="女"checked />女
                </c:if>
                <i>也可以根据身份证号自动获取</i></cite>

            </li>
            <li>
                <label>出生日期</label>
                <input name="sbirthdate" type="date" class="dfinput" value="${emp.sbirthdate}"/><i>也可以根据身份证号自动获取</i></li>
            <li>
            <li>
                <label>入职时间</label>
                <input name="shiredate" type="date" class="dfinput" value="${emp.shiredate}"/><i></i></li>

            <li>
                <label>离职时间</label>
                <input name="sleavedate" type="date" class="dfinput" value="${emp.sleavedate}"/><i></i></li>
            <li>
                <label>是否在职</label><cite>
                <c:if test="${emp.onduty ==1}">
                    <input name="onduty" type="radio" value="1" checked="checked" />是&nbsp;&nbsp;&nbsp;&nbsp;
                    <input name="onduty" type="radio" value="0" />否
                </c:if>
                <c:if test="${emp.onduty ==0}">
                <input name="onduty" type="radio" value="1"/>是&nbsp;&nbsp;&nbsp;&nbsp;
                <input name="onduty" type="radio" value="0"  checked="checked" />否</cite>
                </c:if>
            </li>
            <li>
                <label>所属部门<b>*</b></label>
                <div class="vocation">
                    <select class="select1">
                        <c:forEach items="${depts}" var="dept">
                            <c:choose>
                                <c:when test="${dept.deptno == emp.deptno}">
                                    <option selected>${dept.deptname}</option>
                                </c:when>
                                <c:otherwise>
                                    <option>${dept.deptname}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                    </select>
                </div>

            </li>
            <li>
                <label>直接上级<b>*</b></label>
                <div class="vocation">
                    <select class="select1">
                        <c:forEach items="${mgrs}" var="mgr">
                            <c:choose>
                                <c:when test="${emp.mgrid==mgr.empid}">
                                    <option selected>${mgr.realname}</option>
                                </c:when>
                                <c:otherwise>
                                    <option>${mgr.realname}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                &nbsp;&nbsp;<input name="" type="text" class="dfinput"  placeholder="也可以在此输入首字母帮助显示"/></li>
            </li>
            <li>
                <label>联系方式</label>
                <input name="phone" type="text" class="dfinput" value="${emp.phone}"/>
            </li>
            <li>
                <label>QQ号</label>
                <input name="qq" type="text" class="dfinput" value="${emp.qq}"/>
            </li>
            <li>
                <label>紧急联系人信息</label>
                <textarea name="emercontactperson" cols="" rows="" class="textinput" >${emp.emercontactperson}</textarea>
            </li>
            <li>
                <label>身份证号</label>
                <input name="idcard" type="text" class="dfinput" value="${emp.idcard}"/>
            </li>
            <li>
                <label>&nbsp;</label>
                <input name="" type="submit" class="btn" value="确认保存" />
            </li>
        </form>



    </ul>

</div>

</body>

</html>