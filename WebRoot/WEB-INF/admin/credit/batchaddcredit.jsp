﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>九度网后台管理中心</title>
		<script type="text/javascript">
			function checkForm(){
				var content=document.getElementById("content").value;
				if(content==null || content==""){
					return false;
				}
			}
		</script>
	</head>

	<body>
		<div class="main_top_title">
			<dl id=manage_top>
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>批量添加积分
				</dt>
			</dl>
		</div>
	
	<form name="creditFrom" action="${ctx}/admin/credit/creditAdmin!batchSave.shtml" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
			<tr>
				<th width="19%">
					积分类别:
				</th>
				<td width="81%">
					<select size="1" name="type">
						<option value="1">
							卖家积分
						</option>
						<option value="2">
							买家积分
						</option>
					</select>
				</td>
			</tr>
			<tr>
				<th width="19%">
					信息内容:
				</th>
				<td width="81%">
					<textarea id="content" name="content" rows="15" cols="90"></textarea>
				</td>
			</tr>
			<tr>
				<th width="19%">
					操作:
				</th>
				<td width="81%">
					<input type="submit" name="method.save" value="保存" onClick="return checkForm();" />
					<input type="button" name="method.list" value="取消" onclick="history.back();" />
				</td>
			</tr>
			<tr>
				<th width="19%">
					举例:
				</th>
				<td width="81%">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>等级</td>
							<td>上限</td>
							<td>下限</td>
						</tr>
						<tr>
							<td>1</td>
							<td>1</td>
							<td>4</td>
						</tr>
						<tr>
							<td>2</td>
							<td>5</td>
							<td>20</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>	
	
	</body>
</html>
