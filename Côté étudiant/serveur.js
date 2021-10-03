let express = require("express")
const crypto = require("crypto")
const key = "12345678926457893563672635876353"
const note = 20
const iv = crypto.randomBytes(16);

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
    //Récupération des valeur entré par l'etudiant dans le formulaire
    var data = ""
    var num = req.body.num; 
    var nom = req.body.nom;
    var prenom = req.body.prenom;
    console.log("Num : " + num);
    console.log("Nom : " + nom);
    console.log("Prenom : " + prenom);

    //Chaine a crypté
    data = num+" - "+nom+" - "+prenom+ " - "+note
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
    fs.appendFile('public/files/clé.txt', encrypted, function (err) {
        if (err) throw err;
            console.log('Fichier créé !');
        });
})
app.listen(8080)
