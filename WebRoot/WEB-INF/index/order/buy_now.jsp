<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
	<%     
		response.setHeader("Pragma","No-cache");     
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires",-10);
	%>
		
		<!--menu end -->
		<div class="contaner tc">
			<div class=" ptb10">
				<a href="${ctx}/index.shtml" class="blue">首页</a> &gt;
				<s:if test="bizInfo.game!=null">
					<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${bizInfo.game.id}">${bizInfo.game.gameName }</a> &gt;
					<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${bizInfo.game.id}&bizKindID=${bizInfo.bizKind.id}">${bizInfo.bizKind.kindName}</a>
				</s:if>
				<s:else>
					<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${bizInfo.server.area.game.id}">${bizInfo.server.area.game.gameName }</a> &gt;
					<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${bizInfo.server.area.game.id}&bizKindID=${bizInfo.bizKind.id}">${bizInfo.bizKind.kindName}</a> &gt;
					<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${bizInfo.server.area.game.id}&bizKindID=${bizInfo.bizKind.id}&areaID=${bizInfo.server.area.id}">${bizInfo.server.area.areaName }</a> &gt;
					<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${bizInfo.server.area.game.id}&bizKindID=${bizInfo.bizKind.id}&areaID=${bizInfo.server.area.id}&serverID=${bizInfo.server.id}">${bizInfo.server.serverName }</a>
				</s:else>
			</div>
			<div class=" bs_ccc tl">
				<div class="mainreg">
					<form name="buy_now_form" action="${ctx}/user/trade/order/buy_now_submit.shtml" method="post">
						<div class="fbxx_title">
							<span class="ico_4">&nbsp;</span> 确认购买信息
						</div>
						<table border="0" cellspacing="0" cellpadding="0" width="100%">
							<tr>
								<th width="18%">
									商品编号：
								</th>
								<td>
									${bizInfo.serial}
									<a href="javascript:CopyID('${bizInfo.serial}');" class="blue">复制商品编号</a>
								</td>
							</tr>
							<tr>
								<th width="18%">
									商品名称：
								</th>
								<td>
									<a class="blue">${bizInfo.title }</a>
								</td>
							</tr>
							<tr>
								<th width="18%">
									商品分类：
								</th>
								<td>
									<s:if test="bizInfo.game!=null">
										${bizInfo.game.gameName } >>
									</s:if>
									<s:else>
										${bizInfo.server.area.game.gameName } >> ${bizInfo.server.area.areaName } >> ${bizInfo.server.serverName } >>
									</s:else>
									${bizInfo.bizKind.kindName }
								</td>
							</tr>
							
						<s:if test="bizInfo.sellModel!=3">
							<tr>
								<th>
									数量：(<span class="red">*</span>)
								</th>
								<td>
									<s:if test="#request.errorMessage.order.buyBum!=null">
										<input type="text" id="buyBum" name="order.buyBum" value="${errorMessage.order.buyBum }" onblur="chk_buyBum();" maxlength="25" />
									</s:if>
									<s:else>
										<input type="text" id="buyBum" name="order.buyBum" value="1" onblur="chk_buyBum();" maxlength="25" />
									</s:else>
									<span class="orange" id="buyBumMess"></span>
								</td>
							</tr>
						</s:if>	
							
							<tr>
								<th>
									单价：(<span class="red">*</span>)
								</th>
								<td>
									<strong class="f16 green" id="price">${bizInfo.price}</strong> 元
								</td>
							</tr>
							<tr>
								<th>
									应付金额：(<span class="red">*</span>)
								</th>
								<td>
									<strong class="f24 orange" id="fullmoney">${bizInfo.price}</strong> 元
								</td>
							</tr>
						
						<s:if test="bizInfo.sellModel!=3">	
							<tr>
								<th>
									收货角色名：(<span class="red">*</span>)
								</th>
								<td>
								
								<s:if test="userRoleList.size>0">
									<div class="pd3">
										<input type="radio" name="rad" id="radio_type_1" value="radio" onclick="chk_radio(1);" checked="checked" /><label for="radio_type_1">使用现有角色名</label>
										【<a href="${ctx}/user/account/role.shtml" target="_blank" class="blue_u">收货角色名管理</a>】
										<input type="radio" name="rad" id="radio_type_2" value="radio" onclick="chk_radio(2);" /><label for="radio_type_2">使用其他收货角色名</label>
									</div>
									<div id="roleTxt1" class="pd3 bs_ccc">
										<s:iterator value="userRoleList" status="index">
											<input type="radio" id="radio<s:property value="#index.index" />" name="order.playerRole" value="${roleName }" /><label for="radio<s:property value="#index.index" />">${roleName }</label>
										</s:iterator>
									</div>
								</s:if>
									<div id="roleTxt2" class="pd3" style="display:<s:if test="userRoleList.size>0">none</s:if><s:else>block</s:else>;">
										(<span class="red">*</span>)收货角色名：<INPUT type="text" id="playerRole" name="order.playerRole" value="${errorMessage.order.playerRole}" onblur="chk_playerRole();" maxLength=25>
										(<span class="red">*</span>)确认收货角色名：<INPUT type="text" id="r_playerRole" name="order.r_playerRole" value="" onblur="chk_r_playerRole();" maxLength="25">
									</div>
								</td>
							</tr>
							
							<tr>
								<th>
									收货角色等级：(<span class="red">*</span>)
								</th>
								<td>
									<input type="text" id="playerGrade" name="order.playerGrade" value="${errorMessage.order.playerGrade}" onblur="chk_playerGrade();" maxlength="100" />
								</td>
							</tr>
						</s:if>
						</table>
						<div class="fbxx_title mt10">
							<span class="ico_7">&nbsp;</span> 确认联系信息
						</div>
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<th width="18%">
									您的QQ：(<span class="red">*</span>)
								</th>
								<td>
									<s:if test="#request.errorMessage!=null">
										<input type="text" id="playQQ" name="order.playQQ" value="${errorMessage.order.playQQ}" maxlength="50" onblur="chk_playQQ();"  />
									</s:if>
									<s:else>
										<input type="text" id="playQQ" name="order.playQQ" value="${userSession.qq}" maxlength="50" onblur="chk_playQQ();"  />
									</s:else>
									请输入您的QQ号码！
								</td>
							</tr>
							<tr>
								<th>
									您的电话：(<span class="red">*</span>)
								</th>
								<td>
									<s:if test="#request.errorMessage!=null">
										<input type="text" id="playPhoneNum"  name="order.playPhoneNum" value="${errorMessage.order.playPhoneNum}" maxlength="50" onblur="chk_playPhoneNum();" />
									</s:if>
									<s:else>
										<input type="text" id="playPhoneNum" name="order.playPhoneNum" value="${userSession.phoneNum}" maxlength="50" onblur="chk_playPhoneNum();"  />
									</s:else>
									建议填写手机号，如果是固话或小灵通请在号码前加区号（例：02188888888）！
								</td>
							</tr>
						</table>
						<div class="pd10 tc">
							<input type="hidden" name="bizInfoID" value="${bizInfo.id }" />
							<button type="submit" onclick="return chk_buy_now();" class="orangebutton_big ml20">
								确认购买,去付款
							</button>
						</div>
					</form>
