<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style type="text/css">
#menu1 {
	width: 100px;
	height: 100px;
	background: -webkit-linear-gradient(90deg, #16222A 10%, #3A6073 90%);
	/* Chrome 10+, Saf5.1+ */
	background: -moz-linear-gradient(90deg, #16222A 10%, #3A6073 90%);
	/* FF3.6+ */
	background: -ms-linear-gradient(90deg, #16222A 10%, #3A6073 90%);
	/* IE10 */
	background: -o-linear-gradient(90deg, #16222A 10%, #3A6073 90%);
	/* Opera 11.10+ */
	background: linear-gradient(90deg, #16222A 10%, #3A6073 90%); /* W3C */
	font-family: 'Raleway', sans-serif;
}
</style>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>


<script type="text/javascript">

	$(document).ready(function () {
		
	//	showChart();
	});

	
	function showChart()
	{
		$.getJSON("<%=request.getContextPath() %>/libraryOverdueRank.ana",
			function(json)
			{
				var labraryArr = [];    // 도서관이 들어갈 배열 생성
				
				$.each(json, function(entryIndex, entry){
					labraryArr.push({
						"name": entry.name,
						"y": parseFloat(entry.PERCENTAGE),
						"drilldown": entry.name,
						
					});
				}); // end of $.each(json, function(entryIndex, entry)-----------------------
						
						
				var overdueRateArr = [];			
						
				$.each(json, function(){
					$.getJSON("<%=request.getContextPath() %>/overdueOfLibrary.ana"),
						function(json2){
						
							var subArr =[];
							
							$.each(json2, function(){
								subArr.push([
									entry2.typename,
									parseFloat(entry.PERCENTAGE)
								]);
							}); //  end of $.each()-------------------
						
							overdueRateArr.push({
								"name": entry.SNACKNAMe		
							
							});	
					});
				});	// end of 	$.each(json, function(){}------------------------------------
						
						
				// create the chart
				$('#YMHcontainer').Highcharts({
				    chart: 
				    	{
				        	type: 'column'
				    	},
				    title: 
					    {
					        text: '도서관 장르별 연체율'
					    },
				    subtitle: 
					    {
					    	// 소제목 칸
					    },
				    xAxis: 
					    {
					        type: 'category'
					    },
				    yAxis: 
					    {
					        title: 
					        {
					            text: '연체율 (%)'
					        }
					    },
					legend:
						{
							enabled: false
						},
					plotOptions:
						{
							series:
								{
									borderWidth: 0,
									dataLabels: {
										enabled: true,
										format: '{point.y:.1f}%'
									}
								}
						},
				    tooltip: {
				        headerFormat: '<span style="font-size:10px">{seris.name}</span><br>',
				        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b> style="padding:0"><b>{point.y:.2f}</b> of total<br/>'
				      
				    },
				    series: [{
				        name: '도서관명',
				        colorByPoint: ture,
				        data: labraryArr

				    	}],
				    drilldown:
				    	{
				    		series: overdueRateArr
				    	}
				}); //  end of chart
						

						
		}); // end of $.getJSON()---------------------------------------------------------------------------------------
	}// end of function showChart()
	
	

</script>

<div>
	<div id="YMHcontainer" style="min-width: 310px; height: 400px; margin: 0 auto;"></div>
</div>