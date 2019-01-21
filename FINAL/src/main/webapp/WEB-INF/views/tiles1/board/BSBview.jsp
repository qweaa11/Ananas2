<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    

<style type="text/css">
.one-post {
  margin: 0px 0px 25px 0px;
}
 .bd-radius {
  border-radius: 4px;
  background-color: #ffffff;
  box-shadow: 0 1px 7px rgba(0,0,0,0.05);
  border: 1px solid rgba(0,0,0,0.09) !important;
}
.bd-radius .img-height {
  width: 100% !important;
  height: auto !important;
}
.bd-radius .img-height .video {
  width: 100% !important;
  height: auto !important;
}
.bd-radius .post-post-content {
  padding: 10px 15px 10px 15px !important;
}
.bd-radius .post-post-content.postMetaInline-media {
  width: 100%;
  border-top: 1px solid #eee;
}
.bd-radius .post-post-content.postMetaInline-media .media .media-left a {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  line-height: normal;
}
.bd-radius .post-post-content.postMetaInline-media .media .media-left a .media-object {
  border-radius: 50%;
  width: 46px !important;
  height: 46px !important;
}
.bd-radius .post-post-content.postMetaInline-media .media .media-body {
  display: table-cell;
  vertical-align: middle;
  font-size: 14px;
  line-height: 1.4;
  text-rendering: auto;
}
.bd-radius .post-post-content.postMetaInline-media .media .media-body .media-heading {
  font-weight: 400;
  color: #000;
  font-size: 17px;
}
.bd-radius .post-post-content.postMetaInline-media .media .media-body .media-heading-muted {
  color: rgba(0,0,0,0.44) !important;
  font-size: 12px;
  display: flex;
}
.bd-radius .post-post-content.postMetaInline-media .media .media-body .media-heading-muted .fa-map-marker {
  font-size: 14px;
  margin-top: 2px;
}
.bd-radius .post-post-content.postMetaInline-media .nav li .timed {
  font-size: 15px;
  color: #adadad;
}
.bd-radius .post-post-content.postMetaInline-media .nav li .icondisplay {
  display: flex;
}
.bd-radius .post-post-content.postMetaInline-media .nav li .icondisplay span {
  font-size: 16px;
  color: #adadad;
}
.bd-radius .post-post-content.postMetaInline-media .nav li .icondisplay .line {
  width: 1px !important;
  height: 20px !important;
  background-color: #bababa;
  margin: 2px 5px 0px 5px;
}
 .posttitle-font {
  font-size: 23px;
  font-weight: 600;
}
 .postdesc-font {
  font-size: 17px;
  word-spacing: 2px;
  font-weight: 400;
}
 .subpostMetaInline-media {
  width: 100%;
  border-top: 1px solid #eee;
  padding: 15px 15px 15px 15px !important;
}
.subpostMetaInline-media .media .media-left a {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  line-height: normal;
}
.subpostMetaInline-media .media .media-left a .media-object {
  border-radius: 50%;
  width: 46px !important;
  height: 46px !important;
}
.subpostMetaInline-media .media .media-body {
  display: table-cell;
  vertical-align: middle;
  font-size: 14px;
  line-height: 1.4;
  text-rendering: auto;
}
.subpostMetaInline-media .media .media-body .media-heading {
  font-weight: 400;
  color: #000;
  font-size: 17px;
}
.subpostMetaInline-media .media .media-body .media-heading-muted {
  color: rgba(0,0,0,0.44) !important;
  font-size: 12px;
  display: flex;
}
.subpostMetaInline-media .media .media-body .media-heading-muted .fa-map-marker {
  font-size: 14px;
  margin-top: 2px;
}
.subpostMetaInline-media .media .media-body .form-control.commentpostinput {
  border-radius: 0px;
  padding: 6px 35px 6px 6px;
}
.subpostMetaInline-media .nav li .timed {
  font-size: 15px;
  color: #adadad;
}
.subpostMetaInline-media .nav li .icondisplay {
  display: flex;
  float: right;
}
.subpostMetaInline-media .nav li .icondisplay span {
  font-size: 16px;
  color: #adadad;
}
.subpostMetaInline-media .nav li .icondisplay .line {
  width: 1px !important;
  height: 20px !important;
  background-color: #bababa;
}

