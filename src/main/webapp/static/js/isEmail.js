// isEmail
// Validates email address has one (@), atleast one (.). 
// It also makes sure that there are no spaces, extra '@'s or a (.) just before or after the @. 
// It also makes sure that there is atleast one (.) after the @.

function isEmail(str) {

		var at="@";
		var dot=".";
		var lat=str.indexOf(at);
		var lstr=str.length;
		var ldot=str.indexOf(dot);
		
		if (str.indexOf(at)==-1) {
		
		   	return false;
		   	
		}

		if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr) {
		
		   	return false;
		   	
		}

		if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr) {
		
		    	return false;
		    
		}

		if (str.indexOf(at,(lat+1))!=-1){

			return false;

		}

		if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){

			return false;

		}

		if (str.indexOf(dot,(lat+2))==-1){

			return false;

		}

		if (str.indexOf(" ")!=-1){

			return false;

		}

		return true					
 		 
	}