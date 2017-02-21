/**
 * login-mui
 */
(function($, owner) {
	$.enterfocus = function(selector, callback) {
		var boxArray = [].slice.call(document.querySelectorAll(selector));
		for (var index in boxArray) {
			var box = boxArray[index];
			box.addEventListener('keyup', function(event) {
				if (event.keyCode == 13) {
					var boxIndex = boxArray.indexOf(this);
					if (boxIndex == boxArray.length - 1) {
						if (callback) callback();
					} else {
						var nextBox = boxArray[++boxIndex];
						nextBox.focus();
					}
				}
			}, false);
		}
	};
	
	/**
	 * 用户登录
	 **/
	owner.login = function(loginInfo, callback) {
		callback = callback || $.noop;
		loginInfo = loginInfo || {};
		loginInfo.account = loginInfo.account || '';
		loginInfo.password = loginInfo.password || '';
		if (loginInfo.account.length < 5) {
			return callback('账号最短为 5 个字符');
		}
		if (loginInfo.password.length < 6) {
			return callback('密码最短为 6 个字符');
		}
		
		//登录请求
	};
}(mui, window.app = {}));