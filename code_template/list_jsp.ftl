<#include "/com.renjie120.codegenerate.common.ftl">
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${model.namespace}/${nm}!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection"
		value="${orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="${model.namespace}/${nm}!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<#list model.attributes as attr>  
					<#if "${attr.query}"='true'>
					<td> 
						${attr.desc}</td><td>
						<#if '${attr.type}'='date'>
							<input type="text" name="${attr.name}" class="date" size="30" />
							<a class="inputDateButton" href="javascript:;">选择</a>
						<#else>
						 	<#if '${attr.selectType}'!=''>
								<my:newselect tagName="${attr.name}"  paraType="${attr.selectType}" width="100" allSelected="true" />
							<#else>
								<input name="${attr.name}" class="textInput" size="30" type="text"   />
							</#if> 
						</#if>
					</td> 
					</#if>
					</#list>    
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<#if "${model.noQuery}"!='true'>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									检索
								</button>
							</div>
						</div>
					</li>
					</#if>
					<#if "${model.noQuery2}"!='true'>
					<li>
						<a class="button" href="${model.namespace}/${nm}!beforeQuery.do"
							target="dialog" mask="true" title="查询框"><span>高级检索</span> </a>
					</li>
					</#if>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<#if "${model.noAdd}"!='true'>
			<li>
				<a class="add" href="${model.namespace}/${nm}!beforeAdd.do" target="dialog" mask="true"
					title="添加"><span>添加</span> </a>
			</li>
			</#if>
			<#if "${model.noDelete}"!='true'>
			<li>
				<a class="delete" href="${model.namespace}/${nm}!doDelete.do" postType="string"
					target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			</#if>
			<#if "${model.noUpdate}"!='true'>
			<li>
				<a class="edit" href="${model.namespace}/${nm}!beforeUpdate.do?${model.keyName}={${model.keyName}}" mask="true"
					target="dialog" title="修改"><span>修改</span> </a>
			</li>
			</#if>
			<#if "${model.noExport}"!='true'>
			<li>
				<a class="icon" href="${model.namespace}/${nm}!export.do" target="dwzExport"
					targetType="navTab" title="确实要导出这些记录吗?"><span>导出EXCEL</span> </a>
			</li>
			</#if>
		</ul>
	</div>
	<table class="table" layoutH="-138">
		<thead>
			<tr>
				<th width="30">
					<input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<#list model.attributes as attr> 
				<#if "${attr.visible}"!='false'>
				<th width="${attr.width}"    orderField="${attr.name?upper_case}" >
						${attr.desc} 
				</th> 
				</#if>
				</#list>    
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="${model.keyName}" rel="<s:property value="${model.keyName}" />">
					<td>
						<input name="ids" value="<s:property value="${model.keyName}" />"
							type="checkbox">
					</td>
					<#list model.attributes as attr>  
					<#if "${attr.visible}"!='false'>
					<td>
						<s:property value="${attr.name}" />
					</td> 
					</#if>
					</#list>   
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value,pageNum:1})">
				<option value="20"
					<%if((request.getAttribute("numPerPage")+"").equals("20")){%>
					selected <%} %>>
					20
				</option>
				<option value="50"
					<%if((request.getAttribute("numPerPage")+"").equals("50")){%>
					selected <%} %>>
					50
				</option>
				<option value="100"
					<%if((request.getAttribute("numPerPage")+"").equals("100")){%>
					selected <%} %>>
					100
				</option>
				<option value="200"
					<%if((request.getAttribute("numPerPage")+"").equals("200")){%>
					selected <%} %>>
					200
				</option>
			</select>
			<span>条，总共${totalCount}条记录</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${totalCount}"
			numPerPage="${numPerPage}" pageNumShown="20" currentPage="${pageNum}"></div>
	</div>
</div>

