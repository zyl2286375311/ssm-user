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
	<script type="text/javascript">
        <!--页面加载时 查询userlist集合 -->
        $(function(){
            $("#userDataGrid").datagrid({
                url:'<%=request.getContextPath()%>/user/selectUserList.do',
                method:'post',
                pagination:true,
                rownumbers:true,
                pageNumber:1,
                pageSize:2,
                pageList:[2,4,6,8],
                striped : true,
                rownumbers : true,
                pagination : true,
                singleSelect : true,
                idField : 'id',
                loadMsg:'候着。。。',
                toolbar: '#tb',
                columns:[
                    [
                        {field:'id',title:'ID',width:120},
                        {field:'loginname',title:'账户名',width:120},
                        {field:'name',title:'真实名',width:120},
                        {field:'createdatetime',title:'创建时间',width:120},
                        {field:'updatedatetime',title:'修改时间',width:120},
                    ]
                ]
            });
        });


        function showRoleInfo(flag){
            //获取被选中的 行的 id
            var trObj = $("#userDataGrid").datagrid('getSelected');
            if (flag == 2) {
                $("#showRoleDialog").dialog({
                    buttons:[{
                        text:'修改',
                        iconCls:'icon-edit',
                        handler:function(){
                            grantRole(trObj.id)
                        }
                    }]
                });
            }

            //trObj 非空判断
            if (trObj) {
                $("#showRoleDialog").dialog({
                    title: '查看角色',
                    width: 400,
                    height: 200,
                    closed: false,
                    cache: false,
                    onBeforeOpen:function(){
                        $("#roleTree").tree({
                            url:sys.contextPath+'/user/getRoleTree.do',
                            method:'post',
                            checkbox:true,
                            onLoadSuccess:function(node,data){
                                $.post(
                                    sys.contextPath+'/user/getUserRoleInfo.do',
                                    {"id":trObj.id},
                                    function(msg){
                                        //msg 用户拥有的角色信息
                                        for (var i = 0; i < msg.length; i++) {
                                            //find 根据节点id查找节点对象的方法
                                            node = $("#roleTree").tree('find',msg[i].sysRoleId)
                                            //	alert(node.text);
                                            if (node) {
                                                //check 选中节点的方法
                                                $("#roleTree").tree('check',node.target);
                                            }
                                        }
                                    },
                                    'json'
                                );
                            }
                        });
                    },
                });
            }
        }

        //赋予角色信息，授予角色信息
        //根据用户ID删除角色信息
        //将新选中的角色信息添加到 用户角色关联关系表
        function grantRole(userId){
            var roleIds="";
            //获取节点中所有被选中的复选框的id
            var nodes = $('#roleTree').tree('getChecked','checked');
            $(nodes).each(function(){
                roleIds +=this.id+',';
            });
            $.post(
                sys.contextPath+'/user/grantRoleOfUser.do',
                {"id":userId,"roleIds":roleIds},
                function(data){

                    $.messager.alert('信息',data.msg	);

                    $("#showRoleDialog").dialog('close');
                },
                'text'
            );
        }


	</script>

</body>
</html>