package ro.ase.lab4.models;

public enum Color {

    BLACK("Black"),
    WHITE("White"),
    RED("Red");

    private String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
