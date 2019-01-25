<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<style type="text/css">
.process-step .btn:focus {
	outline: none
}

.process {
	display: table; 
	width: 100%;
	position: relative
}

.process-row {
	display: table-row
}

.process-step button[disabled] {
	opacity: 1 !important;
	filter: alpha(opacity = 100) !important
}

.process-row:before {
	top: 40px;
	bottom: 0;
	position: absolute;
	content: " ";
	width: 100%;
	height: 1px;
	background-color: #ccc;
	z-order: 0
}

.process-step {
	display: table-cell;
	text-align: center;
	position: relative
}

.process-step p {
	margin-top: 4px
}

.btn-circle {
	width: 80px;
	height: 80px;
	text-align: center;
	font-size: 12px;
	border-radius: 50%
}
</style>

<script type="text/javascript">
	$(function() {
		$('.btn-circle').on(
				'click',
				function() {
					$('.btn-circle.btn-info').removeClass('btn-info').addClass(
							'btn-default');
					$(this).addClass('btn-info').removeClass('btn-default')
							.blur();
				});

		$('.next-step, .prev-step').on('click', function(e) {
			var $activeTab = $('.tab-pane.active');

			$('.btn-circle.btn-info').removeClass('btn-info').addClass(
					'btn-default');

			if ($(e.target).hasClass('next-step')) {
				var nextTab = $activeTab.next('.tab-pane').attr('id');
				$('[href="#' + nextTab + '"]').addClass('btn-info')
						.removeClass('btn-default');
				$('[href="#' + nextTab + '"]').tab('show');
			} else {
				var prevTab = $activeTab.prev('.tab-pane').attr('id');
				$('[href="#' + prevTab + '"]').addClass('btn-info')
						.removeClass('btn-default');
				$('[href="#' + prevTab + '"]').tab('show');
			}
		});
	});
</script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"> 
<div class="col-xs-12" style="padding-left: 70px;">
	<div class="container" style="width: 100%; padding-right: 50px;">   
		<div class="row"> 
		
			<!-- 차트 목록 -->
			<div class="process">
				<div class="process-row nav nav-tabs">
				
					<div class="process-step">
						<button type="button" class="btn btn-info btn-circle" data-toggle="tab" href="#menu1">
							<span style="font-size: 20pt;">장르</span>
						</button>
					</div>
					
					<div class="process-step">
						<button type="button" class="btn btn-default btn-circle" data-toggle="tab" href="#menu2">
							<i class="fa fa-file-text-o fa-3x"></i>
						</button>
					</div>
					
					<div class="process-step">
						<button type="button" class="btn btn-default btn-circle" data-toggle="tab" href="#menu3">
							<i class="fa fa-image fa-3x"></i>
						</button>
					</div>
					
					<div class="process-step">
						<button type="button" class="btn btn-default btn-circle" data-toggle="tab" href="#menu4">
							<i class="fa fa-cogs fa-3x"></i>
						</button>
					</div>
					
					<div class="process-step">
						<button type="button" class="btn btn-default btn-circle" data-toggle="tab" href="#menu5">
							<i class="fa fa-check fa-3x"></i>
						</button>
					</div>
					
				</div>
			</div>
			<!-- /차트 목록 -->
			
			<!-- 차트 내용 -->
			<div class="tab-content" style="border-radius: 5px; border: 1px solid black; margin-top: 30px;"> 
			
				<div id="menu1" class="tab-pane fade active in">
					<h3>1번탭</h3>
				</div>
				
				<div id="menu2" class="tab-pane fade">
					<h3>2번탭</h3>
				</div>
				
				<div id="menu3" class="tab-pane fade">
					<h3>3번탭</h3>
				</div>
				
				<div id="menu4" class="tab-pane fade">
					<h3>4번탭</h3>
				</div>
				
				<div id="menu5" class="tab-pane fade">
					<h3>5번탭</h3>
				</div>
				
			</div>
			<!-- /차트 내용 -->
			
		</div>
	</div>
</div>