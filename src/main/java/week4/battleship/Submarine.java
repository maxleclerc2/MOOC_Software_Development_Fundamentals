package week4.battleship;

public class Submarine extends Ship {
    public Submarine() {
        this.setLength(3);
        this.setHit(new boolean[this.getLength()]);
    }

    @Override
    public String getShipType() {
        return "submarine";
    }
}