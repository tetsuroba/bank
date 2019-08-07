package GFX;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

public abstract class window {

    private JFrame gui;
    private JPanel panel;
    private TextField name = new TextField("Felhasználónév ide", 255);
    private TextField fullName = new TextField("Teljes név", 255);
    private TextField adress = new TextField("Cím", 255);
    private TextField city = new TextField("Város", 255);
    private JPasswordField password = new JPasswordField("Jelszó ide", 255);
    private JButton login = new JButton("Belépés");

    public window(int width, int height) {
        setGui(new JFrame("Bank"));
        setPanel(new JPanel());
        getGui().add(getPanel());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        gui.setBounds((screenSize.width / 2) - (width / 2), (screenSize.height / 2) - (height / 2), width, height);
        panel.setBounds((screenSize.width / 2) - (width / 2), (screenSize.height / 2) - (height / 2), width, height);
        getGui().setResizable(false);
        getPanel().setLayout(null);
    }

    public void disposeGui() {
        getGui().dispose();
        getPanel().setEnabled(false);
    }

    public void addLabel(int posX, int posY, int width, int height, JPanel panel, JLabel label) {
        label.setBounds(posX, posY, width, height);
        panel.add(label);
        label.setVisible(false);
    }

    public void addVisibleLabel(int posX, int posY, int width, int height, JPanel panel, JLabel label) {
        label.setBounds(posX, posY, width, height);
        panel.add(label);
        label.setVisible(true);
    }

    public void addField(int posX, int posY, int width, int height, JPanel panel, TextField field) {
        field.setBounds(posX, posY, width, height);
        field.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                field.setText("");
                field.setForeground(Color.BLACK);
            }
        });
        panel.add(field);
        field.setVisible(true);
    }

    public void setUpPanel(JPanel mit, JPanel mire, Rectangle pozicio) {
        mit.setBounds(pozicio);
        mire.add(mit);
        mit.setBorder(new LineBorder(Color.black, 1));
        mit.setLayout(null);
        mit.setVisible(false);
    }

    public void addPasswordField(int posX, int posY, int width, int height, JPanel panel, JPasswordField pw) {
        pw.setBounds(posX, posY, width, height);
        pw.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getPassword().setText("");
                getPassword().setForeground(Color.BLACK);
            }
        });
        panel.add(pw);
        pw.setVisible(true);
    }

    public JFrame getGui() {
        return gui;
    }

    public void setGui(JFrame gui) {
        this.gui = gui;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public TextField getName() {
        return name;
    }

    public void setName(TextField name) {
        this.name = name;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public void setPassword(JPasswordField password) {
        this.password = password;
    }

    public JButton getLogin() {
        return login;
    }

    public void setLogin(JButton login) {
        this.login = login;
    }

    public TextField getFullName() {
        return fullName;
    }

    public void setFullName(TextField fullName) {
        this.fullName = fullName;
    }

    public TextField getAdress() {
        return adress;
    }

    public void setAdress(TextField adress) {
        this.adress = adress;
    }

    public TextField getCity() {
        return city;
    }

    public void setCity(TextField city) {
        this.city = city;
    }

}
