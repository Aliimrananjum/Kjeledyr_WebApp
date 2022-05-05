//Lagre en bruker i database.
function registrerBruker(){
//Validerer alle input før det lagres på server
    const brukernavnOK = validerBrukernavn($("#brukernavn").val())
    const passordOK = validerPassord($("#passord").val())
    const fornavnOK = validerFornavn($("#fornavn").val())
    const etternavnOK = validerEtternavn($("#etternavn").val())
    const epostOK = validerEpost($("#epost").val())
    const telefonOK = validerTelefon($("#telefon").val())
    const adresseOK = validerAdresse($("#adresse").val())
    const postnrOK = validerPostNr($("#postnummer").val())
    const brukerDropdownOK = validerBrukerDropdown($("#valgtKjønn").val())

    /* HVORFOR KAN JEG IKKE GJØRE DET SÅNN??
    if(validerBrukernavn($("#brukernavn").val()) && validerPassord($("#passord").val()) && validerFornavn($("#fornavn").val())
        && validerEtternavn($("#etternavn").val()) && validerEpost($("#epost").val()) && validerTelefon($("#telefon").val())
        && validerAdresse($("#adresse").val()) && validerPostNr($("#postnummer").val()) && validerBrukerDropdown($("#kjønn").val())){ //sjekker om alle innput feltene er validert før den lagrer bruker i database
 */


    if(
        brukernavnOK,passordOK,fornavnOK,etternavnOK,epostOK,telefonOK,adresseOK,postnrOK,brukerDropdownOK
    ){

        //lagrer alle verdiene fra inputfeltet inn i en variabel
        const enBruker = {
            brukernavn : $("#brukernavn").val(),
            passord : $("#passord").val(),
            fornavn : $("#fornavn").val(),
            etternavn : $("#etternavn").val(),
            epost : $("#epost").val(),
            mobilNr : $("#telefon").val(),
            gateAdresse : $("#adresse").val(),
            postnummer : $("#postnummer").val(),
            kjønn : $("#valgtKjønn").val(),
            admin : 0
        }

        //sender en objekt til serveren. Sender brukeren tilbake til hovedsiden
        $.post("/lagreBruker", enBruker, function(validertLagring){
            if(validertLagring){
                window.location.href = "login.html";
                $("#successIndex").html("Bruker opprettet. Logg inn")
                alert("Bruker opprettet. Logg inn")
            }
        })
            .fail(function (jqXHR){
                const json = $.parseJSON(jqXHR.responseText);
                $("#statusBruker").html(json.message);

            })
    }

}