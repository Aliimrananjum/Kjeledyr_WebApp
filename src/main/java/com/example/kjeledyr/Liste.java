package com.example.kjeledyr;

public class Liste {
    private int dyrID;
    private String brukernavn;
    private String navn;
    private String alder;
    private String beskrivelse;
    private String dyr;
    private String type;
    private String kjønn;

    public Liste(int dyrID, String brukernavn, String navn, String alder, String beskrivelse, String dyr, String type, String kjønn) {
        this.dyrID = dyrID;
        this.brukernavn = brukernavn;
        this.navn = navn;
        this.alder = alder;
        this.beskrivelse = beskrivelse;
        this.dyr = dyr;
        this.type = type;
        this.kjønn = kjønn;
    }

    public Liste(){};

    public int getDyrID() {
        return dyrID;
    }

    public void setDyrID(int dyrID) {
        this.dyrID = dyrID;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getAlder() {
        return alder;
    }

    public void setAlder(String alder) {
        this.alder = alder;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public String getDyr() {
        return dyr;
    }

    public void setDyr(String dyr) {
        this.dyr = dyr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKjønn() {
        return kjønn;
    }

    public void setKjønn(String kjønn) {
        this.kjønn = kjønn;
    }
}
