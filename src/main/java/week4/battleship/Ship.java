package week4.battleship;

import org.w3c.dom.Text;

import java.awt.*;

public abstract class Ship {
    private int bowRow;
    private int bowColumn;
    private int length;
    private boolean horizontal;
    private boolean[] hit;

    public abstract String getShipType();

    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        // Vérifie que le bateau ne sort pas du tableau
        if (row < 0 || column < 0) return false;
        if (row + length > 19 || column + length > 19) return false;

        if (horizontal) {
            // Vérifie la colonne à gauche de la proue du bateau SI y'en a une
            if (column > 0) {
                if (ocean.isOccupied(row, column - 1)) return false;
            }
            // Vérifie la colonne à droite de la poupe du bateau SI y'en a une
            if (column < 20) {
                if (ocean.isOccupied(row, column + length)) return false;
            }
        } else {
            // Vérifie la ligne au-dessus de la proue du bateau SI y'en a une
            if (row > 0) {
                if (ocean.isOccupied(row - 1, column)) return false;
            }
            // Vérifie la ligne en dessous de la poupe du bateau SI y'en a une
            if (row < 20) {
                if (ocean.isOccupied(row + length, column)) return false;
            }
        }

        for (int k = 0; k < length; k++) {
            if (horizontal) {
                // Vérifie la ligne au-dessus SI y'en a une
                if (row > 0) {
                    if (ocean.isOccupied(row - 1, column + k)) return false;
                }
                if (ocean.isOccupied(row, column + k)) return false;
                // Vérifie la ligne en dessous SI y'en a une
                if (row < 20) {
                    if (ocean.isOccupied(row + 1, column + k)) return false;
                }
            } else {
                // Vérifie la colonne à gauche SI y'en a une
                if (column > 0) {
                    if (ocean.isOccupied(row + k, column - 1)) return false;
                }
                if (ocean.isOccupied(row + k, column)) return false;
                // Vérifie la colonne à droite SI y'en a une
                if (column < 20) {
                    if (ocean.isOccupied(row + k, column + 1)) return false;
                }
            }
        }

        return true;
    }

    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        this.setBowRow(row);
        this.setBowColumn(column);
        this.setHorizontal(horizontal);

        for (int k = 0; k < length; k++) {
            // Horizontal
            if (this.horizontal) ocean.ships[this.bowRow][this.bowColumn + k] = this;
            // Vertical
            else ocean.ships[this.bowRow + k][this.bowColumn] = this;
        }
    }

    public boolean shootAt(int row, int column) {
        int hit;

        if (this.isHorizontal()) hit = column - getBowColumn();
        else hit = row - getBowRow();

        boolean[] hits = this.getHit();
        hits[hit] = true;
        this.setHit(hits);

        return true;
    }

    public boolean isSunk() {
        for (Boolean b: this.getHit()) {
            // S'il y a une case pas touchée, alors le navire n'est pas coulé
            if (!b) return false;
        }
        // S'il n'y a aucune case pas touchée, alors le navire est coulé
        return true;
    }

    @Override
    public String toString() {
        if (isSunk()) return "X";
        else return "S";
    }

    public int getBowRow() {
        return bowRow;
    }

    public void setBowRow(int bowRow) {
        this.bowRow = bowRow;
    }

    public int getBowColumn() {
        return bowColumn;
    }

    public void setBowColumn(int bowColumn) {
        this.bowColumn = bowColumn;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public boolean[] getHit() {
        return hit;
    }

    public void setHit(boolean[] hit) {
        this.hit = hit;
    }
}
