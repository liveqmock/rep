
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%> 
<div class="pageContent">
	<form method="post" action="/rep/pushcontent!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
					 <div class="unit">
						<label>
							推送用户id:
						</label>
									<input name="userIds" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							推送内容:
						</label>
								<textarea class="" name="content" cols="30" rows="2"></textarea>
					</div>
					 <div class="unit">
						<label>
							推送时间:
						</label>
							<input type="text" name="pushTime" class="date " size="30" readOnly="true"   />
							<a class="inputDateButton" href="javascript:;">选择</a>
					</div>
					 <div class="unit">
						<label>
							推送用户:
						</label>
									<input name="userNames" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							推送机器类型:
						</label>
									<my:newselect tagName="deviceType"  paraType="deviceType" width="100" allSelected="true" />
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