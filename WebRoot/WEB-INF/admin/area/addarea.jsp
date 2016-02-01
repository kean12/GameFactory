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
			<form name="areaSearchForm" method="post" action="${ctx}/admin/area/areaAdmin.shtml" class="fr">
				分区搜索：
				<input name="areaName" type="text" onFocus="fEvent('focus',this)" onBlur="fEvent('blur',this)" value="请输入分区名称" size="30" />
				<input type="hidden" name="gameID" value="${gameID}" />
				<button type="submit" name="method.search">
					搜索分区
				</button>
			</form>
			<dl id=manage_top>
				<dt class="manage_top_title">
					<img src="images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>添加游戏
				</dt>
			</dl>
		</div>
		<form action="${ctx}/admin/area/areaAdmin!save.shtml" method="post" name="areaform">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
				<tr>
					<th width="19%">
						分区名称:
					</th>
					<td width="81%">
						<input class="upfile" size="80" value="${area.areaName}" name="area.areaName" id="areaName">
						<span class="red">*</span>
					</td>
				</tr>
				<tr>
					<th width="19%">
						分区状态(是否可用):
					</th>
					<td width="81%">
						<SELECT size=1 name="area.state">
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
						<input type="hidden" name="area.id" value="${area.id }" />
						<input type="hidden" name="gameID" value="${gameID }" />
						<input type="hidden" name="areaName" value="${areaName }" />
						<input type="hidden" name="goPage" value="${goPage}" />
						<input type="submit" name="method.save" value="保存" onclick="return checkForm();"/>
						<input type="button" name="method.list" value="取消" onclick="history.back();" />
					</td>
				</tr>
			</table>
		</form>
		
	</body>
</html>
