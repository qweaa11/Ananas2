<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/drilldown.js"></script>

<Script type="text/javascript">

	$(document).ready(function(){
		
		var year = $("#year").val();
		var month = $("#month").val();
		
		findDetailBookChart();
		findDelayChart(year, month);
		
		
		$("#year").change( function(){
			year = $("#year").val();
			findDelayChart(year, month);
		});
		
		
		$("#month").change( function(){
			month = $("#month").val();
			findDelayChart(year, month);
		});
		
	});// End of $(document)
	
	
	function findDelayChart(year, month){
		
		$.getJSON("findRentalChart.ana?year="+year+"&month="+month, 
				  
				 function(json){ // json => ajax 요청에 의해 서버로 부터 리턴받은 데이터.
	 								           
                    var bookObjArr = [];
                    var bookObjDetailArr = [];
                    
           	 		
					 $.each(json, function(entryIndex, entry){ 
						 bookObjArr.push({
			                "name": entry.CNAME,
			                "y": parseFloat(entry.PERCENTAGE),
			                "drilldown": entry.CNAME
			                });
		             }); // end of $.each(json, function(entryIndex, entry)----------------			
		    		
		    			
					$.each(json, function(entryIndex, entry) {
						$.getJSON("findRentalChartGender.ana?CNAME="+entry.CNAME+"&year="+year+"&month="+month, 
								function(json2){ 
									var subArr = [];
							// 또는   var subArr = new Array();
				
									$.each(json2, function(entryIndex2, entry2){ 
										subArr.push([
													 entry2.GENDER,
													 parseFloat(entry2.PERCENTAGE)
												    ]);
									});	// end of $.each(json2, function(entryIndex2, entry2){  --------------  
				
									bookObjDetailArr.push({
										"name": entry.CNAME,
										"id": entry.CNAME,
										"data": subArr
									});
									
									//console.log(subArr);
								});
							}); // $.each(json, function(entryIndex, entry) { ----------------------
							
								//console.log(bookObjDetailArr);

						    // Create the chart
						    $('#chart-container').highcharts({
						        chart: {
						            type: 'column'
						        },
						        title: {
						            text: '도서 카테고리별 대여 성별'
						        },
						        subtitle: {
						           // text: 'Click the columns to view versions. Source: <a href="http://netmarketshare.com">netmarketshare.com</a>.'
						        },
						        xAxis: {
						            type: 'category'
						        },
						        yAxis: {
						            title: {
						                text: '점유율(%)'
						            }

						        },
						        legend: {
						            enabled: false
						        },
						        plotOptions: {
						            series: {
						                borderWidth: 0,
						                dataLabels: {
						                    enabled: true,
						                    format: '{point.y:.1f}%'
						                }
						            }
						        },

						        tooltip: {
						            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
						            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
						        },

						        series: [{
						            name: 'Book',
						            colorByPoint: true,
						            data: bookObjArr   // **** 위에서 구한값을 대입시킴. ****
						        }],  
						        drilldown: { 
						        	series: bookObjDetailArr  // **** 위에서 구한값을 대입시킴. ****

						        }  
						    }); 								 
						
						}); // end of $.getJSON("rankShowJSON.action", function(data) {} )-----------
		
	}// End of function findBookChart
	
	
	function findDetailBookChart() {
		
		$.getJSON("findBookChart.ana", 
				  
				 function(json){ // json => ajax 요청에 의해 서버로 부터 리턴받은 데이터.
	 								           
                     var bookObjArr = [];
                     var bookObjDetailArr = [];
                     
            	 		
					 $.each(json, function(entryIndex, entry){ 
						 bookObjArr.push({
			                "name": entry.CNAME,
			                "y": parseFloat(entry.PERCENTAGE),
			                "drilldown": entry.CNAME
			                });
		             }); // end of $.each(json, function(entryIndex, entry)----------------			
		    		
		    			
					$.each(json, function(entryIndex, entry) {
						$.getJSON("findDetailBookChart.ana?CNAME="+entry.CNAME, 
								function(json2){ 
									var subArr = [];
							// 또는   var subArr = new Array();
				
									$.each(json2, function(entryIndex2, entry2){ 
										subArr.push([
													 entry2.GNAME,
													 parseFloat(entry2.PERCENTAGE)
												    ]);
									});	// end of $.each(json2, function(entryIndex2, entry2){  --------------  
				
									bookObjDetailArr.push({
										"name": entry.CNAME,
										"id": entry.CNAME,
										"data": subArr
									});
									
									//console.log(subArr);
								});
							}); // $.each(json, function(entryIndex, entry) { ----------------------
							
								//console.log(bookObjDetailArr);

 						    // Create the chart
						    $('#displayBookChart').highcharts({
						        chart: {
						            type: 'column'
						        },
						        title: {
						            text: '도서 장르별 점유율 소분류'
						        },
						        subtitle: {
						           // text: 'Click the columns to view versions. Source: <a href="http://netmarketshare.com">netmarketshare.com</a>.'
						        },
						        xAxis: {
						            type: 'category'
						        },
						        yAxis: {
						            title: {
						                text: '점유율(%)'
						            }

						        },
						        legend: {
						            enabled: false
						        },
						        plotOptions: {
						            series: {
						                borderWidth: 0,
						                dataLabels: {
						                    enabled: true,
						                    format: '{point.y:.1f}%'
						                }
						            }
						        },

						        tooltip: {
						            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
						            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
						        },

						        series: [{
						            name: 'Book',
						            colorByPoint: true,
						            data: bookObjArr   // **** 위에서 구한값을 대입시킴. ****
						        }],  
						        drilldown: { 
						        	series: bookObjDetailArr  // **** 위에서 구한값을 대입시킴. ****

						        }  
						    }); 								 
						
						}); // end of $.getJSON("rankShowJSON.action", function(data) {} )-----------
	}// end of function sideinfo_setOrderRankChart() { }--------------------------


				
</Script>

<body>
	<div style="margin-left: 42%; border: solid 0px red; margin-bottom: 50px;">
		<h2>전체 도서관 장르별 보유 통계</h2>
	</div>
	
	<div class="col-lg-12 col-sm-12" style="width: 95%; border: solid 0px red;">
		<div class="col-lg-offset-1 col-lg-5 col-sm-11 col-sm-offset-1" id="displayBookChart" style="height: 400px; display: inline-block; border: solid 0px red; margin-left: 5%;"></div>
		<div class="col-lg-offset-1 col-lg-5 col-sm-11 col-sm-offset-1" id="chart-container" style="height: 400px; display: inline-block; border: solid 0px red; margin-left: 5%;"></div>
		<select id="year">
			<option value="2019">2019년</option>
			<option value="2018">2018년</option>
			<option value="2017">2017년</option>
		</select>
		<select id="month">
			<option value="01">1월</option>
			<option value="02">2월</option>
			<option value="03">3월</option>
			<option value="04">4월</option>
			<option value="05">5월</option>
			<option value="06">6월</option>
			<option value="07">7월</option>
			<option value="08">8월</option>
			<option value="09">9월</option>
			<option value="10">10월</option>
			<option value="11">11월</option>
			<option value="12">12월</option>
		</select>
	</div>   
</body>


