<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
		<script type="text/javascript" src="${ctx}/js/index/time_compute.js"></script>
		<div class="contaner">
			<div class=" ptb10">
				<s:if test="game!=null">
					<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${game.id}" class="blue">${game.gameName}</a>
				</s:if>
				<s:else>
					<a href="${ctx}/user/trade/bizInfo/search.shtml" class="blue">所有游戏</a>
				</s:else>>
				<s:if test="area!=null"><a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${game.id}&areaID=${area.id}" class="blue">${area.areaName}</a> > </s:if>
				<s:if test="server!=null"><a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${game.id}&areaID=${area.id}&serverID=${server.id}" class="blue">${server.serverName}</a> > </s:if>
				<s:if test="bizKind!=null">
					<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${game.id}&bizKindID=${bizKind.id }&areaID=${area.id}&serverID=${server.id}" class="blue">${bizKind.kindName}</a>
				</s:if>
				<s:else>
					<a href="${ctx}/user/trade/bizInfo/search.shtml" class="blue">所有分类</a>
				</s:else>
			</div>
			<div class="sale_list_lead">
				<div> 
					每页显示数量：
					<span id="bizOffersList_tdPageSize" class="turnpage_img">
						<s:if test="size==20">
							<img width="18" height="15" src="${ctx}/images/ico/shu_20_1.gif" onclick="document.getElementById('pageSize').value=20;document.pageForm.submit();" />
							<img width="18" height="15" src="${ctx}/images/ico/shu_40_2.gif" onclick="document.getElementById('pageSize').value=40;document.pageForm.submit();" />
							<img width="18" height="15" src="${ctx}/images/ico/shu_80_2.gif" onclick="document.getElementById('pageSize').value=80;document.pageForm.submit();" />
						</s:if>
						<s:elseif test="size==40">
							<img width="18" height="15" src="${ctx}/images/ico/shu_20_2.gif" onclick="document.getElementById('pageSize').value=20;document.pageForm.submit();" />
							<img width="18" height="15" src="${ctx}/images/ico/shu_40_1.gif" onclick="document.getElementById('pageSize').value=40;document.pageForm.submit();" />
							<img width="18" height="15" src="${ctx}/images/ico/shu_80_2.gif" onclick="document.getElementById('pageSize').value=80;document.pageForm.submit();" />
						</s:elseif>
						<s:elseif test="size==80">
							<img width="18" height="15" src="${ctx}/images/ico/shu_20_2.gif" onclick="document.getElementById('pageSize').value=20;document.pageForm.submit();" />
							<img width="18" height="15" src="${ctx}/images/ico/shu_40_2.gif" onclick="document.getElementById('pageSize').value=40;document.pageForm.submit();" />
							<img width="18" height="15" src="${ctx}/images/ico/shu_80_1.gif" onclick="document.getElementById('pageSize').value=80;document.pageForm.submit();" />
						</s:elseif>
					</span>
					<span class="ml20">
						排序方式：<s:select list="#{'bizCreTime desc':'发布时间从近到远','bizCreTime':'发布时间从远到近','proportion':'价格从低到高','proportion desc':'价格从高到低'}" name="order_by" listValue="value" listKey="key" theme="simple" onchange="$('pageOrder_by').value=(this.options[this.selectedIndex].value);document.pageForm.submit();"  />
					</span>
					<span class="sale_turnpage">
						<s:if test="page.maxPage==1">
							<input name="" type="image" src="${ctx}/images/ico/pg_1_gray.gif" alt="首页" title="首页" readonly="readonly" />
							<input name="input" type="image" src="${ctx}/images/ico/pg_2_gray.gif" alt="上一页" title="上一页" );" readonly="readonly" />
							${page.currentPage}/${page.maxPage}
							<input name="" type="image" src="${ctx}/images/ico/pg_3_gray.gif" alt="下一页" title="下一页" readonly="readonly" />
							<input name="input2" type="image" src="${ctx}/images/ico/pg_4_gray.gif" alt="尾页" title="尾页" readonly="readonly" />
						</s:if>
						<s:elseif test="page.currentPage==1">
							<input name="" type="image" src="${ctx}/images/ico/pg_1_gray.gif" alt="首页" title="首页" readonly="readonly" />
							<input name="input" type="image" src="${ctx}/images/ico/pg_2_gray.gif" alt="上一页" title="上一页" readonly="readonly" />
							${page.currentPage}/${page.maxPage}
							<input name="" type="image" src="${ctx}/images/ico/pg_3_orange.gif" alt="下一页" title="下一页" onclick="jumpPage(${page.currentPage+1});" />
							<input name="input2" type="image" src="${ctx}/images/ico/pg_4_orange.gif" alt="尾页" title="尾页" onclick="jumpPage(${page.maxPage});" />
							</s:elseif>
						<s:elseif test="page.currentPage==page.maxPage">
							<input name="" type="image" src="${ctx}/images/ico/pg_1_orange.gif" alt="首页" title="首页" onclick="jumpPage(1);" />
							<input name="input" type="image" src="${ctx}/images/ico/pg_2_orange.gif" alt="上一页" title="上一页" onclick="jumpPage(${page.currentPage-1});" />
							${page.currentPage}/${page.maxPage}
							<input name="" type="image" src="${ctx}/images/ico/pg_3_gray.gif" alt="下一页" title="下一页" readonly="readonly" />
							<input name="input2" type="image" src="${ctx}/images/ico/pg_4_gray.gif" alt="尾页" title="尾页" readonly="readonly" />
							</s:elseif>
						<s:else>
							<input name="" type="image" src="${ctx}/images/ico/pg_1_orange.gif" alt="首页" title="首页" onclick="jumpPage(1);" />
							<input name="input" type="image" src="${ctx}/images/ico/pg_2_orange.gif" alt="上一页" title="上一页" onclick="jumpPage(${page.currentPage-1});" />
							${page.currentPage}/${page.maxPage}
							<input name="" type="image" src="${ctx}/images/ico/pg_3_orange.gif" alt="下一页" title="下一页" onclick="jumpPage(${page.currentPage+1});" />
							<input name="input2" type="image" src="${ctx}/images/ico/pg_4_orange.gif" alt="尾页" title="尾页" onclick="jumpPage(${page.maxPage});" />
						</s:else>
					</span>
				</div>
			</div>
			<div class="sale_list_title">
				<div class="sale01">信息标题、物品类型、游戏/区/服</div>
				<div class="sale00">交易类型</div>
				<div class="sale02">金额</div>
				<div class="sale03">剩余件数</div>
				<div class="sale04">单价</div>
				<div class="sale05">剩余时间</div>
				<div class="clear"></div>
			</div>
			<div class="blank10"></div>
			<!--list -->
		<s:iterator value="bizInfoList">
			<div class="sale_list">
				<div class="sale01">
					<a href="${ctx}/user/trade/bizInfo/detail.shtml?bizInfoID=${id}" class="blue f14" target="_blank">${title }</a>
					<br />
					<span class="rank">
						<label title="积分：${owner.userInfo.sellerCredit} 等级：${owner.userInfo.sellerGrade}">
							<s:if test="owner.userInfo.sellerGrade>0">
								信用等级：[
									<a href="#" onclick="javascript:window.open('${ctx}/user/trade/credit/userCredit1.shtml?userID=${owner.id}')"><span class="srank-sale-${owner.userInfo.sellerGrade}">&nbsp;</span></a>
								]
							</s:if>
							<s:else>
								信用积分：[
								${owner.userInfo.sellerCredit}
								]
							</s:else>
						</label>
						<br />
						<s:if test="game!=null">
							物品种类：<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${game.id}&bizKindID=${bizKind.id}">${bizKind.kindName}</a>
							<br />
							游戏:<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${game.id}">${game.gameName }</a>
						</s:if>
						<s:else>
							物品种类：<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${server.area.game.id}&bizKindID=${bizKind.id}">${bizKind.kindName}</a>
							<br />
							游戏/区/服:<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${server.area.game.id}">${server.area.game.gameName }</a>/<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${server.area.game.id}&areaID=${server.area.id}">${server.area.areaName }</a>/<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${server.area.game.id}&areaID=${server.area.id}&serverID=${server.id}">${server.serverName }</a>
						</s:else>
					</span>
				</div>
				<div class="sale00"><s:if test="buyType==1"><img src="${ctx}/images/ico/ji.gif" alt="寄售交易"/></s:if></div>
				<div class="sale02 orange">${price}</div>
				<div class="sale03">${stock}</div>
				<div class="sale04">${proportion}元/${unit}</div>
				<div id="day${id}" class="sale05"><script type="text/javascript">JSTime('${endSellTime}','day${id}');</script></div>
				<div class="clear"></div>
			</div>
		</s:iterator>
			<!--list end -->

			<div class="manu">
				<jsp:include page="/WEB-INF/index/common/pagination.jsp"></jsp:include>
				<form name="pageForm" action="${ctx}/user/trade/bizInfo/browse.shtml" method="get">
					<input type="hidden" id="goPage" name="goPage" />
					<input type="hidden" id="type" name="type" value="${type}" />
					
					<input type="hidden" name="gameID" value="${game.id}" />
					<input type="hidden" name="areaID" value="${area.id}" />
					<input type="hidden" name="serverID" value="${server.id}" />
					<input type="hidden" name="bizKindID" value="${bizKind.id}" />
					<input type="hidden" id="pageOrder_by" name="order_by" value="${order_by}" />
					<input type="hidden" id="pageSearchContent" name="searchContent" value="" />
					<s:if test="ownerID!=null">
						<input type="hidden" name="ownerID" value="${ownerID}" />
					</s:if>
					<input type="hidden" id="pageSize" name="size" value="${size}" />
				</form>
			</div>

			<div class="clear"></div>
		</div>
		
			<s:if test="game!=null">
				<script type="text/javascript">doSelectGame('${game.gameName}','${game.id}');</script>
			</s:if>
			<s:if test="bizKind!=null">
				<script type="text/javascript">doSelectBizKind('${bizKind.kindName}','${bizKind.id}');</script>
			</s:if>
			<s:if test="area!=null">
				<script type="text/javascript">doSelectArea('${area.areaName}','${area.id}');</script>
			</s:if>
			<s:if test="server!=null">
				<script type="text/javascript">doSelectServer('${server.serverName}','${server.id}');</script>
			</s:if>
			<s:if test="searchContent!=null">
				<script type="text/javascript">
					document.getElementById("searchText").value="${searchContent}";
					document.getElementById("searchContent").value="${searchContent}";
					document.getElementById("pageSearchContent").value=encodeURIComponent("${searchContent}");
				</script>
			</s:if>
	</body>
</html>
