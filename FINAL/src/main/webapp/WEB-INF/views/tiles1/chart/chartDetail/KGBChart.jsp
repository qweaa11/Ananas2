<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>

<link rel="stylesheet" type="text/css" href="resources/css/kgbChartStyle.css"/>



<script type="text/javascript">

	$(document).ready(function () {
		
		$(".btn-select").each(function (e) {
	        var value = $(this).find("ul li.selected").html();
	        if (value != undefined) {
	            $(this).find(".btn-select-input").val(value);
	            $(this).find(".btn-select-value").html(value);
	        }
	    });
		
		
		$(document).on('click', '.btn-select', function (e) {
		    e.preventDefault();
		    var ul = $(this).find("ul");
		    if ($(this).hasClass("active")) {
		        if (ul.find("li").is(e.target)) {
		            var target = $(e.target);
		            target.addClass("selected").siblings().removeClass("selected");
		            var value = target.val();
		            var name = target.html();
		            $(this).find(".btn-select-input").val(value);
		            $(this).find(".btn-select-value").html(name);
		        }
		        ul.hide();
		        $(this).removeClass("active");
		    }
		    else {
		        $('.btn-select').not(this).each(function () {
		            $(this).removeClass("active").find("ul").hide();
		        });
		        ul.slideDown(300);
		        $(this).addClass("active");
		    }
		});

		$(document).on('click', function (e) {
		    var target = $(e.target).closest(".btn-select");
		    if (!target.length) {
		        $(".btn-select").removeClass("active").find("ul").hide();
		    }
		});
		
		
		var data_form = {"currentyear":0};
		
		$.ajax({
			
			url:"genre.ana",
			type:"GET",
			data:data_form,
			dataType:"json",
			success:function(json) {
				
				var rm = new Array();	// 로맨스
				var th = new Array();	// 스릴러
				var sc = new Array();	// 공포
				var de = new Array();	// 추리
				var fn = new Array();	// 판타지
				var sf = new Array();	// 공상과학
				var dr = new Array();	// 드라마
				var cm = new Array();	// 코미디
				var ma = new Array();	// 무협
				var ac = new Array();	// 액션
				var un = new Array();	// 미분류
				
				var chartDater = new Array();
				
				chartDater.push(rm);
				chartDater.push(th);
				chartDater.push(sc);
				chartDater.push(de);
				chartDater.push(fn);
				chartDater.push(sf);
				chartDater.push(dr);
				chartDater.push(cm);
				chartDater.push(ma);
				chartDater.push(ac);
				chartDater.push(un);
				
				$.each(json, function (entryIndex, entry) {
			
					if(entry.CHART.length > 0) {
						
						$.each(entry.CHART, function(chartIndex, chart){
							
							var percent = Number(chart.PERCENT);
							
							console.log(percent);
							
							switch (chart.GCODE) {
							case "RM":
								chartDater[0].push(percent != null? percent:0);
								break;
							case "TH":
								chartDater[1].push(percent != null? percent:0);
								break;
							case "SC":
								chartDater[2].push(percent != null? percent:0);
								break;
							case "DE":
								chartDater[3].push(percent != null? percent:0);
								break;
							case "FN":
								chartDater[4].push(percent != null? percent:0);
								break;
							case "SF":
								chartDater[5].push(percent != null? percent:0);
								break;
							case "DR":
								chartDater[6].push(percent != null? percent:0);
								break;
							case "CM":
								chartDater[7].push(percent != null? percent:0);
								break;
							case "MA":
								chartDater[8].push(percent != null? percent:0);
								break;
							case "AC":
								chartDater[9].push(percent != null? percent:0);
								break;
							case "UN":
								chartDater[10].push(percent != null? percent:0);
								break;

							default:
								break;
							}// end of switch()-------------------------
							
							
						});// end of each 내부-------------------------------------
					
						
						for(var i=0; i<11; i++) { 
							if(chartDater[i].length != entryIndex+1) {
								chartDater[i].push(0);
							}  
						}
						
					}
					else { 
						for(var i=0; i<11; i++) { 
							chartDater[i].push(0);  
						}
					}
					
				});// end of each 외부--------------------------

				for(var i=0; i<11; i++) {
					console.log(chartDater[i]);
				}
				
		 		if(json.length > 0){
					Highcharts.chart('genre', {
					    chart: {
					        type: 'column'
					    },
					    title: {
					        text: '장르별 도서 인기'
					    },
					    subtitle: {
					        text: '출처 : 데이터베이스'
					    },
					    xAxis: {
					        categories: [
					            '1월',
					            '2월',
					            '3월',
					            '4월',
					            '5월',
					            '6월',
					            '7월',
					            '8월',
					            '9월',
					            '10월',
					            '11월',
					            '12월'
					        ],
					        crosshair: true
					    },
					    yAxis: {
					        min: 0,
					        title: {
					            text: '대여 비율'
					        }
					    },
					    tooltip: {
					        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
					        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
					            '<td style="padding:0"><b>{point.y:.0f} %</b></td></tr>',  
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
					        name: '로맨스',
					        data: chartDater[0]

					    }, {
					        name: '스릴러',
					        data: chartDater[1]

					    }, {
					        name: '공포',
					        data: chartDater[2]

					    }, {
					        name: '추리',
					        data: chartDater[3]

					    }, {
					        name: '판타지',
					        data: chartDater[4]

					    }, {
					        name: '공상과학',
					        data: chartDater[5]

					    }, {
					        name: '드라마',
					        data: chartDater[6]

					    }, {
					        name: '코미디',
					        data: chartDater[7]

					    }, {
					        name: '무협',
					        data: chartDater[8]

					    }
					    , {
					        name: '액션',
					        data: chartDater[9]

					    }
					    , {
					        name: '미분류',
					        data: chartDater[10]

					    }]
					});
					
				}// end of if-----------------------
				else {
					$("#genre").html("결과가 없습니다.");
				}
				
				
				
			},
			error: function(request, status, error){
				alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
				$("#loading1").hide();
			}
			
		});// end of $.ajax()-------------------------
		
		
		
	});// end of $(document).ready()-----------------------------------


</script> 

<div>
	<div class="col-xs-12">
	
		<div class="col-xs-2">
			<a class="btn btn-info btn-select btn-select-light">
				<input type="hidden" class="btn-select-input" id="termval" name="term" value="" />
				<span class="btn-select-value">2019년</span>
				<span class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
				<ul id="term">
					<li value="0">2019년</li>
					<li value="1">2018년</li>
				</ul>
			</a>
			 
			<a class="btn btn-info btn-select btn-select-light">
				<input type="hidden" class="btn-select-input" id="" name="" value="" />
				<span class="btn-select-value">선택</span>
				<span class='btn-select-arrow glyphicon glyphicon-chevron-down'></span>
				<ul>
					<li>Option 1</li>
					<li>Option 2</li>
					<li>Option 3</li>
					<li>Option 4</li>
				</ul>
			</a>
		</div>
	
	</div>
	<div id="genre" style="margin: 0 auto; border-radius: 5px; clear: both;"></div>
</div>