<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">var AP="${ctx}";</script>
<script type="text/javascript" src="${ctx}/js/tool.js"></script>
<div class="main_top_title">
	<dl id=manage_top>
		<dt class="manage_top_title">
			<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
			<strong>当前位置：</strong>帐户明细列表信息
		</dt>
	</dl>
</div>
<div class="blank0"></div>
<form action="${ctx}/admin/particulars/particulars.shtml" method="post">
<table id="orderList">
	<tr>
		<td>
			用户ID：<input type="text" name="particulars.user.id" value="${particulars.user.id}"/>
		</td>
		<td>
			商户订单号：<input type="text" name="particulars.orderNum" value="${particulars.orderNum}"/>
		</td>
		<td>
			业务流水号：<input type="text" name="particulars.runningNum" value="${particulars.runningNum}"/>
		</td>
		<td>
			类型：<s:select list="#{'':'----明细类型----','1':'交易付款','2':'交易收款','3':'在线充值','4':'退款','5':'提现'}" name="particulars.type" listValue="value" listKey="key" theme="simple"/>
		</td>
	</tr>
	<tr class="sBox">
		<td colspan="2">交易时间：从<input class="ipt-date to" id="beginTime" name="beginTime" value="${beginTime}" readonly/>
			<input class="ipt-date to" id="endTime" name="endTime" value="${endTime}" readonly/>
		</td>
		<td>交易场所：<input type="text" name="particulars.bank" value="${particulars.bank}"/></td>
		<td><input type="submit" value="搜 索" /></td>
	</tr>
</table>
</form>
<hr color="red"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable">
	<tr>
		<th width="5%">商户订单号</th>
		<th width="10%">业务流水号</th>
		<th width="10%">用户名称</th>
		<th width="15%">交易时间</th>
		<th width="10%">交易场所</th>
		<th width="10%">交易类型</th>
		<th width="10%">收入</th>
		<th width="10%">支出</th>
		<th width="10%">账户总额</th>
		<th width="10%">备注</th>
		<th width="10%">&nbsp;</th>
	</tr>
	<s:if test="page.resultlist.size() < 1">
		<tr><td colspan="11">暂无明细信息！</td></tr>
	</s:if>
	<s:iterator value="page.resultlist">
		<tr onMouseOver="this.style.background='#d6e5ff';" onMouseOut="this.style.background='#ffffff';">
			<td>&nbsp;<s:property value="orderNum" /></td>
			<td>&nbsp;<s:property value="runningNum" /></td>
			<td>&nbsp;<s:property value="user.username" /></td>
			<td>&nbsp;<s:property value="time" /></td>
			<td>&nbsp;<s:property value="bank" /></td>
			<td>
				<s:if test="type == 1">交易付款</s:if>
				<s:elseif test="type == 2">交易收款</s:elseif>
				<s:elseif test="type == 3">在线充值</s:elseif>
				<s:elseif test="type == 4">退款</s:elseif>
				<s:elseif test="type == 5">提现</s:elseif>
				<s:else>错误类型</s:else>
			</td>
			<td>&nbsp;<s:property value="income" /></td>
			<td>&nbsp;<s:property value="expense" /></td>
			<td>&nbsp;<s:property value="money" /></td>
			<td>&nbsp;<s:property value="remark" /></td>

			<td><input type="button" value="查看" onclick="location.href='${ctx}/admin/particulars/particulars!checkParticulars.shtml?particulars.id=${id}'" /></td>
		</tr>
	</s:iterator>
</table>
<div class="manu">
	<jsp:include page="/WEB-INF/index/common/pagination.jsp"></jsp:include>
	<form name="pageForm" action="${ctx}/admin/particulars/particulars.shtml" method="get">
	<input type="hidden" id="goPage" name="goPage" />
	<input type="hidden" name="particulars.user.id" value="${particulars.user.id}"/>
	<input type="hidden" name="particulars.orderNum" value="${particulars.orderNum}"/>
	<input type="hidden" name="particulars.runningNum" value="${particulars.runningNum}"/>
	<input type="hidden" name="particulars.type" value="${particulars.type}"/>
	<input type="hidden" name="beginTime" value="${beginTime}"/>
	<input type="hidden" name="endTime" value="${endTime}"/>
	<input type="hidden" name="particulars.bank" value="${particulars.bank}"/>
	</form>
</div>
