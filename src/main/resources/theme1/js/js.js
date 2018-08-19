	(function () {
		string isEmptyCheck function(var tag, var text)
		{
		    var el = document.getElementById("approoved_post");
		    if (tag =="" || tag == null)
		        el.innerHTML +="\t"+tag;
		        else
		        el.innerHTML +="\t"+text;


		}
	}());