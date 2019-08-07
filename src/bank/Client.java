package bank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;

public class Client {

    private String felhasznalonev;
    private String ugyfelnev;
    private String ugyfelID;
    private int egyenleg;
    private String tipus;
    private int szamlaID;
    private int tartozas;
    private String jelszo;

    public Client(Connection conn, int szamlaID) {
        getClientData(szamlaID, conn);

    }

    public void updateDatabase(Connection conn) {
        String queryString = "UPDATE bankszamla,ugyfel "
                           + "SET egyenleg=\"" + getEgyenleg() + "\" ,tartozas= \"" + getTartozas() + "\" "
                           + "WHERE tulajdonos_ID=\"" + getFelhasznalonev() + "\" AND szamla_ID=\"" + getSzamlaID() + "\" AND bankszamla.tulajdonos_ID = ugyfel.felhasznalonev";
        try {
            Statement stmt = (Statement) conn.createStatement();
            stmt.execute(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atutalas(Client kinek, int osszeg, Connection conn, JLabel label) {
        if (osszeg < 1) {
            errorMessage(label, "Összeg negatív");
        } else {
            try {
                if (kinek.getSzamlaID() == getSzamlaID()) {
                    errorMessage(label, "Magadnak nem utalhatsz!");
                } else {
                    if (osszeg > getEgyenleg()) {
                        errorMessage(label, "Túl nagy összeg!");
                    } else if (kinek.szamlaID != (-1)) {
                        if (!(getTipus().equals(kinek.tipus))) {
                            errorMessage(label, "A másik számla különböző valuta!");
                        } else {
                            setEgyenleg(getEgyenleg() - osszeg);
                            updateDatabase(conn);
                            kinek.setEgyenleg(kinek.getEgyenleg() + osszeg);
                            kinek.updateDatabase(conn);
                            label.setText("<html><font color='green'>Sikeres utalás!</font></html>");
                            label.setVisible(true);
                        }
                    } else {
                        errorMessage(label, "Nincs ilyen felhasználó!");
                    }
                }
            } catch (NullPointerException i) {
                errorMessage(label, "Nincs ilyen felhasználó!");
            }
        }
    }

    public void errorMessage(JLabel label, String errorMsg) {
        label.setText("<html><font color='red'>" + errorMsg + "</font></html>");
        label.setVisible(true);
    }

    public void updatePassword(Connection conn, String mire) {
        String queryString = "UPDATE ugyfel SET jelszo=\"" + mire + "\" WHERE felhasznalonev=\"" + getFelhasznalonev() + "\"";
        try {
            Statement stmt = (Statement) conn.createStatement();
            stmt.execute(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void kolcson(String honnan, int mennyiseg, Connection conn, JLabel hiba) {
        Fiok temp = new Fiok(honnan, conn);
        if (!(getTipus().equals("Forint"))) {
            errorMessage(hiba, "Csak forintszámla!");
        } else {
            if (mennyiseg > temp.getTartalek()) {
                errorMessage(hiba, "Hibás összeg!");
            } else {
                if (mennyiseg >= 1) {
                    setEgyenleg(getEgyenleg() + mennyiseg);
                    setTartozas((int) (mennyiseg * 1.10) + getTartozas());
                    temp.setTartalek(temp.getTartalek() - mennyiseg);
                    hiba.setText("<html><font color='green'>Sikeres kölcsön!</font></html>");
                    hiba.setVisible(true);
                    kolcsonInsert(temp, this, (int) (mennyiseg * 1.10), conn);
                    updateDatabase(conn);
                } else {
                    errorMessage(hiba, "Hibás összeg");
                }
            }
        }

        updateDatabase(conn);
        temp.updateDatabase(conn);
    }

    public int listSize(Connection conn, Client kliens) {
        int size = 0;
        try {
            String queryString = "SELECT COUNT(*) FROM kolcson WHERE felvevo_neve=\"" + kliens.getFelhasznalonev() + "\" AND rendezve=\"" + 0 + "\"";
            Statement stmt = conn.createStatement();
            stmt.execute(queryString);
            ResultSet rs = stmt.executeQuery(queryString);
            while (rs.next()) {
                size = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return size;
    }

    public void szamlaRendezes(Connection conn, Tartozas rendeznivalo, JLabel hiba) {
        Fiok temp = new Fiok(rendeznivalo.getAdoFiok(), conn);
        if (rendeznivalo.getOsszeg() > getEgyenleg()) {
            errorMessage(hiba, "Alacsony egyenleg!");
        } else {
            String queryString = "UPDATE kolcson SET rendezve=\"" + 1 + "\" " + "WHERE kolcsonzesi_ID=\"" + rendeznivalo.getKolcsonID() + "\"";
            try {
                Statement stmt = (Statement) conn.createStatement();
                stmt.execute(queryString);
                setEgyenleg(getEgyenleg() - rendeznivalo.getOsszeg());
                setTartozas(getTartozas() - rendeznivalo.getOsszeg());
                temp.setTartalek(temp.getTartalek() + rendeznivalo.getOsszeg());
                updateDatabase(conn);
                temp.updateDatabase(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Tartozas tartozasLekerdezes(Connection conn, int tartozasAzonosito) {
        String queryString = "SELECT kolcsonzesi_ID,osszeg,felvevo_neve,ado_fiok "
                            + "FROM kolcson "
                            + "WHERE kolcsonzesi_ID=" + tartozasAzonosito;
        Tartozas vissza = null;
        try {
            Statement stmt = (Statement) conn.createStatement();
            stmt.execute(queryString);
            ResultSet rs = stmt.executeQuery(queryString);
            while (rs.next()) {
                vissza = new Tartozas(rs.getInt("kolcsonzesi_ID"), rs.getInt("osszeg"), rs.getString("felvevo_neve"), rs.getString("ado_fiok"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vissza;
    }

    public Tartozas[] kolcsonTablazat(Connection conn, Client kliens) {
        Tartozas[] tartozasok = new Tartozas[listSize(conn, kliens)];
        int k = 0;
        String queryString = "SELECT kolcsonzesi_ID,osszeg,felvevo_neve,ado_fiok "
                            + "FROM kolcson "
                            + "WHERE felvevo_neve=\"" + kliens.getFelhasznalonev() + "\" AND rendezve=\"" + 0 + "\"";

        try {
            Statement stmt = (Statement) conn.createStatement();
            stmt.execute(queryString);
            ResultSet rs = stmt.executeQuery(queryString);
            while (rs.next()) {
                tartozasok[k] = new Tartozas(rs.getInt("kolcsonzesi_ID"), rs.getInt("osszeg"), rs.getString("felvevo_neve"), rs.getString("ado_fiok"));
                k++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tartozasok;
    }

    public void kolcsonInsert(Fiok fiok, Client kliens, int osszeg, Connection conn) {
        try {
            Statement statement = conn.createStatement();
            String queryString = "INSERT INTO `kolcson` (osszeg,felvevo_neve,ado_fiok) VALUES(\"" + osszeg + "\"" + ",\"" + kliens.getFelhasznalonev() + "\",\"" + fiok.getFioknev() + "\")";
            statement.execute(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getClientData(int szamlaID, Connection conn) {
        String queryString = "SELECT felhasznalonev,ugyfelnev,ugyfel_ID,valuta, egyenleg, szamla_ID, tartozas, jelszo"
                + "           FROM ugyfel,bankszamla,valuta"
                + "           WHERE bankszamla.tipus = valuta.valutaKod AND ugyfel.felhasznalonev = bankszamla.tulajdonos_ID AND bankszamla.szamla_ID = \"" + szamlaID + "\"";

        try {
            Statement stmt = (Statement) conn.createStatement();
            stmt.execute(queryString);
            ResultSet rs = stmt.executeQuery(queryString);

            if (rs.next() == false) {
                System.err.println("Nincs ilyen felhasználó");
                this.szamlaID = -1;
                return;
            } else {
                rs.beforeFirst();
            }

            while (rs.next()) {
                setFelhasznalonev(rs.getString("felhasznalonev"));
                setUgyfelnev(rs.getString("ugyfelnev"));
                setUgyfelID(rs.getString("ugyfel_ID"));
                setTipus(rs.getString("valuta"));
                setEgyenleg(rs.getInt("egyenleg"));
                setSzamlaID(rs.getInt("szamla_ID"));
                setTartozas(rs.getInt("tartozas"));
                setJelszo(rs.getString("jelszo"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public String getFelhasznalonev() {
        return felhasznalonev;
    }

    public void setFelhasznalonev(String felhasznalonev) {
        this.felhasznalonev = felhasznalonev;
    }

    public String getUgyfelnev() {
        return ugyfelnev;
    }

    public void setUgyfelnev(String ugyfelnev) {
        this.ugyfelnev = ugyfelnev;
    }

    public String getUgyfelID() {
        return ugyfelID;
    }

    public void setUgyfelID(String ugyfelID) {
        this.ugyfelID = ugyfelID;
    }

    public int getEgyenleg() {
        return egyenleg;
    }

    public void setEgyenleg(int egyenleg) {
        this.egyenleg = egyenleg;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public int getSzamlaID() {
        return szamlaID;
    }

    public void setSzamlaID(int szamlaID) {
        this.szamlaID = szamlaID;
    }

    public int getTartozas() {
        return tartozas;
    }

    public void setTartozas(int tartozas) {
        this.tartozas = tartozas;
    }

    public String getJelszo() {
        return jelszo;
    }

    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }

}
