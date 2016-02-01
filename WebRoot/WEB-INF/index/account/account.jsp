<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type='text/javascript' src='${ctx}/dwr/interface/account.js'></script>
<script type='text/javascript' src='${ctx}/js/index/account.js'></script>
<div class="yhzx_rightside">
	<div class="yhzx_title">
		我的帐户
	</div>
	<div class="yhzx_hy bs_ccc mt10">
		<strong class="f14 orange">个人信息</strong>
		<dl class="dl_list2">
			<dt>
				真实姓名：
			</dt>
			<dd>
				<strong>${userSession.username}</strong>
				<a class="blue" onclick="alertOpen('修改头像','${ctx}/user/portrait.shtml',480,315);" style="margin-left: 20px;cursor: pointer;">修改头像&gt;&gt;</a>
			</dd>
			<dt>
				联系电话：
			</dt>
			<dd>
				<span id="phoneNum">${userSession.phoneNum }</span>
				<a href="javascript:void(0)" onclick="showBlock('updatephoneNum');" class="blue ml10">编辑&gt;&gt;</a>
			</dd>
			<dt>
				联系QQ：
			</dt>
			<dd>
				<span id="qq">${userSession.qq }</span>
				<a href="javascript:void(0)" onclick="showBlock('updateQq');" class="blue ml10">编辑&gt;&gt;</a>
			</dd>
			<dt>
				注册时间：
			</dt>
			<dd>
				${userSession.regTime }
			</dd>
			<dt>
				帐户状态：
			</dt>
			<dd>
				<s:if test="userSession.isUse==0">账号被禁用</s:if>
				<s:elseif test='userSession.lock=="Y"'>账户被锁定</s:elseif>
				<s:elseif test="userSession.isUse==2">提现申请中</s:elseif>
				<s:else>正常</s:else>
			</dd>
		</dl>
		
		<%--修改联系电话 --%>
		<div class="xg" id="updatephoneNum" style="top: 35px;">
			<strong class="blue">修改联系电话：</strong>
			<ul>
				<li><input id="txt_phoneNum" type="text" value="${userSession.phoneNum }" onfocus="this.select();" /></li>
				<li><button onclick="doPhoneNum('updatephoneNum');" class="abutton21">保存</button>&nbsp;&nbsp;&nbsp;<button onclick="closeDiv('updatephoneNum');" class="abutton21">取消</button></li>
			</ul>
		</div>
		<%--END --%>
		<%--修改QQ --%>
		<div class="xg" id="updateQq" style="top: 35px;">
			<strong class="blue">修改联系QQ：</strong>
			<ul>
				<li><input id="txt_qq" type="text" value="${userSession.qq }" onfocus="this.select();" /></li>
				<li><button onclick="doQq('updateQq');" class="abutton21">保存</button>&nbsp;&nbsp;&nbsp;<button onclick="closeDiv('updateQq');" class="abutton21">取消</button></li>
			</ul>
		</div>
		<%--END --%>
		
	</div>
	<div class="yhzx_hy bs_ccc mt10">
		<strong class="f14 orange">帐户信息</strong>
		<dl class="dl_list2">
			<dt>
				可用金额：
			</dt>
			<dd>
				<strong class="f18 red">${userSession.userInfo.money}</strong>元
				<a href="#" class="blue ml10">余额支付（关闭/开启）&gt;&gt;</a><a href="#" class="blue ml10">帐户明细查询&gt;&gt;</a>
			</dd>
			<dt>
				冻结金额：
			</dt>
			<dd>
				<strong class="f18 red">${userSession.userInfo.freemoney}</strong>元
				<a href="#" class="blue ml10">查看详情&gt;&gt;</a>
			</dd>
			<dt>
				提现银行(待处理)：
			</dt>
			<dd>
				<s:set name="bankNum" value="userSession.userInfo.bankNum"></s:set>
				<s:if test="#bankNum!=null">
					${userSession.userInfo.bankName} <s:property value="#bankNum.substring(0,12)" />****<s:property value="#bankNum.substring(16,#bankNum.length())" />
				</s:if>
				<s:else>
					****
				</s:else>
				<a href="${ctx}/user/account/bank.shtml" class="blue ml10">修改&gt;&gt;</a>
			</dd>
			<dt>
				身份证号码：
			</dt>
			<s:if test="userSession.identity!='' && userSession.identity!=null">
				<dd>
					<s:property value="userSession.identity.substring(0,4)" />******<s:property value="userSession.identity.substring(15,18)" />
				</dd>
				<dt>
					真实姓名：
				</dt>
				<dd>
					${userSession.realName }
				</dd>
			</s:if>
			<s:else>
				<dd>
					<span id="identity"></span>
					<a href="javascript:void(0)" id="identity_edit" onclick="showBlock('updateIdentity');" class="blue ml10">编辑&gt;&gt;</a>
				</dd>
				<span id="updateRealName" style="display:none;">
					<dt>
						真实姓名：
					</dt>
					<dd>
						<span id="realName">${userSession.realName }</span>
					</dd>
				</span>
			</s:else>
		</dl>
		
		<%--编辑身份证号码 --%>
		<div class="xg" id="updateIdentity" style="top: 35px;">
			<strong class="blue">编辑身份信息：</strong>
			<ul>
				<li>身份证：<input id="txt_identity" type="text" onfocus="this.select();" /></li>
				<li>姓　名：<input id="txt_realName" type="text" onfocus="this.select();" /></li>
				<li><button onclick="doIdentity('updateIdentity');" class="abutton21">保存</button>&nbsp;&nbsp;&nbsp;<button onclick="closeDiv('updateIdentity');" class="abutton21">取消</button></li>
			</ul>
		</div>
		<%--END --%>
		
	</div>
	
	<div class="yhzx_hy bs_ccc mt10">
		<strong class="f14 orange">帐户安全</strong>
		<dl class="dl_list2">
			<dt>
				电子邮箱：
			</dt>
			<dd>
				<span id="email">${userSession.email}</span>
				<a href="javascript:void(0)" onclick="showBlock('updateEmail');" class="blue ml10">修改&gt;&gt;</a>
			</dd>
			<dt>
				支付密码：
			</dt>
			<dd>
				<s:if test='userSession.userInfo.applyPwd == null || userSession.userInfo.applyPwd ==""'>
					请[<a class="blue" href="javascript:void(0)" onclick="showBlock('setApplyPwd');">设置</a>]您的支付密码，否则无法交易！
				</s:if>
				<s:else>
				********
				<a href="javascript:void(0)" onclick="showBlock('updateApplyPwd');" class="blue ml10">修改&gt;&gt;</a>
				</s:else>
			</dd>
			<dt>
				登陆密码：
			</dt>
			<dd>
				********
				<a href="javascript:void(0)" onclick="showBlock('updatePassword');" class="blue ml10">修改&gt;&gt;</a>
			</dd>
			<dt>
				密码提示问题：
			</dt>
			<dd>
				<s:if test='userSession.hint == null || userSession.hint == ""'>
					[您未设置问题与答案]
				</s:if>
				<span id="hint">${userSession.hint }</span>
				<a href="javascript:void(0)" onclick="showBlock('updateHint');" class="blue ml10">修改&gt;&gt;</a>
			</dd>
		</dl>
		
		<%--编辑电子邮箱 --%>
		<div class="xg" id="updateEmail" style="top: 35px;">
			<strong class="blue">修改电子邮件：</strong>
			<ul>
				<li><input id="txt_email" type="text" onfocus="this.select();" /></li>
				<li><button onclick="doEmail('updateEmail');" class="abutton21">保存</button>&nbsp;&nbsp;&nbsp;<button onclick="closeDiv('updateEmail');" class="abutton21">取消</button></li>
			</ul>
		</div>
		<%--END --%>
		<%--修改支付密码 --%>
		<div class="xg" id="updateApplyPwd" style="top: 15px;">
			<strong class="blue">修改支付密码：</strong>
			<ul>
				<li>原有支付密码：<input id="txt_o_applyPwd" type="password" onfocus="this.select();" /></li>
				<li>新设支付密码：<input id="txt_applyPwd1" type="password" onfocus="this.select();" /></li>
				<li>确认支付密码：<input id="txt_applyPwd2" type="password" onfocus="this.select();" /></li>
				<li><button onclick="doApplyPwd('updateApplyPwd');" class="abutton21">保存</button>&nbsp;&nbsp;&nbsp;<button onclick="closeDiv('updateApplyPwd');" class="abutton21">取消</button></li>
			</ul>
		</div>
		<%--END --%>
		<%--设定支付密码 --%>
		<div class="xg" id="setApplyPwd" style="top: 15px;">
			<strong class="blue">设定支付密码：</strong>
			<ul>
				<li>新设支付密码：<input id="txt_setApplyPwd1" type="password" onfocus="this.select();" /></li>
				<li>确认支付密码：<input id="txt_setApplyPwd2" type="password" onfocus="this.select();" /></li>
				<li><button onclick="setApplyPwd('setApplyPwd');" class="abutton21">保存</button>&nbsp;&nbsp;&nbsp;<button onclick="closeDiv('setApplyPwd');" class="abutton21">取消</button></li>
			</ul>
		</div>
		<%--END --%>
		
		<%--修改登录密码 --%>
		<div class="xg" id="updatePassword" style="top: 15px;">
			<strong class="blue">修改登录密码：</strong>
			<ul>
				<li>原有登录密码：<input id="txt_o_password" type="password" onfocus="this.select();" /></li>
				<li>新设登录密码：<input id="txt_password1" type="password" onfocus="this.select();" /></li>
				<li>确认登录密码：<input id="txt_password2" type="password" onfocus="this.select();" /></li>
				<li><button onclick="doPassword('updatePassword');" class="abutton21">保存</button>&nbsp;&nbsp;&nbsp;<button onclick="closeDiv('updatePassword');" class="abutton21">取消</button></li>
			</ul>
		</div>
		<%--END --%>
		<%--编辑密码提示问题 --%>
		<div class="xg" id="updateHint" style="top: -10px;">
			<strong class="blue">编辑密码提示问题：</strong>
			<ul>
				<li>原提示问题：<select id="sel_o_hint"><jsp:include page="/WEB-INF/index/user/userhint.jsp"></jsp:include></select></li>
				<li>原问题答案：<input id="o_answer" type="text" onfocus="this.select();" /></li>
				
				<li>新提示问题：<select id="sel_hint"><jsp:include page="/WEB-INF/index/user/userhint.jsp"></jsp:include></select></li>
				<li>新问题答案：<input id="answer" type="text" onfocus="this.select();" /></li>
				<li><button onclick="doHint('updateHint');" class="abutton21">保存</button>&nbsp;&nbsp;&nbsp;<button onclick="closeDiv('updateHint');" class="abutton21">取消</button></li>
			</ul>
		</div>
		<%--END --%>
	</div>
	
	<div class="yhzx_hy bs_ccc mt10">
		<strong class="f14 orange">交易信息</strong>
		<dl class="dl_list2">
			<dt>
				交易记录：
			</dt>
			<dd>
				<a onclick="window.location='${ctx}/user/trade/order/list_bought_items.shtml?type=6'" class="blue ml10" style="cursor: pointer;">查看已购买到的宝贝&gt;&gt;</a>
				<a onclick="window.location='${ctx}/user/trade/order/list_sold_items.shtml?type=6'" class="blue ml10" style="cursor: pointer;">查看已卖出的宝贝&gt;&gt;</a>
			</dd>
			<dt>
				评价记录：
			</dt>
			<dd>
				<a href="#" class="blue ml10" onclick="window.open('${ctx}/user/trade/credit/userCredit1.shtml?type=1')">查看最新评价记录&gt;&gt;</a>（0笔交易等待评价）
			</dd>
			<dt>
				收获角色名：
			</dt>
			<dd>
				<a href="${ctx}/user/account/role.shtml"  class="blue ml10">收货角色名管理&gt;&gt;</a>
			</dd>
		</dl>
		<%--编辑收获地址 --%>
		<div class="xg" id="updatePostAddr" style="top: 35px;">
			<strong class="blue">编辑收获地址：</strong>
			<ul>
				<li><input id="txt_postAddr" type="text" onfocus="this.select();" /></li>
				<li><button onclick="doPostAddr('updatePostAddr');" class="abutton21">保存</button>&nbsp;&nbsp;&nbsp;<button onclick="closeDiv('updatePostAddr');" class="abutton21">取消</button></li>
			</ul>
		</div>
		<%--END --%>
	</div>
</div>
<div class="clear"></div>