<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
		<div class="mainlogin bbs_orange" style="width: 500px">
		<form name="findpsw_form" action="${ctx}/findpsw/findpsw_submit.shtml" method="post">
			<div class="title tl">
				<span class="ico_7">&nbsp;</span> <s:if test="pswtype==2">找回支付密码</s:if><s:else>找回登录密码</s:else>
			</div>
			<table border="0" cellspacing="0" cellpadding="0" class="formlisttable" width="98%" style="margin-top: 20px">
				<s:if test="findtype==2">
					<tr>
						<th width="30%">
							电子邮箱(<span class="red">*</span>)
						</th>
						<td align="left">
							<input name="email" type="text" id="email" maxlength="100" style="width: 140px" />
						</td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<th width="30%">
							密码提示问题
						</th>
						<td align="left">
							<select id="question" name="question"><jsp:include page="/WEB-INF/index/user/userhint.jsp"></jsp:include></select>
						</td>
					</tr>
					<tr>
						<th>
							提示问题答案(<span class="red">*</span>)
						</th>
						<td align="left">
							<input name="answer" type="text" id="answer" maxlength="100" style="width: 140px" />
						</td>
					</tr>
					<tr>
						<th>
							请输入您的新密码(<span class="red">*</span>)
						</th>
						<td align="left">
							<input id="password" name="password" type="password" maxlength="25"  />
							<span class="form_action_now" id=pwdInfoDiv>长度为6-20位，字母区分大小写</span>
						</td>
					</tr>
					<tr>
						<th>
							再次输入新密码(<span class="red">*</span>)
						</th>
						<td align="left">
							<input id="r_password" name="r_password" type="password" maxlength="25"  />
							<span class="form_action_now" id="repwdInfoDiv">请再输入一遍您上面输入的密码</span>
						</td>
					</tr>
				</s:else>
				
			</table>
			<div class="tc" style="margin-top: 20px">
				<input type="hidden" name="pswtype" value="${pswtype}" />
				<input type="hidden" name="findtype" value="${findtype}" />
				<input type="hidden" name="username" value="${username}" />
				<button type="submit" onclick="return chk_findpsw_form();" class="orangebutton_big">
					确定提交
				</button>
				&nbsp;&nbsp;
				<button type="button" class="orangebutton_big">
					取 消
				</button>
			</div>
			<script type="text/javascript">
				function chk_findpsw_form(){
					<s:if test="findtype==2">
						var email=$("email").value.Trim();
						if(email==""){
							alertDialog('请填写电子邮箱');
							return false;
						}else{
							$("email").value=email;
						}
						
					</s:if>
					<s:else>
						var question=$("question");
						var answer=$("answer").value.Trim();
						var password=$("password").value;
						var r_password=$("r_password").value;

						if(question.options[question.selectedIndex].value==""){
							alertDialog('请选择密码提示问题');
							return false;
						}

						if(answer==""){
							alertDialog('请填写密码提示问题答案');
							return false;
						}else{
							$("answer").value=answer;
						}
						
						if(password.length<6 || password.length>20){
							alertDialog('密码长度必须为6-20个字符');
							return false;
						}

						if(r_password!=password){
							alertDialog('2次输入的密码不一致，请重新输入');
							return false;
						}
					
					</s:else>
				}
			</script>
			
		</form>
		</div>
	</body>
</html>
