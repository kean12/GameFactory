﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>九度网后台管理中心</title>
		<script type="text/javascript" src="${ctx}/js/tool.js"></script>
		<script type="text/javascript">
			function checkForm(){
				var kindName=$("kindName").value.Trim();
				var orderIndex=$("orderIndex").value.Trim();
				var unit=$("unit").value.Trim();
				var tradeTime=$("tradeTime").value.Trim();
				if(!kindName.match(/^\S+$/)){
					alert("种类名称必须填写！");
					$("kindName").select();
					return false;
				}else{
					$("kindName").value=kindName;
				}
				if(!orderIndex.match(/^[0-9]*[1-9][0-9]*$/)){
					alert("请填写排列顺序");
					$("orderIndex").select();
					return false;
				}else{
					$("orderIndex").value=orderIndex;
				}
				if(!unit.match(/^\S+$/)){
					alert("请填写默认单位");
					$("unit").select();
					return false;
				}else{
					$("unit").value=unit;
				}
				if(!tradeTime.match(/^[0-9]*[1-9][0-9]*$/)){
					alert("请填写交易时间");
					$("tradeTime").select();
					return false;
				}else{
					$("tradeTime").value=tradeTime;
				}
			}
		</script>
	</head>

	<body>
		<div class="main_top_title">
			<dl id=manage_top>
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>添加挂卖种类
				</dt>
			</dl>
		</div>
	
	<form name="bizKindFrom" action="${ctx}/admin/bizKind/bizKindAdmin!save.shtml" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
			<tr>
				<th width="19%">
					种类名称:
				</th>
				<td width="81%">
					<input id="kindName" name="bizKind.kindName" value="${bizKind.kindName }"  class="upfile" size="80" />
					<span class="red">*</span>
				</td>
			</tr>
			<tr>
				<th width="19%">
					排列顺序:
				</th>
				<td width="81%">
					<input id="orderIndex" name="bizKind.orderIndex" value="${bizKind.orderIndex }"  class="upfile" size="80" />
					<span class="red">*</span>
				</td>
			</tr>
			<tr>
				<th width="19%">
					是否默认:
				</th>
				<td width="81%">
					<s:select list="#{0:'否',1:'是'}" name="bizKind.toleration" listValue="value" listKey="key" theme="simple"   />
					<span class="red">*</span>
				</td>
			</tr>
			<tr>
				<th width="19%">
					默认单位:
				</th>
				<td width="81%">
					<input id="unit" name="bizKind.unit" value="${bizKind.unit }"  class="upfile" size="80" />
				</td>
			</tr>
			<tr>
				<th width="19%">
					交易时间(分钟):
				</th>
				<td width="81%">
					<input id="tradeTime" name="bizKind.tradeTime" value="${bizKind.tradeTime }"  class="upfile" size="80" />
				</td>
			</tr>
			<tr>
				<th width="19%">
					默认挂卖方式:
				</th>
				<td width="81%">
					<s:select list="#{0:'普通方式',1:'账号交易',2:'自定义属性'}" name="bizKind.tradeType" listValue="value" listKey="key" theme="simple"   />
				</td>
			</tr>
			<tr>
				<th width="19%">
					备注:
				</th>
				<td width="81%">
					<input id="kindName" name="bizKind.remark" value="${bizKind.remark }"  class="upfile" size="80" />
					<span class="red">*</span>
				</td>
			</tr>
			<tr>
				<th width="19%">
					是否启用:
				</th>
				<td width="81%">
					<s:select list="#{1:'启动',0:'禁用'}" name="bizKind.isUse" listValue="value" listKey="key" theme="simple"   />
				</td>
			</tr>
			
			<tr>
				<th width="19%">
					操作:
				</th>
				<td width="81%">
					<input type="hidden" name="bizKind.id" value="${bizKind.id}">
					<input type="submit" name="method.save" value="保存" onclick="return checkForm();" />
					<input type="button" name="method.list" value="取消" onclick="history.back();" />
				</td>
			</tr>
		</table>
	</form>
	
	</body>
</html>
