var feedback = "";
var ref;
var database;
$.getScript('https://www.gstatic.com/firebasejs/3.2.1/firebase.js', function (){

var config = {
    apiKey: "AIzaSyBhA0eDrGIGtHUkuK3-HD6wKQRm3cCYCa8",
	  authDomain: "employmepls-a631c.firebaseapp.com",
	  databaseURL: "https://employmepls-a631c.firebaseio.com",
	  projectId: "employmepls-a631c",
	  storageBucket: "employmepls-a631c.appspot.com",
	  messagingSenderId: "682947596416"
  };
  firebase.initializeApp(config);
  database = firebase.database();

})

prepare();
function prepare(){
	
	$('#rj').mousedown(function(e){
	var effort_checkBox = document.getElementById("effort");
  	var req_checkBox = document.getElementById("req");
 	 var exp_checkBox = document.getElementById("exp");
 	 var comment = document.getElementById('comment').value;

 	 // If the checkbox is checked, display the output text
 	 if (effort_checkBox.checked == true){
  	  feedback = feedback.concat(" No effort on resume.\n");
 	 }
 	 if (req_checkBox.checked == true){
  	  feedback = feedback.concat(" Basic requirement not met.\n");
 	 }
 	 if (exp_checkBox.checked == true){
 	   feedback = feedback.concat(" Not enough industry experience.\n");
  	}
	if (feedback){	
	ref = database.ref("feedback").push();
	ref.child('additional').set(feedback);
	ref.child('comment').set(comment);
	ref.child('companyName').set("whatever company");
	}else{
	ref = database.ref("feedback").push();
	ref.child('additional').set("No Feedback provided");
	ref.child('comment').set("");
	ref.child('companyName').set("");
	}
	window.location='employer_step_1_2.html';
	});
}