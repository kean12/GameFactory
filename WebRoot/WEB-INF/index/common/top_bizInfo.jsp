<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/index/common/taglibs.jsp"%>
<%-- 文件头部 --%>
<jsp:include page="/WEB-INF/index/common/noscript.jsp"></jsp:include>
<div class="top top_bg contaner">
	<div class="logo"><a href="${ctx}/index.shtml"><img src="${ctx}/images/bg/logo.gif" width="140" height="40" /></a></div>
	<div class="toplogin">
		<dl>
			<dt>
				<span class="ico_1">&nbsp;</span>
				<a onclick="window.external.addFavorite(document.location.href,document.title)" style="cursor: pointer;">收藏</a>
				<span class="ico_2">&nbsp;</span>
				<a onclick="this.style.behavior='url(#default#homepage)';this.setHomePage(location.href);return false;" style="cursor: pointer;">设为首页</a>
				<span class="ico_3">&nbsp;</span>
				<a href="help.html">用户帮助</a>
			</dt>
      		<dd>
      			<form name="indexloginform" action="${ctx}/user/login!carry.shtml" method="post">
			  		<s:if test="userSession!=null">
						您好<span class="orange">${userSession.username}</span>，欢迎来到 游戏买卖网
						 [<a href="${ctx}/user/home.shtml" class="blue" target="_self">用户中心</a>]
						 [<a href="${ctx}/user/logout.shtml" class="blue" target="_self">退出登录</a>]
					</s:if>
					<s:else>
							<label>用户名：</label><input id="index_username" name="user.username" type="text" />
							<label>密码：</label><input id="index_password" name="user.password" type="password" />
							<label>验证码：</label><input id="index_vercode" name="vercode" type="text" onfocus="firstfocus(true,'index_vercodeimg');" style="width: 38px;" maxlength="4" /><IMG onclick="relImg('index_vercodeimg')" id="index_vercodeimg" alt="" src="${ctx}/images/identifyingCodeImage.gif" style="cursor: pointer;" />
							<button type="submit" onclick="return chkindexloginform();"></button>
							<script type="text/javascript">
								function chkindexloginform(){
									var index_username=document.getElementById("index_username").value.Trim();
									var index_password=document.getElementById("index_password").value.Trim();
									var index_vercode=document.getElementById("index_vercode").value.Trim();
									if(index_username.length==0){
										alertDialog('请输入用户名！');
										document.getElementById("index_username").select();
										return false;
									}
									if(index_password.length==0){
										alertDialog('请输入密码！');
										document.getElementById("index_password").select();
										return false;
									}
									if(index_vercode.length==0){
										alertDialog('请输入验证码！');
										document.getElementById("index_vercode").select();
										return false;
									}
								}
							</script>
					</s:else>
			  	</form>
			</dd>
		</dl>
	</div>
</div>
<!--top end -->
