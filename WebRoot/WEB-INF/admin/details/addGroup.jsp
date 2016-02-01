<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>九度网后台管理中心</title>
		<script type="text/javascript">
			function checkForm(){
				var attributeName=document.getElementById("attributeName").value;
				var orderIndex=document.getElementById("orderIndex").value;
				if(!attributeName.match(/^\S+$/)){
					alert("名称不能为空");
					return false;
				}
				if(!orderIndex.match(/^[0-9]*[1-9][0-9]*$/)){
					alert("排列顺序请输入一个正整数");
					return false;
				}
			}
		</script>
	</head>
	<body>
		<div class="main_top_title">
			<dl id="manage_top">
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>挂卖自定义分类-添加属性分组
				</dt>
			</dl>
		</div>
	<form name="form1" action="${ctx}/admin/details/detailsAdmin!saveGroup.shtml" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
			<tr>
				<th width="19%">
					名称:
				</th>
				<td width="81%">
					<input type="text" id="attributeName" name="details.attributeName" value="${details.attributeName}" />
					<span class="red">*</span>
				</td>
			</tr>
			<tr>
				<th width="19%">
					所属类别:
				</th>
				<td width="81%">
					${parent.gameKind.game.gameName}-${parent.gameKind.bizKind.kindName}-${parent.attributeName}
					<span class="red">*</span>
				</td>
			</tr>
			<tr>
				<th width="19%">
					类型:
				</th>
				<td width="81%">
					<s:select list="#{1:'账号分组',2:'属性分组'}" name="details.type" listValue="value" listKey="key" theme="simple" />
					<span class="red"></span>
				</td>
			</tr>
			<tr>
				<th width="19%">
					排列顺序:
				</th>
				<td width="81%">
					<input type="text" id="orderIndex" name="details.orderIndex" value="${details.orderIndex}" />
					<span class="red"></span>
				</td>
			</tr>
			<tr>
				<th width="19%">
					是否启用:
				</th>
				<td width="81%">
					<s:select list="#{1:'启用',0:'禁用'}" name="details.isUser" listValue="value" listKey="key" theme="simple" />
					<span class="red"></span>
				</td>
			</tr>
			<tr>
				<th width="19%">
					操作:
				</th>
				<td width="81%">
					<input type="hidden" name="details.id" value="${details.id}" />
					<input type="hidden" name="parentID" value="${parentID}" />
					<input type="submit" name="method.save" value="保存" onClick="return checkForm();"/>
					<input type="button" name="method.list" value="取消" onclick="history.back();"/>
				</td>
			</tr>
		</table>
	</form>
	</body>
</html>