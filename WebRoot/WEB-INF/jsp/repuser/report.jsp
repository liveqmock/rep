<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<script src="/js/report/repreport.js" type="text/javascript" />
<div class="pageContent">
	<div class="pageFormContent" style="float: left; border: 1 red;">
		<div class="unit">
			<label>
				输入日期
			</label>
			<input type="text" name="inputDate" id="inputDate" class="date" />
			<a class="inputDateButton" href="javascript:;">选择</a>
			<button onclick="seeRank()">
				查看RPI排名
			</button>
		</div>
		<br>
		<div id="rep_chartdiv3" class="highcharts" type="column"
			serialName="时间"
			style="width: 800px; height: 400px; float: left; border: 1 red;"
			url=""></div> 
	</div>
	<br>
</div>
