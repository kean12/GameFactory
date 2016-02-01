﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>九度网后台管理中心</title>
		<script type="text/javascript" src="${ctx}/js/tool.js"></script>
		<script type="text/javascript">
			function serverEdit(serverID,goPage,serverName) {
				location.href = "${ctx}/admin/server/serverAdmin!add.shtml?serverID=" + serverID +"&goPage=" +goPage +"&serverName=" +serverName;
			}
		</script>
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
				<dd class="p9">
					<a href="#" onclick="history.back();">回上一级</a>
				</dd>
			</dl>
		</div>
		<div class="blank0"></div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable">
			<tr>
				<th width="10%">
					服务器编号
				</th>
				<th width="17%">
					服务器名称
				</th>
				<th width="10%">
					所属分区
				</th>
				<th width="8%">
					分区编号
				</th>
				<th width="15%">
					状态
				</th>
				<th>
					操 作
				</th>
			</tr>
		
		<s:iterator value="serverList">
			<tr onMouseOver="this.style.background='#d6e5ff'" onMouseOut="this.style.background='#ffffff'">
				<td>
					<s:property value="id" />
				</td>
				<td>
					<s:property value="serverName" />
				</td>
				<td>
					<s:property value="area.game.gameName" /> - <s:property value="area.areaName" />
				</td>
				<td>
					<s:property value="area.id" />
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
					<input type="button" value="编辑" onClick="serverEdit(${id},${page.currentPage},'${serverName}');" />
				</td>
			</tr>
		</s:iterator>
		</table>
		
		<div class="turnpage">
			<s:if test="page.maxPage>1">
			
				<s:url id="first" action="serverAdmin.shtml">
					<s:param name="goPage" value="1"/>
				</s:url>
				<s:url id="last" action="serverAdmin.shtml">
					<s:param name="goPage" value="%{page.maxPage}"/>
				</s:url>
				<s:url id="next" action="serverAdmin.shtml">
					<s:param name="goPage" value="%{page.currentPage+1}"/>
				</s:url>
				<s:url id="pre" action="serverAdmin.shtml">
					<s:param name="goPage" value="%{page.currentPage-1}"/>
				</s:url>
			
				<s:if test="page.currentPage!=1">
					<s:a href="%{first}">首页</s:a>
					<s:a href="%{pre}">上一页</s:a>
				</s:if>
				<s:else>
					<a disabled>首页</a>
					<a disabled>上一页</a>
				</s:else>
				
				<s:iterator value="page.pageNum" id="pageNum">
					<s:if test="page.currentPage==#pageNum">
						<strong>[<s:property value="#pageNum" />]</strong>
					</s:if>
					<s:else>
						<s:url id="go" action="serverAdmin.shtml">
							<s:param name="goPage" value="%{intValue()}"/>
						</s:url>
						<s:a href="%{go}">
							[<s:property value="#pageNum" />]
						</s:a>
					</s:else>
				</s:iterator>
				
				<s:if test="page.currentPage!=page.maxPage">
					<s:a href="%{next}">下一页</s:a>
					<s:a href="%{last}">尾页</s:a>
				</s:if>
				<s:else>
					<a disabled>下一页</a>
					<a disabled>尾页</a>
				</s:else>
			</s:if>
		</div>
	</body>
</html>
