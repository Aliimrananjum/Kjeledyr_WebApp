//kodingen for siden RegistrerDyr (DROP DOWN MENY)

$(function (){
    hentDyrDropdown()
})

function hentDyrDropdown(){
    $.get("/hentDyrDropdown", function (dyrDropdown){
        formaterDyrDropdown(dyrDropdown);
    })
        .fail(function (jqXHR){
            const json = $.parseJSON(jqXHR.responseText);
            $("#feilDyr").html(json.message);
        });
}


//Drop down med som er avhengig av forrige valgt.
function formaterDyrDropdown(dyrDropdown){
    let ut = "<select id='valgtDyr' onchange='dyrTypeDropdown()' onchange='validerDyrDropdown(this.value)' >";
    let forrige = "";
    ut += "<option disabled selected>Velg Dyr</option>";
    for(const meny of dyrDropdown){
        if(meny.dyr !==forrige){
            ut += "<option>" + meny.dyr +"</option>";
        }
        forrige = meny.dyr;
    }
    ut +="</select>"
    $("#dyr").html(ut);
}

//bygger videre på dropdown
function dyrTypeDropdown(){
    const valgtDyr = $("#valgtDyr").val();
    $.get("/hentDyrDropdown",function (dyrTypeDropdown){
        formaterDyrTypeDropdown(dyrTypeDropdown,valgtDyr);
    })
        .fail(function (jqXHR){
            const json = $.parseJSON(jqXHR.responseText);
            $("#feilType").html(json.message);
        });
}

function formaterDyrTypeDropdown(dyrTypeDropdown,valgtDyr){
    let ut = "<select id='valgtType' onchange='dyrKjønnDropdown()' onchange='dyrTypeDropdown(this.value)'>"
    let forrige = ""
    for(const meny of dyrTypeDropdown){
        if(meny.dyr === valgtDyr){
            if(meny.type !==forrige){
                ut+="<option>" + meny.type + "</option>"
            }
        }
        forrige = meny.type;
    }
    ut += "</select>"
    $("#type").html(ut);
}

function dyrKjønnDropdown(){
    const valgtType = $("#valgtType").val();
    $.get("/hentDyrDropdown",function (dyrKjønnDropdown){
        formaterDyrKjønnDropdown(dyrKjønnDropdown, valgtType)
    })
        .fail(function (jqXHR){
            const json = $.parseJSON(jqXHR.responseText);
            $("#feilKjønn").html(json.message);
        });
}

function formaterDyrKjønnDropdown(dyrKjønnDropdown, valgtType){
    let ut = "<select id='valgtDKjønn' onchange='validerKjønnDropdown(this.value)'>"
    let forrige = ""
    for(const meny of dyrKjønnDropdown){
        if(meny.type === valgtType){
            if(meny.kjønn !==forrige){
                ut+="<option>" + meny.kjønn + "</option>"
            }
        }
        forrige = meny.kjønn;
    }
    ut += "</select>"
    $("#dyrkjønn").html(ut);
}
