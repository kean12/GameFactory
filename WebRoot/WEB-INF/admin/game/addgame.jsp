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
			<form id="gameForm" method="post" action="${ctx}/admin/game/gameAdmin.shtml" class="fr">
				游戏搜索：
				<input name="gameName" id="gameName" type="text" onFocus="fEvent('focus',this)" onBlur="fEvent('blur',this)" value="请输入游戏名称" size="30" />
				<button type="submit" name="method.search" >
					搜索游戏
				</button>
			</form>
			<dl id=manage_top>
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>添加游戏
				</dt>
			</dl>
		</div>
		<form action="${ctx}/admin/game/gameAdmin!save.shtml" method="post" name="gameform">
			<input type="hidden" name="gameName" value="${gameName}" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
				<tr>
					<th width="19%">
						游戏名称:
					</th>
					<td width="81%">
						<input class="upfile" size="80" value="${game.gameName}" name="game.gameName" id="gameName">
						<span class="red">*</span>
					</td>
				</tr>
				<tr>
					<th width="19%">
						营运商:
					</th>
					<td width="81%">
						<input class="upfile" size="80" value="${game.company}" name="game.company" id="company">
						<span class="red">*</span>
					</td>
				</tr>
				<tr>
					<th width="19%">
						是否热门:
					</th>
					<td width="81%">
						<SELECT size=1 name="game.gameHot">
							<option value="1">
								是
							</option>
							<option value="0">
								否
							</option>
						</SELECT>
					</td>
				</tr>
				<tr>
					<th width="19%">
						游戏状态(是否可用):
					</th>
					<td width="81%">
						<SELECT size=1 name="game.state">
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
						<input type="hidden" name="game.id" value="${game.id}" />
						<input type="hidden" name="game.pinyin" value="${game.pinyin }" />
						<input type="hidden" name="game.gameIndex" value="${game.gameIndex }" />
						<input type="hidden" name="gameName" value="${gameName}" />
						<input type="hidden" name="goPage" value="${goPage}" />
						<input type="submit" name="method.save" value="保存" onclick="return checkForm();"/>
						<input type="button" name="method.list" value="取消" onclick="history.back();" />
					</td>
				</tr>
			</table>
		</form>
		
	</body>
</html>