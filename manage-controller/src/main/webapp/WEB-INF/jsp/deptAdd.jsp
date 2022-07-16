
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script src="js/jquery.js"></script>
    <script>

        $(function () {
            $("#deptno").blur(function () {
                var deptno = $("#deptno").val();

                if (deptno!=""){
                    $.ajax({
                        url:"checkDeptNo",
                        data:{"deptNo":deptno},
                        success:function (resp) {
                            
                            $("#deptNoMsg").text(resp);
                        },
                        error: function (){
                            alert("接受错误")
                        }
                    })
                }

            })


        })
        function checkDept(){
            var deptno = $("#deptno").val();
            var deptname = $("#deptname").val();
            var location = $("#location").val();

            if (deptno==""|| deptname=="" || location==""){
                $("#formMsg").text("表单任何项都不能为空");
                return false;
            }
            return true;

        }

    </script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">人事管理</a></li>
        <li><a href="#">添加部门</a></li>
    </ul>
</div>

<div class="formbody">

    <form method="post" action="goDeptAdd" onsubmit="return checkDept()">
        <div class="formtitle"><span>基本信息</span></div>

        <ul class="forminfo">
            <li><label>部门编号</label><input name="deptno" type="text" class="dfinput" id="deptno"/> <font id="deptNoMsg" color="red" ></font></li>

            <li><label>部门名称</label><input name="deptname" type="text" class="dfinput" maxlength="15"id="deptname"/> </li>
            <li><label>办公地点</label><input name="location" type="text" class="dfinput" maxlength="5" id="location"/></li>
            <li><label>&nbsp;</label><input type="submit" class="btn" value="确认保存"/><font color="red" id="formMsg"></font> </li>
        </ul>

    </form>
</div>


</body>

</html>
