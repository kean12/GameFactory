<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="STYLESHEET" type="text/css" href="${ctx}/plugins/dhtmlxTree/codebase/dhtmlxtree.css">
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<script  src="${ctx}/plugins/dhtmlxTree/codebase/dhtmlxcommon.js"></script>
		<script  src="${ctx}/plugins/dhtmlxTree/codebase/dhtmlxtree.js"></script>
		<script  src="${ctx}/plugins/dhtmlxTree/codebase/ext/dhtmlxtree_json.js"></script>
		<title>权限查看与设置</title>
	</head>
	<body>
	<div class="main_top_title">
		<dl id=manage_top>
			<dt class="manage_top_title">
				<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
				<strong>当前位置：</strong>权限查看与设置
			</dt>
			<dd class="p1"><a href="javascript:void(0)" onclick="saveRoleLimt();">保 存</a></dd>
			<dd class="p1"><a href="javascript:void(0);" onclick="reinitTree();">刷 新</a></dd>
		</dl>
	</div>

		
<table height="90%">
    <tr>
        <td bgcolor="f5f5f5" valign="top" height="100%" style="width:300px;border :1px solid Silver; overflow:auto;">
        <div id="treeboxbox_tree2" style="background:none"></div>
        </td>
        <td  style="padding-left:25" valign="top">
        <font color="red">*系统设置菜单在树形结构中不显示，权限应为‘管理员’权限!</font><br />
        <font color="red">${errorMessage} </font><br />
        </td>
    </tr>
</table>    
<script>
tree2 = new dhtmlXTreeObject("treeboxbox_tree2", "100%", "100%", 0);
 
tree2.setSkin('dhx_skyblue');
tree2.setImagePath("${ctx}/plugins/dhtmlxTree/codebase/imgs/csh_bluebooks/");
tree2.setXMLAutoLoading("${ctx}/admin/roleLimit/roleLimit!dhtmlxTreeJSON.shtml");

tree2.enableCheckBoxes(1);
tree2.enableThreeStateCheckboxes(true);
tree2.loadJSON("${ctx}/admin/roleLimit/roleLimit!dhtmlxTreeJSON.shtml");
function reinitTree(){
	tree2.deleteChildItems(0);
	tree2.loadJSON("${ctx}/admin/roleLimit/roleLimit!dhtmlxTreeJSON.shtml");	
}
function saveRoleLimt(){
	//tree2.setCheck(tree2.getSelectedItemId(),true);
	//tree2.getAllPartiallyChecked();
	//tree2.tree2.getAllChecked();
	var strRoleLimit = tree2.getAllCheckedBranches() ;
	
	if(strRoleLimit){
		location.href="${ctx}/admin/roleLimit/roleLimit!saveRoleLimits.shtml?strRoleLimit=" + strRoleLimit;
	}	
}
</script>
</body></html>