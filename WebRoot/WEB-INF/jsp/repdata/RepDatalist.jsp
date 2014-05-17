
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/rep/repdata!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/rep/repdata!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td> 
						输入日期</td><td>
							<input type="text" name="inputDate" class="date" size="30" />
							<a class="inputDateButton" href="javascript:;">选择</a>
					</td> 
					<td> 
						收集方式</td><td>
								<input name="dataType" class="textInput" size="30" type="text"   />
					</td> 
					<td> 
						统计用户</td><td>
								<input name="userId" class="textInput" size="30" type="text"   />
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
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<a class="add" href="/rep/repdata!beforeAdd.do" target="dialog" mask="true"
					title="添加"><span>添加</span> </a>
			</li>
			<li>
				<a class="delete" href="/rep/repdata!doDelete.do" postType="string"
					target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="edit" href="/rep/repdata!beforeUpdate.do?sno={sno}" mask="true"
					target="dialog" title="修改"><span>修改</span> </a>
			</li>
			<li>
				<a class="icon" href="/rep/repdata!export.do" target="dwzExport"
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
				<th width="120"    orderField="DATATYPE" >
						收集方式 
				</th> 
				<th width="100"    orderField="COMENUM" >
						进店人数 
				</th> 
				<th width="100"    orderField="INSTRESTNUM" >
						感兴趣人数 
				</th> 
				<th width="100"    orderField="TRYNUM" >
						试衣人数 
				</th> 
				<th width="100"    orderField="BUYNUM" >
						购买人数 
				</th> 
				<th width="100"    orderField="OLDNUM" >
						老顾客人数 
				</th> 
				<th width=""    orderField="USERID" >
						统计用户 
				</th> 
				<th width="0"    orderField="TIMESPAN" >
						营业时间间隔 
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
						<s:property value="dataType" />
					</td> 
					<td>
						<s:property value="comeNum" />
					</td> 
					<td>
						<s:property value="instrestNum" />
					</td> 
					<td>
						<s:property value="tryNum" />
					</td> 
					<td>
						<s:property value="buyNum" />
					</td> 
					<td>
						<s:property value="oldNum" />
					</td> 
					<td>
						<s:property value="userId" />
					</td> 
					<td>
						<s:property value="timeSpan" />
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

