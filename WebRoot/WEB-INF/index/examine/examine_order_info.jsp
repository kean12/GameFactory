<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
		<div class="blank10 pd10"></div>
		<div class="contaner tc" style="width: 700px">
			<div class="bs_ccc pd10">
				<div class="bbd_ccc ptb5">
					<span class="ico_7">&nbsp;</span>
					<strong class="f14">商品信息</strong>
				</div>
				<style>
					<!--
					.ul_list li {
						width: 305px;
						float: left;
						margin: 0 8px;
						overflow:hidden
					}
					-->
				</style>
				<div class="pd10">
				<ul class="ul_list ptb10 fl">
					<li>
						订单号：${order.orderNum}
					</li>
					<li>
						标题：${order.title}
					</li>
					<li>
						物品单价：${order.price}
					</li>
					<li>
						购买数量：${order.buyBum}
					</li>
					<li>
						总价格：${order.sumPrice}
					</li>
					<li>
						交易地点：${order.site}
					</li>
				<s:if test="order.bizInfo.sellModel!=3">
					<li>
						收获角色名：${order.playerRole}
					</li>
					<li>
						角色等级：${order.playerGrade}
					</li>
				</s:if>	
					<li>
						联系QQ：${order.playQQ}
					</li>
					<li>
						联系电话：${order.playPhoneNum}
					</li>
					<li style="width:630px; overflow:hidden">
						<s:if test="order.game!=null">
						
						</s:if>
						<s:else>
							所属游戏区服：${order.server.area.game.gameName}/${order.server.area.areaName}/${order.server.serverName}
						</s:else>
					</li>
				</ul>
				</div>
				<div class="blank5"></div>
				<div class=" bs_ccc pd10 orange">
					以上商品信息是否正确，如果正确，请点击
					<a style="cursor: pointer;" class="blue" onclick="window.location='${ctx}/user/trade/order/examine/examine_order_next.shtml?orderNum=${order.orderNum}'">下一步&gt;&gt;</a>； 如果不正确，请点击
					<a style="cursor: pointer;" class="blue" onclick="window.location='${ctx}/user/trade/order/examine/examine_order_amend.shtml?orderNum=${order.orderNum}'">修改订单&gt;&gt;</a>
				</div>
			</div>
		</div>
		<div class="blank10 pd10"></div>
	</body>
</html>
