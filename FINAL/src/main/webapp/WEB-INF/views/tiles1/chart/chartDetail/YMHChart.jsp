<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/drilldown.js"></script>

<script type="text/javascript">

	$(document).ready(function () {
		
		showChart();	// 차트보여주기
	});

	
	function showChart()
	{
		$.getJSON("<%=request.getContextPath() %>/libraryOverdueRank.ana",
			function(json)
			{
				var labraryArr = [];    // 도서관이 들어갈 배열 생성
				
				$.each(json, function(entryIndex, entry){
					labraryArr.push({
						"name": entry.LIBNAME,
						"y": parseFloat(entry.PERCENTAGE),
						"drilldown": entry.LIBNAME,
						
					});
				}); // end of $.each(json, function(entryIndex, entry)-----------------------
						
				
						
				var overdueRateArr = [];			
					
				$.each(json, function(entryIndex, entry){
					$.getJSON("<%=request.getContextPath() %>/overdueOfLibraryByGenre.ana?libname="+entry.LIBNAME,
						function(json2){
						
							var subArr =[];
							
							$.each(json2, function(entryIndex2, entry2){
								subArr.push([
									entry2.GNAME,
									parseFloat(entry2.PERCENT)
								]);
							}); //  end of $.each()-------------------
						
							overdueRateArr.push({
								"name": entry.LIBNAME,
								"id": entry.LIBNAME,
								"data": subArr
							});	
					});
				});	// end of 	$.each(json, function(){}------------------------------------
					
						
				// create the chart
				$('#YMHcontainer').highcharts({
				    chart: 
				    	{
				        	type: 'column'	// 차트타입 설정
				    	},
				    title: 
					    {
					        text: '도서관 장르별 연체율'	// 차트제목설정
					    },
				    subtitle: 
					    {
					    	// 소제목 칸
					    },
				    xAxis: 
					    {
					        type: 'category'	// 가로축
					    },
				    yAxis: 
					    {
					        title: 
					        {
					            text: '연체율 (%)'	// 세로축
					        }
					    },
					legend:
						{
							enabled: false // 범례
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
				    tooltip: {	// 막대 그래프에 마우스 포인터를 올리면 보여주는 값 설정
				        headerFormat: '<span style="font-size:10px">{seris.name}</span><br>',
				        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}</b> of total<br/>'
				      
				    },
				    series: [{
				        name: '도서관명',
				        colorByPoint: true,
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