
//validerer input for postnr. Sjekker om den inneholder 4 tall.
function validerPostNr(postNr){
    const regxp = /^[0-9]{4}$/;
    const OK = regxp.test(postNr);
    if(!OK){
        $("#feilPostNr").html("Må Inneholde 4 tall")
        return false;
    }
    //hvis den inneholder 4 tall. Sjekker om den eksisterer i PostSted tabell.
    else{
        const postData = {
            postnummer : $("#postnummer").val(),
            poststed : ""
        }
        const url = "/sjekkPostNr"
        $.post(url,postData, function (postDataValidert){
            if(postDataValidert == null){
                $("#feilPostNr").html("Postnummeret eksisterer ikke. Velg følgende postnr: 1277 (Oslo), 5883 (Bergen), 9001(Tromsø)");
                $("#poststed").val("")
                return false;
            }
            else{
                $("#feilPostNr").html("");
                $("#poststed").val(postDataValidert.poststed) //Legger poststed automatisk inn i input basert på postnr fra database
                return true;
            }
        })
            .fail(function (jqXHR){//HAR PROBLEMER HER. HAR TO FEILMELDINGER
                const json = $.parseJSON(jqXHR.responseText);
                $("#feilPostNr").html(json.message);
            })

    }
}

//validerer brukernavn og sjekker om den allerede eksisterer i Bruker tabellen

function validerBrukernavn(brukernavn){
    const regxp = /^[A-ZÆØÅa-zæøå0-9]{4,20}$/; //bare inneholde bokster og tall.
    const OK = regxp.test(brukernavn);
    if(!OK){
        $("#feilBrukernavn").html("Brukernavn kan bare bestå av bokstaver og tall. 4 til 20 tegn");
        return false;
    }
    else{
        const url = "/sjekkBrukernavn?b=" +$("#brukernavn").val();
        $.get(url, function(validertBrukernavn){
            if(validertBrukernavn){
                $("#feilBrukernavn").html("");
                return true;
            }
            else{
                $("#feilBrukernavn").html("Brukernavnet eksisterer fra før. Velg et annet brukernavn");
                return false;
            }
        })
            .fail(function (jqHQR){
                const json = $.parseJSON(jqHQR.responseText);
                $("#feilBrukernavn").html(json.message);
            })
    }
}

//Validerer passordet.
function validerPassord(passord){
    const regxp = /^[A-ZÆØÅa-zæøå]{4,}[0-9]{1,}[.\-!+]$/; //Må inneholde minst 4 bokstaver, minst 1 tall og et tegn
    const OK = regxp.test(passord)
    if(!OK){
        $("#feilPassord").html("Passord må inneholde minst 4 bokster, minst 1 tall og et tegn (. - ! +)")
        return false;
    }
    else{
        $("#feilPassord").html("")
        return true;
    }
}

//Validerer fornavn
function validerFornavn(fornavn){
    const regexp = /^[a-zA-ZæøåÆØÅ. \-]{2,20}$/
    const OK = regexp.test(fornavn);
    if(!OK){
        $("#feilFornavn").html("Fornavnet må bestå av 2 til 20 bokstaver");
        return false;
    }
    else {
        $("#feilFornavn").html("");
        return true;
    }
}

//Validerer etternavn
function validerEtternavn(etternavn){
    const regexp = /^[a-zA-ZæøåÆØÅ. \-]{2,20}$/
    const OK = regexp.test(etternavn);
    if(!OK){
        $("#feilEtternavn").html("Etternavnet må bestå av 2 til 20 bokstaver");
        return false;
    }
    else {
        $("#feilEtternavn").html("");
        return true;
    }
}

//Validerer epost
function validerEpost(epost){
    const regexp = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/
    const OK = regexp.test(epost);
    if(!OK){
        $("#feilEpost").html("Skriv inn en gyldig e-post");
        return false;
    }
    else {
        $("#feilEpost").html("");
        return true;
    }
}

//Valider telefon
function validerTelefon(telefonnr){
    const regexp = /^[0-9]{8}$/
    const OK = regexp.test(telefonnr);
    if(!OK){
        $("#feilTelefon").html("Skriv inn et gyldig telefonnr");
        return false;
    }
    else {
        $("#feilTelefon").html("");
        return true;
    }
}

//Valider adresse
function validerAdresse(adresse){
    const regexp = /^[a-zA-ZæøåÆØÅ0-9. \-]{4,20}$/
    const OK = regexp.test(adresse);
    if(!OK){
        $("#feilAdresse").html("Adressen må bestå av 4 til 20 bokstaver")
        return false;
    }
    else {
        $("#feilAdresse").html("");
        return true;
    }
}

//Valider BrukerDropdown

function validerBrukerDropdown(dropdown){
    if(dropdown === "Velg kjønn"){
        $("#feilKjønn").html("Velg kjønn")
        return false;
    }
    else {
        $("#feilKjønn").html("");
        return true;
    }
}

//Validerer lagre av dyr

function validerDyrNavn(dyrNavn){
    const regexp = /^[a-zA-ZæøåÆØÅ. \-]{2,20}$/
    const OK = regexp.test(dyrNavn);
    if(!OK){
        $("#feildNavn").html("Navnet må bestå av 2 til 20 bokstaver");
        return false;
    }
    else {
        $("#feildNavn").html("");
        return true;
    }
}

function validerAlder(alder){
    const regexp = /^[0-9]{1,3}$/
    const OK = regexp.test(alder);
    if(!OK){
        $("#feilAlder").html("Skriv inn alderen");
        return false;
    }
    else {
        $("#feilAlder").html("");
        return true;
    }
}

function validerBeskrivelse(beskrivelse){
    const regexp = /^[a-zA-ZæøåÆØÅ0-9. \-]{2,200}$/
    const OK = regexp.test(beskrivelse);
    if(!OK){
        $("#feilAlder").html("Skriv inn alderen");
        return false;
    }
    else {
        $("#feilAlder").html("");
        return true;
    }
}

function validerDyrDropdown(dropdown){
    if(dropdown === "Velg Dyr"){
        $("#feilDyr").html("Velg kjønn")
        return false;
    }
    else {
        $("#feilDyr").html("");
        return true;
    }
}

function validerTypeDropdown(dropdown){
    if(dropdown == null){
        $("#feilType").html("Velg Type")
        return false;
    }
    else {
        $("#feilType").html("");
        return true;
    }
}

function validerKjønnDropdown(dropdown){
    if(dropdown == null){
        $("#feilType").html("Velg Type")
        return false;
    }
    else {
        $("#feilType").html("");
        return true;
    }
}

//validering av brukernavn og passord for loginsiden

function validerLoginBrukernavn(loginBrukernavn){
    const regxp = /^[A-ZÆØÅa-zæøå0-9]{4,20}$/; //bare inneholde bokster og tall.
    const OK = regxp.test(loginBrukernavn);
    if(!OK){
        $("#feilBrukernavn").html("Brukernavn kan bare bestå av bokstaver og tall. 4 til 20 tegn");
        return false;
    }
    else{
        return true;
    }
}

//Validerer passordet.
function validerLoginPassord(loginPassord){
    const regxp = /^[A-ZÆØÅa-zæøå]{4,}[0-9]{1,}[.\-!+]$/; //Må inneholde minst 4 bokstaver, minst 1 tall og et tegn
    const OK = regxp.test(loginPassord)
    if(!OK){
        $("#feilLoginPassord").html("Passord må inneholde minst 4 bokster, minst 1 tall og et tegn (. - ! +)")
        return false;
    }
    else{
        $("#feilLoginPassord").html("")
        return true;
    }
}