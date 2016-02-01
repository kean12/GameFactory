﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>九度网后台管理中心</title>
		<script type="text/javascript" src="${ctx}/js/tool.js"></script>
	</head>

	<body>
		<div class="main_top_title">
			<dl id="manage_top">
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>游戏职业列表
				</dt>
				<dd class="p1" onclick=CreateFolder();>
					<a href="${ctx}/admin/profession/professionAdmin!addProfession.shtml?gameID=${gameID}">添加游戏职业</a>
				</dd>
				<dd class="p9">
					<a href="#" onclick="history.back();">回上一级</a>
				</dd>
			</dl>
		</div>
		<div class="blank0"></div>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable" >
			<tr>
				<th width="10%">
					编号
				</th>
				<th width="10%">
					职业名称
				</th>
				<th width="10%">
					所属游戏
				</th>
				<th width="10%">
					排列顺序
				</th>
				<th width="10%">
					是否使用
				</th>
				<th width="10%">
					操作
				</th>
			</tr>
		
		<s:iterator value="professionList">
			<tr onMouseOver="this.style.background='#d6e5ff'" onMouseOut="this.style.background='#ffffff'">
				<td>
					<s:property value="id" />&nbsp;
				</td>
				<td>
					<s:property value="professionName" />&nbsp;
				</td>
				<td>
					<s:property value="game.gameName" />&nbsp;
				</td>
				<td>
					<s:property value="orderIndex" />&nbsp;
				</td>
				<td>
					<s:if test="isUse==1">
						启用
					</s:if>
					<s:else>
						<span class="red">禁用</span>
					</s:else>
				</td>
				<td>
					<input type="button" value="编辑" onclick="window.location='${ctx}/admin/profession/professionAdmin!addProfession.shtml?professionID=${id}&gameID=${gameID}'" />
					<input type="button" value="删除" onclick="window.location='${ctx}/admin/profession/professionAdmin!deleteProfession.shtml?professionID=${id}&gameID=${gameID}'" />
				</td>
			</tr>
		</s:iterator>
		</table>
		
	</body>
</html>