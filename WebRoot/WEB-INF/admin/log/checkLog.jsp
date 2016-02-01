<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
<div class="main_top_title">
	<dl id="manage_top">
		<dt class="manage_top_title">
			<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
			<strong>当前位置：</strong>日志列表>>查看日志(仅供查看使用)
		</dt>
		<dd class="p9"><a href="#" onclick="history.back();">回上一级</a></dd>
	</dl>
</div>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
	<tr>
		<th width="10%">日志类型:</th>
		<td width="80%">
			<s:select list="#{-1:'--选择信息类型--',1:'信息',2:'警告',3:'错误',4:'灾难性异常'}"  name="log.type"  listValue="value" listKey="key" theme="simple"   />
		</td>
	</tr>
	<tr>
		<th width="10%">记录时间:</th>
		<td width="80%">${log.recordTime}</td>
	</tr>
	<tr>
		<th width="10%">备注:</th>
		<td width="80%">${log.remark}</td>
	</tr>
	<tr>
		<th width="10%">内容:</th>
		<td width="80%">
		<FCK:editor instanceName="content" toolbarSet="Basic" inputName="log.content" width="100%" height="450">
			<jsp:attribute name="value">
				${log.content}
			</jsp:attribute>
			<jsp:body>
			<FCK:config SkinPath="skins/office2003/"/>
			</jsp:body>
		</FCK:editor></td>
	</tr>
</table>
