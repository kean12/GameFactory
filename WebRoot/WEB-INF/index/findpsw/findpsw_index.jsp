<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
		<!--menu end -->
		<div class="mainlogin bbs_orange" style="width: 500px">
		<form name="findpsw_form" action="${ctx}/findpsw/findpsw_answer.shtml" method="post">
			<div class="title tl">
				<span class="ico_7">&nbsp;</span> 找回密码
			</div>
			<table border="0" cellspacing="0" cellpadding="0" class="formlisttable" width="98%" style="margin-top: 20px">
				<tr>
					<th width="30%">
						用户名(<span class="red">*</span>)
					</th>
					<td align="left">
						<input id="usr_Ipt" maxlength="25" id="username" name="username" />
					</td>
				</tr>
				
				<tr>
					<th>
						密码类型(<span class="red">*</span>)
					</th>
					<td align="left">
						<s:radio list="#{'1':'登录密码','2':'支付密码'}" name="pswtype" listValue="value" listKey="key" theme="simple"></s:radio>
					</td>
				</tr>
				
				<tr>
					<th>
						找回方式(<span class="red">*</span>)
					</th>
					<td align="left">
						<s:radio list="#{'1':'通过密码提示问题找回','2':'通过注册邮箱找回'}" name="findtype" listValue="value" listKey="key" theme="simple"></s:radio>
					</td>
				</tr>
				
				<tr>
					<th>
						验证码(<span class="red">*</span>)
					</th>
					<td align="left">
						验证码：<input id="j_validate_code" name="vercode" onfocus="firstfocus(true,'vercodeimg');" style="width: 56px" type="text"  />
						<a href="javascript:relImg('vercodeimg')">
							<img id="vercodeimg" alt="" src="${ctx}/authImg" align="middle" border="0" />
						</a>
						<a href="javascript:relImg('vercodeimg');" class="blue_u">换一个</a>
						
					</td>
				</tr>
			</table>
			<div class="tc" style="margin-top: 20px">
				<button type="submit" class="orangebutton_big" onclick="return chk_findpsw_form();">
					确定提交
				</button>
				&nbsp;&nbsp;
				<button type="button" class="orangebutton_big">
					取 消
				</button>
			</div>
			<script type="text/javascript">
				function chk_findpsw_form(){
					var username=$("username").value.Trim();
					var pswtype_arr=document.getElementsByName("pswtype");
					var findtype_arr=document.getElementsByName("findtype");
					var vercode=$("j_validate_code").value.Trim();
					var pswtype=false;
					var findtype=false;
					
					if(username==""){
						alertDialog('请输入用户名');
						return false;
					}else{
						$("username").value=username;
					}
					
					for(var i=0;i<pswtype_arr.length;i++){
						if(pswtype_arr[i].checked){
							pswtype=true;
							break;
						}
					}
					if(!pswtype){
						alertDialog('请选择需要找回的密码类型');
						return false;
					}
					
					for(var i=0;i<findtype_arr.length;i++){
						if(findtype_arr[i].checked){
							findtype=true;
							break;
						}
					}
					if(!findtype){
						alertDialog('请选择密码找回方式');
						return false;
					}
					
					if(vercode==""){
						alertDialog('请输入验证码');
						return false;
					}else{
						$("j_validate_code").value=vercode;
					}
					
				}
			</script>
		</form>
		</div>
	</body>
</html>