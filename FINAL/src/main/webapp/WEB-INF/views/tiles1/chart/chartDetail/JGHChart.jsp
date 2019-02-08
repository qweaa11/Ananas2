<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="resources/css/jghChartStyle.css"/>

<script type="text/javascript">

	$(document).ready(function () {
		
		$.ajax({	
			url:"jghChartYear.ana",
			type:"GET",
			dataType:"json",
			success:function(json) {
				
				var count = json.count;
				
				if(count > -1) {
					
					var today = new Date();
					var year = today.getFullYear();
					
					var html = "";
					
					
					for(var i=0; i<=count; i++) {
						 html += "<li value='"+i+"' class='jyear'>"+(year-i)+"년</li>"
					}// end of for
					
					$("#jterm").html(html);
					
					chartField(0);
					
				}// end of if

			}, error: function(request, status, error) {
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}// end of success~error
			
		});// end of ajax for year list
		
		
		$(document).on('click', '.jyear', function (e) {
			var year = $(this).val();
			
			chartField(year);
			
		});
		
	});// end of document ready
	
	function chartField(year) {// 월에 따른 도서관별 최다대여 도서분야 불러오기
		var today = new Date();
		var months = today.getMonth()+1;
		
		if(year > 0)
			months = 12;
	
		var monthArray = new Array();
		for(var i=0; i<months; i++) {
			monthArray.push(i+1);
		}// end of for
		
		var monthHTML = "";
		
		for(var i=0; i<monthArray.length; i++) {
			monthHTML += "<li value="+monthArray[i]+">"+monthArray[i]+"월</li>";
			if(i+1 == monthArray.length)
				$(".months").text(monthArray[i]+"월");
		}// end of for
		
		$("#jmonth").html(monthHTML);
	
		var data_form = {"currentyear":year, "month":month};

		/* $.ajax({
			url:"jghChartBestField.ana",
			type:"GET",
			data:data_form,
			dataType:"json",
			success: function(json) {
				
				Highcharts.chart('field', {
				    chart: {
				        type: 'column'
				    },
				    title: {
				        text: '연간 도서관별 최다대여 분야 분포도'
				    },
				    subtitle: {
				        text: 'ANANAS LIBRARY UNION'
				    },
				    xAxis: {
				        categories: [
				            'Jan',
				            'Feb',
				            'Mar',
				            'Apr',
				            'May',
				            'Jun',
				            'Jul',
				            'Aug',
				            'Sep',
				            'Oct',
				            'Nov',
				            'Dec'
				        ],
				        crosshair: true
				    },
				    yAxis: {
				        min: 0,
				        title: {
				            text: '총 권수'
				        }
				    },
				    tooltip: {
				        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
				        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
				            '<td style="padding:0"><b>{point.y:.0f} 권</b></td></tr>',
				        footerFormat: '</table>',
				        shared: true,
				        useHTML: true
				    },
				    plotOptions: {
				        column: {
				            pointPadding: 0.2,
				            borderWidth: 0
				        }
				    },
				    series: json
				});
				
			}, error: function(request, status, error) {
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			}// end of success~error

		});// end of ajax */
		
	}// end of chartField
</script>

<div>
	<div class="col-xs-12">
	
		<div class="col-xs-6">
			연도
		</div>
		
		<div class="col-xs-6">
			월
		</div>
		
		<div class="col-xs-6">
		
			<a class="btn btn-warning btn-select btn-select-light">
				<input type="hidden" class="btn-select-input" id="jtermval" name="jterm" value="0" />
				<span class="btn-select-value">2019년도</span>
				<span class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
				<ul id="jterm">
				</ul>
			</a>

		</div>
		
		<div class="col-xs-6">
			<a class="btn btn-warning btn-select btn-select-light">
				<input type="hidden" class="btn-select-input" id="jmonthval" name="jmonth" value="0" />
				<span class="btn-select-value months">2월</span>
				<span class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
				<ul id="jmonth">
				</ul>
			</a>
		</div>
	
	</div>

	<div id="field" style="margin: 0 auto; border-radius: 5px; clear: both;">
	</div>
	
</div>