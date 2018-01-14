//Reference http://www.williammalone.com/articles/create-html5-canvas-javascript-drawing-app/

var clickX = new Array();
var clickY = new Array();
var clickDrag = new Array();
var paint = false;
var colorRed = "#df4b26";
var colorGreen = "#659b41";
var curColor = colorRed;
var clickColor = new Array();
var ref;
var database;
var storage;  
var storageRef;
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
  storage = firebase.storage();
})

function prepareCanvas()
{
context = document.getElementById('canvas').getContext("2d");
    	 var canvasWidth = document.getElementById('canvas').offsetWidth;
    	 var canvasHeight = document.getElementById('canvas').offsetHeight;
    	 canvas.setAttribute('width', canvasWidth);
canvas.setAttribute('height', canvasHeight);

var cv = new Image();
cv.src = 'coverletter.jpg';
cv.onload = function(){
    context.drawImage(cv,0,1100);   
}
var resume = new Image();
resume.src = 'resume.jpg';
resume.onload = function(){
    context.drawImage(resume,0,0);   
}
$('#rj').mousedown(function(e){
var canvas = document.getElementById('canvas');
	var image = new Image();
	image.crossOrigin = "Anonymous";
	image.onload = function(){
		storageRef = storage.ref.child('feedback.png');
  		storageRef.setItem( "savedImageData", canvas.toDataURL() );
  	}
  	context.clearRect(0, 0, canvasWidth, canvasHeight);
var resume = new Image();
resume.src = 'resume2.jpg';
resume.onload = function(){
    context.drawImage(resume,0,0,475,550);   
}
clickX.length=0;
clickY.length=0;
clickDrag.length=0;
clickColor.length=0;
});

$('#gn').mousedown(function(e){
	curColor = colorGreen;
});
$('#rd').mousedown(function(e){
	curColor = colorRed;
});
$('#clear').mousedown(function(e){
	context.clearRect(0, 0, canvasWidth, canvasHeight);
	var cv = new Image();
cv.src = 'coverletter.jpg';
cv.onload = function(){
    context.drawImage(cv,0,1100);   
}
var resume = new Image();
resume.src = 'resume.jpg';
resume.onload = function(){
    context.drawImage(resume,0,0);   
}
clickX.length=0;
clickY.length=0;
clickDrag.length=0;
clickColor.length=0;
});
$('#interview').mousedown(function(e){
	ref = database.ref("feedback").push();
	ref.child('additional').set("Interview Invitation");
	ref.child('comment').set("Congratulation!");
	ref.child('companyName').set("whatever company");
});
$('#canvas').mousedown(function(e){
  var mouseX = e.pageX - this.offsetLeft;
  var mouseY = e.pageY - this.offsetTop;
		
  paint = true;
  addClick(e.pageX - this.offsetLeft, e.pageY - this.offsetTop);
  redraw();
});
$('#canvas').mousemove(function(e){
  if(paint){
    addClick(e.pageX - this.offsetLeft, e.pageY - this.offsetTop, true);
    redraw();
  }
});
$('#canvas').mouseup(function(e){
  paint = false;
});
$('#canvas').mouseleave(function(e){
  paint = false;
});
}
function addClick(x, y, dragging)
{
  clickX.push(x);
  clickY.push(y);
  clickDrag.push(dragging);
  clickColor.push(curColor);
}
function redraw(){
  context.lineJoin = "round";
  context.lineWidth = 2;
			
  for(var i=0; i < clickX.length; i++) {		
    context.beginPath();
    if(clickDrag[i] && i){
      context.moveTo(clickX[i-1], clickY[i-1]);
     }else{
       context.moveTo(clickX[i]-1, clickY[i]);
     }
     context.lineTo(clickX[i], clickY[i]);
     context.closePath();
     context.strokeStyle = clickColor[i];
     context.stroke();
  }
}
function clearCanvas()
{
	context.clearRect(0, 0, canvasWidth, canvasHeight);
}
