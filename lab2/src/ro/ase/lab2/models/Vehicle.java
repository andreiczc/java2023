package ro.ase.lab2.models;

import ro.ase.lab2.exceptions.IllegalMoveException;
import ro.ase.lab2.interfaces.Moveable;

public abstract class Vehicle implements Moveable {

    /*
     *  Vehicle -> abstract class
     *  MotorizedVehicle -> move any amount
     *  NotMotorizedVehicle -> can move only 3 spaces at a time
     */

    protected int position;
    protected int[] previousPositions;

    public Vehicle() {
        this.previousPositions = new int[0];
    }

    public int getPosition() {
        return this.position;
    }

    private void storeCurrentPosition() {
        int[] newArray = new int[previousPositions.length + 1];
        for(int i = 0; i < previousPositions.length; ++i) {
            newArray[i] = previousPositions[i];
        }
        newArray[previousPositions.length] = this.position;

        this.previousPositions = newArray;
    }

    public int[] getPreviousPositions() {
        var copy = new int[previousPositions.length];
        for(var i = 0; i < previousPositions.length; ++i) {
            copy[i] = previousPositions[i];
        }

        return copy;
    }

    @Override
    public void move(int amountToMove) throws IllegalMoveException {
        storeCurrentPosition();
        this.position += amountToMove;
    }

    @Override
    public boolean equals(Object obj) {
        // check for null
        if(obj == null) {
            return false;
        }

        // check for self-reference
        if(this == obj) {
            return true;
        }

        Vehicle anotherVehicle = (Vehicle) obj;
        if(this.position != anotherVehicle.position) {
            return false;
        }

        return true;
    }
}