<script type="text/javascript">
<s:if test="bizInfo.sellModel!=3">
	function chk_buyBum(){
		var buyBum=$("buyBum").value.Trim();
		var price=$("price").innerHTML.Trim();
		if(!buyBum.match(/^[0-9]*[1-9][0-9]*$/)){
			alertDialog('购买数量必须是一个正整数');
			return false;
		}
		$("fullmoney").innerHTML=(buyBum*price).toFixed(2);
		return true;
	}

	function chk_playerRole(){
		var playerRole=$("playerRole").value.Trim();
		if(playerRole.length==0){
			alertDialog('请填写收货角色名');
			return false;
		}
		return true;
	}

	function chk_r_playerRole(){
		var r_playerRole=$("r_playerRole").value.Trim();
		var playerRole=$("playerRole").value.Trim();
		if(r_playerRole.length==0){
			alertDialog('请确认收货角色名');
			return false;
		}
		if(playerRole!=r_playerRole){
			alertDialog('确认角色名输入错误');
			return false;
		}
		return true;
	}

	function chk_playerGrade(){
		var playerGrade=$("playerGrade").value.Trim();
		if(!playerGrade.match(/^[0-9]*[1-9][0-9]*$/)){
			alertDialog('请填写收货角色等级');
			return false;
		}
		return true;
	}
</s:if>

	function chk_playQQ(){
		var playQQ=$("playQQ").value.Trim();
		if(!playQQ.match(/^\d{5,}$/)){
			alertDialog('请正确输入纯数字的QQ号码');
			return false;
		}
		return true;
	}

	function chk_playPhoneNum(){
		var playPhoneNum=$("playPhoneNum").value.Trim();
		if(!playPhoneNum.match(/^1[3,5]\d{9}$/)){
			alertDialog('请填写正确的手机号码');
			return false;
		}
		return true;
	}
	
	function chk_buy_now(){
	<s:if test="bizInfo.sellModel!=3">
		if(!chk_buyBum()){
			return false;
		}

	<s:if test="userRoleList.size>0">
		if($("radio_type_1").checked){
			var playerRoleName=document.getElementsByName("order.playerRole");
			var flag=false;
			for(var i=0;i<playerRoleName.length;i++){
				if(playerRoleName[i].checked){
					flag=true;
					break;
				}
			}
			if(!flag){
				alertDialog('请选择现有角色名');
				return false;
			}
		}else if($("radio_type_2").checked){
			if(!chk_playerRole()){
				return false;
			}
			if(!chk_r_playerRole()){
				return false;
			}
		}else{
			alertDialog('请填写收货角色名');
			return false;
		}
	</s:if>
	<s:else>
		if(!chk_playerRole()){
			return false;
		}
		if(!chk_r_playerRole()){
			return false;
		}
	</s:else>
		
		if(!chk_playerGrade()){
			return false;
		}
		
	</s:if>	
		if(!chk_playQQ()){
			return false;
		}
		if(!chk_playPhoneNum()){
			return false;
		}
	}

<s:if test="bizInfo.sellModel!=3">
	function chk_radio(index){
		var arr=document.getElementsByName("order.playerRole");
		if(index==1){
			for(var i=0;i<arr.length;i++){
				if(arr[i].type=="radio"){
					arr[i].disabled="";
				}
			}
			arr[0].checked="true";
			$("playerRole").disabled="disabled";
			$("r_playerRole").disabled="disabled";
			showDiv("roleTxt1");
			closeDiv("roleTxt2");
		}else{
			for(var i=0;i<arr.length;i++){
				if(arr[i].type=="radio"){
					arr[i].disabled="disabled";
				}
			}
			$("playerRole").disabled="";
			$("r_playerRole").disabled="";
			showDiv("roleTxt2");
			closeDiv("roleTxt1");
		}
	}
</s:if>	
	<s:if test="#request.errorMessage!=null && #request.errorMessage.errorMessage!=''">
		alertDialog('${errorMessage.errorMessage}');
	</s:if>
</script>
				</div>
			</div>
		</div>
	</body>
</html>

