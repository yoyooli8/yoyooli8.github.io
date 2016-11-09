require(["jquery"],function(require){
	var $   = require('jquery');
	console.log('-----------------------------');
	
	var appID = "101347236";
	//登录授权后的回调地址，设置为当前url
	var redirectURI = "http://www.shuaon.com";
	
	var item = {
			toParamMap:function(str){
				var map = {};
				var segs = str.split("&");
				for (var i in segs){
					var seg = segs[i];
					var idx = seg.indexOf('=');
					if(idx < 0){
						continue;
					}
					var name = seg.substring(0, idx);
					var value = seg.substring(idx+1);
					map[name] = value;
				}
				return map;
			},
			openImplict:function(url){
				var script = document.createElement('script');
				script.src = url;
				document.body.appendChild(script);
			},
			callback:function(obj){
				var openid = obj.openid;
				$("#openid").text(openid);
				//跳转服务端登录url
				var resulturl = "http://www.shuaon.com"; 
				var accessToken = $("#accessToken").text();
				//向服务端传输access_token及openid参数
				document.location.href=resulturl + "?access_token=" + accessToken + "&openid=" + openid;
			}
	};
	
	if (window.location.hash.length == 0){
		var path = 'https://graph.qq.com/oauth2.0/authorize?';
		var queryParams = ['client_id=' + appID,
		                   'redirect_uri=' + encodeURIComponent(redirectURI),
		                   'state=' + 1,
		                   'scope=' + 'get_user_info','response_type=code'];
		var query = queryParams.join('&');
		var url = path + query;
		console.log('url====>'+url);
		window.location.href= url;
	}else{
		//在成功授权后回调时location.hash将带有access_token信息，开始获取openid
		//获取access token
		var accessToken = window.location.hash.substring(1);
		var map = item.toParamMap(accessToken);
		console.log('accessToken====>'+accessToken);
		console.log(map);
		//记录accessToken
		$("#accessToken").text(map.access_token);
		$("#expire").text(map.expires_in);
		
		//使用Access Token来获取用户的OpenID
		var path = "https://graph.qq.com/oauth2.0/me?";
		var queryParams = ['access_token='+map.access_token, 'callback=callback'];
		var query = queryParams.join('&');
		var url = path + query;
		item.openImplict(url);
	}

});