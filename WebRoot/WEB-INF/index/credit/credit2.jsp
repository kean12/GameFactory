<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/index/common/taglibs.jsp"%>
<script type="text/javascript">
<!--
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->
</script>
<!--menu end -->
<div class="contaner tc">
  <div class=" ptb10">您的位置：<a href="${ctx}/index.shtml" class="blue">首页</a> &gt; <a href="${ctx}/user/home.shtml" class="blue">我的买卖</a> &gt; 用户信用及评价</div>
  <div class="attention bs_ccc"><strong class="red">昵称：${user.username}</strong> 注册时间：${user.regTime}</div>
  <div class="blank10"></div>
  <div class="pj_table_list">
    <div class="rank">
    	<strong class="f14">卖家信用等级</strong>
    		<em class="date red mr5">${user.userInfo.sellerCredit}</em>
    			<s:if test="user.userInfo.sellerGrade>0">
					<span class="srank-sale-${user.userInfo.sellerGrade}">&nbsp;</span>
				</s:if>
		卖家好评率：<em class="date red"><s:property value="user.userInfo.sellerPositiveRatio" default="--"/></em>
	</div>
 <table>
  <tr>
    <th>&nbsp;</th>
    <th>总计</th>
    <th>最近1周</th>
    <th>最近1个月</th>
    <th>最近6个月</th>
  </tr>
  <tr>
    <td><strong class="red">好评</strong></td>
    <td><strong class="red">${creditCount.sellerAllHP}</strong></td>
    <td>${creditCount.sellerWeekHP}</td>
    <td>${creditCount.sellerMonthHP}</td>
    <td>${creditCount.sellerYearHP}</td>
  </tr>
  <tr>
    <td><strong class="orange">中评</strong></td>
    <td><strong class="orange">${creditCount.sellerAllZP}</strong></td>
    <td>${creditCount.sellerWeekZP}</td>
    <td>${creditCount.sellerMonthZP}</td>
    <td>${creditCount.sellerYearZP}</td>
  </tr>
  <tr>
    <td><strong class=" green">差评</strong></td>
    <td><strong class=" green">${creditCount.sellerAllCP}</strong></td>
    <td>${creditCount.sellerWeekCP}</td>
    <td>${creditCount.sellerMonthCP}</td>
    <td>${creditCount.sellerYearCP}</td>
  </tr>
</table>
</div>
  <div class="pj_table_list ml10">
   <div class="rank">
    	<strong class="f14">买家信用等级</strong>
    	<em class="date red mr5">${user.userInfo.buyerCredit}</em>
    	<s:if test="user.userInfo.buyerGrade>0">
			<span class="srank-buy-${user.userInfo.buyerGrade}">&nbsp;</span>
		</s:if>
    	买家好评率：<em class="date red"><s:property value="user.userInfo.buyerPositiveRatio" default="--" /></em>
    </div>
  <table>
  <tr>
    <th>&nbsp;</th>
    <th>总计</th>
    <th>最近1周</th>
    <th>最近1个月</th>
    <th>最近6个月</th>
  </tr>
  <tr>
    <td><strong class="red">好评</strong></td>
    <td><strong class="red">${creditCount.buyerAllHP}</strong></td>
    <td>${creditCount.buyerWeekHP}</td>
    <td>${creditCount.buyerMonthHP}</td>
    <td>${creditCount.buyerYearHP}</td>
  </tr>
  <tr>
    <td><strong class="orange">中评</strong></td>
    <td><strong class="orange">${creditCount.buyerAllZP}</strong></td>
    <td>${creditCount.buyerWeekZP}</td>
    <td>${creditCount.buyerMonthZP}</td>
    <td>${creditCount.buyerYearZP}</td>
  </tr>
  <tr>
    <td><strong class=" green">差评</strong></td>
    <td><strong class=" green">${creditCount.buyerAllCP}</strong></td>
    <td>${creditCount.buyerWeekCP}</td>
    <td>${creditCount.buyerMonthCP}</td>
    <td>${creditCount.buyerYearCP}</td>
  </tr>
