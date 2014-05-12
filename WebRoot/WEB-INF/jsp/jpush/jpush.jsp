
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="/rep/repstats!doAdd.do"
		class="pageForm required-validate"
		onsubmit="return myCallback(this, closeDialogWindow);">
		<div class="pageFormContent" layoutH="57">
			<div class="unit">
				<label>
					输入日期:
				</label>
				<input type="text" name="inputDate" class="date required" size="30"
					readOnly="true" />
				<a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			<div class="unit">
				<label>
					输入日期:
				</label>
				<input type="text" name="inputDate" class="date required" size="30"
					readOnly="true" />
				<a class="inputDateButton" href="javascript:;">选择</a>
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