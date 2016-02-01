<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>九度网后台管理中心</title>
	</head>
	<body>
		<div class="main_top_title">
			<dl id="manage_top">
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>查看编辑客户
				</dt>
				<dd class="p9"><a href="#" onclick="history.back();">回上一级</a></dd>
			</dl>
		</div>
		<font color="red" size="3">此操作可直接更改客户资料或密码，请慎用....</font>
		<form action="${ctx}/admin/user/user!saveUser.shtml" method="post">
		<input type="hidden" value="${user.password}" name="user.password">
		<input type="hidden" value="${user.userInfo.applyPwd}" name="user.userInfo.applyPwd">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
			<tr>
				<th width="15%">用户名:</th>
				<td width="35%">
					<INPUT class="upfile" name="user.username" size=30  value="${user.username}" readonly/>
				</td>
				<th width="19%">电话:</th>
				<td width="35%">
					<INPUT class="upfile" name="user.phoneNum" size=30 value="${user.phoneNum}"/>
				</td>
			</tr>
			<tr>
				<th width="10%">QQ:</th>
				<td width="40%">
					<INPUT class="upfile" name="user.qq" size=30 value="${user.qq}" />
				</td>
				<th width="10%">真实姓名:</th>
				<td width="40%">
					<INPUT class="upfile" name="user.realName" size=30 value="${user.realName}"/>
				</td>
			</tr>
			<tr>
				<th width="10%">注册时间:</th>
				<td width="40%">
					<INPUT class="upfile" name="user.regTime" size=30 value="${user.regTime}" />
				</td>
				<th width="10%">电子邮箱:</th>
				<td width="40%">
					<INPUT class="upfile" name="user.email" size=30 value="${user.email}"/>
				</td>
			</tr>
			<tr>
				<th width="10%">邮编:</th>
				<td width="40%">
					<INPUT class="upfile" name="user.postCode" size=30 value="${user.postCode}" />
				</td>
				<th width="10%">身份证:</th>
				<td width="40%">
					<INPUT class="upfile" name="user.identity" size=30 value="${user.identity}"/>
				</td>
			</tr>
			<tr>
				<th width="10%">密码提示问题:</th>
				<td width="40%">
					<INPUT class="upfile" name="user.hint" size=30 value="${user.hint}" />
				</td>
				<th width="10%">提示问题答案:</th>
				<td width="40%">
					<INPUT class="upfile" name="user.answer" size=30 value="${user.answer}" />
				</td>
			</tr>
			<tr>
				<th width="10%">账户是否锁定:</th>
				<td width="40%">
				<s:select list="#{'Y':'锁定','N':'不锁定'}"  name="user.lock"  listValue="value" listKey="key" theme="simple"   />
				</td>
				<th width="10%">账号是否启用:</th>
				<td width="40%">
					<s:select list="#{1:'启用',0:'禁用'}"  name="user.isUse"  listValue="value" listKey="key" theme="simple"   />
				</td>
			</tr>
			<tr>
				<th width="10%">收件地址/详细地址:</th>
				<td width="40%">
					<INPUT class="upfile" name="user.postAddr" size=30 value="${user.postAddr}" />
				</td>
				<th width="10%">用户ID:</th>
				<td width="40%">
					<INPUT class="upfile" name="user.id" readonly value="${user.id}" />
				</td>
				
			</tr>
		</table>
		<hr color="red"/>
		-------用户详细信息---------
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
			<tr>
				<th width="10%">账户可用余额:</th>
				<td width="40%">
					<INPUT class="upfile" name="user.userInfo.money" size=30 value="${user.userInfo.money}" />
				</td>
				<th width="10%">冻结金额:</th>
				<td width="40%">
					<INPUT class="upfile" name="user.userInfo.freemoney" size=30 value="${user.userInfo.freemoney}" />
				</td>
			</tr>
			<tr>
				<th width="10%">买家积分:</th>
				<td width="40%">
					<INPUT class="upfile" name="user.userInfo.buyerCredit" size=30 value="${user.userInfo.buyerCredit}" />
				</td>
				<th width="10%">买家信誉等级:</th>
				<td width="40%">
					<INPUT class="upfile" name="user.userInfo.buyerGrade" size=30 value="${user.userInfo.buyerGrade}" />
				</td>
			</tr>
			<tr>
				<th width="10%">卖家积分:</th>
				<td width="40%">
					<INPUT class="upfile" name="user.userInfo.sellerCredit" size=30 value="${user.userInfo.sellerCredit}" />
				</td>
				<th width="10%">卖家信誉等级:</th>
				<td width="40%">
					<INPUT class="upfile" name="user.userInfo.sellerGrade" size=30 value="${user.userInfo.sellerGrade}" />
				</td>
			</tr>
			<tr>
				<th width="10%">登陆地址:</th>
				<td width="40%">
					<INPUT class="upfile" name="user.userInfo.ip" size=30 value="${user.userInfo.ip}" />
				</td>
				<th width="10%">登陆时间:</th>
				<td width="40%">
					<INPUT class="upfile" name="user.userInfo.loginTime" size=30 value="${user.userInfo.loginTime}" />
				</td>
			</tr>
			<tr>
				<th width="10%">上次登陆地址:</th>
				<td width="40%">
					<INPUT class="upfile" name="user.userInfo.tmpIp" size=30 value="${user.userInfo.tmpIp}" />
				</td>
				<th width="10%">上次登陆时间:</th>
				<td width="40%">
					<INPUT class="upfile" name="user.userInfo.tmpTime" size=30 value="${user.userInfo.tmpTime}" />
				</td>
			</tr>
			<tr>
				<th width="10%">卖家好评率:</th>
				<td width="40%">
					<INPUT class="upfile" name="user.userInfo.sellerPositiveRatio" size=30 value="${user.userInfo.sellerPositiveRatio}" />
				</td>
				<th width="10%">买家好评率:</th>
				<td width="40%">
					<INPUT class="upfile" name="user.userInfo.buyerPositiveRatio" size=30 value="${user.userInfo.buyerPositiveRatio}" />
				</td>
			</tr>
		</table>
		<hr color="red"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
		<tr>
			<th width="10%">用户登录密码:</th>
			<td width="40%">
				<INPUT class="upfile" type="password" name="updatePwd" size=30  />
			</td>
			<th width="10%">用户支付密码:</th>
			<td width="40%">
				<INPUT class="upfile" type="password" name="updateApplyPwd" size=30  />
			</td>
		</tr>
		</table>
		<hr color="red"/>
		<input type="submit" value="保 存">
		</form>
	</body>
</html>