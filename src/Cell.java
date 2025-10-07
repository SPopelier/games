public class Cell {
    private String representation = " ";

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return this.representation;
    }

    public boolean isEmpty() {
        return this.representation.equals(" ");
    }
}
