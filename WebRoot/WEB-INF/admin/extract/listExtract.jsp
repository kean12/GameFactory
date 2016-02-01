<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<link href="${ctx}/css/admin/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">var AP="${ctx}";</script>
<script type="text/javascript" src="${ctx}/js/tool.js"></script>
<div class="main_top_title">
	<dl id=manage_top>
		<dt class="manage_top_title">
			<img src="${ctx}/images/admin/ico01.gif" width="6" height="10">
			<strong>当前位置：</strong>订单管理>>查看订单
		</dt>
	</dl>
</div>
<div class="blank0"></div>
<form action="${ctx}/admin/extract/extractAdmin.shtml" method="post">
<table id="orderList">
	<tr class="sBox">
		<td>提现用户：<input type="text" id="username" name="username" value="${username}" /></td>
		<td>申请时间：从<input class="ipt-date to" id="beginTime" name="beginTime" value="${beginTime}" readonly/>
			<input class="ipt-date to" id="endTime" name="endTime" value="${endTime}" readonly/>
		</td>
		<td>处理结束时间：从<input class="ipt-date to" id="p_beginTime" name="p_beginTime" value="${p_beginTime}" readonly/>
			<input class="ipt-date to" id="p_endTime" name="p_endTime" value="${p_endTime}" readonly/>
		</td>
		<td>提现金额：从<input type="text" id="moneyMin" name="moneyMin" value="${moneyMin}" size="8"/>～<input type="text" id="moneyMax" name="moneyMax" value="${moneyMax}" size="8"/></td>
	</tr>
	<tr>
		<td>订单状态：<s:select list="#{'':'--订单状态--','0':'未处理','1':'成功','2':'处理中','-1':'拒绝'}"  name="state"  listValue="value" listKey="key" theme="simple" /></td>
		<td>订单编号：<input type="text" id="extractNum" name="extractNum" value="${extractNum}" size="20"/></td>
		<td>提现银行：<input type="text" id="bank" name="bank" value="${bank}" size="20"/></td>
		<td>提现银行账号：<input type="text" id="account" name="account" value="${account}" size="20"/></td>
		<td><input type="submit" value="搜 索" /></td>
	</tr>
</table>
</form>
<hr color="red"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="listtable">
	<tr>
		<th width="6%">订单号</th>
		<th width="6%">提现用户</th>
		<th width="6%">真实姓名</th>
		<th width="6%">提现金额</th>
		<th width="4%">手续费</th>
		<th width="8%">提现银行</th>
		<th width="10%">银行账号</th>
		<th width="8%">申请时间</th>
		<th width="8%">处理结束时间</th>
		<th width="6%">操作管理员</th>
		<th width="6%">订单状态</th>
		<th width="15%">操作</th>
	</tr>
	<s:if test="page.resultlist.size() < 1">
		<tr><td colspan="5">暂无订单信息...</td></tr>
	</s:if>
	<s:iterator value="extractList">
		<tr onMouseOver="this.style.background='#d6e5ff';" onMouseOut="this.style.background='#ffffff';">
		<td>${extractNum}</td>
		<td>${user.username}</td>
		<td>${user.realName}</td>
		<td>${money}</td>
		<td>${charge}</td>
		<td>${bank}</td>
		<td>${account}</td>
		<td>${time}</td>
		<td><s:if test="processTime!=null">${processTime}</s:if><s:else>-</s:else></td>
		<td><s:if test="managerName!=null">${managerName}</s:if><s:else>-</s:else></td>
		<td>
			<s:if test="state==-1"><span class="gray">拒绝</span></s:if>
			<s:elseif test="state==1"><span class="green">成功</span></s:elseif>
			<s:elseif test="state==2">处理中</s:elseif>
			<s:elseif test="state==0">未处理</s:elseif>
			<s:else><span class="red">错误状态</span></s:else>
		</td>
		<td>
			<s:if test="state==2">
				<input type="button" value="同意退款" onclick="chk_process(${id},1);" />
				<input type="button" value="拒绝退款" onclick="chk_process(${id},-1);" />
			</s:if>
			<s:elseif test="state==0">
					<input type="button" value="接受处理" onclick="chk_process(${id},2);" />
			</s:elseif>
			<s:else>
				已处理
			</s:else>
		</td>
		</tr>
	</s:iterator>
</table>
<script type="text/javascript">
	function chk_process(extractID,process){
		document.getElementById("process").value=process;
		document.getElementById("extractID").value=extractID;
		document.pageForm.action="${ctx}/admin/extract/extractAdmin!process.shtml";
		document.pageForm.submit();
	}
</script>

<div class="manu">
	<form name="pageForm" action="${ctx}/admin/extract/extractAdmin.shtml" method="post">
	<jsp:include page="/WEB-INF/index/common/pagination.jsp"></jsp:include>
		<input type="hidden" id="goPage" name="goPage" value="${goPage}" />
		<input type="hidden" name="username" value="${username}" />
		<input type="hidden" name="beginTime" value="${beginTime}"/>
		<input type="hidden" name="endTime" value="${endTime}"/>
		<input type="hidden" name="p_beginTime"  value="${p_beginTime}"  />
		<input type="hidden" name="p_endTime" value="${p_endTime}"/>
		<input type="hidden" name="moneyMin"  value="${moneyMin}"/>
		<input type="hidden" name="moneyMax" value="${moneyMax}" />
		<input type="hidden" name="extractNum" value="${extractNum}" />
		<input type="hidden" name="bank" value="${bank}" />
		<input type="hidden" name="account" value="${account}" />
		<input type="hidden" name="state" value="${state}" />
		
		<input type="hidden" name="process" />
		<input type="hidden" name="extractID" />
	</form>
</div>