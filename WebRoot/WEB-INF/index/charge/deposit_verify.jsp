<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/index/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
		<!--top end -->
		<div class="contaner tc">
			<div class=" ptb10">
				您的位置：
				<a href="${ctx}/index.shtml" class="blue">首页</a> >
				<a href="${ctx}/user/home.shtml" class="blue">用户中心</a> > 确认充值
			</div>
			<div class="user_cz_l">
				<dl>
					<dt>
						<a href="#">银行卡在线充值</a>
					</dt>
					<dt>
						<a href="#">支付宝在线充值</a>
					</dt>
					<dt>
						<a href="#" class="red">点卡在线充值</a>
					</dt>
					<dd>
						<a href="#">神州行充值</a>
					</dd>
					<dd>
						<a href="#">盛大游戏卡充值</a>
					</dd>
					<dd>
						<a href="#">征途游戏卡充值</a>
					</dd>
					<dd>
						<a href="#">骏网一卡通充值</a>
					</dd>
					<dd>
						<a href="#">QQ币充值</a>
					</dd>
				</dl>
			</div>
			<div class="user_cz_r">
				<div class="user_cz_r_top12">
					<span class="cz_top01">1、填写金额</span><span class="cz_top02">2、确认充值信息</span><span class="cz_top03">3、进入网上充值 </span>
				</div>
				<div class="blank10"></div>
				<div class="ptb10 f14" style="padding-left: 280px">
					充值卡类：
					<img src="${ctx}/images/bank/${bank }.gif" align="absmiddle" />
					<br />
					<br />
					充值金额：
					<strong class="f24 red">${money}</strong> 元
				</div>

				<div class="attention bbs_orange">
					注意：将会在新窗口中打开页面，充值订单有效时间为24小时，请在有效时间内完成充值
				</div>
				<div class="pd10 tc">
					<form action="${ctx}/user/charge/charge.shtml" method="post">
						<input type="hidden" name="bank" value="${bank }" />
						<input type="hidden" name="money" value="${money }" />
						<button type="button" class="orangebutton_big" onclick="javascript:history.back();">
							上一步
						</button>
						<button type="submit" class="orangebutton_big" style="margin-left: 10px;">
							下一步
						</button>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>
