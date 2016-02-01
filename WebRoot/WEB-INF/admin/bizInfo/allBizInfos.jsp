<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
<div class="main_top_title">
	<dl id=manage_top>
		<dt class="manage_top_title">
			<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
			<strong>当前位置：</strong>挂卖信息列表
		</dt>
	</dl>
</div>
<div class="blank0"></div>
<form action="${ctx}/admin/bizInfo/bizInfo.shtml" method="post">
<table>
<tr>
	<td>
		排序方式：<s:select list="#{'':'----排序方式----','bizCreTime desc':'发布时间从近到远','bizCreTime':'发布时间从远到近','proportion':'价格从低到高','proportion desc':'价格从高到低'}" name="order_by" listValue="value" listKey="key" theme="simple"/>
	</td>
	<td>
		发布者ID：<input type="text" name="bizInfo.owner.id" value="${bizInfo.owner.id}"/>
	</td>
	<td>
		查询内容：<input type="text" name="searchContent" value="${searchContent}"/>
	</td>
	<td><input type="submit" value="搜 索" /></td>
</tr>
</table>
</form>
<hr color="red"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable">
	<tr>
		<th width="5%">商品编号</th>
		<th width="15%">标题</th>
		<th width="10%">交易类型</th>
		<th width="5%">库存量</th>
		<th width="10%">单价</th>
		<th width="10%">发布者</th>
		<th width="10%">是否上架</th>
		<th width="10%">添加时间</th>
		<th width="10%">比例</th>
		<th width="5%">单位</th>
		<th width="10%">交易方式</th>
		<th width="10%">&nbsp;</th>
	</tr>
	<s:if test="page.resultlist.size() < 1">
		<tr><td colspan="5">暂无商品信息...</td></tr>
	</s:if>
	<s:iterator value="page.resultlist">
		<tr onMouseOver="this.style.background='#d6e5ff';" onMouseOut="this.style.background='#ffffff';">
			<td>&nbsp;<s:property value="serial" /></td>
			<td><s:if test="title.length() > 10">
					<s:property value="title.substring(0,10)" />...
				</s:if><s:else>
					<s:property value="title" />&nbsp;
				</s:else></td>
			<td>
				<s:if test="buyType == 1">寄售</s:if>
				<s:elseif test="buyType == 2">担保</s:elseif>
				<s:else>错误类型</s:else>
			</td>
			<td><s:property value="stock" /></td>
			<td><s:property value="price" /></td>
			<td><s:property value="owner.username" /></td>
			<td>
				<s:if test="isBuy == 1"><font color="green">上架</font></s:if>
				<s:elseif test="isBuy == 0"><font color="gray">下架</font></s:elseif>
				<s:else><font color="red">错误</font></s:else>
			</td>
			<td><s:property value="bizCreTime" /></td>
			<td><s:property value="proportion" /></td>
			<td><s:property value="unit" />&nbsp;</td>
			<td>
				<s:if test="sellModel == 1">游戏中当面</s:if>
				<s:elseif test="sellModel == 2">邮寄</s:elseif>
				<s:elseif test="sellModel == 3">账号交易</s:elseif>
				<s:else>错误</s:else>
			</td>
			<td><input type="button" value="查看" onclick="location.href='${ctx}/admin/bizInfo/bizInfo!checkBizInfo.shtml?bizInfo.id=${id}'" /></td>
		</tr>
	</s:iterator>
</table>
<div class="manu">
	<jsp:include page="/WEB-INF/index/common/pagination.jsp"></jsp:include>
	<form name="pageForm" action="${ctx}/admin/bizInfo/bizInfo.shtml" method="get">
	<input type="hidden" id="goPage" name="goPage" />
	</form>
</div>
<script type="text/javascript" src="${ctx}/js/tool.js" charset="gbk"></script>