</table>
</div>
<%--   <div class="clear pd10"><a href="#" target="_blank" class="blue_u f12">◆信用等级如何划分？</a><a href="#" target="_blank"  class="blue_u ml10 f12">◆为什么评价没有计分？</a><a href="#" target="_blank"  class="blue_u ml10 f12">◆什么是信用炒作？</a><a href="#" target="_blank"  class="blue_u ml10 f12">◆如何进行修改评价？</a><a href="#" target="_blank"  class="blue_u ml10 f12">◆有中差评怎么办？</a></div>--%>
  <div id="rightnav">
    <ul>
      <li class="normal_nav" onclick="javascript:window.location='${ctx}/user/trade/credit/userCredit1.shtml?userID=${user.id}&type=1'"><span>来自买家的评价</span></li>
      <li class="action_nav" onclick="javascript:window.location='${ctx}/user/trade/credit/userCredit2.shtml?userID=${user.id}&type=0'"><span>来自卖家的评价</span></li>
      <li class="normal_nav" onclick="javascript:window.location='${ctx}/user/trade/credit/userCredit3.shtml?userID=${user.id}&isSeller=3'"><span>给他人的评价</span></li>
    </ul>
  </div>
  <div class="sale_list_title">
  <div class="sale_u41">评价
    <s:select name="grade" list="#{'':'所有评价','1':'只看好评','0':'只看中评','-1':'只看差评'}" listKey="key" listValue="value" multiple="simple" onchange="ping(this.options[this.options.selectedIndex].value)"></s:select>
  </div>
  <div class="sale_u31">评价内容</div>
  <div class="sale_u3 ml10">买家 | 商品</div>
  <div class="clear"></div>
</div>
<!--list -->

<div class="sale_list">
	<s:iterator value="sellerAssess">
		<s:if test="grade==1">
			<div class="sale_u41 red">【好评】 +1分</div>
			<div class="sale_u31">${content}
				<s:if test="null!=time&&time.toString().length()>19">
	            	<div class="date gray"><s:property value="time.toString().substring(0,19)"/></div>
	            </s:if>
	            <s:else>
	                <div class="date gray">${time}</div>
	            </s:else>
			</div>
			<div class="sale_u3 ml10">
				<ul>
					<li>买家：${initiative.username}　信用：${initiative.userInfo.buyerCredit}<span class="srank-buy-3">&nbsp;</span> </li>
					<li>商品：<a href="${ctx}/user/trade/bizInfo/detail.shtml?bizInfoID=${order.bizInfo.id}" class="blue" target="_blank">${order.title}</a></li>
					<li>价格：<em class="red">${order.price}</em>元</li>
				</ul>
			</div>
			<div class="clear"></div>
		</s:if>
		<s:if test="grade==0">
			<div class="sale_u41 orange">【中评】 不加分</div>
			<div class="sale_u31">${content}
				<s:if test="null!=time&&time.toString().length()>19">
	            	<div class="date gray"><s:property value="time.toString().substring(0,19)"/></div>
	            </s:if>
	            <s:else>
	                <div class="date gray">${time}</div>
	            </s:else>
			</div>
			<div class="sale_u3 ml10">
				<ul>
					<li>买家：${initiative.username}　信用：${initiative.userInfo.buyerCredit}<span class="srank-buy-3">&nbsp;</span> </li>
					<li>商品：<a href="${ctx}/user/trade/bizInfo/detail.shtml?bizInfoID=${order.bizInfo.id}" class="blue" target="_blank">${order.title}</a></li>
					<li>价格：<em class="red">${order.price}</em>元</li>
				</ul>
			</div>
			<div class="clear"></div>
		</s:if>
		<s:if test="grade==-1">
			<div class="sale_u41 green">【差评】 -1分</div>
			<div class="sale_u31">${content}
				<s:if test="null!=time&&time.toString().length()>19">
	            	<div class="date gray"><s:property value="time.toString().substring(0,19)"/></div>
	            </s:if>
	            <s:else>
	                <div class="date gray">${time}</div>
	            </s:else>
			</div>
			<div class="sale_u3 ml10">
				<ul>
					<li>买家：${initiative.username}　信用：${initiative.userInfo.buyerCredit}<span class="srank-buy-3">&nbsp;</span> </li>
					<li>商品：<a href="${ctx}/user/trade/bizInfo/detail.shtml?bizInfoID=${order.bizInfo.id}" class="blue" target="_blank">${order.title}</a></li>
					<li>价格：<em class="red">${order.price}</em>元</li>
				</ul>
			</div>
			<div class="clear"></div>
		</s:if>
	</s:iterator>
</div>

<!--list end -->

<div class="manu">
	<jsp:include page="/WEB-INF/index/common/pagination.jsp"></jsp:include>
	<form id="pageForm" name="pageForm" action="${ctx}/user/trade/credit/userCredit2.shtml?userID=${user.id}&type=0&grade=${grade}" method="post">
		<input type="hidden" id="goPage" name="goPage" />
	</form>
</div>

</div>
<script type="text/javascript">
function ping(grade)
{
	window.location.href="${ctx}/user/trade/credit/userCredit2.shtml?&type=0&userID=${user.id}&grade="+grade;
}
</script>
