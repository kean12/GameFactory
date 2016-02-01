<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
		<script type="text/javascript" src="${ctx}/js/index/publish_custom.js"></script>
		<div class="contaner tc">
			<div>
				<img src="${ctx}/images/fb02.gif" width="951" height="34" />
			</div>
			<div class="bbs_orange tl mt10">
				<form name="publish_search_form" action="${ctx}/user/trade/bizInfo/publish.shtml" method="post">
					<s:if test="game!=null">
						<input type="hidden" name="gameName" value="${game.gameName}">
					</s:if>
					<s:else>
						<input type="hidden" name="gameName" value="${server.area.game.gameName}">
					</s:else>
				</form>
				<form action="${ctx}/user/trade/bizInfo/publish_data_2_save.shtml" method="post" enctype="multipart/form-data">
				
				<div class="mainreg">
						<div class="fbxx_title">
							<span class="ico_4">&nbsp;</span> 商品信息
						</div>
					
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<th width="18%">
									商品分类：
								</th>
								<td>
									<s:if test="typeID==1">
										寄售交易 &gt;&gt; 
									</s:if>
									<s:elseif test="type==2">
										自主交易 &gt;&gt; 
									</s:elseif>
									<s:if test="game!=null">
										<s:property value="game.gameName" />
									</s:if>
									<s:else>
										<s:property value="server.area.game.gameName" /> &gt;&gt; <s:property value="server.area.areaName" /> &gt;&gt; <s:property value="server.serverName" />
									</s:else>
									&gt;&gt; <s:property value="gameKind.bizKind.kindName" />
									<a href="javascript:void(0);" class="green_u" onclick="javascript:document.publish_search_form.submit();">[更改分类]</a>
									<span class="orange"></span>
								</td>
							</tr>
							
							<tr>
								<th>
									宝贝标题：(<span class="red">*</span>)
								</th>
								<td>
									<input type="text" id="title" name="bizInfo.title" size="60" value="${customInfo.bizInfo.title }" onblur="chkTitle();" />
									限定在30个汉字内（60个字符）
									<span id="titleMess" class="orange" <s:if test="customInfo==null || customInfo.error.title">style="display:none;"</s:if>>请输入标题</span>
								</td>
							</tr>
							<tr>
								<th>
									一口价：(<span class="red">*</span>)
								</th>
								<td>
									<input type="text" id="price" name="bizInfo.price" value="${customInfo.bizInfo.price }" onblur="chkPrice();" />
									元 
									<span id="priceMess" class="orange" <s:if test="customInfo==null || customInfo.error.price">style="display:none;"</s:if>>请输入正确的价格</span>
								</td>
							</tr>
							<tr>
								<th>
									宝贝件数：(<span class="red">*</span>)
								</th>
								<td>
									<input type="text" id="stock" name="bizInfo.stock" value="${customInfo.bizInfo.stock}" onblur="chkStock();" />
									件
									<span id="stockMess" class="orange" <s:if test="customInfo==null || customInfo.error.stock">style="display:none;"</s:if>>请输入宝贝件数</span>
								</td>
							</tr>
							<tr>
								<th>
									上传宝贝图片：&nbsp;&nbsp;&nbsp;
								</th>
								<td>
									<input type="checkbox" id="type" name="customInfo.type" value="1" <s:if test="customInfo!=null && customInfo.type==1">checked="checked"</s:if> onclick="chk_pic(this.checked,'Treasure picturess');"  /> 
									<span id="typeMess" class="orange" <s:if test="customInfo==null || customInfo.error.type">style="display:none;"</s:if>>请上传宝贝图片！</span>
								</td>
							</tr>
							
							<tr id="Treasure picturess" <s:if test="customInfo==null || customInfo.type!=1">style="display:none;"</s:if> >
								<th>
									宝贝图片：&nbsp;&nbsp;&nbsp;
								</th>
								<td class="update_frame">
									<ul id="img_content">
										<li id="pic1" class="bbd_ccc">
											<input type="file" id="file1" name="customInfo.file" size="50" /><a href="javascript:void(0);" class="blue_u ml20" onclick="deletePic('pic1');">删除</a>
										</li>
									</ul>
									<a href="javascript:void(0);" class="blue_u ml20" onclick="addPic();">增加一张</a>
									<span id="picMess" class="orange" style="display: none;">最多只能上传5张图片！</span>
								</td>
							</tr>
						</table>
						
						<div class="fbxx_title mt10">
							<span class="ico_6">&nbsp;</span> 交易选项
						</div>
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<th width="18%">
									交易方式：(<span class="red">*</span>)
								</th>
								<td>
									<s:if test="customInfo.bizInfo.sellModel!=1">
										<input type="radio" id="sellModel1" name="bizInfo.sellModel" value="1" onclick="$('div_site').style.display='';" />
										<label for="sellModel1">游戏中当面</label>
										<input type="radio" id="sellModel2" name="bizInfo.sellModel" value="2" onclick="$('div_site').style.display='none';" checked="checked" />
										<label for="sellModel2">邮寄</label>
									</s:if>
									<s:else>
										<input type="radio" id="sellModel1" name="bizInfo.sellModel" value="1" onclick="$('div_site').style.display='';" checked="checked" />
										<label for="sellModel1">游戏中当面</label>
										<input type="radio" id="sellModel2" name="bizInfo.sellModel" value="2" onclick="$('div_site').style.display='none';" />
										<label for="sellModel2">邮寄</label>
									</s:else>
									<label class="orange">选择邮寄,必须保证物品可以邮寄,包裹里除了交易金额外还需要存放邮寄所需的手续费</label>
								</td>
							</tr>
						<TBODY id="div_site" style="display: <s:if test="customInfo.bizInfo.sellModel==2">none</s:if><s:else>block</s:else>;">
							<tr>
								<th width="18%">
									交易地点：(<span class="red">*</span>)
								</th>
								<td>
									<input type="text" id="site" name="bizInfo.site" size="60" value="${customInfo.bizInfo.site }" onblur="chkSellModel();" />
									<span id="siteMess" class="orange" <s:if test="customInfo==null || customInfo.error.site">style="display:none;"</s:if>>请输入交易地点</span>
								</td>
							</tr>
						</TBODY>
						</table>
						
						<div class="fbxx_title mt10">
							<span class="ico_7">&nbsp;</span> 账号信息
						</div>
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<th width="18%">
									游戏账号：(<span class="red">*</span>)
								</th>
								<td>
									<input type="text" id="account" name="bizInfo.account" value="${customInfo.bizInfo.account }" onblur="chkAccount();" />
									<span id="accountMess" class="orange" <s:if test="customInfo==null || customInfo.error.account">style="display:none;"</s:if>>请输入您的游戏账号！</span>
								</td>
							</tr>
							<tr>
								<th>
									游戏密码：(<span class="red">*</span>)
								</th>
								<td>
									<input type="password" id="password" name="bizInfo.password" value="${customInfo.bizInfo.password }" style="width: 150px;" onblur="chkPassword();" />
									<span id="passwordMess" class="orange" <s:if test="customInfo==null || customInfo.error.password">style="display:none;"</s:if>>请输入您账号的密码！</span>
								</td>
							</tr>
							
							<tr>
								<th>
									游戏角色名：(<span class="red">*</span>)
								</th>
								<td>
									<input type="text" id="roleName" name="bizInfo.roleName" value="${customInfo.bizInfo.roleName }" style="width: 150px;" onblur="chkRoleName();" />
									<span id="roleNameMess" class="orange" <s:if test="customInfo==null || customInfo.error.roleName">style="display:none;"</s:if>>请输入您的游戏角色名！</span>
								</td>
							</tr>
							<tr>
								<th>
									密码锁：&nbsp;&nbsp;&nbsp;
								</th>
								<td>
									<input type="password" id="coded_lock" name="bizInfo.coded_lock" value="${customInfo.bizInfo.coded_lock}" style="width: 150px;"  />
									请输入您的密码锁！
									<span id="coded_lockMess" class="orange"></span>
								</td>
							</tr>
							<tr>
								<th>
									物品存放处：(<span class="red">*</span>)
								</th>
								<td>
									<input type="text" id="place" name="bizInfo.place" value="${customInfo.bizInfo.place }" size="60" onblur="chkPlace();" />
									<span id="placeMess" class="orange" <s:if test="customInfo==null || customInfo.error.place">style="display:none;"</s:if>>请输入您的物品存放处！</span>
								</td>
							</tr>
							
							<tr>
								<th>
									上传密保卡：&nbsp;&nbsp;&nbsp;
								</th>
								<td>
									<input type="checkbox" id="pwdType" name="customInfo.pwdType" value="1" <s:if test="customInfo!=null && customInfo.pwdType==1">checked="checked"</s:if> onclick="chk_pic(this.checked,'Crypto Card');"  /> 
									<span id="pwdTypeMess" class="orange" <s:if test="customInfo==null || customInfo.error.pwdType">style="display:none;"</s:if>>请上传您的密保卡！</span>
								</td>
							</tr>
							<tr id="Crypto Card" <s:if test="customInfo==null || customInfo.pwdType!=1">style="display:none;"</s:if>>
								<th>
									密保卡：&nbsp;&nbsp;&nbsp;
								</th>
								<td>
									<input type="file" id="pwdfile" name="file" size="50" /><a href="javascript:void(0);" class="blue_u ml20" onclick="clearInputFile('pwdfile');">清空</a>
								</td>
							</tr>
						</table>
						
						<div class="fbxx_title mt10">
							<span class="ico_7">&nbsp;</span> 联系信息
						</div>
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<th width="18%">
									您的QQ：(<span class="red">*</span>)
								</th>
								<td>
									<s:if test="customInfo==null">
										<input type="text" id="qq" name="bizInfo.qq" value="${userSession.qq}" onblur="chkQq();" />
									</s:if>
									<s:else>
										<input type="text" id="qq" name="bizInfo.qq" value="${customInfo.bizInfo.qq}" onblur="chkQq();" />
									</s:else>
									<span id="qqMess" class="orange" <s:if test="customInfo==null || customInfo.error.qq">style="display:none;"</s:if>>请输入您的QQ号码！</span>
								</td>
							</tr>
							<tr>
								<th>
									您的电话：(<span class="red">*</span>)
								</th>
								<td>
									<s:if test="customInfo==null">
										<input type="text" id="phoneNum" name="bizInfo.phoneNum" value="${userSession.phoneNum}" onblur="chkphoneNum();" />
									</s:if>
									<s:else>
										<input type="text" id="phoneNum" name="bizInfo.phoneNum" value="${customInfo.bizInfo.phoneNum}" onblur="chkphoneNum();" />
									</s:else>
									<span id="phoneNumMess" class="orange" <s:if test="customInfo==null || customInfo.error.phoneNum">style="display:none;"</s:if>>请输入您的手机号码！</span>
								</td>
							</tr>
						</table>
						<div class="fbxx_title mt10">
							<span class="ico_7">&nbsp;</span> 宝贝介绍
						</div>
						
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<th width="18%">
									宝贝描述：
								</th>
								<td>
									<dl class="zhmm_spxx">
										<s:iterator value="detailsList">
											<s:if test="isUser==1 && parent.isUser==1">
												<dt>${attributeName}:</dt>
												<s:iterator value="child" id="chd">
													<s:if test="isUser==1">
														<dd><em class="mintxt">${attributeName}:</em><input type="text" id="${formName}" name="${formName}" value="<s:property value="attributeInfo.map.get('%{#chd.attributeName})'" />" /></dd>
													</s:if>
												</s:iterator>
											</s:if>
										</s:iterator>
									</dl>
								</td>
							</tr>
						</table>
					</div>
					<div class="pd10 tc clear">
						<s:if test="game!=null">
							<input type="hidden" name="gameID" value="${game.id}">
						</s:if>
						<s:else>
							<input type="hidden" name="serverID" value="${server.id}">
						</s:else>
						<input type="hidden" name="gameKindID" value="${gameKind.id}">
						<input type="hidden" id="content" name="customInfo.content" />
						<input type="hidden" name="typeID" value="${typeID}">
						<button type="submit" class="orangebutton_big ml20" onclick="return chkPublicCustom();">
							确认无误,提交
						</button>
					</div>
				</form>
				<div class="blank10" style="height:30px"></div>
			</div>
		</div>
		<script type="text/javascript">
			function chkContent(){
				var content="";
				<s:iterator value="detailsList">
					<s:if test="isUser==1 && parent.isUser==1">
						<s:iterator value="child" id="chd">
							<s:if test="isUser==1">
								content=content+"${attributeName}"+"::"+$("${formName}").value.Trim()+";;";
							</s:if>
						</s:iterator>
					</s:if>
				</s:iterator>
				$("content").value=content;
				return true;
			}
		</script>
		
	</body>
</html>

