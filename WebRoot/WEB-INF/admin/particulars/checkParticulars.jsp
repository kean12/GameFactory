<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
<div class="main_top_title">
	<dl id="manage_top">
		<dt class="manage_top_title">
			<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
			<strong>当前位置：</strong>帐户明细列表信息>>查看明细信息
		</dt>
		<dd class="p9"><a href="#" onclick="history.back();">回上一级</a></dd>
	</dl>
</div>
<form action="${ctx}/admin/particulars/particulars!saveParticulars.shtml" method="post">
<input type="hidden" name="particulars.id" value="${particulars.id}"/>
<input type="hidden" name="particulars.user.id" value="${particulars.user.id}"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
	<tr>
		<th width="10%">业务流水号:</th>
		<td width="40%">
			<input type="text" name="particulars.runningNum" value="${particulars.runningNum}" readonly />
		</td>
		<th width="10%">商户订单号:</th>
		<td width="40%">
			<input type="text" name="particulars.orderNum" value="${particulars.orderNum}" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">交易时间:</th>
		<td width="40%">
			<input type="text" name="particulars.time" value="${particulars.time}" readonly />
		</td>
		<th width="10%">交易场所:</th>
		<td width="40%">
			<input type="text" name="particulars.bank" value="${particulars.bank}" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">收入:</th>
		<td width="40%">
			<input type="text" name="particulars.income" value="${particulars.income}" readonly />
		</td>
		<th width="10%">支出:</th>
		<td width="40%">
			<input type="text" name="particulars.expense" value="${particulars.expense}" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">交易类型:</th>
		<td width="40%">
		<s:select list="#{1:'交易付款',2:'交易收款',3:'在线充值',4:'退款',5:'提现'}"  name="particulars.type"  listValue="value" listKey="key" theme="simple"   />
		</td>
		<th width="10%">账户总额:</th>
		<td width="40%">
			<input type="text" name="particulars.money" value="${particulars.money}" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">交易详情:</th>
		<td width="40%">
			<FCK:editor instanceName="synopsis" toolbarSet="Basic" inputName="particulars.synopsis" width="100%" height="300">
				<jsp:attribute name="value">
					${particulars.synopsis}
				</jsp:attribute>
				<jsp:body>
				<FCK:config SkinPath="skins/office2003/"/>
				</jsp:body>
			</FCK:editor>
		</td>
		<th width="10%">备注:</th>
		<td width="40%">
			<FCK:editor instanceName="answer" toolbarSet="Basic" inputName="particulars.remark" width="100%" height="300">
				<jsp:attribute name="value">
					${particulars.remark}
				</jsp:attribute>
				<jsp:body>
				<FCK:config SkinPath="skins/office2003/"/>
				</jsp:body>
			</FCK:editor>
		</td>
	</tr>
	<tr>
		<th width="10%">用户名称:</th>
		<td width="40%">
			<input type="text" name="particulars.user.username" value="${particulars.user.username}" disabled="disabled" />
			<button onclick="window.open('${ctx}/admin/user/user!checkUser.shtml?id=${particulars.user.id}');">查看投诉者信息</button>
		</td>
		<th width="10%">操作:</th>
		<td width="40%">
		<input type="submit" value="保 存"/>&nbsp;<input type="button" onclick="history.back();" value="取 消"/>
		<font color="red" size="2">${errorMessage}</font>
		</td> 
	</tr>
</table>
</form>