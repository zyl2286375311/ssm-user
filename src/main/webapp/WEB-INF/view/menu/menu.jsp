<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ include file="/common/include.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<!-- tree插件 -->
<ul id="menuTree">

</ul>
<!-- 异步tree加载 -->
<!-- 递归tree加载 -->
<script type="text/javascript">

    $(function(){

        $("#menuTree").tree({
            //请求后台url
            url:'<%=request.getContextPath()%>/menu/selectMenuList.do',
            //访问方式默认post
            //method:'post',
            //显示复选框
            checkbox:true,
            //选中子节点时是否级联选中父节点
            cascadeCheck:true,
            //末级节点显示复选框
            onlyLeafCheck:true,
            //显示虚线
            lines:true,
            onClick:function(node){
                //node点击的当前节点
                alert(node.text+node.id+node.state);
            },
        });
    })
</script>

<input value="获取所有被选中的节点的id">


<input value="回显节点的是否被选中状态">

<!--





 -->




</body>
</html>