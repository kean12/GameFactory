<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
<script type="text/javascript">
	function showmain(obj) {
		var menu_num = document.getElementById("rightnav")
				.getElementsByTagName("li");
		for (i = 0; i < menu_num.length; i++) {
			menu_num[i].className = i == obj ? "action_nav" : "normal_nav";
			document.getElementById('box' + i).style.display = i == obj ? "block"
					: "none";
		}
	}
</script>
<script type="text/javascript" src="${ctx}/js/index/order.js"></script>
			<form id="form1" name="form1" action="" method="post">
				<input type="hidden" id="orderID" name="orderID" />
				<input type="hidden" id="state" name="state" />
				<input type="hidden" id="toPage" name="goPage" value="${goPage }" />
			</form>		

		<div class="contaner tc">
			<div class=" ptb10">
				您的位置：
				<a href="${ctx}/index.shtml" class="blue">首页</a> >
				<a href="${ctx}/user/home.shtml" class="blue">用户中心</a> >
				<s:if test="kind==1"><a href="${ctx}/user/trade/order/list_sold_items.shtml" class="blue">已卖出的宝贝</a></s:if>
				<s:elseif test="kind==0"><a href="${ctx}/user/trade/order/list_bought_items.shtml" class="blue">已买入的宝贝</a></s:elseif>
				<s:else><script type="text/javascript">window.location="${ctx}/user/home.shtml";</script></s:else>
			</div>
			<div class="bs_ccc tl fb_success">
				<span class="red">
					当前订单状态：
					<s:if test="order.state==1">
						<s:if test="order.buyType==1">
							买家已付款，等待交易员发货
						</s:if>
						<s:else>
							买家已付款，等待商家发货
						</s:else>
					</s:if>
					<s:elseif test="order.state==2">
						<s:if test="order.buyType==1">
							交易员已发货，等待买家确认。
						</s:if>
						<s:else>
							商家已发货，等待买家确认。
						</s:else>
					</s:elseif>
					<s:elseif test="order.state==3">
						交易关闭
					</s:elseif>
					<s:elseif test="order.state==4">
						退款处理中
					</s:elseif>
					<s:elseif test="order.state==5">
						退款成功
					</s:elseif>
					<s:elseif test="order.state==6">
						<span class="green">交易完成</span>
					</s:elseif>
					<s:elseif test="order.state==7">
						<span>拒绝退款</span>
					</s:elseif>
					<s:else>
						等待买家付款
					</s:else>
				</span>
				<br />
				<br />
				
			<s:if test="order.buyType==1"><%--寄售 --%>
				<span>
					<s:if test="order.state==1 && kind==1"></s:if>
					<s:elseif test="order.state==2 && kind==0">
						<button class="abutton3" onclick="chk_state('${order.id}','2');">确认收货</button>
					</s:elseif>
					<s:elseif test="order.state==3"></s:elseif>
					<s:elseif test="order.state==4 && kind==1"></s:elseif>
					<s:elseif test="order.state==5"></s:elseif>
					<s:elseif test="order.state==6"></s:elseif>
					<s:elseif test="order.state==7"></s:elseif>
					<s:elseif test="order.state==0 && kind==0">
						<button class="abutton2" onclick="">付款</button>
					</s:elseif>
				</span>
			</s:if>
			<s:else>
				<span>
					<s:if test="order.state==1 && kind==1">
						<button class="abutton2" onclick="chk_state('${order.id}','2');">发货</button>
					</s:if>
					<s:elseif test="order.state==2 && kind==0">
						<button class="abutton3" onclick="chk_state('${order.id}','2');">确认收货</button>
					</s:elseif>
					<s:elseif test="order.state==3"></s:elseif>
					<s:elseif test="order.state==4 && kind==1">
						<button class="abutton3" onclick="chk_state('${order.id}','5');">同意退款</button>&nbsp;&nbsp;
						<button class="abutton3" onclick="chk_state('${order.id}','7');">拒绝退款</button>
					</s:elseif>
					<s:elseif test="order.state==5"></s:elseif>
					<s:elseif test="order.state==6"></s:elseif>
					<s:elseif test="order.state==7"></s:elseif>
					<s:elseif test="order.state==0 && kind==0">
						<button class="abutton2" onclick="">付款</button>
					</s:elseif>
				</span>
			</s:else>
			
						<%--0 待付款|1 已付款|2 已发货3 交易关闭|4 退款处理|5 退款完成|6 交易成功--%>
			</div>
			
			<%--评价 start--%>
			<s:if test="order.state==6 && (order.isAssess==2 || order.isAssess==1)">
			<div class="contaner tc">
			<div class="blank10"></div>
			<div class="bs_ccc pd10">
			<div class="bbd_ccc ptb5"><span class="ico_7">&nbsp;</span> <strong class="f14">我要评价</strong></div>
			<div class="blank10"></div>
			<form action="${ctx}/user/trade/assess/assess.shtml" method="post">
				<input type="hidden" name="orderID" value="${order.id}"/>
				<s:radio list="#{1:'好评',0:'中评',-1:'差评'}" name="assess.grade" cssClass="vam" listKey="key" listValue="value" theme="simple"></s:radio>
				<textarea name="assess.content" cols="100" rows="5"></textarea><br />
				<button class="abutton31" type="submit">确认</button>
			</form>
			</div>
			</div>
			</s:if>
			<%--评价 end--%>
			
			<div id="rightnav">
				<ul>
					<li class="action_nav" onclick="showmain(0);">
						<span>订单信息</span>
					</li>
					<li class="normal_nav" onclick="showmain(1);">
						<span>收货信息</span>
					</li>
				</ul>
			</div>
			<div class="contentbox" id="box0" style="display: block">
			<s:if test="kind==1"><%-- 买家信息 --%>
				<div class="sale_list_title">
					<strong class="f14">&nbsp;&nbsp;买家信息</strong>
				</div>
				<ul class="ul_list ptb10 fl" style="width: 460px">
					<li>用户名：${order.consumer.username }</li>
					<li>邮箱：${order.consumer.email }</li>
				</ul>
				<ul class="ul_list ptb10 fl" style="width:460px">
			 		<li>联系QQ：${order.playQQ}</li>
			 		<li>联系电话：${order.playPhoneNum }</li>
			 	</ul>
				<div class="clear"></div>
			</s:if>
			<s:elseif test="kind==0"><%-- 商家信息 --%>
				<div class="sale_list_title">
					<strong class="f14">&nbsp;&nbsp;卖家信息</strong>
				</div>
				<ul class="ul_list ptb10 fl" style="width: 460px">
					<li>用户名：${order.owner.username }</li>
					<li>邮箱：${order.owner.email }</li>
				</ul>
				<ul class="ul_list ptb10 fl" style="width:460px">
			 		<li>联系QQ：${order.sellQQ }</li>
			 		<li>联系电话：${order.sellPhoneNum }</li>
			 	</ul>
				<div class="clear"></div>
			</s:elseif>	
			<div class="sale_list_title">
				<div class="sale_u3">
					&nbsp;&nbsp;信息标题、物品类型、游戏/区/服
				</div>
				<div class="sale_u4">金额</div>
				<div class="sale_u5">
					件数
				</div>
				<div class="sale_u4">总价</div>
				<div class="sale_u6">
					成交时间
				</div>
				<div class="sale_u81">
					操作
				</div>
				<div class="sale_u71">
					评价
				</div>
			</div>
				<!--list -->
			<div class="sale_list">
			<%-- 卖家 --%>
				<s:if test="kind==1">
					<div class="sale_u0">
						<span class="ml20 gray">编号：${order.orderNum}</span><span class="ml20 gray">下单时间：${order.orderTime}</span><span class="ml20 gray">买家：${order.consumer.username}</span>
					</div>
					<div class="sale_u3">
						<a href="${ctx}/user/trade/bizInfo/detail.shtml?bizInfoID=${order.bizInfo.id}" class="blue">${order.title }</a>
						<br />
					</div>
					<div class="sale_u4 orange">
						${order.price}
					</div>
					<div class="sale_u5">
						${order.buyBum}
					</div>
					<div class="sale_u4 orange">
						${order.sumPrice}
					</div>
					<div class="sale_u6">
						<s:if test="succTime!=null">
							${order.succTime}
						</s:if>
						<s:else>
							--
						</s:else>
					</div>
					<div class="sale_u81">
						<%--0 待付款|1 已付款|2 已发货3 交易关闭|4 退款处理|5 退款完成|6 交易成功--%>
						<s:if test="order.buyType==1"><%--寄售 --%>
							<s:if test="order.state==1">
								<span class="orange">等待交易员发货</span><br /><span class="orange">已付款</span>
							</s:if>
							<s:elseif test="order.state==2">
								<span class="orange">已发货</span>
							</s:elseif>
							<s:elseif test="order.state==3">
								<span class="gray">交易关闭</span>
							</s:elseif>
							<s:elseif test="order.state==4">
								<span class="orange">退款处理中</span>
							</s:elseif>
							<s:elseif test="order.state==5">
								<span class="gray">退款成功</span>
							</s:elseif>
							<s:elseif test="order.state==6">
								<span class="green">交易完成</span>
							</s:elseif>
							<s:else>
								<span class="orange">等待买家付款</span>
							</s:else>
						</s:if>
						<s:else>
							<s:if test="order.state==1">
								<button class="abutton2" onclick="chk_state('${order.id}','2');">发货</button><br /><span class="orange">已付款</span>
							</s:if>
							<s:elseif test="order.state==2">
								<span class="orange">已发货</span>
							</s:elseif>
							<s:elseif test="order.state==3">
								<span class="gray">交易关闭</span>
							</s:elseif>
							<s:elseif test="order.state==4">
								<span class="orange">退款处理中</span>
							</s:elseif>
							<s:elseif test="order.state==5">
								<span class="gray">退款成功</span>
							</s:elseif>
							<s:elseif test="order.state==6">
								<span class="green">交易完成</span>
							</s:elseif>
							<s:else>
								<span class="orange">等待买家付款</span>
							</s:else>
						</s:else>
						<br />
					</div>
					
					<div class="sale_u71">
						<s:if test="order.state==6">
							<s:if test="order.isAssess==1">
								<a style="cursor: pointer;"  class="orange">双方都未评价</a><br />
								<a style="cursor: pointer;"  class="blue">评价</a>
							</s:if>
							<s:if test="order.isAssess==2">
								<a style="cursor: pointer;"  class="orange">对方已评</a>
								<br/>
								<a style="cursor: pointer;"  class="blue">评价</a>
							</s:if>
							<s:if test="order.isAssess==3">
								<a style="cursor: pointer;"  class="orange">对方未评</a>
							</s:if>
							<s:if test="order.isAssess==4">
								<a style="cursor: pointer;"  class="orange">双方都已评</a>
							</s:if>
						</s:if>
					</div>
				</s:if>
			<%-- 买家 --%>
				<s:elseif test="kind==0">
					<div class="sale_u0">
						<span class="ml20 gray">编号：${order.orderNum}</span><span class="ml20 gray">下单时间：${order.orderTime}</span><span class="ml20 gray">卖家：${order.owner.username}</span>
					</div>
					<div class="sale_u3">
						<a href="${ctx}/user/trade/bizInfo/detail.shtml?bizInfoID=${order.bizInfo.id}" class="blue">${order.title }</a>
						<br />
					</div>
					<div class="sale_u4 orange">
						${order.price}
					</div>
					<div class="sale_u5">
						${order.buyBum}
					</div>
					<div class="sale_u4 orange">
						${order.sumPrice}
					</div>
					<div class="sale_u6">
						<s:if test="succTime!=null">
							${order.succTime}
						</s:if>
						<s:else>
							--
						</s:else>
					</div>
					<div class="sale_u81">
						<%--0 待付款|1 已付款|2 已发货3 交易关闭|4 退款处理|5 退款完成|6 交易成功--%>
						<s:if test="order.state==1">
							<span class="orange">已付款</span><br />
							<span class="orange">
							<s:if test="order.buyType==1">
								等待交易员发货
							</s:if>
							<s:else>
								等待商家发货
							</s:else>
							</span>
						</s:if>
						<s:elseif test="order.state==2">
							<button class="abutton3" onclick="chk_state('${order.id}','6');">确认收货</button>
						</s:elseif>
						<s:elseif test="order.state==3">
							<span class="gray">交易关闭</span>
						</s:elseif>
						<s:elseif test="order.state==4">
							<span class="orange">退款处理中</span>
						</s:elseif>
						<s:elseif test="order.state==5">
							<span class="gray">退款成功</span>
						</s:elseif>
						<s:elseif test="order.state==6">
							<span class="green">交易完成</span>
						</s:elseif>
						<s:else>
							<button class="abutton2" onclick="chk_pay('${order.id}');">付款</button>
						</s:else>
						<br />
					</div>
					<div class="sale_u71">
						<s:if test="order.state==6">
							<s:if test="order.isAssess==1">
								<a style="cursor: pointer;" class="orange">双方都未评价</a><br />
								<a style="cursor: pointer;" class="blue">评价</a>
							</s:if>
							<s:if test="order.isAssess==2">
								<a style="cursor: pointer;" class="orange">对方已评</a>
								<br/>
								<a style="cursor: pointer;" class="blue">评价</a>
							</s:if>
							<s:if test="order.isAssess==3">
								<a style="cursor: pointer;" class="orange">对方未评</a>
							</s:if>
							<s:if test="order.isAssess==4">
								<a style="cursor: pointer;" class="orange">双方都已评</a>
							</s:if>
						</s:if>
					</div>
				</s:elseif>
					<div class="clear"></div>
				</div>
				<!--list end -->
			</div>
			
			<div class="contentbox" id="box1">
				<ul class="ul_list ptb10 fl" style="width: 460px">
					<li>收货角色名：${order.playerRole }</li>
					<li>收货角色等级：${order.playerGrade }</li>
				</ul>
				<ul class="ul_list ptb10 fl" style="width:460px">
					<li>联系QQ：${order.playQQ }</li>
			 		<li>联系电话：${order.playPhoneNum }</li>
			 	</ul>
				<div class="clear"></div>
			</div>
			
			<s:if test="order.reason!=null">
				<div class="pd10">
					<div class="sale_list_title f14">&nbsp;&nbsp;退款理由:</div>
					<div class="bs_ccc tl pd10 mt8">
						${order.reason }
					</div>
					
					<div class="sale_list_title f14">&nbsp;&nbsp;处理结果:</div>
					<div class="bs_ccc tl pd10 mt8">
						<s:if test="order.outcome==null">
							<span class="red">正在处理中，请耐心等待。</span>
						</s:if>
						<s:else>
							${order.outcome }
						</s:else>
						
					</div>
				</div>
			</s:if>
			
			
			
		</div>
	</body>
</html>

