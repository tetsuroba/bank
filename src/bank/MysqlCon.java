package bank;

import GFX.*;
import java.sql.*;

public class MysqlCon {

    private Connection conn = null;

    public Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            String url = "jdbc:mysql://localhost:3306/bank";
            setConn(DriverManager.getConnection(url, "root", ""));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return getConn();
    }

    public String[] bankList(Connection conn) {
        String[] bankok = new String[bankListSize(conn)];
        String queryString = "SELECT fioknev FROM bankfiok";
        int k = 0;
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(queryString);
            ResultSet rs = stmt.executeQuery(queryString);
            while (rs.next()) {
                bankok[k] = rs.getString(1);
                k++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bankok;
    }

    public int bankListSize(Connection conn) {
        int size = 0;
        try {
            String queryString = "SELECT COUNT(*) FROM bankfiok";
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

    public void registration(String username, String password, String fullname, String address, String city, Connection conn, RegistrationWindow w, int tipus) {
        Encrypt egy = new Encrypt();
        try {
            Statement statement = conn.createStatement();
            statement.execute("INSERT INTO `ugyfel` (felhasznalonev, jelszo, ugyfelnev, ugyfelutca, ugyfelvaros) VALUES  (\"" + username + "\"" + ",\"" + egy.encrypt(password) + "\"" + ",\"" + fullname + "\"" + ",\"" + address + "\"" + ",\"" + city + "\")");
            statement.execute("INSERT INTO `bankszamla` (tipus,egyenleg,tulajdonos_ID) VALUES(\"" + tipus + "\"" + ",\"" + 0 + "\",\"" + username + "\")");
            w.getGui().dispose();
            MainWindow kezdo = new MainWindow(500, 500);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000") && e.getMessage().contains("Duplicate")) {
                w.getFoglaltFelh().setVisible(true);
            }
            System.err.println(e.getMessage());
        }
    }

    public void checkCreds(String username, String password, Connection conn, MainWindow w) {
        Encrypt egy = new Encrypt();
        int dbSzamla = 0;
        String queryString = "SELECT * FROM `ugyfel` WHERE felhasznalonev=\"" + username + "\"" + " AND jelszo=\"" + egy.encrypt(password) + "\"";
        try {
            Statement stmt = (Statement) conn.createStatement();
            stmt.execute(queryString);
            ResultSet rs = stmt.executeQuery(queryString);
            if (rs.next() == false) {
                w.getLoginError().setVisible(true);
            } else {
                rs.beforeFirst();

            }

            while (rs.next()) {
                String dbUsername = rs.getString("felhasznalonev");
                String dbPassword = rs.getString("jelszo");
                if (dbUsername.equals(username) && dbPassword.equals(egy.encrypt(password))) {
                    String newQuery = "SELECT szamla_ID FROM ugyfel,bankszamla WHERE ugyfel.felhasznalonev = bankszamla.tulajdonos_ID AND felhasznalonev = \"" + dbUsername + "\"";
                    stmt = (Statement) conn.createStatement();
                    stmt.execute(newQuery);
                    rs = stmt.executeQuery(newQuery);
                    while (rs.next()) {
                        dbSzamla = rs.getInt("szamla_ID");
                    }
                    ClientWindow kliens = new ClientWindow(800, 500, getConn(), dbSzamla);
                    w.getGui().dispose();
                }
            }
        } catch (Exception e) {
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
