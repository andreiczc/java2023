package ro.ase.lab2.interfaces;

import ro.ase.lab2.exceptions.IllegalMoveException;

public interface Moveable {

    void move(int amountToMove) throws IllegalMoveException;

}
