public class ArtificialPlayer extends Player {
    private String representation;
    private String playerType;

    public ArtificialPlayer(String optionSymbol, String type) {
        super(optionSymbol, type);
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
