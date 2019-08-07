package GFX;

import bank.Client;
import bank.Encrypt;
import bank.MysqlCon;
import bank.Tartozas;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;

public class ClientWindow extends window {

    //Főmenü
    private Client felhaszn;

    private JButton kolcson = new JButton("Kölcsön");
    private JButton utalas = new JButton("Utalás");
    private JButton jelszovalt = new JButton("Jelszóvált");
    private JButton tartozasok = new JButton("Tartozások");

    private JLabel nevLabel;
    private JLabel egyenlegLabel;
    private JLabel cimLabel;
    private MysqlCon con = new MysqlCon();

    //Tartozások rendezése
    private JPanel tartozasokPanel = new JPanel();
    private JButton kolcs_List = new JButton("Listázás");
    private JButton kolcs_rend = new JButton("Rendezés");
    private JLabel tartozasHiba = new JLabel("");

    private JComboBox kolcsList;

    //Utalás
    private JPanel utalasPanel = new JPanel();

    private TextField utalasiCim = new TextField("Utalási cím");
    private TextField utalasiNev = new TextField("Neve");
    private TextField utaltOsszeg = new TextField("Összeg");

    private JButton kuldes = new JButton("Küldés");

    private JLabel utalasHiba = new JLabel("Hiba");

    //Kölcsön
    private JPanel kolcsonPanel = new JPanel();
    private JButton felveves = new JButton("Felvétel");
    private JButton kiszamolas = new JButton("Kiszámolás");

    private TextField kolcsonOsszeg = new TextField("Összeg");

    private JLabel kolcsonHiba = new JLabel("Hiba");
    private JLabel vissza;
    private JLabel kolcs_ID = new JLabel("");
    private JLabel kolcs_osszeg = new JLabel("");
    private JLabel ado_fiok = new JLabel("");

    private JComboBox bankList;

    //Jelszóváltoztatás
    private JPanel jelszovaltPanel = new JPanel();
    private JButton pwcButton = new JButton("Változtatás");
    private JPasswordField regiJelszo = new JPasswordField("");
    private JPasswordField ujJelszo = new JPasswordField("");
    private JPasswordField ujJelszo2 = new JPasswordField("");
    private JLabel regiJelszoLabel = new JLabel("Régi jelszó:");
    private JLabel ujJelszoLabel = new JLabel("Új jelszó:");
    private JLabel ujJelszoLabel2 = new JLabel("Új jelszó megint:");
    private JLabel pwHiba = new JLabel("<html><font color='red'>Nem egyeznek a jelszavak!</font></html>");

    public ClientWindow(int width, int height, Connection conn, int szamlaID) {
        super(width, height);
        setCon(con);
        getGui().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setFelhaszn(new Client(conn, szamlaID));
        setNevLabel(new JLabel(getFelhaszn().getFelhasznalonev()));
        setEgyenlegLabel(new JLabel(Integer.toString(getFelhaszn().getEgyenleg()) + " " + getFelhaszn().getTipus()));
        setCimLabel(new JLabel(Integer.toString(getFelhaszn().getSzamlaID())));
        addButtons(conn);
        addLabels(getPanel());
        setUpPanels(getPanel(), conn);
        getGui().setVisible(true);
        getPanel().setVisible(true);
    }

    public void addLabels(JPanel panel) {
        addVisibleLabel(570, 80, 80, 20, panel, getNevLabel());
        addVisibleLabel(570, 120, 80, 20, panel, new JLabel("Egyenleg:"));
        addVisibleLabel(650, 120, 150, 20, panel, getEgyenlegLabel());
        addVisibleLabel(570, 160, 80, 20, panel, new JLabel("Számlaszám:"));
        addVisibleLabel(700, 160, 80, 20, panel, getCimLabel());
    }

    public void addButtons(Connection conn) {
        addLoanButton(50, 80, 100, 50, getPanel());
        addSendButton(50, 150, 100, 50, getPanel());
        addPWCButton(50, 220, 100, 50, getPanel());
        addLoansButton(50, 290, 100, 50, getPanel(), conn);
    }

