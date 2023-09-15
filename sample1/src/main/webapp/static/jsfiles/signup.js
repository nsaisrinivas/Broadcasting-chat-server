
const name=document.getElementById("name");
const password=document.getElementById("password");
const confpassword=document.getElementById("confirmpassword");
const mobilenumber=document.getElementById("mobilenumber");
const error=document.getElementById("error");
const form=document.getElementById("form");

form.addEventListener('submit',(e)=>{
	console.log(mobilenumber.value);
	let messages="";
	if(name.value==='' || name.value==null){
		messages='name is required';
	}
	else if(password.value==''|| password.value==null){
		messages="password is required";
	}
	else if((password.value).length<8 || (password.value).length>15){
		messages="password length should be between 8 and 15 ";
	}
	else if(!((password.value).includes("*")||(password.value).includes("@")||(password.value).includes("#")||(password.value).includes("$"))){
		messages="password must containe 1 special character";
	}
	else if(password.value!=confpassword.value){
		messages="password and confirm password doesnot match";
	}
	else if(mobilenumber.value=='' || mobilenumber.value==null){
		messages="mobilenumber is required";
	}
	else if((mobilenumber.value).length!=10){
		messages="mobilenumber must be of length 10"
	}
	else if(parseInt((mobilenumber.value)[0])<6){
		messages="invalid mobilenumber";
	}
	if(messages.length>0){
		e.preventDefault();
		error.innerText=messages;
	}
})