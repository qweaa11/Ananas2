<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style type="text/css">

	#menu2 {
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
		        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0]

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

	<div id="field" style="min-width: 310px; height: 400px; margin: 0 auto;">
	</div>
	
</div>