var http = require('http');

http.createServer(function (req, res) {
  res.writeHead(200, {'Content-Type': 'text/html'});
  res.write("<h1>Salut tout le monde</h1>");
  res.end();
}).listen(8080);

console.log('Le serveur roule sur le port 8080.');