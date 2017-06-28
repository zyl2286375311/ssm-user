<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="sessionInfoDiv" style="position: absolute; right: 10px; top: 5px;">
	欢迎<font color='red' size='5'>${sessionInfo.user.loginname }</font>登录！！！
</div>
<div style="position: absolute; right: 0px; bottom: 0px;">
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#kzmbMenu',iconCls:'ext-icon-bell'">控制面板</a> 
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#zxMenu',iconCls:'icon-ok'">注销</a>
</div>

<div id="kzmbMenu" style="width: 100px; display: none;">
	<div data-options="iconCls:'icon-ok'" onclick="$('#passwordDialog').dialog('open');">修改密码</div>
	 <div class="menu-sep"></div>   
	<div data-options="iconCls:'icon-ok'" onclick="showMyInfoFun();">我的信息</div>
</div>
<div id="zxMenu" style="width: 100px; display: none;">
	<div data-options="iconCls:'icon-ok'" onclick="$('#loginDialog').dialog('open');">锁定窗口</div>
	<div data-options="iconCls:'icon-ok'" onclick="logoutFun();">退出系统</div>
</div>


<script type="text/javascript" charset="utf-8">
	var logoutFun = function() {
		$.post(
				sys.contextPath+'/user/logoutSysUser.do', 
				function(result) {
						window.location.href=sys.contextPath+"/index.jsp";
				}, 'text');
	};  
	var showMyInfoFun = function() {
		var url = sys.contextPath + '/main/userInfo.do';
		var dialog = $('<div/>').dialog({
			title : '我的信息',
			content:'<iframe id="" src="' +url + '" allowTransparency="true" scrolling="auto" width="100%" height="98%" frameBorder="0" name=""></iframe>',
			width : 640,
			height : 480,
			modal : true,
			onClose : function() {
				$(this).dialog('destroy');
			},
		});
	};
	
	
</script>
	