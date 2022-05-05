//Lager alle ready-funksjoner

$(function (){
    //Henter dropdown på registrering av bruker siden
    hentBrukerDropdown();
})

//Denne funksjonen skal hente frem dropdown meny for registreringsskjema for bruker.
// Henter data fra server, og putter dataen i en ny funksjonen for å formatere den som en dropdown meny.
function hentBrukerDropdown(){
    $.get("/hentBrukerDropdown", function (brukerDropdown){
        formaterBrukerDropdown(brukerDropdown);
    })
        //Feilhåndetering. Feilmelding fra server blir overfør til klienten. Feilmeldingen vises til brukeren.
        .fail(function (jqXHR){
            const json=$.parseJSON(jqXHR.responseText);
            $("#feilKjønn").html(json.message);
        });
}

//formaterer dropdown meny til registrering av bruker siden
function formaterBrukerDropdown(brukerDropdown){
    let ut = "<select id='valgtKjønn' onchange='validerBrukerDropdown(this.value)'>";
    ut += "<option disabled selected>Velg kjønn</option>";
    for(const alt of brukerDropdown){
        ut += "<option>" + alt.kjonn+ "</option>";
    }
    ut += "</select>"
    $("#kjønn").html(ut);


}