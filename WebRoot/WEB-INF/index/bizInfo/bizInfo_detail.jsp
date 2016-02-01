<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
	<style>
	.zhmm_spxx{font-size:12px; background:none}
	.zhmm_spxx dt{border-bottom:#ccc 1px dotted; font-weight:normal}
	</style>
	<s:if test="bizInfo==null">
		<script type="text/javascript">
			function showmain(obj){
				var menu_num=document.getElementById("rightnav").getElementsByTagName("li");
				for(i=0;i<menu_num.length;i++){
					menu_num[i].className=i==obj?"action_nav":"normal_nav";
					document.getElementById('box'+i).style.display=i==obj?"block":"none";
				}
			}
		</script>
		<div class="contaner tc">
			<div class=" ptb10">
				您的位置：<a href="${ctx}" class="blue">首页</a> > <a href="${ctx}/user/home.shtml" class="blue">用户中心</a> > 订单详情
			</div>
			<div class="bs_ccc tl fb_warning">
				<span class="red">很抱歉，您查看的宝贝不存在，可能已下架或者被转移。</span><br />
				您可以：<br />
				1.检查刚才的输入<br />
				2.去其它地方逛逛：<a href="${ctx}" class="blue">首页</a> | <a href="${ctx}/user/home.shtml" class="blue">用户中心</a>
			</div>
			<div class="contentbox" id="box1"></div>
		</div>
	</s:if>
	<s:else>
		<script type="text/javascript" src="${ctx}/js/index/time_compute.js"></script>
		<div class="contaner">
			<div class=" ptb10">
				<a href="${ctx}/index.shtml" class="blue">首页</a> &gt;
				<s:if test="bizInfo.game!=null">
					<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${bizInfo.game.id}">${bizInfo.game.gameName }</a> &gt;
					<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${bizInfo.game.id}&bizKindID=${bizInfo.bizKind.id}">${bizInfo.bizKind.kindName}</a>
				</s:if>
				<s:else>
					<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${bizInfo.server.area.game.id}">${bizInfo.server.area.game.gameName }</a> &gt;
					<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${bizInfo.server.area.game.id}&bizKindID=${bizInfo.bizKind.id}">${bizInfo.bizKind.kindName}</a> &gt;
					<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${bizInfo.server.area.game.id}&bizKindID=${bizInfo.bizKind.id}&areaID=${bizInfo.server.area.id}">${bizInfo.server.area.areaName }</a> &gt;
					<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${bizInfo.server.area.game.id}&bizKindID=${bizInfo.bizKind.id}&areaID=${bizInfo.server.area.id}&serverID=${bizInfo.server.id}">${bizInfo.server.serverName }</a>
				</s:else>
			</div>
		
		
			<div class="product_show_l">
				<s:if test="pictureList.size()>0">
					<s:iterator value="pictureList">
						<a href="#"><img src="${ctx}${route}" onclick="window.open('${ctx}/user/trade/bizInfo/picture.shtml?bizInfoID=${bizInfo.id}&pictureID=${id}')" width="180" height="120" /></a>
					</s:iterator>
				</s:if>
				<s:else>
					<img src="${ctx}/images/gameicon/${bizInfo.server.area.game.gameNum}.jpg" width="180" height="120" />
				</s:else>
			</div>

			<div class="product_show_m">
				<h1>
					${bizInfo.title}
				</h1>
				<ul>
					<li>
						<div>
							<a href="javascript:CopyID('${bizInfo.serial}');" class="blue_u">复制商品编号</a> &nbsp;
						</div>
						商品编号:${bizInfo.serial}
					</li>
					<li>
						游戏区服:
						<s:if test="bizInfo.game!=null">
							<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${bizInfo.game.id}">${bizInfo.game.gameName }</a>
						</s:if>
						<s:else>
							<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${bizInfo.server.area.game.id}">${bizInfo.server.area.game.gameName }</a>/
							<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${bizInfo.server.area.game.id}&areaID=${bizInfo.server.area.id}">${bizInfo.server.area.areaName }</a>/
							<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${bizInfo.server.area.game.id}&areaID=${bizInfo.server.area.id}&serverID=${bizInfo.server.id}">${bizInfo.server.serverName }</a>
						</s:else>
					</li>
					<li>
						<div>
							商品类型:
							<s:if test="bizInfo.game!=null">
								<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${bizInfo.game.id}&bizKindID=${bizInfo.bizKind.id}">${bizInfo.bizKind.kindName }</a>
							</s:if>
							<s:else>
								<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${bizInfo.server.area.game.id}&areaID=${bizInfo.server.area.id}&serverID=${bizInfo.server.id}&bizKindID=${bizInfo.bizKind.id}">${bizInfo.bizKind.kindName }</a>
							</s:else>
						</div>
						商品价格:
						<span class="red">${bizInfo.price}</span>元
					</li>
					
					<li>
						<div>
							单位: ${bizInfo.unit}
						</div>
						每件宝贝数量:
						<span class="red">${bizInfo.amount}</span>
					</li>
					
					<li>
						<div>
							剩余时间: <span id="day"><script type="text/javascript">JSTime('${bizInfo.endSellTime}','day');</script></span>
						</div>
						商品件数:
						<span class="red">${bizInfo.stock}</span> 件
					</li>
					<li>
						<div>
							发布时间: ${bizInfo.startSellTime}
						</div>
						挂卖方式: <s:if test="bizInfo.buyType==2">担保</s:if><s:else>寄售</s:else>
					</li>
					<li>
						<s:if test="bizInfo.sellModel==1">
							<div>
								交易地点: ${bizInfo.site}
							</div>
							交易方式: 游戏中当面
						</s:if>
						<s:else>
							交易方式: 邮寄
						</s:else>
					</li>
					<li>
						<div>
							<a target="_blank" class="orange">寄售交易帮助</a>
						</div>
						交易时间: ${bizInfo.tradeStart}—${bizInfo.tradeEnd }
					</li>
					<li><strong>商品描述:</strong></li>
				</ul>
				<div class="spms">
					${bizInfo.info }
				</div>
				<div class="paybox">
					<button onclick="location.href='${ctx}/user/trade/order/buy_now.shtml?bizInfoID=${bizInfo.id}';"></button>
				</div>
			<s:if test="bizInfo.stock==0">
				<div class="mainpop">
					商品已卖完！
				</div>
			</s:if>
			
			<%-- 
				<div class="quicklink">
					<a href="#" target="_blank">送货上门</a><a id="EscortDetail1_aNetBar" href="javascript:SubmitForm(-1)">去网吧购买</a><a href="#" target="_blank">充值卡购买</a><a href="#" target="_blank">用神州行卡购买</a><a href="#" target="_blank">用游戏点卡购买</a>
				</div>
			--%>
			</div>

			<div class="product_show_r">
				<div class="mj_title">
					卖家信息
				</div>
				<ul>
					<li>
						<a href="#" onclick="javascript:window.open('${ctx}/user/trade/credit/userCredit1.shtml?userID=${bizInfo.owner.id}')"><strong>用户名：${bizInfo.owner.username}</strong></a>
					</li>
					<li>
						<a href="#" onclick="javascript:window.open('${ctx}/user/trade/credit/userCredit1.shtml?userID=${bizInfo.owner.id}')"><strong>卖家信用：${bizInfo.owner.userInfo.sellerCredit}</strong></a>
					</li>
					<li>
						信誉等级：<label class="rank" title="积分：${bizInfo.owner.userInfo.sellerCredit} 等级：${bizInfo.owner.userInfo.sellerGrade}">
									<s:if test="bizInfo.owner.userInfo.sellerGrade>0">
										<a href="#" onclick="javascript:window.open('${ctx}/user/trade/credit/userCredit1.shtml?userID=${bizInfo.owner.id}')" class="blue_u"><span class="srank-sale-${bizInfo.owner.userInfo.sellerGrade}">&nbsp;</span></a>
									</s:if>
									<s:else>
										 暂无星级
									</s:else>
								</label>
					</li>
					<s:if test="isSellerPositiveRatio==-1">
						<li class="bb_ccc">
							好评率： ——
						</li>
					</s:if>
					<s:elseif test="isSellerPositiveRatio==0">
						<li class="bb_ccc">
							好评率： ${sellerPositiveRatio}%
						</li>
					</s:elseif>
					<li>
						<a href="${ctx}/user/trade/credit/userCredit1.shtml?userID=${bizInfo.owner.id}" target="_blank"><strong>买家信用：${bizInfo.owner.userInfo.buyerCredit}</strong></a>
					</li>
					<li class="mt5">
						信誉等级：<label class="rank" title="积分：${bizInfo.owner.userInfo.buyerCredit} 等级：${bizInfo.owner.userInfo.buyerGrade}">
									<s:if test="bizInfo.owner.userInfo.buyerGrade>0">
										<a href="${ctx}/user/trade/credit/userCredit1.shtml?userID=${bizInfo.owner.id}" target="_blank" class="blue_u"><span class="srank-buy-${bizInfo.owner.userInfo.buyerGrade}">&nbsp;</span></a>
									</s:if>
									<s:else>
										 暂无星级
									</s:else>
								</label>
					</li>
					<s:if test="isBuyerPositiveRatio==-1">
						<li class="bb_ccc">
							好评率：——
						</li>
					</s:if>
					<s:elseif test="isBuyerPositiveRatio==0">
						<li class="bb_ccc">
							好评率： ${buyerPositiveRatio}%
						</li>
					</s:elseif>
				</ul>
			</div>
			<div class="clear"></div>
		</div>
	</s:else>
	</body>
</html>

