const section = document.getElementsByClassName("luaIn")[0];
const but = document.getElementById("generate");
const pct = document.getElementById("pct");
const result = document.getElementById("result");
const resultClair = document.getElementById("resultClair");
const buttonControl = document.getElementsByClassName("buttonControl")[0];
const overlay = document.getElementById("overlay");
const controler = document.getElementById("controler");
const labelCrypt = document.getElementById("labelCrypt");
const textInput = document.getElementsByClassName("textArea");
const date = document.getElementById("date");

var i = 0;

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
    setTimeout(temp, 100);
}

function maFct3() {
    var i = 0;
    // On vérifie la présence de champs non remplis
    for (let index = 0; index < textInput.length; index++) {
        const element = textInput[index];
        if (element.value == "") {
            i += 1;
            element.style.border = "1px solid tomato";
        }  
        
    }
    if (i == 0) { // Reset des bordures
        for (let i = 0; i < textInput.length; i++) {
            textInput[i].style.border = "1px solid #CCC";
        }  
        maFct();  
    }
}

function temp() { // Animation du bouton Download
    result.style.opacity="1"
    result.style.marginTop="1rem"
    result.style.transition = "all";
    result.style.transitionDuration = "1s";
}

function affichePopUp() { //  Affiche le popup
    overlay.style.display="block";
    overlay.style.opacity = "0.1";
    setTimeout(opacity, 100)
}

function opacity() { // Effectue l'animation de l'affichage popup
    overlay.style.opacity = "1";
    overlay.style.transition = "all";
    overlay.style.transitionDuration = "1s";
}
//Evenement sur le bouton 
but.addEventListener("click", maFct3, false);
buttonControl.addEventListener("click", affichePopUp, false);


