<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
		<div class="contaner tc">
			<div class="ptb5">
			</div>
			<div class=" bs_ccc tl">
				<div class="mainreg">
					<form name="buy_now_form" action="${ctx}/user/trade/order/examine/examine_order_amend_submit.shtml" method="post">
						<div class="fbxx_title">
							<span class="ico_4">&nbsp;</span> 确认购买信息
						</div>
						<table border="0" cellspacing="0" cellpadding="0" width="100%">
							<tr>
								<th width="18%">
									订单编号：
								</th>
								<td>
									${order.orderNum}
									<a href="javascript:CopyID('${order.orderNum}');" class="blue">复制商品编号</a>
								</td>
							</tr>
							<tr>
								<th width="18%">
									商品名称：
								</th>
								<td>
									<a class="blue">${order.title }</a>
								</td>
							</tr>
							<tr>
								<th width="18%">
									商品分类：
								</th>
								<td>
									<s:if test="order.game!=null">
										${order.bizInfo.game.gameName } >>
									</s:if>
									<s:else>
										${order.bizInfo.server.area.game.gameName } >> ${order.bizInfo.server.area.areaName } >> ${order.bizInfo.server.serverName } >>
									</s:else>
									${order.bizKind.kindName }
								</td>
							</tr>
							<tr>
								<th>
									单价：(<span class="red">*</span>)
								</th>
								<td>
									<strong class="f16 green" id="price">${order.price}</strong> 元
								</td>
							</tr>
							<tr>
								<th>
									数量：(<span class="red">*</span>)
								</th>
								<td>
									<strong class="f16 green" id="price">${order.buyBum}</strong> 件
								</td>
							</tr>
							<tr>
								<th>
									应付金额：(<span class="red">*</span>)
								</th>
								<td>
									<strong class="f24 orange" id="fullmoney">${order.price}</strong> 元
								</td>
							</tr>
						
						<s:if test="order.bizInfo.sellModel!=3">	
							<tr>
								<th>
									收货角色名：(<span class="red">*</span>)
								</th>
								<td>
								
								<s:if test="userRoleList.size>0">
									<div class="pd3">
										<input type="radio" name="rad" id="radio_type_1" value="radio" onclick="chk_radio(1);" checked="checked" /><label for="radio_type_1">原始收货角色名</label>
										<input type="radio" name="rad" id="radio_type_2" value="radio" onclick="chk_radio(2);" /><label for="radio_type_2">使用其他收货角色名</label>
									</div>
									<div id="roleTxt1" class="pd3 bs_ccc">
										<input type="radio" id="rad_playerRole" name="order.playerRole" value="${order.playerRole}" checked="checked" /><label for="">${order.playerRole}</label>
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
									<input type="text" id="playerGrade" name="order.playerGrade" value="${order.playerGrade}" onblur="chk_playerGrade();" maxlength="100" />
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
										<input type="text" id="playPhoneNum" name="order.playPhoneNum" value="${user.phoneNum}" maxlength="50" onblur="chk_playPhoneNum();"  />
									</s:else>
									建议填写手机号，如果是固话或小灵通请在号码前加区号（例：02188888888）！
								</td>
							</tr>
						</table>
						<div class="pd10 tc">
							<input type="hidden" name="orderNum" value="${orderNum}" />
							<button type="submit" onclick="return chk_buy_now();" class="orangebutton_big ml20">
								确认购买,去付款
							</button>
						</div>
					</form>
<script type="text/javascript">
<s:if test="order.bizInfo.sellModel!=3">
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
	<s:if test="order.bizInfo.sellModel!=3">
		if(!$("radio_type_1").checked){
			if(!chk_playerRole()){
				return false;
			}
			if(!chk_r_playerRole()){
				return false;
			}
		}
		
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

<s:if test="order.bizInfo.sellModel!=3">
	function chk_radio(index){
		if(index==1){
			$("rad_playerRole").disabled="";
			$("playerRole").disabled="disabled";
			$("r_playerRole").disabled="disabled";
			showDiv("roleTxt1");
			closeDiv("roleTxt2");
		}else{
			$("rad_playerRole").disabled="disabled";
			$("playerRole").disabled="";
			$("r_playerRole").disabled="";
			showDiv("roleTxt2");
			closeDiv("roleTxt1");
		}
	}
</s:if>	
	<s:if test="#session.errorMessage!=null && #session.errorMessage.errorMessage!=''">
		alertDialog('${errorMessage.errorMessage}');
	</s:if>
</script>
				</div>
			</div>
		</div>
	</body>
</html>


