<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
<div class="main_top_title">
	<dl id=manage_top>
		<dt class="manage_top_title">
			<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
			<strong>当前位置：</strong>日志列表
		</dt>
	</dl>
</div>
<div class="blank0"></div>
<form action="${ctx}/admin/log/log.shtml" method="post">
<table>
	<tr>
		<td>日志类型:
		<s:select list="#{-1:'--选择信息类型--',1:'信息',2:'警告',3:'错误',4:'灾难性异常'}"  name="log.type"  listValue="value" listKey="key" theme="simple"   />
		</td>
		<td><input type="submit" value="搜 索" /></td>
	</tr>
</table>
</form>
<hr color="red"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable">
	<tr>
		<th width="5%">ID</th>
		<th width="45%">日志内容</th>
		<th width="20%">备注</th>
		<th width="10%">时间</th>
		<th width="10%">类型</th>
		<th width="10%">&nbsp;</th>
	</tr>
	<s:if test="page.resultlist.size() < 1">
		<tr><td colspan="5">暂无日志信息...</td></tr>
	</s:if>
	<s:iterator value="page.resultlist">
		<tr onMouseOver="this.style.background='#d6e5ff';" onMouseOut="this.style.background='#ffffff';">
			<td><s:property value="id" /></td>
			<td>
				<s:if test="content.length() > 70">
					<s:property value="content.substring(0,70)" />
				</s:if><s:else>
					<s:property value="content" />&nbsp;
				</s:else>
			</td>
			<td>
				<s:if test="remark.length() > 20">
					<s:property value="remark.substring(0,20)" />
				</s:if><s:else>
					<s:property value="remark" />&nbsp;
				</s:else>
			</td>
			<td><s:property value="recordTime" />&nbsp;</td>
			<td>
				<s:if test="type == 1">信息</s:if>
				<s:elseif test="type == 2">警告</s:elseif>
				<s:elseif test="type == 3">错误</s:elseif>
				<s:elseif test="type == 4">灾难性异常</s:elseif>
				<s:else><font color="red" size="2">异常状态</font></s:else>
			</td>
			<td><input type="button" value="查看" onclick="location.href='${ctx}/admin/log/log!checkLog.shtml?id=${id}'" />
			<input type="button" value="删除" onclick="location.href='${ctx}/admin/log/log!removeLog.shtml?id=${id}'" /></td>
			
		</tr>
	</s:iterator>
</table>
<div class="manu">
	<form name="pageForm" action="${ctx}/admin/log/log.shtml" method="get">
		<jsp:include page="/WEB-INF/index/common/pagination.jsp"></jsp:include>
		<input type="hidden" id="goPage" name="goPage" />
		<input type="hidden" name="log.type" value="${log.type}"/>
	</form>
</div>
<script type="text/javascript" src="${ctx}/js/tool.js" charset="gbk"></script>
