<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/drilldown.js"></script>

<Script type="text/javascript">

	$(document).ready(function(){
		
		$("#categoryNo").bind("change", function(){
			findBookChart();			
		});// End of $("#categoryNo")
		
		findBookChart();
		findDetailBookChart();
		
	});// End of $(document)
	
	
	function findBookChart(){
		
		$.ajax({
			
			url:"findBookChart.ana",
			type:"GET",
			dataType:"JSON",
			success:function(json){
				var bookObjArr = [];	
				
				$.each(json, function(entryIndex, entry){ 
				bookObjArr.push({
     			   name:entry.CNAME,
     			   y: parseFloat(entry.PERCENTAGE),
                  });
				
				});	// end of $.each(data, function(entryIndex, entry)----------------
						
				// Create the chart
				Highcharts.chart('displayBookChart', {
				 chart: {
				     plotBackgroundColor: null,
				     plotBorderWidth: null,
				     plotShadow: false,
				     type: 'pie'
				 },
				 title: {
				     text: '도서별 보유 비율'
				 },
				 tooltip: {
				     pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
				 },
				 plotOptions: {
				     pie: {
				         allowPointSelect: true,
				         cursor: 'pointer',
				         dataLabels: {
				             enabled: true,
				             format: '<b>{point.name}</b>: {point.percentage:.1f} %',
				             style: {
				                 color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
				             }
				         }
				     }
				 },
				 series: [{
				     name: '보유율',
				     colorByPoint: true,
				     data: bookObjArr   // **** 위에서 구한값을 대입시킴. ****

				 }]
				});
				
			},
			error: function(request, status, error){
					alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		 	}
		});
		
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
						    $('#chart-container').highcharts({
						        chart: {
						            type: 'column'
						        },
						        title: {
						            text: '도서 장르별 점유율'
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
	<div style="margin-left: 45%; border: solid 0px red; margin-bottom: 50px;">
		<h2>책  대여 통계</h2>
	</div>
	
	<div style="width: 90%; border: solid 0px red;">
	
		<div id="displayBookChart" style="height: 400px; display: inline-block; border: solid 0px red; margin-left: 5%;">
		                           <!-- style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto" -->
		</div>
		
		<div id="chart-container" style="height: 400px; display: inline-block; border: solid 0px red; margin-left: 5%;">
		
		</div>
		
	</div>
	
	
	<div align="center" style="width: 60%; border: solid red 0px; margin: auto; margin-top: 50px;">
		<form name="orderFrm"> <%-- 서브밋으로 전송을 하지 않고 ajax 요청으로 서버로 전송하므로 action 과 method 의 기술은 자바스크립트 함수에서 기재하도록 한다. --%>
													         
		</form>
	</div>
</body>


