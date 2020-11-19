function check(){

	if(!valid){
		return;
	}


	var question1 = document.quiz.question1.value;
	var question2 = document.quiz.question2.value;
	var question3 = document.quiz.question3.value;
	var question4 = document.quiz.question4.value;
	var question5 = document.quiz.question5.value;
	var question6 = document.quiz.question6.value;
	var score = 0;



	//formula: (3*age/10) + race + rheumArth + estr + fracHist - (weight(lbs)/10)



	//age
	score += (3*question1/10);

	//race
	if(question2 == "nonBlack"){
		score += 5;
	}

	//rheumArth
	if(question3 == "present"){
		score += 4;
	}

	//estr
	if(question4 == "noPriorUse"){
		score += 1;
	}

	//fracHist
	if(question5 == "noFracture"){
		score += 0;
	}

	else if(question5 == "oneFracture"){
		score += 4;
	}

	else if(question5 == "twoFracture"){
		score += 8;
	}

	else if(question5 == "threeFracture"){
		score += 12;
	}

	//weight
	score -= (question6*2.205)/10;

	document.getElementById("after_submit").style.visibility = "visible";
	document.getElementById("number_score").innerHTML = score.toFixed(1);
	document.quiz.hidden_score.value = score.toFixed(1);
}


function validate() {
      
	document.getElementById("after_submit").style.visibility = "hidden";
	document.getElementById("after_error").style.visibility = "hidden";

	//age
	if( document.quiz.question1.value == "" ) {
	    document.getElementById("after_error").style.visibility = "visible";
	    document.getElementById("error_message").innerHTML = errorMsg;
        valid = false;
        return;
    }

    //weight
    if( document.quiz.question6.value == "" ) {
	    document.getElementById("after_error").style.visibility = "visible";
	    document.getElementById("error_message").innerHTML = errorMsg;
        valid = false;
        return;
    }

    //everything else
    var chx = document.getElementsByTagName("input");
    var count = 0;
    for(var i = 0; i < chx.length; i++){
    	if(chx[i].type == "radio" && chx[i].checked){
    		count++;
    	}
    }
    if(count < 4){
    	document.getElementById("after_error").style.visibility = "visible";
	    document.getElementById("error_message").innerHTML = errorMsg;
		valid = false;
		return
	}


    valid = true;
    return;
}


var valid = true;
var errorMsg = "Please make sure you entered all of the information correctly.";