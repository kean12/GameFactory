<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/index/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<body>
		<div class="contaner tc">
			<div class=" ptb10">
				您的位置：
				<a href="${ctx}/index.shtml" class="blue">首页</a> >
				<a href="${ctx}/user/home.shtml" class="blue">用户中心</a> > 充值
			</div>

			<div class="user_cz_l">
				<dl>
					<dt>
						<a id="mode0" onclick="showmenu(0)" style="cursor: pointer;" class="red">银行卡在线充值</a>
					</dt>
					<dt>
						<a onclick="showmenu(1)" style="cursor: pointer;">点卡在线充值</a>
					</dt>
					<dd>
						<a id="mode1" onclick="showmenu(1)" style="cursor: pointer;">神州行充值</a>
					</dd>
					<dd>
						<a id="mode2" onclick="showmenu(2)" style="cursor: pointer;">盛大游戏卡充值</a>
					</dd>
					<dd>
						<a id="mode3" onclick="showmenu(3)" style="cursor: pointer;">征途游戏卡充值</a>
					</dd>
					<dd>
						<a id="mode4" onclick="showmenu(4)" style="cursor: pointer;">骏网一卡通充值</a>
					</dd>
					<dd>
						<a id="mode5" onclick="showmenu(5)" style="cursor: pointer;">QQ币充值</a>
					</dd>
				</dl>
			</div>
		<form action="${ctx}/user/charge/deposit_verify.shtml" method="post">	
			<div id="pay0" class="user_cz_r">
				<div class="user_cz_r_top11">
					<span class="cz_top01">1、选择银行并填写金额</span><span class="cz_top04">2、确充值信息</span><span
						class="cz_top05">3、进入银行充值 </span>
				</div>
				
				<div class="ptb10 f14">
					<span class="red">*</span>请选择网上银行：
				</div>
				<table width="100%" border="0" cellspacing="5" cellpadding="0">
					<tr>
						<td align="center">
							<input type="radio" name="bank" id="radio1" value="ICBC-NET" checked="checked" />
						</td>
						<td>
							<label for="radio1" onclick="$('radio1').checked=true;">
								<img src="${ctx}/images/bank/ICBC-NET.gif" alt="中国工商银行" />
							</label>
						</td>
						<td align="center">
							<input type="radio" name="bank" id="radio2" value="CMBCHINA-NET"  />
						</td>
						<td>
							<label for="radio2" onclick="$('radio2').checked=true;">
								<img src="${ctx}/images/bank/CMBCHINA-NET.gif" alt="招商银行" />
							</label>
						</td>
						<td align="center">
							<input type="radio" name="bank" id="radio3" value="CCB-NET" />
						</td>
						<td>
							<label for="radio3" onclick="$('radio3').checked=true;">
								<img src="${ctx}/images/bank/CCB-NET.gif" alt="中国建设银行" />
							</label>
						</td>
						<td align="center">
							<input type="radio" name="bank" id="radio4" value="ABC-NET"  />
						</td>
						<td>
							<label for="radio4" onclick="$('radio4').checked=true;">
								<img src="${ctx}/images/bank/ABC-NET.gif" alt="中国农业银行" />
							</label>
						</td>
					</tr>
					<tr>
						<td align="center">
							<input type="radio" name="bank" id="radio5" value="BOC-NET"  />
						</td>
						<td>
							<label for="radio5" onclick="$('radio5').checked=true;">
								<img src="${ctx}/images/bank/BOC-NET.gif" alt="中国银行" />
							</label>
						</td>
						<td align="center">
							<input type="radio" name="bank" id="radio6" value="BOCO-NET"  />
						</td>
						<td>
							<label for="radio6" onclick="$('radio6').checked=true;">
								<img src="${ctx}/images/bank/BOCO-NET.gif" alt="交通银行" />
							</label>
						</td>
						<td align="center">
							<input type="radio" name="bank" id="radio7" value="SPDB-NET"  />
						</td>
						<td>
							<label for="radio7" onclick="$('radio7').checked=true;">
								<img src="${ctx}/images/bank/SPDB-NET.gif" alt="上海浦东发展银行" />
							</label>
						</td>
						<td align="center">
							<input type="radio" name="bank" id="radio8" value="GDB-NET" onclick="showHtml('');" />
						</td>
						<td>
							<label for="radio8" onclick="$('radio8').checked=true;">
								<img src="${ctx}/images/bank/GDB-NET.gif" alt="广东发展银行" />
							</label>
						</td>
					</tr>
					<tr>
						<td align="center">
							<input type="radio" name="bank" id="radio9" value="ECITIC-NET" onclick="showHtml('');"  />
						</td>
						<td>
							<label for="radio9" onclick="$('radio9').checked=true;">
								<img src="${ctx}/images/bank/ECITIC-NET.gif" alt="中信银行" />
							</label>
						</td>
						<td align="center">
							<input type="radio" name="bank" id="radio10" value="CEB-NET"  />
						</td>
						<td>
							<label for="radio10" onclick="$('radio10').checked=true;">
								<img src="${ctx}/images/bank/CEB-NET.gif" alt="光大银行" />
							</label>
						</td>
						
						<td align="center">
							<input type="radio" name="bank" id="radio11" value="CIB-NET"  />
						</td>
						<td>
							<label for="radio11" onclick="$('radio11').checked=true;">
								<img src="${ctx}/images/bank/CIB-NET.gif" alt="兴业银行" />
							</label>
						</td>
						<td align="center">
							<input type="radio" name="bank" id="radio12" value="SDB-NET"  />
						</td>
						<td>
							<label for="radio12" onclick="$('radio12').checked=true;">
								<img src="${ctx}/images/bank/SDB-NET.gif" alt="深圳发展银行" />
							</label>
						</td>
					</tr>
					
					<tr>
						<td align="center">
							<input type="radio" name="bank" id="radio13" value="CMBC-NET"  />
						</td>
						<td>
							<label for="radio13" onclick="$('radio13').checked=true;">
								<img src="${ctx}/images/bank/CMBC-NET.gif" alt="中国民生银行" />
							</label>
						</td>
						<td align="center">
							<input type="radio" name="bank" id="radio14" value="NBCB-NET"  />
						</td>
						<td>
							<label for="radio14" onclick="$('radio14').checked=true;">
								<img src="${ctx}/images/bank/NBCB-NET.gif" alt="宁波银行" />
							</label>
						</td>
						
						<td align="center">
						</td>
						<td>
						</td>
						<td align="center">
						</td>
						<td>
						</td>
					</tr>
				</table>
			</div>
			
			<div id="pay1" class="user_cz_r">
				<div class="user_cz_r_top11">
					<span class="cz_top01">1、填写金额</span><span class="cz_top02">2、确认充值信息</span><span
						class="cz_top03">3、进入网上充值 </span>
				</div>
				<div class="blank10"></div>
				<div class="attention bbs_orange"
					style="line-height: 150%; font-size: 12px">
					充值时，请注意购买指定的游戏点卡，并仔细核对手中点卡面值与您选择充值的面值是否相同，如不同，则会造成充值失败，并且卡内金额无法退还。
				</div>
				<div class="ptb10 f14 mt10 bbd_ccc">
					<span class="red">* </span>请选择充值卡类：
				</div>
				<div class="dk_list">
					<img src="${ctx}/images/bank/SZX-NET.gif" onclick="showmain(0);"/><img src="${ctx}/images/bank/SNDACARD-NET.gif" onclick="showmain(1);"/><img src="${ctx}/images/bank/ZHENGTU-NET.gif" onclick="showmain(2);"/><img src="${ctx}/images/bank/JUNNET-NET.gif" onclick="showmain(3);"/><img src="${ctx}/images/bank/QQCARD-NET.gif" onclick="showmain(4);"/>
				</div>
				<div id="dkinfo">
					<div>
						<h2>
							神州行卡充值面额及实充金额
						</h2>
						10元 &gt;&gt; 神州行卡<em>10</em>元面值实充<em>8.5</em> 元人民币<br />
						20元 &gt;&gt; 神州行卡<em>20</em>元面值实充<em>17</em> 元人民币<br />
						30元 &gt;&gt; 神州行卡<em>30</em>元面值实充<em>25.5</em> 元人民币<br />
						50元 &gt;&gt; 神州行卡<em>50</em>元面值实充<em>42.5</em> 元人民币<br />
						100元 &gt;&gt; 神州行卡<em>100</em>元面值实充<em>85</em> 元人民币<br />
						300元 &gt;&gt; 神州行卡<em>300</em>元面值实充<em>255</em> 元人民币<br />
						500元 &gt;&gt; 神州行卡<em>500</em>元面值实充<em>425</em> 元人民币<br />
					</div>
					<div style="display: none">
						<h2>
							盛大游戏卡充值面额及实充金额
						</h2>
						10元 &gt;&gt; 盛大游戏卡<em>10</em>元面值实充<em>7.5</em> 元人民币<br />
						30元 &gt;&gt; 盛大游戏卡<em>30</em>元面值实充<em>22.5</em> 元人民币<br />
						45元 &gt;&gt; 盛大游戏卡<em>45</em>元面值实充<em>33.75</em> 元人民币<br />
						50元 &gt;&gt; 盛大游戏卡<em>50</em>元面值实充<em>37.5</em> 元人民币<br />
						100元 &gt;&gt; 盛大游戏卡<em>100</em>元面值实充<em>75</em> 元人民币
					</div>
					<div style="display: none">
						<h2>
							征途游戏卡充值面额及实充金额
						</h2>
						10 元 &gt;&gt; 征途游戏卡<em>10 </em>元面值实充<em>7.5</em> 元人民币<br />
						20 元 &gt;&gt; 征途游戏卡<em>20 </em>元面值实充<em>15</em> 元人民币<br />
						30 元 &gt;&gt; 征途游戏卡<em>30 </em>元面值实充<em>22.5</em> 元人民币<br />
						50 元 &gt;&gt; 征途游戏卡<em>50 </em>元面值实充<em>37.5</em> 元人民币<br />
						60 元 &gt;&gt; 征途游戏卡<em>60 </em>元面值实充<em>45</em> 元人民币<br />
						100元 &gt;&gt; 征途游戏卡<em>100</em>元面值实充<em>75</em> 元人民币<br />
						300元 &gt;&gt; 征途游戏卡<em>300</em>元面值实充<em>225</em> 元人民币<br />
						468元 &gt;&gt; 征途游戏卡<em>468</em>元面值实充<em>351</em> 元人民币
					</div>
					<div style="display: none">
						<h2>
							骏网一卡通充值面额及实充金额
						</h2>
						10 元 &gt;&gt; 骏网一卡通<em>10</em> 元面值实充<em>7.5</em> 元人民币<br />
						15 元 &gt;&gt; 骏网一卡通<em>15</em> 元面值实充<em>11.25</em> 元人民币<br />
						30 元 &gt;&gt; 骏网一卡通<em>30</em> 元面值实充<em>22.5</em> 元人民币<br />
						50 元 &gt;&gt; 骏网一卡通<em>50</em> 元面值实充<em>37.5</em> 元人民币<br />
						100元 &gt;&gt; 骏网一卡通<em>100</em> 元面值实充<em>75</em> 元人民币<br />
					</div>
					<div style="display: none">
						<h2>
							Q币卡充值面额及实充金额
						</h2>
						5 元 &gt;&gt; Q币卡<em>5 </em>元面值实充<em>3</em> 元人民币<br />
						10元 &gt;&gt; Q币卡<em>10</em> 元面值实充<em>6</em> 元人民币<br />
						15元 &gt;&gt; Q币卡<em>15</em> 元面值实充<em>9</em> 元人民币<br />
						20元 &gt;&gt; Q币卡<em>20</em> 元面值实充<em>12</em> 元人民币<br />
						30元 &gt;&gt; Q币卡<em>30</em> 元面值实充<em>18</em> 元人民币<br />
						60元 &gt;&gt; Q币卡<em>60 </em>元面值实充<em>36</em> 元人民币
					</div>
				</div>
	
			</div>
			
			<div class="user_cz_r">
				<div class="ptb10 f14">
					<span class="red">* </span>请输入充值金额：
				</div>
				<div>
					&nbsp;&nbsp;
					<input id="p3_Amt" maxlength="9" id="money" name="money" value="" />
					&nbsp;元&nbsp;&nbsp;
					<span class="orange" id="spanMsg">(请您正确填写充值金额！)</span>
					<br />
				</div>
				<div class="pd10">
					<input type="radio" id="radio50" name="bank" value="SZX-NET" style="display:none;" />
					<input type="radio" id="radio51" name="bank" value="SNDACARD-NET" style="display:none;" />
					<input type="radio" id="radio52" name="bank" value="ZHENGTU-NET" style="display:none;" />
					<input type="radio" id="radio53" name="bank" value="JUNNET-NET" style="display:none;" />
					<input type="radio" id="radio54" name="bank" value="QQCARD-NET" style="display:none;" />
					<input type="submit" class="orangebutton_big" value="下一步" onclick="return checkForm();" />
				</div>
				<font color="red"></font>
				<div class="clear"></div>
			</div>
		</form>
		</div>
		
		
<script type="text/javascript" language="javascript">
function checkForm(){
	var money=$("money").value.Trim();
	if(money=="" || isNaN(money) || money<=0){
		alertDialog('请您正确填写充值金额！');
		return false;
	}
	$("money").value=money;
}

function showmain(b){
	var menu_num=document.getElementById("dkinfo").getElementsByTagName("div");
	for(i=0;i<menu_num.length;i++){
		menu_num[i].style.display=i==b?"block":"none";
		if(b==i){
			$("radio5"+i).checked=true;
		}
	}
}

function showmenu(num){
	for(var i=0;i<6;i++){
		if(i==num){
			$("mode"+i).className="red"
			if(num==0){
				showDiv("pay"+i);
				$("radio1").checked=true;
			}else{
				showDiv("pay1");
				showmain(i-1);
			}
		}else{
			$("mode"+i).className=""
			if(i<2){
				closeDiv("pay"+i);
			}
		}
	}
}
showmenu(0);
</script>
	</body>
</html>
