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
				申请提现
			</div>
			<div class="attention bs_ccc">
				买卖网账户余额提取到您的银行卡，个人用户每天最多可提现
				<span class="red">3</span> 次，每次提现金额不高于
				<span class="red">${serverMoney}</span>元
			</div>
			<div class="txlist">
			<form action="${ctx}/user/extract/extract_apply_submit.shtml" method="post">
				<dl>
					<dt>
						您的账户：
					</dt>
					<dd>
						<strong>${userSession.realName}</strong>
					</dd>
					<dt>
						提现银行账户：
					</dt>
					<dd>
						<s:set name="bankNum" value="userSession.userInfo.bankNum"></s:set>
						<strong>${userSession.userInfo.bankName}</strong><s:property value="#bankNum.substring(0,12)" />****<s:property value="#bankNum.substring(16,#bankNum.length())" />
						<a href="${ctx}/user/account/bank.shtml" class="blue" >设置银行账号</a>
					</dd>
					<dt></dt>
					<dd>
						<span class="bbs_orange pd3 red"> ！您的提现资金将在1-2个工作日后到达您的银行账户</span>
					</dd>
					<dt>
						提现金额：
					</dt>
					<dd>
						<input type="text" name="money" id="money" />
						元
					</dd>
					<dt></dt>
					<dd>
						可用余额：
						<span class="red">${userSession.userInfo.money}</span>元。（还可以提现<s:if test="count>0">${count}</s:if><s:else>0</s:else>次）
					</dd>
					<dt></dt>
					<dd class="ptb10">
						<button class="orangebutton_big" type="submit" onclick="return chk_money();">
							下一步
						</button>
					</dd>
				</dl>
			</form>	
				
				<div class="blank10"></div>
			</div>
		</div>
		<script type="text/javascript">
			function chk_money(){
				var money=$("money").value.Trim();
				if(money=="" || isNaN(money)){
					alertDialog('请输入提现金额');
					return false;
				}else{
					$("money").value=money;
				}
				
				if(money>${serverMoney}){
					alertDialog('单次提现金额不能大于${serverMoney}元');
					return false;
				}
				
			}
		</script>
		
		
		<s:if test="count==0">
			<script type="text/javascript">
				art.dialog({content:'您今天的提现次数已满', fixed:true,lock:true,style:'succeed',width:'280',height:'80'},
					function(){
						window.location="${ctx}/user/home.shtml";
					}
				);
			</script>
		</s:if>
		<s:if test="userSession.userInfo.bankNum==null">
			<script type="text/javascript">
				art.dialog({content:'请您先设置提现银行账号', fixed:true,lock:true,style:'succeed',width:'280',height:'80'},
					function(){
						window.location="${ctx}/user/account/bank.shtml";
					}
				);
			</script>
		</s:if>
		
	</body>
</html>
