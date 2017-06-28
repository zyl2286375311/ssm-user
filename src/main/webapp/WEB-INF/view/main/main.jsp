<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/common/include.jsp"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>主页面</title>
<script type="text/javascript">
</script>
</head>
<body id="mainLayout" class="easyui-layout">
	<div data-options="region:'north',href:'<%=request.getContextPath()%>/main/north.do'" style="height: 70px; overflow: hidden;" class="logo"></div>
	<div data-options="region:'west',href:'',split:true" title="导航" style="width: 200px; padding: 10px;">
		<ul id="mainMenu"></ul>
	</div>
	<div data-options="region:'center'" style="overflow: hidden;">
		<div id="mainTabs">
			<div title="首页" data-options="iconCls:'icon-filter'">
				<iframe src="<%=contextPath%>/main/home.do" allowTransparency="true" style="border: 0; width: 100%; height: 99%;" frameBorder="0"></iframe>
			</div>
		</div>
	</div>
	<div data-options="region:'south',href:'<%=contextPath%>/main/south.do',border:false" style="height: 30px; overflow: hidden;"></div>

	<div id="loginDialog" title="解锁登录" style="display: none;">
		<form method="post" class="easyui-form" >
			<table class="table">
				<tr>
					<th width="50">登录名</th>
					<td><input name="loginname" readonly="readonly" type="" value="${sessionInfo.user.loginname }" /></td>
				</tr>
				<tr>
					<th>密码</th>
					<td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="passwordDialog" title="修改密码" style="display: none;">
		<form  method="post" class="easyui-form">
			<table class="table">
				<tr>
					<th>旧密码</th>
					<td><input id="oldpwd" name="oldPwd" type="password" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>新密码</th>
					<td><input id="newpwd" name="newPwd" type="password" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>重复密码</th>
					<td><input type="password" class="easyui-validatebox" data-options="required:true,validType:'equals[\'#newpwd\']'" /></td>
				</tr>
			</table>
		</form>
	</div>
	
	<script type="text/javascript" charset="utf-8">
	
	/*
	*自定义验证规则----两次密码必须一致
	*/
	$(function(){	
		//自定义验证规则----两次密码必须一致
		$.extend($.fn.validatebox.defaults.rules, {    
		    equals: {    
		        validator: function(value,param){    
		            return value == $(param[0]).val();    
		        },    
		        message: '两次密码输入不一致'   
		    }    
		}); 
	});
	
	$(function(){
		/*
		*解锁登录----ajax校验登录
		*/
		var loginFun = function() {
			if ($('#loginDialog form').form('validate')) {
				$('#loginBtn').linkbutton('disable');
				$.post(
						sys.contextPath+'/user/checkSysUserLogin.do?flag=1',
						$('#loginDialog form').serialize() , 
						function(result) {
							if (result.success) {
								$('#loginDialog').dialog('close');
							} else {
								$.messager.alert('提示', result.msg, 'error', function() {
									$('#loginDialog form :input:eq(1)').focus();
								});
							}
							$('#loginBtn').linkbutton('enable');
					}, 'json');
			}
		};
		
		/*
		*解锁登录 dialog 弹框
		*/
		$('#loginDialog').show().dialog({
			modal : true,
			closable : false,
			iconCls : 'icon-add',
			buttons : [ {
				id : 'loginBtn',
				text : '登录',
				handler : function() {
					loginFun();
				}
			} ],
			onOpen : function() {
				$('#loginDialog form :input[name="pwd"]').val('');
				$('form :input').keyup(function(event) {
					if (event.keyCode == 13) {
						loginFun();
					}
				});
			}
		}).dialog('close');

		
		/*
		* 修改密码 dialog 弹框 
		*/
		$('#passwordDialog').show().dialog({
			modal : true,
			closable : true,
			iconCls : 'icon-edit',
			buttons : [ {
				text : '修改',
				handler : function() {
					/*
					* ajax修改密码
					*/
					if ($('#passwordDialog form').form('validate')) {
						$.post(
								sys.contextPath+'/user/updateSysUserPwd.do', 
								$('#passwordDialog form').serialize(),
								function(result) {
									if (result.success) {
										$.messager.alert('提示', result.msg, 'info');
										$('#passwordDialog').dialog('close');
									}else{
										$.messager.alert('提示', result.msg, 'warning');
									}
								}, 
								'json');
					}
				}
			} ],
			onOpen : function() {
				$('#passwordDialog form :input').val('');
			}
		}).dialog('close');
	});
	
	//tree菜单
	var mainMenu;
	var mainTabs;
	$(function(){
		/*
		*页面加载 渲染左侧 tree菜单
		*/
		mainMenu = $('#mainMenu').tree({
			//请求后台url
			url:'<%=request.getContextPath()%>/resource/selectMainMenu.do',
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
			//	alert(node.text+node.attributes.url);
				addTabs(node);
			},
		});
		
		/*
		*
		*/
		mainTabs = $('#mainTabs').tabs({
			fit : true,
			border : false,
			tools : [ {
				text : '刷新',
				iconCls : 'ext-icon-arrow_refresh',
				handler : function() {
					var panel = mainTabs.tabs('getSelected').panel('panel');
					var frame = panel.find('iframe');
						if (frame.length > 0) {
							for ( var i = 0; i < frame.length; i++) {
								frame[i].contentWindow.document.write('');
								frame[i].contentWindow.close();
								frame[i].src = frame[i].src;
							}
						}
				}
			}, {
				text : '关闭',
				iconCls : 'ext-icon-cross',
				handler : function() {
					var index = mainTabs.tabs('getTabIndex', mainTabs.tabs('getSelected'));
					var tab = mainTabs.tabs('getTab', index);
				//	mainTabs.tabs('getSelected').panel('options');
					if (tab.panel('options').closable) {
						mainTabs.tabs('close', index);//title
					} else {
						$.messager.alert('提示', '[' + tab.panel('options').title + ']不可以被关闭！', 'error');
					}
				}
			} ]
		});
		function addTabs(node){
			var tabs = $("#mainTabs")
			var src = sys.contextPath + node.attributes.url;
			if (tabs.tabs('exists', node.text)) {
				tabs.tabs('select', node.text);
			} else {
				tabs.tabs('add',{    
				    title:node.text,    
				    content:formatString('<iframe src="{0}" allowTransparency="true" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>', src),
				    closable:true,    
					border : false,
					fit : true
				});  
			}
		}
	})
	
	 function formatString(str) {
		for ( var i = 0; i < arguments.length - 1; i++) {
			str = str.replace("{" + i + "}", arguments[i + 1]);
		}
		return str;
};

	
	
	
	
	
	
	</script>
</body>
</html>