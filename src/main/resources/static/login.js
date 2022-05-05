//validerer brukernavn og passord for loginsiden. S
function validerLogin(){

    const loginBrukernavnOK = validerLoginBrukernavn($("#loginBrukernavn").val());
    const loginPassordOK = validerLoginPassord($("#loginPassord").val());

    if(loginBrukernavnOK && loginPassordOK){

        const login = {
            brukernavn : $("#loginBrukernavn").val(),
            passord : $("#loginPassord").val()
        }
        //sjekker om det er admin som logger seg inn. Krypterer passordet og da logger inn.
            $.post("/login", login, function(validertLogin){
                if(validertLogin){
                    alert("Login Success! " + "Velkommen " + $("#loginBrukernavn").val())
                    window.location.href="index.html";
                }
            })
                .fail(function (jqXHR){
                    const json = $.parseJSON(jqXHR.responseText);
                    $("#failLogin").html(json.message);

                })
    }
}
