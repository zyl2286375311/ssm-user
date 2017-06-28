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
	
	<!-- treeGrid 的工具条-->
	<div id="toolbar" style="display: none;">
				<a onclick="redoFun();" href="javascript:void(0);" class="easyui-linkbutton"
				 data-options="plain:true,iconCls:'ext-icon-resultset_next'">展开</a>
				 <a onclick="undoFun();" href="javascript:void(0);" class="easyui-linkbutton" 
				 data-options="plain:true,iconCls:'ext-icon-resultset_previous'">折叠</a>
				  <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" 
				 data-options="plain:true,iconCls:'ext-icon-resultset_previous'">新增</a>
	</div>
	
	<!-- treeGrid-->
	<table id="resourceTreegrid"   ></table>  
	
	<!-- 新增资源 -->
	<div id="addResrouceDialog"></div>

	<script type="text/javascript">
	</script>
</body>
</html>