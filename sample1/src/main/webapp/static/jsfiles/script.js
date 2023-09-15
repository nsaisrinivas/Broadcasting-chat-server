const mobilenumber=document.getElementById("mobilenumber");
const password=document.getElementById("password");
const form=document.getElementById("form");
const error=document.getElementById("error");
form.addEventListener('submit',(e)=>{
	let messages=[];
	if(mobilenumber.value==='' || mobilenumber.value==null){
		messages.push("Mobilenumber is required");
	}
	else if(password.value==='' || password.value==null){
		messages.push("password required");
	}
	if(messages.length>0){
		e.preventDefault();
		error.innerText=messages.join(',');
	}
	
})