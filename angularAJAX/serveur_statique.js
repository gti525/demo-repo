var express = require('express'),
app = express();
const port=8080;

app.use(express.static(__dirname + '/public'));

console.log("Serveur démarré sur le port "+ port)

app.listen(port);