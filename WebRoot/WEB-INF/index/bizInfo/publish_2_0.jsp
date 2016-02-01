<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
<style type="text/css">
        .preview_box{padding:5px 0; border-bottom:#ccc 1px dotted;}
        .preview_fake{ /* 该对象用户在IE下显示预览图片 */     
		    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);
		    float:left; padding:3px; border:#ccc 1px solid; background:#fff; margin-right:10px; width:90px; height:60px; text-align: center
		}  
		.preview_size_fake{ /* 该对象只用来在IE下获得图片的原始尺寸，无其它用途 */
		    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);       
		    display:none;
		}     
		.scjc{margin-top:10px; padding:10px; margin-left:0; border:#ccc 1px solid}
		.preview_input{width:500px;float:left;}
		.preview_input strong{display: block; padding:5px 0}
</style>
<script type="text/javascript" src="${ctx}/js/image.js"></script>
<script type="text/javascript">
	function chk_pic(flag,picDiv){
		if(flag){
			document.getElementById(picDiv).style.display="";
		}else{
			document.getElementById(picDiv).style.display="none";
		}
	}
</script>
		<div class="contaner tc">
			<div>
				<img src="${ctx}/images/fb02.gif" width="951" height="34" />
			</div>
			<form name="publish_search_form" action="${ctx}/user/trade/bizInfo/publish.shtml" method="post">
				<s:if test="game!=null">
					<input type="hidden" name="gameName" value="${game.gameName}">
				</s:if>
				<s:else>
					<input type="hidden" name="gameName" value="${server.area.game.gameName}">
				</s:else>
			</form>
			<form name="publish_data_form" action="${ctx}/user/trade/bizInfo/publish_data_save.shtml" method="post" enctype="multipart/form-data">
			<div class="bbs_orange tl mt10">
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
								<a href="#" class="green_u" onclick="javascript:document.publish_search_form.submit();">[更改分类]</a>
								<span class="orange">${session.publishErrorInfo.bizKind}</span>
							</td>
						</tr>
						
						<tr>
							<th>
								宝贝标题：(<span class="red">*</span>)
							</th>
							<td>
								<input type="text" name="bizInfo.title" size="60" value="${session.bizInfo.title }" />
								限定在30个汉字内（60个字符）
								<span class="orange">${session.publishErrorInfo.title}</span>
							</td>
						</tr>
						<tr>
							<th>
								每件宝贝数量：(<span class="red">*</span>)
							</th>
							<td>
								<input type="text" name="bizInfo.amount" value="${session.bizInfo.amount}" />
								${gameKind.unit}
								<span class="orange">${session.publishErrorInfo.amount}</span>
							</td>
						</tr>
						<tr>
							<th>
								一口价：(<span class="red">*</span>)
							</th>
							<td>
								<input type="text" name="bizInfo.price" value="${session.bizInfo.price }" />
								元 
								<span class="orange">${session.publishErrorInfo.price}</span>
							</td>
						</tr>
						<tr>
							<th>
								宝贝件数：(<span class="red">*</span>)
							</th>
							<td>
								<input type="text" name="bizInfo.stock" value="${session.bizInfo.stock}" />
								件
								<span class="orange">${session.publishErrorInfo.stock}</span>
							</td>
						</tr>
						
					<%-- 	
						<tr>
							<th>
								比例：(<span class="red">*</span>)
							</th>
							<td>
								每 <input type="text" name="bizInfo.amount" size="3" value="${session.bizInfo.amount}" /> / <input type="text" name="bizInfo.unit_price" value="100" size="2" readonly="readonly" />元
								<span class="orange">${session.publishErrorInfo.amount} ${session.publishErrorInfo.unit_price}</span>
							</td>
						</tr>
					--%>
					
					
						<tr>
							<th>
								上传宝贝图片：&nbsp;&nbsp;&nbsp;
							</th>
							<td>
								<input type="checkbox" onclick="chk_pic(this.checked,'Treasure picturess');"  /> 
							</td>
						</tr>
						<tr id="Treasure picturess" style="display:none;">
							<th style="height:180px">
								宝贝图片：&nbsp;&nbsp;&nbsp;
							</th>
							<td>
								<div class="preview_box">
											<div  id="preview_fake" class="preview_fake"/>
												<img id="preview" class="preview" src="${ctx}/images/slt.gif" align="left"  onload='loaded(this,90,60)'/>
												<img id="preview_size_fake" class="preview_size_fake" src="${ctx}/images/slt.gif" align="left"  onload='loaded(this,90,60)' />
											</div>
											<div class="scjc" style=" border:0; text-align:left">
												<input id="file" name="upload" type="file" class="vam mr5" size="50" onchange="onUploadImgChange('preview','preview_fake','preview_size_fake',this);" />
											</div>
									</div>				
								<div class="preview_box">		
											<div  id="preview_fake1" class="preview_fake"/>
												<img id="preview1" class="preview" src="${ctx}/images/slt.gif" align="left"  onload='loaded(this,90,60)'/>
												<img id="preview_size_fake1" class="preview_size_fake" src="${ctx}/images/slt.gif" align="left"  onload='loaded(this,90,60)' />
											</div>
											<div class="scjc" style=" border:0; text-align:left">
												<input id="file" name="upload" type="file" class="vam mr5" size="50" onchange="onUploadImgChange('preview1','preview_fake1','preview_size_fake1',this);" />
											</div>
								</div>	
								<div class="preview_box" style="border-bottom:0">
											<div  id="preview_fake2" class="preview_fake"/>
												<img id="preview2" class="preview" src="${ctx}/images/slt.gif" align="left"  onload='loaded(this,90,60)'/>
												<img id="preview_size_fake2" class="preview_size_fake" src="${ctx}/images/slt.gif" align="left"  onload='loaded(this,90,60)' />
											</div>
										
											<div class="scjc" style=" border:0; text-align:left">
												<input id="file" name="upload" type="file" class="vam mr5" size="50" onchange="onUploadImgChange('preview2','preview_fake2','preview_size_fake2',this);" />
											</div>
								</div>	
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
								<s:if test="#session.bizInfo.sellModel!=1">
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
					<TBODY id="div_site" style="display: <s:if test="#session.bizInfo.sellModel==2">none</s:if><s:else>block</s:else>;">
						<tr>
							<th width="18%">
								交易地点：(<span class="red">*</span>)
							</th>
							<td>
								<input type="text" name="bizInfo.site" size="60" value="${session.bizInfo.site }" />
								<span class="orange">${session.publishErrorInfo.site}</span>
							</td>
						</tr>
					</TBODY>
						<tr>
							<th width="18%">
								有效期：(<span class="red">*</span>)
							</th>
							<td>
								<s:radio list="#{'7':'7天'}" name="bizInfo.term_of_validity" listValue="value" listKey="key" value="'7'" theme="simple"></s:radio>
								<%-- 请选择求购发布单的有效期，过期后求购发布单将自动取消！ --%>
								<span class="orange">${session.publishErrorInfo.term_of_validity}</span>
							</td>
						</tr>
						<tr>
							<th>
								方便交易时间：(<span class="red">*</span>)
							</th>
							<td>
								每天
								<s:set name="trade_time" value="#{
									'00:00':'00:00','01:00':'01:00','02:00':'02:00','03:00':'03:00','04:00':'04:00',
									'05:00':'05:00','06:00':'06:00','07:00':'07:00','08:00':'08:00','09:00':'09:00',
									'10:00':'10:00','11:00':'11:00','12:00':'12:00','13:00':'13:00','14:00':'14:00',
									'15:00':'15:00','16:00':'16:00','17:00':'17:00','18:00':'18:00','19:00':'19:00',
									'20:00':'20:00','21:00':'21:00','22:00':'22:00','23:00':'23:00','23:59':'23:59'}" />
								
								<s:select list="trade_time" name="bizInfo.tradeStart" listValue="value" listKey="key" theme="simple"   />
								到
								<s:select list="trade_time" name="bizInfo.tradeEnd" listValue="value" listKey="key" theme="simple" headerKey="23:59" headerValue="23:59" />
								点 请选择您可以上线交易的时间，如果不选择，系统将默认您24小时方便交易！
								<span class="orange">${session.publishErrorInfo.tradeTime}</span>
							</td>
						</tr>
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
								<input type="text" name="bizInfo.account" value="${session.bizInfo.account }" />
								请输入您的游戏账号！
								<span class="orange">${session.publishErrorInfo.account}</span>
							</td>
						</tr>
						<tr>
							<th>
								游戏密码：(<span class="red">*</span>)
							</th>
							<td>
								<input type="password" name="bizInfo.password" value="${session.bizInfo.password }" style="width: 150px;" />
								请输入您账号的密码！
								<span class="orange">${session.publishErrorInfo.password}</span>
							</td>
						</tr>
						
						<tr>
							<th>
								游戏角色名：(<span class="red">*</span>)
							</th>
							<td>
								<input type="text" name="bizInfo.roleName" value="${session.bizInfo.roleName }" style="width: 150px;" />
								请输入您的游戏角色名！
								<span class="orange">${session.publishErrorInfo.roleName}</span>
							</td>
						</tr>
						<tr>
							<th>
								密码锁：&nbsp;&nbsp;&nbsp;
							</th>
							<td>
								<input type="password" name="bizInfo.coded_lock" value="${session.bizInfo.coded_lock}" style="width: 150px;" />
								请输入您的密码锁！
								<span class="orange">${session.publishErrorInfo.coded_lock}</span>
							</td>
						</tr>
						<tr>
							<th>
								物品存放处：(<span class="red">*</span>)
							</th>
							<td>
								<input type="text" name="bizInfo.place" value="${session.bizInfo.place }" size="60" />
								请输入您的物品存放处！
								<span class="orange">${session.publishErrorInfo.place}</span>
							</td>
						</tr>
						
						<tr>
							<th>
								上传密保卡：&nbsp;&nbsp;&nbsp;
							</th>
							<td>
								<input type="checkbox" onclick="chk_pic(this.checked,'Crypto Card');"  /> 
							</td>
						</tr>
						<tr id="Crypto Card" style="display: none;">
							<th>
								密保卡：&nbsp;&nbsp;&nbsp;
							</th>
							<td>
								<div class="preview_box" style="border-bottom:0">
									<div  id="preview_fake10" class="preview_fake"/>
										<img id="preview10" class="preview" src="${ctx}/images/slt.gif" align="left"  onload='loaded(this,90,60)'/>
										<img id="preview_size_fake10" class="preview_size_fake" src="${ctx}/images/slt.gif" align="left"  onload='loaded(this,90,60)' />
									</div>
									<div class="scjc" style=" border:0; text-align:left">
										<input id="file" name="file" type="file" class="vam mr5" size="50" onchange="onUploadImgChange('preview10','preview_fake10','preview_size_fake10',this);" />
									</div>
								</div>
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
								<input type="text" name="bizInfo.qq" value="${userSession.qq}" />
								请输入您的QQ号码！
								<span class="orange">${session.publishErrorInfo.qq}</span>
							</td>
						</tr>
						<tr>
							<th>
								您的电话：(<span class="red">*</span>)
							</th>
							<td>
								<input type="text" name="bizInfo.phoneNum" value="${userSession.phoneNum}" />
								请输入您的手机号码！
								<span class="orange">${session.publishErrorInfo.phoneNum}</span>
							</td>
						</tr>
					</table>
					<div class="fbxx_title mt10">
						<span class="ico_7">&nbsp;</span> 宝贝介绍
					</div>
				</div>
				<dl class="txteare">
					<dt>宝贝描述：(<span class="red">*</span>)</dt>
						<dd>
						<s:if test="session.publishErrorInfo.info!=null">
							<span class="orange">${session.publishErrorInfo.info}</span>
							<br />
						</s:if>
						<script type="text/javascript" charset="utf-8" src="${ctx}/kindeditor/kindeditor.js"></script>
<style type="text/css" rel="stylesheet">
    form {
        margin: 0;
    }
    .editor {
        margin-top: 5px;
        margin-bottom: 5px;
    }
</style>
<script type="text/javascript">
    KE.show({
        id : 'content',
        resizeMode : 1,
        cssPath : '${ctx}/kindeditor/index.css'
    });
</script>
						<textarea id="content" name="bizInfo.info" style="width:730px;height:300px;visibility:hidden;">${session.bizInfo.info}</textarea>
					</dd>
				</dl>
				<div class="pd10 tc clear">
					<s:if test="game!=null">
						<input type="hidden" name="gameID" value="${game.id}">
					</s:if>
					<s:else>
						<input type="hidden" name="serverID" value="${server.id}">
					</s:else>
					<input type="hidden" name="gameKindID" value="${gameKind.id}">
					<input type="hidden" name="typeID" value="${typeID}">
				<%-- 	
					<button type="button" class="orangebutton_big">
						预览
					</button>
				--%>	
					<button type="submit" class="orangebutton_big ml20">
						确认无误,提交
					</button>
				</div>
					
				<div class="blank10" style="height:30px"></div>
			</div>
		</form>
		</div>
	</body>
</html>

