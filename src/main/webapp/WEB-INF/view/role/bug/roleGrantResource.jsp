<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>资源权限</h1>
	
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