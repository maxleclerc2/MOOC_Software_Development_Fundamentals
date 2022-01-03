package week4.battleship;

public class Cruiser extends Ship {
    public Cruiser() {
        this.setLength(6);
        this.setHit(new boolean[this.getLength()]);
    }

    @Override
    public String getShipType() {
        return "cruiser";
    }
}