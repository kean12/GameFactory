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
					<strong class="f14">友情提示</strong>
				</div>
				<style>
					<!--
					.ul_list li {
						width: 640px;
						float: left;
						margin: 0 8px;
						overflow:hidden;
					}
					-->
				</style>
				<div class="pd10">
					<ul class="ul_list ptb10 fl">
					<s:if test="assign.order.bizInfo.sellModel==1">
						<li>
							请在游戏中交易地点等待收货：${assign.order.site}
						</li>
						<li>
							卖家游戏角色名为：${assign.order.bizInfo.roleName}
						</li>
					</s:if>
					<s:elseif test="assign.order.bizInfo.sellModel==2">
						<li>
							请在游戏中等待卖家游戏角色添加你为好友
						</li>
						<li>
							卖家游戏角色名为：${assign.order.bizInfo.roleName}
						</li>
					</s:elseif>
						<li>
							交易员联系QQ：${assign.manage.qq}
						</li>
						
					<s:if test="assign.order.state==1">
						<li>
							<span class="fr mr5"><label id="describe">剩余时间</label>:<label id="remaining"></label></span>
							承诺发货时间：${assign.order.bizKind.tradeTime}分钟
						</li>
					</s:if>
					
					<li id="overtimeMess" class="orange" <s:if test="assign.order.overtime!=1">style="display:none;"</s:if>>
						发货超时,我们会在1-3个工作日内调查,如情况属实您将得到10元的赔付金额!(联系客服可以取消此订单)
					</li>
					
					<s:if test="assign.order.state!=1">
						<li class="orange">
							订单号:${assign.order.orderNum}&nbsp;已处理,请到用户中心
							<a class="blue" style="cursor: pointer;" onclick="window.location='${ctx}/user/trade/order/list_bought_items.shtml';">
								"已买到的宝贝&gt;&gt;"
							</a>
							中查看!&nbsp;&nbsp;交易耗时:${assign.order.costTime}
						</li>
					</s:if>
					
					</ul>
				</div>
				<div class="blank5"></div>
			</div>
		</div>
		<div class="blank10 pd10"></div>
	<s:if test="assign.order.state==1">
		<script type='text/javascript' src='${ctx}/dwr/interface/ajaxOrder.js'></script>
		<script type="text/javascript" src="${ctx}/js/index/countdown.js"></script>
    	<script type="text/javascript">
    		var timeAssignId="<s:property value="@com.game.util.web.CryptTool@base64Encode(assign.id+'')" />";
			setExpireDate('${assign.startTime}',<%=System.currentTimeMillis()%>,${assign.order.bizInfo.bizKind.tradeTime},'remaining');
    	</script>
    </s:if>	
	</body>
</html>
