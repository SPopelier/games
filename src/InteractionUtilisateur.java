import javax.swing.*; //importe les classes du package swing, y compris JOptionPane

public class InteractionUtilisateur {

    public void showError(String message) {
        JOptionPane.showMessageDialog(null,message,"Error",JOptionPane.ERROR_MESSAGE);
    }

    public String requestText(String prompt) {
        return JOptionPane.showInputDialog(prompt);
    }

    public void displayText(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    //méthode pour le choix de l'adversaire
    public String choosePlayerType() {
        String[] playerType = {"humanPlayer", "artificialPlayer"};

        String type = (String) JOptionPane.showInputDialog(
                null,
                "Choose your way to play, HumanPlayer or ArtificialPlayer ?",
                "Choose way to play",
                JOptionPane.QUESTION_MESSAGE,
                null,
                playerType,
                playerType[0]);
        if (type != null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Your way to play is " + type + " !",
                    "Choose way to play",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return type;
    }

    //méthode pour le choix de l'adversaire
    public String choosePlayerTypeOpponent() {
        String[] playerType = {"humanPlayer", "artificialPlayer"};

        String type = (String) JOptionPane.showInputDialog(
                null,
                "Choose your opponent, HumanPlayer or ArtificialPlayer ?",
                "Choose Opponent",
                JOptionPane.QUESTION_MESSAGE,
                null,
                playerType,
                playerType[0]);
        if (type != null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Your opponent is " + type + " !",
                    "Choose Opponent",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return type;
    }

    //méthode pour le choix du symbol
    public String chooseSymbol() {
        String[] symbols = {"X", "O"};

        String optionSymbol = (String) JOptionPane.showInputDialog(
                null,
                "Choose your symbol, X or O ?",
                "Choose Symbol",
                JOptionPane.QUESTION_MESSAGE,
                null,
                symbols,
                symbols[0]);
        if (optionSymbol != null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Your symbol is " + optionSymbol,
                    "Choose Symbol",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return optionSymbol;
    }

    //méthode qui boucle sur choice pour vérifier que l'input est valide
    protected int askNumber(String label) {
        //String userInput = menu.requestText("Please enter a whole number in "+label+" : ");
        int choice = -1;

        boolean isInvalid = true;
        // While user hasn't entered a valid number
        while (isInvalid) {
            try {
                String userInput = requestText("Please enter a whole number in " + label + " : ");
                choice = Integer.parseInt(userInput);
                displayText("You have chosen " + choice + " .");
                if (choice >= 0 && choice <= 2) {
                    isInvalid = false;
                } else {
                    showError("Please enter a number between 0 and 2");
                }

            } catch (NumberFormatException e) {
                showError("Please enter a number ! ");
            }
        }
        return choice;
    }



}
