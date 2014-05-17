function seeRank() {
	if ($('#inputDate').val() == '') {
		alert('必须选择日期!');
		return false;
	} else {
		var inD = $('#inputDate').val();
		$
				.ajax( {
					type : 'GET',
					dataType : "json",
					url : '/services/repReportService!reportRpiRankByTime.do?inDate=' + inD,
					cache : false,
					data : {},
					success : function(json) {
						if (!json)
							return;
						var html = '';
					},
					error : function(json) {
						if (json.status == 200) {
							showColumn(json.responseText, inD);
						} else {
							alert("出现错误.");
						}
					}
				});
	}
}

function showColumn(obj, ind) {
	eval("var arr =" + obj);
	show2Column_two(arr, ind + '的RPI报表', 'rep_chartdiv3', 'RPI数值');
}

function showColumn2(obj, ind) {
	eval("var arr =" + obj);
	show3Column(arr, '高级RPI报表', 'rep_chartdiv4');
}

function seeRank2() {
	var d1 = $('#inputDate1').val();
	var u = $('#selectuser').val(); 
	var d2 = $('#inputDate2').val();
	if (d1 == '' || d2 == ''||u=='') {
		alert('必须选择日期!');
		return false;
	} else {
		var inD = $('#inputDate').val();
		$.ajax( {
					type : 'GET',
					dataType : "json",
					url : '/services/repReportService!reportRpiByTime.do?userId='+u+'&inDateSmall="+d1+"&inDateBig=' + d2,
					cache : false,
					success : function(json) {
						if (!json)
							return;
						var html = '';
					},
					error : function(json) {
						if (json.status == 200) {
							showColumn2(json.responseText, inD);
						} else {
							alert("出现错误.");
						}
					}
				});
	}
}