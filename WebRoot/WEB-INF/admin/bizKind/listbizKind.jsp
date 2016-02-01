﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>九度网后台管理中心</title>
		<script type="text/javascript" src="${ctx}/js/tool.js"></script>
		<script type="text/javascript">
			function bizKindEdit(bizKindID) {
				location.href = "${ctx}/admin/bizKind/bizKindAdmin!add.shtml?bizKindID=" + bizKindID;
			}
			
			function bizKindDel(bizKindID){
				if(confirm("建议更改状态为禁用，\n\r删除此数据可能导致系统出现异常！")){
					location.href="${ctx}/admin/bizKind/bizKindAdmin!delete.shtml?bizKindID=" + bizKindID;
				}
				return false;
			}
		</script>
	</head>

	<body>
		<div class="main_top_title">
			<dl id="manage_top">
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>挂卖种类列表
				</dt>
				<dd class="p1" onclick=CreateFolder();>
					<a href="${ctx}/admin/bizKind/bizKindAdmin!add.shtml">新增种类</a>
				</dd>
				<dd class="p9">
					<a href="#" onclick="history.back();">回上一级</a>
				</dd>
				<dd>
					<span class="red">如将挂卖种类设置为默认，所有游戏将使用</span>
				</dd>
			</dl>
		</div>
		<div class="blank0"></div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable" >
			<tr>
				<th width="8%">
					编号
				</th>
				<th width="8%">
					种类名称
				</th>
				<th width="8%">
					排列顺序
				</th>
				<th width="8%">
					是否默认
				</th>
				<th width="8%">
					是否使用
				</th>
				<th width="8%">
					默认单位
				</th>
				<th width="8%">
					交易时间
				</th>
				<th width="8%">
					默认挂卖类型
				</th>
				<th width="30%">
					备注
				</th>
				<th>
					操 作
				</th>
			</tr>
		
		<s:iterator value="bizKindList">
			<tr onMouseOver="this.style.background='#d6e5ff'" onMouseOut="this.style.background='#ffffff'">
				<td>
					<s:property value="id" />&nbsp;
				</td>
				<td>
					<s:property value="kindName" />&nbsp;
				</td>
				<td>
					<s:property value="orderIndex" />&nbsp;
				</td>
				<td>
					<s:if test="toleration==1">
						<span class="red">是</span>
					</s:if>
					<s:else>
						否
					</s:else>&nbsp;
				</td>
				<td>
					<s:if test="isUse==1">
						启用
					</s:if>
					<s:else>
						<span class="red">禁用</span>
					</s:else>&nbsp;
				</td>
				<td>
					<s:property value="unit" />&nbsp;
				</td>
				<td>
					<s:property value="tradeTime" />&nbsp;
				</td>
				<td>
					<s:select list="#{0:'普通方式',1:'账号交易',2:'自定义属性'}" name="tradeType" listValue="value" listKey="key" theme="simple" disabled="true"  />
				</td>
				<td>
					<s:property value="remark" />&nbsp;
				</td>
				<td>
					<input type="button" value="编辑" onClick="bizKindEdit(${id});" />
					<%-- <input type="button" value="删除" onClick="bizKindDel(${id});" /> --%>
				</td>
			</tr>
		</s:iterator>
		</table>
		
	</body>
</html>