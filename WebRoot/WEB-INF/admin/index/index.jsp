﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>九度网后台管理中心</title>
	</head>
	<body scroll="no">
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="5" height="73">
					<iframe id="top" name="top" src="${ctx}/admin/manage!top.shtml" scrolling="no" height="73" width="100%" frameborder="0"></iframe>
				</td>
			</tr>
			<tr>
				<td class="main_ml"></td>
				<td width="187" id="leftside" style="display: ;">
					<iframe id="left" name="left" src="${ctx}/admin/manage!left.shtml"  height="100%" width="187" style="overflow-x:hidden;overflow-y:auto" frameborder="0"></iframe>
				</td>
				<td class="main_mm" onClick="turn();">
					<img id="img1" src="${ctx}/css/admin/images/line_bu.gif" width="5" height="10">
				</td>
				<td>
					<iframe id="MainFrame" name="MainFrame" src="${ctx}/admin/manage!main.shtml" scrolling="yes" height="100%" width="100%" frameborder="0"></iframe>
				</td>
				<td class="main_mr"></td>
			</tr>
			<tr>
				<td colspan="5" id="foot"></td>
			</tr>
		</table>
		<script language="javascript" type="text/javascript">
			function turn() {
				var a = document.getElementById('leftside').style.display;
				var b = document.getElementById('img1').src;
				if (a == "") {
					a = "none";
					b = "${ctx}/css/admin/images/line_bu1.gif";
				} else {
					a = "";
					b = "${ctx}/css/admin/images/line_bu.gif";
				}
				document.getElementById('leftside').style.display = a;
				document.getElementById('img1').src = b;
			}
		</script>
	</body>
</html>