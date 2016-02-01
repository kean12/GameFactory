<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>菜单管理</title>
	</head>
	<body>
		<div class="main_top_title">
			<dl id=manage_top>
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>客户用户 >> 买家信誉排行查看
					<dd class="p9"><a href="#" onclick="location.href='${ctx}/admin/user/user!orderBySeller.shtml';">查看卖家信誉</a></dd>
				</dt>
			</dl>
		</div>
		<div class="blank0"></div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable">
			<tr>
				<th width="10%">用户ID</th>
				<th width="10%">用户登录名</th>
				<th width="10%">电话</th>
				<th width="10%">电子邮箱</th>
				<th width="10%">买家积分</th>
				<th width="10%">买家信誉等级</th>
				<th width="10%">卖家积分</th>
				<th width="10%">卖家信誉等级</th>
				<th width="12%">账户是否锁定</th>
				<th width="10%">账号是否启用</th>
				<th width="10%">&nbsp;</th>
			</tr>
			
			<s:iterator value="page.resultlist">
				<tr onMouseOver="this.style.background='#d6e5ff';" onMouseOut="this.style.background='#ffffff';">
					<td><s:property value="id" /></td>
					<td><s:property value="username" /></td>
					<td><s:property value="phoneNum" />&nbsp;</td>
					<td><s:property value="email" />&nbsp;</td>
					<td><s:property value="userInfo.buyerCredit" />&nbsp;</td>
					<td><s:property value="userInfo.buyerGrade" />&nbsp;</td>
					<td><s:property value="userInfo.sellerCredit" />&nbsp;</td>
					<td><s:property value="userInfo.sellerGrade" />&nbsp;</td>
					<td>
						<s:if test='lock == "Y"'>锁定</s:if>
						<s:elseif test='lock == "N"'>不锁定</s:elseif>
						<s:else><font color="red" size="2">异常状态</font></s:else>
					</td>
					<td>
						<s:if test="isUse == 0">禁用</s:if><s:elseif test="isUse == 1">启用</s:elseif>
						<s:else><font color="red" size="2">异常状态</font></s:else>
					</td>
					<td>
						<input type="button" value="查看" onclick="location.href='${ctx}/admin/user/user!checkUser.shtml?id=${id}'" />
					</td>
				</tr>
			</s:iterator>
		</table>
		<div class="manu">
			<jsp:include page="/WEB-INF/index/common/pagination.jsp"></jsp:include>
			<form name="pageForm" action="${ctx}/admin/user/user!orderBySeller.shtml" method="get">
				<input type="hidden" id="goPage" name="goPage" />
			</form>
		</div>
		<script type="text/javascript" src="${ctx}/js/tool.js" charset="gbk"></script>
	</body>
</html>
