package com.example.kjeledyr;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository //VIKTIG Å BETEGNE AT DETTE ER EN REPOSITORY CLASSE
public class ClassRepository {

    @PostConstruct
    public void init(){
        String sql = "UPDATE Bruker SET passord = ? WHERE brukernavn = ?";

        try{
                db.update(sql,krypterPassord("Admin1!"),"Admin");
        }
        catch(Exception e){
            logger.error("Feil i krypterAdminPassordR " + e);
        }
    }

    @Autowired
    private JdbcTemplate db; //Knytter kontakt med databasen

    private Logger logger = LoggerFactory.getLogger(ClassRepository.class); //Gir muligheten til å manipulere feilloggen.


    //Importerer classen for å kryptere passord
    BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder(15);

    //Lager en metode for å kryptere passordet.
    private String krypterPassord(String passord){
        return bCrypt.encode(passord);
    }

    //Metode for å verifisere passordet fra login-siden. Metoden tar inn to String fra loginR() metoden.
    private boolean validerLoginPassord(String loginPassord, String DBpassord){
        if(bCrypt.matches(loginPassord,DBpassord)){
            return true;
        }
        else{
            return false;
        }
    }


    //henter data fra databasen. Innholdet fra databasen blir alternativenene i dropdown meny til registrering av bruker siden
    public List<BrukerDropdown> hentBrukerDropdownR(){
        String sql ="SELECT * FROM BrukerDropdown";

        //feilhåndering
        try{
            List<BrukerDropdown> brukerDropdown = db.query(sql, new BeanPropertyRowMapper(BrukerDropdown.class)); //henter data fra database. Data formateres til array og assignet til en variabel.
            return brukerDropdown;
        }
        catch (Exception e){
            logger.error("Feil i hentBrukerDropdownR" + e); //legger til en egendefinert feilmelding i error-loggen.
            return null;
        }
    }

    public List<DyrDropdown> hentDyrDropdownR(){
        String sql = "SELECT * FROM DyrDropdown";

        try{
            List<DyrDropdown> dyrDropdown = db.query(sql,new BeanPropertyRowMapper(DyrDropdown.class));
            return dyrDropdown;
        }
        catch (Exception e){
            logger.error("Feil i hentDyrDropdown " + e);
            return null;
        }
    }

