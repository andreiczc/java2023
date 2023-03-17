package ro.ase.lab2.models;

import ro.ase.lab2.exceptions.IllegalMoveException;

public class MotorizedVehicle extends Vehicle{

    public MotorizedVehicle() {

    }

    @Override
    public void move(int amountToMove) throws IllegalMoveException {
        if(amountToMove <= 0) {
            throw new IllegalMoveException();
        }

        super.move(amountToMove);
    }

    @Override
    public String toString() {
        return String.format("Motorized vehicle is placed at %d", position);
    }
}