    public void setUpPanels(JPanel panel, Connection conn) {
        Rectangle pozicio = new Rectangle(160, 80, 400, 260);
        setUpKolcsonPanel(panel, pozicio, conn);
        setUpUtalasPanel(panel, pozicio, conn);
        setUpPWCPanel(panel, pozicio, conn);
        setUpTartozasPanel(panel, pozicio, conn);
    }

    ///////////////////////UTALÁS PANEL///////////////////////////////////
    public void setUpUtalasPanel(JPanel panel, Rectangle pozicio, Connection conn) {
        setUpPanel(getUtalasPanel(), panel, pozicio);
        addVisibleLabel(50, 50, 80, 20, getUtalasPanel(), new JLabel("Utalási cím:"));
        addVisibleLabel(50, 90, 80, 20, getUtalasPanel(), new JLabel("Neve:"));
        addVisibleLabel(50, 130, 80, 20, getUtalasPanel(), new JLabel("Összeg:"));
        addField(150, 50, 150, 25, getUtalasPanel(), getUtalasiCim());
        addField(150, 90, 150, 25, getUtalasPanel(), getUtalasiNev());
        addField(150, 130, 150, 25, getUtalasPanel(), getUtaltOsszeg());
        addLabel(110, 165, 200, 25, getUtalasPanel(), getUtalasHiba());
        addKuldesButton(150, 200, 100, 40, getUtalasPanel(), conn);

    }

    public void addKuldesButton(int posX, int posY, int width, int height, JPanel panel, Connection conn) {
        getKuldes().setBounds(posX, posY, width, height);
        getKuldes().addActionListener((ActionEvent e) -> {
            Client kinek = null;
            getUtalasHiba().setVisible(false);
            try {
                try {
                    kinek = new Client(conn, Integer.parseInt(getUtalasiCim().getText()));
                } catch (NumberFormatException h) {
                    getUtalasHiba().setText("<html><font color='red'>Hibás felhasználó!</font></html>");
                    getUtalasHiba().setVisible(true);
                }
                getFelhaszn().atutalas(kinek, Integer.parseInt(getUtaltOsszeg().getText()), conn, getUtalasHiba());
                getEgyenlegLabel().setText("" + getFelhaszn().getEgyenleg() + " " + getFelhaszn().getTipus());
                getPanel().repaint();

            } catch (NumberFormatException g) {
                getUtalasHiba().setText("<html><font color='red'>Hibás összeg!</font></html>");
                getUtalasHiba().setVisible(true);
            }
        });
        panel.add(getKuldes());
        getKuldes().setVisible(true);
    }

    public void addSendButton(int posX, int posY, int width, int height, JPanel panel) {
        getUtalas().setBounds(posX, posY, width, height);
        getUtalas().addActionListener((ActionEvent e) -> {
            hidePanels();
            getUtalasPanel().setVisible(true);
        });
        panel.add(getUtalas());
        getUtalas().setVisible(true);
    }

    ////////////////////////KÖLCSÖN////////////////////////////
    public void setUpKolcsonPanel(JPanel panel, Rectangle pozicio, Connection conn) {
        setUpPanel(getKolcsonPanel(), panel, pozicio);
        addBankList(50, 50, 200, 20, getKolcsonPanel(), conn);
        addVisibleLabel(50, 90, 80, 20, getKolcsonPanel(), new JLabel("Mennyiség:"));
        addField(150, 90, 150, 25, getKolcsonPanel(), getKolcsonOsszeg());
        addVisibleLabel(50, 130, 120, 20, getKolcsonPanel(), new JLabel("Visszafizetendő:"));
        setVissza(new JLabel("" + 0));
        addVisibleLabel(190, 130, 80, 20, getKolcsonPanel(), getVissza());
        addLabel(110, 165, 200, 25, getKolcsonPanel(), getKolcsonHiba());
        addCalcButton(50, 200, 100, 40, getKolcsonPanel());
        addInnerLoanButton(200, 200, 100, 40, getKolcsonPanel(), conn);

    }

    public void addBankList(int posX, int posY, int width, int height, JPanel panel, Connection conn) {
        String[] banklist = con.bankList(conn);
        setBankList(new JComboBox(banklist));
        getBankList().setBounds(posX, posY, width, height);
        panel.add(getBankList());
    }

