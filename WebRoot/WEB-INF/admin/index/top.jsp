﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>九度网后台管理中心</title>
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div id="top">
			<div class="logo"></div>
			<div class="user">
				<form name="form1" method="post" action="">
					<label>
						欢迎你，<s:property value="manageSession.username"/>&nbsp;&nbsp;
						<s:property value="manageSession.name"/>
					</label>
				</form>
			</div>
			<div class="top_nav">
				<ul>
					<li class="nav4" onClick="parent.window.location.href = '${ctx}/admin/logout.shtml'"></li>
					<li class="nav3" onClick="parent.window.MainFrame.location='${ctx}/admin/manage/manage!updatePassword.shtml'"></li>
					<li class="nav2" onClick="window.open('${ctx}/index.shtml');"></li>
					<li class="nav1" onClick="parent.window.location = '${ctx}/admin/manage.shtml';"></li>
				</ul>
			</div>
		</div>
	</body>
</html>