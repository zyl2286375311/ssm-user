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

<script type="text/javascript">
    $(function(){
        $("#roleDataGrid").datagrid({
            url:sys.contextPath+'/role/selectRoleList.do',
            method:'post',
            columns:[[
                {field:'id',width:150,title:'ID'},
                {field:'name',width:150,title:'角色名'},
                {field:'description',width:150,title:'角色描述'},
            ]],
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
            toolbar: '#tb',
        });
    })


    //权限 dialog
    function resourceDialog(dialogId){
        //获取被选中的行
        var trObj = $("#roleDataGrid").datagrid('getSelected');
        if (trObj) {
            if (dialogId == 'showResourceDialog') {
                $("#"+dialogId).dialog({
                    title: '查看资源',
                    width: 400,
                    height: 400,
                    closed: false,
                    cache: false,
                    onBeforeOpen:function(){
                        //初始化 资源tree插件
                        initTree(dialogId,trObj.id);
                    }
                })
            }else{
                $("#"+dialogId).dialog({
                    title: '修改资源',
                    width: 400,
                    height: 400,
                    closed: false,
                    cache: false,
                    buttons:[
                        {
                            text:'授予权限',
                            iconCls:'icon-edit',
                            handler:function(){
                                //给当前角色授予权限/修改权限
                                grandResource(dialogId,trObj.id);
                            }
                        }
                    ],
                    onBeforeOpen:function(){
                        //初始化 资源tree插件
                        initTree(dialogId,trObj.id);
                    }
                })
            }
        }
    }

    //初始化 资源tree插件
    function initTree(dialogId,roleId){
        //后代选择器   父元素  空格 后代元素
        //$("#"+dialogId+' ul')

        //获取后代方法 find()
        var  ul = $("#"+dialogId).find('ul');
        $(ul).tree({
            url:sys.contextPath+'/resource/getResourceTree.do',
            method:'post',
            checkbox:true,
            onLoadSuccess:function(node,data){
                $.ajax({
                    url:sys.contextPath+'/resource/getResourceByRoleId.do',
                    type:'POST',
                    data:{'id':roleId},
                    dataType:'json',
                    success:function(data){
                        for (var i = 0; i < data.length; i++) {
                            //根据节点id获取node节点
                            var node =$(ul).tree('find',data[i].sysResourceId);
                            //当node节点存在是，判断node节点是否是叶子节点/子节点 --- isLeaf方法
                            if (node) {
                                //isLeaf 返回boolean类型---判断node节点是否是叶子节点
                                var isLeaf = $(ul).tree('isLeaf',node.target)
                                if (isLeaf) {
                                    $(ul).tree('check',node.target);
                                }
                            }
                        }
                    }
                })
            }
        });
    }


    //授予权限/修改权限
    function grandResource(dialogId,roleId){

        //获取后代元素 ul
        var ul = $("#"+dialogId).find('ul');

        var resourceIds="";
        //获取 被选中复选框的 node节点id   以及   不确定的复选框的 node节点id【父级节点半选中状态】
        var nodes = $(ul).tree('getChecked',['checked','indeterminate']);
        $(nodes).each(function(){
            resourceIds += this.id+",";
        });
        $.post(
            sys.contextPath+'/resource/grantResourceOfRole.do',
            {'sysRoleId':roleId,'sysResourceId':resourceIds},
            function(data){
                $.messager.alert('提示信息',data.msg,'info');
                $("#"+dialogId).dialog('close');
            },
            'json'
        );

    }

</script>
</body>
</html>