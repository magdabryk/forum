function validateLoginForm(){
var login = document.getElementById("login");
var password = document.getElementById("password");
var regex = /^[a-zA-Z0-9]{5,}$/;
var result = true;
    if(!regex.test(login.value)){
    login.style.background = "#ff0000";
    result = false;
    }else{
    login.style.background = "#ffffff";
    }
    if(!regex.test(password.value)){
    password.style.background = "#ff0000";
    result = false;
    }else{
    password.style.background = "#ffffff";
    }
    return result;
    }

function validateRegisterForm() {
var name = document.getElementById("name");
var surname = document.getElementById("surname");
var login = document.getElementById("login");
var password = document.getElementById("password");
var password2 = document.getElementById("password2");
var info = document.getElementById("info")

var nameRegex = /^[A-Z]{1}[a-ząćęłńóśźż]+$/;
var surnameRegex = /^[A-Z]{1}[a-za-ząćęłńóśźż]+(-[A-Z]{1}[a-za-ząćęłńóśźż]+)?$/;
var loginAndPasswordRegex = /^[a-zA-Z0-9]{5,}$/;
var result = true;
var infoText = "";

    if(!nameRegex.test(name.value)){
    name.style.background = "#ff0000";
    result = false;
    infoText = infoText + "Nieprawidłowe imię <br>" ;
    }else{
    name.style.background = "#ffffff";
    }
    if(!surnameRegex.test(name.value)){
    surname.style.background = "#ff0000";
    result = false;
    infoText =infoText + "Nieprawidłowe nazwisko <br>" ;
    }else{
    surname.style.background = "#ffffff";
    }
    if(!loginAndPasswordRegex.test(login.value)){
    login.style.background = "#ff0000";
    result = false;
    infoText =infoText + "Nieprawidłowe login <br>" ;
    }else{
    login.style.background = "#ffffff";
    }
    if(!loginAndPasswordRegex.test(password.value)){
    password.style.background = "#ff0000";
    result = false;
    infoText =infoText + "Nieprawidłowe hasło <br>" ;
    }else{
    password.style.background = "#ffffff";
    }

    if(password.value != password2.value){
    password2.style.background = "#ff0000";
    result = false;
    infoText =infoText + " Hasła nie są jednakowe <br>" ;
    }else{
    password2.style.background = "#ffffff";
    }
    info.innerHTML = infoText;
    return result;
}


