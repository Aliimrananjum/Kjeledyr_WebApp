//POJO for poststed slik at applikasjonen kan teste om brukeres har skrevet inn riktig postnr i registrering av bruker siden.

package com.example.kjeledyr;

public class PostSted {
    private String postnummer;
    private String poststed;
    private String kommunenummer;
    private String kommunenavn;
    private String kategori;

    public PostSted(String postnummer, String poststed, String kommunenummer, String kommunenavn, String kategori) {
        this.postnummer = postnummer;
        this.poststed = poststed;
        this.kommunenummer = kommunenummer;
        this.kommunenavn = kommunenavn;
        this.kategori = kategori;
    }

    public String getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(String postnummer) {
        this.postnummer = postnummer;
    }

    public String getPoststed() {
        return poststed;
    }

    public void setPoststed(String poststed) {
        this.poststed = poststed;
    }

    public String getKommunenummer() {
        return kommunenummer;
    }

    public void setKommunenummer(String kommunenummer) {
        this.kommunenummer = kommunenummer;
    }

    public String getKommunenavn() {
        return kommunenavn;
    }

    public void setKommunenavn(String kommunenavn) {
        this.kommunenavn = kommunenavn;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public PostSted(){
    }

}
