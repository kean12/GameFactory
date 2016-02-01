<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<body>
	<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
	<table style="width:100%;height:100%; background:#999"><tr>
	<td align="center" valign="middle">
		<div style="width:700px; background:#666; margin:0 auto; position:relative">
		<div class="wrong" style="text-align:left; border:#666 1px dotted; position:relative; top:-10px; left:-8px">
			<span class="red">${exception.message}</span>
			<br />
			您可以：
			<br />
			1.检查刚才的输入
			<br />
			2.去其它地方逛逛
			<a onclick="window.location='${ctx}/index.shtml'" style="cursor: pointer;" class="blue">首页</a> |
			<a onclick="window.location='${ctx}/user/home.shtml'" style="cursor: pointer;" class="blue">用户中心</a> |
			<a href="javascript:void(0)" onclick="javascript:history.back();" class="blue">返回</a>
		</div></div>
	</td></tr></table>
</body>
</html>
