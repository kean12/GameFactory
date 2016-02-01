﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>九度网后台管理中心</title>
		
		<script type="text/javascript" src="${ctx}/js/tool.js"></script>
		<script type="text/javascript">
			function gameEdit(gameID,goPage,gameName) {
				location.href = "${ctx}/admin/game/gameAdmin!add.shtml?gameID=" + gameID + "&goPage=" + goPage + "&gameName="+gameName;
			}
		
			function gameYxqfList(gameID) {
				location.href = "${ctx}/admin/area/areaAdmin.shtml?gameID=" +gameID;
			}
		</script>
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
			
			<dl id="manage_top">
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>游戏列表
				</dt>
				<dd class="p1" onclick=CreateFolder();>
					<a href="${ctx}/admin/game/gameAdmin!add.shtml">添加游戏</a>
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
					<input type="button" value="编辑" onClick="gameEdit(${id},${page.currentPage},'${gameName }');" />
					<input type="button" value="游戏区服管理" onClick="gameYxqfList(${id});" />
				</td>
			</tr>
		</s:iterator>
		</table>
		
		<div class="turnpage">
			<s:if test="page.maxPage>1">
			
				<s:url id="first" action="gameAdmin.shtml">
					<s:param name="goPage" value="1"/>
				</s:url>
				<s:url id="last" action="gameAdmin.shtml">
					<s:param name="goPage" value="%{page.maxPage}"/>
				</s:url>
				<s:url id="next" action="gameAdmin.shtml">
					<s:param name="goPage" value="%{page.currentPage+1}"/>
				</s:url>
				<s:url id="pre" action="gameAdmin.shtml">
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
						<s:url id="go" action="gameAdmin.shtml">
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