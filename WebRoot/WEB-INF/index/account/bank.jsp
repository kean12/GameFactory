<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
		<div class="contaner tc">
			<div class=" ptb10">
				您的位置：
				<a href="${ctx}/index.shtml" class="blue">首页</a> >
				<a href="${ctx}/user/home.shtml" class="blue">用户中心</a> >
				<a href="${ctx}/user/account/my_account.shtml" class="blue">我的账户</a> >
				设置银行账号
			</div>
			<div class="txlist bs_ccc">
			<form name="bank_form" action="${ctx}/user/account/update_bank.shtml" method="post">
				<dl>
					<dt>
						真实姓名：
					</dt>
					<dd>
						<input type="text" id="realName" value="${userSession.realName }" disabled="disabled" /><span class="ml10" >如：中国工商银行</span>
					</dd>
					<dt></dt>
					<dd><span class="red">真实姓名必须与下方个人银行卡的开户人户名一致</span></dd>
					
					
					<dt>
						开户银行名称：
					</dt>
					<dd>
						<input type="text" id="bankName" name="bankName" value="${userSession.userInfo.bankName }" /><span class="ml10" >如：中国工商银行</span>
					</dd>
					
					<dt>
						开户所在地：
					</dt>
					<dd>
						<input type="text" id="bankSite" name="bankSite" value="${userSession.userInfo.bankSite }" /><span class="ml10" >如：浙江杭州</span>
					</dd>
					<dt></dt>
					<dd>
						<span class="red">格式：如浙江杭州，请不要填入省和市二字</span>
					</dd>
					<dt>
						开户人户名：
					</dt>
					<dd>
						<input type="text" id="bankUserName" name="bankUserName" value="${userSession.realName }" /><span class="ml10" >如：张三</span>
					</dd>
					
					<dt>
						个人银行账号：
					</dt>
					<dd>
						<s:if test="userSession.userInfo.bankNum!=null">
							<s:set name="bankNum" value="userSession.userInfo.bankNum"></s:set>
							<input type="text" id="bankNum" name="bankNum" value="<s:property value="#bankNum.substring(0,12)" />****<s:property value="#bankNum.substring(16,#bankNum.length())" />"/><span class="ml10" >如：6222021234567890123</span>
						</s:if>
						<s:else>
							<input type="text" id="bankNum" name="bankNum" value="${userSession.userInfo.bankNum }" /><span class="ml10" >如：6222021234567890123</span>
						</s:else>
					</dd>
					<dt></dt>
					<dd>
						<span class="red">
							<s:if test="userSession.realName!=null">
								请输入有效的银行卡号，该银行卡户名必须是“<s:property value="userSession.realName" />”，否则无法提现
							</s:if>
							<s:else>
								请输入有效的银行卡号，该银行卡户名必须与真实姓名一致，否则无法提现
							</s:else>
						</span>
					</dd>
					<dt class="ptb10"></dt>
					<dd class="ptb10">
						<button class="orangebutton_big" onclick="chk_bank_from();">
							保存设置
						</button>
					</dd>
				</dl>
				<div class="blank10"></div>
			</form>
			</div>
		</div>
		<script type="text/javascript">
			function chk_bank_from(){
				var realName=$("realName").value.Trim();
				var bankName=$("bankName").value.Trim();
				var bankSite=$("bankSite").value.Trim();
				var bankUserName=$("bankUserName").value.Trim();
				var bankNum=$("bankNum").value.Trim();
				if(bankName==""){
					alertDialog('请输入开户银行名称！');
					$("bankName").select();
					return false;
				}else{
					$("bankName").value=bankName;
				}

				if(bankSite==""){
					alertDialog('请输入开户所在地！');
					$("bankSite").select();
					return false;
				}else{
					$("bankSite").value=bankSite;
				}

				if(realName!=bankUserName){
					alertDialog('开户名与真实姓名不一致！');
					$("bankUserName").select();
					return false;
				}

				if(bankNum==""){
					alertDialog('请输入个人银行账号！');
					$("bankNum").select();
					return false;
				}else{
					$("bankNum").value=bankNum;
				}
			
				document.bank_form.submit();
			}
			<s:if test="userSession.realName==null">
				art.dialog({content:'请先设置你的身份证和真实姓名！', fixed:true,lock:true,style:'succeed',width:'280',height:'80'},
					function(){
						window.location=AP+'/user/account/my_account.shtml';
					}
				);
			</s:if>
		</script>
	</body>
</html>
