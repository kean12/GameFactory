<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="yhzx_rightside">
	<div class="yhzx_title">站内消息</div>
	<div id="rightnav">
	<ul>
	<s:if test="type==null">
		<li class="action_nav" onclick="window.location='${ctx}/user/message/message.shtml'">
			<span>查看新消息</span>
		</li>
		<li class="normal_nav" onclick="window.location='${ctx}/user/message/message.shtml?type=862798123'">
			<span>查看全部消息</span>
		</li>
	</s:if>
	<s:else>
		<li class="normal_nav" onclick="window.location='${ctx}/user/message/message.shtml'">
			<span>查看新消息</span>
		</li>
		<li class="action_nav" onclick="window.location='${ctx}/user/message/message.shtml?type=862798123'">
			<span>查看全部消息</span>
		</li>
	</s:else>
	</ul>
</div>
<div class="contentbox" id="box0" style="display: block">

<form name="message_form" action="${ctx}/user/message/deleteAll.shtml" method="post">
	<table width="100%" class="yhzx_list" style="background: none; border: 0" >
		<tr>
			<th width="10%" scope="col">
				<label onclick="selectAll();" style="cursor: pointer;">全选</label>/<label onclick="selectInvert();" style="cursor: pointer;">反选</label>
			</th>
			<th width="59%" scope="col">标题	</th>
			<th width="16%" scope="col">作者</th>
			<th width="9%" scope="col">状态</th>
			<th width="6%" scope="col">操作</th>
		</tr>
	<s:iterator value="postList">
		<tr>
			<td align="center" valign="middle">
				<label>
					<input type="checkbox" name="idKey" value="${id}"  />
				</label>
			</td>
			<td class="yhzx_list_title">
				<a href="${ctx}/user/message/read.shtml?postID=${id}">${message.title}</a>
			</td>
			<td>
				<div>${message.addresser}<br />
					${message.time}
				</div>
			</td>
			<td align="center">
				<s:if test="state==1">已读</s:if>
				<s:else>
					<img src="${ctx}/images/post_new2.gif">						
				</s:else>
			</td>
			<td align="center">
				<a href="javascript:deletemessage(${id});" class="blue">删除</a>
			</td>
		</tr>
	</s:iterator>
		<tr>
			<td colspan="5">
				<input type="button" name="select" value="全 选" onclick="selectAll();" class="orangebutton1">
				&nbsp;
				<input type="button" value="反 选" onclick="selectInvert();" class="orangebutton1" />
				&nbsp;
				<input type="button" value="删除" onclick="return check();" class="orangebutton1">
				
				<input type="hidden" name="goPage" value="${page.currentPage }" />
				<input type="hidden" name="type" value="${type }" />
			</td>
		</tr>
	</table>
</form>	
	
	<div class="manu">
		<jsp:include page="/WEB-INF/index/common/pagination.jsp"></jsp:include>
		<form name="pageForm" action="${ctx}/user/message/message.shtml" method="get">
			<input type="hidden" id="goPage" name="goPage" />
			<input type="hidden" name="type" value="${type}" />
			</form>
		</div>
	</div>
</div>
<div class="clear"></div>
<script type="text/javascript">
function selectAll(){
	var arr=document.getElementsByName("idKey");
	for(var i=0;i<arr.length;i++){
		arr[i].checked="checked";
	}
}
function selectInvert(){
	var arr=document.getElementsByName("idKey");
	for(var i=0;i<arr.length;i++){
		if(arr[i].checked==true){
			arr[i].checked="";
		}else{
			arr[i].checked="checked";
		}
	}						
}

function check(){
	art.dialog({content:"删除后数据将无法恢复，您确定要删除？", fixed:true,lock:true,style:'succeed',width:'280',height:'80'},
		function(){
			document.message_form.submit();
		},
		function(){}
	);
}

function deletemessage(postID){
	art.dialog({content:"删除后数据将无法恢复，您确定要删除？", fixed:true,lock:true,style:'succeed',width:'280',height:'80'},
		function(){
			window.location="${ctx}/user/message/delete.shtml?goPage=${page.currentPage}&postID="+postID+"&type=${type}";
		},
		function(){}
	);
}
</script>

