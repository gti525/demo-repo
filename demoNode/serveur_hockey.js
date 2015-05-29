var http = require('http');

var joueurs;

//Fonction qui parse le fichier
var Parser = require('./parser');

// Module de fileSystem
var fs = require('fs');

// Lis le fichier en mémoire
fs.readFile('joueurs.csv', function (err, fichierJoueurs) {
  
  if (err) throw err;
  
  var contenuFichier = fichierJoueurs.toString();
  
  var parser = new Parser();
  
  joueurs = parser.parse(contenuFichier);
});

http.createServer(function (req, res) {
  res.writeHead(200, {'Content-Type': 'application/json'});
  res.write(joueurs);
  res.end();
}).listen(8080);

console.log('Server running on port 8080.');