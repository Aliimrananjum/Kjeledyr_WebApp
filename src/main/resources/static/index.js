$(function (){
    //hentAlleDyr();
    hentDataAvDyr()
})
/*
//henter ut liste av dyr fra server
function hentAlleDyr(){
    $.get("/hentAlleDyr", function (listeAvDyr){
        formaterListeAvDyr(listeAvDyr)
    })
}

function formaterListeAvDyr(listeAvDyr){
    let ut = "<table class='table table-striped'><tr><th>Brukernavn</th><th>Navn</th><th>Alder</th><th>Beskrivelse</th>" +
        "<th>Dyr</th><th>Type</th><th>Kjønn</th></tr>";
    for(const data of listeAvDyr){
        ut += "<tr><td>" + data.brukernavn + "</td><td>" + data.navn + "</td><td>" + data.alder + "</td><td>" + data.beskrivelse + "</td>" +
            "<td>" + data.dyr + "</td><td>" + data.type + "</td><td>" + data.kjønn + "</td></tr>";
    }
    hentDataAvDyr()
    ut += "</table>"
    console.log(ut)
    $("#listeAvDyr").html(ut);
}

 */


//Henter informasjon fra to tabeller og legger dem inn i Liste Objetet for å liste det opp på frontend
function hentDataAvDyr(){
    $.get("/hentDataAvDyr", function(dataAvDyr){
        formaterDataAvDyr(dataAvDyr)
    })
        .fail(function (jqXHR){
            const json = $.parseJSON(jqXHR.responseText);
            $("#statusIndex").html(json.message);
        })
}

function formaterDataAvDyr(dataAvDyr){
    let ut = "<table class='table table-striped'><tr><th>Brukernavn</th><th>Navn</th><th>Alder</th><th>Beskrivelse</th>" +
        "<th>Dyr</th><th>Type</th><th>Kjønn</th><th></th><th></th></tr>";
        if(dataAvDyr !== null){
            for(const data of dataAvDyr){
                ut += "<tr><td>" + data.brukernavn + "</td><td>" + data.navn + "</td><td>" + data.alder + "</td><td>" + data.beskrivelse + "</td>" +
                    "<td>" + data.dyr + "</td><td>" + data.type + "</td><td>" + data.kjønn + "</td>" +
                    "<td><button class='btn btn-primary' onclick='validerBrukerEndre("+ data.dyrID +")'>Endre</button></td>" +
                    "<td><button class='btn btn-danger' onclick='validerBrukerSlett("+data.dyrID +")'>Slett </button></td></tr>";
            }
        }
        ut += "</table>"
    $("#listeAvDyr").html(ut);
}

//til endringsiden
function idTilEndring(id){
    window.location.href ="endreDyr.html?" +id;
}

//logger ut brukeren
function logout(){
    $.get("/logout", function (validerLogout){
        if(validerLogout){
            window.location.href ="login.html"
        }
    })
        .fail(function (jqXHR){
            const json = $.parseJSON(jqXHR.responseText);
            $("#statusIndex").html(json.message);
        })

}

//validerer bruker om de kan redigere eller slette et dyr
function validerBrukerEndre(id){
    $.get("/validerBruker", function (validertBruker){
        if(validertBruker){
            idTilEndring(id)
        }
        else{
            $("#statusIndex").html("Du har ikke rettigheter for å gjøre endringer");
        }
    })
        .fail(function (jqXHR){
            const json = $.parseJSON(jqXHR.responseText);
            $("#statusIndex").html(json.message);
        })
}

function validerBrukerSlett(id){
    $.get("/validerBruker", function (validertBruker){
        if(validertBruker){
            const url = "/slettEtDyr?id=" + id;
            $.get(url,function (validerEtSlett){
                if(validerEtSlett){
                    hentDataAvDyr()
                }
                else{
                    $("#statusIndex").html("noe galt her");

                }
            })
                .fail(function (jqXHR){
                    const json = $.parseJSON(jqXHR.responseText);
                    $("#statusIndex").html(json.message);
                })
        }

    })
        .fail(function (jqXHR){
            const json = $.parseJSON(jqXHR.responseText);
            $("#statusIndex").html(json.message);
        })
}