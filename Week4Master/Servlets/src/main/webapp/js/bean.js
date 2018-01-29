window.onload=function(){
	var xhttp= new XMLHttpRequest();
	xhttp.onreadystatechange=function(){
		if(xhttp.readyState===4 && xhttp.status===200){
			let bean = JSON.parse(xhttp.responseText);
			console.log(bean);
		}
	};
	xhttp.open("GET", "bean");
	xhttp.send();
};