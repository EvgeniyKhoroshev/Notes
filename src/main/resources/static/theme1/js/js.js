	var input = document.body.children[0];


	(function () {

    function httpGet(theUrl)
    {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
        xmlHttp.send( null );
        return xmlHttp.responseText;
    }

      input.oninput.getElementById("filter_text") = function() {
        var el = document.getElementById('filter_text');
      };
	}());