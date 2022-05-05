//side for å lagre et dyr i register

function lagreDyr(){
    const dyrNavnOK = validerDyrNavn($("#dNavn").val())
    const dyrAlderOK = validerAlder($("#alder").val())
    const beskrivelseOK = validerBeskrivelse($("#beskrivelse").val())
    const dyrOK = validerDyrDropdown($("#valgtDyr").val())
    const typeOK = validerTypeDropdown($("#valgtType").val())
    const dyrKjønnOK = validerKjønnDropdown($("#valgtDKjønn").val())

    if(dyrNavnOK && dyrAlderOK && beskrivelseOK && dyrOK && typeOK && dyrKjønnOK){

        const etDyr = {
            navn : $("#dNavn").val(),
            alder : $("#alder").val(),
            beskrivelse : $("#beskrivelse").val(),
            dyr : $("#valgtDyr").val(),
            type : $("#valgtType").val(),
            kjønn : $("#valgtDKjønn").val(),
        }

        $.post("/lagreEtDyr", etDyr, function(validertDyr){
            if(validertDyr){

                $("#statusDyr").html("Kjæledyr registrert!")
            }
        })
            .fail(function (jqXHR){
                const json = $.parseJSON(jqXHR.responseText);
                $("#statusDyr").html(json.message);

            })
    }
}

