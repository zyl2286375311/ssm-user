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
	 var grid;
		$(function(){
			grid = $('#resourceTreegrid').treegrid({
				title : '资源列表',
				url : sys.contextPath + '/resource/selectResourceList.do',
				method:'post',
				idField : 'id',
				treeField : 'name',
			//	parentField : '_parentId',
				pagination:true,
				rownumbers:true,
/* 				pageNumber:1,
				pageSize:2,
				pageList:[2,4,6,8], */
				sortName : 'seq',
				sortOrder : 'asc',
				toolbar : '#toolbar',
				frozenColumns : [ [ {
					width : '200',
					title : '资源名称',
					field : 'name'
				} ] ],
				columns : [ [ {
					width : '200',
					title : '图标名称',
					field : 'iconCls'
				}, {
					width : '200',
					title : '资源路径',
					field : 'url'
				}, {
					width : '60',
					title : '资源类型',
					field : 'resourcetypeId',
					formatter : function(value, row) {
						return value;
					}
				}, {
					width : '150',
					title : '创建时间',
					field : 'createdatetime',
					hidden : true
				}, {
					width : '150',
					title : '修改时间',
					field : 'updatedatetime',
					hidden : true
				}, {
					width : '200',
					title : '资源描述',
					field : 'description',
					formatter : function(value, row) {
						if(value){
							return sys.formatString('<span title="{0}">{1}</span>', value, value);
						}
					}
				}, {
					width : '80',
					title : '排序',
					field : 'seq',
					hidden : true
				}, {
					title : '操作',
					field : 'action',
					width : '90',
					formatter : function(value, row) {
						var str = '';
						
						str += "<input class='easyui-linkbutton' dataoptions='iconCls:icon-remove' onclick='deleteResource("+row.id+")'>";
						
						return str;
					}
				} ] ],
				onBeforeLoad: function(row,param){
					if (!row) {
						param.id = null;	//  id=0
					}
				},
				onLoadSuccess:function(){
					//$('#resourceTreegrid').treegrid('expandAll');
				}
			});
		});
		
		var redoFun = function() {
			var node = grid.treegrid('getSelected');
			if (node) {
				grid.treegrid('expandAll', node.id);
			} else {
				grid.treegrid('expandAll');
			}
		};
		var undoFun = function() {
			var node = grid.treegrid('getSelected');
			if (node) {
				grid.treegrid('collapseAll', node.id);
			} else {
				grid.treegrid('collapseAll');
			}
		};
		var addDialog ;
		function addFun(){
			addDialog = $('#addResrouceDialog').dialog({    
			    title: '新增资源',    
			    width: 600,    
			    height: 500,    
			    closed: false,    
			    cache: false,    
			    href: sys.contextPath+'/resource/toAddResource.do',    
			    modal: true,
			    buttons:[{
					text:'保存',
					handler:function(){
						$.ajax({
							url:sys.contextPath+'/resource/addResource.do',
							type:'post',
							data:$("#addResource").serialize(),
							dataType:'json',
							success:function(data){
								$.messager.alert('警告',data.msg);
								//关闭弹框
								addDialog.dialog('close');
								//刷新 treeGrid
								grid.treegrid('reload');
							},
							error:function(){
								$.messager.alert('警告',"ajax失败");
							}
						})
						
					}
				},{
					text:'关闭',
					handler:function(){
						
					}
				}]
			});    
		}
		
		
		function deleteResource(id){
			alert(id);
		}
		

		
		
		
	</script>
</body>
</html>