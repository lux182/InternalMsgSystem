<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>点融内部信息系统管理平台</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">

		<link href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
		<link href="http://cdn.bootcss.com/animate.css/3.2.0/animate.min.css" rel="stylesheet">
		<script src="http://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
		<style>
        	.alert{position:absolute;width:100%; margin-top:20px;text-shadow:0 1px 0 rgba(255, 255, 255, 0.5);background-color:#fcf8e3;border:1px solid #fbeed5;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px;}
        	.alert,.alert h4{color:#c09853;}
        	.alert-success{background-color:#dff0d8;border-color:#d6e9c6;color:#468847;}
			.alert-success h4{color:#468847;}
			.alert-danger,.alert-error{background-color:#f2dede;border-color:#eed3d7;color:#b94a48;}
			.alert-danger h4,.alert-error h4{color:#b94a48;}
        </style>
    </head>
    <body>
    <div class="container">
		<div class="row col-md-10 col-md-offset-1">
			<div class="span12">
				<div id="alert" style="display: none;" class="alert alert-success" >
					<button type="button" class="close" id="alertCloseBtn">×</button>
					<span id="message"></span>
				</div>
			</div>
		</div>
	</div>