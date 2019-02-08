<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-more.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/drilldown.js"></script>

<style type="text/css">

#container {
  min-width: 320px;
  max-width: 600px;
  margin: 0 auto;
}

</style>

<script type="text/javascript">

	$(document).ready(function(){
		
		languageBookChart();
		
		liblanguageBookChart();
	
		
	}); // end of $(document).ready()--------------
	
	 function languageBookChart(){
		
		$.getJSON("languageBookChart.ana", 
				
			function(json){
			
				var languageArr = [];
				var percentageArr = [];
				
				$.each(json, function(entryIndex, entry){
					languageArr.push(
						entry.LNAME
					); // end of languageArr.push()
					
					percentageArr.push(
						parseFloat(entry.PERCENTAGE)	
					); // end of percentageArr.push()
					
				}); // $.each(json, function(entryIndex, entry)
					
				var chart = Highcharts.chart('container', {

					  title: {
					    text: '도서관 전체 도서언어 점유율'
					  },

					  subtitle: {
					   // text: 'Plain'
					  },

					  xAxis: {
					    categories: languageArr//['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
					  },

					  series: [{
					    type: 'column',
					    colorByPoint: true,
					    data: percentageArr,
					    showInLegend: false
					  }]

					});
				
			
		});// end of function(json)
		
	}// end of function languageBookChart() 
	
	function liblanguageBookChart() {
	
		$.getJSON("libBookChart.ana",
				
				function(json){
			
					var libBookObjArr = [];
					var libBookObjDetailArr = [];
					
					$.each(json, function(entryIndex, entry){
						libBookObjArr.push({
							"name": entry.LIBNAME,
							"y": parseFloat(entry.PERCENTAGE),
							"drilldown": entry.LIBNAME
						});
					});// end of $.each(json, function(entryIndex, entry)
							
					$.each(json, function(entryIndex, entry){
						$.getJSON("liblanguageBookChart.ana?LIBNAME="+entry.LIBNAME,
								function(json2){
									var subArr = [];
									
									$.each(json2, function(entryIndex2, entry2){
										subArr.push([
											entry2.LNAME,
											parseFloat(entry2.PERCENTAGE)
										]);
									});// end of $.each(json2, function(entryIndex2, entry2)
										
									libBookObjDetailArr.push({
										"name": entry.LIBNAME,
										"id": entry.LIBNAME,
										"data": subArr
									});
											
						});// end of $.getJSON("liblanguageBookChart.ana
								
					});// end of $.each(json, function(entryIndex, entry)
							
					// Create the chart
					Highcharts.chart('container2', {
					    chart: {
					        type: 'column'
					    },
					    title: {
					        text: '도서관별 도서언어 점유율'
					    },
					    subtitle: {
					     //   text: 'Click the columns to view versions. Source: <a href="http://statcounter.com" target="_blank">statcounter.com</a>'
					    },
					    xAxis: {
					        type: 'category'
					    },
					    yAxis: {
					        title: {
					            text: 'Values'
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

					    "series": [
					        {
					            "name": "book",
					            "colorByPoint": true,
					            "data": libBookObjArr
					        }
					    ],
					    "drilldown": {
					        "series": libBookObjDetailArr
					    }
					});// end of Highcharts.chart('container2'
			
		})// end of $.getJSON("liblanguageBookChart"
	
	}// end of function liblanguageBookChart()

</script>

<body>
	<div class="col-lg-12 col-sm-12" style="border: solid 0px red; margin-bottom: 50px; height: 550px;">
		<div class="col-lg-offset-1 col-lg-5 col-sm-11 col-sm-offset-1" id="container" style="height: 400px; display: inline-block; border: solid 0px blue; margin-top:1%; margin-left: 10%;"></div>
		<div class="col-lg-offset-1 col-lg-5 col-sm-11 col-sm-offset-1" id="container2" style="height: 400px; display: inline-block; border: solid 0px blue; margin-top:1%; margin-left: 5%;"></div>
	</div>
</body>
