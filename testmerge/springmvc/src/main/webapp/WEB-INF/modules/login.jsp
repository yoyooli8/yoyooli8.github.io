<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
    <style type="text/css">
        body{
            padding: 0 0;
            margin:  0 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-sm-1">1</div>
            <div class="col-sm-2">2</div>
            <div class="col-sm-3">3</div>
            <div class="col-sm-4">4</div>
            <div class="col-sm-2">2</div>
        </div>
    </div>
    <script type="text/javascript">
        require(["jquery","bootstrap"],function($){
        	$('.container').append('<div class="row"><div class="col-sm-5">5</div><div class="col-sm-7">7</div></div>');
        	$('.container .row > div').css({borderColor:'gray',borderStyle:'solid',borderWidth:'1px'});
        });
    </script>
</body>
</html>