    public void addCalcButton(int posX, int posY, int width, int height, JPanel panel) {
        getKiszamolas().setBounds(posX, posY, width, height);
        getKiszamolas().addActionListener((ActionEvent e) -> {
            getKolcsonHiba().setVisible(false);
            try {
                int visszaOsszeg = (int) (Integer.parseInt(getKolcsonOsszeg().getText()) * 1.10);
                getVissza().setText("" + visszaOsszeg);
                panel.repaint();
            } catch (NumberFormatException g) {
                getKolcsonHiba().setText("<html><font color='red'>Hibás összeg!</font></html>");
                getKolcsonHiba().setVisible(true);
            }
        });
        panel.add(getKiszamolas());
        getKiszamolas().setVisible(true);
    }

    public void addInnerLoanButton(int posX, int posY, int width, int height, JPanel panel, Connection conn) {
        getFelveves().setBounds(posX, posY, width, height);
        getFelveves().addActionListener((ActionEvent e) -> {
            getKolcsonHiba().setVisible(false);
            try {
                getFelhaszn().kolcson((String) (getBankList().getSelectedItem()), Integer.parseInt(getKolcsonOsszeg().getText()), conn, getKolcsonHiba());
                getEgyenlegLabel().setText("" + getFelhaszn().getEgyenleg() + " " + getFelhaszn().getTipus());
                getPanel().repaint();
            } catch (NumberFormatException g) {
                getKolcsonHiba().setText("<html><font color='red'>Hibás összeg!</font></html>");
                getKolcsonHiba().setVisible(true);
            }
        });
        panel.add(getFelveves());
        getFelveves().setVisible(true);
    }

    public void addLoanButton(int posX, int posY, int width, int height, JPanel panel) {
        getKolcson().setBounds(posX, posY, width, height);
        getKolcson().addActionListener((ActionEvent e) -> {
            hidePanels();
            getKolcsonPanel().setVisible(true);
        });
        panel.add(getKolcson());
        getKolcson().setVisible(true);
    }

    ///////////////////////JELSZÓVÁLTOZTATÁS////////////////////////////////
    public void setUpPWCPanel(JPanel panel, Rectangle pozicio, Connection conn) {
        setUpPanel(getJelszovaltPanel(), panel, pozicio);
        addVisibleLabel(50, 50, 120, 20, getJelszovaltPanel(), getRegiJelszoLabel());
        addVisibleLabel(50, 90, 120, 20, getJelszovaltPanel(), getUjJelszoLabel());
        addVisibleLabel(50, 130, 120, 20, getJelszovaltPanel(), getUjJelszoLabel2());

        addLabel(140, 175, 200, 20, getJelszovaltPanel(), getPwHiba());

        addPasswordField(170, 50, 120, 20, getJelszovaltPanel(), getRegiJelszo());
        addPasswordField(170, 100, 120, 20, getJelszovaltPanel(), getUjJelszo());
        addPasswordField(170, 150, 120, 20, getJelszovaltPanel(), getUjJelszo2());

        addPWCButton(150, 200, 100, 40, conn);

    }

    public void addPWCButton(int posX, int posY, int width, int height, Connection conn) {
        Encrypt egy = new Encrypt();
        getPwcButton().setBounds(posX, posY, width, height);
        getPwcButton().addActionListener((ActionEvent e) -> {
            getPwHiba().setVisible(false);
            if (PWCheck(egy.encrypt(getRegiJelszo().getText()))) {
                getFelhaszn().updatePassword(conn, egy.encrypt(getUjJelszo().getText()));
                getPwHiba().setText("<html><font color='green'>Sikeres jelszóváltoztatás!</font></html>");
                getPwHiba().setVisible(true);
            } else {
                getPwHiba().setText("<html><font color='red'>Nem egyeznek a jelszavak!</font></html>");
                getPwHiba().setVisible(true);
            }
        });
        getJelszovaltPanel().add(getPwcButton());
        getPwcButton().setVisible(true);
    }

    public boolean PWCheck(String jelszo) {
        if (jelszo.equals(getFelhaszn().getJelszo())) {
            return getUjJelszo().getText().equals(getUjJelszo2().getText());
        } else {
            return false;
        }
    }

