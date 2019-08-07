package GFX;

import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import bank.*;
import javax.swing.JButton;
import javax.swing.JLabel;

public class MainWindow extends window {

    private MysqlCon con = new MysqlCon();
    private JButton register = new JButton("Regisztráció");
    private JLabel loginError = new JLabel("<html><font color='red'>Hibás bejelentkezés!</font></html>");

    public MainWindow(int width, int height) {
        super(width, height);
        getCon().connect();
        getGui().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addField(150, 150, 200, 30, getPanel(), getName());
        addPasswordField(150, 225, 200, 30, getPanel(), getPassword());
        addLoginButton(90, 300, 150, 70, getPanel());
        addLabel(190, 250, 300, 30, getPanel(), getLoginError());
        addRegistrationButton(260, 300, 150, 70, getPanel());
        getGui().setVisible(true);
        getPanel().setVisible(true);
    }

    public void addRegistrationButton(int posX, int posY, int width, int height, JPanel panel) {
        getRegister().setBounds(posX, posY, width, height);
        getRegister().addActionListener((ActionEvent e) -> {
            RegistrationWindow regisztracio = new RegistrationWindow(500, 700, getCon());
            this.disposeGui();
        });
        panel.add(getRegister());
        getRegister().setVisible(true);
    }

    public void addLoginButton(int posX, int posY, int width, int height, JPanel panel) {
        getLogin().setBounds(posX, posY, width, height);
        getLogin().addActionListener((ActionEvent e) -> {
            getCon().checkCreds(getName().getText(), getPassword().getText(), getCon().getConn(), this);
        });
        panel.add(getLogin());
        getLogin().setVisible(true);
    }

    public JButton getRegister() {
        return register;
    }

    public void setRegister(JButton register) {
        this.register = register;
    }

    public JLabel getLoginError() {
        return loginError;
    }

    public void setLoginError(JLabel loginError) {
        this.loginError = loginError;
    }

    public MysqlCon getCon() {
        return con;
    }

    public void setCon(MysqlCon con) {
        this.con = con;
    }

}
