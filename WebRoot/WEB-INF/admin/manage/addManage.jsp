<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tool.js" charset="gbk" ></script>
<div class="main_top_title">
	<dl id="manage_top">
		<dt class="manage_top_title">
			<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
			<strong>当前位置：</strong>用户管理
		</dt>
		<dd class="p9"><a href="#" onclick="history.back();">回上一级</a></dd>
	</dl>
</div>
<form action="${ctx}/admin/manage/manage!saveManage.shtml" method="post" onsubmit="return checkForm(this)">
<INPUT type="hidden" name="manageInfo.id" value="${manageInfo.id}" />
<INPUT type="hidden" name="manageInfo.lockAccessIp" id="lockAccessIp"/>
<INPUT type="hidden" name="manageInfo.registerTime" value="${manageInfo.registerTime}" />
<INPUT type="hidden" name="manageInfo.regip" value="${manageInfo.regip}" />
<INPUT type="hidden" name="manageInfo.loginTime" value="${manageInfo.loginTime}" />
<INPUT type="hidden" name="manageInfo.ip" value="${manageInfo.ip}" />
<INPUT type="hidden" name="manageInfo.tmpTime" value="${manageInfo.tmpTime}" />
<!-- 有安全隐患 待修改 -->
<INPUT type="hidden" name="manageInfo.password" value="${manageInfo.password}" />
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable1">
	<tr>
		<th width="10%">用户名:</th>
		<td width="81%">
			<INPUT class="upfile" name="manageInfo.username" id="username" size=80 value="${manageInfo.username}" />
			<span class="red">*</span>
		</td>
	</tr>
	<tr>
		<th width="10%">密码:</th>
		<td width="81%">
			<INPUT type="password" class="upfile" name="manageInfo.password2" size=80 />
			<span class="red">*(修改时，为空则不修改密码)</span>
		</td>
	</tr>
	<tr>
		<th width="10%">名称:</th>
		<td width="81%">
			<INPUT class="upfile" id="name" name="manageInfo.name" size=80 value="${manageInfo.name}" />
			<span class="red">*</span>
		</td>
	</tr>
	<tr>
		<th width="10%">联系QQ:</th>
		<td width="81%">
			<INPUT type="text" class="upfile" name="manageInfo.qq" value="${manageInfo.qq}" size="80" />
			<span class="red">*</span>
		</td>
	</tr>
	<tr>
		<th width="10%">权限:</th>
		<td width="81%">
			<s:select list="roleList"  name="manageInfo.role.id"  listValue="description" listKey="id" theme="simple"   />
			<span class="red">*</span>
		</td>
	</tr>
	<tr>
		<th width="10%"></th>
		<td width="80%">
		
		允许IP:
			<select id="accessIpSel" size="7" ondblclick="removeSelect(this)" multiple="multiple"  style="width:120px;">
				<s:iterator value="manageInfo.accessIp">
					<option value="<s:property />"><s:property /></option>
				</s:iterator>
			</select>
			<input type="text" id="txtAccessIp"/> <button onclick="addIp('accessIpSel','txtAccessIp');">添加允许IP</button>
			<span class="red">*</span>
		锁定IP:
			<select multiple="multiple" size="7" id="lockIpSel" style="width:120px;" ondblclick="removeSelect(this)">
				<s:iterator value="manageInfo.lockIp">
					<option value="<s:property />"><s:property /></option>
				</s:iterator>
			</select>
			<input type="text" id="txtLockIp"/> <button onclick="addIp('lockIpSel','txtLockIp');">添加锁定IP</button>
			<span class="red">*</span>
		</td>
	</tr>
	<tr>
		<th width="10%">状态:</th>
		<td width="81%">
			<s:select list="#{1:'启用',0:'禁用'}"  name="manageInfo.isuse"  listValue="value" listKey="key" theme="simple"   />
			<span class="red">*</span>
		</td>
	</tr>
	<tr>
		<th width="10%">操作:</th>
		<td width="81%">
			<input type="submit" value="保存"/>
			<input type="button" value="取消" onclick="history.back();"/>
			<FONT color="red"><s:property value="errorMessage"/></FONT>
		</td>
	</tr>
</table>
	<pre class="red">	
		/*
		 * 判断帐户的IP限制规则
		 * 1，当允许ip [getAccessIp()]为空，锁定ip [getLockIp()]为空 ，通过
		 *
		 * 2，当允许ip [getAccessIp()]为空，锁定ip [getLockIp()]不为空
		 * 		且锁定ip包含IP时，不通过
		 * 
		 * 
		 * 3，当允许ip [getAccessIp()]不为空且包含该IP时，通过
		 * 
		 * 4，当允许ip [getAccessIp()]为空，锁定ip [getLockIp()]不为空
		 * 		且锁定ip包含IP时，不通过
		 * */</pre>
</form>
<script type="text/javascript">
function checkIp(ip){
	var pattern=/^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
	return pattern.test(ip) ;
}

function removeSelect(o){
	if(o.selectedIndex != -1){
		o.remove(o.selectedIndex);
	}
}
function addIp( selId , addId){
	var s1 = $('accessIpSel');
	var s2 = $('lockIpSel');
	
	var s = $(selId);
	var txt = $(addId);
	
	if(!checkIp(txt.value)){
		alert('不是合法的IP！');return false ;
	}
	for(var i = 0; i < s1.options.length ; i ++ ){
		if(s1.options[i].text == txt.value){
			s1.options[i].focus();
			alert('已经存在该IP！')
			return;
		}
	}
	for(var i = 0; i < s2.options.length ; i ++ ){
		if(s2.options[i].text == txt.value){
			s2.options[i].focus();
			alert('已经存在该IP！')
			return;
		}
	}
	
	var o = document.createElement('option');
	o.appendChild(document.createTextNode( txt.value ));
	o.setAttribute('value', txt.value);
	s.appendChild(o);
	return false ;
}
function checkForm(f){
	if($('username').value == ''){
		alert('用户名不能为空!');return false ;
	}
	var y = '';
	var s = $('accessIpSel');
	for(var i = 0; i < s.options.length ; i ++ ){
		y += ('^' + s.options[i].value + ',');
	}
	var s = $('lockIpSel');
	for(var i = 0; i < s.options.length ; i ++ ){
		y += ('!' + s.options[i].value + ',');
	}
	$('lockAccessIp').value = y ;
	return true ;
}
</script>