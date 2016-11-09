var path = window.location.origin || window.location.protocol+"//"+window.location.host;
require.config({
	baseUrl:path+"/assets/scripts",
	
    shim:{
    	'bootstrap': {
    		deps : ['jquery'],
    		exports : 'bt'  
    	}
    },
    hbs:{
    	devStyleDirectory:'../css/modules/',
    	templateExtension : 'hbs',
    	helperDirectory : 'template/helpers/',
    	handlebarsPath: 'handlebars'
    },
	paths:{
		"jquery": "jquery-1.10.2.min",
		"bootstrap": "bootstrap.min",
		'hbs' : 'plugins/handlebars/hbs',
		'handlebarsrun': 'plugins/handlebars/hbs/handlebars.runtime',
		'handlebars': 'plugins/handlebars/hbs/handlebars',
		'json2': 'plugins/handlebars/hbs/json2',
		'underscore': 'plugins/handlebars/hbs/underscore'
	}
});

