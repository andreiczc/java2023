package ro.ase.lab2.models;

import ro.ase.lab2.exceptions.IllegalMoveException;

public class NotMotorizedVehicle extends Vehicle{

    public NotMotorizedVehicle() {
    }

    @Override
    public void move(int amountToMove) throws IllegalMoveException {
        if(amountToMove > 3 || amountToMove <= 0) {
            throw new IllegalMoveException();
        }

        super.move(amountToMove);
    }

    @Override
    public String toString() {
        return String.format("Not motorized vehicle is placed at position %d",
                position);
    }
}
