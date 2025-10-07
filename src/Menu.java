import javax.swing.*; //importe les classes du package swing, y compris JOptionPane

public class Menu {

    public String requestText(String prompt) {
        return JOptionPane.showInputDialog(prompt);
    }

    public void displayText(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
