<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath(); //获取当前工程的根目录
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; //项目url根目录
%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link href="css/select.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="js/jquery.js"></script>

    <script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="js/select-ui.min.js"></script>
    <script type="text/javascript" src="editor/kindeditor.js"></script>
    <script type="text/javascript">
        $(document).ready(function(e) {
            $(".select1").uedSelect({
                width : 200
            });

        });
    </script>
    <script type="text/javascript">
        $(document).ready(function(){
            $(".click").click(function(){
                $(".tip").fadeIn(200);
            });

            $(".tiptop a").click(function(){
                $(".tip").fadeOut(200);
            });

            $(".sure").click(function(){
                $(".tip").fadeOut(100);
            });

            $(".cancel").click(function(){
                $(".tip").fadeOut(100);
            });

        });
    </script>

    <script>
        function deleteConfirm(empid){
            if(confirm("你真的要删除吗?")){  //询问
                //发送ajax请求
                $.get("empDelete/"+empid,function (data) {
                    var n = data;
                    if(n=="删除成功"){
                        window.location.href="showEmpList/1";
                    }
                })
            }

        }
    </script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">人事管理</a></li>
        <li><a href="#">员工管理</a></li>
    </ul>
</div>

<div class="rightinfo">
    <form action="mutiQuery" method="post">
        <ul class="prosearch">
            <li>
                <label>查询：</label><i>用户名</i>
                <a>
                    <input name="empid" type="text" class="scinput" />
                </a><i>所属部门</i>
                <a>
                    <select class="select1" name="deptno">
                        <option selected value="-1">--请选择部门--</option>
                        <c:forEach items="${depts}" var="dept" >
                            <option value="${dept.deptno}">${dept.deptname}</option>
                        </c:forEach>
                    </select>
                </a>
            </li>

            <li>
                <label>是否在职：</label>
                <input name="onduty" type="radio" value="1" checked="checked" />&nbsp;是&nbsp;&nbsp;
                <input name="onduty" type="radio" value="0" />&nbsp;否
            </li>
            <li>
                <label>入职时间：</label>
                <a>
                    <input name="shiredate" type="date" class="scinput" />
                </a>
            </li>
            <a>
                <input name="" type="submit" class="sure" value="查询" />
            </a>
        </ul>
    </form>




    <div class="formtitle1"><span>员工列表</span></div>

    <table class="tablelist">
        <thead>
        <tr>
            <th>
                <input name="" type="checkbox" value="" checked="checked" />
            </th>
            <th>用户名<i class="sort"><img src="images/px.gif" /></i></th>
            <th>真实姓名</th>
            <th>所属部门</th>
            <th>所属岗位</th>
            <th>入职时间</th>
            <th>联系方式</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${employees}" var="emp">
            <tr>
                <td>
                    <input name="" type="checkbox" value="" />
                </td>
                <td>${emp.empid}</td>
                <td>${emp.realname}</td>
                <td>${emp.dept.deptname}</td>
                <td>${emp.position.pname}</td>
                <td>${emp.shiredate}</td>
                <td>${emp.phone}</td>
                <td>
                    <a href="goEmpInfo/${emp.empid}" class="tablelink">查看</a>
                    <a href="empUpdateBefore/${emp.empid}" class="tablelink">修改</a>

                    <a href="javascript:void(0)" class="tablelink click" onclick="deleteConfirm('${emp.empid}')"> 删除</a>
                    <a href="resetPassword/${emp.empid}" class="tablelink"> 重置密码</a>
                </td>
            </tr>
        </c:forEach>


        </tbody>
    </table>

    <div class="pagin">
        <div class="message">共<i class="blue">${total}</i>条记录，共<i class="blue">${pages}</i>页，当前显示第&nbsp;<i class="blue">${pageNum}&nbsp;</i>页</div>
        <ul class="paginList">
<%--            上一页--%>
            <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>

    <c:forEach var="i" begin="1" end="${pages}" step="1">
        <c:choose>
            <c:when test="${pageNum==i}">
                <li class="paginItem current"><a href="showEmpList/${i}">${i}</a></li>
            </c:when>
            <c:otherwise>
                <li class="paginItem"><a href="showEmpList/${i}">${i}</a></li>
            </c:otherwise>
        </c:choose>
    </c:forEach>

            <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
        </ul>
    </div>

</div>

<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>

</body>

</html>
