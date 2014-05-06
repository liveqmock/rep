
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
							<input type="text" name="inputDate" class="date required" size="30" readOnly="true"   />
							<a class="inputDateButton" href="javascript:;">选择</a>
					</div>
					 <div class="unit">
						<label>
							统计数据1:
						</label>
									<input name="statis1" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							统计数据2:
						</label>
									<input name="statis2" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							统计数据3:
						</label>
									<input name="statis3" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							统计数据4:
						</label>
									<input name="statis4" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							统计数据5:
						</label>
									<input name="statis5" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							统计数据6:
						</label>
									<input name="statis6" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							问题:
						</label>
									<input name="problem" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							额外参数2:
						</label>
									<input name="param2" class="textInput " size="30" type="text"   />
					</div>
					 <div class="unit">
						<label>
							额外参数1:
						</label>
									<input name="param1" class="textInput " size="30" type="text"   />
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