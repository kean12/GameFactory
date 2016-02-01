﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>九度网后台管理中心</title>
		<script type="text/javascript" src="${ctx}/js/tool.js"></script>
		<script type="text/javascript">
			function checkForm(){
				var professionName=$("professionName").value.Trim();
				var orderIndex=$("orderIndex").value.Trim();
				if(!professionName.match(/^\S+$/)){
					alert("职业名称必须填写！");
					$("professionName").select();
					return false;
				}else{
					$("professionName").value=professionName;
				}
				if(!orderIndex.match(/^[0-9]*[1-9][0-9]*$/)){
					alert("排列顺序请输入一个正整数");
					$("orderIndex").select();
					return false;
				}else{
					$("orderIndex").value=orderIndex;
				}
			}
		</script>
	</head>

	<body>
		<div class="main_top_title">
			<dl id=manage_top>
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>添加游戏职业
				</dt>
			</dl>
		</div>
	
	<form name="professionFrom" action="${ctx}/admin/profession/professionAdmin!saveProfession.shtml" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
			<tr>
				<th width="19%">
					职业名称:
				</th>
				<td width="81%">
					<input id="professionName" name="profession.professionName" value="${profession.professionName}"  class="upfile" size="80" />
					<span class="red">*</span>
				</td>
			</tr>
			<tr>
				<th width="19%">
					所属游戏:
				</th>
				<td width="81%">
					${game.gameName}
				</td>
			</tr>
			<tr>
				<th width="19%">
					排列顺序:
				</th>
				<td width="81%">
					<input id="orderIndex" name="profession.orderIndex" value="${profession.orderIndex }"  class="upfile" size="80" />
					<span class="red">*</span>
				</td>
			</tr>
			<tr>
				<th width="19%">
					是否启用:
				</th>
				<td width="81%">
					<s:select list="#{1:'启动',0:'禁用'}" name="profession.isUse" listValue="value" listKey="key" theme="simple"   />
				</td>
			</tr>
			<tr>
				<th width="19%">
					操作:
				</th>
				<td width="81%">
					<input type="hidden" name="profession.id" value="${profession.id}">
					<input type="hidden" name="gameID" value="${gameID}">
					<input type="submit" value="保存" onClick="return checkForm();" />
					<input type="button" value="取消" onclick="history.back();" />
				</td>
			</tr>
		</table>
	</form>
	
	</body>
</html>
