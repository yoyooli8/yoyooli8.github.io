<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
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
            <p>AccessToken:<span id="accessToken"></span>--ExpireIn<span id="expire"></span></p>
            <p>OpenID:<span id="openid"></span></p>
            <button class="btn btn-default" id="qqLoginBtn">qq登陆</button>
        </div>
    </div>
    <script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" data-appid="101347236" data-scope="get_user_info" data-redirecturi="http://www.shuaon.com/index" charset="utf-8"></script>
    <script type="text/javascript">
        (function(){
        	QC.Login({btnId:'qqLoginBtn'});
        })();
    </script>
</body>
</html>