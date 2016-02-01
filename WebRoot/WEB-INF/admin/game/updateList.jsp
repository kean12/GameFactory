﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
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
    <dt class="manage_top_title"><img src="${ctx}/images/admin/ico01.gif" width="6" height="10"> <strong>当前位置：</strong>更新游戏文件</dt>
  </dl>
</div>

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
  <tr>
    <th width="19%">更新游戏文件:</th>
    <td width="81%"><strong class="red">注意：</strong><BR>
      1.更新游戏文件功能主要用于更新首页，我要买页面，我要卖等页面的游戏列表。<BR>
      2.当您改动了后台管理的游戏列表中的文件后，前台的游戏列表页面是不会随之更新的。<BR>
      3.您需要在此处点击&rdquo;确定更新&ldquo;后，前台的游戏列表才会随之更新。<BR>
      4.若后台游戏列表无改动，请不要使用此功能，以免增加服务器的工作压力。<BR>
    前台游戏列表示意图：<BR></td>
  </tr>
  <tr>
    <th width="19%">操作:</th>
    <td width="81%"><input type="button" name="method.save" value="确定更新" onClick="location.href='${ctx}/admin/game/gameAdmin!saveGameList.shtml'"/></td>
  </tr>
</table>
</body>
</html>