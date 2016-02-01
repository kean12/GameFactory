<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
		<div class="contaner">
			<div class="w223 bbs_orange fl">
				<div class="title w213">
					<span class="ico_6">&nbsp;</span>交易排行榜
				</div>
				<ul class="index_sale">
					<s:iterator value="rankList" id="rank">
						<li><a href="${ctx}/ranking.shtml?gameID=${rank[1]}" title="开放式求购信息平台上线&nbsp;&nbsp;2009-08-19">${rank[0]}</a></li>
					</s:iterator>
					<s:if test="rankList.size()<10">
						<s:bean name="org.apache.struts2.util.Counter" id="counter">
							<s:param name="first" value="rankList.size()+1" />
							<s:param name="last" value="10" />   
							<s:iterator>
								<li>虚位以待</li>
							</s:iterator>
						</s:bean>
					</s:if>
				</ul>
			</div>
			
			<div class="w713 fl ml10">
				<s:if test="game==null">
					<div class="attention bbs_orange">
						<strong class="red">您所搜索的游戏不存在</strong>
					</div>
				</s:if>
				<s:else>
					<div class="attention bbs_orange">
						<strong class="red">${game.gameName }卖得最火的卖家排行</strong>
					</div>
					<div class="bbs_orange" style="width: 713px; margin: 10px 0 0">
						<div class="pd10 l20">
							<s:iterator value="rank_merchant_list" status="index" id="merchant">
								<s:if test="#index.index+1==1">
									<strong class="red">第一名：</strong>
									<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${game.id}&ownerID=${merchant[1]}" class="red">${merchant[0]}</a>
									<br />
								</s:if>
								<s:elseif test="#index.index+1==2">
									<strong class="orange">第二名：</strong>
									<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${game.id}&ownerID=${merchant[1]}" class="red">${merchant[0]}</a>
									<br />
								</s:elseif>
								<s:elseif test="#index.index+1==3">
									<strong class="blue">第三名：</strong>
									<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${game.id}&ownerID=${merchant[1]}" class="red">${merchant[0]}</a>
									<br />
								</s:elseif>
								<s:elseif test="#index.index+1>3 && #index.index+1<=10">
									<s:if test="#index.index+1==4">
										<strong class="green">第四名到第十名：</strong>
									</s:if>
									<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${game.id}&ownerID=${merchant[1]}" class="red">${merchant[0]}</a>
								</s:elseif>
								<s:elseif test="#index.index+1>10">
									<s:if test="#index.index+1==11">
										<br />
										<strong class="green">第四名到第十名：</strong>
									</s:if>
									<a href="${ctx}/user/trade/bizInfo/browse.shtml?gameID=${game.id}&ownerID=${merchant[1]}" class="red">${merchant[0]}</a>
								</s:elseif>
							</s:iterator>
						</div>
					</div>
				</s:else>
				
			</div>
		</div>
	</body>
</html>
