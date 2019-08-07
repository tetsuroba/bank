package bank;
public class Encrypt {

    public String encrypt(String password) {
        String encryptedPassword = "";
        for (int i = 0; i < password.length(); i++) {
            encryptedPassword += (char) (password.charAt(i) + 1);
        }
        return encryptedPassword;
    }

    public String decrypt(String password) {
        String decryptedPassword = "";
        for (int i = 0; i < password.length(); i++) {
            decryptedPassword += (char) (password.charAt(i) - 1);
        }
        return decryptedPassword;
    }
}
