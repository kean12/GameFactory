﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>九度网后台管理中心</title>
		
		<script type="text/javascript" src="${ctx}/js/tool.js"></script>
		<script type="text/javascript">
			function afficheEdit(afficheID,goPage) {
				location.href = "${ctx}/admin/affiche/afficheAdmin!add.shtml?afficheID=" + afficheID + "&goPage=" + goPage;
			}
			
			function afficheDel(afficheID,goPage){
				location.href="${ctx}/admin/affiche/afficheAdmin!delete.shtml?afficheID=" + afficheID + "&goPage=" + goPage;
			
			}
		</script>
	</head>

	<body>
		<div class="main_top_title">
			<dl id="manage_top">
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>公告列表
				</dt>
				<dd class="p1" onclick=CreateFolder();>
					<a href="${ctx}/admin/affiche/afficheAdmin!add.shtml">添加公告</a>
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
					编号
				</th>
				<th width="15%">
					类别
				</th>
				<th width="30%">
					公告标题
				</th>
				<th width="15%">
					发布者
				</th>
				<th width="8%">
					公告状态
				</th>
				<th width="15%">
					最后修改时间
				</th>
				<th>
					操 作
				</th>
			</tr>
		
		<s:iterator value="afficheList">
			<tr onMouseOver="this.style.background='#d6e5ff'" onMouseOut="this.style.background='#ffffff'">
				<td>
					<s:property value="id" />
				</td>
				<td>
					<s:if test="type==3">
						官方新闻
					</s:if>
					<s:elseif test="type==2">
						最新动态
					</s:elseif>
					<s:else>
						网站公告
					</s:else>
				</td>
				<td>
					<s:property value="title" />
				</td>
				<td>
					<s:property value="manage.name" />
				</td>
				<td>
					<s:if test="state==1">
						显示
					</s:if>
					<s:else>
						<span class="red">不显示</span>
					</s:else>
				</td>
				<td>
					<s:property value="time" />
				</td>
				<td>
					<input type="button" value="编辑" onClick="afficheEdit(${id},${page.currentPage});" />
					<input type="button" value="删除" onClick="afficheDel(${id},${page.currentPage});" />
				</td>
			</tr>
		</s:iterator>
		</table>
		
		<div class="turnpage">
			<s:if test="page.maxPage>1">
			
				<s:url id="first" action="afficheAdmin.shtml">
					<s:param name="goPage" value="1"/>
				</s:url>
				<s:url id="last" action="afficheAdmin.shtml">
					<s:param name="goPage" value="%{page.maxPage}"/>
				</s:url>
				<s:url id="next" action="afficheAdmin.shtml">
					<s:param name="goPage" value="%{page.currentPage+1}"/>
				</s:url>
				<s:url id="pre" action="afficheAdmin.shtml">
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
						<s:url id="go" action="afficheAdmin.shtml">
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