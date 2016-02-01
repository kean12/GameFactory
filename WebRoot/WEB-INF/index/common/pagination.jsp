<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="page.currentPage!=1">
	<a href="javascript:jumpPage(1);">首页</a>
	<a href="javascript:jumpPage(${page.currentPage-1});">&lt;  上一页</a>
</s:if>
<s:else>
	<span class="disabled">首页</span>
	<span class="disabled"> &lt;  上一页</span>
</s:else>

<s:iterator value="page.pageNum" id="pageNum">
	<s:if test="page.currentPage==#pageNum">
		<span class="current"><s:property value="#pageNum" /></span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(${pageNum});"><s:property value="#pageNum" /></a>
	</s:else>
</s:iterator>

<s:if test="page.currentPage!=page.maxPage">
	<a href="javascript:jumpPage(${page.currentPage+1});">下一页  &gt;</a>
	<a href="javascript:jumpPage(${page.maxPage});">尾页</a>
</s:if>
<s:else>
	<span class="disabled"> 下一页  &gt;</span>
	<span class="disabled"> 尾页</span>
</s:else>
<s:if test="page.maxPage<200">
&nbsp;转到
<select name="jumpMenu" id="jumpMenu" onchange="jumpPage(this.options[this.selectedIndex].text)">
	<s:bean name="org.apache.struts2.util.Counter" id="counter">
		<s:param name="first" value="1" />
		<s:param name="last" value="page.maxPage" />   
		<s:iterator id="pageNum">
			<s:if test="page.currentPage==#pageNum">
				<option selected="selected"><s:property/></option>
		  	</s:if>
			<s:else>
		   		<option><s:property/></option>
			</s:else>
		</s:iterator>
	</s:bean>
</select>页 
</s:if>