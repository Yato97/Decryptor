const section = document.getElementsByClassName("luaIn")[0];
const but = document.getElementById("generate");
const pct = document.getElementById("pct");
const result = document.getElementById("result");
const resultClair = document.getElementById("resultClair");
const buttonControl = document.getElementsByClassName("buttonControl")[0];
const overlay = document.getElementById("overlay");
const controler = document.getElementById("controler");
const labelCrypt = document.getElementById("labelCrypt");

var i = 0;
var resultText = "0zfX23(-eIUsHZe-sEd89f2e3'HOf'dIAedfzeoizej";

//On met une animation pour le remplissage de la barre de chargement 
function maFct() {
    section.style.width="100%";
    section.style.transition="width 1s";
    setTimeout(maFct2, 1000);
}

//Reset de la barre sans transition
function maFct2() {  
    pct.innerHTML=parseInt(section.style.width)+"%";
    result.style.display="block";
    setTimeout(maFct3, 300);
}

function maFct3() {
    result.style.opacity="1"
    result.style.left="30rem"
    result.style.transition = "all";
    result.style.transitionDuration = "1s";
}

function affichePopUp() {
    overlay.style.display="block";
    overlay.style.opacity = "0.1";
    setTimeout(opacity, 100)
}

function opacity() { // Effectue l'animation de l'affichage opoup
    overlay.style.opacity = "1";
    //overlay.style.height = "25rem";
    //overlay.style.width = "50rem";
    overlay.style.transition = "all";
    overlay.style.transitionDuration = "1s";
}
//Evenement sur le bouton 
but.addEventListener("click", maFct, false);
buttonControl.addEventListener("click", affichePopUp, false);


