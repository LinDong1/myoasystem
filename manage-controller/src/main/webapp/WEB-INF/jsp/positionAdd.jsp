
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<script src="js/jquery.js"></script>
<script>
    $(function () {
        $("#posid").blur(function () {
            var posid=$("#posid").val()
            if (posid!=""){
                $.ajax({
                    url:"checkPosid",
                    method:"post",
                    data:{"posid":posid},
                    success:function (resp) {
                        $("#posidmsg").text(resp)
                    },
                })
            }

        })
    })
</script>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">人事管理</a></li>
        <li><a href="#">添加岗位</a></li>
    </ul>
</div>

<div class="formbody">

    <form action="goPositionAdd" method="post">
        <div class="formtitle"><span>基本信息</span></div>

        <ul class="forminfo">
            <li><label>岗位编号</label><input name="posid" type="text" class="dfinput" id="posid"/><font color="red"id="posidmsg"></font></li>
            <li><label>岗位名称</label><input name="pname" type="text" class="dfinput" /></li>
            <li><label>岗位描述</label><input name="pdesc" type="text" class="dfinput" /></li>
            <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存"/></li>
        </ul>
    </form>



</div>


</body>

</html>
