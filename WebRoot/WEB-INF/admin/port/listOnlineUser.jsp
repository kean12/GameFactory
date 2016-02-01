<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
<div class="main_top_title">
	<dl id=manage_top>
		<dt class="manage_top_title">
			<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
			<strong>当前位置：</strong>客户端在线用户列表信息
		</dt>
	</dl>
</div>
<div class="blank0"></div>
<hr color="red"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable">
	<tr>
		<th>用户名称</th>
	</tr>
	<s:iterator value="online" id="onlineUser">
		<tr onMouseOver="this.style.background='#d6e5ff';" onMouseOut="this.style.background='#ffffff';">
			<td><s:property value="#onlineUser" /></td>
		</tr>
	</s:iterator>
</table>