    public void addPWCButton(int posX, int posY, int width, int height, JPanel panel) {
        getJelszovalt().setBounds(posX, posY, width, height);
        getJelszovalt().addActionListener((ActionEvent e) -> {
            hidePanels();
            getJelszovaltPanel().setVisible(true);
        });
        panel.add(getJelszovalt());
        getJelszovalt().setVisible(true);
    }

    ///////////////////////////TARTOZÁSOK/////////////////////////////////////
    public void setUpTartozasPanel(JPanel panel, Rectangle pozicio, Connection conn) {
        setUpPanel(getTartozasokPanel(), panel, pozicio);
        setupTartozasList(20, 50, 100, 20, getTartozasokPanel(), conn);
        addVisibleLabel(150, 50, 80, 20, getTartozasokPanel(), new JLabel("ID:"));
        addVisibleLabel(150, 90, 80, 20, getTartozasokPanel(), new JLabel("Összeg:"));
        addVisibleLabel(150, 130, 80, 20, getTartozasokPanel(), new JLabel("Fiók:"));

        addLabel(270, 50, 80, 20, getTartozasokPanel(), getKolcs_ID());
        addLabel(270, 90, 80, 20, getTartozasokPanel(), getKolcs_osszeg());
        addLabel(270, 130, 120, 20, getTartozasokPanel(), getAdo_fiok());

        addLabel(180, 160, 150, 20, getTartozasokPanel(), getTartozasHiba());

        setUpListazasButton(100, 200, 100, 40, getTartozasokPanel(), conn);
        setUpRendezesButton(220, 200, 100, 40, getTartozasokPanel(), conn);

    }

    public void updateTartozasLabels(Connection conn, String tartozasID) {

        Tartozas temp = getFelhaszn().tartozasLekerdezes(conn, Integer.parseInt(tartozasID));

        getKolcs_ID().setText(Integer.toString(temp.getKolcsonID()));
        getKolcs_osszeg().setText(Integer.toString(temp.getOsszeg()));
        getAdo_fiok().setText(temp.getAdoFiok());

        getKolcs_ID().setVisible(true);
        getKolcs_osszeg().setVisible(true);
        getAdo_fiok().setVisible(true);

        getTartozasokPanel().repaint();

    }

    public void setUpListazasButton(int posX, int posY, int width, int height, JPanel panel, Connection conn) {
        getKolcs_List().setBounds(posX, posY, width, height);
        getKolcs_List().addActionListener((ActionEvent e) -> {
            try {
                getTartozasHiba().setVisible(false);
                updateTartozasLabels(conn, getKolcsList().getSelectedItem().toString());
            } catch (NullPointerException x) {
                getTartozasHiba().setText("<html><font color='red'>Nincs tartozás!</font></html>");
                getTartozasHiba().setVisible(true);
            }

        });
        panel.add(getKolcs_List());
        getKolcs_List().setVisible(true);

    }

    public void setUpRendezesButton(int posX, int posY, int width, int height, JPanel panel, Connection conn) {
        getKolcs_rend().setBounds(posX, posY, width, height);
        getKolcs_rend().addActionListener((ActionEvent e) -> {
            try {
                getTartozasHiba().setVisible(false);
                Tartozas rendeznivalo = getFelhaszn().tartozasLekerdezes(conn, Integer.parseInt(getKolcsList().getSelectedItem().toString()));
                getFelhaszn().szamlaRendezes(conn, rendeznivalo, getTartozasHiba());
                updateTartozasList(conn);
                getEgyenlegLabel().setText("" + getFelhaszn().getEgyenleg() + " " + getFelhaszn().getTipus());
                getPanel().repaint();
            } catch (NullPointerException x) {
                getTartozasHiba().setText("<html><font color='red'>Nincs tartozás!</font></html>");
                getTartozasHiba().setVisible(true);
            }

        });
        panel.add(getKolcs_rend());
        getKolcs_rend().setVisible(true);

    }

    public void setupTartozasList(int posX, int posY, int width, int height, JPanel panel, Connection conn) {
        Tartozas[] l = getFelhaszn().kolcsonTablazat(conn, getFelhaszn());
        setKolcsList(new JComboBox(l));

        getKolcsList().setBounds(posX, posY, width, height);

        panel.add(getKolcsList());
    }

