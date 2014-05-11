
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<%@ page import="rep.user.RepUser"%>
<% 
	RepUser vo = (RepUser) request.getAttribute("vo"); 
%>
<div class="pageContent">
	<form method="post" action="/rep/repuser!doUpdate.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<input type='hidden' name="sno"
			value="<s:property value="vo.sno"/>">
		<div class="pageFormContent" layoutH="57"> 
					 <div class="unit">
						<label>
							用户id:
						</label>
									<input name="userId" class="textInput  required"  size="30" type="text"  value="<s:property value="vo.userId"/>" />
					</div>
					 <div class="unit">
						<label>
							品牌名称:
						</label>
									<input name="brandName" class="textInput  "  size="30" type="text"  value="<s:property value="vo.brandName"/>" />
					</div>
					 <div class="unit">
						<label>
							品类:
						</label>
									<input name="brandType" class="textInput  "  size="30" type="text"  value="<s:property value="vo.brandType"/>" />
					</div>
					 <div class="unit">
						<label>
							营业面积:
						</label>
									<input name="area" class="textInput  "  size="30" type="text"  value="<s:property value="vo.area"/>" />
					</div>
					 <div class="unit">
						<label>
							店铺地址:
						</label>
									<input name="address" class="textInput  "  size="30" type="text"  value="<s:property value="vo.address"/>" />
					</div>
					 <div class="unit">
						<label>
							主力单价:
						</label>
									<input name="masterPrice" class="textInput  "  size="30" type="text"  value="<s:property value="vo.masterPrice"/>" />
					</div>
					 <div class="unit">
						<label>
							人流量-工作日:
						</label>
									<input name="workNum" class="textInput  "  size="30" type="text"  value="<s:property value="vo.workNum"/>" />
					</div>
					 <div class="unit">
						<label>
							营业时间:
						</label>
									<input name="workTime" class="textInput  "  size="30" type="text"  value="<s:property value="vo.workTime"/>" />
					</div>
					 <div class="unit">
						<label>
							人流量-周末:
						</label>
									<input name="weekendNum" class="textInput  "  size="30" type="text"  value="<s:property value="vo.weekendNum"/>" />
					</div>
					 <div class="unit">
						<label>
							联系方式:
						</label>
									<input name="phone" class="textInput  "  size="30" type="text"  value="<s:property value="vo.phone"/>" />
					</div>
					 <div class="unit">
						<label>
							密码:
						</label>
									<input name="password" class="textInput  " readonly='true' size="30" type="text"  value="<s:property value="vo.password"/>" />
					</div>
					 <div class="unit">
						<label>
							额外参数1:
						</label>
									<input name="param1" class="textInput  "  size="30" type="text"  value="<s:property value="vo.param1"/>" />
					</div>
					 <div class="unit">
						<label>
							纬度:
						</label>
									<input name="lng_north" class="textInput  "  size="30" type="text"  value="<s:property value="vo.lng_north"/>" />
					</div>
					 <div class="unit">
						<label>
							经度:
						</label>
									<input name="lat_east" class="textInput  "  size="30" type="text"  value="<s:property value="vo.lat_east"/>" />
					</div>
					 <div class="unit">
						<label>
							位置:
						</label>
									<input name="location" class="textInput  "  size="30" type="text"  value="<s:property value="vo.location"/>" />
					</div>
					 <div class="unit">
						<label>
							额外参数2:
						</label>
									<input name="param2" class="textInput  "  size="30" type="text"  value="<s:property value="vo.param2"/>" />
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

