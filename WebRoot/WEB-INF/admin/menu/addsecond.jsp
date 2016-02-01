<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>九度网后台管理中心</title>
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript">var AP="${ctx}";</script>
		<script src="${ctx}/js/tool.js"></script>
		<script type="text/javascript">
			function checkForm(){
				var menuName = $("menuName").value.Trim();
				var orderIndex = $("orderIndex").value.Trim();
				if(!menuName.match(/^\S+$/)) {
					alertDialog("请输入菜单名称!");
					return false;
				}else {
					$("menuName").value = menuName;
				}
				if(!orderIndex.match(/^[0-9]*[1-9][0-9]*$/)){
					alertDialog("请填写排列顺序");
					return false;
				}else {
					$("orderIndex").value = orderIndex;
				}
			}
		</script>
	</head>
	<body>
		<div class="main_top_title">
			<dl id="manage_top">
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>菜单管理
				</dt>
			</dl>
		</div>
		<form name="form1" action="${ctx}/admin/menu/menuAdmin!saveSecond.shtml" method="post">
		<input type="hidden" name="menu.power" value="${menu.power}">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
			<tr>
				<th width="19%">
					菜单名称:
				</th>
				<td width="81%">
					<INPUT class="upfile" id="menuName" name="menu.menuName" size=80 value="${menu.menuName}" />
					<span class="red">*</span>
				</td>
			</tr>
			<tr>
				<th width="19%">
					顺序(数字):
				</th>
				<td width="81%">
					<INPUT class="upfile" id="orderIndex" name="menu.orderIndex" size=80 value="${menu.orderIndex}" />
					<span class="red">*</span>
				</td>
			</tr>
			<tr>
				<th width="19%">
					是否显示:
				</th>
				<td width="81%">
					<INPUT class="upfile" id="isView" name="menu.isView" type="checkbox" checked="checked" value="1" />
					<span class="red">*</span>
				</td>
			</tr>
			<tr style="display:none;">
				<th width="19%">
					父节点:
				</th>
				<td width="81%">
					<INPUT type="text" name="menu.parent.id" value="${parentID}" />
					<INPUT type="hidden" id="menuID" name="menu.id" value="${menu.id}" />
					<span class="red"></span>
				</td>
			</tr>
			<tr>
				<th width="19%">
					操作:
				</th>
				<td width="81%">
					<input type="submit" name="method.save" value="保存" onClick="return checkForm();"/>
					<input type="button" name="method.list" value="取消" onclick="history.back();"/>
				</td>
			</tr>
		</table>
		</form>
	</body>
</html>