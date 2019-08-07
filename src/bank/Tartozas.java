package bank;
public class Tartozas {

    private int kolcsonID;
    private int osszeg;
    private String felvevoNeve;
    private String adoFiok;

    public Tartozas(int kolcsonID, int osszeg, String felvevoNeve, String adoFiok) {
        setKolcsonID(kolcsonID);
        setOsszeg(osszeg);
        setFelvevoNeve(felvevoNeve);
        setAdoFiok(adoFiok);
    }

    @Override
    public String toString() {
        return Integer.toString(kolcsonID);
    }

    public Tartozas toTartozas() {
        return this;
    }

    /*
    "SELECT kolcsonzesi_ID,osszeg,felvevo_neve,ado_fiok " +
                         "FROM kolcson " +
                         "WHERE felvevo_neve=\"" + kliens.getFelhasznalonev() + "\" AND rendezve=\"" + 0 + "\"";
     */
    public int getKolcsonID() {
        return kolcsonID;
    }

    public void setKolcsonID(int kolcsonID) {
        this.kolcsonID = kolcsonID;
    }

    public int getOsszeg() {
        return osszeg;
    }

    public void setOsszeg(int osszeg) {
        this.osszeg = osszeg;
    }

    public String getFelvevoNeve() {
        return felvevoNeve;
    }

    public void setFelvevoNeve(String felvevoNeve) {
        this.felvevoNeve = felvevoNeve;
    }

    public String getAdoFiok() {
        return adoFiok;
    }

    public void setAdoFiok(String adoFiok) {
        this.adoFiok = adoFiok;
    }

}
