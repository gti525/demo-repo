/*
 * Serve content over a socket
 */
fs = require('fs');

module.exports = function (socket) {

  setInterval(function () {
    socket.emit('send:time', {
      time: (new Date()).toString()
    });
  }, 1000);

  setInterval(function () {
  	var data = fs.readFileSync('message.txt');
    socket.emit('send:message', {
		  message: data.toString()
	});
  }, 5000);
};
