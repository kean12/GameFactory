<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
<style>
.sale_list {
	width: 925px;
	margin-left: 20px;
	margin-top: 10px
}

.sale_list li {
	padding: 5px 0 3px
}

.sale_list .th li {
	background: #f1f1f1;
	border-bottom: #ccc 1px solid;
	padding: 10px 0 5px
}

.sale_list .th1 li {
	border-bottom: #ccc 1px dotted;
	padding: 10px 0 5px
}

.sale_list .sale01 {
	padding-left: 10px
}

.fbxx_title {
	width: 948px;
	border-top: 0;
	padding-left: 10px;
}
</style>

		<div class="contaner tc">
			<img src="${ctx}/images/fb03.gif" width="951" height="34" />
			<div class="bbs_orange tl fb_success mt10">
				<strong class="red">您的宝贝已经成功发布！ </strong>
				<ul>
					<li>
						点击<a href="${ctx}/user/trade/bizInfo/detail.shtml?bizInfoID=${bizInfo.id}" class="blue_u">这里</a>查看，
					</li>
					<li>
						或者继续<a href="${ctx}/user/trade/bizInfo/publish.shtml" class="blue_u">发布宝贝</a>。
					</li>
					<li>
						如遇系统繁忙,不能马上在店铺、分类、搜索中显示出来，请耐心等待。
					</li>
				</ul>
			</div>
			<div class="blank10"></div>
		</div>

	</body>
</html>

