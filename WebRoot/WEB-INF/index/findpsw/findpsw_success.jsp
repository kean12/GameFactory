<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
		<div class="contaner tc">
			<div class=" ptb10">
				您的位置：
				<a href="${ctx}/index.shtml" class="blue">首页</a> >
				取回密码成功
			</div>
			<div class="bs_ccc tl fb_success">
				<s:if test="findtype==2">
					<strong class="orange">我们已经把您的密码发送至你的邮箱<span class="orange">${email}</span>中</strong>
					<br />
					如果半小时内没有收到密码邮件：
					<br />
					到你邮箱的广告邮件、垃圾邮件目录中查找
					重新取回密码
				</s:if>
				<s:else>
					<strong class="orange">您的密码已修改成功</strong>
					<br />
					<a href="${ctx}/index.shtml" class="blue">返回首页</a>
				</s:else>
			</div>
			<div class="contentbox" id="box1">
			</div>
		</div>
	</body>
</html>
