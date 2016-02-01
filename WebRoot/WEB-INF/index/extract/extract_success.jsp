<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
		<div class="contaner tc">
			<div class=" ptb10">
				您的位置：
				<a href="${ctx}/index.shtml" class="blue">首页</a> >
				<a href="${ctx}/user/home.shtml" class="blue">用户中心</a> > 提现成功
			</div>
			<div class="bs_ccc tl fb_success">
				<strong class="orange">您的提现申请已提交，款项将会在1-2个工作日内到达您的银行账户。</strong>
				<br />
				提现款项已从您的账户扣除，我们会在第二个工作日处理你的申请。
				<br />
				您可能需要：
				<a href="${ctx}/user/extract/list_extract_record.shtml" class="blue_u">查询提现记录</a> |
				<a href="${ctx}/user/extract/extract_apply.shtml" class="blue_u">继续提现</a> |
				<a href="${ctx}/user/home.shtml" class="blue_u">返回我的账户</a>
			</div>
		</div>
	</body>
</html>

