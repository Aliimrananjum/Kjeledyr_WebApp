//POJO for Dropdown meny når en bruker skal registreres
package com.example.kjeledyr;

public class BrukerDropdown {
    private String kjonn;

    public BrukerDropdown(String kjonn) {
        this.kjonn = kjonn;
    }

    public BrukerDropdown(){

    }

    public String getKjonn() {
        return kjonn;
    }

    public void setKjonn(String kjonn) {
        this.kjonn = kjonn;
    }
}
