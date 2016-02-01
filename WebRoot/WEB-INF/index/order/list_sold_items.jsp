<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${ctx}/css/style.css"
			rel="stylesheet" type="text/css" />
	</head>
	<body>

		<div class="yhzx_rightside">
			<div class="yhzx_title">
				已卖出的宝贝
			</div>
			<div id="rightnav"><%--0 待付款|1 已付款|2 已发货3 交易关闭|4 退款处理|5 退款完成|6 交易成功|7拒绝客户退款--%>
				<ul>
					<s:if test="type==0">
						<li class="normal_nav" onclick="$('type').value='';document.pageForm.submit();">
							<span>全部</span>
						</li>
						<li class="action_nav" onclick="$('type').value='0';document.pageForm.submit();">
							<span>等待付款</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='1';document.pageForm.submit();">
							<span>等待发货</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='2';document.pageForm.submit();">
							<span>已发货</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='4';document.pageForm.submit();">
							<span>退款中</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='5';document.pageForm.submit();">
							<span>退款成功</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='7';document.pageForm.submit();">
							<span>退款失败</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='6';document.pageForm.submit();">
							<span>成功的订单</span>
						</li>
					</s:if>
					<s:elseif test="type==1">
						<li class="normal_nav" onclick="$('type').value='';document.pageForm.submit();">
							<span>全部</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='0';document.pageForm.submit();">
							<span>等待付款</span>
						</li>
						<li class="action_nav" onclick="$('type').value='1';document.pageForm.submit();">
							<span>等待发货</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='2';document.pageForm.submit();">
							<span>已发货</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='4';document.pageForm.submit();">
							<span>退款中</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='5';document.pageForm.submit();">
							<span>退款成功</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='7';document.pageForm.submit();">
							<span>退款失败</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='6';document.pageForm.submit();">
							<span>成功的订单</span>
						</li>
					</s:elseif>
					<s:elseif test="type==2">
						<li class="normal_nav" onclick="$('type').value='';document.pageForm.submit();">
							<span>全部</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='0';document.pageForm.submit();">
							<span>等待付款</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='1';document.pageForm.submit();">
							<span>等待发货</span>
						</li>
						<li class="action_nav" onclick="$('type').value='2';document.pageForm.submit();">
							<span>已发货</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='4';document.pageForm.submit();">
							<span>退款中</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='5';document.pageForm.submit();">
							<span>退款成功</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='7';document.pageForm.submit();">
							<span>退款失败</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='6';document.pageForm.submit();">
							<span>成功的订单</span>
						</li>
					</s:elseif>
					<s:elseif test="type==4">
						<li class="normal_nav" onclick="$('type').value='';document.pageForm.submit();">
							<span>全部</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='0';document.pageForm.submit();">
							<span>等待付款</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='1';document.pageForm.submit();">
							<span>等待发货</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='2';document.pageForm.submit();">
							<span>已发货</span>
						</li>
						<li class="action_nav" onclick="$('type').value='4';document.pageForm.submit();">
							<span>退款中</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='5';document.pageForm.submit();">
							<span>退款成功</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='7';document.pageForm.submit();">
							<span>退款失败</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='6';document.pageForm.submit();">
							<span>成功的订单</span>
						</li>
					</s:elseif>
					<s:elseif test="type==5">
						<li class="normal_nav" onclick="$('type').value='';document.pageForm.submit();">
							<span>全部</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='0';document.pageForm.submit();">
							<span>等待付款</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='1';document.pageForm.submit();">
							<span>等待发货</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='2';document.pageForm.submit();">
							<span>已发货</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='4';document.pageForm.submit();">
							<span>退款中</span>
						</li>
						<li class="action_nav" onclick="$('type').value='5';document.pageForm.submit();">
							<span>退款成功</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='7';document.pageForm.submit();">
							<span>退款失败</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='6';document.pageForm.submit();">
							<span>成功的订单</span>
						</li>
					</s:elseif>
					<s:elseif test="type==6">
						<li class="normal_nav" onclick="$('type').value='';document.pageForm.submit();">
							<span>全部</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='0';document.pageForm.submit();">
							<span>等待付款</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='1';document.pageForm.submit();">
							<span>等待发货</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='2';document.pageForm.submit();">
							<span>已发货</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='4';document.pageForm.submit();">
							<span>退款中</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='5';document.pageForm.submit();">
							<span>退款成功</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='7';document.pageForm.submit();">
							<span>退款失败</span>
						</li>
						<li class="action_nav" onclick="$('type').value='6';document.pageForm.submit();">
							<span>成功的订单</span>
						</li>
					</s:elseif>
					<s:elseif test="type==7">
						<li class="normal_nav" onclick="$('type').value='';document.pageForm.submit();">
							<span>全部</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='0';document.pageForm.submit();">
							<span>等待付款</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='1';document.pageForm.submit();">
							<span>等待发货</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='2';document.pageForm.submit();">
							<span>已发货</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='4';document.pageForm.submit();">
							<span>退款中</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='5';document.pageForm.submit();">
							<span>退款成功</span>
						</li>
						<li class="action_nav" onclick="$('type').value='7';document.pageForm.submit();">
							<span>退款失败</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='6';document.pageForm.submit();">
							<span>成功的订单</span>
						</li>
					</s:elseif>
					<s:else>
						<li class="action_nav" onclick="$('type').value='';document.pageForm.submit();">
							<span>全部</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='0';document.pageForm.submit();">
							<span>等待付款</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='1';document.pageForm.submit();">
							<span>等待发货</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='2';document.pageForm.submit();">
							<span>已发货</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='4';document.pageForm.submit();">
							<span>退款中</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='5';document.pageForm.submit();">
							<span>退款成功</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='7';document.pageForm.submit();">
							<span>退款失败</span>
						</li>
						<li class="normal_nav" onclick="$('type').value='6';document.pageForm.submit();">
							<span>成功的订单</span>
						</li>
					</s:else>
				</ul>
			</div>
			<div class="sale_list_title">
				<div class="sale_u3">
					信息标题、物品类型、游戏/区/服
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
				<div class="clear"></div>
			</div>
			<!--list -->
		<s:iterator value="orderList">
			<div class="sale_list">
				<div class="sale_u0">
					<span class="ml20 gray">编号：${orderNum}</span><span class="ml20 gray">下单时间：${orderTime}</span><span class="ml20 gray">买家：${consumer.username}</span>
				</div>
				<div class="sale_u3">
					<a href="${ctx}/user/trade/bizInfo/detail.shtml?bizInfoID=${bizInfo.id}" class="blue">${title }</a>
					<br />
				</div>
				<div class="sale_u4 orange">
					${price}
				</div>
				<div class="sale_u5">
					${buyBum}
				</div>
				<div class="sale_u4 orange">
					${sumPrice}
				</div>
				<div class="sale_u6">
					<s:if test="succTime!=null">
						${succTime}
					</s:if>
					<s:else>
						--
					</s:else>
				</div>
				
			<s:if test="buyType==1"><%--寄售 --%>
				<div class="sale_u81">
					<%--0 待付款|1 已付款|2 已发货3 交易关闭|4 退款处理|5 退款完成|6 交易成功--%>
					<s:if test="state==1">
						<a style="cursor: pointer;" onclick="detail('${id}');" class="orange">等待交易员发货</a><br /><span class="orange">已付款</span>
					</s:if>
					<s:elseif test="state==2">
						<a style="cursor: pointer;" onclick="detail('${id}');" class="orange">已发货</a>
					</s:elseif>
					<s:elseif test="state==3">
						<a style="cursor: pointer;" onclick="detail('${id}');" class="ml20 gray">交易关闭</a>
					</s:elseif>
					<s:elseif test="state==4">
						<a style="cursor: pointer;" onclick="detail('${id}');" class="orange">退款处理中</a>
					</s:elseif>
					<s:elseif test="state==5">
						<a style="cursor: pointer;" onclick="detail('${id}');" class="gray">退款成功</a>
					</s:elseif>
					<s:elseif test="state==6">
						<a style="cursor: pointer;" onclick="detail('${id}');" class="green">交易完成</a>
					</s:elseif>
					<s:elseif test="state==7">
						<span class="orange">拒绝客户退款</span>
					</s:elseif>
					<s:else>
						<a style="cursor: pointer;" onclick="detail('${id}');" class="orange">等待买家付款</a>
					</s:else>
					<br />
					<a style="cursor: pointer;" onclick="detail('${id}');" class="blue">详情</a>
				</div>
			</s:if>
			<s:else><%--其他 --%>
				<div class="sale_u81">
					<%--0 待付款|1 已付款|2 已发货3 交易关闭|4 退款处理|5 退款完成|6 交易成功--%>
					<s:if test="state==1">
						<button class="abutton2" onclick="chk_state('${id}','2','${type}','${goPage}');">发货</button><br /><span class="orange">已付款</span>
					</s:if>
					<s:elseif test="state==2">
						<a style="cursor: pointer;" onclick="detail('${id}');" class="orange">已发货</a>
					</s:elseif>
					<s:elseif test="state==3">
						<a style="cursor: pointer;" onclick="detail('${id}');" class="ml20 gray">交易关闭</a>
					</s:elseif>
					<s:elseif test="state==4">
						<a style="cursor: pointer;" onclick="detail('${id}');" class="orange">退款处理中</a>
					</s:elseif>
					<s:elseif test="state==5">
						<a style="cursor: pointer;" onclick="detail('${id}');" class="gray">退款成功</a>
					</s:elseif>
					<s:elseif test="state==6">
						<a style="cursor: pointer;" onclick="detail('${id}');" class="green">交易完成</a>
					</s:elseif>
					<s:elseif test="state==7">
						<span class="orange">拒绝客户退款</span>
					</s:elseif>
					<s:else>
						<a style="cursor: pointer;" onclick="detail('${id}');" class="orange">等待买家付款</a>
					</s:else>
					<br />
					<a style="cursor: pointer;" onclick="detail('${id}');" class="blue">详情</a>
				</div>
			</s:else>	
				
				<div class="sale_u71">
					<s:if test="state==6">
						<s:if test="isAssess==1">
							<a style="cursor: pointer;" onclick="detail('${id}');" class="blue">评价</a>
						</s:if>
						<s:if test="isAssess==2">
							<a class="orange">对方已评</a>
							<br/>
							<a style="cursor: pointer;" onclick="detail('${id}');" class="blue">评价</a>
						</s:if>
						<s:if test="isAssess==3">
							<a class="orange">对方未评</a>
						</s:if>
						<s:if test="isAssess==4">
							<a class="orange">双方都已评</a>
						</s:if>
					</s:if>
				</div>
				<div class="clear"></div>
			</div>
		</s:iterator>
		
		<script type="text/javascript" src="${ctx}/js/index/order.js"></script>
			<form id="form1" name="form1" action="" method="post">
				<input type="hidden" id="orderID" name="orderID" />
				<input type="hidden" id="state" name="state" />
			</form>	
			<!--list end -->
			
		<%-- 分页  --%>
			<div class="manu">
				<jsp:include page="/WEB-INF/index/common/pagination.jsp"></jsp:include>
				<form name="pageForm" action="${ctx}/user/trade/order/list_sold_items.shtml" method="get">
					<input type="hidden" id="goPage" name="goPage" />
					<input type="hidden" id="type" name="type" value="${type}" />
				</form>
			</div>
		<%-- 分页END  --%>
		</div>
		<div class="clear"></div>
	</body>
</html>

