<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
<div class="main_top_title">
	<dl id=manage_top>
		<dt class="manage_top_title">
			<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
			<strong>当前位置：</strong>投诉列表信息
		</dt>
	</dl>
</div>
<div class="blank0"></div>
<form action="${ctx}/admin/complaint/complaint.shtml" method="post">
<table>
<tr>
	<td>
		投诉用户ID：<input type="text" name="complaint.user.id" value="${complaint.user.id}"/>
	</td>
	<td>
		被投诉用户ID：<input type="text" name="complaint.defendant.id" value="${complaint.defendant.id}"/>
	</td>
	<td>
		状态：<s:select list="#{'':'----投诉状态----','0':'未处理','1':'已处理','2':'处理中'}" name="complaint.state" listValue="value" listKey="key" theme="simple"/>
	</td>
	<td>
		类型：<s:select list="#{'':'----投诉类型----','1':'卖家投诉','2':'买家投诉'}" name="complaint.type" listValue="value" listKey="key" theme="simple"/>
	</td>
	<td><input type="submit" value="搜 索" /></td>
</tr>
</table>
</form>
<hr color="red"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable">
	<tr>
		<th width="5%">投诉订单号</th>
		<th width="10%">投诉用户</th>
		<th width="10%">被投诉用户</th>
		<th width="15%">投诉内容</th>
		<th width="10%">响应内容</th>
		<th width="10%">类型</th>
		<th width="10%">状态</th>
		<th width="10%">投诉时间</th>
		<th width="10%">处理时间</th>
		<th width="10%">处理客服</th>
		<th width="10%">&nbsp;</th>
	</tr>
	<s:if test="page.resultlist.size() < 1">
		<tr><td colspan="11">暂无投诉信息...嘿嘿.人品超好！！！</td></tr>
	</s:if>
	<s:iterator value="page.resultlist">
		<tr onMouseOver="this.style.background='#d6e5ff';" onMouseOut="this.style.background='#ffffff';">
			<td>&nbsp;<s:property value="order.orderNum" /></td>
			<td>&nbsp;<s:property value="user.username" /></td>
			<td>&nbsp;<s:property value="defendant.username" /></td>
			<td><s:if test="content.length() > 15">
					<s:property value="content.substring(0,15)" />...
				</s:if><s:else>
					<s:property value="content" />&nbsp;
				</s:else>
			</td>
			<td><s:if test="answer.length() > 8">
					<s:property value="answer.substring(0,8)" />...
				</s:if><s:else>
					<s:property value="answer" />&nbsp;
				</s:else>
			</td>
			<td>
				<s:if test="type == 1">卖家投诉</s:if>
				<s:elseif test="type == 2">买家投诉</s:elseif>
				<s:else>错误类型</s:else>
			</td>
			<td>
				<s:if test="state == 0">未处理</s:if>
				<s:elseif test="state == 1">已经处理</s:elseif>
				<s:elseif test="state == 2">处理中</s:elseif>
				<s:else>错误状态</s:else>
			</td>
			<td><s:property value="applyTime" /></td>
			<td><s:property value="processTime" /></td>
			<td><s:property value="manageName" />&nbsp;</td>
			<td><input type="button" value="查看" onclick="location.href='${ctx}/admin/complaint/complaint!checkComplaint.shtml?complaint.id=${id}'" /></td>
		</tr>
	</s:iterator>
</table>
<div class="manu">
	<jsp:include page="/WEB-INF/index/common/pagination.jsp"></jsp:include>
	<form name="pageForm" action="${ctx}/admin/complaint/complaint.shtml" method="get">
	<input type="hidden" id="goPage" name="goPage" />
	<input type="hidden" name="complaint.user.id" value="${complaint.user.id}"/>
	<input type="hidden" name="complaint.defendant.id" value="${complaint.defendant.id}"/>
	<input type="hidden" name="complaint.state" value="${complaint.state}"/>
	<input type="hidden" name="complaint.type" value="${complaint.type}"/>
	</form>
</div>
<script type="text/javascript" src="${ctx}/js/tool.js" charset="gbk"></script>
