<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<script src="/js/report/repreport.js" type="text/javascript" />


<div class="pageContent">
	<div class="pageFormContent" style="float: left; border: 1 red;">
		<div class="unit">
			<label>
				选择用户
			</label>
			<input type="text" name="selectuser" value='6666666,2147483647' id="selectuser"  />
			<label>
				开始日期
			</label>
			<input type="text" name="inputDate1" value='2014-1-1' id="inputDate1" class="date" />
			<a class="inputDateButton" href="javascript:;">选择</a>
			<label>
				结束日期
			</label>
			<input type="text" name="inputDate2" id="inputDate2" class="date" value='2014-12-12'  />
			<a class="inputDateButton" href="javascript:;">选择</a>
			<button onclick="seeRank2()">
				查看排名
			</button>
		</div>
		<br>
		<div id="rep_chartdiv4" class="highcharts" type="column"
			serialName="时间"
			style="width: 500px; height: 400px; float: left; border: 1 red;"
			url=""></div>

	</div>
	<br>
</div>
