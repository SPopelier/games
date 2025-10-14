package player;

public class Player {
    protected String representation;
    protected String playerType;

    public Player(String optionSymbol, String type) {
        this.representation = optionSymbol;
        this.playerType = type;
    }

    public String getRepresentation() {
        return this.representation;
    }

    public String getPlayerType() {
        return this.playerType;
    }

    public void setRepresentation(String optionSymbol) {
        this.representation = optionSymbol;
    }

    public void setPlayerType(String type) {
        this.playerType = type;
    }

}
