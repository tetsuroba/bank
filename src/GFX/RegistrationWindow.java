package GFX;

import bank.*;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

/**
 *
 * @author Robi
 */
public class RegistrationWindow extends window {

    private String felh;
    private String teljesnev;
    private String cim;
    private String varos;
    private String jelszo;
    private JRadioButton forintGomb = new JRadioButton("Forint");
    private JRadioButton euroGomb = new JRadioButton("Euró");
    private JRadioButton dollarGomb = new JRadioButton("Dollár");
    private ButtonGroup valutaCsoport = new ButtonGroup();
    boolean[] valutak = new boolean[3];
    private JButton regisztr = new JButton("Regisztráció");
    private JLabel foglaltFelh = new JLabel("<html><font color='red'>Ez a felhasználónév már foglalt!</font></html>");
    private MysqlCon con;

    public RegistrationWindow(int width, int height, MysqlCon con) {
        super(width, height);
        setCon(con);
        getGui().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addField(150, 150, 200, 30, getPanel(), getName());
        addPasswordField(150, 225, 200, 30, getPanel(), getPassword());
        addField(150, 300, 200, 30, getPanel(), getFullName());
        addField(150, 375, 200, 30, getPanel(), getAdress());
        addField(150, 450, 200, 30, getPanel(), getCity());
        addLabel(160, 520, 300, 50, getPanel(), getFoglaltFelh());
        addLoginButton(175, 580, 150, 70, getPanel());
        addValutaGomb(getPanel());

        getGui().setVisible(true);
        getPanel().setVisible(true);
    }

    public void addValutaGomb(JPanel panel) {
        getForintGomb().setBounds(130, 510, 60, 20);
        getEuroGomb().setBounds(230, 510, 60, 20);
        getDollarGomb().setBounds(330, 510, 60, 20);

        panel.add(getForintGomb());
        panel.add(getEuroGomb());
        panel.add(getDollarGomb());

        getForintGomb().setSelected(true);

        getValutaCsoport().add(getForintGomb());
        getValutaCsoport().add(getEuroGomb());
        getValutaCsoport().add(getDollarGomb());

    }

    public void addLoginButton(int posX, int posY, int width, int height, JPanel panel) {
        getRegisztr().setBounds(posX, posY, width, height);
        getRegisztr().addActionListener((ActionEvent e) -> {
            if (getForintGomb().isSelected()) { //1
                regisztr(1);
            } else if (getEuroGomb().isSelected()) { //2
                regisztr(2);
            } else if (getDollarGomb().isSelected()) { // 3
                regisztr(3);
            }

        });
        panel.add(getRegisztr());
        getRegisztr().setVisible(true);
    }

    public void regisztr(int tipus) {
        con.registration(getName().getText(), getPassword().getText(), getFullName().getText(), getAdress().getText(), getCity().getText(), getCon().getConn(), this, tipus);
    }

    public MysqlCon getCon() {
        return con;
    }

    public void setCon(MysqlCon con) {
        this.con = con;
    }

    public String getFelh() {
        return felh;
    }

    public void setFelh(String felh) {
        this.felh = felh;
    }

    public String getTeljesnev() {
        return teljesnev;
    }

    public void setTeljesnev(String teljesnev) {
        this.teljesnev = teljesnev;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public String getVaros() {
        return varos;
    }

    public void setVaros(String varos) {
        this.varos = varos;
    }

    public String getJelszo() {
        return jelszo;
    }

    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }

    public JButton getRegisztr() {
        return regisztr;
    }

    public void setRegisztr(JButton regisztr) {
        this.regisztr = regisztr;
    }

    public JLabel getFoglaltFelh() {
        return foglaltFelh;
    }

    public void setFoglaltFelh(JLabel foglaltFelh) {
        this.foglaltFelh = foglaltFelh;
    }

    public JRadioButton getForintGomb() {
        return forintGomb;
    }

    public void setForintGomb(JRadioButton forintGomb) {
        this.forintGomb = forintGomb;
    }

    public JRadioButton getEuroGomb() {
        return euroGomb;
    }

    public void setEuroGomb(JRadioButton euroGomb) {
        this.euroGomb = euroGomb;
    }

    public JRadioButton getDollarGomb() {
        return dollarGomb;
    }

    public void setDollarGomb(JRadioButton dollarGomb) {
        this.dollarGomb = dollarGomb;
    }

    public ButtonGroup getValutaCsoport() {
        return valutaCsoport;
    }

    public void setValutaCsoport(ButtonGroup valutaCsoport) {
        this.valutaCsoport = valutaCsoport;
    }

}
