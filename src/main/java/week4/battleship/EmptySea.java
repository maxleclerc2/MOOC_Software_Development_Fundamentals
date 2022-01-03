package week4.battleship;

public class EmptySea extends Ship {
    public EmptySea() {
        this.setLength(1);
        this.setHit(new boolean[this.getLength()]);
    }

    @Override
    public boolean shootAt(int row, int column) {
        this.setHit(new boolean[]{true});

        return false;
    }

    @Override
    public boolean isSunk() {
        return false;
    }

    @Override
    public String toString() {
        if (this.getHit()[0]) return "#";
        else return "~";
    }

    @Override
    public String getShipType() {
        return "empty";
    }
}