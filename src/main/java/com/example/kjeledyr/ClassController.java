//CONTROLLER
package com.example.kjeledyr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@RestController //VIKTIG Å BETEGNE AT DENNE CLASSEN ER EN CONTROLLER
public class ClassController {


    //Server validerings av bruker metode. Type boolean.
    private boolean validerBruker(Bruker b){
        String regexBrukernavn = "[A-ZÆØÅa-zæøå0-9]{4,20}";
        String regexPassord = "[A-ZÆØÅa-zæøå]{4,}[0-9]{1,}[.\\-!+]";
        String regexFornavnEtternavn = "[a-zA-ZæøåÆØÅ. \\-]{2,20}";
        String regexAdresse = "[a-zA-ZæøåÆØÅ0-9. \\-]{4,20}";
        String regexEpost =  "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";
        String regexTelefon = "[0-9]{8}";

        boolean brukernavnOK = b.getBrukernavn().matches(regexBrukernavn);
        boolean passordOK = b.getPassord().matches(regexPassord);
        boolean fornavnOK = b.getFornavn().matches(regexFornavnEtternavn);
        boolean etternavnOK = b.getEtternavn().matches(regexFornavnEtternavn);
        boolean adresseOK = b.getGateAdresse().matches(regexAdresse);
        boolean epostOK = b.getEpost().matches(regexEpost);
        boolean telefonOK = b.getMobilNr().matches(regexTelefon);

        if(brukernavnOK && passordOK && fornavnOK && etternavnOK && adresseOK && epostOK && telefonOK){
            return true;
        }
        else{
            logger.error("Feil i server verifisering av bruker");
            return false;
        }
    }

    private boolean validerEtDyr(Dyr d){
        String regexDyrNavn = "[a-zA-ZæøåÆØÅ. \\-]{2,20}";
        String regexAlder = "[0-9]{1,3}";
        String regexBeskrivelse = "[a-zA-ZæøåÆØÅ0-9. \\-]{2,200}";


        boolean dyrNavnOK = d.getNavn().matches(regexDyrNavn);
        boolean alderOK = d.getAlder().matches(regexAlder);
        boolean beskrivelseOK = d.getBeskrivelse().matches(regexBeskrivelse);

        if(dyrNavnOK && alderOK && beskrivelseOK){
            return true;
        }
        else{
            logger.error("Feil i server verfivisering av kjæledyr");
            return false;
        }
    }



    //Muligheten for å legge/hente feilmelding i/fra loggen.
    private Logger logger = LoggerFactory.getLogger(ClassController.class);

    @Autowired //Knytter kontakt med Repository classen
    private ClassRepository rep;


    @Autowired //knytter session til de som logger seg inn
    private HttpSession session;

    //BrukerDropdown meny
    @GetMapping("hentBrukerDropdown")
    public List<BrukerDropdown> hentBrukerDropdownC(HttpServletResponse response) throws IOException{ //Henter feilmelding fra reposiorty og sender videre til klienten.
        List<BrukerDropdown> brukerDropdown = rep.hentBrukerDropdownR(); //flytter data fra repository inn i en variabel
        if(brukerDropdown == null){ //Hvis variabelen ikke inneholder en liste, gir det en feilmelding
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Kan ikke hente innholdet, prøv igjen senere"); //lager feilmeldingen "56"
            return null; //returnerer ingenting til klienten
        }
        else{
            return brukerDropdown;
        }
    }

    //DyrDropdown meny
    @GetMapping("/hentDyrDropdown")
    public List<DyrDropdown> hentDyrDropdownC(HttpServletResponse response) throws IOException{
        List<DyrDropdown> dyrDropdown = rep.hentDyrDropdownR();
        if(dyrDropdown == null){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Kan ikke hente inneholdet, prøv igjen senere");
            return null;
        }
        else{
            return dyrDropdown;
        }
    }

    //Validerer postnr i registrering for bruker-skjema. Returnerer poststed tilbake til klienten.
    //VIKTIG: Vi skal ikke retunere en List tilbake. Kun et OBJEKT AV TYPE PostSted.
    @PostMapping("/sjekkPostNr")
    public PostSted sjekkPostDataC(PostSted p, HttpServletResponse response) throws IOException{
        PostSted postData = rep.sjekkPostDataR(p);
        if(postData == null){
            response.sendError(HttpStatus.NO_CONTENT.value(), "Server Error, prøv igjen senere");
            return null;
        }
        else{
            return postData;
        }
    }

