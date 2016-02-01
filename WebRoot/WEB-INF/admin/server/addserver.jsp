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
			<form name="serverSearchForm" method="post" action="${ctx}/admin/server/serverAdmin.shtml" class="fr">
				服务器搜索：
				<input name="serverName" type="text" onFocus="fEvent('focus',this)" onBlur="fEvent('blur',this)" value="请输入服务器名称" size="30" />
				<input type="hidden" name="areaID" value="${areaID}" />
				<button type="submit" name="method.search">
					搜索服务器
				</button>
			</form>
			<dl id="manage_top">
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>游戏列表&nbsp;>&nbsp;分区列表&nbsp;>&nbsp;服务器列表
				</dt>
				<dd class="p1" onclick=CreateFolder();>
					<a href="${ctx}/admin/server/serverAdmin!add.shtml?areaID=${areaID}">添加服务器</a>
				</dd>
				<dd class="p9" disabled>
					<a href="#" onclick="history.back();">回上一级</a>
				</dd>
			</dl>
		</div>
		
		<form action="${ctx}/admin/server/serverAdmin!save.shtml" method="post" name="serverform">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
				<tr>
					<th width="19%">
						服务器名称:
					</th>
					<td width="81%">
						<input class="upfile" size="80" value="${server.serverName}" name="server.serverName" id="serverName">
						<span class="red">*</span>
					</td>
				</tr>
				<tr>
					<th width="19%">
						服务器状态(是否可用):
					</th>
					<td width="81%">
						<SELECT size=1 name="server.state">
							<option value="1">
								开启
							</option>
							<option value="0">
								禁用
							</option>
						</SELECT>
					</td>
				</tr>
				<tr>
					<th width="19%">
						操作:
					</th>
					<td width="81%">
						<input type="hidden" name="server.id" value="${server.id }" />
						<input type="hidden" name="areaID" value="${areaID }" />
						<input type="hidden" name="serverName" value="${serverName }" />
						<input type="hidden" name="goPage" value="${goPage}" />
						<input type="submit" name="method.save" value="保存" onclick="return checkForm();"/>
						<input type="button" name="method.list" value="取消" onclick="history.back();" />
					</td>
				</tr>
			</table>
		</form>
		
	</body>
</html>
