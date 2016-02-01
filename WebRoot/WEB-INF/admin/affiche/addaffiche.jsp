﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
		<title>九度网后台管理中心</title>
		<script type="text/javascript" src="${ctx}/editor/FCKeditor/fckeditor.js"></script>
		<script type="text/javascript">
			function checkForm(){
				var title=document.getElementById("title").value;
				if(title==null || title==""){
					alert("请将标题填写完整");
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
			<dl id=manage_top>
				<dt class="manage_top_title">
					<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
					<strong>当前位置：</strong>添加公告
				</dt>
			</dl>
		</div>
	
	<form name="afficheFrom" action="${ctx}/admin/affiche/afficheAdmin!save.shtml" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
			<tr>
				<th width="19%">
					信息类别:
				</th>
				<td width="81%">
					<s:select list="#{1:'网站公告',2:'最新动态',3:'官方新闻'}" name="affiche.type" listValue="value" listKey="key" theme="simple"   />
					<span class="red">*</span>
				</td>
			</tr>
			<tr>
				<th width="19%">
					信息标题:
				</th>
				<td width="81%">
					<input id="title" name="affiche.title" value="${affiche.title }"  class="upfile" size="80" />
					<span class="red">*</span>
				</td>
			</tr>
			<tr>
				<th width="19%">
					是否显示:
				</th>
				<td width="81%">
					<select size="1" name="affiche.state">
						<option value="1">
							显示
						</option>
						<option value="0">
							不显示
						</option>
					</select>
				</td>
			</tr>
			<tr>
				<th width="19%">
					信息内容:
				</th>
				<td width="81%">
				<FCK:editor instanceName="infoContent" inputName="affiche.content" width="100%" height="400">
					<jsp:attribute name="value">
						${affiche.content}
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
					<input type="hidden" name="affiche.id" value="${affiche.id}">
					<input type="hidden" name="goPage" value="${goPage}">
					<input type="submit" name="method.save" value="保存" onClick="return checkForm();" />
					<input type="button" name="method.list" value="取消" onclick="history.back();" />
				</td>
			</tr>
		</table>
	</form>	
	
	</body>
</html>
