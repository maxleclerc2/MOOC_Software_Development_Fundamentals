package week4.battleship;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Ocean {
    Ship[][] ships = new Ship[20][20];
    int shotsFired;
    int hitCount;
    int shipsSunk;

    public Ocean() {
        this.shotsFired = 0;
        this.hitCount = 0;
        this.shipsSunk = 0;

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                ships[i][j] = new EmptySea();
            }
        }
    }

    void placeAllShipsRandomly() {
        Ship[] shipsToPlace = new Ship[13];
        shipsToPlace[0] = new BattleShip();
        shipsToPlace[1] = new BattleCruiser();
        shipsToPlace[2] = new Cruiser();
        shipsToPlace[3] = new Cruiser();
        shipsToPlace[4] = new LightCruiser();
        shipsToPlace[5] = new LightCruiser();
        shipsToPlace[6] = new Destroyer();
        shipsToPlace[7] = new Destroyer();
        shipsToPlace[8] = new Destroyer();
        shipsToPlace[9] = new Submarine();
        shipsToPlace[10] = new Submarine();
        shipsToPlace[11] = new Submarine();
        shipsToPlace[12] = new Submarine();

        int shipsPlaced = 0;

        while (shipsPlaced != 13) {
            int randRow = ThreadLocalRandom.current().nextInt(0, 21);
            int randCol = ThreadLocalRandom.current().nextInt(0, 21);
            boolean randBool = new Random().nextBoolean();

            if (shipsToPlace[shipsPlaced].okToPlaceShipAt(randRow, randCol, randBool, this)) {
                shipsToPlace[shipsPlaced].placeShipAt(randRow, randCol, randBool, this);

                shipsPlaced++;
            }
        }

    }

    boolean isOccupied(int row, int column) {
        return !this.ships[row][column].getShipType().equals("empty");
    }

    boolean shootAt(int row, int column) {
        this.shotsFired++;

        // Vérifie qu'il y a un navire
        if (isOccupied(row, column)) {
            // Vérifie que le bateau n'est pas coulé
            if (!this.ships[row][column].isSunk()) {
                // Vérifie qu'on peut tirer
                if (this.ships[row][column].shootAt(row, column)) {
                    this.hitCount++;

                    if (this.ships[row][column].isSunk()) this.shipsSunk++;
                }
                return true;
            }
        } else {
            // Tire dans l'eau pour changer le caractère
            this.ships[row][column].shootAt(row, column);
        }
        return false;
    }

    int getShotsFired() {
        return shotsFired;
    }

    int getHitCount() {
        return hitCount;
    }

    int getShipsSunk() {
        return shipsSunk;
    }

    boolean isGameOver() {
        return getShipsSunk() == 13;
    }

    Ship[][] getShipArray() {
        return ships;
    }

    void print() {
        System.out.println("   0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19");
        for(int i = 0; i < 20; i++) {
            if (i < 10)
                System.out.print(i + " ");
            else
                System.out.print(i);
            for(int j = 0; j < 20; j++) {
                if (this.ships[i][j].getShipType().equals("empty")) {
                    if (j == 19) {
                        System.out.println(" " + ships[i][j] + " ");
                    } else {
                        System.out.print(" " + ships[i][j] + " ");
                    }
                } else {
                    int pos;
                    if (ships[i][j].isHorizontal()) pos = j - ships[i][j].getBowColumn();
                    else pos = i - ships[i][j].getBowRow();

                    String display;
                    if (ships[i][j].getHit()[pos]) display = ships[i][j].toString();
                    else display = "~";

                    if (j == 19) {
                        System.out.println(" " + display + " ");
                    } else {
                        System.out.print(" " + display + " ");
                    }
                }
            }
        }
    }
}
