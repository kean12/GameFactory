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
				var grade=$("grade").value.Trim();
				var lower=$("lower").value.Trim();
				var upper=$("upper").value.Trim();
				if(isNaN(grade)){
					alert("请输入一个数字");
					$("grade").select();
					return false;
				}
				if(isNaN(lower)){
					alert("请输入一个数字");
					$("lower").select();
					return false;
				}
				if(isNaN(upper)){
					alert("请输入一个数字");
					$("upper").select();
					return false;
				}	
				$("grade").value=grade;
				$("lower").value=lower;
				$("upper").value=upper;
			}
		</script>
	</head>

	<body>
		<div class="main_top_title">
			<dl id=manage_top>
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>添加单个积分
				</dt>
			</dl>
		</div>
	
	<form name="afficheFrom" action="${ctx}/admin/credit/creditAdmin!save.shtml" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
			<tr>
				<th width="19%">
					类别:
				</th>
				<td width="81%">
					<s:select list="#{1:'卖家积分',2:'买家积分'}" id="type" name="credit.type" listValue="value" listKey="key" theme="simple"   />
				</td>
			</tr>
			<tr>
				<th width="19%">
					等级:
				</th>
				<td width="81%">
					<input id="grade" name="credit.grade" value="${credit.grade }"  class="upfile" size="80" />
					<span class="red">*</span>
				</td>
			</tr>
			<tr>
				<th width="19%">
					下限:
				</th>
				<td width="81%">
					<input id="lower" name="credit.lower" value="${credit.lower }"  class="upfile" size="80" />
					<span class="red">*</span>
				</td>
			</tr>
			<tr>
				<th width="19%">
					上限:
				</th>
				<td width="81%">
					<input id="upper" name="credit.upper" value="${credit.upper }"  class="upfile" size="80" />
					<span class="red">*</span>
				</td>
			</tr>
			<tr>
				<th width="19%">
					操作:
				</th>
				<td width="81%">
					<input type="hidden" name="credit.id" value="${credit.id}">
					<input type="submit" name="method.save" value="保存" onClick="return checkForm();" />
					<input type="button" name="method.list" value="取消" onclick="history.back();" />
				</td>
			</tr>
		</table>
	</form>	
	
	</body>
</html>
