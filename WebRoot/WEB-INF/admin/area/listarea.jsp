﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>九度网后台管理中心</title>
		<script type="text/javascript" src="${ctx}/js/tool.js"></script>
		<script type="text/javascript">
			function areaEdit(areaID,goPage,areaName) {
				location.href = "${ctx}/admin/area/areaAdmin!add.shtml?areaID=" + areaID +"&goPage=" +goPage +"&areaName=" +areaName;
			}
		
			function serverList(areaID) {
				location.href = "${ctx}/admin/server/serverAdmin.shtml?areaID=" +areaID;
			}
		</script>
	</head>

	<body>
		<div class="main_top_title">
			<form id="areaForm" method="post" action="${ctx}/admin/area/areaAdmin.shtml" class="fr">
				分区搜索：
				<input name="areaName" type="text" onFocus="fEvent('focus',this)" onBlur="fEvent('blur',this)" value="请输入分区名称" size="30" />
				<input type="hidden" name="gameID" value="${gameID}" />
				<button type="submit" name="method.search">
					搜索分区
				</button>
			</form>
			
			<dl id="manage_top">
				<dt class="manage_top_title">
					<img src="images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>游戏列表&nbsp;>&nbsp;分区列表
				</dt>
				<dd class="p1" onclick=CreateFolder();>
					<a href="${ctx}/admin/area/areaAdmin!add.shtml?gameID=${gameID}">添加分区</a>
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
					分区编号
				</th>
				<th width="17%">
					分区名称
				</th>
				<th width="10%">
					所属游戏
				</th>
				<th width="8%">
					游戏编号
				</th>
				<th width="15%">
					状态
				</th>
				<th>
					操 作
				</th>
			</tr>
		
		<s:iterator value="areaList">
			<tr onMouseOver="this.style.background='#d6e5ff'" onMouseOut="this.style.background='#ffffff'">
				<td>
					<s:property value="id" />
				</td>
				<td>
					<s:property value="areaName" />
				</td>
				<td>
					<s:property value="game.gameName" />
				</td>
				<td>
					<s:property value="game.id" />
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
					<input type="button" value="编辑" onClick="areaEdit(${id},${page.currentPage},'${areaName }');" />
					<input type="button" value="服务器管理" onClick="serverList(${id});" />
				</td>
			</tr>
		</s:iterator>
		</table>
		
		<div class="turnpage">
			<s:if test="page.maxPage>1">
			
				<s:url id="first" action="areaAdmin.shtml">
					<s:param name="goPage" value="1"/>
				</s:url>
				<s:url id="last" action="areaAdmin.shtml">
					<s:param name="goPage" value="%{page.maxPage}"/>
				</s:url>
				<s:url id="next" action="areaAdmin.shtml">
					<s:param name="goPage" value="%{page.currentPage+1}"/>
				</s:url>
				<s:url id="pre" action="areaAdmin.shtml">
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
						<s:url id="go" action="areaAdmin.shtml">
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
