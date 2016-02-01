<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="yhzx_rightside">
<div class="txtbox">
  <div class="yhzx_ckxx_title">
	<h2>${post.message.title}</h2>
	时间：  ${post.message.time} &nbsp; 发布者： ${post.message.addresser}
	</div>
	<div class="yhzx_ckxx txt">
	${post.message.content}
	</div>
	<div class="tc pd10"><button class="abutton3" onclick="location.href='${ctx}/user/message/message.shtml'">返回列表</button></div>
  </div>
</div>
<!--查看全部消息 end -->
<div class="clear"></div>