    //Metoden henter inn variabel b av type STRING. IKKE Bruker.
    //Sender brukernavnet fra klienten. Validerer om brukernavnet eksisterer i Bruker tabellen. Returnerer true/false;
    @GetMapping("/sjekkBrukernavn")
    public boolean sjekkBrukernavnDataC(String b, HttpServletResponse response) throws IOException{
        String brukerData = rep.sjekkBrukernavnDataR(b);
        if(brukerData != "catch"){
            if(brukerData != null){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            response.sendError(HttpStatus.FORBIDDEN.value(),"Server Error. Prøv igjen senere");
            return false;
        }

    }

    //Skal validere objektet på server siden ved å kjøre objektet som kommer inn i en annen verifiserings-metode.
    @PostMapping("/lagreBruker")
    public boolean lagreBrukerC(Bruker b,HttpServletResponse response) throws IOException{
        if(!validerBruker(b)){
            response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), "Informasjonen du skrev ble ikke godkjent");
            return false;
        }
        else{
            if(!rep.lagreBrukerR(b)){
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i lagring av ny bruker, prøv igjen senere");
                return false;
            }
            return true;
        }

    }

    //lagre et kjæledyr
    @PostMapping("/lagreEtDyr")
    public boolean lagreEtDyrC(Dyr d,HttpServletResponse response) throws IOException{

        Bruker s = (Bruker) session.getAttribute("Innlogget");
        if(!validerEtDyr(d)){
            response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), "Informasjonen du skrev ble ikke godkjent");
            return false;
        }
        else{
            if(!rep.lagreEtDyrR(d,s)){
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i lagring av ny kjæledyr, prøv igjen senere");
                return false;
            }
            return true;
        }
    }

    //Lar bruker logge seg inn og legger til session med et objekt
    @PostMapping("/login")
    public boolean loginC(Bruker b, HttpServletResponse response) throws IOException{
        if(!rep.loginR(b)){
            response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), "Brukernavn eller passordet er feil");
            return false;
        }
        else{
            if(b.getBrukernavn().equals("Admin")){
                session.setAttribute("Innlogget", new Bruker(0,"Admin","NA","NA","NA","NA","NA","NA","NA","NA",1));
                return true;
            }
            else {
                session.setAttribute("Innlogget",b);
                return true;
            }
            //Bruker innloggetBruker = (Bruker) session.setAttribute("innlogget", b);
        }
    }

    //krypterer admin passordet.

//for å logge ut
    @GetMapping("/logout")
    public boolean logoutC(HttpServletResponse response) throws IOException{
        session.removeAttribute("Innlogget");
        if(session.getAttribute("Innlogget") == null){
            return true;
        }
        else {
            response.sendError(HttpStatus.I_AM_A_TEAPOT.value(), "Feil på server, kunne ikke logge ut");
            return false;
        }
    }




    //henter data fra Bruker og Dyr tabellen og legger det inn i en Liste array som sendes tilbake til klienten for å liste det opp
    @GetMapping("/hentDataAvDyr")
    public List<Liste> hentListeAvDyrC(HttpServletResponse response) throws IOException{
        List<Liste> dataFraDB = rep.hentListeAvDyrR();
        //hvorfor funker det hvis jeg tar == 0 og ikke isEmpty
        //spør hva som skjer her med if, blir return dataFraDB gjort uansett?
        if(dataFraDB == null){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i server, kunne ikke hente data. Prøv igjen senere!");
        }
        return dataFraDB;
    }


    //henter et dyr fra DB for å sende videre til klienten for endring.
    @GetMapping("/hentEtDyr")
    public Liste hentEtDyrC(int id, HttpServletResponse response) throws IOException{
        Liste utEtDyr = rep.hentEtDyrR(id);
        if(utEtDyr == null){
            response.sendError(HttpStatus.NO_CONTENT.value(), "Kunne ikke hente ut data, prøv igjen senere");
            return null;
        }
        return utEtDyr;
    }

    //lagre endring av et dyr fra siden endring av dyr.
    @PostMapping("/endreEtDyr")
    public boolean endreEtDyrC(Dyr d,HttpServletResponse response) throws IOException{
        if(!rep.endreEtDyrR(d)){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Kunne ikke endre i server. Prøv igjen senere");
            return false;
        }
        else{
            return true;
        }
    }

    //sjekker om brukeren har admin rettigheter
    @GetMapping("/validerBruker")
    public boolean validerBruker(){
        Bruker s = (Bruker) session.getAttribute("Innlogget");
        if(s.getAdmin() != 1){
            return false;
        }
        else{
            return true;
        }
    }

    //Sletter et dyr fra DB
    @GetMapping("/slettEtDyr")
    public boolean slettEtDyrC(int id, HttpServletResponse response) throws IOException{
        if(!rep.slettEtDyrR(id)){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i server. Kunne ikke slette");
            return false;
        }
        else{
            return true;
        }
    }

    @GetMapping("/slettAlt")
    public boolean slettAltC(HttpServletResponse response) throws IOException{
        if(!rep.slettAltR()){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Kunne ikke slette. Prøv igjen senere");
            return false;
        }
        else{
            return true;
        }
    }

}

