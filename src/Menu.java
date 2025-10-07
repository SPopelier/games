import javax.swing.*; //importe les classes du package swing, y compris JOptionPane

public class Menu {

    public void showError(String message) {
        JOptionPane.showMessageDialog(null,message,"Error",JOptionPane.ERROR_MESSAGE);
    }

    public String requestText(String prompt) {
        return JOptionPane.showInputDialog(prompt);
    }

    public void displayText(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
