﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>九度网后台管理中心</title>
		<script type="text/javascript" src="${ctx}/js/tool.js"></script>
		<script type="text/javascript">
			function creditEdit(creditID) {
				location.href = "${ctx}/admin/credit/creditAdmin!add.shtml?creditID=" + creditID;
			}
			
			function creditDel(creditID){
				location.href="${ctx}/admin/credit/creditAdmin!delete.shtml?creditID=" + creditID;
			
			}
		</script>
	</head>

	<body>
		<div class="main_top_title">
			<dl id="manage_top">
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>积分列表
				</dt>
				<dd class="p1" onclick=CreateFolder();>
					<a href="${ctx}/admin/credit/creditAdmin!add.shtml">添加单个积分</a>
				</dd>
				<dd class="p1" onclick=CreateFolder();>
					<a href="${ctx}/admin/credit/creditAdmin!batchAdd.shtml">批量添加积分</a>
				</dd>
				<dd class="p9">
					<a href="#" onclick="history.back();">回上一级</a>
				</dd>
			</dl>
		</div>
		<div class="blank0"></div>
		<table width="46%" border="0" cellspacing="0" cellpadding="0" class="listtable" style="float: left;margin-left: 20px;" >
			<tr>
				<td colspan="4" align="center">
					<h3>卖家积分等级</h3>
				</td>
			</tr>
			<tr>
				<th width="10%">
					积分等级
				</th>
				<th width="30%">
					下限值
				</th>
				<th width="30%">
					上限值
				</th>
				<th>
					操 作
				</th>
			</tr>
		
		<s:iterator value="creditList">
			<s:if test="type==1">
				<tr onMouseOver="this.style.background='#d6e5ff'" onMouseOut="this.style.background='#ffffff'">
					<td>
						<s:property value="grade" />
					</td>
					<td>
						<s:property value="lower" />
					</td>
					<td>
						<s:property value="upper" />
					</td>
					<td>
						<input type="button" value="编辑" onClick="creditEdit(${id});" />
						<input type="button" value="删除" onClick="creditDel(${id});" />
					</td>
				</tr>
			</s:if>
		</s:iterator>
		</table>
		
		<table width="46%" border="0" cellspacing="0" cellpadding="0" class="listtable" style="float: right;margin-right: 20px;">
			<tr>
				<td colspan="4" align="center">
					<h3>买家积分等级</h3>
				</td>
			</tr>
			<tr>
				<th width="10%">
					积分等级
				</th>
				<th width="30%">
					下限值
				</th>
				<th width="30%">
					上限值
				</th>
				<th>
					操 作
				</th>
			</tr>
		
		<s:iterator value="creditList">
			<s:if test="type==2">
				<tr onMouseOver="this.style.background='#d6e5ff'" onMouseOut="this.style.background='#ffffff'">
					<td>
						<s:property value="grade" />
					</td>
					<td>
						<s:property value="lower" />
					</td>
					<td>
						<s:property value="upper" />
					</td>
					<td>
						<input type="button" value="编辑" onClick="creditEdit(${id});" />
						<input type="button" value="删除" onClick="creditDel(${id});" />
					</td>
				</tr>
			</s:if>
		</s:iterator>
		</table>
		
		
	</body>
</html>