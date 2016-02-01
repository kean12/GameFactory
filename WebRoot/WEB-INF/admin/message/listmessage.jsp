﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>九度网后台管理中心</title>
		<script type="text/javascript" src="${ctx}/js/tool.js"></script>
		<script type="text/javascript">
			function messageEdit(messageID,goPage) {
				location.href = "${ctx}/admin/message/messageAdmin!add.shtml?messageID=" + messageID +"&goPage=" +goPage;
			}
		</script>
	</head>

	<body>
		<div class="main_top_title">
			<dl id="manage_top">
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>站内消息&nbsp;>&nbsp;消息列表
				</dt>
				<dd class="p1" onclick=CreateFolder();>
					<a href="${ctx}/admin/message/messageAdmin!add.shtml">发送站内信</a>
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
					消息编号
				</th>
				<th width="30%">
					消息标题
				</th>
				<th width="15%">
					发送人
				</th>
				<th width="15%">
					发送时间
				</th>
				<th width="12%">
					类别
				</th>
				<th>
					操 作
				</th>
			</tr>
		
		<s:iterator value="messageList">
			<tr onMouseOver="this.style.background='#d6e5ff'" onMouseOut="this.style.background='#ffffff'">
				<td>
					<s:property value="id" />
				</td>
				<td>
					<s:property value="title" />
				</td>
				<td>
					<s:property value="addresser" />
				</td>
				<td>
					<s:property value="time" />
				</td>
				<td>
					<s:if test="type==1">
						系统消息
					</s:if>
					<s:else>
						客户消息
					</s:else>
				</td>
				<td>
					<input type="button" value="编辑" onClick="messageEdit(${id},${page.currentPage});" />
				</td>
			</tr>
		</s:iterator>
		</table>
		
		<div class="turnpage">
			<s:if test="page.maxPage>1">
				<s:if test="page.currentPage!=1">
					<a href="javascript:jumpPage(1);">首页</a>
					<a href="javascript:jumpPage(${page.currentPage-1});">上一页</a>
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
						<a href="javascript:jumpPage(${pageNum});">[<s:property value="#pageNum" />]</a>
					</s:else>
				</s:iterator>
				
				<s:if test="page.currentPage!=page.maxPage">
					<a href="javascript:jumpPage(${page.currentPage+1});">下一页</a>
					<a href="javascript:jumpPage(${page.maxPage});">尾页</a>
				</s:if>
				<s:else>
					<a disabled>下一页</a>
					<a disabled>尾页</a>
				</s:else>
			</s:if>
			
			
			<form name="pageForm" action="${ctx}/admin/message/messageAdmin.shtml" method="post">
				<input type="hidden" id="goPage" name="goPage" />
				<input type="hidden" id="type" name="type" value="${type}" />
			</form>
			
		</div>
	</body>
</html>
