package ro.ase.lab4.models;

public abstract class Vehicle {

    private static int ID_GENERATOR = 1;

    private int id;
    private int capacity;

    public Vehicle(int capacity) {
        this.id = ID_GENERATOR++;
        this.capacity = capacity;
    }

    public abstract void move(int distance);

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }
}
