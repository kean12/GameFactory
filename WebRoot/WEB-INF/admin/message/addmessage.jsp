﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>九度网后台管理中心</title>
		<script type="text/javascript" src="${ctx}/js/tool.js"></script>
		<script type="text/javascript" src="${ctx}/editor/FCKeditor/fckeditor.js"></script>
		<script type="text/javascript">
			function checkForm(){
				var title=document.getElementById("title").value;
				if(title==null || title==""){
					alert("请填写标题！");
					return false;
				}
				
				var addresser=document.getElementById("addresser").value;
				if(addresser.length==0){
					alert("请填写发件人");
					return false;
				}
				
				var oEditor = FCKeditorAPI.GetInstance("infoContent") ; 
				var returnValue = oEditor.GetXHTML(true);
				if(returnValue==""){
					alert("你好！内容必须填写！");
					oEditor.EditorDocument.body.focus();
					return false;
				}
				
				return true;
			}
		</script>
	</head>

	<body>
		<div class="main_top_title">
			<dl id="manage_top">
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>站内消息&nbsp;>&nbsp;发送站内信
				</dt>
				<dd class="p9" disabled>
					<a href="#" onclick="history.back();">回上一级</a>
				</dd>
			</dl>
		</div>
		
		<form action="${ctx}/admin/message/messageAdmin!save.shtml" method="post" name="messageform">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
				<tr>
					<th width="19%">
						信息标题:
					</th>
					<td width="81%">
						<input class="upfile" size="80" value="${message.title}" name="message.title" id="title">
						<span class="red">*</span>
					</td>
				</tr>
				<tr>
					<th width="19%">
						发件人:
					</th>
					<td width="81%">
						<s:if test="message.addresser!=null">
							<input class="upfile" size="80" id="addresser" value="${message.addresser}" name="message.addresser">
						</s:if>
						<s:else>
							<input class="upfile" size="80" id="addresser" value="游戏买卖网" name="message.addresser">
						</s:else>
						<span class="red">*</span>
					</td>
				</tr>
				<tr>
					<th width="19%">
						信息类别:
					</th>
					<td width="81%">
						<s:select list="#{1:'系统信息'}"  name="message.type"  listValue="value" listKey="key" theme="simple"   />
					</td>
				</tr>
				<tr>
					<th width="19%">
						内容:
					</th>
					<td width="81%">
						<FCK:editor instanceName="infoContent" inputName="message.content" width="100%" height="400">
							<jsp:attribute name="value">
								${message.content}
							</jsp:attribute>
							<jsp:body>
							<FCK:config SkinPath="skins/office2003/"/>
							</jsp:body>
						</FCK:editor>
					</td>
				</tr>
				<tr>
					<th width="19%">
						操作:
					</th>
					<td width="81%">
						<input type="hidden" name="message.id" value="${message.id }" />
						<input type="hidden" name="message.time" value="${message.time }" />
						<input type="hidden" name="goPage" value="${goPage}" />
						<input type="submit" name="method.save" value="保存" onclick="return checkForm();"/>
						<input type="button" name="method.list" value="取消" onclick="history.back();" />
					</td>
				</tr>
			</table>
		</form>
		
	</body>
</html>
