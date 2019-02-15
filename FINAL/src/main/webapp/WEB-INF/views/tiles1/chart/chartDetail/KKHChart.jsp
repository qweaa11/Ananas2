<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="resources/js/KKHchart.js"></script>
<script>

$(document).ready(function(){
	
	chartByCategoryInLibrary();
	
});

var libraryArr = [];
var countByCategoryArr = [];


function chartByCategoryInLibrary(){

	$.ajax({
		url:"getAllLibrary.ana",
		type:"GET",
		dataType:"JSON",
		success:function(json){
			
			$.each(json,function(libIndex,lib){
				libraryArr.push(lib.LIBNAME);
			});//End Of each
			
		},error: function(request, status, error){
			alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		}
	});//End of ajax(getAllLibrary.ana)
		
	
	$.ajax({
		url:"getAllCategory.ana",
		type:"GET",
		dataType:"JSON",
		success:function(json1){
			
		
			$.each(json1,function(categoryIndex, category){        
				var valArr= [];
				var dataform = {"ccode":category.CCODE};
				$.ajax({
					url:"KKHChartLibrary.ana",
					type:"GET",
					data:dataform,
					async: false,
					dataType:"JSON",
					success:function(json2){
							
							$.each(json2,function(valueIndex,value){
								valArr[valueIndex] = Number(value.CNT);
							});//End of each
							
					
					},error: function(request, status, error){
						alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
					}
				});//End of ajax(chartLibrary.ana)
				
				var temp = {"name":category.CNAME,"data":valArr};
				
				countByCategoryArr.push(temp);//End of Array push
				
				
			});//End Of each
			
			
			
			console.log(countByCategoryArr[0].data[0]); 
			console.log(countByCategoryArr); 
			
			Highcharts.chart('KKHcontainer', { 
			    chart: {
			        type: 'bar'
			    },
			    title: {
			        text: '도서관별 보유 장서 비율'
			    },
			    xAxis: {
			    	categories:libraryArr
			    },
			    yAxis: {
			        min: 0,
			        title: {
			            text: 'Ananas'
			        }
			    },
			    legend: {
			        reversed: true
			    },
			    plotOptions: {
			        series: {
			            stacking: 'normal'
			        }
			    },
			    series:  [{
			        name: countByCategoryArr[0].name,
			        data: countByCategoryArr[0].data//countByCategoryArr[0].data
			    }, {
			        name: countByCategoryArr[1].name,
			        data:  countByCategoryArr[1].data//countByCategoryArr[1].data
			    }, {
			        name: countByCategoryArr[2].name,
			        data: countByCategoryArr[2].data
			    }, {
			        name: countByCategoryArr[3].name,
			        data: countByCategoryArr[3].data
			    }, {
			    	name: countByCategoryArr[4].name,
			     	data:countByCategoryArr[4].data
			    }, {
		    	 	name: countByCategoryArr[5].name,
		     		data:countByCategoryArr[5].data
		    	},{
		    	 	name: countByCategoryArr[6].name,
		     		data: countByCategoryArr[6].data  
		    	},{
		    	 	name: countByCategoryArr[7].name,
		     		data:countByCategoryArr[7].data
		    	},{
			 		name: countByCategoryArr[8].name,
		 			data: countByCategoryArr[8].data
				}] 
			});
			
		},error: function(request, status, error){
			alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
		}
	});
		
		
		

}
</script>
<div id="KKHcontainer" style="min-width: 310px; max-width: 100%; height: 500px; margin: 0 auto">


</div>
    