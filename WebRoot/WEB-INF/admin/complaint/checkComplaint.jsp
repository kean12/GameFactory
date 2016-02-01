<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
<div class="main_top_title">
	<dl id="manage_top">
		<dt class="manage_top_title">
			<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
			<strong>当前位置：</strong>投诉列表>>查看投诉信息
		</dt>
		<dd class="p9"><a href="#" onclick="history.back();">回上一级</a></dd>
	</dl>
</div>
<form action="${ctx}/admin/complaint/complaint!saveComplaint.shtml" method="post">
<input type="hidden" name="complaint.user.id" value="${complaint.user.id}"/>
<input type="hidden" name="complaint.id" value="${complaint.id}"/>
<input type="hidden" name="complaint.defendant.id" value="${complaint.defendant.id}"/>
<input type="hidden" name="complaint.order.id" value="${complaint.order.id}"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
	<tr>
		<th width="10%">投诉类型:</th>
		<td width="40%">
		<s:select list="#{1:'卖家投诉',2:'买家投诉'}"  name="complaint.type"  listValue="value" listKey="key" theme="simple"   />
		</td>
		<th width="10%">投诉状态:</th>
		<td width="40%">
		<s:select list="#{0:'未处理',1:'已处理',2:'处理中'}"  name="complaint.state"  listValue="value" listKey="key" theme="simple"   />
		</td>
	</tr>
	<tr>
		<th width="10%">投诉时间:</th>
		<td width="40%">
			<input type="text" name="complaint.applyTime" value="${complaint.applyTime}" readonly />
		</td>
		<th width="10%">处理时间:</th>
		<td width="40%">
			<input type="text" name="complaint.processTime" value="${complaint.processTime}" readonly />
		</td>
	</tr>
	<tr>
		<th width="10%">处理客服名称:</th>
		<td width="40%">
			<input type="text" name="complaint.manageName" value="${complaint.manageName}" readonly />
		</td>
		<th width="10%">订单编号:</th>
		<td width="40%">
			<input type="text" name="complaint.order.orderNum" value="${complaint.order.orderNum}" disabled="disabled" />
			<button onclick="window.open('${ctx}/admin/order/order!checkOrder.shtml?order.id=${complaint.order.id}');">查看订单信息</button>
		</td>
	</tr>
	<tr>
		<th width="10%">投诉者:</th>
		<td width="40%">
			<input type="text" name="complaint.user.username" value="${complaint.user.username}" disabled="disabled" />
			<button onclick="window.open('${ctx}/admin/user/user!checkUser.shtml?id=${complaint.user.id}');">查看投诉者信息</button>
		</td>
		<th width="10%">购买者:</th>
		<td width="40%">
			<input type="text" name="complaint.defendant.username" value="${complaint.defendant.username}" disabled="disabled" />
			<button onclick="window.open('${ctx}/admin/user/user!checkUser.shtml?id=${complaint.defendant.id}');">查看被投诉者信息</button>
		</td>
	</tr>
	<tr>
		<th width="10%">投诉内容:</th>
		<td width="40%">
			<FCK:editor instanceName="content" toolbarSet="Basic" inputName="complaint.content" width="100%" height="300">
				<jsp:attribute name="value">
					${complaint.content}
				</jsp:attribute>
				<jsp:body>
				<FCK:config SkinPath="skins/office2003/"/>
				</jsp:body>
			</FCK:editor>
		</td>
		<th width="10%">响应内容:</th>
		<td width="40%">
			<FCK:editor instanceName="answer" toolbarSet="Basic" inputName="complaint.answer" width="100%" height="300">
				<jsp:attribute name="value">
					${complaint.answer}
				</jsp:attribute>
				<jsp:body>
				<FCK:config SkinPath="skins/office2003/"/>
				</jsp:body>
			</FCK:editor>
		</td>
	</tr>
	<tr>
		<th width="10%">操作:</th>
		<td width="80%" colspan="3">
		<input type="submit" value="保 存"/>&nbsp;<input type="button" onclick="history.back();" value="取 消"/>
		<font color="red" size="2">${errorMessage}</font>
		</td> 
	</tr>
</table>
</form>