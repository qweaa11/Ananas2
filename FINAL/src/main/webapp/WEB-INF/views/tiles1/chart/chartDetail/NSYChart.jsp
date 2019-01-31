<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/drilldown.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		libraryBookPieChart();
		libraryBookBarChart();
	});
	
	// 도서관별 보유도서 비율을(통계) 나타내는 PieType차트
	function libraryBookPieChart(){
		$.ajax({
			url: "libraryBookPieChart.ana",
			type:"GET",
			dataType:"JSON",
			success: function(json){
				var libraryBookArr = [];
				// 또는 = new Array();
				$.each(json, function(entryIndex, entry){ 
					libraryBookArr.push([ entry.libname, Number(entry.percent)]);
				});
				
				// Create the chart
				Highcharts.chart('libraryBookPieChart', {
				    chart: {
				        type: 'pie',
				        options3d: {
				            enabled: true,
				            alpha: 45
				        }
				    },
				    title: {
				        text: '도서관별 보유도서 비율'
				    },
				    subtitle: {
				        text: '3D donut in Highcharts'
				    },
				    plotOptions: {
				        pie: {
				            innerSize: 100,
				            depth: 45
				        }
				    },
				    series: [{
				        name: '보유도서 비율(%)',
				        data: libraryBookArr   
				        // **** 위에서 구한값을 대입시킴. ****	
				        /*[
							['Bananas', 8],
				            ['Kiwi', 3],
				            ['Mixed nuts', 1],
				            ['Oranges', 6],
				            ['Apples', 8],
				            ['Pears', 4],
				            ['Clementines', 4],
				            ['Reddish (bag)', 1],
				            ['Grapes (bunch)', 1]
							
				        ] */
				    }]
				});
				
			},
			error: function(request, status, error){
				   alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			} 
		});
	}
	
	// 도서관별 보유도서 비율(분류별포함)을 나타내는 BarType 차트
	function libraryBookBarChart() {
		
	$.getJSON("libraryBookPieChart.ana", 
		  
	function(json){ // json => ajax 요청에 의해 서버로 부터 리턴받은 데이터.
					           
		var libraryBookArr = [];
		var libraryBookDetailArr = [];
		// 또는 = new Array();
		
		$.each(json, function(entryIndex, entry){ 
			
			libraryBookArr.push({
				"name": entry.libname,
				"y": parseFloat(entry.percent),
				"drilldown": entry.libname
			});
		}); // end of $.each(json, function(entryIndex, entry)----------------
			
		$.each(json, function(entryIndex, entry) { 
			$.getJSON("libraryBookBarChart.ana?libname="+entry.libname, 
					function(json2){ 
						var subArr = [];
						// 또는 = new Array();
	
						$.each(json2, function(entryIndex2, entry2){ 
							
							subArr.push([
										 entry2.cname,
										 parseFloat(entry2.percent)
									    ]);
						});	// end of $.each(json2, function(entryIndex2, entry2){  --------------
						
						libraryBookDetailArr.push({
							"name": entry.libname,
							"id": entry.libname,
							"data": subArr
						});	
						
					});
					
				}); // $.each(json, function(entryIndex, entry) { ----------------------
					 
		    // Create the chart
		    $('#libraryBookBarChart').highcharts({
		        chart: {
		            type: 'column'
		        },
		        title: {
		            text: '도서관별 보유도서 비율(종류별포함[그래프클릭])'
		        },
		        subtitle: {
		           // text: 'Click the columns to view versions. Source: <a href="http://netmarketshare.com">netmarketshare.com</a>.'
		        },
		        xAxis: {
		            type: 'category'
		        },
		        yAxis: {
		            title: {
		                text: '비율(%)'
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
		            name: 'Brands',
		            colorByPoint: true,
		            data: libraryBookArr   // **** 위에서 구한값을 대입시킴. ****
		        }],
		        drilldown: { 
		        	series: libraryBookDetailArr  // **** 위에서 구한값을 대입시킴. ****
		         /* series: [{
		                name: libraryBookDetailArr[0].name,  // *** 위에서 한 작업으로 키값이 JEPUMNAME 에서 name 으로 변경됨.
		                id: libraryBookDetailArr[0].name,    // *** 위에서 한 작업으로 키값이 JEPUMNAME 에서 name 으로 변경됨.
		                data: [
		                    [
		                        'v11.0',
		                        24.13
		                    ],
		                    [
		                        'v8.0',
		                        17.2
		                    ],
		                    [
		                        'v9.0',
		                        8.11
		                    ],
		                    [
		                        'v10.0',
		                        5.33
		                    ],
		                    [
		                        'v6.0',
		                        1.06
		                    ],
		                    [
		                        'v7.0',
		                        0.5
		                    ]
		                ]
		            }, {
		                name: jepumObjArr[1].name,
		                id: jepumObjArr[1].name,
		                data: [
		                    [
		                        'v40.0',
		                        5
		                    ],
		                    [
		                        'v41.0',
		                        4.32
		                    ],
		                    [
		                        'v42.0',
		                        3.68
		                    ],
		                    [
		                        'v39.0',
		                        2.96
		                    ],
		                    [
		                        'v36.0',
		                        2.53
		                    ],
		                    [
		                        'v43.0',
		                        1.45
		                    ],
		                    [
		                        'v31.0',
		                        1.24
		                    ],
		                    [
		                        'v35.0',
		                        0.85
		                    ],
		                    [
		                        'v38.0',
		                        0.6
		                    ],
		                    [
		                        'v32.0',
		                        0.55
		                    ],
		                    [
		                        'v37.0',
		                        0.38
		                    ],
		                    [
		                        'v33.0',
		                        0.19
		                    ],
		                    [
		                        'v34.0',
		                        0.14
		                    ],
		                    [
		                        'v30.0',
		                        0.14
		                    ]
		                ]
		            }, {
		                name: jepumObjArr[2].name,
		                id: jepumObjArr[2].name,
		                data: [
		                    [
		                        'v35',
		                        2.76
		                    ],
		                    [
		                        'v36',
		                        2.32
		                    ],
		                    [
		                        'v37',
		                        2.31
		                    ],
		                    [
		                        'v34',
		                        1.27
		                    ],
		                    [
		                        'v38',
		                        1.02
		                    ],
		                    [
		                        'v31',
		                        0.33
		                    ],
		                    [
		                        'v33',
		                        0.22
		                    ],
		                    [
		                        'v32',
		                        0.15
		                    ]
		                ]
		            }, {
		                name: jepumObjArr[3].name,
		                id: jepumObjArr[3].name,
		                data: [
		                    [
		                        'v8.0',
		                        2.56
		                    ],
		                    [
		                        'v7.1',
		                        0.77
		                    ],
		                    [
		                        'v5.1',
		                        0.42
		                    ],
		                    [
		                        'v5.0',
		                        0.3
		                    ],
		                    [
		                        'v6.1',
		                        0.29
		                    ],
		                    [
		                        'v7.0',
		                        0.26
		                    ],
		                    [
		                        'v6.2',
		                        0.17
		                    ]
		                ]
		            }, {
		                name: jepumObjArr[4].name,
		                id: jepumObjArr[4].name,
		                data: [
		                    [
		                        'v12.x',
		                        0.34
		                    ],
		                    [
		                        'v28',
		                        0.24
		                    ],
		                    [
		                        'v27',
		                        0.17
		                    ],
		                    [
		                        'v29',
		                        0.16
		                    ]
		                ]
		            }] 
		        */
		        }  
		    });	 								 
		
		}); // end of $.getJSON("rankShowJSON.action", function(data) {} )-----------
	}// end of function sideinfo_setOrderRankChart() { }--------------------------

</script>

<div id="libraryBookPieChart" style="min-width: 90%; margin-top: 50px; margin-bottom: 50px; padding-left: 20px; padding-right: 20px;"></div>
<div id="libraryBookBarChart" style="min-width: 90%; margin-top: 50px; margin-bottom: 50px; padding-left: 20px; padding-right: 20px;"></div>


