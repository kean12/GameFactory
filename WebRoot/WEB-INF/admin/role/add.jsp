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
				alert("表单验证");
			}
		</script>
	</head>
	<body>
		<div class="main_top_title">
			<dl id="manage_top">
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>用户管理
				</dt>
			</dl>
		</div>
		<form action="${ctx}/admin/role/role!save.shtml" method="post">
		<INPUT type="hidden" name="role.id" value="${role.id}" />
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
			<tr>
				<th width="19%">
					角色名称:
				</th>
				<td width="81%">
					<INPUT class="upfile" id="name" name="role.name" size=80 value="${role.name}" />
					<span class="red">*</span>
				</td>
			</tr>
			<tr>
				<th width="19%">
					备注:
				</th>
				<td width="81%">
					<INPUT class="upfile" id="description" name="role.description" size=80 value="${role.description}" />
					<span class="red">*</span>
				</td>
			</tr>
			<tr>
				<th width="19%">
					状态:
				</th>
				<td width="81%">
					<s:select list="#{1:'启用',0:'禁用'}"  name="role.state"  listValue="value" listKey="key" theme="simple"   />
					<span class="red"></span>
				</td>
			</tr>
			<tr>
				<th width="19%">操作:</th>
				<td width="81%">
					<input type="submit" value="保存"/>
					<input type="button" value="取消" onclick="history.back();"/>
				</td>
			</tr>
		</table>
		</form>
	</body>
</html>