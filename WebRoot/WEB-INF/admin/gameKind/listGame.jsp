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
			<form id="gameForm" method="post" action="${ctx}/admin/gameKind/gameKindAdmin.shtml" class="fr">
				游戏搜索：
				<s:if test="gameName==null">
					<input name="gameName" id="gameName" type="text" onFocus="fEvent('focus',this)" onBlur="fEvent('blur',this)" value="请输入游戏名称" size="30" />
				</s:if>
				<s:else>
					<input name="gameName" id="gameName" type="text" onFocus="fEvent('focus',this)" onBlur="fEvent('blur',this)" value="${gameName}" size="30" />
				</s:else>
				<button type="submit" name="method.search" >
					搜索游戏
				</button>
			</form>
			
			<dl id="manage_top">
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>游戏列表
				</dt>
				<dd class="p9">
					<a href="#" onclick="history.back();">回上一级</a>
				</dd>
			</dl>
		</div>
		<div class="blank0"></div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable">
			<tr>
				<th width="10%">
					游戏编号
				</th>
				<th width="17%">
					游戏名称
				</th>
				<th width="10%">
					全拼
				</th>
				<th width="8%">
					首字母
				</th>
				<th width="15%">
					运营商
				</th>
				<th width="5%">
					是否热门
				</th>
				<th width="5%">
					是否可用
				</th>
				<th>
					操 作
				</th>
			</tr>
		
		<s:iterator value="gameList">
			<tr onMouseOver="this.style.background='#d6e5ff'" onMouseOut="this.style.background='#ffffff'">
				<td>
					<s:property value="id" />
				</td>
				<td>
					<s:property value="gameName" />
				</td>
				<td>
					<s:property value="pinyin" />
				</td>
				<td>
					<s:property value="gameIndex" />
				</td>
				<td>
					<s:property value="company" />&nbsp;
				</td>
				<td>
					<s:if test="gameHot==1">
						热门
					</s:if>
					<s:else>
						<span class="red">否</span>
					</s:else>
				</td>
				<td>
					<s:if test="state==1">
						可用
					</s:if>
					<s:else>
						<span class="red">禁用</span>
					</s:else>
				</td>
				<td>
					<input type="button" value="查看挂卖类别" onClick="gameKindEditor(${id});" />
				</td>
			</tr>
		</s:iterator>
		</table>
		<div class="turnpage">
			<jsp:include page="/WEB-INF/index/common/pagination.jsp"></jsp:include>
			<form name="pageForm" action="${ctx}/admin/gameKind/gameKindAdmin.shtml" method="post">
				<input type="hidden" id="goPage" name="goPage" />
				<input type="hidden" id="type" name="type" value="${type}" />
				<input type="hidden" id="page_gameName" name="gameName" value="${gameName}" />
				
				<input type="hidden" id="gameID" name="gameID" />
			</form>
		</div>
		<script type="text/javascript">
			function gameKindEditor(gameID) {
				$("gameID").value=gameID;
				document.pageForm.action="${ctx}/admin/gameKind/gameKindAdmin!listGameKind.shtml";
				document.pageForm.submit();
			}
		</script>
		
	</body>
</html>