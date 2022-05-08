//POJO for dropdown meny for registrering av dyr.
package com.example.kjeledyr;

public class DyrDropdown {

    String dyr;
    String type;
    String kjønn;

    public DyrDropdown(String dyr, String type, String kjønn) {
        this.dyr = dyr;
        this.type = type;
        this.kjønn = kjønn;
    }

    public DyrDropdown(){}

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

    public String getKjønn(){return kjønn;}

    public void setKjønn(String kjønn){
        this.kjønn = kjønn;
    }
}
