
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="rep.data.RepData"%>
<% 
	RepData vo = (RepData) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/rep/repdata!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="sno"
			value="<s:property value="vo.sno"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							输入日期:
						</label>
							<input type="text" name="inputDate" class="date required" size="30" readOnly="true"  value="<s:property value="vo.inputDate"/>" />
							<a class="inputDateButton" href="javascript:;">选择</a>
					</div>
					 <div class="unit">
						<label>
							收集方式:
						</label>
									<input name="dataType" class="textInput  "  size="30" type="text"  value="<s:property value="vo.dataType"/>" />
					</div>
					 <div class="unit">
						<label>
							进店人数:
						</label>
									<input name="comeNum" class="textInput  "  size="30" type="text"  value="<s:property value="vo.comeNum"/>" />
					</div>
					 <div class="unit">
						<label>
							感兴趣人数:
						</label>
									<input name="instrestNum" class="textInput  "  size="30" type="text"  value="<s:property value="vo.instrestNum"/>" />
					</div>
					 <div class="unit">
						<label>
							试衣人数:
						</label>
									<input name="tryNum" class="textInput  "  size="30" type="text"  value="<s:property value="vo.tryNum"/>" />
					</div>
					 <div class="unit">
						<label>
							购买人数:
						</label>
									<input name="buyNum" class="textInput  "  size="30" type="text"  value="<s:property value="vo.buyNum"/>" />
					</div>
					 <div class="unit">
						<label>
							老顾客人数:
						</label>
									<input name="oldNum" class="textInput  "  size="30" type="text"  value="<s:property value="vo.oldNum"/>" />
					</div>
					 <div class="unit">
						<label>
							额外参数1:
						</label>
									<input name="param1" class="textInput  " readonly='true' size="30" type="text"  value="<s:property value="vo.param1"/>" />
					</div>
					 <div class="unit">
						<label>
							额外参数2:
						</label>
									<input name="param2" class="textInput  " readonly='true' size="30" type="text"  value="<s:property value="vo.param2"/>" />
					</div>
					 <div class="unit">
						<label>
							额外参数3:
						</label>
									<input name="param3" class="textInput  " readonly='true' size="30" type="text"  value="<s:property value="vo.param3"/>" />
					</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">
								保存
							</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">
								取消
							</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>

