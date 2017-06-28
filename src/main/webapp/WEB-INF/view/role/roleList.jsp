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



	<!-- datagrid-->
	<table id="roleDataGrid"></table>
	
	<!-- datagrid 工具条 -->
	<div id="tb">
	<a href="javascript:resourceDialog('showResourceDialog')" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">查看权限</a>
	<a href="javascript:resourceDialog('updateResourceDialog')" class="easyui-linkbutton" data-options="iconCls:'icon-help',plain:true">修改权限</a>
	</div>
	
	
	<!-- 查看 资源   dialog -->
	<div id="showResourceDialog" style="display:none">
		<fieldset>
			<legend>查看角色授权</legend>
			<ul name="resourceTree"></ul>
		</fieldset>
	</div>
	
	<!-- 修改 资源   dialog -->
	<div id="updateResourceDialog" style="display:none">
		<fieldset>
			<legend>修改角色授权</legend>
			<ul name="resourceTree"></ul>
		</fieldset>
	</div>


</body>
</html>