    //Sjekker om postnummeret eksisterer i databasen og leverer poststed til controller. Returnerer en OBJEKT.
    public PostSted sjekkPostDataR(PostSted p){
        String sql ="SELECT * FROM PostSted WHERE postnummer = ?";
        try{
            //hva gjør dette?
            //List<PostSted> postData = db.queryForList(sql,PostSted.class,p.getPostnummer());
            List<PostSted> postData = db.query(sql,new BeanPropertyRowMapper<>(PostSted.class),p.getPostnummer()); //Formaterer data fra database til et array. Søker på postnummer fra klienten.
            if(!postData.isEmpty()){
                return postData.get(0); //returenrer første objektet i arrayet.
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            logger.error("Feil i SjekkPostalDataR" + e);
            return null;
        }

    }

    //Sjekker om brukernavn fra klient er i databasen. Returnerer String. Den returnerer 'catch' hvis det er server feil.
    public String sjekkBrukernavnDataR(String b){
        String sql = "SELECT * FROM Bruker WHERE brukernavn = ?";
        try{
            List<Bruker> brukerData = db.query(sql, new BeanPropertyRowMapper(Bruker.class),b);
            if(brukerData.isEmpty()){
                return null;
            }
            else{
                return b;
            }
        }
        catch(Exception e){
            logger.error("Feil i sjekkBrukernavnDataR " + e);
            return "catch";
        }
    }

    //Legger bruker inn i databaseen tabellen Bruker
    public boolean lagreBrukerR(Bruker b){
        String sql = "INSERT INTO Bruker(brukernavn,passord,fornavn,etternavn,epost,mobilNr,gateAdresse,postnummer,kjønn,admin)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?)";
        try{//LEGGER TIL ET KRYPTERT PASSORD
            db.update(sql,b.getBrukernavn(),krypterPassord(b.getPassord()),b.getFornavn(),b.getEtternavn(),b.getEpost(),b.getMobilNr(),b.getGateAdresse(),b.getPostnummer(),b.getKjønn(),b.getAdmin());
            return true;
        }
        catch (Exception e){
            logger.error("Feil i lagreBrukerR "+e );
            return false;
        }
    }

    //legger til et dyr i databasen tabellen Dyr
    @Transactional
    public boolean lagreEtDyrR(Dyr d,Bruker s){
        String sql1 = "SELECT * FROM BRUKER WHERE brukernavn = ?";
        String sql2 = "INSERT INTO Dyr(brukerID,navn,alder,beskrivelse,dyr,type,kjønn)" +
                "VALUES (?,?,?,?,?,?,?)";
        try{ //hvorfor ber den om String
            Bruker utBruker = db.queryForObject(sql1, new BeanPropertyRowMapper<>(Bruker.class),s.getBrukernavn());
            db.update(sql2,String.valueOf(utBruker.getBrukerID()),d.getNavn(),d.getAlder(),d.getBeskrivelse(),d.getDyr(),d.getType(),d.getKjønn());
            return true;
        }
        catch (Exception e){
            logger.error("Feil i lagreDyr " + e);
            return false;
        }
    }


    //metode for å logge inn bruker ved å sjekke brukernavn og passord med DB.
    @Transactional
    public boolean loginR(Bruker b){
        String sql1 = "SELECT * FROM Bruker WHERE brukernavn = ?";
        try{
            Bruker dbBruker = db.queryForObject(sql1,new BeanPropertyRowMapper<>(Bruker.class),b.getBrukernavn());
            return validerLoginPassord(b.getPassord(), dbBruker.getPassord());
        }
        catch(Exception e){
            logger.error("Feil i loginR " + e);
            return false;
        }
    }
/*
    //krypterer Admin passordet.
    public boolean krypterAdminPassordR(Bruker admin){
        String sql = "SELECT * FROM Bruker WHERE brukernavn = ?";
        String sql2 = "UPDATE Bruker SET passord = ? WHERE brukernavn = ?";

        try{
            Bruker utAdmin = db.queryForObject(sql, new BeanPropertyRowMapper<>(Bruker.class),admin.getBrukernavn());
            if(admin.getPassord().equals(utAdmin.getPassord())){
                db.update(sql2,krypterPassord(utAdmin.getPassord()),admin.getBrukernavn());
                return true;
            }
            else {
                logger.error("Feil i krypterAdminPassordR. Passordet stemte ikke");
                return false;
            }
        }
        catch(Exception e){
            logger.error("Feil i krypterAdminPassordR " + e);
            return false;
        }
    }


 */

    //HENTER LISTE AV DYR som er registrert av brukere
    public List<Liste> hentAlleDyrR(){
        String sql = "SELECT * FROM Liste";

        try{
            List<Liste> listeFraDB = db.query(sql, new BeanPropertyRowMapper(Liste.class));
            return listeFraDB;
        }
        catch (Exception e){
            logger.error("Feil i hentAlleDyr " + e);
            return null;
        }
    }
    //henter data fra to tabeller Bruker og Dyr. Plasserer data i en objent av type Liste. Returnerer listen som en array.
    public List<Liste> hentListeAvDyrR(){
        String sql1 = "SELECT Dyr.dyrID, Bruker.brukernavn,Dyr.navn,Dyr.alder,Dyr.beskrivelse,Dyr.dyr,Dyr.type,Dyr.kjønn " +
                    "FROM Bruker, DYR " +
                    "WHERE Bruker.BrukerID = Dyr.BrukerID";

        try{
            List<Liste> dataFraDB = db.query(sql1, new BeanPropertyRowMapper(Liste.class));

            return dataFraDB;
        }
        catch(Exception e){
            logger.error("Feil i hentDataFraListeR " + e );
            return null;
        }
    }

    //Henter ut EN SPESEFIKK dyr slik at brukeren kan endre det på fronted.
    public Liste hentEtDyrR(int id){
        String sql =  "SELECT Dyr.dyrID, Bruker.brukernavn,Dyr.navn,Dyr.alder,Dyr.beskrivelse,Dyr.dyr,Dyr.type,Dyr.kjønn " +
                "FROM Bruker, DYR " +
                "WHERE Bruker.BrukerID = Dyr.BrukerID " +
                "AND Dyr.dyrID = ?";
        try{
            Liste etDyrFraDB = db.queryForObject(sql, new BeanPropertyRowMapper<>(Liste.class),id);
            return etDyrFraDB;
        }
        catch (Exception e){
            logger.error("Feil i hentEtDyr" + e);
            return null;
        }
    }

    public boolean endreEtDyrR(Dyr d){
        String sql = "UPDATE Dyr " +
                "SET navn=?,alder=?,beskrivelse=?,dyr=?,type=?,kjønn=? " +
                "WHERE dyrID=?";
        try{
            db.update(sql,d.getNavn(),d.getAlder(),d.getBeskrivelse(),d.getDyr(),d.getType(),d.getKjønn(),d.getDyrID());
            return true;
        }
        catch(Exception e){
            logger.error("Feil i EndreEtDyr " + e);
            return false;
        }
    }

    public boolean slettEtDyrR(int id){
        String sql = "DELETE FROM Dyr WHERE dyrID = ?";

        try{
            db.update(sql,id);
            return true;
        }
        catch (Exception e){
            logger.error("Feil i slettEtDyrR " + e);
            return false;
        }
    }

}
