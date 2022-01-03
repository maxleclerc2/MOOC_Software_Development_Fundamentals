package week4.battleship;

public class Destroyer extends Ship {
    public Destroyer() {
        this.setLength(4);
        this.setHit(new boolean[this.getLength()]);
    }

    @Override
    public String getShipType() {
        return "destroyer";
    }
}