
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<form id="pagerForm" method="post" action="/rep/repuser!query.do">
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection"
		value="${param.orderDirection}" />
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"
		action="/rep/repuser!query.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td> 
						品牌名称</td><td>
								<input name="brandName" class="textInput" size="30" type="text"   />
					</td> 
					<td> 
						店铺地址</td><td>
								<input name="address" class="textInput" size="30" type="text"   />
					</td> 
					<td> 
						联系方式</td><td>
								<input name="phone" class="textInput" size="30" type="text"   />
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
						<a class="button" href="/rep/repuser!beforeQuery.do"
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
				<a class="add" href="/rep/repuser!beforeAdd.do" target="dialog" mask="true"
					title="添加"><span>添加</span> </a>
			</li>
			<li>
				<a class="delete" href="/rep/repuser!doDelete.do" postType="string"
					target="selectedTodo" rel="ids" title="确定要删除吗?"><span>删除</span>
				</a>
			</li>
			<li>
				<a class="edit" href="/rep/repuser!beforeUpdate.do?sno={sno}" mask="true"
					target="dialog" title="修改"><span>修改</span> </a>
			</li>
			<li>
				<a class="icon" href="/rep/repuser!export.do" target="dwzExport"
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
				<th width="100"    orderField="USERID" >
						用户id 
				</th> 
				<th width="120"    orderField="BRANDNAME" >
						品牌名称 
				</th> 
				<th width="100"    orderField="BRANDTYPE" >
						品类 
				</th> 
				<th width="80"    orderField="AREA" >
						营业面积 
				</th> 
				<th width="250"    orderField="ADDRESS" >
						店铺地址 
				</th> 
				<th width="100"    orderField="MASTERPRICE" >
						主力单价 
				</th> 
				<th width="60"    orderField="WORKNUM" >
						人流量-工作日 
				</th> 
				<th width="60"    orderField="WEEKENDNUM" >
						人流量-周末 
				</th> 
				<th width="100"    orderField="PHONE" >
						联系方式 
				</th> 
				<th width="100"    orderField="LNG_NORTH" >
						纬度 
				</th> 
				<th width="100"    orderField="LAT_EAST" >
						经度 
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
						<s:property value="userId" />
					</td> 
					<td>
						<s:property value="brandName" />
					</td> 
					<td>
						<s:property value="brandType" />
					</td> 
					<td>
						<s:property value="area" />
					</td> 
					<td>
						<s:property value="address" />
					</td> 
					<td>
						<s:property value="masterPrice" />
					</td> 
					<td>
						<s:property value="workNum" />
					</td> 
					<td>
						<s:property value="weekendNum" />
					</td> 
					<td>
						<s:property value="phone" />
					</td> 
					<td>
						<s:property value="lng_north" />
					</td> 
					<td>
						<s:property value="lat_east" />
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

