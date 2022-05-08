//POJO for når det skal registreres en bruker på på bruker.html/bruker.js

package com.example.kjeledyr;

public class Bruker {
    private int brukerID; //Primærnøkkel i DB.
    private String brukernavn;
    private String passord;
    private String fornavn;
    private String etternavn;
    private String epost;
    private String mobilNr;
    private String gateAdresse;
    private String postnummer;
    private String kjønn;
    private int admin; // for å legge rettigheter (0/1) gjennom sessions.

    public Bruker(int brukerID, String brukernavn, String passord, String fornavn, String etternavn, String epost, String mobilNr, String gateAdresse, String postnummer, String kjønn, int admin) {
        this.brukerID = brukerID;
        this.brukernavn = brukernavn;
        this.passord = passord;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.epost = epost;
        this.mobilNr = mobilNr;
        this.gateAdresse = gateAdresse;
        this.postnummer = postnummer;
        this.kjønn = kjønn;
        this.admin = admin;
    }

    public Bruker(){}

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public int getBrukerID() {
        return brukerID;
    }

    public void setBrukerID(int brukerID) {
        this.brukerID = brukerID;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public String getMobilNr() {
        return mobilNr;
    }

    public void setMobilNr(String mobilNr) {
        this.mobilNr = mobilNr;
    }

    public String getGateAdresse() {
        return gateAdresse;
    }

    public void setGateAdresse(String gateAdresse) {
        this.gateAdresse = gateAdresse;
    }

    public String getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(String postnummer) {
        this.postnummer = postnummer;
    }


    public String getKjønn() {
        return kjønn;
    }

    public void setKjønn(String kjønn) {
        this.kjønn = kjønn;
    }
}
