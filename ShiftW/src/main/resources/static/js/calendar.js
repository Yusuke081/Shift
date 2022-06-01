
//function sbh1(){
//  document.getElementById("test1").style.display = "none"  
//}
//初期表示は非表示
document.getElementById("sbh3").style.display ="none";

document.getElementById("sbh2").style.display ="none";

function clickBtn1(){
	const p1 = document.getElementById("sbh2");
	document.getElementById("sbh2").style.display = "none";
}

document.getElementById("sbh1").style.display ="block";

function clickBtn2(){
	//const p1 = document.getElementById("sbh1");
	document.getElementById("sbh1").style.display ="none";
	//document.getElementById("sbh3").style.display ="block";
}

function clickBtn3(){
	document.getElementById("sbh1").style.display ="block";
	document.getElementById("sbh2").style.display ="block";
}



