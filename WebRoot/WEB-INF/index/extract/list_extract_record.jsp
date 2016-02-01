<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
		<div class="yhzx_rightside">
			<div class="yhzx_title">
				我要提现&gt;提现记录
			</div>
			<div class="blank10"></div>
			<style>
			.sale_list_lead div{background:none; border:0}
			</style>
			<div class="sale_list_lead mt10" style=" background:#E1F0FF; padding-bottom:5px">
				<form name="record_search" action="${ctx}/user/extract/list_extract_record.shtml">
					<div>
						提现银行：<input name="bank" type="text" id="bank" value="${bank}" size="16" />
						银行账号：<input name="account" type="text" id="account" value="${account}" size="16" />
						状态：<s:select name="state" list="#{'':'所有状态','0':'申请中','1':'成功','-1':'失败','2':'处理中'}" listKey="key" listValue="value" multiple="simple"></s:select>
					</div>
					<div id="orderList" class="sBox">
						起始日期：<input class="ipt-date to" name="beginTime" value="${beginTime}" readonly/>
						终止日期：<input class="ipt-date to" name="endTime" value="${endTime}" readonly/>
						<button type="submit" class="abutton2 ml10" onclick="
							$('bank').value=encodeURIComponent($('bank').value);
						"> 查询 </button>
					</div>
				</form>
			</div>
			<div class="sale_list_title">
				<div class="wb15 ml20">
					金额
				</div>
				<div class="wb11">
					手续费
				</div>
				<div class="wb17">
					提现银行
				</div>
				<div class="wb17">
					银行账号
				</div>
				<div class="wb15 tc">
					状态
				</div>
				<div class="wb15 tc">
					提现时间
				</div>
				<div class="clear"></div>
			</div>
		
		<s:iterator value="extractList">
			<!--list -->
			<div class="sale_list">
				<div class="wb15 ml20">
					${money }
				</div>
				<div class="wb11">
					${charge }
				</div>
				<div class="wb17">
					${bank }
				</div>
				<div class="wb17">
					${account }
				</div>
				<div class="wb15 tc">
					<s:if test="state==1">
						提现成功
					</s:if>
					<s:elseif test="state==0">
						申请中
					</s:elseif>
					<s:elseif test="state==-1">
						拒绝
					</s:elseif>
					<s:elseif test="state==2">
						处理中
					</s:elseif>
				</div>
				<div class="wb15 tc">
					${time }
				</div>
				<div class="clear"></div>
			</div>
			<!--list end -->
		</s:iterator>

			<div class="manu">
				<jsp:include page="/WEB-INF/index/common/pagination.jsp"></jsp:include>
				<form name="pageForm" action="${ctx}/user/extract/list_extract_record.shtml" method="get">
					<input type="hidden" id="goPage" name="goPage" />
					
					<input type="hidden" id="page_bank" name="bank" value="" />
					<input type="hidden" id="page_account" name="account" value="" />
					<input type="hidden" name="state" value="${state }" />
					<input type="hidden" name="beginTime" value="${beginTime }" />
					<input type="hidden" name="endTime" value="${endTime }" />
				</form>
				
			</div>

		</div>
		<div class="clear"></div>
	</body>
</html>
