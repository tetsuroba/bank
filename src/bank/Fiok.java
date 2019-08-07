package bank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Fiok {

    private String fioknev;
    private int tartalek;
    private String varos;

    public Fiok(String fiok, Connection conn) {
        getFiokData(fiok, conn);
    }

    public void updateDatabase(Connection conn) {
        String queryString = "UPDATE bankfiok "
                + "SET tartalek=\"" + getTartalek() + "\" "
                + "WHERE fioknev=\"" + getFioknev() + "\"";
        try {
            Statement stmt = (Statement) conn.createStatement();
            stmt.execute(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getFiokData(String fiok, Connection conn) {
        String queryString = "SELECT fioknev,tartalek,varos "
                + "FROM bankfiok "
                + "WHERE fioknev=\"" + fiok + "\"";
        try {
            Statement stmt = (Statement) conn.createStatement();
            stmt.execute(queryString);
            ResultSet rs = stmt.executeQuery(queryString);
            while (rs.next()) {
                setFioknev(rs.getString("fioknev"));
                setTartalek(rs.getInt("tartalek"));
                setVaros(rs.getString("varos"));
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public String getFioknev() {
        return fioknev;
    }

    public void setFioknev(String fioknev) {
        this.fioknev = fioknev;
    }

    public int getTartalek() {
        return tartalek;
    }

    public void setTartalek(int tartalek) {
        this.tartalek = tartalek;
    }

    public String getVaros() {
        return varos;
    }

    public void setVaros(String varos) {
        this.varos = varos;
    }

}
