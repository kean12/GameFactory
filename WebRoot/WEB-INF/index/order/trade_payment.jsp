<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="${ctx}/css/bank.css" rel="stylesheet" type="text/css" />
<jsp:include page="/WEB-INF/index/common/setApplypwd.jsp" />
<div class="contaner tc">

<form name="pay_from" action="${ctx}/user/trade/order/trade_payment_submit.shtml" method="post">
	<div id="rightnav">
		<ul>
			<li class="action_nav" onclick="showmain(0)">
				<span>余额付款</span>
			</li>
			<li class="normal_nav" onclick="showmain(1)">
				<span>网上银行付款</span>
			</li>
		</ul>
	</div>
	<div class="contentbox" id="box0" style="display: block">
		<ul>
			<li class="pd10 bbd_ccc">
				您当前的可用余额：
				<strong class="f24 orange">${userSession.userInfo.money}</strong> 元
			</li>
		<s:if test="flag=='true'">
			<li class="pd10 bbd_ccc">
				应付金额额：
				<strong class="f24 orange">${order.sumPrice}</strong> 元
			</li>
			<s:if test='userSession.userInfo.applyPwd == null || userSession.userInfo.applyPwd ==""'>
				<div class="attention bs_ccc">您还没有设置‘<span class="red">支付密码</span>’，点击[<a href="javascript:void(0)" class="blue_u" onclick="setDiv()">设置</a>]</div>
			</s:if>
			<s:else>
				<li class="pd10 bbd_ccc">
					支付密码：<input type="password" id="ye_applyPwd" />
					<a href="${ctx}/findpsw/findpsw_index.shtml" class="blue">找回支付密码</a>
				</li>
				<li class="pd10">
					<button type="button" onclick="chk_YE();" class="orangebutton_big ml20">
						确认付款
					</button>
				</li>
			</s:else>
		</s:if>
		</ul>
	</div>
	
	<input type="hidden" name="applyPwd" id="applyPwd" />
	<input type="hidden" name="orderID" value="${order.id }" />
	
	<div class="contentbox" id="box1" style="display:none">
		<div>
			<label class="linkBank ICBCPer" ><input name="bank" id="ICBCPer" type="radio" value="ICBC-NET" /></label>
			<!-- 中国工商银行 -->
			
			<label class="linkBank CMB" ><input name="bank" id="CMB" type="radio" value="CMBCHINA-NET" /></label>
				<!-- 招商银行 -->
				
			<label class="linkBank ICC" ><input name="bank" id="ICC" type="radio" value="CCB-NET" /></label>
				<!-- 建设银行 -->
				
			<label class="linkBank BOCB2C" ><input name="bank" id="BOCB2C" type="radio" value="BOC-NET" /></label>
				<!-- 中国银行-->
			
			<label class="linkBank ABC" ><input name="bank" id="ABC" type="radio" value="ABC-NET" /></label>
				<!-- 中国农业银行-->
			
			<label class="linkBank COMM" ><input name="bank" id="COMM" type="radio" value="BOCO-NET" /></label>
				<!-- 交通银行-->
			
			<label class="linkBank SPDBPer" ><input name="bank" id="SPDBPer" type="radio" value="SPDB-NET" /></label>
				<!-- 上海浦东发展银行 -->
			
			<label class="linkBank GDB" ><input name="bank" id="GDB" type="radio" value="GDB-NET" /></label>
				<!-- 广东发展银行-->
			
			<label class="linkBank CITIC" ><input name="bank" id="CITIC" type="radio" value="ECITIC-NET" /></label>
				<!-- 中信银行 -->
			
			<label class="linkBank CEBBANK" ><input name="bank" id="CEBBANK" type="radio" value="CEB-NET" /></label>
				<!-- 中国光大银行 -->
			
			<label class="linkBank CIB" ><input name="bank" id="CIB" type="radio" value="CIB-NET" /></label>
				<!-- 兴业银行-->
			
			<label class="linkBank SDB" ><input name="bank" id="SDB" type="radio" value="SDB-NET" /></label>
				<!-- 深圳发展银行-->
			
			<label class="linkBank CMBC" ><input name="bank" id="CMBC" type="radio" value="CMBC-NET" /></label>
				<!-- 中国民生银行--><!--
			
			<label class="linkBank HZCBB2C" ><input name="bank" id="HZCBB2C" type="radio" value="HZCBB2C" /></label>
				 杭州银行
			
			<label class="linkBank SHBANK" ><input name="bank" id="SHBANK" type="radio" value="SHBANK" /></label>
				 上海银行 
			
			--><label class="linkBank NBBANK" ><input name="bank" id="NBBANK" type="radio" value="NBCB-NET" /></label>
				<!-- 宁波银行 -->
		</div>
		<div class="blank10"></div>
		<div class="pd10 bbd_ccc"><s:radio list="#{'1':'余额付款','2':'网银付款'}" name="mode" onclick="chk_mode('mode',%{order.sumPrice},%{userSession.userInfo.money});"></s:radio></div>
		<div class="pd10 bbd_ccc">应付金额：<strong class="f24 orange">${order.sumPrice}</strong> 元 </div>
		
		<div class="pd10 bbd_ccc">余额支付：<strong class="f24 orange" id="paybalance"></strong> 元 </div>
		
				<div class="pd10 bbd_ccc">网银支付：<strong class="f24 orange" id="paymoney"></strong> 元</div>
		<div class="pd10 bbd_ccc">
			<s:if test='userSession.userInfo.applyPwd == null || userSession.userInfo.applyPwd ==""'>
				<div class="attention bs_ccc">您还没有设置‘<span class="red">支付密码</span>’，点击[<a href="javascript:void(0)" class="blue_u" onclick="setDiv()">设置</a>]</div>
			</s:if>
			<s:else>
				支付密码：<input type="password" id="wy_applyPwd" />
				<a href="${ctx}/findpsw/findpsw_index.shtml" class="blue">找回支付密码</a>
				<div class="pd10 bbd_ccc">
					<button type="button" onclick="chk_WY()" class="orangebutton_big ml20">
						确认付款
					</button>
				</div>
			</s:else>
		</div>
		
