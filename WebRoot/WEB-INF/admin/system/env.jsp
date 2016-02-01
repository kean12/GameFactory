<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>JSP 探针</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style>
A {
	COLOR: #000000;
	TEXT-DECORATION: none
}
A:hover {
	COLOR: #3399cc
}
body,td,span {
	font-size: 9pt
}
.input {
	BACKGROUND-COLOR: #ffffff;
	BORDER: #3399cc 1px solid;
	FONT-SIZE: 9pt
}
.backc {
	BACKGROUND-COLOR: #3399cc;
	BORDER: #3399cc 1px solid;
	FONT-SIZE: 9pt;
	color: white
}
.PicBar {
	background-color: #3399cc;
	border: 1px solid #000000;
	height: 12px;
}
.tableBorder {
	BORDER-RIGHT: #3399cc 1px solid;
	BORDER-TOP: #3399cc 1px solid;
	BORDER-LEFT: #3399cc 1px solid;
	BORDER-BOTTOM: #3399cc 1px solid;
	BACKGROUND-COLOR: #ffffff;
	WIDTH: 760;
	float:left;
	margin-bottom:10px
}
.divcenter {
	position: absolute;
	height: 30px;
	z-index: 1000;
	left: 101px;
	top: 993px;
}
</STYLE>
<script language="javascript">
function showsubmenu(sid)
{
whichEl = eval("submenu" + sid);
if (whichEl.style.display == "none")
{
eval("submenu" + sid + ".style.display=\"\";");
eval("txt" + sid + ".innerHTML=\"<a href='#' title='关闭此项'><font face='Wingdings' color=#FFFFFF>x</font></a>\";");
}
else
{
eval("submenu" + sid + ".style.display=\"none\";");
eval("txt" + sid + ".innerHTML=\"<a href='#' title='打开此项'><font face='Wingdings' color=#FFFFFF>y</font></a>\";");
}
}
</script>
	</head>
	<body topmargin="0" leftmargin="0">
		<div style="text-align:left; width:760px; padding:10px">
			选项：
			<a href="#ServerInfo">服务器相关参数</a> |
			<a href="#JAVAInfo">JAVA相关参数</a> |
			<a href="#ServerAbility">服务器运算能力</a> |
			<a href="javascript:location.reload()">刷新</a><a name="ServerInfo"></a>
			
			<table border="0" cellpadding="0" cellspacing="1" class="tableBorder" align="left">
				<tr>
					<td height="22" align="center" bgcolor="#3399cc" onclick="showsubmenu(0)">
						<font color=#FFFFFF><strong>服务器相关参数</strong></font>
						<a href="#top" title="返回顶部"><font face='Webdings' color=#FFFFFF>5</font></a>
						<span id=txt0 name=txt0><a href='#' title='关闭此项'><font face='Wingdings' color=#FFFFFF>x</font></a></span>
					</td>
				</tr>
				<tr>
					<td style="" id='submenu0'>
						<table border="0" width="100%" cellspacing="1" cellpadding="3" bgcolor="#3399cc">
							<tr bgcolor="#FFFFFF" height="22">
								<td width="130">服务器名</td>
								<td colspan="3" height="22">
									<s:property value="serverName" />(<s:property value="remoteAddr" />)
								</td>
							</tr>
							<tr bgcolor="#FFFFFF" height="22">
								<td>服务器操作系统</td>
								<td colspan="3">
									<s:property value="@com.game.util.web.EnvUtil@get('os.name')"/>
									<s:property value="@com.game.util.web.EnvUtil@get('os.version')"/>
									<s:property value="@com.game.util.web.EnvUtil@get('sun.os.patch.level')"/>
								</td>
							</tr>
							<tr bgcolor="#FFFFFF" height="22">
								<td>服务器操作系统类型</td>
								<td>
									<s:property value="@com.game.util.web.EnvUtil@get('os.arch')"/>
								</td>
								<td>服务器操作系统模式</td>
								<td>
									<s:property value="@com.game.util.web.EnvUtil@get('sun.arch.data.model')"/>位
								</td>
							</tr>
							<tr bgcolor="#FFFFFF" height="22">
								<td>服务器所在地区</td>
								<td>
									<s:property value="@com.game.util.web.EnvUtil@get('user.country')"/>
								</td>
								<td>服务器语言</td>
								<td>
									<s:property value="@com.game.util.web.EnvUtil@get('user.language')"/>
								</td>
							</tr>
							<tr bgcolor="#FFFFFF" height="22">
								<td>服务器时区</td>
								<td>
									<s:property value="@com.game.util.web.EnvUtil@get('user.timezone')"/>
								</td>
								<td>服务器时间</td>
								<td><s:property value="serverTime" /></td>
							</tr>
							<tr bgcolor="#FFFFFF" height="22">
								<td>服务器解译引擎</td>
								<td width="170"><s:property value="serverInfo" /></td>
								<td width="130">服务器端口</td>
								<td width="170"><s:property value="serverPort" /></td>
							</tr>
							<tr bgcolor="#FFFFFF" height="22">
								<td height="22">当前用户</td>
								<td height="22" colspan="3">
									<s:property value="@com.game.util.web.EnvUtil@get('user.name')"/>
								</td>
							</tr>
							<tr bgcolor="#FFFFFF" height="22">
								<td>用户目录</td>
								<td colspan="3">
									<s:property value="@com.game.util.web.EnvUtil@get('user.dir')"/>
								</td>
							</tr>
							<tr bgcolor="#FFFFFF" height="22">
								<td align=left bgcolor="#FFFFFF">本文件实际路径</td>
								<td height="8" colspan="3" bgcolor="#FFFFFF"><s:property value="realPath" /></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<a name="JAVAInfo" id="JAVAInfo"></a>
			<br>
			<table border="0" cellpadding="0" cellspacing="1" class="tableBorder" align="left">
				<tr>
					<td height="22" align="center" bgcolor="#3399cc" onclick="showsubmenu(1)">
						<font color=#FFFFFF><strong>JAVA相关参数</strong></font>
						<a href="#top" title="返回顶部"><font face='Webdings' color=#FFFFFF>5</font>
						</a>
						<span id=txt1 name=txt1>
							<a href='#' title='关闭此项'><font face='Wingdings' color=#FFFFFF>x</font></a>
						</span>
					</td>
				</tr>
				<tr>
					<td style="" id='submenu1'>
						<table border=0 width=100% cellspacing=1 cellpadding=3 bgcolor="#3399cc">
							<tr bgcolor="#a8e1f3" height="22">
								<td width="30%">名称</td>
								<td width="50%" height="22">英文名称</td>
								<td width="20%" height="22">版本</td>
							</tr>
							<tr bgcolor="#FFFFFF" height="22">
								<td width="30%">JAVA运行环境名称</td>
								<td width="50%" height="22">
									<s:property value="@com.game.util.web.EnvUtil@get('java.runtime.name')"/>
								</td>
								<td width="20%" height="22">
									<s:property value="@com.game.util.web.EnvUtil@get('java.runtime.version')"/>
								</td>
							</tr>
							<tr bgcolor="#FFFFFF" height="22">
								<td width="30%">JAVA运行环境说明书名称
								</td>
								<td width="50%" height="22">
									<s:property value="@com.game.util.web.EnvUtil@get('java.specification.name')"/>
								</td>
								<td width="20%" height="22">
									<s:property value="@com.game.util.web.EnvUtil@get('java.specification.version')"/>
								</td>
							</tr>
							<tr bgcolor="#FFFFFF" height="22">
								<td width="30%">JAVA虚拟机名称</td>
								<td width="50%" height="22">
									<s:property value="@com.game.util.web.EnvUtil@get('java.vm.name')"/>
								</td>
								<td width="20%" height="22">
									<s:property value="@com.game.util.web.EnvUtil@get('java.vm.version')"/>
								</td>
							</tr>
							<tr bgcolor="#FFFFFF" height="22">
								<td width="30%">JAVA虚拟机说明书名称</td>
								<td width="50%" height="22">
									<s:property value="@com.game.util.web.EnvUtil@get('java.vm.specification.name')"/>
								</td>
								<td width="20%" height="22">
									<s:property value="@com.game.util.web.EnvUtil@get('java.vm.specification.version')"/>
								</td>
							</tr>
							<tr bgcolor="#FFFFFF" height="22">
								<td height="22">
									JAVA虚拟机剩余内存：
								</td>
								<td height="22" colspan="2">
									<img align=absmiddle class=PicBar width='<s:property value="@com.game.util.web.EnvUtil@proportion()" />%'>
									<s:property value="@com.game.util.web.EnvUtil@freememory()" />M
								</td>
							</tr>
							<tr bgcolor="#FFFFFF" height="22">
								<td height="22">
									JAVA虚拟机分配内存
								</td>
								<td height="22" colspan="2">
									<img align=absmiddle class=PicBar width='85%'>
									<s:property value="@com.game.util.web.EnvUtil@totalMemory()" />M
								</td>
							</tr>
						</table>
						<table border=0 width=100% cellspacing=1 cellpadding=3
							bgcolor="#3399cc">
							<tr bgcolor="#a8e1f3" height="22">
								<td width="30%">
									参数名称
								</td>
								<td width="70%" height="22">
									参数路径
								</td>
							</tr>
							<tr bgcolor="#FFFFFF" height="22">
								<td width="30%">
									java.class.path
								</td>
								<td width="70%" height="22">
									<s:property value="@com.game.util.web.EnvUtil@get('java.class.path')" />
								</td>
							</tr>
							<tr bgcolor="#FFFFFF" height="22">
								<td width="30%">
									java.home
								</td>
								<td width="70%" height="22">
									<s:property value="@com.game.util.web.EnvUtil@get('java.home')" />
								</td>
							</tr>
							<tr bgcolor="#FFFFFF" height="22">
								<td width="30%">
									java.endorsed.dirs
								</td>
								<td width="70%" height="22">
									<s:property value="@com.game.util.web.EnvUtil@get('java.endorsed.dirs')" />
								</td>
							</tr>
							<tr bgcolor="#FFFFFF" height="22">
								<td width="30%">
									java.library.path
								</td>
								<td width="70%" height="22">
									<s:property value="@com.game.util.web.EnvUtil@get('java.library.path')" />
								</td>
							</tr>
							<tr bgcolor="#FFFFFF" height="22">
								<td width="30%">
									java.io.tmpdir
								</td>
								<td width="70%" height="22">
									<s:property value="@com.game.util.web.EnvUtil@get('java.io.tmpdir')" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<br />
			<a name="ServerAbility" id="ServerAbility"></a>
			<table border="0" cellpadding="0" cellspacing="1" class="tableBorder" align="left">
				<tr>
					<td height="22" align="center" bgcolor="#3399cc" onclick="showsubmenu(3)">
						<font color=#FFFFFF><strong>服务器运算能力</strong></font>
						<a href="#top" title="返回顶部"><font face='Webdings' color=#FFFFFF>5</font></a>
						<span id=txt3 name=txt3><a href='#' title='关闭此项'><font face='Wingdings' color=#FFFFFF>x</font></a></span>
					</td>
				</tr>
				<tr>
					<td style="" id='submenu3'>
						<table border="0" width="100%" cellspacing="1" cellpadding="3" bgcolor="#3399cc">
							<tr bgcolor="#a8e1f3" height="22">
								<td colspan="3">
									
									<font face='Webdings'>4</font> 让服务器执行300万次加法(
									<font color="#000000">整数运算</font>)和20万次开方(浮点运算)，记录其所使用的时间。
								</td>
							</tr>
							<tr height="22" bgcolor="#FFFFFF">
								<td align=center bgcolor="#FFFFFF">
									<font color="#000000">可 供 参 考 的 服 务 器 列 表</font>
								</td>
								<td align="center">
									整数运算
								</td>
								<td align="center">
									浮点运算
								</td>
							</tr>
							<tr bgcolor="#FFFFFF" height="22">
								<td align=left>公司的电脑(CPU:Celeron 1G 内存:256M)</td>
								<td align="center">60 毫秒</td>
								<td align="center">70 毫秒</td>
							</tr>
							<tr bgcolor="#FFFFFF" height="22">
								<td align=left>家里的电脑(CPU:Duron 1G 内存:384M)</td>
								<td align="center">20 毫秒</td>
								<td align="center">10 毫秒</td>
							</tr>
							<tr bgcolor="#FFFFFF" height="22">
								<td align=left>中国网聚服务器 (CPU:Intel Pentium III 1G 内存:768M)</td>
								<td align="center">20 毫秒</td>
								<td align="center">3 毫秒</td>
							</tr>
							<tr bgcolor="#FFFFFF" height="22">
								<td align=left>IBM俱乐部 (CPU:IIntel(R) Celeron(R) CPU 1.70G 内存:256M)</a></td>
								<td align="center">3 毫秒</td>
								<td align="center">7 毫秒</td>
							</tr>
							<tr bgcolor="#FFFFFF" height=25>
								<td align=left>
									<font color=red>您正在使用的这台服务器</font>
									<INPUT type="button" class=backc onclick="javascript:location.reload()" value="重新测试">
								</td>
								<td align="center">
									<font color=red><s:property value="@com.game.util.web.EnvUtil@testNoat()" /> 毫秒</font>
								</td>
								<td align="center">
									<font color=red><s:property value="@com.game.util.web.EnvUtil@testSqrt()" /> 毫秒</font>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</body>
	
</html>