.readmore {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  text-align: center;
  margin: 0;
  padding: 42px 0px 0px 0px;
  background: -webkit-linear-gradient(rgba(255,255,255,0) 0%,#ffffff 100%);
  background-image: -moz-linear-gradient(rgba(255,255,255,0) 0%,#ffffff 100%);
  background-image: -o-linear-gradient(rgba(255,255,255,0) 0%,#ffffff 100%);
  background-image: linear-gradient(rgba(255,255,255,0) 0%,#ffffff 100%);
  background-image: -ms-linear-gradient(rgba(255,255,255,0) 0%,#ffffff 100%);
}
.readmore .btn-default:hover {
  color: #FFF;
  background-color: #ffcd13;
  border-color: #f9e69d;
}
.readmore .btn-default .a-dec:hover {
  color: #FFF;
}
 .backbtn {
  border-radius: 8px 8px 0px 0px !important;
  box-shadow: 0px 1px 7px 0px rgba(0,0,0,0.2);
}
 .a-dec {
  text-decoration: none !important;
}

body{ background: #fafafa;}
.widget-area.blank {
background: none repeat scroll 0 0 rgba(0, 0, 0, 0);
-webkit-box-shadow: none;
-moz-box-shadow: none;
-ms-box-shadow: none;
-o-box-shadow: none;
box-shadow: none;
}
body .no-padding {
padding: 0;
}
.widget-area {
background-color: #fff;
-webkit-border-radius: 4px;
-moz-border-radius: 4px;
-ms-border-radius: 4px;
-o-border-radius: 4px;
border-radius: 4px;
-webkit-box-shadow: 0 0 16px rgba(0, 0, 0, 0.05);
-moz-box-shadow: 0 0 16px rgba(0, 0, 0, 0.05);
-ms-box-shadow: 0 0 16px rgba(0, 0, 0, 0.05);
-o-box-shadow: 0 0 16px rgba(0, 0, 0, 0.05);
box-shadow: 0 0 16px rgba(0, 0, 0, 0.05);
float: left;
margin-top: 30px;
padding: 25px 30px;
position: relative;
width: 100%;
}
.status-upload {
background: none repeat scroll 0 0 #f5f5f5;
-webkit-border-radius: 4px;
-moz-border-radius: 4px;
-ms-border-radius: 4px;
-o-border-radius: 4px;
border-radius: 4px;
float: left;
width: 100%;
}
.status-upload form {
float: left;
width: 100%;
}
.status-upload form textarea {
background: none repeat scroll 0 0 #fff;
border: medium none;
-webkit-border-radius: 4px 4px 0 0;
-moz-border-radius: 4px 4px 0 0;
-ms-border-radius: 4px 4px 0 0;
-o-border-radius: 4px 4px 0 0;
border-radius: 4px 4px 0 0;
color: #777777;
float: left;
font-family: Lato;
font-size: 14px;
height: 142px;
letter-spacing: 0.3px;
padding: 20px;
width: 100%;
resize:vertical;
outline:none;
border: 1px solid #F2F2F2;
}

.status-upload ul {
float: left;
list-style: none outside none;
margin: 0;
padding: 0 0 0 15px;
width: auto;
}
.status-upload ul > li {
float: left;
}
.status-upload ul > li > a {
-webkit-border-radius: 4px;
-moz-border-radius: 4px;
-ms-border-radius: 4px;
-o-border-radius: 4px;
border-radius: 4px;
color: #777777;
float: left;
font-size: 14px;
height: 30px;
line-height: 30px;
margin: 10px 0 10px 10px;
text-align: center;
-webkit-transition: all 0.4s ease 0s;
-moz-transition: all 0.4s ease 0s;
-ms-transition: all 0.4s ease 0s;
-o-transition: all 0.4s ease 0s;
transition: all 0.4s ease 0s;
width: 30px;
cursor: pointer;
}
.status-upload ul > li > a:hover {
background: none repeat scroll 0 0 #606060;
color: #fff;
}
.status-upload form button {
border: medium none;
-webkit-border-radius: 4px;
-moz-border-radius: 4px;
-ms-border-radius: 4px;
-o-border-radius: 4px;
border-radius: 4px;
color: #fff;
float: right;
font-family: Lato;
font-size: 14px;
letter-spacing: 0.3px;
margin-right: 9px;
margin-top: 9px;
padding: 6px 15px;
}
.dropdown > a > span.green:before {
border-left-color: #2dcb73;
}
.status-upload form button > i {
margin-right: 7px;
}
.user_name{
    font-size:14px;
    font-weight: bold;
}
.comments-list .media{
    border-bottom: 1px dotted #ccc;
}

</style>


<div class="container">
	<div class="row">
		<h2><img alt="boardlist" src="resources/img/boardlist.png" width="30px;" height="30px;">&nbsp;<span style="font-weight: bolder;">자유 게시판</span></h2>
        <div class="">                    
    <div class="one-post">        
        <!--Post Description-->
        <div class="bd-radius">
            <div class="post-post-content postMetaInline-media" style="">
                <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9 pd">                                                
                    <div class="media">
                        <div class="media-left">
                            <a href="#">
                                <img src="http://www.freeiconspng.com/uploads/person-male-light-icon--vista-people-iconset--icons-land-20.png" class="media-object" alt="Sample Image">
                            </a>
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading mg mg-t-5">
                                <a href="#">
                                    <span>${boardvo.name}</span>
                                </a>
                            </h4>
                            
                            <span class="media-heading-muted">${boardvo.libid_fk}  </span>
                        </div>
                        <h4 class="mg mg-t-5 mg-b-5 posttitle-font">${boardvo.subject }</h4>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 pd">
                    <ul class="nav list-unstyled">
                        <li>
                            <div class="text-right timed">${boardvo.regDate}</div>
                        </li>
                        <li>
                            <div class="text-right timed">
                               
                                    <span> <i class="glyphicon glyphicon-eye-open"></i>&nbsp;조회수 : &nbsp;${boardvo.readCount}</span>
                               
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="clearfix"></div>
            </div>
           
            <div class="pd-b-10 pd-t-10 pd-r-15 pd-l-15" style="max-height: 250px;position: relative;overflow: hidden;  overflow-y: scroll; height:300px;">
                
                
                    <div style="padding-left: 40px;">${boardvo.content}</div>
                   
            </div>
            <div class="post-post-content postMetaInline-media">
                <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9 pd">                                                
                    <ul class="nav list-unstyled">
                        <li>
                            <div class="icondisplay">
                                <a data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                    <svg class="svgIcon-use" width="23" height="23" viewBox="0 0 25 25">
                                    <path d="M19 6c0-1.1-.9-2-2-2H8c-1.1 0-2 .9-2 2v14.66h.012c.01.103.045.204.12.285a.5.5 0 0 0 .706.03L12.5 16.85l5.662 4.126a.508.508 0 0 0 .708-.03.5.5 0 0 0 .118-.285H19V6zm-6.838 9.97L7 19.636V6c0-.55.45-1 1-1h9c.55 0 1 .45 1 1v13.637l-5.162-3.668a.49.49 0 0 0-.676 0z" fill-rule="evenodd"></path>
                                    </svg>
                                </a>
                                <span>55</span>
                                <div class="line"></div>
                                <a data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                    <svg class="svgIcon-use" width="23" height="23" viewBox="0 0 25 25">
                                    <path d="M12.5 21a.492.492 0 0 1-.327-.122c-.278-.24-.61-.517-.978-.826-2.99-2.5-7.995-6.684-7.995-10.565C3.2 6.462 5.578 4 8.5 4c1.55 0 3 .695 4 1.89a5.21 5.21 0 0 1 4-1.89c2.923 0 5.3 2.462 5.3 5.487 0 3.97-4.923 8.035-7.865 10.464-.42.35-.798.66-1.108.93a.503.503 0 0 1-.327.12zM8.428 4.866c-2.414 0-4.378 2.05-4.378 4.568 0 3.475 5.057 7.704 7.774 9.975.243.2.47.39.676.56.245-.21.52-.43.813-.68 2.856-2.36 7.637-6.31 7.637-9.87 0-2.52-1.964-4.57-4.377-4.57-1.466 0-2.828.76-3.644 2.04-.1.14-.26.23-.43.23-.18 0-.34-.09-.43-.24-.82-1.27-2.18-2.03-3.65-2.03z" fill-rule="evenodd"></path>
                                    </svg>
                                </a>
                                <span>55</span>
                                <div class="line"></div>
                                <a data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                    <svg class="svgIcon-use" width="23" height="23" viewBox="0 0 25 25">
                                    <path d="M12.5 21a.492.492 0 0 1-.327-.122c-.278-.24-.61-.517-.978-.826-2.99-2.5-7.995-6.684-7.995-10.565C3.2 6.462 5.578 4 8.5 4c1.55 0 3 .695 4 1.89a5.21 5.21 0 0 1 4-1.89c2.923 0 5.3 2.462 5.3 5.487 0 3.97-4.923 8.035-7.865 10.464-.42.35-.798.66-1.108.93a.503.503 0 0 1-.327.12zM8.428 4.866c-2.414 0-4.378 2.05-4.378 4.568 0 3.475 5.057 7.704 7.774 9.975.243.2.47.39.676.56.245-.21.52-.43.813-.68 2.856-2.36 7.637-6.31 7.637-9.87 0-2.52-1.964-4.57-4.377-4.57-1.466 0-2.828.76-3.644 2.04-.1.14-.26.23-.43.23-.18 0-.34-.09-.43-.24-.82-1.27-2.18-2.03-3.65-2.03z" fill-rule="evenodd"></path>
                                    </svg>
                                </a>
                                <span>55</span>
                            </div> 
                        </li>
                    </ul>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 pd">
                    <ul class="nav list-unstyled">
                        <li>
                            <div class="icondisplay pull-right">
                                <a data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                    <svg class="svgIcon-use" width="23" height="23" viewBox="0 0 25 25">
                                    <path d="M19 6c0-1.1-.9-2-2-2H8c-1.1 0-2 .9-2 2v14.66h.012c.01.103.045.204.12.285a.5.5 0 0 0 .706.03L12.5 16.85l5.662 4.126a.508.508 0 0 0 .708-.03.5.5 0 0 0 .118-.285H19V6zm-6.838 9.97L7 19.636V6c0-.55.45-1 1-1h9c.55 0 1 .45 1 1v13.637l-5.162-3.668a.49.49 0 0 0-.676 0z" fill-rule="evenodd"></path>
                                    </svg>
                                </a>
                                <span>55</span>
                                <div class="line"></div>
                                <a data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                    <svg class="svgIcon-use" width="23" height="23" viewBox="0 0 25 25">
                                    <path d="M12.5 21a.492.492 0 0 1-.327-.122c-.278-.24-.61-.517-.978-.826-2.99-2.5-7.995-6.684-7.995-10.565C3.2 6.462 5.578 4 8.5 4c1.55 0 3 .695 4 1.89a5.21 5.21 0 0 1 4-1.89c2.923 0 5.3 2.462 5.3 5.487 0 3.97-4.923 8.035-7.865 10.464-.42.35-.798.66-1.108.93a.503.503 0 0 1-.327.12zM8.428 4.866c-2.414 0-4.378 2.05-4.378 4.568 0 3.475 5.057 7.704 7.774 9.975.243.2.47.39.676.56.245-.21.52-.43.813-.68 2.856-2.36 7.637-6.31 7.637-9.87 0-2.52-1.964-4.57-4.377-4.57-1.466 0-2.828.76-3.644 2.04-.1.14-.26.23-.43.23-.18 0-.34-.09-.43-.24-.82-1.27-2.18-2.03-3.65-2.03z" fill-rule="evenodd"></path>
                                    </svg>
                                </a>
                                <span>55</span>

                            </div> 
                        </li>
                    </ul>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        
        <div style="margin-top: 30px;">
        <a href="board.ana"><button class="btn btn-primary btn-xs" data-title="board"  data-target="#board" style="float: right;" ><span class="glyphicon glyphicon-menu-hamburger">&nbsp;목록보기</span></button></a>        
        </div>
        
    <div class="container">
            <div class="row">
                <div class="col-md-8">
                  <div class="page-header">
                    <h1><small class="pull-right">45 comments</small> Comments </h1>
                  </div> 
                   <div class="comments-list">
                       <div class="media">
                           <p class="pull-right"><small>5 days ago</small></p>
                            <a class="media-left" href="#">
                              <img src="http://lorempixel.com/40/40/people/1/">
                            </a>
                            <div class="media-body">
                                
                              <h4 class="media-heading user_name">Baltej Singh</h4>
                              Wow! this is really great.
                              
                              <p><small><a href="">Like</a> - <a href="">Share</a></small></p>
                            </div>
                          </div>
                       <div class="media">
                           <p class="pull-right"><small>5 days ago</small></p>
                            <a class="media-left" href="#">
                              <img src="http://lorempixel.com/40/40/people/2/">
                            </a>
                            <div class="media-body">
                                
                              <h4 class="media-heading user_name">Baltej Singh</h4>
                              Wow! this is really great.
                              
                              <p><small><a href="">Like</a> - <a href="">Share</a></small></p>
                            </div>
                          </div>
                       <div class="media">
                           <p class="pull-right"><small>5 days ago</small></p>
                            <a class="media-left" href="#">
                              <img src="http://lorempixel.com/40/40/people/3/">
                            </a>
                            <div class="media-body">
                                
                              <h4 class="media-heading user_name">Baltej Singh</h4>
                              Wow! this is really great.
                              
                              <p><small><a href="">Like</a> - <a href="">Share</a></small></p>
                            </div>
                          </div>
                       <div class="media">
                           <p class="pull-right"><small>5 days ago</small></p>
                            <a class="media-left" href="#">
                              <img src="http://lorempixel.com/40/40/people/4/">
                            </a>
                            <div class="media-body">
                                
                              <h4 class="media-heading user_name">Baltej Singh</h4>
                              Wow! this is really great.
                              
                              <p><small><a href="">Like</a> - <a href="">Share</a></small></p>
                            </div>
                          </div>
                   </div>
                    
                    
                    
                </div>
            </div>
        </div>
      
      <div class="container">
	<div class="row">
		<h3>댓글쓰기</h3>
	</div>
    
    <div class="row">
    
    <div class="col-md-6">
    						<div class="widget-area no-padding blank">
								<div class="status-upload">
									<form>
										<textarea placeholder="What are you doing right now?" ></textarea>
										<ul>
											
										</ul>
										<button type="submit" class="btn btn-success green"><i class="fa fa-share"></i> 작성하기</button>
									</form>
								</div><!-- Status Upload  -->
							</div><!-- Widget Area -->
						</div>
        
    </div>
</div>
      
    </div>
</div>
	</div>
</div>