<script type="text/javascript">
function chk_YE(){
	document.getElementById("applyPwd").value="";
	var applyPwd=document.getElementById("ye_applyPwd").value;
	if(applyPwd==""){
		alertDialog('请输入支付密码!');
		return false;
	}else{
		document.getElementById("applyPwd").value=applyPwd;
	}
	document.pay_from.submit();
}

function chk_WY(){
	document.getElementById("applyPwd").value="";
	var applyPwd=document.getElementById("wy_applyPwd").value;
	var paymoney=document.getElementById("paymoney").innerHTML;
	var mode=document.getElementsByName("mode");
	if(applyPwd==""){
		alertDialog('请输入支付密码!');
		return false;
	}else{
		document.getElementById("applyPwd").value=applyPwd;
	}
	for(var i=0;i<mode.length;i++){
		if(mode[i].checked && paymoney>0){
			var flag=true;
			var bank=document.getElementsByName("bank");
			for(var j=0;j<bank.length;j++){
				if(bank[j].checked){
					flag=false;
					break;
				}
			}
			if(flag){
				alertDialog('请选择支付银行!');
				return false;
			}
			break;
		}
	}
	document.pay_from.submit();
}
			
function showmain(obj){
	var menu_num=document.getElementById("rightnav").getElementsByTagName("li");
	for(i=0;i<menu_num.length;i++){
		menu_num[i].className=i==obj?"action_nav":"normal_nav";
		document.getElementById('box'+i).style.display=i==obj?"block":"none";
		if(i==0){
			if(document.getElementsByName("mode")){
				document.getElementsByName("mode")[0].checked="checked";
			}
			chk_mode('mode',${order.sumPrice},${userSession.userInfo.money});
		}
	}
}

function chk_mode(obj,sumPrice,money){
	var mode_arr = document.getElementsByName(obj);
	for(var i=0;i<mode_arr.length;i++){
		if(mode_arr[i].checked){
			if($('mode')){
				$('mode').value=mode_arr[i].value;
			}
			if(mode_arr[i].value==1){
				if(sumPrice-money<0){
					document.getElementById("paybalance").innerHTML=sumPrice.toFixed(2);
					document.getElementById("paymoney").innerHTML="0.00";
				}else{
					document.getElementById("paybalance").innerHTML=money.toFixed(2);
					document.getElementById("paymoney").innerHTML=(sumPrice-money).toFixed(2);
				}
			}else{
				document.getElementById("paybalance").innerHTML="0.00";
				document.getElementById("paymoney").innerHTML=sumPrice.toFixed(2);
			}
		}
	}
}
chk_mode('mode',${order.sumPrice},${userSession.userInfo.money});
</script>
		<jsp:include page="/WEB-INF/index/order/bank_introduction.jsp" />
	</div>
</form>
</div>
<s:if test="flag=='false'"><script type="text/javascript">showmain(1);</script></s:if>
