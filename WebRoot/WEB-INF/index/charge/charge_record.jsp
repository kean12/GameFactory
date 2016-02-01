<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
		<div class="yhzx_rightside">
			<div class="yhzx_title">
				<a href="${ctx}/index.shtml">首页</a>&gt;<a href="${ctx}/user/home.shtml">用户中心</a>&gt;充值记录
			</div>
			<style>
			.sale_list_lead div{background:none; border:0}
			</style>
			<div class="sale_list_lead mt10" style=" background:#E1F0FF; padding-bottom:5px">
				<form name="particulars_search" action="${ctx}/user/charge/record.shtml">
					<div>
				  		商户订单号：<input name="orderNum" type="text" id="orderNum" value="${orderNum}" size="12" />
						业务流水号：<input name="runningNum" type="text" id="runningNum" value="${runningNum}" size="16" />
						交易场所：<input name="bank" type="text" id="bank" value="${bank}" size="16" />
					</div>
					<div id="orderList" class="sBox">
						起始日期：<input class="ipt-date to" name="beginTime" value="${beginTime}" readonly/>
						终止日期：<input class="ipt-date to" name="endTime" value="${endTime}" readonly/>
						<button type="submit" class="abutton2 ml10" onclick="
							$('orderNum').value=encodeURIComponent($('orderNum').value);
							$('runningNum').value=encodeURIComponent($('runningNum').value);
							$('bank').value=encodeURIComponent($('bank').value);
						"> 查询 </button>
					</div>
				</form>
			</div>
			<div class="blank10"></div>
			<div class="sale_list_title">
				<div class="wb11 tc">
					商户订单号
				</div>
				<div class="wb17 tc">
					业务流水号
				</div>
				<div class="wb15 tc">
					日期
				</div>
				<div class="wb15 tc">
					交易场所
				</div>
				<div class="wb8 tc">
					类型
				</div>
				<div class="wb8 tc">
					充值金额
				</div>
				<div class="wb8 tc">
					账户余额
				</div>
				<div class="wb4 tc">
					详情
				</div>
				<%--<div class="wb4 tc">
					备注
				</div>
				--%><div class="clear"></div>
			</div>
		<s:iterator value="particularsList">
			<!--list -->
			<div class="sale_list">
				<div class="wb11">
					${orderNum }
				</div>
				<div class="wb17">
					${runningNum }
				</div>
				<div class="wb15">
					<s:property value="time.substring(0,19)" />
				</div>
				<div class="wb15 tc">
					${bank }
				</div>
				<div class="wb8 tc">
					<s:if test="type==1">
						交易付款
					</s:if>
					<s:elseif test="type==2">
						交易收款
					</s:elseif>
					<s:elseif test="type==3">
						充值
					</s:elseif>
					<s:elseif test="type==4">
						退款
					</s:elseif>
					<s:elseif test="type==5">
						提现
					</s:elseif>
					<s:elseif test="type==6">
						差额入账
					</s:elseif>
				</div>
				<div class="wb8 tc">
					<s:if test="income!=null">${income}元</s:if>
				</div>
				<div class="wb8 tc">
					${money}元
				</div>
				<div class="wb4 tc">
					<a href="#" class="blue_u" title="${synopsis}">详情</a>
				</div>
				<%--<div class="wb4 tc">
					<span title="${remark }">备注</span>
				</div>
				--%><div class="clear"></div>
			</div>
			<!--list end -->
		</s:iterator>
			<div class="manu">
				<jsp:include page="/WEB-INF/index/common/pagination.jsp"></jsp:include>
				<form name="pageForm" action="${ctx}/user/charge/record.shtml" method="get">
					<input type="hidden" id="goPage" name="goPage" value="${goPage }" />
					<input type="hidden" id="page_orderNum" name="orderNum" value=""  />
					<input type="hidden" id="page_runningNum" name="runningNum" value="" />
					<input type="hidden" id="page_bank" name="bank" value="" />
					<input type="hidden" name="beginTime" value="${beginTime }" />
					<input type="hidden" name="endTime" value="${endTime }" />
				</form>
				<script type="text/javascript">
					$("page_orderNum").value=encodeURIComponent('${orderNum }');
					$("page_runningNum").value=encodeURIComponent('${runningNum }');
					$("page_bank").value=encodeURIComponent('${bank }');
				</script>
			</div>
		</div>
		<div class="clear"></div>
	</body>
</html>


