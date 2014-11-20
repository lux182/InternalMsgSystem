<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt"	prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"	prefix="fmt" %>
<%@ include file="../GlobalHeader.jsp"%>

<div class="container-fluid">
	<div class="row-fluid">
		<div>
			<div class="page-header">
			 <h1>内部信息系统&emsp;<small><a href="/logout">logout</a></small></h1>
			</div>
		</div>
		<div style="padding:10px;">
			<div class="tabbable" id="tabs">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel-msg" data-toggle="tab">信息管理</a>
					</li>
					<li>
						<a href="#panel-base" data-toggle="tab">系统基本参数</a>
					</li>
					
				</ul>
				
				<div class="tab-content" style="padding:10px;">
					<!-- base tab -->
					<div class="tab-pane" id="panel-base">
						
						<!-- cfg configuration -->
						<div class="col-xs-6">
							<div class="panel panel-default">
							  <div class="panel-heading">系统参数配置&emsp;<a href="/cfg/init">重新加载</a></div>
							  <div class="panel-body">
							    <form action="/cfg" method="post" class="form-horizontal" style="padding:10px;">
							    	<c:forEach items="${cfgs}" var="cfg">
							    	<div class="form-group">
								    	<div class="input-group">
								     	 <div class="input-group-addon">${cfg.name()}</div>
								     	 <input class="form-control" type="text" name="${cfg.name()}" value="${cfg.value}">
								    	</div>
								    </div>
							    	</c:forEach>
							    	
							    	<button type="submit" class="btn btn-primary">修改</button>
							    </form>
							  </div>
							</div>
						</div>
					</div>
					
					<!-- msg tab -->
					<div class="tab-pane active" id="panel-msg">
						<!-- Message publish -->
						<div class="col-xs-6">
							<div class="panel panel-default">
							  <div class="panel-heading">发送消息</div>
							  <div class="panel-body">
							    <form action="/api/msg" method="post" class="form-horizontal" style="padding:10px;">
							    	<div class="form-group" id="titleDiv">
								    	<div class="input-group">
								     	 <div class="input-group-addon">Title</div>
								     	 <input class="form-control" type="text" name="title" placeholder="Message Title" value="系统消息">
								    	</div>
								    </div>
								    <div class="form-group">
								     	<lable>Content</lable>
								    	<textarea class="form-control" type="text" name="content" placeholder="Message Content" style="height: 150px;"></textarea>
								    </div>
								    
								    <div class="form-group" id="emailDiv" style="display:none">
								    	<div class="input-group">
								     	 <div class="input-group-addon">Email</div>
								     	 <input class="form-control" type="email" name="email" placeholder="Reciver's Email">
								    	</div>
								    </div>
								    <div class="form-group" id="urlDiv" style="display:none">
								    	<div class="input-group">
								     	 <div class="input-group-addon">URL</div>
								     	 <input class="form-control" type="text" name="url" placeholder="Open URL">
								    	</div>
								    </div>
								    <div class="form-group" id="phoneDiv" style="display:none">
								    	<div class="input-group">
								     	 <div class="input-group-addon">Phone</div>
								     	 <input class="form-control" type="text" name="phone" placeholder="Reciver's Phone">
								    	</div>
								    </div>
									
								    <div class="form-group" id="indateDiv" style="display:none">
								    	<div class="input-group">
								     	 <div class="input-group-addon">Indate Days(天)</div>
								     	 <input class="form-control" type="number" name="indateDay" placeholder="Message Indate Days" value="-1">
								    	</div>
								    </div>
								    <div class="form-group" id="sendToDiv" style="display:none"> 
								    	<div class="input-group">
								     	 <div class="input-group-addon">Admin Send To</div>
								     	 <input class="form-control" type="text" name="recId" placeholder="Message Recevier ID">
								    	</div>
								    </div>
								    <div class="form-group">
								    	<lable>Channel</lable>
								    	<select name="chanel" class="form-control" id="channelSelect">
										  <option value="INNER" selected>站内信</option>
										  <option value="BAIDU_PUSH">Baidu推送</option>
										  <option value="SMS">短信通知</option>
										  <option value="EMAIL">Email</option>
										</select>
								    </div>
								    <div class="checkbox">
								     <label id="persistenceLable">
								       <input id="persistenceCheck" type="checkbox" name="persistence" checked disabled>Need Persistence
								     </label>
								     <span id="optional">
								     <label>
								       <input id="indateCheck" type="checkbox" name="timeLimit" >Time limitation Msg 
								     </label>
								     <label>
								       <input id="publicCheck" type="radio" name="type" value="PUBLIC" checked >Public
								     </label>
								     <label>
								       <input id="privateCheck" type="radio" name="type" value="PRIVATE">Private
								     </label>
								     </span>
								    </div>
								    <button type="submit" class="btn btn-primary">发送</button>
							    </form>
							  </div>
							</div>
						</div>
						
						<!-- Message Record -->
						<div class="col-xs-6">
						
							<div class="panel panel-default">
							  <div class="panel-heading">消息记录</div>
							  <div class="panel-body">
							  <table class="table table-hover">
							      <thead>
							        <tr>
							          <th>#</th>
							          <th>Title</th>
							          <th>Content</th>
							          <th>Indate</th>
							          <th>Type</th>
							        </tr>
							      </thead>
							      <tbody>
							        <c:forEach items="${msgs}" var="msg">
							        <tr>
							          <td>${msg.id}</td>
							          <td>${msg.title}</td>
							          <td>${msg.content}</td>
							          <td>
							          <c:if test="${empty msg.indate}">一直有效</c:if>
							          <c:if test="${!empty msg.indate}">
							          <fmt:formatDate value="${msg.indate}" pattern="yyyy-MM-dd mm:hh" />
							          </c:if>
							          </td>
							          <td>${msg.type.name()}</td>
							        </tr>
							        </c:forEach>
							      </tbody>
							    </table>
							  </div>
							</div>

						</div>
					</div>
				
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	$(function(){
		
		$("#publicCheck").click(function(){
			$("#sendToDiv").hide();
		});
		$("#privateCheck").click(function(){
			$("#sendToDiv").show();
		});
		$("#indateCheck").click(function(){
			$("#indateDiv").toggle();
		});
		$("#channelSelect").change(function(){
			$("#persistenceCheck").prop('checked',false);
			if($("#channelSelect").val()=="SMS"){
				$("#phoneDiv").show();
				$("#emailDiv").hide();
				$("#optional").hide();
				$("#titleDiv").hide();
			}else if($("#channelSelect").val()=="EMAIL"){
				$("#emailDiv").show();
				$("#phoneDiv").hide();
				$("#optional").hide();
				$("#titleDiv").show();
			}
			else{
				$("#emailDiv").hide();
				$("#phoneDiv").hide();
				$("#optional").show();
				$("#titleDiv").show();
			}			
			
			if($("#channelSelect").val()=="INNER"){
				$("#persistenceCheck").prop('checked',true);
				$("#persistenceCheck").attr('disabled',true);
			}else{
				$("#persistenceCheck").attr('disabled',false);
			}
			
			if($("#channelSelect").val()=="BAIDU_PUSH"){
				$("#urlDiv").show();
			}else{
				$("#urlDiv").hide()
			}
		});
	});
</script>
<%@ include file="../GlobalFooter.jsp"%>