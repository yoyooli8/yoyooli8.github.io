var http = require('http');
var url  = require('url');
var fs   = require('fs');
var mine = require('./mine').types;
var path = require('path');

var server = http.createServer(function(req,res){
	var pathname = url.parse(req.url).pathname;
	if(pathname == '/') pathname = pathname + 'index.html';
	var realpath = path.join('charisma-master',pathname);
	console.log(realpath);
	var ext      = path.extname(realpath);
	ext = ext ? ext.slice(1) : 'unknown';

	fs.exists(realpath,function(exists){
		if(!exists){
			res.writeHead(404,{
				'Content-Type':'text/plain'
			});

			res.write('This request URL' + pathname + ' was not found on the server.');
			res.end();
		}else{
			fs.readFile(realpath,'binary',function(err,file){
				if(err){
					res.writeHead(500,{
						 'Content-Type':'text/plain'
					});
					res.end(err);
				}else{
					var contentType = mine[ext] || 'text/plain';
					res.writeHead(200,{
						 'Content-Type':contentType
					});
					res.write(file,'binary');
					res.end();
				}
			});
		}
	});
});

server.listen(8088);
console.log('Server runing at port:8088');

