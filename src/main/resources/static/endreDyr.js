//nettside for endre et dyr. Henting av et dyr. Lagre endringen
$(function (){
    hentDyrDropdown()
    hentEtDyr()
})

function hentEtDyr(){
    const id = window.location.search.substring(1);
    const url = "/hentEtDyr?id=" + id;

    $.get(url, function (etDyr){
        $("#dyrID").val(etDyr.dyrID)
        $("#brukernavn").val(etDyr.brukernavn)
        $("#dNavn").val(etDyr.navn)
        $("#alder").val(etDyr.alder)
        $("#beskrivelse").val(etDyr.beskrivelse)
        $("#valgtDyr").val(etDyr.dyr)
        $("#valgtType").val(etDyr.type)
        $("#ValgtDKjønn").val(etDyr.type)
    })
}

function endreEtDyr(){

    const dyrNavnOK = validerDyrNavn($("#dNavn").val())
    const dyrAlderOK = validerAlder($("#alder").val())
    const beskrivelseOK = validerBeskrivelse($("#beskrivelse").val())
    const dyrOK = validerDyrDropdown($("#valgtDyr").val())
    const typeOK = validerTypeDropdown($("#valgtType").val())
    const dyrKjønnOK = validerKjønnDropdown($("#valgtDKjønn").val())

    if(dyrNavnOK && dyrAlderOK && beskrivelseOK && dyrOK && typeOK && dyrKjønnOK){

        const etDyr = {
            dyrID : $("#dyrID").val(),
            navn : $("#dNavn").val(),
            alder : $("#alder").val(),
            beskrivelse : $("#beskrivelse").val(),
            dyr : $("#valgtDyr").val(),
            type : $("#valgtType").val(),
            kjønn : $("#valgtDKjønn").val(),
        }

        $.post("/endreEtDyr", etDyr, function(validertDyr){
            if(validertDyr){

                $("#statusDyr").html("Kjæledyr Endret!")
            }
        })
            .fail(function (jqXHR){
                const json = $.parseJSON(jqXHR.responseText);
                $("#statusDyr").html(json.message);

            })
    }
}
