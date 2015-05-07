// Parser constructor.
var Parser = function() {

};

// Parses the specified text.
Parser.prototype.parse = function(text) {
  
// Break up the file into lines.
  var lines = text.split('\n');
  
  var result = [];
 
  var headers=lines[0].split(',');
 
  for(var i=1;i<lines.length;i++){
 
	  var obj = {};
	  var currentline=lines[i].split(",");
 
	  for(var j=0;j<headers.length;j++){
		  obj[headers[j]] = currentline[j];
	  }
 
	  result.push(obj);
 
  }
  
  //return result; //JavaScript object
  return JSON.stringify(result); //JSON
};

// Export the Parser constructor from this module.
module.exports = Parser;