    public void updateTartozasList(Connection conn) {
        Tartozas[] l = getFelhaszn().kolcsonTablazat(conn, getFelhaszn());
        getKolcsList().removeAllItems();
        for (Tartozas i : l) {
            getKolcsList().addItem(i);
        }
    }

    public void addLoansButton(int posX, int posY, int width, int height, JPanel panel, Connection conn) {
        getTartozasok().setBounds(posX, posY, width, height);
        getTartozasok().addActionListener((ActionEvent e) -> {
            hidePanels();
            updateTartozasList(conn);
            getTartozasokPanel().setVisible(true);
        });
        panel.add(getTartozasok());
        getTartozasok().setVisible(true);
    }

    public void hidePanels() {
        getKolcsonPanel().setVisible(false);
        getUtalasPanel().setVisible(false);
        getJelszovaltPanel().setVisible(false);
        getTartozasokPanel().setVisible(false);
        getGui().repaint();
    }

    public MysqlCon getCon() {
        return con;
    }

    public void setCon(MysqlCon con) {
        this.con = con;
    }

    public JButton getKolcson() {
        return kolcson;
    }

    public void setKolcson(JButton kolcson) {
        this.kolcson = kolcson;
    }

    public JButton getUtalas() {
        return utalas;
    }

    public void setUtalas(JButton utalas) {
        this.utalas = utalas;
    }

    public JButton getJelszovalt() {
        return jelszovalt;
    }

    public void setJelszovalt(JButton jelszovalt) {
        this.jelszovalt = jelszovalt;
    }

    public JButton getTartozasok() {
        return tartozasok;
    }

    public void setTartozasok(JButton tartozasok) {
        this.tartozasok = tartozasok;
    }

    public JPanel getKolcsonPanel() {
        return kolcsonPanel;
    }

    public void setKolcsonPanel(JPanel kolcsonPanel) {
        this.kolcsonPanel = kolcsonPanel;
    }

    public JPanel getUtalasPanel() {
        return utalasPanel;
    }

    public void setUtalasPanel(JPanel utalasPanel) {
        this.utalasPanel = utalasPanel;
    }

    public JPanel getJelszovaltPanel() {
        return jelszovaltPanel;
    }

    public void setJelszovaltPanel(JPanel jelszovaltPanel) {
        this.jelszovaltPanel = jelszovaltPanel;
    }

    public JPanel getTartozasokPanel() {
        return tartozasokPanel;
    }

    public void setTartozasokPanel(JPanel tartozasokPanel) {
        this.tartozasokPanel = tartozasokPanel;
    }

    public Client getFelhaszn() {
        return felhaszn;
    }

    public void setFelhaszn(Client felhaszn) {
        this.felhaszn = felhaszn;
    }

    public JLabel getNevLabel() {
        return nevLabel;
    }

    public void setNevLabel(JLabel nevLabel) {
        this.nevLabel = nevLabel;
    }

    public JLabel getEgyenlegLabel() {
        return egyenlegLabel;
    }

    public void setEgyenlegLabel(JLabel egyenlegLabel) {
        this.egyenlegLabel = egyenlegLabel;
    }

    public JLabel getCimLabel() {
        return cimLabel;
    }

    public void setCimLabel(JLabel cimLabel) {
        this.cimLabel = cimLabel;
    }

    public TextField getUtalasiCim() {
        return utalasiCim;
    }

    public void setUtalasiCim(TextField utalasiCim) {
        this.utalasiCim = utalasiCim;
    }

    public TextField getUtalasiNev() {
        return utalasiNev;
    }

    public void setUtalasiNev(TextField utalasiNev) {
        this.utalasiNev = utalasiNev;
    }

    public TextField getUtaltOsszeg() {
        return utaltOsszeg;
    }

    public void setUtaltOsszeg(TextField utaltOsszeg) {
        this.utaltOsszeg = utaltOsszeg;
    }

    public JButton getKuldes() {
        return kuldes;
    }

    public void setKuldes(JButton kuldes) {
        this.kuldes = kuldes;
    }

    public JComboBox getBankList() {
        return bankList;
    }

    public void setBankList(JComboBox bankList) {
        this.bankList = bankList;
    }

