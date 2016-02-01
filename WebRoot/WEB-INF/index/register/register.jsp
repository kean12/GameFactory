<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<title>用户注册</title>
<script type="text/javascript" src="${ctx}/js/index/register.js"></script>
<script type='text/javascript' src='${ctx}/dwr/interface/ajaxUser.js'></script>
<!--menu end -->
<div class="contaner tc">
	<div class="bbs_orange tl">
		<div class="title">
		<span class="ico_7">&nbsp;</span> <span id="registerTitle">用户注册</span>
		</div>
		<div class="mainreg">
		<form name="regForm" action="${ctx}/user/verify.shtml" method="post">
			<input type="hidden" name="user.userInfo.ip" value="-"/>
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th>用户名(<span class="red">*</span>)</th>
					<td>
						<input id="username" name="user.username" value="${errorUser.username}" onblur="chkusername(this.value);"  maxlength="25" style="width:150px;" />
						<span id="usernameMess" class="form_action_now">
							<s:if test="errorInfo!=null">
								${errorInfo.usernameMess}
							</s:if>
							<s:else>
								6-20个字符(包括大小写字母、数字、下划线)，一旦注册成功会员名不能修改
							</s:else>
						</span>
					</td>
				</tr>
				<tr>
					<th width="18%">密码(<span class="red">*</span>)</th>
					<td width="82%">
						<input id="password" name="user.password" value="${errorUser.password}" onblur="chkpassword();" onKeyPress="chkpassword();" type="password" maxLength="25" style="width:150px;"  />
						<span class="form_action_now" id="passwordMess">
							<s:if test="errorInfo!=null">${errorInfo.passwordMess}</s:if>
							<s:else>密码长度为6-20位，字母请区分大小写</s:else>
						</span>
					</td>
				</tr>
				<tr>
					<th>重复登录密码(<span class="red">*</span>)</th>
					<td>
						<input id="password2" onblur="chkpassword2();"  onKeyPress="chkpassword2();" type="password" maxLength="25" style="width:150px;" />
						<span class="form_action_now" id="password2Mess">
							请再输入一遍您上面输入的密码
						</span>
					</td>
				</tr>
				<tr>
					<th>提示问题(<span class="red">*</span>)</th>
					<td>
						<select id="hint" name="user.hint" style="width:150px;" onchange="chkhint();">
							<s:include value="/WEB-INF/index/user/userhint.jsp"></s:include>
						</select>
						<span class="form_action_now" id="hintMess">
							<s:if test="errorInfo!=null">
								${errorInfo.hintMess}
								<script type="text/javascript">
									function onloadchk(){
										var hint=document.getElementById("hint");
										var index='${errorUser.hint}';
										for(var i=0;i<hint.options.length;i++){
											if(hint.options[i].value==index){
												hint.options[i].selected=true;
												return;
											}
										}
									}
									onloadchk();
								</script>
							</s:if>
							<s:else>
								请选择密码提示问题，用于修改个人信息。
							</s:else>
						</span>
					</td>
				</tr>
				<tr>
					<th>提示问题答案(<span class="red">*</span>)</th>
					<td>
						<input id="answer" name="user.answer" value="${errorUser.answer}" type="text" maxlength="100" onKeyPress="chkanswer();" onblur="chkanswer()" style="width:150px;" />
						<span class="form_action_now" id="answerMess">
							<s:if test="errorInfo!=null">${errorInfo.answerMess}</s:if>
							<s:else>密码提示答案至少需要2个汉字或4个字符</s:else>
						</span>
					</td>
				</tr>
				<tr>
					<th>您的电子邮件地址(<span class="red">*</span>)</th>
					<td>
						<input id="email" name="user.email" value="${errorUser.email}" type="text" maxlength="50" onblur="chkemail();" onKeyPress="chkemail();" style="width:150px;" />
						<span class="form_action_now" id="emailMess">
							<s:if test="errorInfo!=null">${errorInfo.emailMess}</s:if>
							<s:else>请认真填写，用于密码找回</s:else>
						</span>
					</td>
				</tr>
				<tr>
					<th>您的QQ号码(<span class="red">*</span>)</th>
					<td>
						<input id="qq" name="user.qq" value="${errorUser.qq}" type="text" maxlength="50" onblur="chkqq();" onKeyPress="chkqq();" style="width:150px;"  />
						<span class="form_action_now" id="qqMess">
							<s:if test="errorInfo!=null">${errorInfo.qqMess}</s:if>
							<s:else>请填写有效的QQ，用于交易时与客服联系</s:else>
						</span>
					</td>
				</tr>
				<tr>
					<th>您的手机(<span class="red">*</span>)</th>
					<td>
						<input id="phoneNum" name="user.phoneNum" value="${errorUser.phoneNum}" type="text" maxlength="50" onblur="chkphoneNum();" onKeyPress="chkphoneNum();" style="width:150px;" />
						<span class="form_action_now" id="phoneNumMess">
							<s:if test="errorInfo!=null">${errorInfo.phoneNumMess}</s:if>
							<s:else>请填写有效的手机号码，用于交易时与客服联系。</s:else>
						</span>
					</td>
				</tr>
				<tr>
					<th>验证码(<span class="red">*</span>)</th>
					<td>
						<input id="reg_vercode" name="vercode" onblur="chkvercode();" type="text" maxlength="6" style="width: 56px;" />
						<img id="valimg" alt="" src="${ctx}/authImg" align="middle" border="0" />
						<span>
							[<a class="blue" onclick="relImg('valimg');" style="cursor: pointer;text-decoration: underline">看不清楚？换个图片</a>]
						</span>
						<span class="form_action_now" id="vercodeMess">
							<s:if test="errorInfo!=null">${errorInfo.vercodeMess}</s:if>
						</span>
					</td>
				</tr>
			</table>
			<div class="formpop">
				友情提醒：为保证您的帐户安全，您可以先
				<a href="http://sd.beike.cn/" target="_blank" class="blue_u" coords="red">免费查找病毒木马</A>，看看电脑是否安全。
			</div>
			<div class="pd10 tc">
			<%-- 
				<input id="followme" type="checkbox" CHECKED value="" name="" />
				<a class="l-b" href="#" target="_blank">我已阅读并同意服务协议</A><br />
				<br />
			--%>	
				<button type="button" onclick="chkform();" class="orangebutton_big">确定提交</button>
			</div>
 		</form>
		</div>
	</div>
</div>
<script type="text/javascript">
if(getUrlParam('registerType') == 'completeInformation'){
	$('registerTitle').innerText = '完善用户资料';
	$('username').value = getUrlParam('u') ;
	$('password').value = getUrlParam('p') ;
}
</script>