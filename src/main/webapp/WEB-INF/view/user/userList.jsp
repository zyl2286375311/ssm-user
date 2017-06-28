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

	<!-- ID唯一！！！  -->
	
	<!-- 条件查询表单 -->
	
	<!-- easyui渲染方式二  datagrid() -->
	<table id="userDataGrid"></table>
	
	<!-- datagrid 工具条 -->
	<div id="tb">
	<a href="javascript:showRoleInfo(1)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">查看角色</a>
	<a href="javascript:showRoleInfo(2)" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">修改角色</a>
	</div>
	
	<!-- 查看角色 dialog -->
	<div id="showRoleDialog">
		<fieldset>
			<legend>角色授权</legend>
			<ul id="roleTree"></ul>
		</fieldset>
	</div>
	

</body>
</html>