    public TextField getKolcsonOsszeg() {
        return kolcsonOsszeg;
    }

    public void setKolcsonOsszeg(TextField kolcsonOsszeg) {
        this.kolcsonOsszeg = kolcsonOsszeg;
    }

    public JLabel getVissza() {
        return vissza;
    }

    public void setVissza(JLabel vissza) {
        this.vissza = vissza;
    }

    public JButton getFelveves() {
        return felveves;
    }

    public void setFelveves(JButton felveves) {
        this.felveves = felveves;
    }

    public JButton getKiszamolas() {
        return kiszamolas;
    }

    public void setKiszamolas(JButton kiszamolas) {
        this.kiszamolas = kiszamolas;
    }

    public JLabel getUtalasHiba() {
        return utalasHiba;
    }

    public void setUtalasHiba(JLabel utalasHiba) {
        this.utalasHiba = utalasHiba;
    }

    public JLabel getKolcsonHiba() {
        return kolcsonHiba;
    }

    public void setKolcsonHiba(JLabel kolcsonHiba) {
        this.kolcsonHiba = kolcsonHiba;
    }

    public JLabel getKolcs_ID() {
        return kolcs_ID;
    }

    public void setKolcs_ID(JLabel kolcs_ID) {
        this.kolcs_ID = kolcs_ID;
    }

    public JLabel getKolcs_osszeg() {
        return kolcs_osszeg;
    }

    public void setKolcs_osszeg(JLabel kolcs_osszeg) {
        this.kolcs_osszeg = kolcs_osszeg;
    }

    public JLabel getAdo_fiok() {
        return ado_fiok;
    }

    public void setAdo_fiok(JLabel ado_fiok) {
        this.ado_fiok = ado_fiok;
    }

    public JButton getKolcs_List() {
        return kolcs_List;
    }

    public void setKolcs_List(JButton kolcs_List) {
        this.kolcs_List = kolcs_List;
    }

    public JButton getKolcs_rend() {
        return kolcs_rend;
    }

    public void setKolcs_rend(JButton kolcs_rend) {
        this.kolcs_rend = kolcs_rend;
    }

    public JComboBox getKolcsList() {
        return kolcsList;
    }

    public void setKolcsList(JComboBox kolcsList) {
        this.kolcsList = kolcsList;
    }

    public JLabel getTartozasHiba() {
        return tartozasHiba;
    }

    public void setTartozasHiba(JLabel tartozasHiba) {
        this.tartozasHiba = tartozasHiba;
    }

    public JPasswordField getRegiJelszo() {
        return regiJelszo;
    }

    public void setRegiJelszo(JPasswordField regiJelszo) {
        this.regiJelszo = regiJelszo;
    }

    public JPasswordField getUjJelszo() {
        return ujJelszo;
    }

    public void setUjJelszo(JPasswordField ujJelszo) {
        this.ujJelszo = ujJelszo;
    }

    public JPasswordField getUjJelszo2() {
        return ujJelszo2;
    }

    public void setUjJelszo2(JPasswordField ujJelszo2) {
        this.ujJelszo2 = ujJelszo2;
    }

    public JLabel getRegiJelszoLabel() {
        return regiJelszoLabel;
    }

    public void setRegiJelszoLabel(JLabel regiJelszoLabel) {
        this.regiJelszoLabel = regiJelszoLabel;
    }

    public JLabel getUjJelszoLabel() {
        return ujJelszoLabel;
    }

    public void setUjJelszoLabel(JLabel ujJelszoLabel) {
        this.ujJelszoLabel = ujJelszoLabel;
    }

    public JLabel getUjJelszoLabel2() {
        return ujJelszoLabel2;
    }

    public void setUjJelszoLabel2(JLabel ujJelszoLabel2) {
        this.ujJelszoLabel2 = ujJelszoLabel2;
    }

    public JLabel getPwHiba() {
        return pwHiba;
    }

    public void setPwHiba(JLabel pwHiba) {
        this.pwHiba = pwHiba;
    }

    public JButton getPwcButton() {
        return pwcButton;
    }

    public void setPwcButton(JButton pwcButton) {
        this.pwcButton = pwcButton;
    }

}
