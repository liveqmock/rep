
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/rep/repstats!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/rep/repstats!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td> 
						输入日期</td><td>
							<input type="text" name="inputDate" class="date" size="30" />
							<a class="inputDateButton" href="javascript:;">选择</a>
					</td> 
					<td> 
						统计用户</td><td>
								<input name="userId" class="textInput" size="30" type="text"   />
					</td> 
					<td> 
						问题</td><td>
								<input name="problem" class="textInput" size="30" type="text"   />
					</td> 
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									检索
								</button>
							</div>
						</div>
					</li>
					<li>
						<a class="button" href="/rep/repstats!beforeQuery.do"
							target="dialog" mask="true" title="查询框"><span>高级检索</span> </a>
					</li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<a class="add" href="/rep/repstats!beforeAdd.do" target="dialog" mask="true"
					title="添加"><span>添加</span> </a>
			</li>
			<li>
				<a class="delete" href="/rep/repstats!doDelete.do" postType="string"
					target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="edit" href="/rep/repstats!beforeUpdate.do?sno={sno}" mask="true"
					target="dialog" title="修改"><span>修改</span> </a>
			</li>
			<li>
				<a class="icon" href="/rep/repstats!export.do" target="dwzExport"
					targetType="navTab" title="确实要导出这些记录吗?"><span>导出EXCEL</span> </a>
			</li>
		</ul>
	</div>
	<table class="table" layoutH="-138">
		<thead>
			<tr>
				<th width="30">
					<input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th width="100"    orderField="INPUTDATE" >
						输入日期 
				</th> 
				<th width="100"    orderField="STATIS1" >
						统计数据1 
				</th> 
				<th width="100"    orderField="STATIS2" >
						统计数据2 
				</th> 
				<th width="100"    orderField="STATIS3" >
						统计数据3 
				</th> 
				<th width="100"    orderField="STATIS4" >
						统计数据4 
				</th> 
				<th width="100"    orderField="STATIS5" >
						统计数据5 
				</th> 
				<th width="100"    orderField="STATIS6" >
						统计数据6 
				</th> 
				<th width="100"    orderField="USERID" >
						统计用户 
				</th> 
				<th width="100"    orderField="RPI" >
						rpi数值 
				</th> 
				<th width="100"    orderField="RANK" >
						排名 
				</th> 
				<th width="100"    orderField="PROBLEM" >
						问题 
				</th> 
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" status="stu">
				<tr target="sno" rel="<s:property value="sno" />">
					<td>
						<input name="ids" value="<s:property value="sno" />"
							type="checkbox">
					</td>
					<td>
						<s:property value="inputDate" />
					</td> 
					<td>
						<s:property value="statis1" />
					</td> 
					<td>
						<s:property value="statis2" />
					</td> 
					<td>
						<s:property value="statis3" />
					</td> 
					<td>
						<s:property value="statis4" />
					</td> 
					<td>
						<s:property value="statis5" />
					</td> 
					<td>
						<s:property value="statis6" />
					</td> 
					<td>
						<s:property value="userId" />
					</td> 
					<td>
						<s:property value="rpi" />
					</td> 
					<td>
						<s:property value="rank" />
					</td> 
					<td>
						<s:property value="problem" />
					</td> 
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

