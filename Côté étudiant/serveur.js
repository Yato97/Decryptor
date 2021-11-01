let express = require("express")
const crypto = require("crypto")
const key = "12345678926457893563672635876353"
const iv = "1234567891123456";
const note = 20

let app = express()

app.set("view engine", "ejs")
app.use(express.static("public"))

// Parse URL-encoded bodies (as sent by HTML forms)
app.use(express.urlencoded());

// Parse JSON bodies (as sent by API clients)
app.use(express.json());

app.get("/", (req, res) => {
    res.render("pages/index")
    console.log("Connection [+]")
})

app.post("/", (req, res) => {
    let date_ob = new Date();
    let date = ("0" + date_ob.getDate()).slice(-2);
    // current month
    let month = ("0" + (date_ob.getMonth() + 1)).slice(-2);
    // current year
    let year = date_ob.getFullYear();
    // current hours
    let hours = date_ob.getHours();
    // current minutes
    let minutes = date_ob.getMinutes();
    // current seconds
    let seconds = date_ob.getSeconds();
    let currentDate = date + "/" + month + "/" + year;
    let currentHour = hours + ":" + minutes + ":" + seconds;
    
    //Récupération des valeur entré par l'etudiant dans le formulaire
    //Retire les espaces vides
    var data = ""
    var num = (req.body.num).replace(/ /g, ''); 
    var nom = (req.body.nom).replace(/ /g, '');
    var prenom = (req.body.prenom).replace(/ /g, '');
    function reverse(s) {
        return s.split("-").reverse().join("/");
    }
    var dateN = reverse(req.body.date); // On inverse la chaine contenant la date :> format jj/mm/aaaa
    
    console.log("Num : " + num);
    console.log("Nom : " + nom);
    console.log("Prenom : " + prenom);
    console.log(currentDate);

    //Chaine a crypté
    data = num+","+nom+","+prenom+ ","+dateN+","+note + ","+ currentDate + ","+ currentHour;
    console.log(data)
    console.log(req.body.lua)

    //On initialise le hash
    let hash = crypto.createHmac("sha256", key).update(data).digest("hex")
    console.log("Clé de hash : "+ hash)
   
    //Cryptage des données 
    let cipher = crypto.createCipheriv("aes-256-cbc", key, iv)
    let encrypted = cipher.update(data, "utf-8", "hex")
    encrypted += cipher.final("hex")
    console.log("clé crypté : "+ encrypted)
    
    //Test de decryptage
    let decipher = crypto.createDecipheriv("aes-256-cbc", key, iv)
    var decrypted = decipher.update(encrypted, "hex", "utf8")
    decrypted += decipher.final("utf-8")
    console.log("clé décrypté : "+ decrypted)
    let hash2 = crypto.createHmac("sha256", key).update(decrypted).digest("hex")
    
    //On verifie si la chaine crypté na pas été modifier
    if(hash === hash2) {
        console.log("Clé [+]")
    }
    else console.log("Clé [-]")
    // res.render(__dirname + "/views/pages/index", {name:data})
    const fs = require('fs');
    fs.unlinkSync('public/files/clé.txt') // Reset du fichier dans le cas ou une clé est déjà présente 
    fs.appendFile('public/files/clé.txt', encrypted, function (err) { // On ajoute la clé au fichier txt
        if (err) throw err;
            console.log('Fichier créé !');
        });
})
app.listen(8080)
