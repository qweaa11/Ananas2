<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="resources/css/jghChartStyle.css"/>

<script type="text/javascript">

	$(document).ready(function () {
		
		
		
		Highcharts.chart('field', {
		    chart: {
		        type: 'column'
		    },
		    title: {
		        text: '연간 도서관별 최다대여 분야 분포도'
		    },
		    subtitle: {
		        text: 'Source: WorldClimate.com'
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
		            text: 'Rainfall (mm)'
		        }
		    },
		    tooltip: {
		        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
		        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
		            '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
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
		    series: [{
		        name: '강남도서관',
		        data: [15.9, 71.5, 40.4, 32.2, 90.0, 26.0]

		    }, {
		        name: '을지로도서관',
		        data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5]

		    }, {
		        name: '당산도서관',
		        data: [48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0]

		    }, {
		        name: '김포도서관',
		        data: [48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0]

		    }, {
		        name: '부산도서관',
		        data: [48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0]

		    }, {
		        name: '성남도서관',
		        data: [48.9, 38.8, 39.3, 41.4, 47.0, 48.3, 59.0]
		    }]
		});
	});


</script>

<div>

	<div id="field" style="margin: 0 auto; border-radius: 5px; clear: both;">
	</div>
	
</div>