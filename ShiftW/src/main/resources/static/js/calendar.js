

//初期表示は非表示




//function clickBtn1(clicked_id){
	
	//var nam = clicked_id.substr(str.indexOf('_'));
	//alert(clicked_id);
	//var sbh2 = 'sbh2_' + clicked_id;
	//alert(sbh2);
	//element.setAttribute(id,sbh2);
	//var sbh2 = 'sbh2_' + 1;
	document.getElementByid("sbh2_1").style.display = "none";
	//document.getElementById('sbh3_' + clicked_id).style.display ="block";
	//document.getElementById('time1_' + clicked_id).style.display ="none";
	//document.getElementById('time2_' + clicked_id).style.display ="none";
	
	}


//function clickBtn2(clicked_id){
	//var nam = clicked_id.substr(str.indexOf('_'));
	//alert(nam);
	document.getElementById("sbh1_1").style.display ="none";
	document.getElementById("sbh2_1").style.display ="none";
	document.getElementById("sbh3_1").style.display ="block";
	document.getElementById("time1_1").style.display ="block";
	document.getElementById("time2_1").style.display ="block";
	
	}
		
	

//function clickBtn3(){
	document.getElementById("sbh1_1").style.display ="block";
	document.getElementById("sbh2_1").style.display ="block";
	document.getElementById("sbh3_1").style.display ="none";
	document.getElementById("time1_1").style.display ="none";
	document.getElementById("time2_1").style.display ="none";
}

